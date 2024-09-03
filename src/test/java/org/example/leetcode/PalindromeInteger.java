package org.example.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Given an integer x, return true if x is a
 * palindrome
 * , and false otherwise.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: x = 121
 * Output: true
 * Explanation: 121 reads as 121 from left to right and from right to left.
 * Example 2:
 * <p>
 * Input: x = -121
 * Output: false
 * Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
 * Example 3:
 * <p>
 * Input: x = 10
 * Output: false
 * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 */
public class PalindromeInteger {

    @Test
    public void test() {
        assertTrue(isPalindrome(121));
        assertFalse(isPalindrome(-121));
        assertFalse(isPalindrome(10));
        assertTrue(isPalindrome(1221));
        assertTrue(isPalindrome(1));
    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        char[] c = String.valueOf(Math.abs(x)).toCharArray();
        int left = 0;
        int right = c.length - 1;
        while (left < right) {
            if (c[left] != c[right]) {
                return false;
            } else {
                left++;
                right--;
            }
        }
        return true;
    }
}
