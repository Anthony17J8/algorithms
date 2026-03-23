package org.example.leetcode;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/valid-parentheses/
 */
public class ValidParentheses {

    @ParameterizedTest
    @Timeout(1)
    @MethodSource("source")
    void test(String input, boolean out) {
        assertEquals(out, isValid(input));
    }

    static Map<Character, Character> close_to_open = Map.of(
            ']', '[', ')', '(', '}', '{'
    );

    public boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        boolean isValid = true;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    isValid = false;
                    break;
                }
                Character peek = stack.peek();
                if (peek == close_to_open.get(c)) {
                    stack.pop();
                } else {
                    isValid = false;
                    break;
                }
            }
        }
        return isValid && stack.isEmpty();

    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("()", true),
                Arguments.of("(", false),
                Arguments.of("()[]{}", true),
                Arguments.of("(]", false),
                Arguments.of("([])", true),
                Arguments.of("([)]", false)
        );
    }
}
