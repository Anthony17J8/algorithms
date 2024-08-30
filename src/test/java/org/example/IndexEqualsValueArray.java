package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Дан отсортированный (от наименьшего до наибольшего элемента) массив A из n разных целых чисел, которые могут быть
 * положительными, отрицательными или нулевыми. Определите, существует ли индекс i такой, что A[i] = i. Разработайте
 * самый быстрый алгоритм для решения этой задачи.
 */
public class IndexEqualsValueArray {


    // -28, -15, -5, 1, 2, 3, 6, 18, 22
    @Test
    void test() {
        Integer[] input = new Integer[] {-28, -15, -5, 0, 2, 3, 6, 18, 22};
        boolean result = getResult(input);
        assertTrue(result);
    }

    private boolean getResult(Integer[] input) {
        boolean result = false;
        for (int i = 0; i < input.length; i++) {
            if (input[i] < 0) {
                continue;
            }
            if (input[i] == i) {
                result = true;
                break;
            }
        }
        return result;
    }
}
