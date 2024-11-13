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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SumNumbers {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] nk = reader.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);
        int[] seq = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        writer.write(String.valueOf(getResult(seq, k)));

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
                Arguments.arguments(1, 17, new int[]{17, 7, 10, 7, 10}, 4),
                Arguments.arguments(2, 10, new int[]{1, 2, 3, 4, 1}, 2),
                Arguments.arguments(3, 17, new int[]{17, 7, 2, 3, 5, 10, 7, 10}, 4),
                Arguments.arguments(4, 4, new int[]{4}, 1),
                Arguments.arguments(5, 3, new int[]{4}, 0),
                Arguments.arguments(6, 7, new int[]{1, 2, 3, 5}, 0),
                Arguments.arguments(7, 5, new int[]{10, 9, 8, 7, 6}, 0),
                Arguments.arguments(8, 3, new int[]{10, 12, 1, 2, 3}, 2),
                Arguments.arguments(8, 3, new int[]{1, 1, 1, 1, 1, 3}, 4),
                Arguments.arguments(8, 5, new int[]{8, 6, 5, 3, 2}, 2),
                Arguments.arguments(10, 1, generate(), 100000)
        );
    }

    private static int[] generate() {
        int[] in = new int[100000];
        for (int i = 0; i < 100000; i++) {
            in[i] = 1;
        }
        return in;
    }

    private static int getResult(int[] seq, int k) {
        int r = 0;
        int sumnow = seq[r];
        int count = 0;

        if (seq.length == 1) {
            return seq[0] == k ? 1 : 0;
        }

        for (int l = 0; l < seq.length; l++) {
            while (r + 1 < seq.length && sumnow < k) {
                r++;
                sumnow += seq[r];
            }
            if (sumnow == k) {
                count++;
            }
            if (l == seq.length - 1) {
                break;
            }
            if (l == r) {
                r++;
                sumnow += seq[r];
            }

            sumnow -= seq[l];
        }
        return count;
    }
}
