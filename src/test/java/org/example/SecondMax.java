package org.example;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * В качестве входных данных имеется неотсортированный массив из n разных чисел, где n — степень числа 2. Предложите
 * алгоритм, который определяет второе по величине число в массиве, при этом алгоритм использует не более n + log2 n – 2
 * сравнений. [Подсказка: какой информацией вы будете обладать после вычисления наибольшего числа?]
 */
public class SecondMax {

    @Test
    void name() {
        int[] x = {5, 4, 1, 8, 7, 2, 6, 9, 3};
        int expected = 8;
        assertEquals(expected, getResult(x));
    }

    private int getResult(int[] x) {
        int max = getMax(x);

        int i = 0;
        int result = Integer.MIN_VALUE;
        while (i < x.length) {
            if (x[i] < max && x[i] > result) {
                result = x[i];
            }
            i++;
        }
        return result;
    }

    private int getMax(int[] x) {
        if (x.length == 1) {
            return x[0];
        }
        int index = x.length % 2 == 0 ? x.length / 2 : x.length / 2 + 1;
        int a = getMax(Arrays.copyOfRange(x, 0, index));
        int b = getMax(Arrays.copyOfRange(x, index, x.length));
        return Math.max(a, b);
    }
}
