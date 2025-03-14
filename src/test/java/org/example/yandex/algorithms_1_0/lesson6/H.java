/*
   Copyright 2020 Russian Post
   <p>
   This source code is Russian Post Confidential Proprietary.
   This software is protected by copyright. All rights and titles are reserved.
   You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
   Otherwise this violation would be treated by law and would be subject to legal prosecution.
   Legal use of the software provides receipt of a license from the right holder only.
 */

package org.example.yandex.algorithms_1_0.lesson6;

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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class H {
    static TestHelper fs = new TestHelper();

    public static void main(String[] args) throws Exception {
        int[] in = fs.readStringAsIntArray();
        int idx = 0;
        int n = in[0];
        int k = in[1];
        long[] N = new long[n];
        while (n-- > 0) {
            N[idx++] = fs.nextLong();
        }
        fs.write(getResult(N, k));
        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(long[] n, long k, long out) throws Exception {
        assertEquals(out, getResult(n, k));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new long[]{2, 7}, 2, 3),
                Arguments.of(new long[]{802, 743, 457, 539}, 11, 200),
                Arguments.of(new long[]{10}, 5, 2),
                Arguments.of(new long[]{1, 1}, 1, 1),
                Arguments.of(new long[]{100}, 2, 50),
                Arguments.of(new long[]{1, 1}, 2, 1),
                Arguments.of(new long[]{1, 2}, 2, 1),
                Arguments.of(new long[]{2, 2}, 5, 0),
                Arguments.of(new long[]{2, 2}, 4, 1),
                Arguments.of(new long[]{6, 9}, 4, 3),
                Arguments.of(new long[]{2, 7}, 3, 2),
                Arguments.of(new long[]{6, 25}, 4, 6),
                Arguments.of(new long[]{1}, 2, 0),
                Arguments.of(new long[]{10000}, 10, 1000),
                Arguments.of(new long[]{10_000_000}, 2, 5000000),
                Arguments.of(new long[]{10_000_000, 5}, 2, 5000000),
                Arguments.of(TestHelper.generateLong(10_000, 2), 1, 2),
                Arguments.of(TestHelper.generateLong(10_000, 2), 10_000, 2)

        );
    }

    private static long getResult(long[] n, long k) {
        long sum = Arrays.stream(n).sum();
        long min = Arrays.stream(n).max().getAsLong() * 2;
        if (sum < k) return 0;

        long from = 1;
        long to = min;
        long res = from;
        while (from <= to) {
            long mid = (from + to) / 2;
            long r = getAll(n, mid);
            if (r > k) {
                from = mid + 1;
                res = mid;
            } else if (r < k) {
                to = mid - 1;
            } else {
                from = mid + 1;
                res = mid;
            }
        }
        return res;
    }

    private static long getAll(long[] n, long l) {
        long res = 0L;
        for (long s : n) {
            res += new BigDecimal(s).divide(new BigDecimal(l), 0, RoundingMode.DOWN).longValue();
        }
        return res;
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
                return Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
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

        public static int[] range(int from, int to) {
            return IntStream.range(from, to).toArray();
        }

        public static int[] generateRandom(int size) {
            //todo
            return new int[]{};
        }
    }
}
