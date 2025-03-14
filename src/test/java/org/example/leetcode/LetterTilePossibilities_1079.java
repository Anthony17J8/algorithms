/*
   Copyright 2020 Russian Post
   <p>
   This source code is Russian Post Confidential Proprietary.
   This software is protected by copyright. All rights and titles are reserved.
   You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
   Otherwise this violation would be treated by law and would be subject to legal prosecution.
   Legal use of the software provides receipt of a license from the right holder only.
 */

package org.example.leetcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LetterTilePossibilities_1079 {

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(String text, int out) throws Exception {
        assertEquals(out, getResult(text));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("AAB", 8),
                Arguments.of("AAABBC", 188),
                Arguments.of("V", 1)

        );
    }

    private static int getResult(String in) {
        int result = 0;
        Map<Character, Integer> dict = new HashMap<>();
        for (char c : in.toCharArray()) {
            if (dict.computeIfPresent(c, (k, v) -> v + 1) == null) {
                dict.put(c, 1);
            }
        }
        result += (int) dict.values().stream().collect(Collectors.summarizingInt(Integer::intValue)).getSum();

        return 0;
    }
}
