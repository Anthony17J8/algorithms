package org.example.luridas;

import java.util.Arrays;

public class ArrayQueueImpl {

    private int[] arr = new int[9];

    private int head;

    private int tail;

    public void push(int el) {

        if (tail + 1 == head) {
            System.out.println("Full");
            return;
        }
        if (arr.length == tail + 1) {
            tail = 0;
        }
        tail++;
        arr[tail] = el;
    }

    public int pop() {
        if (head == tail) {
            System.out.println("empty");
            return 0;
        }

        if (arr.length == tail + 1) {
            head = 0;
        }
        int res = arr[head];
        arr[head] = 0;
        head++;
        return res;
    }

    @Override
    public String toString() {
        return "ArrayQueueImpl{" +
            "arr=" + Arrays.toString(arr) +
            ", head=" + head +
            ", tail=" + tail +
            '}';
    }

    public static void main(String[] args) {
        ArrayQueueImpl arrayQueue = new ArrayQueueImpl();
        arrayQueue.push(1);
        arrayQueue.push(4);
        arrayQueue.push(3);
        System.out.println(arrayQueue);
        System.out.println(arrayQueue.pop());
        System.out.println(arrayQueue.pop());
        System.out.println(arrayQueue.pop());
        System.out.println(arrayQueue.pop());
        System.out.println(arrayQueue.pop());
        System.out.println(arrayQueue.pop());
        System.out.println(arrayQueue);
        arrayQueue.push(3);
        System.out.println(arrayQueue);
        arrayQueue.push(3);
        System.out.println(arrayQueue);
    }
}
