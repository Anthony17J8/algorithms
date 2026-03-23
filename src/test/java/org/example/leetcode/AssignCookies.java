package org.example.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/assign-cookies/description/?envType=problem-list-v2&envId=two-pointers
 */
public class AssignCookies {
    @ParameterizedTest
    @MethodSource("source")
    void test(int[] g, int[] s, int out) {
        assertEquals(out, findContentChildren(g, s));
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3}, new int[]{1, 1}, 1),
                Arguments.of(new int[]{1, 2}, new int[]{1, 2, 3}, 2),
                Arguments.of(new int[]{1, 2, 1}, new int[]{1, 2, 3}, 3),
                Arguments.of(new int[]{1, 2, 2}, new int[]{1, 2, 3}, 3),
                Arguments.of(new int[]{3, 2, 2}, new int[]{1, 2, 3}, 2),
                Arguments.of(new int[]{3, 2, 2}, new int[]{1, 1, 1}, 0),
                Arguments.of(new int[]{3, 2, 2}, new int[]{1}, 0),
                Arguments.of(new int[]{3, 2, 2}, new int[]{2}, 1),
                Arguments.of(new int[]{2, 3}, new int[]{3, 1}, 1)
        );
    }

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int gidx = 0;
        int sidx = 0;
        int counter = 0;

        while (gidx < g.length && sidx < s.length) {
            if (g[gidx] <= s[sidx]) {
                counter++;
                gidx++;
                sidx++;
            } else
                sidx++;
        }
        return counter;
    }
}
