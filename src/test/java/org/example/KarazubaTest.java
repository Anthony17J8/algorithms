package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//3141592653589793238462643383279502884197169399375105820974944592
// 2718281828459045235360287471352662497757247093699959574966967627
public class KarazubaTest {

    @Test
    // 1234
    // 5678
    // x X y = (10n/2 × a + b) × (10n/2 × c + d) =
    //= 10n × (a × c) + 10n/2 × (a × d + b × c) + b × d
    // (a × d) + (b × c) = (a + b) × (c + d) - a × c - b × d
    public void testMulti() {
        double x = 2718281828459045235360287471352662497757247093699959574966967627d;
        double y = 3141592653589793238462643383279502884197169399375105820974944592d;
        assertEquals(7006652, karazubaMulti(x,y));
    }

    // 12, 56
    private double karazubaMulti(double x, double y) {
        String xStr = String.valueOf(x);
        String yStr = String.valueOf(y);
        if (xStr.length() == 1 || yStr.length() == 1) {
            return Integer.parseInt(xStr) * Integer.parseInt(yStr);
        } else {
            double[] ab = split(xStr);
            double[] cd = split(yStr);
            double axc = karazubaMulti(ab[0], cd[0]);
            double bxd = karazubaMulti(ab[1], cd[1]);
            double p = karazubaMulti(ab[0] + ab[1], cd[0] + cd[1]) - axc - bxd;
            return Math.pow(10, xStr.length()) * axc + Math.pow(10, xStr.length() / 2d) * p + bxd;
        }
    }

    private double[] split(String input) {
        if (input.length() == 1) {
            return new double[] {Double.parseDouble(input)};
        }

        int delimiter = input.length() % 2 == 0 ? input.length() / 2 : input.length() / 2 + 1;
        double a = Double.parseDouble(input.substring(0, delimiter));
        double b = Double.parseDouble(input.substring(delimiter));
        return new double[]{a, b};
    }
}
