/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/?envType=study-plan-v2&envId=top-interview-150
 */

package org.example.leetcode;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveDuplicatesFromSortedArray2 {

    @ParameterizedTest
    @Timeout(1)
    @MethodSource("source")
    void test(int[] nums, int out) {
        assertEquals(out, removeDuplicates(nums));
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(
                        new int[]{1, 1, 1, 2, 2, 3}, 5
                ),
                Arguments.of(
                        new int[]{1, 1, 1, 2, 2, 2, 3, 3}, 6
                ),
                Arguments.of(
                        new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3}, 7
                ),
                Arguments.of(
                        new int[]{0, 0, 0, 0, 0, 0, 0, 1}, 3
                ),
                Arguments.of(
                        new int[]{1, 2, 3, 4, 4, 4}, 5
                )


        );
    }

    public int removeDuplicates(int[] nums) {
        int left = 0;
        int right = 0;
        if (nums.length == 1) {
            return 1;
        }
        int res = 1;
        int cnt = 1;
        right++;
        int idx = right;
        while (left < right && right < nums.length) {
            if (nums[left] == nums[right]) {
                if (cnt == 2) {
                    cnt++;
                    right++;
                } else if (cnt < 2) {
                    if (idx != right) {
                        nums[idx] = nums[right];
                    }
                    idx++;
                    right++;
                    res++;
                    cnt++;
                } else {
                    right++;
                }
            } else {
                cnt = 1;
                left = right;
                if (idx != right) {
                    nums[idx] = nums[right];
                }
                idx++;
                res++;
                right++;
            }
        }
        return res;
    }
}
