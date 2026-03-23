
package org.example.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/longest-uncommon-subsequence-ii/description/?envType=problem-list-v2&envId=two-pointers
 */
public class LongestUncommonSubSeq2 {
    @ParameterizedTest
    @MethodSource("source")
    void test(String[] in, int out) {
        assertEquals(out, findLUSlength(in));
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new String[]{"abcd", "bcdef", "bcdef"}, 4),
                Arguments.of(new String[]{"ab", "acdf", "afs", "azb"}, 4),
                Arguments.of(new String[]{"aabbcc", "aabbcc", "b", "bc"}, -1),
                Arguments.of(new String[]{"abcd", "bcde", "de", "mnop"}, 4),
                Arguments.of(new String[]{"abcd", "abcd", "bcd", "cd"}, -1),
                Arguments.of(new String[]{"abcd", "bcd", "cd"}, 4),
                Arguments.of(new String[]{"af", "abcd", "ac", "abcd", "ac"}, 2),
                Arguments.of(new String[]{"aba", "cdc", "aea"}, 3),
                Arguments.of(new String[]{"aaaa", "aaa", "aa"}, 4),
                Arguments.of(new String[]{"aaa", "aaa", "aa"}, -1),
                Arguments.of(new String[]{"abcd", "ab", "cd"}, 4),
                Arguments.of(new String[]{"abcd", "ab", "abc"}, 4)
        );
    }

    public int findLUSlength(String[] strs) {
        Arrays.sort(strs, (s1, s2) -> s2.length() - s1.length());
        int result = -1;
        for (int i = 0; i < strs.length; i++) {
            String s1 = strs[i];
            boolean found = false;
            for (int j = 0; j < strs.length; j++) {
                if (i == j) {
                    continue;
                }
                String s2 = strs[j];
                if (isSub(s1, s2)) {
                    found = true;
                    break;
                }

            }
            if (!found) {
                result = s1.length();
                break;
            }
        }
        return result;
    }

    public static boolean isSub(String s1, String s2) {
        int idx1 = 0;
        int idx2 = 0;
        while (idx1 < s1.length() && idx2 < s2.length()) {
            if (s1.charAt(idx1) == s2.charAt(idx2)) {
                idx1++;
                idx2++;
            } else {
                idx2++;
            }
        }
        return idx1 == s1.length();
    }

}
