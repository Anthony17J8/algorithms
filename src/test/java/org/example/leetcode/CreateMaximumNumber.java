package org.example.leetcode;

import java.util.Arrays;

/**
 * You are given two integer arrays nums1 and nums2 of lengths m and n respectively. nums1 and nums2 represent the digits of two numbers. You are also given an integer k.
 * <p>
 * Create the maximum number of length k <= m + n from digits of the two numbers. The relative order of the digits from the same array must be preserved.
 * <p>
 * Return an array of the k digits representing the answer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [3,4,6,5], nums2 = [9,1,2,5,8,3], k = 5
 * Output: [9,8,6,5,3]
 * Example 2:
 * <p>
 * Input: nums1 = [6,7], nums2 = [6,0,4], k = 5
 * Output: [6,7,6,0,4]
 * Example 3:
 * <p>
 * Input: nums1 = [3,9], nums2 = [8,9], k = 3
 * Output: [9,8,9]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == nums1.length
 * n == nums2.length
 * 1 <= m, n <= 500
 * 0 <= nums1[i], nums2[i] <= 9
 * 1 <= k <= m + n
 */
public class CreateMaximumNumber {
    public static void main(String[] args) {
//        System.out.println(Arrays.toString(maxNumber(new int[]{3, 4, 6, 5}, new int[]{9, 1, 2, 5, 8, 3}, 5)));
//        System.out.println(Arrays.toString(maxNumber(new int[]{3, 4, 6, 5}, new int[]{7, 1, 2, 5, 8, 7}, 5)));
//        System.out.println(Arrays.toString(maxNumber(new int[]{6, 7}, new int[]{6, 0, 4}, 5)));
//        System.out.println(Arrays.toString(maxNumber(new int[]{1, 1}, new int[]{1, 0, 8}, 4)));
//        System.out.println(Arrays.toString(maxNumber(new int[]{3, 9}, new int[]{8, 9}, 3)));
//        System.out.println(Arrays.toString(maxNumber(new int[]{8, 9}, new int[]{3, 9}, 3)));
//        System.out.println(Arrays.toString(maxNumber(new int[]{1, 1, 9, 1, 2}, new int[]{1, 1, 9, 1, 1}, 3))); //992
//        System.out.println(Arrays.toString(maxNumber(new int[]{3,4,8,9,3,0}, new int[]{6,1,9,1,1,2}, 6)));
//        System.out.println(Arrays.toString(maxNumber(new int[]{9, 8}, new int[]{9, 3}, 3)));
//        System.out.println(Arrays.toString(maxNumber(new int[]{1, 6, 5, 4, 7, 3, 9, 5, 3, 7, 8, 4, 1, 1, 4}, new int[]{4, 3, 1, 3, 5, 9}, 21))); // 4,3,1,6,5,4,7,3,9,5,3,7,8,4,1,3,5,9,1,1,4
//        System.out.println(Arrays.toString(maxNumber(new int[]{2, 5, 6, 4, 4, 0}, new int[]{7, 3, 8, 0, 6, 5, 7, 6, 2}, 15))); // 7,3,8,2,5,6,4,4,0,6,5,7,6,2,0
//        System.out.println(Arrays.toString(maxNumber(new int[]{1, 6, 5, 4, 7, 3, 9, 5, 3, 7, 8, 4, 1, 1, 4}, new int[]{4, 3, 1, 3, 5, 9}, 21))); // 4,3,1,6,5,4,7,3,9,5,3,7,8,4,1,3,5,9,1,1,4
        System.out.println(Arrays.toString(maxNumber(new int[]{8, 0, 4, 4, 1, 7, 3, 6, 5, 9, 3, 6, 6, 0, 2, 5, 1, 7, 7, 7, 8, 7, 1, 4, 4, 5, 4, 8, 7, 6, 2, 2, 9, 4, 7, 5, 6, 2, 2, 8, 4, 6, 0, 4, 7, 8, 9, 1, 7, 0}, new int[]{6, 9, 8, 1, 1, 5, 7, 3, 1, 3, 3, 4, 9, 2, 8, 0, 6, 9, 3, 3, 7, 8, 3, 4, 2, 4, 7, 4, 5, 7, 7, 2, 5, 6, 3, 6, 7, 0, 3, 5, 3, 2, 8, 1, 6, 6, 1, 0, 8, 4}, 50))); // 9,9,9,9,9,8,7,5,6,3,4,2,4,7,4,5,7,7,2,5,6,3,6,7,2,2,8,4,6,0,4,7,8,9,1,7,0,3,5,3,2,8,1,6,6,1,0,8,4,0
    }

