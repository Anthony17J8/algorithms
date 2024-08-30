package org.example;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * Дана решетка n на n разных чисел. Число является локальным минимумом, если оно меньше, чем все его соседи. (Сосед
 * числа — это элемент, который находится выше, ниже, слева или справа. Большинство чисел имеют четырех соседей; числа,
 * расположенные по сторонам решет- ки, имеют трех; четыре угловых числа имеют двух соседей.) Используйте парадигму
 * разработки алгоритмов «разделяй и властвуй» для вычисления локального минимума, со сравнениями между парами чисел, со
 * временем исполнения O (n). (Примечание: поскольку во входных данных имеется n2 чисел, вы не можете позволить себе
 * просматривать их все.) [Подсказка: выясните, как выполнять рекурсию на решетке на за время исполнения O (n).]
 */
public class FindLocalMinimumMatrix {

    @Test
    void test() {
        Integer[][] input = new Integer[][] {
                {22, 7, 3, 4},
                {10, 3, 21, 9},
                {6, 5, 1, 8},
                {0, 2, 4, 5}
        };

        int result = getResult(input);
        System.out.println(result);
    }

    private int getResult(Integer[][] input) {
        if (input[0].length == 1 || input.length == 1) {
            return input[0][0];
        }
        int rowIndex = input.length / 2;
        int columnIndex = input[0].length / 2;
        Integer[][] a = split(input, 0, rowIndex, 0, columnIndex);
        Integer[][] b = split(input, rowIndex, input.length, 0, columnIndex);
        Integer[][] c = split(input, 0, rowIndex, columnIndex, input[0].length);
        Integer[][] d = split(input, rowIndex, input.length, columnIndex, input[0].length);
        int result = getResult(a);
        int result1 = getResult(b);
        int result2 = getResult(c);
        int result3 = getResult(d);
        return Math.min(Math.min(result, result1), Math.min(result2, result3));
    }

    private Integer[][] split(Integer[][] input, int fromRow, int toRow, int fromCol, int toCol) {
        Integer[][] result = new Integer[toRow - fromRow][toCol - fromCol];
        for (int i = 0; i < toRow - fromRow; i++) {
            if (toCol - fromCol >= 0)
                System.arraycopy(input[fromRow + i], fromCol, result[i], 0, toCol - fromCol);
        }
        return result;
    }
}
