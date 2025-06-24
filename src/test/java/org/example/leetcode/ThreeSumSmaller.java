package org.example.leetcode;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://www.lintcode.com/problem/918/
 */
public class ThreeSumSmaller {

    @ParameterizedTest
    @Timeout(1)
    @MethodSource("source")
    void test(int[] nums, int target, int out) {
        assertEquals(out, threeSumSmaller(nums, target));
    }

    public int threeSumSmaller(int[] nums, int target) {
        int ax = 0;
        int cnt = 0;
        Arrays.sort(nums);
        while (ax < nums.length - 2) {
            int s = target - nums[ax];
            int bx = ax + 1;
            int cx = nums.length - 1;
            while (bx < cx) {
                if (nums[bx] + nums[cx] < s) {
                    cnt += cx - bx;
                    bx++;
                } else {
                    cx--;
                }
            }
            ax++;
        }
        return cnt;
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(
                        new int[]{-2, 0, 1, 3}, 2, 2
                ),
                Arguments.of(
                        new int[]{-2, 0, -1, 3}, 2, 3
                )
        );
    }
}
