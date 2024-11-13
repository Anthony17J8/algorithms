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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Details {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] arr = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        writer.write(getResult(arr));
        writer.flush();
        reader.close();
        writer.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    void test(int num, int[] in, int out) {
        assertEquals(out, getResult(in));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(1, new int[]{10, 5, 2}, 4),
                Arguments.of(1, new int[]{13, 5, 3}, 3),
                Arguments.of(1, new int[]{14, 5, 3}, 4),
                Arguments.of(1, new int[]{30, 5, 7}, 0),
                Arguments.of(1, new int[]{2, 5, 7}, 0)
        );
    }

    private static int getResult(int[] arr) {
        int n = arr[0];
        int k = arr[1];
        int m = arr[2];

        if (n < k || k < m) {
            return 0;
        }

        int ans = 0;
        int k1 = 0;
        while (n - k >= 0) {
            k1 = k;
            while (k1 - m >= 0) {
                ans++;
                k1 -= m;
            }
            n -= k;
            n += k1 % m;
        }
        return ans;
    }
}
