package org.example.leetcode;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * https://leetcode.com/problems/move-zeroes/description/?envType=problem-list-v2&envId=two-pointers
 */
public class MoveZeroes {

    @ParameterizedTest
    @Timeout(1)
    @MethodSource("source")
    void test(int[] nums, int[] out) {
        moveZeroes(nums);
        assertArrayEquals(out, nums);
    }

    public void moveZeroes(int[] nums) {
        int left = 0;
        int notzero = 0;
        int countzero = 0;
        while (left < nums.length) {
            if (nums[left] == 0) {
                countzero++;
            } else {
                nums[notzero++] = nums[left];
            }
            left++;
        }
        int idx = 1;
        while (countzero > 0) {
            nums[nums.length - idx++] = 0;
            countzero--;
        }
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(
                        new int[]{0, 1, 0, 3, 12}, new int[]{1, 3, 12, 0, 0}
                ),
                Arguments.of(
                        new int[]{0}, new int[]{0}
                ),
                Arguments.of(
                        new int[]{1, 2}, new int[]{1, 2}
                )
        );
    }
}
