package org.example.luridas;

import java.util.LinkedList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RPNImpl {

    @Test
    void test() {
        String input = "1 2 3 * + 2 -";
        int expected = 5;
        String input2 = "3 5 6 + *";
        double expected2 = 33;

        assertEquals(expected, calculate(input));
        assertEquals(expected2, calculate(input2));
    }

    private int calculate(String input) {
        LinkedList<Integer> numbers = new LinkedList<>();
        LinkedList<String> operands = new LinkedList<>();
        String[] s = input.split(" ");
        for (String value : s) {
            if (isNumber(value)) {
                numbers.push(Integer.parseInt(value));
            } else {
                Integer first = numbers.pop();
                Integer second = numbers.pop();
                numbers.push(getResult(second, first, value));
            }
        }

        return numbers.pop();
    }

    private int getResult(Integer op1, Integer op2, String operand) {
        return switch (operand) {
            case "+" -> op1 + op2;
            case "-" -> op1 - op2;
            case "*" -> op1 * op2;
            case "/" -> op1 / op2;
            default -> throw new IllegalArgumentException();
        };
    }

    private boolean isNumber(String val) {
        try {
            Integer.parseInt(val);
            return true;
        } catch (NumberFormatException exc) {
            return false;
        }
    }
}
