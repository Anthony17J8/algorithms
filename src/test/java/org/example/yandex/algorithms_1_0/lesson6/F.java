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
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class F {
    static TestHelper fs = new TestHelper();

    public static void main(String[] args) throws Exception {
        long[] in = fs.readStringAsLongArray();
        long a = in[0];
        long b = in[1];
        long c = in[2];
        fs.write(getResultV2(a, b, c));
        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(long a, long b, long c, long out) throws Exception {
        assertEquals(out, getResultV2(a, b, c));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(4, 1, 1, 3),
                Arguments.of(5, 1, 1, 3),
                Arguments.of(5, 1, 2, 4),
                Arguments.of(1, 10, 9, 9),
                Arguments.of(2 * 100000000, 10, 10, 1000000010),
                Arguments.of(200, 10, 10, 1010),
                Arguments.of(2, 10, 10, 20),
                Arguments.of(3, 10, 10, 20)
        );
    }

    private static long getResult(long n, long x, long y) {
        long minOneCopy = Math.min(x, y); // минимальное время для 1 копии
        long min = 0;
        long max = n * x + n * y;
        long res = max;
        while (min <= max) {
            long mid = (min + max) / 2;
            long X = mid / x;
            long Y = mid / y;
            if (X + Y + minOneCopy > n) {
                max = mid - 1;
                res = mid + 1;
            } else if (X + Y < n) {
                min = mid + 1;
            } else {
                res = mid + 1;
            }
        }
        return res;
    }

    private static long getResultV2(long n, long x, long y) {
        BigDecimal N = new BigDecimal(n);
        BigDecimal X = new BigDecimal(x);
        BigDecimal Y = new BigDecimal(y);
        BigDecimal minOneCopy = X.compareTo(Y) < 0 ? X : Y; // минимальное время для 1 копии
        BigDecimal min = BigDecimal.ZERO;
        BigDecimal max = N.multiply(X).add(N.multiply(Y));
        BigDecimal res = minOneCopy;
        while (min.compareTo(max) <= 0) {
            //кол-секунд
            BigDecimal mid = (min.add(max)).divide(new BigDecimal(2), 0, RoundingMode.UP);
            // кол-во страниц за mid
            BigDecimal Xnew = mid.divide(X, 0, RoundingMode.DOWN);
            // кол-во страниц за mid
            BigDecimal Ynew = mid.divide(Y, 0, RoundingMode.DOWN);
            if (Xnew.add(Ynew).add(BigDecimal.ONE).compareTo(N) > 0) {
                max = mid.subtract(BigDecimal.ONE);
                res = mid;
            } else if (Xnew.add(Ynew).add(BigDecimal.ONE).compareTo(N) < 0) {
                min = mid.add(BigDecimal.ONE);
            } else {
                res = mid;
                max = mid.subtract(BigDecimal.ONE);;
            }
        }
        return res.add(minOneCopy).longValue();
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
