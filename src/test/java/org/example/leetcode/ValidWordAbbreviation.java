package org.example.leetcode;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://www.lintcode.com/problem/637/
 */
public class ValidWordAbbreviation {

    @ParameterizedTest
    @Timeout(1)
    @MethodSource("source")
    void test(String word, String abbr, boolean expected) {
        assertEquals(expected, validWordAbbreviation(word, abbr));
    }

    public boolean validWordAbbreviation(String word, String abbr) {
        int wdx = 0;
        int abbidx = 0;
        String buffer = "";
        boolean result = true;
        while (abbidx < abbr.length()) {
            char c = abbr.charAt(abbidx);
            if (Character.isDigit(c)) {
                if (buffer.isEmpty() && (Integer.parseInt(String.valueOf(c)) == 0)) {
                    result = false;
                    break;
                } else {
                    buffer = buffer + c;
                }
            } else {
                if (!buffer.isEmpty()) {
                    wdx += Integer.parseInt(buffer);
                    buffer = "";
                }
                if (word.charAt(wdx) != c) {
                    result = false;
                    break;
                } else {
                    wdx++;
                }
            }
            abbidx++;
        }
        if (!buffer.isEmpty()) {
            wdx += Integer.parseInt(buffer);
        }
        if (wdx != word.length()) {
            result = false;
        }
        return result;
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("internationalization", "i12iz4n", true),
                Arguments.of("apple", "a2e", false),
                Arguments.of("aaaaa", "5", true),
                Arguments.of("aaaaa", "a2", false),
                Arguments.of("aaaaa", "a2a", false),
                Arguments.of("aaaaa", "aaa2", true),
                Arguments.of("aa", "a2", false),
                Arguments.of("a", "01", false),
                Arguments.of("", "0", true)
        );
    }
}
