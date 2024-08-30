package org.example.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 * <p>
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 * <p>
 * Return the maximum amount of water a container can store.
 * <p>
 * Notice that you may not slant the container.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
 * Example 2:
 * <p>
 * Input: height = [1,1]
 * Output: 1
 */
public class ContainerWithMostWater {

    @Test
    void test() {
        assertEquals(49, maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        assertEquals(49, maxAreaOptimize(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        assertEquals(8, maxAreaOptimize(new int[]{1, 5, 1, 1, 1, 1, 1, 1, 1}));
        assertEquals(1, maxArea(new int[]{1, 1}));
        assertEquals(1, maxAreaOptimize(new int[]{1, 1}));
    }

    // O(n2)
    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int pow = Math.min(height[i], height[j]) * (j - i);
                if (pow > max) {
                    max = pow;
                }
            }
        }
        return max;
    }

    public int maxAreaOptimize(int[] height) {
        int max = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            max = Math.max(Math.min(height[right], height[left]) * (right - left), max);
            if (height[right] > height[left]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
}
