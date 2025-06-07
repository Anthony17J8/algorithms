package org.example.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://www.lintcode.com/problem/640/
 */
public class OneEditDistance {

    @ParameterizedTest
    @MethodSource("source")
    void test(String s, String t, boolean expected) {
        assertEquals(expected, isOneEditDistance(s, t));
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("aDb", "adb", true),
                Arguments.of("ab", "ab", false),
                Arguments.of("abc", "ab", true),
                Arguments.of("ab", "abc", true),
                Arguments.of("bcde", "abcde", true),
                Arguments.of("", "", false),
                Arguments.of("aaaa", "a", false)
        );
    }

    static int left = -1;
    static int right = 1;
    static int both = 0;

    public boolean isOneEditDistance(String s, String t) {
        int mode = both;
        if (s.length() > t.length()) mode = left;
        if (s.length() < t.length()) mode = right;
        if (Math.abs(s.length() - t.length()) > 1) {
            return false;
        }
        return check(s, t, mode);
    }

    public boolean check(String s, String t, int mode) {
        int leftIdx = 0;
        int rightIdx = 0;
        int ops = 0;
        while (leftIdx < s.length() && rightIdx < t.length()) {
            if (s.charAt(leftIdx) == t.charAt(rightIdx)) {
                leftIdx++;
                rightIdx++;
            } else {
                if (mode == left) {
                    leftIdx++;
                } else if (mode == right) {
                    rightIdx++;
                } else {
                    leftIdx++;
                    rightIdx++;
                }
                ops++;
            }
        }

         if (leftIdx < s.length()) ops++;
         if (rightIdx < t.length()) ops++;
        return ops == 1;
    }

}
