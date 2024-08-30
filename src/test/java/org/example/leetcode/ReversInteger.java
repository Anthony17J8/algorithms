package org.example.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
 * <p>
 * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: x = 123
 * Output: 321
 * Example 2:
 * <p>
 * Input: x = -123
 * Output: -321
 * Example 3:
 * <p>
 * Input: x = 120
 * Output: 21
 */
public class ReversInteger {

    @Test
    void test() {
        assertEquals(123, reverse(321));
        assertEquals(-321, reverse(-123));
        assertEquals(21, reverse(120));
        assertEquals(21, reverse(1534236469));
    }

    public int reverse(int x) {
        if (x == 0) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        boolean isNeg = x < 0;
        x = Math.abs(x);
        while (x > 0) {
            sb.append(x % 10);
            x /= 10;
        }
        try {
            return (isNeg ? -1 : 1) * Integer.parseInt(sb.toString());
        } catch (NumberFormatException exc) {
            return 0;
        }
    }
}
