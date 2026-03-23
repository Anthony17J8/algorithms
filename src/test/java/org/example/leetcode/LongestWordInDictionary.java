
package org.example.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/longest-uncommon-subsequence-ii/description/?envType=problem-list-v2&envId=two-pointers
 */
public class LongestWordInDictionary {
    @ParameterizedTest
    @MethodSource("source")
    void test(String s, List<String> dict, String out) {
        assertEquals(out, findLongestWord(s, new ArrayList<>(dict)));
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("abpcplea", List.of("ale", "apple", "monkey", "plea"), "apple"),
                Arguments.of("abpcplea", List.of("a", "b", "c"), "a"),
                Arguments.of("abce", List.of("abe", "abc"), "abc")
        );
    }

    public String findLongestWord(String s, List<String> dictionary) {
        dictionary.sort((s1, s2) -> s2.length() - s1.length());
        String result = "";
        for (String word : dictionary) {
            if (s.length() >= word.length()) {
                if (isSub(word, s)) {
                    if (result.length() == word.length()) {
                        result = result.compareTo(word) < 0 ? result : word;
                    } else if (result.length() < word.length()) {
                        result = word;
                    }
                }
            }

        }
        return result;
    }

    public static boolean isSub(String s1, String s2) {
        int idx1 = 0;
        int idx2 = 0;
        while (idx1 < s1.length() && idx2 < s2.length()) {
            char c1 = s1.charAt(idx1);
            char c2 = s2.charAt(idx2);

            if (c1 == c2) {
                idx1++;
                idx2++;
            } else {
                idx2++;
            }
        }
        return idx1 == s1.length();
    }
}
