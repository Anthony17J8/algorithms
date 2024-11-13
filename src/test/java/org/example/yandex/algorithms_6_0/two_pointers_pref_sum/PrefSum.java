/*
   Copyright 2020 Russian Post
   <p>
   This source code is Russian Post Confidential Proprietary.
   This software is protected by copyright. All rights and titles are reserved.
   You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
   Otherwise this violation would be treated by law and would be subject to legal prosecution.
   Legal use of the software provides receipt of a license from the right holder only.
 */

package org.example.yandex.algorithms_6_0.two_pointers_pref_sum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;
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

public class PrefSum {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(reader.readLine());
        int[] seq = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] result = getResult(seq);
        for (int i : result) {
            writer.write(i +" ");
        }

        writer.flush();
        reader.close();
        writer.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(int num, int n, int[] seq, int[] result) {
        assertArrayEquals(result, getResult(seq));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.arguments(1, 5, new int[]{10, -4, 5, 0, 2}, new int[]{10, 6, 11, 11, 13}),
                Arguments.arguments(2, 1, new int[]{2}, new int[]{2}),
                Arguments.arguments(2, 3, new int[]{0, 0, 0,}, new int[]{0, 0, 0}),
                Arguments.arguments(2, 4, new int[]{-1, -2, -3, 0}, new int[]{-1, -3, -6, -6}),
                Arguments.arguments(5, 1000, generate(), generate())
        );
    }

    private static int[] generate() {
        int[] in = new int[1000];
        for (int i = 0; i < 1000; i++) {
            in[i] = 0;
        }
        return in;
    }

    private static int[] getResult(int[] seq) {
        int[] res = new int[seq.length + 1];
        res[0] = 0;
        for (int i = 0; i < seq.length; i++) {
            res[i + 1] = res[i] + seq[i];
        }

        return Arrays.copyOfRange(res, 1, res.length);
    }

}