    public static int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int maxLength = Math.min(k, nums1.length + nums2.length);
        int highBound = Integer.MAX_VALUE;
        int[] result = new int[maxLength];
        int count = maxLength;
        for (int i = 0; i < count; i++) {
            int length1 = nums1.length;
            int length2 = nums2.length;
            ArrVal max1 = getMax(nums1, highBound);
            ArrVal max2 = getMax(nums2, highBound);
            if (max1.value > max2.value && length1 - max1.index + length2 >= maxLength) {
                nums1 = Arrays.copyOfRange(nums1, max1.index + 1, length1);
                result[i] = max1.value;
                maxLength--;
                highBound = Integer.MAX_VALUE;
                continue;
            }
            if (max1.value < max2.value && length2 - max2.index + length1 >= maxLength) {
                nums2 = Arrays.copyOfRange(nums2, max2.index + 1, length2);
                result[i] = max2.value;
                maxLength--;
                highBound = Integer.MAX_VALUE;
                continue;
            }
            if (max1.value == max2.value) {
                if (length1 - max1.index + length2 >= maxLength && length2 - max2.index + length1 >= maxLength) {
                    if (nums1.length == 1) {
                        nums2 = Arrays.copyOfRange(nums2, max2.index + 1, length2);
                        result[i] = max2.value;
                        maxLength--;
                        highBound = Integer.MAX_VALUE;
                        continue;
                    }
                    if (nums2.length == 1) {
                        nums1 = Arrays.copyOfRange(nums1, max1.index + 1, length1);
                        result[i] = max1.value;
                        maxLength--;
                        highBound = Integer.MAX_VALUE;
                        continue;
                    }
                    int[] before1 = Arrays.copyOfRange(nums1, 0, max1.index);
                    int[] before2 = Arrays.copyOfRange(nums2, 0, max2.index);
                    int[] after1 = new int[]{};
                    int[] after2 = new int[]{};
                    if (max1.index + 1 != nums1.length) {
                        after1 = Arrays.copyOfRange(nums1, max1.index + 1, nums1.length);

                    }
                    if (max2.index + 1 != nums2.length) {
                        after2 = Arrays.copyOfRange(nums2, max2.index + 1, nums2.length);
                    }
                    int[] arr1 = before1.length > after1.length ? before1 : after1;
                    int[] arr2 = before2.length > after2.length ? before2 : after2;

                    int counter = Math.min(arr1.length, arr2.length);
                    for (int j = 0; j < counter; j++) {
                        if (arr1[j] > arr2[j]) {
                            nums1 = Arrays.copyOfRange(nums1, max1.index + 1, length1);
                            result[i] = max1.value;
                            break;
                        }
                        if (arr2[j] > arr1[j]) {
                            nums2 = Arrays.copyOfRange(nums2, max2.index + 1, length2);
                            result[i] = max2.value;
                            break;
                        }
                    }
                    maxLength--;
                    highBound = Integer.MAX_VALUE;
                    continue;
                }

                if (length1 - max1.index + length2 >= maxLength) {
                    nums1 = Arrays.copyOfRange(nums1, max1.index + 1, length1);
                    result[i] = max1.value;
                    maxLength--;
                    highBound = Integer.MAX_VALUE;
                    continue;
                }
                if (length2 - max2.index + length1 >= maxLength) {
                    nums2 = Arrays.copyOfRange(nums2, max2.index + 1, length2);
                    result[i] = max2.value;
                    maxLength--;
                    highBound = Integer.MAX_VALUE;
                    continue;
                }
            }
            highBound = Math.max(max1.value, max2.value);
            i--;
        }

        return result;
    }

    private static ArrVal getMax(int[] arr, int highBound) {
        ArrVal val = new ArrVal();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > val.value && arr[i] < highBound) {
                val.value = arr[i];
                val.index = i;
            }
        }
        return val;
    }

    static class ArrVal {
        int index = 0;
        int value = Integer.MIN_VALUE;

        public ArrVal() {
        }
    }
}
