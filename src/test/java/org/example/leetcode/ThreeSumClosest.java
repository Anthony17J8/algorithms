//https://leetcode.com/problems/3sum-closest/description/?envType=problem-list-v2&envId=two-pointers

package org.example.leetcode;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThreeSumClosest {

    @ParameterizedTest
    @Timeout(1)
    @MethodSource("source")
    void test(int[] nums, int target, int out) {
        assertEquals(out, threeSumClosest(nums, target));
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new int[]{-1, 2, 1, -4}, 1, 2),
                Arguments.of(new int[]{-1, 2, 1, -4, 3}, 2, 2),
                Arguments.of(new int[]{0, 0, 0}, 1, 0)
        );
    }

    public int threeSumClosest(int[] nums, int target) {
        int idx1 = 0;
        int idx2 = 1;
        int idx3 = 2;
        Arrays.sort(nums);
        int res = nums[idx1] + nums[idx2] + nums[idx3];
        while (idx1 != nums.length - 2) {
            int sum = nums[idx1] + nums[idx2] + nums[idx3];
            res = Math.abs(target - sum) > Math.abs(target - res) ? res : sum;
            if (sum != target) {
                idx3++;
                if (idx3 == nums.length) {
                    idx2++;
                    if (idx2 == nums.length - 1) {
                        idx1++;
                        idx2 = idx1 + 1;
                    }
                    idx3 = idx2 + 1;
                }
            } else {
                res = sum;
                break;
            }
        }
        return res;
    }
}