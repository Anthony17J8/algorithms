package org.example;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MergeSortTest {

    @Test
        //[5,4,1,8,7,2,6,3]
    void mergeSortTest() {
        int[] x = {5, 4, 1, 8, 7, 2, 6, 3};
        String expected = "[1, 2, 3, 4, 5, 6, 7, 8]";
        int[] result = mergeSort(x);

        assertEquals(expected, Arrays.toString(result));
    }

    private int[] mergeSort(int[] x) {
        if (x.length == 1) {
            return x;
        }
        int index = x.length % 2 == 0 ? x.length / 2 : x.length / 2 + 1;
        int[] a = mergeSort(Arrays.copyOfRange(x, 0, index));
        int[] b = mergeSort(Arrays.copyOfRange(x, index, x.length));
        return merge(a, b);
    }

    private int[] merge(int[] a, int[] b) {
        int[] res = new int[a.length + b.length];
        int k = 0;
        int i = 0;
        int j = 0;
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                res[k] = a[i];
                i++;
            } else {
                res[k] = b[j];
                j++;
            }
            k++;
        }

        if (i < a.length) {
            while (i < a.length) {
                res[k] = a[i];
                k++;
                i++;
            }
        }
        if (j < b.length) {
            while (j < b.length) {
                res[k] = b[j];
                k++;
                j++;
            }
        }
        return res;
    }
}
