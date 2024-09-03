package org.example;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Дан унимодальный массив из n различных элементов, это означает, что его элементы находятся в строго возрастающем
 * порядке до своего максимального элемента, после чего его элементы находятся в строго убывающем порядке. Разработайте
 * алгоритм вычисления максимального элемента унимодального массива, который выполняется за время O(log n).
 */
public class MaxUnimodArray {
    static int count = 0;

    // [1, 6, 9, 44, 32, 19, 9, 6, 2]
    @Test
    public void maxOfUnimodalArray() {
        Integer[] input = new Integer[] {1, 6, 8, 9, 22, 33, 34, 38, 42, 9, 6, 2};
        assertEquals(42, getResult(input));
        System.out.println(count);
    }

    private int getResult(Integer[] input) {
        if (input.length == 1) {
            return input[0];
        }
        if (input.length == 2){
            return Math.max(input[0], input[1]);
        }
        count++;
        int split = input.length / 2;
        int left = getResult(Arrays.copyOfRange(input, 0, split));
        int right = getResult(Arrays.copyOfRange(input, split, input.length));

        return Math.max(left, right);
    }
}
