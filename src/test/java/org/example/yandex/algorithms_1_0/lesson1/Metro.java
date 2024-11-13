/*
   Copyright 2020 Russian Post
   <p>
   This source code is Russian Post Confidential Proprietary.
   This software is protected by copyright. All rights and titles are reserved.
   You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
   Otherwise this violation would be treated by law and would be subject to legal prosecution.
   Legal use of the software provides receipt of a license from the right holder only.
 */

package org.example.yandex.algorithms_1_0.lesson1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Metro {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int a = Integer.parseInt(reader.readLine());
        int b = Integer.parseInt(reader.readLine());
        int n = Integer.parseInt(reader.readLine());
        int m = Integer.parseInt(reader.readLine());
        for (int i : getResult(a, b, n, m)) {
            writer.write(i + " ");
        }
        writer.flush();
        reader.close();
        writer.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    void test(int num, int a, int b, int n, int m, int[] out) {
        assertArrayEquals(out, getResult(a, b, n, m));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(1, 1, 3, 3, 2, new int[]{5, 7}),
                Arguments.of(1, 1, 5, 1, 2, new int[]{-1}),
                Arguments.of(1, 1, 3, 3, 1, new int[]{5, 7}),
                Arguments.of(1, 1, 1, 1000, 1000, new int[]{1999, 2001}),
                Arguments.of(1, 1, 1, 4, 4, new int[]{7, 9}),
                Arguments.of(1, 1, 1, 1, 1, new int[]{1, 3}),
                Arguments.of(1, 3, 2, 2, 3, new int[]{7, 11}),
                Arguments.of(1, 2, 1, 2, 1, new int[]{-1})
        );
    }

    private static int[] getResult(int a, int b, int n, int m) {
        int minA = n + (a * (n - 1));
        int minB = m + (b * (m - 1));
//        int midA = n + n * a;
//        int midB = m + m * b;

        int maxA = n + (a * (n + 1));
        int maxB = m + (b * (m + 1));

        if (minA > maxB || minB > maxA) {
            return new int[]{-1};
        }
        return new int[]{Math.max(minA, minB), Math.min(maxA, maxB)};
    }
}