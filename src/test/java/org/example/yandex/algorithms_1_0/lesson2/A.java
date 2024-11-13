/*
   Copyright 2020 Russian Post
   <p>
   This source code is Russian Post Confidential Proprietary.
   This software is protected by copyright. All rights and titles are reserved.
   You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
   Otherwise this violation would be treated by law and would be subject to legal prosecution.
   Legal use of the software provides receipt of a license from the right holder only.
 */

package org.example.yandex.algorithms_1_0.lesson2;

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

public class A {

    static String yes = "YES", no = "NO";

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
    void test(int[] in, String out) {
        assertEquals(out, getResult(in));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3}, yes),
                Arguments.of(new int[]{0, 0, 1}, no),
                Arguments.of(new int[]{1, 9, 7}, no),
                Arguments.of(new int[]{-3, -2, 1}, yes),
                Arguments.of(new int[]{-10, -10, -10}, no),
                Arguments.of(new int[]{-1, 2, 20}, yes),
                Arguments.of(new int[]{1, 2}, yes),
                Arguments.of(new int[]{2, 2}, no)

        );
    }

    private static String getResult(int[] in) {
        boolean result = true;
        int prev = in[0];
        for (int i = 1; i < in.length; i++) {
            if (prev >= in[i]) {
                result = false;
            }
            prev = in[i];
        }
        return result ? yes : no;
    }
}
