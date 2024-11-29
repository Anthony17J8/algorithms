/*
   Copyright 2020 Russian Post
   <p>
   This source code is Russian Post Confidential Proprietary.
   This software is protected by copyright. All rights and titles are reserved.
   You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
   Otherwise this violation would be treated by law and would be subject to legal prosecution.
   Legal use of the software provides receipt of a license from the right holder only.
 */

package org.example.yandex.algorithms_1_0.lesson2;

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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class J {

    static final String closer = "closer", further = "further";

    static DecimalFormat df;

    static {
        DecimalFormatSymbols instance = DecimalFormatSymbols.getInstance();
        instance.setDecimalSeparator('.');
        df = new DecimalFormat("#.0#####", instance);
    }

    public static void main(String[] args) throws Exception {
        TestHelper fs = new TestHelper();
        int n = fs.nextInt();
        BigDecimal first = BigDecimal.valueOf(fs.nextDouble());
        n--;
        List<Map.Entry<BigDecimal, String>> l = new ArrayList<>();
        while (n > 0) {
            String[] in = fs.next().split(" ");
            l.add(Map.entry(BigDecimal.valueOf(Double.parseDouble(in[0])), in[1]));
            n--;
        }

        fs.writeAll(getResult(first, l));
        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(BigDecimal first, List<Map.Entry<BigDecimal, String>> l, String[] out) throws Exception {
        assertArrayEquals(out, getResult(first, l));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(440.), List.of(
                                Map.entry(BigDecimal.valueOf(220.), closer),
                                Map.entry(BigDecimal.valueOf(300.), further)
                        ),
                        new String[]{"30.0", "260.0"}
                ),
                Arguments.of(BigDecimal.valueOf(440.), List.of(
                                Map.entry(BigDecimal.valueOf(220.), further),
                                Map.entry(BigDecimal.valueOf(300.), closer)
                        ),
                        new String[]{"330.0", "4000.0"}
                ),
                Arguments.of(BigDecimal.valueOf(440.), List.of(
                                Map.entry(BigDecimal.valueOf(220.), closer),
                                Map.entry(BigDecimal.valueOf(300.), closer)
                        ),
                        new String[]{
                                "260.0", "330.0"
                        }
                ),
                Arguments.of(BigDecimal.valueOf(440.), List.of(
                                Map.entry(BigDecimal.valueOf(500.), further),
                                Map.entry(BigDecimal.valueOf(300.), closer)
                        ),
                        new String[]{
                                "30.0", "400.0"
                        }
                ),
                // TODO !!!!!
                Arguments.of(BigDecimal.valueOf(440.), List.of(
                                Map.entry(BigDecimal.valueOf(500.), closer),
                                Map.entry(BigDecimal.valueOf(300.), further)
                        ),
                        new String[]{
                                "470.0", "4000.0"
                        }
                ),
                Arguments.of(BigDecimal.valueOf(440.), List.of(
                                Map.entry(BigDecimal.valueOf(500.), closer),
                                Map.entry(BigDecimal.valueOf(813.), further)
                        ),
                        new String[]{
                                "470.0", "656.5"
                        }
                ),
                Arguments.of(BigDecimal.valueOf(440.), List.of(
                                Map.entry(BigDecimal.valueOf(800.), further),
                                Map.entry(BigDecimal.valueOf(700.), closer)
                        ),
                        new String[]{
                                "30.0", "620.0"
                        }
                ),
                Arguments.of(BigDecimal.valueOf(440.), List.of(
                                Map.entry(BigDecimal.valueOf(912.1214), closer),
                                Map.entry(BigDecimal.valueOf(500.12121), further)
                        ),
                        new String[]{
                                "706.121305", "4000.0"
                        }
                ),
                Arguments.of(BigDecimal.valueOf(554.), List.of(
                                Map.entry(BigDecimal.valueOf(880.), further),
                                Map.entry(BigDecimal.valueOf(440.), closer),
                                Map.entry(BigDecimal.valueOf(622.), closer)
                        ),
                        new String[]{
                                "531.0", "660.0"
                        }
                ),
                Arguments.of(BigDecimal.valueOf(440.1312545), List.of(
                                Map.entry(BigDecimal.valueOf(500.14235345), closer)
                        ),
                        new String[]{
                                "470.136804", "4000.0"
                        }
                )
        );
    }

    private static String[] getResult(BigDecimal first, List<Map.Entry<BigDecimal, String>> l) {
        BigDecimal min = BigDecimal.valueOf(30.);
        BigDecimal max = BigDecimal.valueOf(4000.);
        BigDecimal two = BigDecimal.valueOf(2.);

        for (Map.Entry<BigDecimal, String> e : l) {
            if (e.getValue().equals(closer)) {
                if (e.getKey().compareTo(first) > 0) {
                    BigDecimal add = first.add(e.getKey().subtract(first).divide(two, 6, RoundingMode.HALF_UP));
                    if (add.compareTo(min) > 0) {
                        min = add;
                    }
                } else if (e.getKey().compareTo(first) < 0) {
                    BigDecimal add = e.getKey().add(first.subtract(e.getKey()).divide(two, 6, RoundingMode.HALF_UP));
                    if (add.compareTo(max) < 0) {
                        max = add;
                    }
                }

            } else {
                if (e.getKey().compareTo(first) > 0) {
                    BigDecimal add = first.add(e.getKey().subtract(first).divide(two, 6, RoundingMode.HALF_UP));
                    if (add.compareTo(max) < 0) {
                        max = add;
                    }
                } else if (e.getKey().compareTo(first) < 0) {
                    BigDecimal add = e.getKey().add(first.subtract(e.getKey()).divide(two, 6, RoundingMode.HALF_UP));
                    if (add.compareTo(min) > 0) {
                        min = add;
                    }
                }
            }
            first = e.getKey();
        }

        return new String[]{df.format(min), df.format(max)};
    }

    private static class TestHelper {
        BufferedReader in;
        BufferedWriter out;

        TestHelper() {
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        void write(String s) throws IOException {
            out.write(s);
        }

        void write(long i) throws IOException {
            out.write(String.valueOf(i));
        }

        void writeAll(long[] i) throws IOException {
            for (long el : i) {
                out.write(el + " ");
            }
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

        String next() {
            try {
                return in.readLine();
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
            for (int i = 0; i < size; i++) {
                in[i] = num;
            }
            return in;
        }

        public static int[] generateRandom(int size) {
            //todo
            return new int[]{};
        }
    }
}
