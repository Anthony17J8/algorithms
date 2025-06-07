
/*
   Copyright 2020 Russian Post
   <p>
   This source code is Russian Post Confidential Proprietary.
   This software is protected by copyright. All rights and titles are reserved.
   You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
   Otherwise this violation would be treated by law and would be subject to legal prosecution.
   Legal use of the software provides receipt of a license from the right holder only.
 */

package org.example.yandex.algorithms_1_0.lesson8;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class I {
    static TestHelper fs = new TestHelper();
    static int seed = 2025;

    public static void main(String[] args) throws Exception {
        int N = fs.nextInt();
        int idx = 0;
        Tree tree = new Tree(N);
        while (idx++ < N - 1) {
            String[] in = fs.readStringAsStringArray();
            tree.addNode(in[0]);
            tree.addNode(in[1]);
            Node child = tree.addNode(in[0]);
            Node parent = tree.addNode(in[1]);
            map.put(child, parent);
            incrementall(parent, child.children);
        }

        Map.Entry<String, Integer>[] result = getResult(tree, N);
        Arrays.stream(result).forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));
        fs.close();
    }

    static int idx = 0;

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(String[] in, Map.Entry<String, Integer>[] out) throws Exception {
        idx = 0;
        map.clear();
        assertArrayEquals(out, getResult(tree(in), in.length + 1));
    }

    static Map<Node, Node> map = new HashMap<>();

    static Tree tree(String[] in) {
        Tree tree = new Tree(in.length);
        for (String s : in) {
            String[] split = s.split(" ");
            Node child = tree.addNode(split[0]);
            Node parent = tree.addNode(split[1]);
            map.put(child, parent);
            incrementall(parent, child.children);
        }
        return tree;
    }

    private static void incrementall(Node parent, int childrenNum) {
        Node cur = parent;
        while (cur != null) {
            cur.incChildren(childrenNum);
            cur = map.get(cur);
        }
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new String[]{
                                "Alexei Peter_I",
                                "Anna Peter_I",
                                "Elizabeth Peter_I",
                                "Peter_II Alexei",
                                "Peter_III Anna",
                                "Paul_I Peter_III",
                                "Alexander_I Paul_I",
                                "Nicholaus_I Paul_I"},
                        new Map.Entry[]{
                                Map.entry("Alexander_I", 0),
                                Map.entry("Alexei", 1),
                                Map.entry("Anna", 4),
                                Map.entry("Elizabeth", 0),
                                Map.entry("Nicholaus_I", 0),
                                Map.entry("Paul_I", 2),
                                Map.entry("Peter_I", 8),
                                Map.entry("Peter_II", 0),
                                Map.entry("Peter_III", 3)
                        }
                ),
                Arguments.of(new String[]{
                                "AICHNG ZLNYXGO",
                                "BDLHBJZWY BELAFXWA",
                                "COXUC WVWNNC",
                                "ELPPUINHJ BELAFXWA",
                                "HIOSZOHQWG AICHNG",
                                "UVRRAVHX BDLHBJZWY",
                                "WVWNNC ELPPUINHJ",
                                "YKYWCJBAX BDLHBJZWY",
                                "ZLNYXGO BDLHBJZWY"},
                        new Map.Entry[]{
                                Map.entry("AICHNG", 1),
                                Map.entry("BDLHBJZWY", 5),
                                Map.entry("BELAFXWA", 9),
                                Map.entry("COXUC", 0),
                                Map.entry("ELPPUINHJ", 2),
                                Map.entry("HIOSZOHQWG", 0),
                                Map.entry("UVRRAVHX", 8),
                                Map.entry("WVWNNC", 1),
                                Map.entry("YKYWCJBAX", 0),
                                Map.entry("ZLNYXGO", 2)
                        }
                )
        );
    }

    private static Map.Entry<String, Integer>[] getResult(Tree tree, int size) {
        Map.Entry[] res = new Map.Entry[size];
        Node current = tree.root;
        dfs(current, res);
        return res;
    }

    private static void dfs(Node node, Map.Entry<String, Integer>[] out) {
        if (node == null) {
            return;
        }
        dfs(node.left, out);
        out[idx++] = Map.entry(node.key, node.children);
        dfs(node.right, out);
    }

    static class Node {
        String key;
        int idx;
        Node left;
        Node right;
        int children = 0;

        public Node(String key, int idx, Node left, Node right) {
            this.key = key;
            this.idx = idx;
            this.left = left;
            this.right = right;
        }

        public void incChildren(int childChildren) {
            children += childChildren + 1;
        }
    }

    static class Tree {
        List<Node> elements;
        Node root;
        int currentIdx = 0;
        //0 - index
        //1 - key
        //2 - left
        //3 - right
        //4 - lvl

        public Tree(int size) {
            this.elements = new ArrayList<>(size);
        }

        public Node addNode(String key) {
            if (root == null) {
                Node element = new Node(key, currentIdx, null, null);
                root = element;
                elements.add(currentIdx, element);
                currentIdx++;
                return element;
            } else {
                Node parent = root;
                Node newElement = null;
                while (parent != null) {
                    if (elements.get(parent.idx).key.compareTo(key) > 0) {
                        if (elements.get(parent.idx).left == null) {
                            Node newNode = new Node(key, currentIdx, null, null);
                            elements.add(currentIdx, newNode);
                            parent.left = newNode;
                            currentIdx++;
                            newElement = newNode;
                            break;
                        } else {
                            parent = elements.get(parent.idx).left;
                        }
                    } else if (elements.get(parent.idx).key.compareTo(key) < 0) {
                        if (elements.get(parent.idx).right == null) {
                            Node newNode = new Node(key, currentIdx, null, null);
                            elements.add(currentIdx, newNode);
                            parent.right = newNode;
                            currentIdx++;
                            newElement = newNode;
                            break;
                        } else {
                            parent = elements.get(parent.idx).right;
                        }
                    } else {
                        newElement = elements.get(parent.idx);
                        break;
                    }
                }
                return newElement;
            }
        }
    }


    private static class TestHelper {
        BufferedReader in;
        BufferedWriter out;

        TestHelper() {
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        boolean isReady() throws Exception {
            return in.ready();
        }

        Stream<String> readFromFile() throws IOException {
            return Files.lines(Path.of("src/test/java/org/example/yandex/algorithms_1_0/lesson4/input.txt"));
//            return Files.lines(Path.of("input.txt"));
        }

        void write(String s) throws IOException {
            out.write(s);
        }

        void write(long l) throws IOException {
            out.write(String.valueOf(l));
        }

        void write(int i) throws IOException {
            out.write(String.valueOf(i));
        }

        void writeAll(long[] i) throws IOException {
            for (long el : i) {
                out.write(el + " ");
            }
        }

        void writeAll(long[] i, char sep) throws IOException {
            for (long el : i) {
                out.write(el + "" + sep);
            }
        }

        void writeAll(int[] i) throws IOException {
            for (int el : i) {
                out.write(el + " ");
            }
        }

        void writeAll(int[] i, char sep) throws IOException {
            for (int el : i) {
                out.write(el + "" + sep);
            }
        }

        void writeAll(Iterable<String> it) {
            it.forEach(o -> {
                try {
                    newLine();
                    write(o);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        void writeAll(String[] w) throws IOException {
            for (String b : w) {
                out.write(b + " ");
            }
        }

        void writeAll(String[] w, char sep) throws IOException {
            for (String b : w) {
                out.write(b + sep);
            }
        }

        void writeAllMultiline(String[] s) {
            Arrays.stream(s)
                    .filter(Objects::nonNull)
                    .forEach(System.out::println);
        }

        void writeOneByOne(char[][] in) throws IOException {
            for (int i = 0; i < in.length; i++) {
                for (int j = 0; j < in[0].length; j++) {
                    out.write(in[i][j] + " ");
                }
                out.write("\n");
            }
        }

        void newLine() throws IOException {
            out.newLine();
        }

        String next() {
            try {
                return in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        int[] readStringAsIntArray() {
            try {
                return Arrays.stream(in.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        long[] readStringAsLongArray() {
            try {
                return Arrays.stream(in.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        String[] readStringAsStringArray() {
            try {
                return in.readLine().split(" ");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        void close() throws Exception {
            out.flush();
            in.close();
            out.close();
        }

        public static int[] generateInt(int size, int num) {
            int[] in = new int[size];
            Arrays.fill(in, num);
            return in;
        }

        public static String generate(int size) {
            return Stream.generate(() -> "a")
                    .limit(size)
                    .collect(Collectors.joining());
        }

        public static long[] generateLong(int size, int num) {
            long[] in = new long[size];
            Arrays.fill(in, num);
            return in;
        }

        public static long[] generateRandomLong(int size, int seed) {
            long[] in = new long[size];
            Random random = new Random(seed);
            Arrays.fill(in, random.nextLong());
            return in;
        }

        public static long[] generateRandomLong(int size) {
            Random random = new Random(seed++);
            long[] in = random.longs(1, 100).limit(size).toArray();
            Arrays.sort(in);
            return in;
        }

        public static int[] range(int from, int to) {
            return IntStream.range(from, to).toArray();
        }

        public static int[] generateRandomSorted(int size, int seed) {
            Random random = new Random(seed);
            int[] array = random.ints(1, 20).limit(size).toArray();
            Arrays.sort(array);
            return array;
        }

        public static int[] generateRandom(int size, int seed) {
            Random random = new Random(seed);
            return random.ints(1, 10).limit(size).toArray();
        }

        public static int[][] generateTwoDimensionalRandom(int row, int col) {
            int[][] res = new int[row][col];
            for (int i = 0; i < row; i++) {
                res[i] = generateRandom(col, i);
            }
            return res;
        }
    }
}
