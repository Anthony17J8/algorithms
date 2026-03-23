package org.example.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/string-compression/description/?envType=problem-list-v2&envId=two-pointers
 */
public class StringCompression {
    @ParameterizedTest
    @MethodSource("source")
    void test(char[] chars, int out) {
        assertEquals(out, compress(chars));
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new char[]{'a', 'a', 'b', 'b', 'c', 'c', 'c'}, 6),
                Arguments.of(new char[]{'a'}, 1),
                Arguments.of(new char[]{'a', 'b', 'b'}, 3),
                Arguments.of(new char[]{'b', 'a', 'b'}, 3),
                Arguments.of(new char[]{'b', 'a', 'a'}, 3),
                Arguments.of(new char[]{'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}, 4)
        );
    }

    public int compress(char[] chars) {
        int writeidx = 0;
        int start = 0;
        char target = chars[start];
        if (chars.length == 1) {
            return 1;
        }
        for (int i = 0; i < chars.length; i++) {
            if (target != chars[i]) {
                int length = i - start;
                writeidx = writeAns(chars, target, writeidx, length);
                target = chars[i];
                start = i;
            }
        }
        if (start <= chars.length - 1) {
            writeidx = writeAns(chars, target, writeidx, chars.length - start);
        }
        return writeidx;
    }

    public int writeAns(char[] chars, char ch, int start, int length) {
        chars[start] = ch;
        if (length > 1) {
            String s = String.valueOf(length);
            int idx = 0;
            while (idx < s.length()) {
                chars[start + 1 + idx] = s.charAt(idx);
                idx++;
            }
            return start + 1 + idx;
        }
        return start + 1;
    }
}
