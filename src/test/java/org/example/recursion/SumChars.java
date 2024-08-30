package org.example.recursion;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * С помощью рекурсии напишите функцию, которая принимает массив строк и возвращает общее количество символов во всех строках.
 * Например, если входной массив -["ab", "c", "def", "ghij"], то эта функция должна вернуть значение 10, так как в массиве
 * всего 10 символов.
 */
public class SumChars {

    @Test
    void test() {
        assertEquals(10, getCount(new String[]{"ab", "c", "def", "ghif"}));
    }

    private static int getCount(String[] arr) {
        if (arr.length == 1) {
            return arr[0].toCharArray().length;
        }
        return arr[0].toCharArray().length + getCount(Arrays.copyOfRange(arr, 1, arr.length));
    }
}
