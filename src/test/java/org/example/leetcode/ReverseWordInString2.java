package org.example.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReverseWordInString2 {

    @ParameterizedTest
    @MethodSource("source")
    void test(String in, String out) {
        assertEquals(out, reverseWords(in));
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("the sky is blue", "blue is sky the"),
                Arguments.of("hello world", "world hello"),
                Arguments.of("a good example", "example good a")
        );
    }

    public String reverseWords(String str) {
        int left = 0;
        int right = str.length() - 1;
        char[] arr = str.toCharArray();
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }

        int from = 0;
        int to = from;
        while (to < arr.length) {
            if (arr[to] == ' ') {
                reverse(arr, from, to - 1);
                from = to + 1;
            }
            to++;
        }
        reverse(arr, from, to - 1);
        return new String(arr);
    }

    public void reverse(char[] c, int from, int to) {
        while (from < to) {
            char temp = c[from];
            c[from] = c[to];
            c[to] = temp;
            from++;
            to--;
        }
    }
}
