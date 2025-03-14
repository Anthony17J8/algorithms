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

public class G {
    static TestHelper fs = new TestHelper();

    public static void main(String[] args) throws Exception {
        long n = fs.nextLong();
        long m = fs.nextLong();
        long t = fs.nextLong();
        fs.write(getResult(n, m, t));
        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(long n, long m, long t, long out) throws Exception {
        assertEquals(out, getResult(n, m, t));
        assertEquals(out, getResultV2(n, m, t));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(5, 8, 25, 1),
                Arguments.of(5, 8, 19, 0),
                Arguments.of(5, 8, 40, 2),
                Arguments.of(6, 7, 38, 2),
                Arguments.of(3, 3, 10, 1),
                Arguments.of(3, 3, 1, 0),
                Arguments.of(3, 4, 1, 0),
                Arguments.of(3, 3, 100, 1),
                Arguments.of(3, 3, 3, 0),
                Arguments.of(3, 3, 8, 1),
                Arguments.of(2 * 1000000000, 2 * 1000000000, (long) 2 * 1000000000 * 2 * 1000000000, 999999999)
        );
    }

    private static long getResult(long n, long m, long t) {
        long minS = Math.min(n, m);
        long minW = 1;
//        long maxS = Math.max(m, n);
        long maxW = (minS - (minS % 2 == 0 ? 2 : 1)) / 2;
        long res = 0;

        while (minW <= maxW) {
            long mid = (maxW + minW) / 2;
            long N = (n - mid * 2);
            long M = (m - mid * 2);
            long NXM = N * M; // кол-во плиток
            if (n * m - NXM < t) {
                minW = mid + 1;
                res = mid;
            } else if (n * m - NXM > t) {
                maxW = mid - 1;
            } else {
                res = mid;
                break;
            }
        }
        return res;
    }

    private static long getResultV2(long n, long m, long t) {
        BigDecimal nd = new BigDecimal(n);
        BigDecimal md = new BigDecimal(m);
        BigDecimal td = new BigDecimal(t);
        BigDecimal minS = nd.compareTo(md) < 0 ? nd : md;
        BigDecimal minW = BigDecimal.ONE;
//        long maxS = Math.max(m, n);
        BigDecimal maxW = (minS.subtract(minS.longValue() % 2 == 0 ? new BigDecimal(2) : BigDecimal.ONE)).divide(
                new BigDecimal(2));
        BigDecimal res = BigDecimal.ZERO;

        while (minW.compareTo(maxW) <= 0) {
            BigDecimal mid = maxW.add(minW).divide(new BigDecimal(2), 0, RoundingMode.HALF_UP);
            BigDecimal N = (nd.subtract(mid.multiply(new BigDecimal(2))));
            BigDecimal M = (md.subtract(mid.multiply(new BigDecimal(2))));
            BigDecimal NXM = N.multiply(M); // кол-во плиток
            if (nd.multiply(md).subtract(NXM).compareTo(td) < 0) {
                minW = mid.add(BigDecimal.ONE);
                res = mid;
            } else if (nd.multiply(md).subtract(NXM).compareTo(td) > 0) {
                maxW = mid.subtract(BigDecimal.ONE);
            } else {
                res = mid;
                break;
            }
        }
        return res.longValue();
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
