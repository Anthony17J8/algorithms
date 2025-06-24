package org.example.leetcode;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/find-the-duplicate-number/description/?envType=problem-list-v2&envId=two-pointers
 */
public class FindDuplicateNumber {

    @ParameterizedTest
    @Timeout(1)
    @MethodSource("source")
    void test(int[] nums, int out) {
        assertEquals(out, findDuplicate(nums));
    }

    public int findDuplicate(int[] nums) {
        int[] arr = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            arr[nums[i]]++;
        }
        int result = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > 1) {
                result = i;
            }
        }
        return result;
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(
                        new int[]{1, 3, 4, 2, 2}, 2
                ),
                Arguments.of(
                        new int[]{3, 1, 3, 4, 2},
                        3
                ),
                Arguments.of(
                        new int[]{3, 3, 3, 3, 3}, 3
                )
        );
    }
}
