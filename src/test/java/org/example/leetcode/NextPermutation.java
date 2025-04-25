//https://leetcode.com/problems/next-permutation/description/?envType=problem-list-v2&envId=two-pointers

package org.example.leetcode;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class NextPermutation {

    @ParameterizedTest
    @Timeout(1)
    @MethodSource("source")
    void test(int[] in, int[] out) {
        nextPermutation(in);
        assertArrayEquals(out, in);
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3}, new int[]{1, 3, 2}),
                Arguments.of(new int[]{1, 3, 2}, new int[]{2, 1, 3}),
                Arguments.of(new int[]{3, 2, 1}, new int[]{1, 2, 3}),
                Arguments.of(new int[]{1, 1, 5}, new int[]{1, 5, 1}),
                Arguments.of(new int[]{1, 2, 4, 3}, new int[]{1, 3, 2, 4}),
                Arguments.of(new int[]{1, 2}, new int[]{2, 1}),
                Arguments.of(new int[]{2, 1}, new int[]{1, 2}),
                Arguments.of(new int[]{1}, new int[]{1}),
                Arguments.of(new int[]{1, 1, 1, 2}, new int[]{1, 1, 2, 1}),
                Arguments.of(new int[]{1, 3, 2, 4}, new int[]{1, 3, 4, 2}),
                Arguments.of(new int[]{1, 2, 1, 2}, new int[]{1, 2, 2, 1}),
                Arguments.of(new int[]{2, 2, 2, 2}, new int[]{2, 2, 2, 2}),
                Arguments.of(new int[]{1, 7, 8, 4}, new int[]{1, 8, 4, 7})
        );
    }

    public void nextPermutation(int[] nums) {
        int from = nums.length - 1;
        int target = -1;
        int source = nums.length - 1;
        for (int i = from; i >= 1; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j] && j > target) {
                    target = j;
                    source = i;
                    break;
                }
            }
        }
        if (target == -1) {
            Arrays.sort(nums);
        } else {
            int temp = nums[target];
            nums[target] = nums[source];
            nums[source] = temp;
        }
        Arrays.sort(nums, target + 1, nums.length);
    }
}