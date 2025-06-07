/*
   Copyright 2020 Russian Post
   <p>
   This source code is Russian Post Confidential Proprietary.
   This software is protected by copyright. All rights and titles are reserved.
   You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
   Otherwise this violation would be treated by law and would be subject to legal prosecution.
   Legal use of the software provides receipt of a license from the right holder only.
 */

package org.example.yandex.algorithms_1_0.lesson7;

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
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class J {
    static TestHelper fs = new TestHelper();
    static int start = 1, end = -1, point = 0;
    static int seed = 2025;
    static String NO = "NO", YES = "YES";

    public static void main(String[] args) throws Exception {
        int[] in = fs.readStringAsIntArray();
        int N = in[0];
        int W = in[1];
        int L = in[2];
        int[][] input = new int[N][6];
        int idx = 0;
        int num = N;
        while (num-- > 0) {
            input[idx++] = fs.readStringAsIntArray();
        }
        Result result = getResult(input, W, L);
        fs.write(result.can);
        if (result.can.equals("YES")) {
            fs.newLine();
            fs.write(result.minused);
            fs.newLine();
            fs.writeAll(result.blocks);
        }
        fs.close();

    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(2)
    void test(int[][] in, int W, int L, String out) throws Exception {
        Result result = getResult(in, W, L);
        assertEquals(out, result.can);
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new int[][]{
                        {0, 0, 0, 10, 10, 10},
                }, 10, 10, YES),
                Arguments.of(new int[][]{
                        {0, 0, 0, 10, 5, 5},
                        {0, 5, 5, 10, 10, 10},
                }, 10, 10, NO)
        );
    }

    private static Result getResult(int[][] in, int w, int l) {
        List<Event> events = new ArrayList<>();
        int minused = in.length + 1;
        int totalArea = w * l;
        for (int i = 0; i < in.length; i++) {
            int x1 = in[i][0];
            int y1 = in[i][1];
            int z1 = in[i][2];
            int x2 = in[i][3];
            int y2 = in[i][4];
            int z2 = in[i][5];
            int area = Math.abs(x2 - x1) * Math.abs(y2 - y1);
            events.add(new Event(z1, start, i + 1, area));
            events.add(new Event(z2, end, i + 1, area));
        }

        events.sort(Comparator.comparing(Event::z).thenComparing(Event::type));
        int curarea = 0;
        int blocks = 0;
        for (int i = 0; i < events.size(); i++) {
            Event e = events.get(i);
            if (e.type == start) {
                curarea += e.area;
                blocks++;
            } else if (e.type == end) {
                curarea -= e.area;
                blocks--;
            }
            if (curarea == totalArea && blocks < minused) {
                minused = blocks;
            }
        }
        if (minused == in.length + 1) {
            return new Result("NO");
        }

        Set<Integer> numBlocks = new HashSet<>();
        for (int i = 0; i < events.size(); i++) {
            Event e = events.get(i);
            if (e.type == start) {
                curarea += e.area;
                numBlocks.add(e.idx);
                blocks++;
            } else if (e.type == end) {
                curarea -= e.area;
                numBlocks.remove(e.idx);
                blocks--;
            }
            if (curarea == totalArea && blocks == minused) {
                break;
            }
        }
        return new Result("YES", minused,
                Arrays.stream(numBlocks.toArray(new Integer[0])).mapToInt(Integer::intValue).sorted().toArray());
    }

    static class Result {
        String can;
        int minused;
        int[] blocks;

        public Result(String can) {
            this.can = can;
        }

        public Result(String can, int minused, int[] blocks) {
            this.can = can;
            this.minused = minused;
            this.blocks = blocks;
        }
    }

    static class Event {
        int z;
        int type;
        int idx;
        int area;

        public Event(int z, int type, int idx, int area) {
            this.type = type;
            this.idx = idx;
            this.z = z;
            this.area = area;
        }

        int type() {
            return type;
        }

        int z() {
            return z;
        }

        int area() {
            return area;
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

        void writeOneByOne(int[][] in) throws IOException {
            for (int i = 0; i < in.length; i++) {
                for (int j = 0; j < in[i].length; j++) {
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
            return random.ints(0, 10000).limit(size).toArray();
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
