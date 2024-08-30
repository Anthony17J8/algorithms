package org.example.leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j !=
 * k, and nums[i] + nums[j] + nums[k] == 0.
 * <p>
 * Notice that the solution set must not contain duplicate triplets.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-1,0,1,2,-1,-4] Output: [[-1,-1,2],[-1,0,1]] Explanation: nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 =
 * 0. nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0. nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0. The distinct
 * triplets are [-1,0,1] and [-1,-1,2]. Notice that the order of the output and the order of the triplets does not
 * matter. Example 2:
 * <p>
 * Input: nums = [0,1,1] Output: [] Explanation: The only possible triplet does not sum up to 0. Example 3:
 * <p>
 * Input: nums = [0,0,0] Output: [[0,0,0]] Explanation: The only possible triplet sums up to 0.
 */
public class ThreeSum {

    @Test
    void name() {
        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        System.out.println(threeSum(new int[]{0, 1, 1}));
        System.out.println(threeSum(new int[]{0, 0, 0}));
        System.out.println(threeSum(new int[]{-7, -7, -2, 9, 1, -6, -3, -10,1}));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        return List.of();
    }
}
