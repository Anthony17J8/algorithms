package org.example.recursion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Есть последовательность так называемых треугольных чисел, которая на- чинается как 1, 3, 6, 10, 15, 21 и продолжается
 * с N-го числа в шаблоне, равного N плюс предыдущее число. Например, седьмое число последовательности - 28, то есть 7
 * (номер числа N) плюс 21 (предыдущее число после- довательности). Напишите функцию, которая принимает номер числа N и
 * возвращает соответствующее число последовательности. Например, при передаче этой функции значения 7 она должна
 * вернуть 28.
 */
public class TriangleNumbers {

    @Test
    void test() {
        assertEquals(28, getTriangleNum(7));
    }

    private int getTriangleNum(int pos) {
        if (pos == 1) {
            return 1;
        }
        return pos + getTriangleNum(pos - 1);
    }
}
