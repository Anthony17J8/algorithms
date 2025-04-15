/**
 * {@link https://leetcode.com/problems/majority-element/?envType=study-plan-v2&envId=top-interview-150}
 */

package org.example.leetcode;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MajorityElement {

    @ParameterizedTest
    @Timeout(1)
    @MethodSource("source")
    void test(int[] nums, int out) {
        assertEquals(out, majorityElement(nums));
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(
                        new int[]{3, 2, 3}, 3
                ),
                Arguments.of(
                        new int[]{2, 2, 1, 1, 1, 2, 2}, 2
                ),
                Arguments.of(
                        new int[]{1, 1, 0, 1}, 1
                )
        );
    }

    public int majorityElement(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int threshold = nums.length / 2;
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            Integer newVal = map.computeIfPresent(i, (k, v) -> v + 1);
            if (newVal == null) {
                map.put(i, 1);
            } else {
                if (newVal > threshold) {
                    res = i;
                }
            }
        }
        return res;
    }
}
