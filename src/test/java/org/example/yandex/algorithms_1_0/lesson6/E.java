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

public class E {
    static TestHelper fs = new TestHelper();

    public static void main(String[] args) throws Exception {
        long a = fs.nextLong();
        long b = fs.nextLong();
        long c = fs.nextLong();
        fs.write(getResult2(a, b, c));
        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(long a, long b, long c, long out) throws Exception {
//        assertEquals(out, getResult(a, b, c));
        assertEquals(out, getResult2(a, b, c));

    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(2, 0, 0, 2),
                Arguments.of(0, 0, 1, 0),
                Arguments.of(0, 2, 2, 0),
                Arguments.of(0, 1, 0, 1),
                Arguments.of(1, 1, 0, 2),
                Arguments.of(1000, 1000, 1000, 1000),
                Arguments.of(10000, 10000, 10000, 10000),
                Arguments.of(2, 2, 2, 2),
                Arguments.of(2, 0, 0, 2),
                Arguments.of(1000000000000000L, 1000000000000000L, 1000000000000000L, 1000000000000000L),
                Arguments.of(1000000000000000L, 0, 0, 1000000000000000L),
                Arguments.of(0, 1000000000000000L, 0, 333333333333334L),
                Arguments.of(0, 0, 1000000000000000L, 0)

        );
    }

    private static long getResult2(long a, long b, long c) {
        BigDecimal A = new BigDecimal(a);
        BigDecimal B = new BigDecimal(b);
        BigDecimal C = new BigDecimal(c);
        BigDecimal max = A.add(B);
        BigDecimal min = new BigDecimal(0);
        BigDecimal res = new BigDecimal(0);
        while (min.compareTo(max) <= 0) {
            var mid = max.add(min).divide(new BigDecimal(2), 0, RoundingMode.HALF_UP);
            var av = avg2(A, B, C, mid);
            if (av < 4) {
                min = mid.add(new BigDecimal(1));
            } else if (av > 5) {
                max = mid.subtract(BigDecimal.ONE);
            } else {
                res = mid;
                max = mid.subtract(BigDecimal.ONE);
            }
        }
        return res.longValue();
    }

    private static long getResult(long a, long b, long c) {
        long max = a + b;
        long min = 0;
        long res = 0;
        while (min <= max) {
            long mid = (max + min) / 2;
            System.out.println(mid);
            long av = avg(a, b, c, mid);
            System.out.println(av);
            if (av < 4) {
                min = mid + 1;
            } else if (av > 5) {
                max = mid - 1;
            } else {
                res = mid;
                max = mid - 1;
            }
        }
        return res;
    }

    private static long avg2(BigDecimal count2, BigDecimal count3, BigDecimal count4, BigDecimal count5) {
        BigDecimal res = (count2.multiply(new BigDecimal(2)).add(count3.multiply(new BigDecimal(3)))
                .add(count4.multiply(new BigDecimal(4)))
                .add(count5.multiply(new BigDecimal(5)))).divide(count2.add(count3).add(count4).add(count5), 0, RoundingMode.HALF_UP);
        return res.longValue();
    }

    private static long avg(long count2, long count3, long count4, long count5) {
        return Math.round(
                (2d * count2 + 3 * count3 + 4 * count4 + count5 * 5) / (count3 + count2 + count4 + count5));
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
