package org.example.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/compare-version-numbers/description/?envType=problem-list-v2&envId=two-pointers
 */
public class CompareVersionNumbers {

    @ParameterizedTest
    @MethodSource("source")
    void test(String s1, String s2, int out) {
        assertEquals(out, compareVersion(s1, s2));
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("1.2", "1.10", -1),
                Arguments.of("2.2", "1.10", 1),
                Arguments.of("12312312.2", "1.999910", 1),
                Arguments.of("1.01", "1.0001", 0),
                Arguments.of("1.0", "1.0.0.0", 0),
                Arguments.of("1.0", "1", 0),
                Arguments.of("7.5.2.4", "7.5.3", -1)
        );
    }

    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int len = Math.min(v1.length, v2.length);
        int res = 0;
        for (int i = 0; i < len; i++) {
            int i1 = Integer.parseInt(v1[i]);
            int i2 = Integer.parseInt(v2[i]);
            if (i1 > i2) {
                res = 1;
                break;
            } else if (i1 < i2) {
                res = -1;
                break;
            }
        }
        if (res == 0) {
            if (len < v1.length) {
                for (int i = len; i < v1.length; i++) {
                    if (Integer.parseInt(v1[i]) != 0) {
                        res = 1;
                        break;
                    }
                }
            }
            if (len < v2.length) {
                for (int i = len; i < v2.length; i++) {
                    if (Integer.parseInt(v2[i]) != 0) {
                        res = -1;
                        break;
                    }
                }
            }
        }
        return res;
    }
}
