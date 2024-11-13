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

public class NotebookPositions {

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
        return Stream.of(Arguments.of(1, new int[]{10, 2, 2, 10}, new int[]{20, 2}),
                Arguments.of(1, new int[]{5, 7, 3, 2}, new int[]{5, 9}));
    }

    private static int[] getResult(int[] arr) {
        int[] result = new int[2];
        int a = arr[0];
        int b = arr[1];
        int c = arr[2];
        int d = arr[3];
        int max = Integer.MAX_VALUE;

        if (max > getS(a + c, Math.max(b, d))) {
            max = getS(a + c, Math.max(b, d));
            result[0] = a + c;
            result[1] = Math.max(b, d);
        }
        if (max > getS(a + d, Math.max(b, c))) {
            max = getS(a + d, Math.max(b, c));
            result[0] = a + d;
            result[1] = Math.max(b, c);
        }
        if (max > getS(b + c, Math.max(a, d))) {
            max = getS(b + c, Math.max(a, d));
            result[0] = b + c;
            result[1] = Math.max(a, d);
        }
        if (max > getS(d + b, Math.max(a, c))) {
            result[0] = d + b;
            result[1] = Math.max(a, c);
        }
        return result;
    }

    private static int getS(int a, int b) {
        return a * b;
    }
}
