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
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class G {
    static TestHelper fs = new TestHelper();
    static int start = -1, end = 1, point = 0;
    static int seed = 2025;

    public static void main(String[] args) throws Exception {
        int[] in = fs.readStringAsIntArray();
        int M = in[0];
        int N = in[1];
        int[][] arr = new int[N][];
        int idx = 0;
        while (N-- > 0) {
            arr[idx++] = fs.readStringAsIntArray();
        }
        fs.writeOneByOne(getResult(arr, M));
        fs.close();

    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(int[][] in, int M, int[][] out) throws Exception {
        int[][] result = getResult(in, M);
        for (int i = 0; i < out.length; i++) {
            assertArrayEquals(out[i], result[i]);
        }
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(
                        new int[][]{
                                {2, 1, 1},
                                {1, 1, 2},
                        }, 1,
                        new int[][]{
                                {1},
                                {0, 1}
                        }),
                Arguments.of(
                        new int[][]{
                                {1, 1, 1},
                                {1, 1, 1},
                        }, 2,
                        new int[][]{
                                {1},
                                {1, 1}
                        }),
                Arguments.of(
                        new int[][]{
                                {1, 1, 1},
                                {1, 1, 1},
                        }, 0,
                        new int[][]{
                                {0},
                                {0, 0}
                        }),
                Arguments.of(
                        new int[][]{
                                {2, 2, 1},
                                {1, 2, 2},
                        }, 5,
                        new int[][]{
                                {5},
                                {2, 3}
                        }),
                Arguments.of(
                        new int[][]{
                                {1, 1, 1},
                        }, 1000,
                        new int[][]{
                                {1999},
                                {1000}
                        }),
                Arguments.of(
                        new int[][]{
                                {1, 1, 1},
                        }, 10,
                        new int[][]{
                                {19},
                                {10}
                        })
        );
    }

    private static int[][] getResult(int[][] in, int M) {
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < in.length; i++) {
            int[] person = in[i];
            int T = person[0];
            int Z = person[1];
            int Y = person[2];
            int total = M;
            int c = Z;
            int diff = 0;
            int v = 0;
            while (total-- > 0) {
                c--;
                v += T + diff;
                events.add(new Event(v, -1, i));
                if (c == 0) {
                    c = Z;
                    diff = Y;
                } else {
                    diff = 0;
                }
            }
        }

        int[] counter = new int[in.length];
        events.sort(Comparator.comparingInt(Event::val));
        int total = 0;
        int totalTime = 0;
        for (Event event : events) {
            counter[event.idx]++;
            totalTime = event.val;
            total++;
            if (total == M) {
                break;
            }
        }

        int[][] result = new int[2][];
        result[0] = new int[]{totalTime};
        result[1] = counter;
        return result;
    }


    static class Event {
        int val;
        int type;
        int idx;

        public Event(int val, int type, int idx) {
            this.type = type;
            this.idx = idx;
            this.val = val;
        }

        int type() {
            return type;
        }

        int val() {
            return val;
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
