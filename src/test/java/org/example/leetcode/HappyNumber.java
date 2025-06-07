package org.example.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HappyNumber {

    @ParameterizedTest
    @MethodSource("source")
    void test(int in, boolean out) {
        assertEquals(out, isHappy(in));
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(19, true),
                Arguments.of(2, false),
                Arguments.of(13, true)
        );
    }

    public boolean isHappy(int n) {
        Set<Integer> cache = new HashSet<>();
        return check(n, cache);
    }

    public boolean check(int n, Set<Integer> cache) {
        if (n == 1) return true;
        int m = 0;
        while (n > 0) {
            int i = n % 10;
            m += i * i;
            n /= 10;
        }

        if (m == 1) {
            return true;
        }
        if (cache.contains(m)) {
            return false;
        }
        cache.add(m);

        return check(m, cache);
    }
}
