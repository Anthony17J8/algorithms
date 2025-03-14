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
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class C {
    static TestHelper fs = new TestHelper();

    public static void main(String[] args) throws Exception {
        int[] in = fs.readStringAsIntArray();
        int w = in[0];
        int h = in[1];
        int n = in[2];
        fs.write(getResultDec(w, h, n));
        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(long w, long h, long n, long out) throws Exception {
//        assertEquals(out, getResult(w, h, n));
        assertEquals(out, getResultDec(w, h, n));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(2, 3, 10, 9),
                Arguments.of(2, 3, 1, 3),
                Arguments.of(2, 3, 6, 6),
                Arguments.of(2, 3, 2, 4),
                Arguments.of(2, 3, 8, 8),
                Arguments.of(2, 3, 11, 9),
                Arguments.of(2, 3, 12, 9),
                Arguments.of(2, 3, 13, 10),
                Arguments.of(1, 1, 1, 1),
                Arguments.of(1, 1, 2, 2),
                Arguments.of(1000000000, 1000000000, 1000000000, 31623000000000L),
                Arguments.of(60, 1, 1, 60)

        );
    }

    private static long getResult(long w, long h, long n) {
        long s = (long) w * h * n;
        long l = 0;
        long r = s;
        long val = s;
        boolean found = false;
        while (l < r) {
            long mid = (l + r) / 2;
            long sq = mid * mid;
            if (sq > s) {
                if (check(mid, w, h, n)) {
                    val = mid;
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else if (sq < s) {
                l = mid + 1;
            } else {
                found = true;
                val = mid;
                break;
            }
        }

        if (!found && check(r, w, h, n)) val = r;
        return val;

    }

    private static boolean check(long sq, long w, long h, long n) {
        return ((sq / w) * (sq / h)) >= n;
    }

    private static boolean check(BigInteger sq, BigInteger w, BigInteger h, BigInteger n) {
        return ((sq.divide(w)).multiply (sq.divide(h))).compareTo(n) >= 0;
    }

    private static long getResultDec(long w, long h, long n) {
        BigInteger bi = new BigInteger(String.valueOf(w));
        BigInteger bh = new BigInteger(String.valueOf(h));
        BigInteger bn = new BigInteger(String.valueOf(n));
        BigInteger b = bi.multiply(bh).multiply(bn);
        BigInteger l = BigInteger.ZERO;
        BigInteger r = b;
        BigInteger val = b;
        boolean found = false;
        while (l.compareTo(r) < 0) {
            BigInteger mid = l.add(r).divide(new BigInteger("2"));
            BigInteger sq = mid.multiply(mid);
            if (sq.compareTo(b) > 0) {
                if (check(mid, bi, bh, bn)) {
                    val = mid;
                    r = mid.subtract(BigInteger.ONE);
                } else {
                    l = mid.add(BigInteger.ONE);
                }
            } else if (sq.compareTo(b) < 0) {
                l = mid.add(BigInteger.ONE);
            } else {
                found = true;
                val = mid;
                break;
            }
        }

        if (!found && check(r, bi, bh, bn)) val = r;
        return val.longValue();

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
