//https://leetcode.com/problems/sort-colors/?envType=problem-list-v2&envId=two-pointers

package org.example.leetcode;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SortColors {

    @ParameterizedTest
    @Timeout(1)
    @MethodSource("source")
    void test(int[] in, int[] out) {
        sortColors(in);
        assertArrayEquals(out, in);
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new int[]{2, 0, 2, 1, 1, 0}, new int[]{0, 0, 1, 1, 2, 2}),
                Arguments.of(new int[]{2, 0, 1}, new int[]{0, 1, 2})

        );
    }

    public void sortColors(int[] nums) {
        int cnt0 = 0;
        int cnt1 = 0;
        int cnt2 = 0;

        for (int i : nums) {
            if (i == 0) {
                cnt0++;
            } else if (i == 1) {
                cnt1++;
            } else cnt2++;
        }
        int idx = 0;

        while (idx < nums.length) {
            if (cnt0 > 0) {
                nums[idx++] = 0;
                cnt0--;
            } else if (cnt1 > 0) {
                nums[idx++] = 1;
                cnt1--;
            } else if (cnt2 > 0) {
                nums[idx++] = 2;
                cnt2--;
            }
        }
    }
}