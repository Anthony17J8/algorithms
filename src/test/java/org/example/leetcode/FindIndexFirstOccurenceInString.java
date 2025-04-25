//https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/?envType=problem-list-v2&envId=two-pointers

package org.example.leetcode;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindIndexFirstOccurenceInString {

    @ParameterizedTest
    @Timeout(1)
    @MethodSource("source")
    void test(String s, String sub, int out) {
        assertEquals(out, strStr(s, sub));
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("sadbutsad", "sad", 0),
                Arguments.of("leetcode", "leeto", -1),
                Arguments.of("a", "a", 0),
                Arguments.of("a", "b", -1),
                Arguments.of("mississippi", "issip", 4)
        );
    }

    public int strStr(String haystack, String needle) {
        int len = 0;
        boolean found = false;
        int expected = needle.length();
        int firstOcc = -1;
        int idx = 0;
        while (len < expected && idx < haystack.length()) {
            if (haystack.charAt(idx) == needle.charAt(len)) {
                if (len == 0) {
                    found = true;
                    firstOcc = idx;
                }
                len++;
                idx++;
            } else {
                len = 0;
                idx = found ? firstOcc + 1 : idx + 1;
                found = false;
            }
        }
        return len == expected ? firstOcc : -1;
    }
}