package org.example;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommonNumInArrays {


    // 5, 2, 6, 9, 3 - 8
    // Input: [1,2,4,5], [3,3,4], [2,3,4,5,6]
    // Output: 4
    @Test
    void test() {

        Integer[] a = {1, 2, 4, 5}, b = {3, 3, 4}, c = {2, 3, 4, 5, 6};
        assertEquals(4, getCommon(a, b, c));
        assertEquals(32, 16 << 1);
    }

    private int getCommon(Integer[] a, Integer[] b, Integer[] c) {
        Map<Integer, Integer> map = Arrays.stream(a)
                .collect(Collectors.toMap(Function.identity(), val -> 1, (prev, next) -> prev));
        Arrays.stream(b)
                .forEach(i -> map.computeIfPresent(i, (key, val) -> val + 1));

        return Arrays.stream(c)
                .filter(i -> map.containsKey(i) && map.get(i) == 2)
                .findFirst().orElse(0);
    }
}
