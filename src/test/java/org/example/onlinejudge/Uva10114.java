/*
   Copyright 2020 Russian Post
   <p>
   This source code is Russian Post Confidential Proprietary.
   This software is protected by copyright. All rights and titles are reserved.
   You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
   Otherwise this violation would be treated by law and would be subject to legal prosecution.
   Legal use of the software provides receipt of a license from the right holder only.
 */

package org.example.onlinejudge;

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
import java.math.MathContext;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Uva10114 {

    public static void main(String[] args) throws Exception {
        TestHelper fs = new TestHelper();
        StringBuilder sb = new StringBuilder();
        while (true) {
            String[] in = fs.readStringAsStringArray();
            int n = Integer.parseInt(in[0]);
            if (n < 0) {
                break;
            }
            int result = 0;
            BigDecimal fixed = new BigDecimal(in[1], MathContext.DECIMAL64);
            BigDecimal loan = new BigDecimal(in[2], MathContext.DECIMAL64);
            int m = Integer.parseInt(in[3]);
            Map<Integer, String> map = getMap(m, fs);
            BigDecimal sum = fixed.add(loan, MathContext.DECIMAL64);
            int month = 1;
            BigDecimal prevPercent = new BigDecimal(map.get(0), MathContext.DECIMAL64);
            BigDecimal total = sum.multiply(BigDecimal.ONE.subtract(prevPercent), MathContext.DECIMAL64);
            BigDecimal mod = loan;
            while (n-- > 0) {
                BigDecimal percent = map.containsKey(month) ? new BigDecimal(map.get(month),
                        MathContext.DECIMAL64) : prevPercent;
                BigDecimal newTotal = total.multiply(BigDecimal.ONE.subtract(percent), MathContext.DECIMAL64);
                BigDecimal newMod = mod.subtract(fixed, MathContext.DECIMAL64);
                if (newMod.compareTo(newTotal) < 0) {
                    result = month;
                    break;
                } else {
                    total = newTotal;
                    mod = newMod;
                }
                prevPercent = percent;
                month++;
            }
            sb.append(result).append("\n");
        }
        fs.write(sb.toString());
        fs.close();
    }

    private static Map<Integer, String> getMap(int m, TestHelper fs) {
        Map<Integer, String> res = new HashMap<>();
        while (m-- > 0) {
            String[] intervals = fs.readStringAsStringArray();
            res.put(Integer.parseInt(intervals[0]), intervals[1]);
        }
        return res;
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(int n, String fixed, String loan, String[][] intervals, String out) throws Exception {
        assertEquals(out, getResult(n, fixed, loan, intervals));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(30, "500.0", "15000.0", new String[][]{
                        {"0", ".10"},
                        {"1", ".03"},
                        {"3", ".002"},
                }, "4 months")

        );
    }

    private static String getResult(int n, String fixed, String loan, String[][] intervals) {
        String result = "";

        return result;
    }

    private static class TestHelper {
        BufferedReader in;
        BufferedWriter out;

        TestHelper() {
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        boolean ready() throws IOException {
            return in.ready();
        }

        void write(String s) throws IOException {
            out.write(s);
            out.flush();
        }

        void write(long i) throws IOException {
            out.write(String.valueOf(i));
        }

        void writeAll(long[] i) throws IOException {
            for (long el : i) {
                out.write(el + " ");
            }
        }

        void writeAll(int[] i) throws IOException {
            for (int el : i) {
                out.write(el + " ");
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
            return Stream.iterate("A", s -> s + "A")
                    .limit(size)
                    .reduce((first, sec) -> sec)
                    .get();
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
