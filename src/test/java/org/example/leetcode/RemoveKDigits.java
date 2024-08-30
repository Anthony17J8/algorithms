package org.example.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer
 * after removing k digits from num.
 * <p>
 * Input: num = "1432219", k = 3 Output: "1219" Explanation: Remove the three digits 4, 3, and 2 to form the new number
 * 1219 which is the smallest. Example 2:
 * <p>
 * Input: num = "10200", k = 1 Output: "200" Explanation: Remove the leading 1 and the number is 200. Note that the
 * output must not contain leading zeroes. Example 3:
 * <p>
 * Input: num = "10", k = 2 Output: "0" Explanation: Remove all the digits from the number and it is left with nothing
 * which is 0.
 */
public class RemoveKDigits {


    // too slow need refactor
    @Test
    void test() {
        assertEquals("1219", removeKdigits("1432219", 3));
        assertEquals("200", removeKdigits("10200", 1));
        assertEquals("0", removeKdigits("10", 2));
        assertEquals("0", removeKdigits("100000", 3));
        assertEquals("0", removeKdigits("990155", 6));
        assertEquals("0", removeKdigits("990155", 5));
        assertEquals("1", removeKdigits("990155", 4));
        assertEquals("34521", removeKdigits("384521", 1));
        assertEquals("321", removeKdigits("384521", 3));
        assertEquals("2521", removeKdigits("382521", 2));
        assertEquals("12", removeKdigits("12354", 3));
        assertEquals("1234", removeKdigits("123456786", 5));
    }

    public String removeKdigits(String num, int k) {
        int len = num.length();
        if (len == k) {
            return "0";
        }
        char[] chars = num.toCharArray();
        int idx = k;

        for (int i = 0; i < chars.length - 1 && idx > 0; i++) {
            if (toInt(chars[i]) > toInt(chars[i + 1])) {
                chars[i] = Character.MIN_VALUE;
                idx--;
                int inverseIdx = i - 1;
                while (idx > 0 && inverseIdx >= 0) {
                    if (chars[inverseIdx] != Character.MIN_VALUE) {
                        if (toInt(chars[inverseIdx]) > toInt(chars[i + 1])) {
                            chars[inverseIdx] = Character.MIN_VALUE;
                            idx--;
                        } else if (i + 1 == chars.length - 1) {
                            chars[i + 1] = Character.MIN_VALUE;
                            idx--;
                            while (idx > 0) {
                                chars[inverseIdx--] = Character.MIN_VALUE;
                                idx--;
                            }
                        }
                    }
                    inverseIdx--;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len - idx; i++) {
            if (chars[i] != Character.MIN_VALUE) {
                sb.append(chars[i]);
            }
        }
        String res = sb.toString();
        while (res.startsWith("0") && res.length() != 1) {
            res = res.substring(1);
        }
        return res;
    }

    private int toInt(Character s) {
        return s != null ? Integer.parseInt(String.valueOf(s)) : 0;
    }
}
