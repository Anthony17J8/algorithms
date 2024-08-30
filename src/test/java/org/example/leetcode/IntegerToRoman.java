package org.example.leetcode;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Seven different symbols represent Roman numerals with the following values:
 * <p>
 * Symbol	Value I	1 V	5 X	10 L	50 C	100 D	500 M	1000 Roman numerals are formed by appending the conversions of decimal
 * place values from highest to lowest. Converting a decimal place value into a Roman numeral has the following rules:
 * <p>
 * If the value does not start with 4 or 9, select the symbol of the maximal value that can be subtracted from the
 * input, append that symbol to the result, subtract its value, and convert the remainder to a Roman numeral. If the
 * value starts with 4 or 9 use the subtractive form representing one symbol subtracted from the following symbol, for
 * example, 4 is 1 (I) less than 5 (V): IV and 9 is 1 (I) less than 10 (X): IX. Only the following subtractive forms are
 * used: 4 (IV), 9 (IX), 40 (XL), 90 (XC), 400 (CD) and 900 (CM). Only powers of 10 (I, X, C, M) can be appended
 * consecutively at most 3 times to represent multiples of 10. You cannot append 5 (V), 50 (L), or 500 (D) multiple
 * times. If you need to append a symbol 4 times use the subtractive form. Given an integer, convert it to a Roman
 * numeral.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: num = 3749
 * <p>
 * Output: "MMMDCCXLIX"
 * <p>
 * Explanation:
 * <p>
 * 3000 = MMM as 1000 (M) + 1000 (M) + 1000 (M) 700 = DCC as 500 (D) + 100 (C) + 100 (C) 40 = XL as 10 (X) less of 50
 * (L) 9 = IX as 1 (I) less of 10 (X) Note: 49 is not 1 (I) less of 50 (L) because the conversion is based on decimal
 * places Example 2:
 * <p>
 * Input: num = 58
 * <p>
 * Output: "LVIII"
 * <p>
 * Explanation:
 * <p>
 * 50 = L 8 = VIII Example 3:
 * <p>
 * Input: num = 1994
 * <p>
 * Output: "MCMXCIV"
 * <p>
 * Explanation:
 * <p>
 * 1000 = M 900 = CM 90 = XC 4 = IV
 */
public class IntegerToRoman {
    private static final Map<Integer, String> DICT = Map.of(
            1000, "M",
            500, "D",
            100, "C",
            50, "L",
            10, "X",
            5, "V",
            1, "I"
    );

    @Test
    void test() throws InterruptedException {
        assertEquals("MMM", intToRoman(3000));
        assertEquals("MMMDCCXLIX", intToRoman(3749));
        assertEquals("LVIII", intToRoman(58));
        assertEquals("MCMXCIV", intToRoman(1994));
        assertEquals("IX", intToRoman(9));
        assertEquals("MDCCCXXXIII", intToRoman(1833));
    }

    public String intToRoman(int num) {
        StringBuilder result = new StringBuilder();
        int len = getLength(num);
        if (len == 4) {
            num = check4thNum(result, num);
            len = getLength(num);
        }
        if (len == 3) {
            num = checkAny(result, num, 100, 500, 1000);
            len = getLength(num);
        }
        if (len == 2) {
            num = checkAny(result, num, 10, 50, 100);
            len = getLength(num);
        }
        if (len == 1) {
            checkAny(result, num, 1, 5, 10);
        }
        return result.toString();
    }

    private int checkAny(StringBuilder sb, int num, int min, int mid, int max) {
        String sNum = String.valueOf(num);
        if (sNum.startsWith("4")) {
            sb.append(DICT.get(min));
            sb.append(DICT.get(mid));
        } else if (sNum.startsWith("9")) {
            sb.append(DICT.get(min));
            sb.append(DICT.get(max));
        } else if (num >= mid) {
            sb.append(DICT.get(mid));
            int idx = mid;
            while (num - idx >= min) {
                sb.append(DICT.get(min));
                idx += min;
            }
        } else {
            int idx = min;
            while (idx <= num) {
                sb.append(DICT.get(min));
                idx += min;
            }
        }
        return num % (int) Math.pow(10, sNum.length() - 1);
    }

    private int check4thNum(StringBuilder sb, int num) {
        int pow = (int) Math.pow(10, 3);
        int idx = num / pow;
        while (idx > 0) {
            sb.append(DICT.get(pow));
            idx--;
        }
        return num % pow;
    }

    private int getLength(int num) {
        return String.valueOf(num).length();
    }
}