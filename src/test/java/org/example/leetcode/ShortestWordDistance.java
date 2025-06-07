package org.example.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShortestWordDistance {

    @ParameterizedTest
    @MethodSource("source")
    void test(String[] in, String word1, String word2, int out) {
        assertEquals(out, shortestDistance(in, word1, word2));
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(
                        new String[]{"practice", "makes", "perfect", "coding", "makes"},
                        "coding", "practice", 3),
                Arguments.of(
                        new String[]{"practice", "makes", "perfect", "coding", "makes"},
                        "makes", "coding", 1),
                Arguments.of(
                        new String[]{"a", "b", "c", "a", "d", "b"},
                        "c", "a", 1),
                Arguments.of(
                        new String[]{"w", "b", "c", "a", "d", "e"},
                        "e", "w", 5),
                Arguments.of(
                        new String[]{"a", "b", "c", "a", "d", "e"},
                        "a", "b", 1),
                Arguments.of(
                        new String[]{"a", "b", "c", "a", "d", "e"},
                        "d", "e", 1)
        );
    }

    public int shortestDistance(String[] words, String word1, String word2) {
        int idxW1 = -1;
        int idxW2 = -1;
        int cur = 0;
        int bestD = words.length;
        while (cur < words.length) {
            if (word1.equals(words[cur])) {
                idxW1 = cur;
            } else if (word2.equals(words[cur])) {
                idxW2 = cur;
            }
            if (idxW1 != -1 && idxW2 != -1 && Math.abs(idxW1 - idxW2) < bestD) {
                bestD = Math.abs(idxW1 - idxW2);
            }
            cur++;
        }
        return bestD;
    }
}