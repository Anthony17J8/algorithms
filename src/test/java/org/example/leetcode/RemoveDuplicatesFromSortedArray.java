/**
 * https://leetcode.com/problems/merge-sorted-array/?envType=study-plan-v2&envId=top-interview-150
 */

package org.example.leetcode;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveDuplicatesFromSortedArray {

    @ParameterizedTest
    @Timeout(1)
    @MethodSource("source")
    void test(int[] nums, int out) {
        assertEquals(out, removeDuplicates(nums));
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(
                        new int[]{1, 1, 2}, 2
                ),
                Arguments.of(
                        new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}, 5
                )

        );
    }

    public int removeDuplicates(int[] nums) {
        int[] res = new int[nums.length];

        int left = 0;
        int right = left + 1;

        if (nums.length == 1) {
            return 1;
        }


        int idx = 0;
        res[idx++] = nums[left];
        while (left < right && right < nums.length) {
            if (nums[left] == nums[right]) {
                right++;
            } else {
                res[idx++] = nums[right];
                left = right;
                right++;
            }
        }

        System.arraycopy(res, 0, nums, 0, nums.length);
        return idx;
    }
}
