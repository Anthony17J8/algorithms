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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class H {
    static TestHelper fs = new TestHelper();
    static int start = -1, end = 1, point = 0;
    static int seed = 2025;
    static String WRONG = "Wrong Answer", ACCEPTED = "Accepted";

    public static void main(String[] args) throws Exception {
        int K = fs.nextInt();
        int num = K;
        String[] res = new String[K];
        int idx = 0;
        while (num-- > 0) {
            int[] in = fs.readStringAsIntArray();
            res[idx++] = getResult(in);
        }
        fs.writeAll(res, '\n');
        fs.close();

    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(4)
    void test(int[] in, String out) throws Exception {
        assertEquals(out, getResult(in));
    }

    private static Stream<Arguments> source() {
        int[] ints = TestHelper.generateRandom(20001, 2025);
        ints[0] = 10000;
        ints[1] = 0;
        ints[2] = 10000;
        return Stream.of(
                Arguments.of(new int[]{3, 0, 3000, 2500, 7000, 2700, 10000}, WRONG),
                Arguments.of(new int[]{2, 0, 3000, 2700, 10000}, ACCEPTED),
                Arguments.of(new int[]{3, 0, 3000, 3500, 5000, 4000, 10000}, WRONG),
                Arguments.of(new int[]{3, 0, 3000, 3000, 5000, 4000, 9999}, WRONG),
                Arguments.of(new int[]{3, 0, 3000, 3500, 5000, 4000, 10000}, WRONG),
                Arguments.of(new int[]{2, 0, 5000, 5000, 10000}, ACCEPTED),
                Arguments.of(new int[]{3, 10, 3000, 3000, 5000, 4000, 10000}, WRONG),
                Arguments.of(new int[]{3, 1, 3000, 3000, 5000, 4000, 10000}, WRONG),
                Arguments.of(new int[]{1, 0, 1000}, WRONG),
                Arguments.of(new int[]{1, 0, 10000}, ACCEPTED),
                Arguments.of(new int[]{2, 0, 3000, 0, 10000}, WRONG),
                Arguments.of(new int[]{2, 0, 10000, 3000, 10000}, WRONG),
                Arguments.of(ints, WRONG)
        );
    }

    private static String getResult(int[] in) {
        List<Event> events = new ArrayList<>();
        int idx = 0;
        int pow = 1;
        while (idx < in[0]) {
            events.add(new Event(in[pow], start, idx, in[pow + 1] - in[pow]));
            events.add(new Event(in[pow + 1], end, idx, in[pow] - in[pow + 1]));
            idx++;
            pow += 2;
        }

        events.sort(Comparator.comparing(Event::val).thenComparing(Event::type)
                .thenComparing(Comparator.comparing(Event::len).reversed()));

//        String result = ACCEPTED;
        boolean isSecured = checkSecurity(events);
        if (!isSecured) {
            return WRONG;
        }

        String result = checkSecurityFast(events) ? ACCEPTED : WRONG;
//        int start = 0;
//        int end = (in.length - 1) / 2;
//        while (start < end) {
//            if (!checkSecurityFast(events)) {
//                result = WRONG;
//                break;
//            }
//            start++;
//        }

        return result;
    }

    private static boolean checkSecurity(List<Event> events) {
        Integer st = null;
        Integer en = null;
        int now = 0;
        boolean result = true;
        for (Event event : events) {
            if (event.type == start) {
                if (now == 0 && en != null && en != 10000) {
                    result = false;
                    break;
                }
                now += 1;
                if (st == null) {
                    st = event.val;
                } else {
                    st = Math.min(st, event.val);
                }
            } else if (event.type == end) {
                now -= 1;
                if (en == null) {
                    en = event.val;
                } else {
                    en = Math.max(en, event.val);
                }
            }
        }
        if (en != null && st != null && en - st != 10000) {
            result = false;
        } else if (st == null) {
            result = false;
        }
        return result;
    }

    private static boolean checkSecurity(List<Event> events, int skipId) {
        Integer st = null;
        Integer en = null;
        int now = 0;
        boolean result = true;
        for (Event event : events) {
            if (event.idx == skipId) {
                continue;
            }
            if (event.type == start) {
                if (now == 0 && en != null && en != 10000) {
                    result = false;
                    break;
                }
                now += 1;
                if (st == null) {
                    st = event.val;
                } else {
                    st = Math.min(st, event.val);
                }
            } else if (event.type == end) {
                now -= 1;
                if (en == null) {
                    en = event.val;
                } else {
                    en = Math.max(en, event.val);
                }
            }
        }
        if (en != null && st != null && en - st != 10000) {
            result = false;
        } else if (st == null) {
            result = false;
        }
        return result;
    }

    private static boolean checkSecurityFast(List<Event> events) {
        boolean result = true;
        int current = 0;
        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            if (event.type == end) {
                current--;
            }
            if (event.type == start) {
                if (current > 0) {
                    boolean isSecured = checkOne(events, i, event.idx, current);
                    if (isSecured) {
                        result = false;
                        break;
                    }
                }
                current++;
            }
        }
        return result;
    }

    private static boolean checkOne(List<Event> events, int i, int idx, int current) {
        boolean isSecured = true;
        for (int j = i + 1; events.get(j).idx != idx; j++) {
            Event event = events.get(j);
            if (event.type == end) {
                current--;
            }
            if (event.type == start) {
                current++;
            }
            if (current == 0) {
                isSecured = false;
                break;
            }
        }
        return isSecured;
    }

    static class Event {
        int val;
        int type;
        int idx;
        int len;

        public Event(int val, int type, int idx, int len) {
            this.type = type;
            this.idx = idx;
            this.val = val;
            this.len = len;
        }

        int type() {
            return type;
        }

        int val() {
            return val;
        }

        int len() {
            return len;
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
