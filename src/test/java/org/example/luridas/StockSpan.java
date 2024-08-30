package org.example.luridas;

import java.util.LinkedList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class StockSpan {

    @Test
    public void test() {
        int[] input = {2, 5, 6, 3, 11, 2};
        int[] expected = {1, 2, 3, 1, 5, 1};
        assertArrayEquals(expected, simpleStockSpan(input));
        assertArrayEquals(expected, stackStockSpan(input));
    }

    private int[] simpleStockSpan(int[] values) {
        int[] result = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            int k = 1;
            boolean end = false;
            while (i - k >= 0 && !end) {
                if (values[i] >= values[i - k]) {
                    k++;
                } else {
                    end = true;
                }
            }
            result[i] = k;

        }
        return result;
    }

    private int[] stackStockSpan(int[] values) {
        LinkedList<Integer> stack = new LinkedList<>();
        int[] result = new int[values.length];
        result[0] = 1;
        stack.push(0);
        for (int i = 1; i < values.length; i++) {
            while (!stack.isEmpty() && values[stack.peek()] <= values[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? (i + 1) : (i - stack.peek());
            stack.push(i);
        }
        return result;
    }
}
