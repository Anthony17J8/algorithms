package org.example.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://www.lintcode.com/problem/607/
 */
public class TwoSum {

    List<Integer> arr = new ArrayList<>();

    /**
     * @param number: An integer
     * @return: nothing
     */
    public void add(int number) {
        arr.add(number);
        // write your code here
    }

    /**
     * @param value: An integer
     * @return: Find if there exists any pair of numbers which sum is equal to the value.
     */
    public boolean find(int value) {
        Collections.sort(arr);
        int left = 0;
        int right = arr.size() - 1;
        while (left < right) {
            int sum = arr.get(left) + arr.get(right);
            if (sum == value) {
                return true;
            } else if (sum < value) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }
}
