package org.example.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class FlattenVector2D {

    @ParameterizedTest
    @MethodSource("source")
    void test(List<List<Integer>> vec2d, int[] out) {
        Vector2D i = new Vector2D(vec2d);
        int[] res = new int[i.arr.length];
        while (i.hasNext()) {
            res[i.cursor] = i.next();
        }
        assertArrayEquals(out, res);
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(List.of(
                        List.of(1, 2),
                        List.of(3),
                        List.of(4, 5, 6)
                ), new int[]{1, 2, 3, 4, 5, 6}),
                Arguments.of(List.of(
                        List.of(7, 9),
                        List.of(5)
                ), new int[]{7, 9, 5}),
                Arguments.of(List.of(
                ), new int[]{})
        );
    }
}

class Vector2D implements Iterator<Integer> {
    int[] arr;
    int cursor = 0;

    public Vector2D(List<List<Integer>> vec2d) {
        this.arr = vec2d.stream()
                .flatMap(List::stream)
                .mapToInt(Integer::intValue)
                .toArray();
    }

    @Override
    public Integer next() {
        return arr[cursor++];
    }

    @Override
    public boolean hasNext() {
        return cursor < arr.length;
    }

    @Override
    public void remove() {

    }
}
