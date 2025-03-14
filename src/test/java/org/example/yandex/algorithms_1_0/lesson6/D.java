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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class D {
    static TestHelper fs = new TestHelper();

    public static void main(String[] args) throws Exception {
        long[] in = fs.readStringAsLongArray();
        long n = in[0];
        long a = in[1];
        long b = in[2];
        long w = in[3];
        long h = in[4];
        fs.write(getResult(n, a, b, w, h));
        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(long n, long a, long b, long w, long h, long out) throws Exception {
        assertEquals(out, getResult(n, a, b, w, h));

    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(1, 1, 1, 1, 1, 0),
                Arguments.of(1, 1, 1, 2, 2, 0),
                Arguments.of(1, 1, 1, 4, 4, 1),
                Arguments.of(1, 1, 1, 3, 3, 1),
                Arguments.of(11, 3, 2, 21, 25, 2),
                Arguments.of(1, 1, 1, 1000000000, 999999997, 499999998),
                Arguments.of(1, 1, 1, 10, 7, 3),
                Arguments.of(1, 1, 1, 100, 97, 48),
                Arguments.of(3, 1, 2, 1000000000000000000L, 1000000000000000000L, 249999999999999999L),
                Arguments.of(309, 13, 20, 15748036, 499528215, 2424887),
                Arguments.of(1000000000000000000L, 1000000000000000000L, 1000000000000000000L, 1000000000000000000L,
                        1000000000000000000L, 0)

        );
    }

    private static long getResult(long n, long a, long b, long w, long h) {
        return Math.max(getRes(n, a, b, w, h), getRes(n, a, b, h, w));
    }

    private static long getRes(long n, long a, long b, long w, long h) {
        long dmin = 0;
        long dmax = Math.min((w - a) / 2, (h - b) / 2) * 2;
        long res = dmin;
        while (dmin <= dmax) {
            long d = (dmax + dmin) / 2;
            long newSizeA = a + 2 * d;
            long newSizeB = b + 2 * d;
            long nums = (w / newSizeA) * (h / newSizeB);
            if (nums > n) {
                dmin = d + 1;
                res = d;
            } else if (nums < n) {
                dmax = d - 1;
            } else {
                res = d;
                dmin = d + 1;
            }
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

        public static int[] generate(int size, int num) {
            int[] in = new int[size];
            Arrays.fill(in, num);
            return in;
        }

        public static String generate(int size) {
            return Stream.generate(() -> "a")
                    .limit(size)
                    .collect(Collectors.joining());
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
