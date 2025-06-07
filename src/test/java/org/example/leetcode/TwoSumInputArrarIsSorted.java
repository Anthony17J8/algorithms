package org.example.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/?envType=problem-list-v2&envId=two-pointers
 */
public class TwoSumInputArrarIsSorted {

    @ParameterizedTest
    @MethodSource("source")
    void test(int[] nums, int target, int[] out) {
        assertArrayEquals(out, twoSum(nums, target));
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 5, 6, 100, 101}, 11, new int[]{4, 5}),
                Arguments.of(new int[]{2, 7, 11, 15}, 9, new int[]{1, 2}),
                Arguments.of(new int[]{2, 3, 4}, 6, new int[]{1, 3}),
                Arguments.of(new int[]{0, 0, 3, 4}, 0, new int[]{1, 2}),
                Arguments.of(new int[]{1, 3, 4, 4}, 8, new int[]{3, 4}),
                Arguments.of(new int[]{-1, 0}, -1, new int[]{1, 2}),
                Arguments.of(new int[]{1, 2, 3, 4, 5, 6, 7}, 13, new int[]{6, 7}),
                Arguments.of(new int[]{1, 2, 2, 3, 4, 5}, 4, new int[]{1, 4}),
                Arguments.of(new int[]{1, 2, 3, 4, 4, 9, 56, 90}, 8, new int[]{4, 5})
        );
    }

    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            if (numbers[left] + numbers[right] == target) {
                break;
            } else if (numbers[left] + numbers[right] < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{left + 1, right + 1};
    }


}
