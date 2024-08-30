package org.example.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer.
 * <p>
 * The algorithm for myAtoi(string s) is as follows:
 * <p>
 * Whitespace: Ignore any leading whitespace (" ").
 * Signedness: Determine the sign by checking if the next character is '-' or '+', assuming positivity is neither present.
 * Conversion: Read the integer by skipping leading zeros until a non-digit character is encountered or the end of the string is reached. If no digits were read, then the result is 0.
 * Rounding: If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then round the integer to remain in the range. Specifically, integers less than -231 should be rounded to -231, and integers greater than 231 - 1 should be rounded to 231 - 1.
 * Return the integer as the final result.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "42"
 * <p>
 * Output: 42
 * <p>
 * Explanation:
 * <p>
 * The underlined characters are what is read in and the caret is the current reader position.
 * Step 1: "42" (no characters read because there is no leading whitespace)
 * ^
 * Step 2: "42" (no characters read because there is neither a '-' nor '+')
 * ^
 * Step 3: "42" ("42" is read in)
 * ^
 * Example 2:
 * <p>
 * Input: s = " -042"
 * <p>
 * Output: -42
 * <p>
 * Explanation:
 * <p>
 * Step 1: "   -042" (leading whitespace is read and ignored)
 * ^
 * Step 2: "   -042" ('-' is read, so the result should be negative)
 * ^
 * Step 3: "   -042" ("042" is read in, leading zeros ignored in the result)
 * ^
 * Example 3:
 * <p>
 * Input: s = "1337c0d3"
 * <p>
 * Output: 1337
 * <p>
 * Explanation:
 * <p>
 * Step 1: "1337c0d3" (no characters read because there is no leading whitespace)
 * ^
 * Step 2: "1337c0d3" (no characters read because there is neither a '-' nor '+')
 * ^
 * Step 3: "1337c0d3" ("1337" is read in; reading stops because the next character is a non-digit)
 * ^
 * Example 4:
 * <p>
 * Input: s = "0-1"
 * <p>
 * Output: 0
 * <p>
 * Explanation:
 * <p>
 * Step 1: "0-1" (no characters read because there is no leading whitespace)
 * ^
 * Step 2: "0-1" (no characters read because there is neither a '-' nor '+')
 * ^
 * Step 3: "0-1" ("0" is read in; reading stops because the next character is a non-digit)
 * ^
 * Example 5:
 * <p>
 * Input: s = "words and 987"
 * <p>
 * Output: 0
 * <p>
 * Explanation:
 * <p>
 * Reading stops at the first non-digit character 'w'.
 */
public class MyAtoi {

    @Test
    void test() {
        assertEquals(42, myAtoi("42"));
        assertEquals(0, myAtoi("0-1"));
        assertEquals(-42, myAtoi("-042"));
        assertEquals(1337, myAtoi("1337c0d3"));
        assertEquals(0, myAtoi("000000"));
        assertEquals(Integer.MIN_VALUE, myAtoi("-91283472332"));
        assertEquals(1, myAtoi("+1"));
        assertEquals(0, myAtoi("+-12"));
    }

    private int myAtoi(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int result;
        boolean firstDigitRead = false;
        boolean isNegative = false;
        boolean signRead = false;
        s = s.trim();
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            String s1 = String.valueOf(c);
            try {
                int i = Integer.parseInt(s1);
                if (i != 0) {
                    sb.append(s1);
                    firstDigitRead = true;
                } else if (firstDigitRead) {
                    sb.append(s1);
                } else {
                    firstDigitRead = true;
                }
            } catch (NumberFormatException exc) {
                if ((s1.equals("-") || s1.equals("+")) && !signRead && !firstDigitRead) {
                    if (s1.equals("-")) {
                        isNegative = true;
                    }
                    signRead = true;
                } else {
                    break;
                }
            }
        }
        try {
            result = sb.toString().length() > 0 ? Integer.parseInt(sb.toString()) : 0;
        } catch (NumberFormatException exc) {
            return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
        return result * (isNegative ? -1 : 1);
    }
}
