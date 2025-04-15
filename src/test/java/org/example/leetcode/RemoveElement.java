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

public class RemoveElement {

    @ParameterizedTest
    @Timeout(1)
    @MethodSource("source")
    void test(int[] nums, int val, int k) {
        assertEquals(k, removeElement1(nums, val));
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(
                        new int[]{3, 2, 2, 3}, 3, 2
                ),
                Arguments.of(
                        new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2, 5
                )

        );
    }

    public int removeElement(int[] nums, int val) {
        int cnt = 0;
        int[] res = new int[nums.length];
        int idx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                cnt++;
                res[idx++] = nums[i];
            }
        }
        System.arraycopy(res, 0, nums, 0, nums.length);
        return cnt;
    }

    public int removeElement1(int[] nums, int val) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {

            if (nums[start] == val && nums[end] != val) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                end--;
                start++;
            }
            if (nums[start] != val) {
                start++;
            }
            if (nums[end] == val) {
                end--;
            }
        }

        return start;
    }
}
