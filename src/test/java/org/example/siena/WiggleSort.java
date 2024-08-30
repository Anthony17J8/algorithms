package org.example.siena;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Given an integer array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 * <p>
 * You may assume the input array always has a valid answer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,5,1,1,6,4]
 * Output: [1,6,1,5,1,4]
 * Explanation: [1,4,1,5,1,6] is also accepted.
 * Example 2:
 * <p>
 * Input: nums = [1,3,2,2,3,1]
 * Output: [2,3,1,3,1,2]
 */
public class WiggleSort {

    @Test
    void test() {
        assertArrayEquals(new int[]{1, 6, 1, 5, 1, 4}, wiggleSort(new int[]{1, 5, 1, 1, 6, 4}));
        assertArrayEquals(new int[]{2, 3, 1, 3, 1, 2}, wiggleSort(new int[]{1, 3, 2, 2, 3, 1}));
        assertArrayEquals(new int[]{4, 20, 3, 5, 2, 5, 1}, wiggleSort(new int[]{1, 2, 3, 4, 5, 5, 20}));
        assertArrayEquals(new int[]{5, 6, 4, 5}, wiggleSort(new int[]{4, 5, 5, 6}));
    }

    public int[] wiggleSort(int[] nums) {
        List<Integer> sorted = Arrays.stream(nums)
            .boxed()
            .sorted()
            .toList();
        List<Integer> partOne = new ArrayList<>();
        List<Integer> partTwo = new ArrayList<>();

        int middle = sorted.size() % 2 == 0 ? sorted.size() / 2 : sorted.size() / 2 + 1;
        for (int i = 0; i < sorted.size(); i++) {
            if (i < middle) {
                partOne.add(sorted.get(i));
            } else {
                partTwo.add(sorted.get(i));
            }
        }
        List<Integer> reversedOne = partOne.stream().sorted(Comparator.reverseOrder()).toList();
        List<Integer> reversedTwo = partTwo.stream().sorted(Comparator.reverseOrder()).toList();

        int index = 0;

        for (int i = 0, j = 0; i < reversedOne.size() && j < reversedTwo.size(); i++, j++) {
            nums[index++] = reversedOne.get(i);
            nums[index++] = reversedTwo.get(j);
        }
        if (index < reversedOne.size() + reversedTwo.size()) {
            nums[index] = reversedOne.get(reversedOne.size() - 1);
        }
        return nums;
    }
}
