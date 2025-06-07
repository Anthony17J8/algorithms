package org.example.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://www.lintcode.com/problem/644/
 */
public class StrobogrammaticNumber {

    @ParameterizedTest
    @MethodSource("source")
    void test(String in, boolean out) {
        assertEquals(out, isStrobogrammatic(in));
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("69", true),
                Arguments.of("66199", true),
                Arguments.of("68", false),
                Arguments.of("818", true),
                Arguments.of("808", true),
                Arguments.of("8181", false),
                Arguments.of("88", true),
                Arguments.of("8", true),
                Arguments.of("7", false),
                Arguments.of("6", false),
                Arguments.of("9", false),
                Arguments.of("288", false),
                Arguments.of("96801866799810896", false)
        );
    }

    public boolean isStrobogrammatic(String num) {
        Map<Character, Character> map = Map.of(
                '6', '9',
                '9', '6',
                '0', '0',
                '8', '8',
                '1', '1'
        );
        if (num.length() == 1) {
            return num.charAt(0) != '6' && num.charAt(0) != '9' && map.containsKey(num.charAt(0));
        }
        int left = 0, right = num.length() - 1;
        boolean res = true;
        while (left <= right) {
            char c = num.charAt(left);
            if (map.get(c) == null || map.get(c) != num.charAt(right)) {
                res = false;
                break;
            }
            left++;
            right--;
        }
        return res;
    }
}
