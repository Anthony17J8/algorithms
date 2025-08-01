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
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class C {
    static TestHelper fs = new TestHelper();
    static int seed = 2025;

    public static void main(String[] args) throws Exception {
        fs.write(getResult(fs.readStringAsIntArray()));
        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(int[] in, int out) throws Exception {
        assertEquals(out, getResult(in));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new int[]{7, 3, 2, 1, 9, 5, 4, 6, 8, 0}, 8),
                Arguments.of(new int[]{2, 1}, 1),
                Arguments.of(new int[]{1, 2}, 1)
        );
    }


    private static int getResult(int[] in) {
        Tree tree = new Tree(in.length);
        for (int j = 0; j < in.length; j++) {
            int i = in[j];
            if (i == 0 && j == in.length - 1) {
                break;
            }
            tree.addNode(i);
        }
        return tree.max2;
    }

    static class Tree {
        Integer[][] elements;
        Integer root;
        int currentIdx = 0;
        Integer max;
        Integer max2;
        //0 - index
        //1 - key
        //2 - left
        //3 - right
        //4 - lvl

        public Tree(int size) {
            this.elements = new Integer[size][5];
            for (int i = 0; i < size; i++) {
                elements[i] = new Integer[]{i, null, null, null, null};
            }
        }

        public void addNode(int key) {
            if (root == null) {
                root = currentIdx;
                elements[currentIdx][1] = key;
                elements[currentIdx][4] = 1;
                max = key;
                currentIdx++;
            } else {
                Integer parent = root;
                while (parent != null) {
                    if (elements[parent][1] > key) {
                        if (elements[parent][2] == null) {
                            elements[currentIdx][1] = key;
                            int curlvl = elements[parent][4] + 1;
                            elements[currentIdx][4] = curlvl;
                            elements[parent][2] = currentIdx;
                            currentIdx++;
                            if (max2 == null) {
                                Integer temp = max;
                                max = Math.max(max, key);
                                max2 = Math.min(temp, key);
                            } else {
                                if (max < key) {
                                    Integer temp = max;
                                    max = key;
                                    max2 = temp;
                                } else if (max2 < key) {
                                    max2 = key;
                                }
                            }
                                break;
                        } else {
                            parent = elements[parent][2];
                        }
                    } else if (elements[parent][1] < key) {
                        if (elements[parent][3] == null) {
                            elements[currentIdx][1] = key;
                            int curlvl = elements[parent][4] + 1;
                            elements[currentIdx][4] = curlvl;
                            elements[parent][3] = currentIdx;
                            currentIdx++;
                            if (max2 == null) {
                                Integer temp = max;
                                max = Math.max(max, key);
                                max2 = Math.min(temp, key);
                            } else {
                                if (max < key) {
                                    Integer temp = max;
                                    max = key;
                                    max2 = temp;
                                } else if (max2 < key) {
                                    max2 = key;
                                }
                            }
                            break;
                        } else {
                            parent = elements[parent][3];
                        }
                    } else {
                        break;
                    }
                }
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
