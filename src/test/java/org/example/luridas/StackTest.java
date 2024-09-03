package org.example.luridas;

import org.junit.jupiter.api.Test;

public class StackTest {

    @Test
    void test() {
        StackImpl<Integer> stack = new StackImpl<>(5);
        System.out.println("IsEmpty:? " + stack.isStackEmpty());
        stack.push(1);
        System.out.println("IsEmpty:? " + stack.isStackEmpty());
        System.out.println(stack.pop());
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println(stack.size());
        System.out.println(stack.top());
        System.out.println(stack.size());
    }
}
