package org.example.recursion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Эта проблема известна как проблема «уникальных путей»: допустим, увас есть сетка строк и столбцов. Напишите функцию,
 * которая принимает чис- ло строк и столбцов и вычисляет количество возможных «кратчайших» путей от верхнего левого
 * квадрата до нижнего правого.
 */
public class ShortestPath {

    @Test
    void test() {
        assertEquals(20, totalPaths(4, 4));
    }

    private int totalPaths(int cols, int rows) {
        if (cols == 1 || rows == 1) {
            return 1;
        }
        return totalPaths(cols, rows - 1) + totalPaths(cols - 1, rows);
    }
}
