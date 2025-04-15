/**
 * https://leetcode.com/problems/rotate-array/?envType=study-plan-v2&envId=top-interview-150
 */

package org.example.leetcode;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class RotateArray {

    @ParameterizedTest
    @Timeout(1)
    @MethodSource("source")
    void test(int[] nums, int k, int[] out) {
//        rotate(nums, k);
        rotate2(nums, k);
        assertArrayEquals(out, nums);
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(
                        new int[]{1}, 1, new int[]{1}
                ),
                Arguments.of(
                        new int[]{1}, 0, new int[]{1}
                ),
                Arguments.of(
                        new int[]{1, 2}, 2, new int[]{1, 2}
                ),
                Arguments.of(
                        new int[]{1, 2, 3, 4, 5, 6, 7}, 3, new int[]{5, 6, 7, 1, 2, 3, 4}
                ),
                Arguments.of(
                        new int[]{1, 2, 3, 4, 5, 6}, 3, new int[]{4, 5, 6, 1, 2, 3}
                ),
                Arguments.of(
                        new int[]{1, 2, 3, 4, 5, 6}, 4, new int[]{3, 4, 5, 6, 1, 2}
                ),
                Arguments.of(
                        new int[]{-1, -100, 3, 99}, 2, new int[]{3, 99, -1, -100}
                ),
                Arguments.of(
                        new int[]{-1, -100, 3, 99}, 3, new int[]{-100, 3, 99, -1}
                ),
                Arguments.of(
                        new int[]{1, 1, 1, 2, 2}, 6, new int[]{2, 1, 1, 1, 2}
                )
        );
    }

    public void rotate(int[] nums, int k) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[(i + k) % nums.length] = nums[i];
        }
        System.arraycopy(res, 0, nums, 0, res.length);
    }

    public void rotate2(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] num, int from, int to) {
        int left = from;
        int right = to;

        while (left < right) {
            int tmp = num[left];
            num[left] = num[right];
            num[right] = tmp;
            left++;
            right--;
        }
    }
}
