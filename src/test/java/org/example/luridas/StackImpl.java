package org.example.luridas;

import java.util.ArrayList;

public class StackImpl<T> {
    private T[] source;

    private int currentIndex = -1;

    private int maxSize;

    private boolean limitReached;

    public StackImpl(int size) {
        this.maxSize = size;
        this.source = (T[]) new ArrayList<T>().toArray(new Object[size]);
    }

    public boolean isStackEmpty() {
        return currentIndex == -1;
    }

    public void push(T elem) {
        if (limitReached) {
            throw new IllegalArgumentException();
        }
        currentIndex++;
        source[currentIndex] = elem;
        if (currentIndex == maxSize) {
            limitReached = true;
        }
    }

    public T top() {
        if (isStackEmpty()) {
            throw new IllegalArgumentException();
        }
        return source[currentIndex];
    }

    public T pop() {
        if (isStackEmpty()) {
            throw new IllegalArgumentException();
        }
        T result = source[currentIndex];
        currentIndex--;
        return result;
    }

    public int size() {
        return currentIndex + 1;
    }
}
