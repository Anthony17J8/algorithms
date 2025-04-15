/**
 * https://leetcode.com/problems/merge-sorted-array/?envType=study-plan-v2&envId=top-interview-150
 */

package org.example.leetcode;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeSortedArray {

    @ParameterizedTest
    @Timeout(1)
    @MethodSource("source")
    void test(int[] in1, int[] in2, int m, int n, int[] out) {
//        merge(in1, m, in2, n);
//        assertArrayEquals(out, in1);
        merge1(in1, m, in2, n);
        assertArrayEquals(out, in1);
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(
                        new int[]{1, 2, 3, 0, 0, 0},
                        new int[]{2, 5, 6},
                        3,
                        3,
                        new int[]{1, 2, 2, 3, 5, 6}
                ),
                Arguments.of(
                        new int[]{1},
                        new int[]{},
                        1,
                        0,
                        new int[]{1}
                ),
                Arguments.of(
                        new int[]{0},
                        new int[]{1},
                        0,
                        1,
                        new int[]{1}
                )
        );
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] res = new int[n + m];
        int idx1 = 0;
        int idx2 = 0;
        int idx = 0;

        while (idx1 < m && idx2 < n) {
            int n1 = nums1[idx1];
            int n2 = nums2[idx2];
            if (n1 <= n2) {
                res[idx++] = nums1[idx1++];
            } else {
                res[idx++] = nums2[idx2++];
            }
        }

        if (idx1 < m) {
            for (int i = idx1; i < m; i++) {
                res[idx++] = nums1[i];
            }
        }

        if (idx2 < n) {
            for (int i = idx2; i < n; i++) {
                res[idx++] = nums2[i];
            }
        }
        System.arraycopy(res, 0, nums1, 0, res.length);
    }

    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        int idx = n + m;
        m -= 1;
        n -= 1;

        while (m >= 0 && n >= 0) {
            if (nums1[m] <= nums2[n]) {
                nums1[--idx] = nums2[n--];
            } else {
                nums1[--idx] = nums1[m--];
            }
        }
        while (n >= 0) {
            nums1[--idx] = nums2[n--];
        }
    }
}
