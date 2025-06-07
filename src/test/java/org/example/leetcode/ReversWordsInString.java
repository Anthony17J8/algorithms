package org.example.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/reverse-words-in-a-string/description/?envType=problem-list-v2&envId=two-pointers
 */
public class ReversWordsInString {

    @ParameterizedTest
    @MethodSource("source")
    void test(String in, String out) {
        assertEquals(out, reverseWords(in));
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("the sky is blue", "blue is sky the"),
                Arguments.of("  hello world  ", "world hello"),
                Arguments.of("a good   example", "example good a")
        );
    }

    public String reverseWords(String s) {
        String[] split = s.split(" +");
        return IntStream.range(0, split.length)
                        .mapToObj(idx -> split[split.length-idx-1])
                                .collect(Collectors.joining(" ")).trim();
    }

}
