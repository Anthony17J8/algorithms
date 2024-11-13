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
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Equation {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] arr = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i : getResult(arr)) {
            writer.write(i + " ");
        }
        writer.flush();
        reader.close();
        writer.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    void test(int num, int[] in, int[] out) {
        assertArrayEquals(out, getResult(in));
    }

    private static Stream<Arguments> source() {
        return Stream.of(Arguments.of(1, new int[]{89, 20, 41, 1, 11}, new int[]{2, 3}),
                Arguments.of(1, new int[]{11, 1, 1, 1, 1}, new int[]{0, 1}),
                Arguments.of(1, new int[]{3, 2, 2, 2, 1}, new int[]{-1, -1}),
                Arguments.of(1, new int[]{5, 20, 2, 1, 1}, new int[]{1, 0}));
    }

    private static int[] getResult(int[] arr) {
        int k1 = arr[0];
        int m = arr[1];
        int k2 = arr[2];
        int p2 = arr[3];
        int n2 = arr[4];
        if (m == 1 && n2 == 1) {
            return new int[]{0, 1};
        }
        int num = Double.valueOf(Math.ceil(k2 * 1.0 / n2)).intValue();
        if ((p2 - 1) * num * m + 1 >= k2 || p2 * n2 * m <= k2) {
            return new int[]{-1, -1};

        }
        int p1 = 1;
        while (p1 * num * m < k1) {
            p1++;
        }
        if (n2 == 1) {
            return new int[]{p1, 0};
        }
        int k3 = k1 % (num * m);
        int n1 = Double.valueOf(Math.ceil(k3 * 1.0 / num)).intValue();
        if ((p1 - 1) * num * m + 1 >= k1 || p1 * n1 * m <= k1) {
            return new int[]{-1, -1};
        }
        return new int[]{p1, n1};
    }
}
