package org.example.recursion;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * С помощью рекурсии напишите функцию, которая принимает массив чисел и возвращает новый,
 * в котором будут только четные.
 */
public class OnlyEvenNumbers {

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 2, 3, 5, 6, 10};
        List<Integer> l = new ArrayList<>();
        for (int a : onlyEven(arr, l, 0)) {
            System.out.println(a);
        }
    }

    private static List<Integer> onlyEven(int[] arr, List<Integer> l, int idx1) {
        if (idx1 == arr.length) {
            return l;
        }
        if (arr[idx1] % 2 == 0) {
            l.add(arr[idx1]);
        }
        return onlyEven(arr, l, ++idx1);
    }
}
