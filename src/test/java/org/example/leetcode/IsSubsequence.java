package org.example.leetcode;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/is-subsequence/?envType=problem-list-v2&envId=two-pointers
 */
public class IsSubsequence {

    @ParameterizedTest
    @Timeout(1)
    @MethodSource("source")
    void test(String s, String t, boolean expected) {
        assertEquals(expected, isSubsequence(s, t));
    }

    public boolean isSubsequence(String s, String t) {
        if (s.isEmpty()) {
            return true;
        }
        if (t.isEmpty()) {
            return false;
        }
        int sIdx = 0;
        int tIdx = 0;
        int counter = 0;
        boolean result = false;
        while (tIdx < t.length()) {
            if (s.charAt(sIdx) == t.charAt(tIdx)) {
                counter++;
                sIdx++;
                tIdx++;
            } else {
                tIdx++;
            }

            if (counter == s.length()) {
                result = true;
                break;
            }
        }

        return result;
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("abc", "ahbgdc", true),
                Arguments.of("axc", "ahbgdc", false),
                Arguments.of("", "", false)
        );
    }
}
