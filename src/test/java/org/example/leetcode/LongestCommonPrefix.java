package org.example.leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 * <p>
 * If there is no common prefix, return an empty string "".
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: strs = ["flower","flow","flight"] Output: "fl" Example 2:
 * <p>
 * Input: strs = ["dog","racecar","car"] Output: "" Explanation: There is no common prefix among the input strings.
 */
public class LongestCommonPrefix {

    @Test
    void test() {
        assertEquals("fl", longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        assertEquals("", longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
        assertEquals("racecar", longestCommonPrefix(new String[]{"racecar", "racecar", "racecar"}));

        assertEquals("fl", longestCommonPrefixSorted(new String[]{"flower", "flow", "flight"}));
        assertEquals("", longestCommonPrefixSorted(new String[]{"dog", "racecar", "car"}));
        assertEquals("racecar", longestCommonPrefixSorted(new String[]{"racecar", "racecar", "racecar"}));
    }

    public String longestCommonPrefix(String[] strs) {
        char[] pivot = strs[0].toCharArray();
        int len = pivot.length;
        for (int j = 1; j < strs.length; j++) {
            char[] chars = strs[j].toCharArray();
            len = Math.min(chars.length, len);
            for (int i = 0; i < len; i++) {
                if (pivot[i] != chars[i]) {
                    len = i;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(pivot[i]);
        }
        return sb.toString();
    }

    public String longestCommonPrefixSorted(String[] strs) {
        Arrays.sort(strs);
        String f = strs[0];
        String l = strs[strs.length-1];
        int idx = 0;
        while (idx < f.length() && idx < l.length()) {
            if (f.toCharArray()[idx] != l.toCharArray()[idx]) {
                break;
            } else {
                idx++;
            }
        }
        return f.substring(0, idx);
    }
}
