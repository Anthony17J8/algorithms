//https://leetcode.com/problems/partition-list/description/?envType=problem-list-v2&envId=two-pointers

package org.example.leetcode;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidPalindrome {

    @ParameterizedTest
    @Timeout(1)
    @MethodSource("source")
    void test(String in, boolean out) {
        assertEquals(out, isPalindrome(in));
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("A man, a plan, a canal: Panama", true),
                Arguments.of("race a car", false),
                Arguments.of(" ", true),
                Arguments.of("maam", true),
                Arguments.of("ab_a", true),
                Arguments.of("mam", true)
        );
    }

    public boolean isPalindrome(String s) {
        if (s.length() == 1) {
            return true;
        }
        s = s.toLowerCase().replaceAll("[\\W|_]", "");
        int left = 0;
        int right = s.length() - 1;

        boolean result = true;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                result = false;
                break;
            }
            left++;
            right--;
        }
        return result;
    }
}