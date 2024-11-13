/*
   Copyright 2020 Russian Post
   <p>
   This source code is Russian Post Confidential Proprietary.
   This software is protected by copyright. All rights and titles are reserved.
   You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
   Otherwise this violation would be treated by law and would be subject to legal prosecution.
   Legal use of the software provides receipt of a license from the right holder only.
 */

package org.example.yandex.algorithms_1_0.lesson1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class LinEquation {

    public static final int SCALE = 6;
    static String yes = "YES", no = "NO";
    static DecimalFormat format = new DecimalFormat("0.######");

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        double a = Double.parseDouble(reader.readLine());
        double b = Double.parseDouble(reader.readLine());
        double c = Double.parseDouble(reader.readLine());
        double d = Double.parseDouble(reader.readLine());
        double e = Double.parseDouble(reader.readLine());
        double f = Double.parseDouble(reader.readLine());

        for (double i : getResult(a, b, c, d, e, f)) {
            writer.write(format.format(i).replace(',', '.') + " ");
        }
        writer.flush();
        reader.close();
        writer.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    void test(int num, double a, double b, double c, double d, double e, double f, double[] out) {
        assertArrayEquals(out, getResult(a, b, c, d, e, f));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
//                Arguments.of(1, 1, 0, 0, 1, 3, 3, new double[]{2, 3, 3}),
//                Arguments.of(1, 1, 1, 2, 2, 1, 2, new double[]{1, -1, 1}),
//                Arguments.of(1, 0, 2, 0, 4, 1, 2, new double[]{4, 0.5}),
//                Arguments.of(1, 3, 0, 9, 0, 1, 3, new double[]{3, 0.333333}),
//                Arguments.of(1, 1, 1, 2, -3, 12, 14, new double[]{2, 10, 2}),
//                Arguments.of(1, 2, -3, 6, -9, 7, 12, new double[]{0}),
//                Arguments.of(1, 0, 0, 0, 0, 0, 0, new double[]{5}),
//                Arguments.of(1, 0, 0, 1, 0, 1, 0, new double[]{0}),
//                Arguments.of(1, 0, 0, 1, 0, 0, 1, new double[]{3, 1}),
//                Arguments.of(1, 2, 3, 4, 6, 1, 2, new double[]{1, -0.666667, 0.333333}),
//                Arguments.of(1, 0, 0, 0, 0, 0, 1, new double[]{0}),
//                Arguments.of(1, 0, 0, 1, 1, 1, 2, new double[]{0}),
//                Arguments.of(1, 1, -1, -1, 1, 0, 0, new double[]{1, 1, 0}),
//                Arguments.of(1, 0, 1, 0, 1, 1, 1, new double[]{4, 1}),
//                Arguments.of(1, 0, 0, 0, 0, 0, 0.1, new double[]{0}),
//                Arguments.of(1, 0, 0, 1, 2, 0, 3, new double[]{1, -0.5, 1.5}),
                Arguments.of(1, 0, 0, 0, 2, 0, 3, new double[]{4, 1.5})

        );
    }

    private static double[] getResult(double a, double b, double c, double d, double e, double f) {
        BigDecimal biga = new BigDecimal(a);
        BigDecimal bigb = new BigDecimal(b);
        BigDecimal bigc = new BigDecimal(c);
        BigDecimal bigd = new BigDecimal(d);
        BigDecimal bige = new BigDecimal(e);
        BigDecimal bigf = new BigDecimal(f);


        if (bigb.equals(BigDecimal.ZERO) && biga.equals(BigDecimal.ZERO) && bigc.equals(
                BigDecimal.ZERO) && bigd.equals(BigDecimal.ZERO) && bigf.equals(BigDecimal.ZERO) && bige.equals(
                BigDecimal.ZERO)) {
            return new double[]{5};
        }

        if (!bigb.equals(BigDecimal.ZERO) && !biga.equals(BigDecimal.ZERO) && !bigc.equals(
                BigDecimal.ZERO) && !bigd.equals(BigDecimal.ZERO)) {
            if (biga.divide(bigc, SCALE, RoundingMode.HALF_UP).equals(bigb.divide(bigd, SCALE, RoundingMode.HALF_UP)) &&
                    !bige.equals(BigDecimal.ZERO) && !bigf.equals(BigDecimal.ZERO) &&
                    biga.divide(bigc, SCALE, RoundingMode.HALF_UP)
                            .equals(bige.divide(bigf, SCALE, RoundingMode.HALF_UP))
            ) {
                return new double[]{1, biga.divide(bigb, SCALE, RoundingMode.HALF_UP)
                        .negate().doubleValue(), bige.divide(
                        bigb, SCALE,
                        RoundingMode.HALF_UP).doubleValue()};
            }
            if (biga.divide(bigc, SCALE, RoundingMode.HALF_UP).equals(bigb.divide(bigd, SCALE, RoundingMode.HALF_UP)) &&
                    (bige.equals(BigDecimal.ZERO) || bigf.equals(BigDecimal.ZERO))
            ) {
                return new double[]{1, biga.divide(bigb, SCALE, RoundingMode.HALF_UP)
                        .negate().doubleValue(), BigDecimal.ZERO.doubleValue()};
            }

            if (biga.divide(bigc, SCALE, RoundingMode.HALF_UP).equals(bigb.divide(bigd, SCALE, RoundingMode.HALF_UP)) &&
                    !biga.divide(bigc, SCALE, RoundingMode.HALF_UP)
                            .equals(bige.divide(bigf, SCALE, RoundingMode.HALF_UP))
            ) {
                return new double[]{0};
            }

            if (!biga.divide(bigc, SCALE, RoundingMode.HALF_UP)
                    .equals(bigb.divide(bigd, SCALE, RoundingMode.HALF_UP))) {
                BigDecimal divider = biga.multiply(bigd).subtract(bigb.multiply(bigc));
                BigDecimal x = new BigDecimal(bige.multiply(bigd).subtract(bigf.multiply(bigb)).divide(
                        divider, SCALE, RoundingMode.HALF_UP).doubleValue());
                BigDecimal y = new BigDecimal(biga.multiply(bigf).subtract(bige.multiply(bigc)).divide(
                        divider, SCALE, RoundingMode.HALF_UP).doubleValue());
                return new double[]{2, x.doubleValue(), y.doubleValue()};
            }
        }
        if (biga.equals(BigDecimal.ZERO) && bigb.equals(BigDecimal.ZERO)) {
            if (!bige.equals(BigDecimal.ZERO)) {
                return new double[]{0};
            } else {
                if (!bigc.equals(BigDecimal.ZERO) && !bigd.equals(BigDecimal.ZERO) && !bigf.equals(BigDecimal.ZERO))
                    return new double[]{1, bigc.divide(bigd, SCALE, RoundingMode.HALF_UP).negate().doubleValue(),
                            bigf.divide(bigd, SCALE, RoundingMode.HALF_UP).doubleValue()};
            }
        }
        if (bigc.equals(BigDecimal.ZERO) && bigd.equals(BigDecimal.ZERO)) {
            if (!bigf.equals(BigDecimal.ZERO)) {
                return new double[]{0};
            } else {
                if (!biga.equals(BigDecimal.ZERO) && !bigb.equals(BigDecimal.ZERO) && !bige.equals(BigDecimal.ZERO))
                    return new double[]{1, biga.divide(bigb, SCALE, RoundingMode.HALF_UP).negate().doubleValue(),
                            bige.divide(bigb, SCALE, RoundingMode.HALF_UP).doubleValue()};
            }
        }

        if (biga.equals(BigDecimal.ZERO) && bigc.equals(BigDecimal.ZERO)) {
            if (!bigb.equals(BigDecimal.ZERO) && !bigd.equals(BigDecimal.ZERO)) {
                BigDecimal y1 = BigDecimal.valueOf(bige.divide(bigb, SCALE, RoundingMode.HALF_UP).doubleValue());
                BigDecimal y2 = BigDecimal.valueOf(bigf.divide(bigd, SCALE, RoundingMode.HALF_UP).doubleValue());
                if (y1.equals(y2)) {
                    return new double[]{4, y1.doubleValue()};
                }
            } else {
                if (bigb.equals(bige) && bigd.equals(bigf)) {
                    return new double[]{4, bige.add(bigf).doubleValue()};
                } else {
                    return new double[]{0};
                }
            }
        }

        if (bigb.equals(BigDecimal.ZERO) && bigd.equals(BigDecimal.ZERO)) {
            if (!biga.equals(BigDecimal.ZERO) && !bigc.equals(BigDecimal.ZERO)) {
                BigDecimal x1 = BigDecimal.valueOf(bige.divide(biga, SCALE, RoundingMode.HALF_UP).doubleValue());
                BigDecimal x2 = BigDecimal.valueOf(bigf.divide(bigc, SCALE, RoundingMode.HALF_UP).doubleValue());
                if (x1.equals(x2)) {
                    return new double[]{3, x1.doubleValue()};
                }
            } else {
                if (biga.equals(bige) && bigc.equals(bigf)) {
                    return new double[]{3, bige.add(bigf).doubleValue()};
                } else {
                    return new double[]{0};
                }
            }
        }
        BigDecimal divider = biga.multiply(bigd).subtract(bigb.multiply(bigc));
        if (divider.equals(BigDecimal.ZERO)) {
            return new double[]{0};
        }
        BigDecimal x = new BigDecimal(bige.multiply(bigd).subtract(bigf.multiply(bigb)).divide(
                divider, SCALE, RoundingMode.HALF_UP).doubleValue());
        BigDecimal y = new BigDecimal(biga.multiply(bigf).subtract(bige.multiply(bigc)).divide(
                divider, SCALE, RoundingMode.HALF_UP).doubleValue());
        return new double[]{2, x.doubleValue(), y.doubleValue()};
    }
}
