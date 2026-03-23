package org.example.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/circular-array-loop/description/?envType=problem-list-v2&envId=two-pointers
 */
public class CircularArrayLoop {
    @ParameterizedTest
    @MethodSource("source")
    void test(int[] nums, boolean out) {
        assertEquals(out, circularArrayLoop(nums));
    }

    static Stream<Arguments> source() {
        return Stream.of(

        );
    }

    public boolean circularArrayLoop(int[] nums) {
        return false;
    }
}
