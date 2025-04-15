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

public class B {
    static TestHelper fs = new TestHelper();
    static int start = -1, end = 1, point = 0;
    static int seed = 2025;

    public static void main(String[] args) throws Exception {

        int[] in = fs.readStringAsIntArray();
        int N = in[0];
        int M = in[1];
        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            arr[i] = fs.readStringAsIntArray();
        }
        int[] points = fs.readStringAsIntArray();
        fs.writeAll(getResult(arr, points), ' ');
        fs.close();

    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(int[][] in, int[] points, int[] out) throws Exception {
        assertArrayEquals(out, getResult(in, points));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new int[][]{
                        {0, 5},
                        {-3, 2},
                        {7, 10}
                }, new int[]{1, 6}, new int[]{2, 0}),
                Arguments.of(new int[][]{
                        {0, 5},
                        {2, -3},
                        {7, 10}
                }, new int[]{1, 6}, new int[]{2, 0}),
                Arguments.of(new int[][]{
                        {1, 1},
                        {2, 2},
                        {3, 3}
                }, new int[]{1, 2, 3, 4}, new int[]{1, 1, 1, 0}),
                Arguments.of(new int[][]{
                        {1, 2},
                        {2, 3},
                        {2, 5}
                }, new int[]{1, 2, 3, 4}, new int[]{1, 3, 2, 1}),
                Arguments.of(new int[][]{
                        {-10, 10}
                }, new int[]{-100, 100, 0}, new int[]{0, 0, 1})

        );
    }


    private static int[] getResult(int[][] in, int[] points) {
        int counts = 0;
        int[] result = new int[points.length];
        List<Event> events = new ArrayList<>();
        for (int[] arr : in) {
            events.add(new Event(Math.min(arr[0], arr[1]), start, -1));
            events.add(new Event(Math.max(arr[0], arr[1]), end, -1));
        }
        for (int i = 0 ; i < points.length; i++) {
            events.add(new Event(points[i], point, i));
        }

        events.sort(Comparator.comparing(Event::number).thenComparing(Event::type));

        for (Event e : events) {
            if (e.type == start) {
                counts++;
            } else if (e.type == point) {
                result[e.idx] = counts;

            } else if (e.type == end) {
                counts--;
            }
        }
        return result;
    }

    static class Event {
        int number;
        int type;
        int idx;

        public Event(int number, int type, int idx) {
            this.number = number;
            this.type = type;
            this.idx = idx;
        }

        int number() {
            return number;
        }

        int type() {
            return type;
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
