package org.example.siena;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Given an array of integers temperatures represents the daily temperatures,
 * return an array answer such that answer[i] is the number of days you have to wait after the ith
 * day to get a warmer temperature. If there is no future day for which this is possible,
 * keep answer[i] == 0 instead.
 * <p>
 * Example 1:
 * <p>
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 * Example 2:
 * <p>
 * Input: temperatures = [30,40,50,60]
 * Output: [1,1,1,0]
 * Example 3:
 * <p>
 * Input: temperatures = [30,60,90]
 * Output: [1,1,0]
 */
public class DailyTemperatures {

    public int[] dailyTemperatures(int[] temperatures) { // too slow
        int[] result = new int[temperatures.length];
        int index = 0;
        for (int i = 1; i < temperatures.length; i++) {
            if (temperatures[i - 1] < temperatures[i]) {
                result[i - 1] = 1;
                continue;
            }

            if (temperatures[i - 1] >= temperatures[i]) {
                for (int j = i; j < temperatures.length; j++) {
                    if (temperatures[i - 1] >= temperatures[j]) {
                        index++;
                    } else {
                        result[i - 1] = index + 1;
                        break;
                    }
                }
                index = 0;
            }
        }
        return result;
    }

    public int[] dailyTemperaturesOptimized(int[] temperatures) {
        int[] result = new int[temperatures.length];
        LinkedList<Integer> indexes = new LinkedList<>();
        indexes.push(0);
        for (int i = 1; i < temperatures.length; i++) {
            while (!indexes.isEmpty() && temperatures[indexes.peek()] < temperatures[i]) {
                result[indexes.peek()] = i - indexes.pop();
            }
            indexes.push(i);
        }
        return result;
    }

    @Test
    void test() {
        int[] input = {73, 74, 75, 71, 69, 72, 76, 73};
        assertArrayEquals(new int[]{1, 1, 4, 2, 1, 1, 0, 0}, dailyTemperaturesOptimized(input));
        assertArrayEquals(new int[]{1, 1, 1, 0}, dailyTemperaturesOptimized(new int[]{30, 40, 50, 60}));
        assertArrayEquals(new int[]{1, 1, 0}, dailyTemperaturesOptimized(new int[]{30, 60, 90}));
        assertArrayEquals(new int[]{0, 0, 0}, dailyTemperaturesOptimized(new int[]{3, 2, 1}));
        assertArrayEquals(new int[]{1, 1, 1, 0, 0, 0, 0, 0}, dailyTemperaturesOptimized(new int[]{-20, 0, 11, 12, 11, 11, -2, -2}));
        assertArrayEquals(new int[]{3, 1, 1, 2, 1, 1, 0, 1, 1, 0}, dailyTemperaturesOptimized(new int[]{55, 38, 53, 81, 61, 93, 97, 32, 43, 78}));
        assertArrayEquals(new int[]{8, 1, 5, 4, 3, 2, 1, 1, 0, 0}, dailyTemperaturesOptimized(new int[]{89, 62, 70, 58, 47, 47, 46, 76, 100, 70}));
    }
}