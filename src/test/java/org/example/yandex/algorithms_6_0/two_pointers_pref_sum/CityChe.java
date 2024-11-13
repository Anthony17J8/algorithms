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
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CityChe {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] nk = reader.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int r = Integer.parseInt(nk[1]);
        int[] seq = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        writer.write(String.valueOf(getResult(seq, r)));

        writer.flush();
        reader.close();
        writer.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(int num, int k, int[] seq, int result) {
        assertEquals(result, getResult(seq, k));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.arguments(1, 4, new int[]{1, 3, 5, 8}, 2),
                Arguments.arguments(2, 3, new int[]{2, 8}, 1),
                Arguments.arguments(2, 10, new int[]{2, 8}, 0),
                Arguments.arguments(2, 1, new int[]{2, 2}, 0),
                Arguments.arguments(3, 5, new int[]{1, 2, 3, 4}, 0),
                Arguments.arguments(4, 1, new int[]{10, 11, 13, 14, 20}, 8),
                Arguments.arguments(10, 1_000_000_000, generate(), 0)
//                Arguments.arguments(10, 1, generate(), 0)
        );
    }

    private static int[] generate() {
        return IntStream.range(1, 300000)
                .toArray();
    }

    private static long getResult(int[] seq, int k) {
        long cnt = 0;
        int r = 1;
        for (int i = 0; i < seq.length; i++) {
            while (r < seq.length && seq[r] - seq[i] <= k) {
                r++;
            }
            cnt += seq.length - r;
        }
        return cnt;
    }
}
