package org.example.leetcode;

import org.hamcrest.Matchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
 */
public class LetterCombinations {
    @ParameterizedTest
    @MethodSource("source")
    void test(String in, List<String> out) {
        assertThat(out, Matchers.is(letterCombinations(in)));
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("2", List.of("a", "b", "c")),
                Arguments.of("22", List.of("aa", "ab", "ac", "ba", "bb", "bc", "ca", "cb", "cc")),
                Arguments.of("3", List.of("d", "e", "f")),
                Arguments.of("23", List.of("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"))
        );
    }

    public List<String> letterCombinations(String digits) {
        String[] arr = digits.split("");
        List<String> result = new ArrayList<>();
        backTrack("", 0, arr, result);
        return result;
    }

    private static void backTrack(String cur, int curIdx, String[] digits, List<String> result) {
        if (curIdx == digits.length) {
            result.add(cur);
            return;
        }
        String digit = digits[curIdx];
        String[] letters = dict.get(digit).split("");
        int nextIdx = curIdx + 1;
        for (String letter : letters) {
            backTrack(cur + letter, nextIdx, digits, result);
        }
    }

    static Map<String, String> dict = Map.of(
            "2", "abc",
            "3", "def",
            "4", "ghi",
            "5", "jkl",
            "6", "mno",
            "7", "pqrs",
            "8", "tuv",
            "9", "wxyz"
    );
}
