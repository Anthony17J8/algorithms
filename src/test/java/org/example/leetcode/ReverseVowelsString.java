package org.example.leetcode;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/reverse-vowels-of-a-string/?envType=problem-list-v2&envId=two-pointers
 */
public class ReverseVowelsString {

    @ParameterizedTest
    @Timeout(1)
    @MethodSource("source")
    void test(String s, String out) {
        assertEquals(out, reverseVowels(s));
    }

    public String reverseVowels(String s) {
        Set<Character> cache = new HashSet<>();
        cache.add('a');
        cache.add('e');
        cache.add('i');
        cache.add('o');
        cache.add('u');
        cache.add('A');
        cache.add('E');
        cache.add('I');
        cache.add('O');
        cache.add('U');
        int left = 0;
        int right = s.length() - 1;
        char[] chars = s.toCharArray();
        while (true) {
            while (left < right && !cache.contains(chars[left])) {
                left++;
            }
            if (left >= right) {
                break;
            }
            while (left < right && !cache.contains(chars[right])) {
                right--;
            }
            if (left >= right) {
                break;
            }
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        return new String(chars);
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("IceCreAm", "AceCreIm"),
                Arguments.of("leetcode", "leotcede"),
                Arguments.of("mnaqnbcvz", "mnaqnbcvz"),
                Arguments.of("ai", "ia")
        );
    }
}
