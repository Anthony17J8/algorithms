package org.example.leetcode;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * https://leetcode.com/problems/reverse-string/?envType=problem-list-v2&envId=two-pointers
 */
public class ReverseString {

    @ParameterizedTest
    @Timeout(1)
    @MethodSource("source")
    void test(char[] s, char[] out) {
        reverseString(s);
        assertArrayEquals(out, s);
    }

    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(
                        new char[]{'h', 'e', 'l', 'l', 'o'}, new char[]{'o', 'l', 'l', 'e', 'h'}
                ),
                Arguments.of(
                        new char[]{'H', 'a', 'n', 'n', 'a', 'h'}, new char[]{'h', 'a', 'n', 'n', 'a', 'H'}
                )
        );
    }
}
