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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class F {
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
        fs.writeOneByOne(getResult(arr));
        fs.close();

    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(int[][] in, int[][] out) throws Exception {
        int[][] result = getResult(in);
        for (int i = 0; i < out.length; i++) {
            assertArrayEquals(out[i], result[i]);
        }
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(
                        new int[][]{
                                {2, 5, 1988, 13, 11, 2005},
                                {1, 1, 1, 1, 1, 30},
                                {1, 1, 1910, 1, 1, 1990},
                        }, new int[][]{
                                {2},
                                {3}
                        }),
                Arguments.of(
                        new int[][]{
                                {1, 1, 1, 1, 1, 20},
                                {1, 1, 30, 1, 1, 60},
                        }, new int[][]{
                                {1},
                                {2}
                        }),
                Arguments.of(
                        new int[][]{
                                {1, 1, 1, 1, 1, 60},
                                {1, 1, 20, 1, 1, 40},
                        }, new int[][]{
                                {1, 2},
                        }),
                Arguments.of(
                        new int[][]{
                                {1, 1, 1, 1, 1, 40},
                                {1, 1, 20, 1, 1, 60},
                        }, new int[][]{
                                {1, 2},
                        }),
                Arguments.of(
                        new int[][]{
                                {2, 5, 1968, 13, 11, 2005},
                                {1, 1, 1, 1, 1, 30},
                                {1, 1, 1910, 1, 1, 1990},
                        }, new int[][]{
                                {2},
                                {1, 3}
                        }),
                Arguments.of(
                        new int[][]{
                                {2, 5, 1988, 13, 11, 2005},
                                {1, 1, 1, 1, 1, 10},
                                {2, 1, 1910, 1, 1, 1920},
                        }, new int[][]{
                                {0},
                        }),
                Arguments.of(
                        new int[][]{
                                {2, 5, 1988, 13, 11, 2005},
                                {1, 1, 1940, 1, 1, 1970},
                                {2, 1, 1910, 1, 1, 2010},
                        }, new int[][]{
                                {2, 3},
                        }),
                Arguments.of(
                        new int[][]{
                                {2, 5, 1950, 13, 11, 2005},
                                {1, 1, 1940, 1, 1, 1970},
                                {2, 1, 1910, 1, 1, 2010},
                        }, new int[][]{
                                {1, 2, 3},
                        }),
                Arguments.of(
                        new int[][]{
                                {1, 1, 1, 1, 1, 89},
                                {1, 1, 50, 1, 1, 100},
                                {1, 1, 82, 1, 1, 300},
                        }, new int[][]{
                                {1, 2},
                                {3},
                        })
        );
    }


    /**
     * год, номер которого кратен 400, — високосный;
     * остальные годы, номер которых кратен 100, — невисокосные (например, годы 1700, 1800, 1900, 2100, 2200, 2300);
     * остальные годы, номер которых кратен 4, — високосные;
     * все остальные годы — невисокосные
     */
    private static int[][] getResult(int[][] in) {
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < in.length; i++) {
            int[] ev = in[i];
            LocalDate y18 = LocalDate.of(ev[2], ev[1], ev[0]).plusYears(18);
            LocalDate death = LocalDate.of(ev[5], ev[4], ev[3]);
            LocalDate end = y18.plusYears(80);
            if (y18.isBefore(death)) {
                events.add(new Event(y18, -1, i + 1));
                events.add(new Event(end.isBefore(death) ? end : death, 1, i + 1));
            }
        }
        if (events.isEmpty()) {
            return new int[][]{{0}};
        }

        events.sort(Comparator.comparing(Event::date).thenComparing(Event::type));

        Map<Integer, Set<Integer>> result = new HashMap<>();
        Set<Integer> current = new HashSet<>();
        for (int i = 0; i < events.size(); i++) {
            Event ev = events.get(i);
            if (ev.type == start) {
                List<Integer> temp = new ArrayList<>();
                Set<Integer> now = new HashSet<>();
                now.add(ev.idx);
                for (int j = i + 1; j < events.size(); j++) {
                    Event ev2 = events.get(j);
                    if (ev2.type == end && ev2.idx == ev.idx) {
                        if (now.size() > current.size()) {
                            result.put(ev.idx, now);
                            temp.forEach(v -> result.get(v).remove(ev.idx));
                        }
                        break;
                    }
                    if (ev2.type == end && result.containsKey(ev2.idx)) {
                        temp.add(ev2.idx);
                        now.remove(ev2.idx);
                    } else if (ev2.type == start) {
                        now.add(ev2.idx);
                        if (now.size() > current.size()) {
                            result.put(ev.idx, now);
                            temp.forEach(v -> result.get(v).remove(ev.idx));
                        }
                    }
                }

                current.add(ev.idx);
            } else if (ev.type == end) {
                current.remove(ev.idx);
            }
        }

        int extIdx = 0;
        int[][] res = new int[result.size()][];
        for (Set<Integer> values : result.values()) {
            int[] rc = new int[values.size()];
            int idx = 0;
            for (Integer integer : values) {
                rc[idx++] = integer;
            }
            res[extIdx++] = rc;
        }
        return res;
    }


    static class Event {
        LocalDate date;
        int type;
        int idx;

        public Event(LocalDate date, int type, int idx) {
            this.type = type;
            this.idx = idx;
            this.date = date;
        }

        int type() {
            return type;
        }

        LocalDate date() {
            return date;
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
