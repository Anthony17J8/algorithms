package org.example.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * <p>
 * Write the code that will take a string and make this conversion given a number of rows:
 * <p>
 * string convert(string s, int numRows);
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 * Example 2:
 * <p>
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * Example 3:
 * <p>
 * Input: s = "A", numRows = 1
 * Output: "A"
 */
public class ZigZagConversion {

    @Test
    void test() {
        assertEquals("PAHNAPLSIIGYIR", convert("PAYPALISHIRING", 3));
        assertEquals("A", convert("A", 1));
        assertEquals("PINALSIGYAHRPI", convert("PAYPALISHIRING", 4));
        assertEquals("AB", convert("AB", 2));
        assertEquals("ABG", convert("AGB", 2));
    }

    String convert(String s, int num) {
        if (num == 1 || num == 0) {
            return s;
        }
        int cursor = 0;
        char[][] c = new char[num][getNumCol(s.length(), num)];
        int row = 0;
        int col = 0;
        char[] ch = s.toCharArray();
        for (char value : ch) {
            if (cursor > 0) {
                cursor--;
                c[row][col] = value;
                row--;
                col++;
            } else {
                if (row < num - 1) {
                    c[row][col] = value;
                    row++;
                } else {
                    c[row][col] = value;
                    cursor = num - 2;
                    row--;
                    col++;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char[] rowCh : c) {
            for (char colCh : rowCh) {
                if (colCh != '\u0000') {
                    sb.append(colCh);
                }
            }
        }
        return sb.toString();
    }

    private int getNumCol(int length, int num) {
        int i = length;
        int count = 0;
        boolean isStep1 = true;
        while (i >= 0) {
            if (isStep1) {
                i = i - num;
                isStep1 = false;
                count++;
            } else {
                i = i - (num - 2);
                count = count + num - 2;
                isStep1 = true;
            }
        }
        return count + (isStep1 ? i : 0);
    }
}
