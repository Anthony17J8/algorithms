package org.example;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * <p>
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * <p>
 * You can return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 * Example 2:
 * <p>
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 * Example 3:
 * <p>
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 */
public class TwoSum {

    @Test
    public void test() {
//        int[] nums = {2, 7, 11, 15};
//        int[] nums = {2, 6, 3, 11, 15};
//        int[] nums = {3, 2, 4};
        int[] nums = {-2, 11, 2};
//        int[] nums = {-2, 0, 9};
//        int[] nums = {-1, -2, -3, -4, -5};
//        int[] nums = {3, 2, 95, 4, -3};
//        int[] nums = {9185270,
//            -9977634, 5152130, -7569234, 2078434, 3082495, 6630551, -5476859, -3746865, -3892704, 1314811, 7619357, -9039179, -6152661, 6718089, -2160315, 7257890, 9998875, -8418017, 6772238, 1487058, -4208671, 7610442, -3644241, -4687679, -5264841, 2238501, 2263653, -2994599, -7558681, 8861551, -1567796, 443072, -7669938, -2559029, 3670014, -8390519, -523343, 3776620, -8505117, 6172189, -6364875, -1846766, 3822129, -3259582, 7545828, 837103, -6187186, 6997658, -1331194, -5064789, 1869867, -8844455, 3257616, -7393237, 3166721, -6228727, 4927408, 2617702, 4478877, -144697, 9045981, 8987755, -892588, -1442130, 9590416, -1799681, 8790644, 4362169, 8309913, 1596232, 3628118, -3804169, 1353228, 1842292, 9146961, 5746009, 6578231, -7447924, 1657044, -5198384, 2811812, -6711089, -1012526, -6987160, 131697, 2366105, 3881988, -8627952, -5160644, -8445966, 4336763, 6695562, 6741860, 5357542, -8124284, -2873154, -9957607, -8884846, -9303786, -8171897, -636998, -4973823, 1279033, 9723692, -6368166, -3637946, 7463847, -4645125, -698264, -8193632, 6135662, -4544220, -9591663, -600766, -577348, 7330344, -9714552};
//        int[] nums = {-18, 12, 3, 0};
        int target = 9;
        assertArrayEquals(new int[]{0, 1}, twoSum(nums, target));
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> pair = new HashMap<>();
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int value = target - nums[i];
            if (pair.containsKey(value)) {
                result[0] = pair.get(value);
                result[1] = i;
                break;
            }
            pair.put(nums[i], i);
        }
        return result;
    }
}
