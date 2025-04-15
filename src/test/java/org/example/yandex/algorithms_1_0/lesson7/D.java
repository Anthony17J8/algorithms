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

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class D {
    static TestHelper fs = new TestHelper();
    static int start = -1, end = 1, point = 0;
    static int seed = 2025;
    static int timeOn = 5;

    public static void main(String[] args) throws Exception {

        int N = fs.nextInt();
        int[][] arr = new int[N][];
        int idx = 0;
        while (N-- > 0) {
            arr[idx++] = fs.readStringAsIntArray();
        }

        fs.writeAll(getResult(arr));
        fs.close();

    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(int[][] in, int[] outArr) throws Exception {
        assertArrayEquals(outArr, getResult(in));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new int[][]{
                        {1, 11},
                        {1, 3},
                        {6, 15},
                        {1, 6}
                }, new int[]{3, 1, 6}),
                Arguments.of(new int[][]{
                        {1, 10},
                }, new int[]{1, 1, 6}),
                Arguments.of(new int[][]{
                        {1, 10},
                        {11, 20},
                        {21, 30}
                }, new int[]{2, 5, 15}),
                Arguments.of(new int[][]{
                        {1, 10},
                        {3, 8},
                        {11, 16}
                }, new int[]{3, 3, 11}),
                Arguments.of(new int[][]{
                        {1, 7},
                        {3, 8},
                        {11, 16}
                }, new int[]{2, 2, 11}),
                Arguments.of(new int[][]{
                        {1, 20},
                        {2, 6},
                        {4, 10},
                        {5, 10}
                }, new int[]{3, 5, 25}),
                Arguments.of(new int[][]{
                        {1, 20},
                        {2, 6},
                        {5, 10},
                        {5, 10}
                }, new int[]{3, 5, 25}),
                Arguments.of(new int[][]{
                        {1, 10},
                        {1, 6},
                        {4, 13},
                        {7, 14}
                }, new int[]{4, 1, 8}),
                Arguments.of(new int[][]{
                        {1, 6},
                        {1, 7},
                        {1, 8},
                        {1, 9}
                }, new int[]{4, 1, 14}),
                Arguments.of(new int[][]{
                        {1, 6},
                        {3, 10},
                        {3, 10}
                }, new int[]{2, 5, 15}),
                Arguments.of(new int[][]{
                                {1, 100},
                                {80, 90},
                                {80, 90}
                        }, new int[]{3, 85, 105},
                        Arguments.of(new int[][]{
                                {1, 100},
                                {1, 6},
                                {1, 8},
                                {1, 9},
                                {1, 10},
                                {50, 60},
                                {51, 61},
                                {51, 58}
                        }, new int[]{8, 1, 53})
                )
        );
    }


    private static int[] getResult(int[][] in) {
        Set<Integer> nowInShopClients = new HashSet<>();
        int best1 = 0;
        int best2 = 0;
        int thebest = 0;

        List<Event> events = new ArrayList<>();
        for (int i = 0; i < in.length; i++) {
            if (in[i][1] - in[i][0] >= 5) {
                events.add(new Event(in[i][0], start, i));
                events.add(new Event(in[i][1] - 5, end, i));
            }

        }
        if (events.isEmpty()) {
            return new int[]{0, 1, 6};
        }

        events.sort(Comparator.comparing(Event::number).thenComparing(Event::type));

        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            if (event.type == start) {
                nowInShopClients.add(event.idx);
                if (nowInShopClients.size() > thebest) {
                    thebest = nowInShopClients.size();
                    best1 = event.number;
                    best2 = event.number + timeOn;
                }
            }

            int counter = 0;
            for (int j = i + 1; j < events.size(); j++) {
                Event event2 = events.get(j);
                if (event2.type == start && !nowInShopClients.contains(event2.idx)) {
                    counter++;
                }
                if (event2.number - event.number >= timeOn && nowInShopClients.size() + counter > thebest) {
                    thebest = nowInShopClients.size() + counter;
                    best1 = event.number;
                    best2 = event2.number;
                }
                if (event2.type == end && !nowInShopClients.contains(event2.idx)) {
                    counter--;
                }
            }
            if (event.type == end) {
                nowInShopClients.remove(event.idx);
            }
        }
        return new int[]{thebest, best1, best2};
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
