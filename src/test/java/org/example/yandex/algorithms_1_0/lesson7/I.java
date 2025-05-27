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

public class I {
    static TestHelper fs = new TestHelper();
    static int start = -1, end = 1, point = 0;
    static int seed = 2025;

    public static void main(String[] args) throws Exception {
        int[] in = fs.readStringAsIntArray();
        int N = in[0];
        int M = in[1];
        int num = M;
        int idx = 0;
//
        List<Event> events = new ArrayList<>(M);
        while (num-- > 0) {
           addEvents(events, fs.readStringAsStringArray(), idx);
            idx++;
        }
        fs.write(getResult(events));
        fs.close();
    }

//    public static void main(String[] args) throws Exception {
//        String[] in = fs.readFromFile().toList().toArray(new String[]{});
//        String[] n = in[0].split(" ");
//        int N = Integer.parseInt(n[0]);
//        int M = Integer.parseInt(n[1]);
//        int num = M;
//        int idx = 0;
//
//        List<Event> events = new ArrayList<>(M);
//        while (num-- > 0) {
//           addEvents(events, in[idx + 1].split(" "), idx);
//            idx++;
//        }
//        fs.write(getResult(events));
//        fs.close();
//
//    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(2)
    void test(String[][] in, int out) throws Exception {
//        assertEquals(out, getResult(in));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new String[][]{
                        {"2", "20:00", "1", "10:00"},
                        {"1", "08:00", "2", "21:00"}
                }, 3),
                Arguments.of(new String[][]{
                        {"1", "09:00", "2", "20:00"},
                        {"2", "20:00", "1", "09:00"}
                }, 1),
                Arguments.of(new String[][]{
                        {"3", "03:52", "1", "08:50"},
                        {"1", "18:28", "3", "21:53"},
                        {"2", "03:58", "3", "09:00"},
                        {"3", "14:59", "2", "21:13"}
                }, 2),
                Arguments.of(new String[][]{
                        {"1", "12:37", "2", "14:56"},
                        {"2", "14:58", "3", "15:07"},
                        {"1", "11:34", "3", "15:23"},
                        {"3", "16:00", "1", "19:23"}
                }, -1),
                Arguments.of(new String[][]{
                        {"1", "03:52", "2", "08:50"},
                        {"1", "04:52", "2", "01:50"},
                }, -1)
        );
    }

    private static void addEvents(List<Event> events, String[] row, int idx) {
        int start = Integer.parseInt(row[0]);
        String[] stSplit = row[1].split(":");
        int startTime = Integer.parseInt(stSplit[0]) * 60 + Integer.parseInt(stSplit[1]);
        events.add(new Event(startTime, 1, start, idx, false));
//            left.add(start);
        int end = Integer.parseInt(row[2]);
        String[] endSplit = row[3].split(":");
        int endTime = Integer.parseInt(endSplit[0]) * 60 + Integer.parseInt(endSplit[1]);
        events.add(new Event(endTime, -1, end, idx, startTime > endTime));
    }

    private static int getResult(List<Event> events) {
//        if (!left.equals(right)) {
//            return -1;
//        }

        events.sort(Comparator.comparing(Event::val).thenComparing(Event::type));
        int count3 = 0;
//        boolean[] busesActive = new boolean[in.length];
        int busesIdx = 0;
        Map<Integer, Integer> stations = new HashMap<>();
        for (int i = 0; i < events.size() * 3; i++) {
            int ix = i % events.size();
            if (i / events.size() == 2 && ix == 0) {
                count3 = busesIdx;
            }
            Event event = events.get(ix);
            if (event.type == 1) {
                if (stations.containsKey(event.city) && stations.get(event.city) > 0) {
                    stations.computeIfPresent(event.city, (key, value) -> value - 1);
                } else {
                    busesIdx++;
                }
//                busesActive[event.idx] = true;
            } else if (event.type == -1 && !(i / events.size() < 1 && event.skipEnd)) {
                if (!stations.containsKey(event.city)) {
                    stations.put(event.city, 1);
                } else {
                    stations.put(event.city, stations.get(event.city) + 1);
                }
            }
        }
        if (busesIdx == count3) return busesIdx;
        else return -1;
    }

    private static List<Event> getEvents(String[][] in) {
        List<Event> events = new ArrayList<>(in.length);
        int idx = 0;
//        Set<Integer> left = new HashSet<>();
//        Set<Integer> right = new HashSet<>();
        for (String[] row : in) {

//            right.add(end);
            idx++;
        }
        return events;
    }

    static class Event {
        int val;
        int type;
        int idx;
        int city;
        boolean skipEnd;

        public Event(int val, int type, int city, int idx, boolean skipEnd) {
            this.type = type;
            this.idx = idx;
            this.val = val;
            this.city = city;
            this.skipEnd = skipEnd;
        }

        int type() {
            return type;
        }

        int val() {
            return val;
        }

        int city() {
            return city;
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
            return Files.lines(Path.of("src/test/java/org/example/yandex/algorithms_1_0/lesson7/1.txt"));
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
