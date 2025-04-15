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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class E {
    static TestHelper fs = new TestHelper();
    static int start = -1, end = 1, point = 0;
    static int seed = 2025;

    public static void main(String[] args) throws Exception {

        int N = fs.nextInt();
        int[][] arr = new int[N][];
        int idx = 0;
        while (N-- > 0) {
            arr[idx++] = fs.readStringAsIntArray();
        }
        fs.write(getResult(arr));
        fs.close();

    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(int[][] in, int out) throws Exception {
        assertEquals(out, getResult(in));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new int[][]{
                        {1, 0, 23, 0},
                        {12, 0, 12, 0},
                        {22, 0, 2, 0},
                }, 120),
                Arguments.of(new int[][]{

                        {1, 0, 1, 0},
                        {3, 1, 3, 1},
                        {3, 2, 3, 0},
                }, 2),
                Arguments.of(new int[][]{
                        {1, 0, 2, 0},
                }, 60),
                Arguments.of(new int[][]{
                        {1, 0, 1, 0},
                }, 1440),
                Arguments.of(new int[][]{
                        {23, 0, 1, 0},
                }, 120),
                Arguments.of(new int[][]{
                        {9, 30, 14, 0},
                        {14, 15, 21, 0},
                }, 0),
                Arguments.of(new int[][]{
                        {14, 00, 18, 00},
                        {10, 00, 14, 01},
                }, 1)
        );
    }


    private static int getResult(int[][] in) {
        List<Event> events = new ArrayList<>();

        if (in.length == 1) {
            int s1 = in[0][0] * 60 + in[0][1];
            int s2 = in[0][2] * 60 + in[0][3];
            if (s1 == s2) {
                return 60 * 24;
            }
            if (s2 < s1) {
                return 60 * 24 - (s1 - s2);
            }
        }

        for (int i = 0; i < in.length; i++) {
            events.add(new Event(in[i][0] * 60 + in[i][1], start, i));
            events.add(new Event(in[i][2] * 60 + (in[i][3] - 1), end, i));
        }

        events.sort(Comparator.comparing(Event::time).thenComparing(Event::type));
        Event startE = null;
        Map<String, Integer> intvls = new HashMap<>();
        boolean[] isOn = new boolean[in.length];
        int cnt = 0;
        for (int i = 0; i < events.size() * 2; i++) {
            int idx = i % events.size();
            Event e = events.get(idx);
            if (e.type == start) {
                isOn[e.idx] = true;
                if (++cnt == in.length) {
                    startE = e;
                }
            } else if (e.type == end && isOn[e.idx]) {
                isOn[e.idx] = false;
                if (cnt == in.length && !intvls.containsKey("" + e.time + startE.time)) {
                    intvls.put("" + e.time + startE.time, e.time + 1 - startE.time);
                }
                cnt--;
            }
        }

        return intvls.values().stream().mapToInt(Integer::intValue).sum();
    }


    static class Event {
        int time;
        int type;
        int idx;

        public Event(int time, int type, int idx) {
            this.time = time;
            this.type = type;
            this.idx = idx;
        }

        int time() {
            return time;
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
