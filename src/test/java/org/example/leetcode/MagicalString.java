package org.example.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/magical-string/description/?envType=problem-list-v2&envId=two-pointers
 */
public class MagicalString {
    @ParameterizedTest
    @MethodSource("source")
    void test(int in, int out) {
        assertEquals(out, magicalString(in));
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(6, 3),
                Arguments.of(1, 1),
                Arguments.of(2, 1),
                Arguments.of(18, 9),
                Arguments.of(9758, 4874)
        );
    }

    public int magicalString(int n) {
        List<String> l = new ArrayList<>();
        l.add("1");
        l.add("2");
        l.add("2");
        int idx = l.size() - 1;
        int result = 1;
        if (n <= 3) {
            return result;
        }
        while (l.size() < n) {
            if (l.get(idx).equals("2")) {
                if (l.get(l.size() - 1).equals("1")) {
                    l.add("2");
                    l.add("2");
                } else {
                    l.add("1");
                    result++;
                    if (l.size() == n) {
                        break;
                    }
                    l.add("1");
                    result++;
                }
            } else {
                if (l.get(l.size() - 1).equals("1")) {
                    l.add("2");
                } else {
                    l.add("1");
                    result++;
                }
            }
            idx++;
        }
        return result;
    }
}
