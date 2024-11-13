/*
   Copyright 2020 Russian Post
   <p>
   This source code is Russian Post Confidential Proprietary.
   This software is protected by copyright. All rights and titles are reserved.
   You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
   Otherwise this violation would be treated by law and would be subject to legal prosecution.
   Legal use of the software provides receipt of a license from the right holder only.
 */

package org.example.yandex.algorithms_6_0;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShortsAndSocks {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        long bShorts = Long.parseLong(reader.readLine());
        long rShorts = Long.parseLong(reader.readLine());
        long bSocks = Long.parseLong(reader.readLine());
        long rSocks = Long.parseLong(reader.readLine());
        Map.Entry<Long, Long> result = getResult(bShorts, rShorts, bSocks, rSocks);
        writer.write(String.valueOf(result.getKey() + " " + result.getValue()));
        writer.flush();
        reader.close();
        writer.close();
    }

    @ParameterizedTest
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    @MethodSource(value = {"input"})
    void test(int bSh, int rSh, int bSo, int rSo, int sumSh, int sumSo) {
        Map.Entry<Long, Long> result = getResult(bSh, rSh, bSo, rSo);
        // m + n майки + носки
        assertEquals(sumSh, result.getKey());
        assertEquals(sumSo, result.getValue());
    }

    private static Stream<Arguments> input() {
        return Stream.of(
                Arguments.arguments(2, 2, 2, 2, 3, 1),
                Arguments.arguments(6, 2, 7, 3, 3, 4),
                Arguments.arguments(6, 2, 2, 2, 1, 3),
                Arguments.arguments(6, 4, 7, 0, 5, 1),
                Arguments.arguments(2, 0, 1, 0, 1, 1),
                Arguments.arguments(7, 5, 0, 1, 8, 1),
                Arguments.arguments(0, 2, 3, 1, 1, 4),
                Arguments.arguments(10, 0, 4, 2, 1, 3),
                Arguments.arguments(10, 4, 4, 0, 5, 1),
                Arguments.arguments(10, 7, 0, 6, 11, 1),
                Arguments.arguments(0, 1, 2, 3, 1, 3),
                Arguments.arguments(6, 2, 3, 7, 7, 1),
                Arguments.arguments(1, 1, 1, 1, 2, 1),
                Arguments.arguments(1, 0, 1, 1, 1, 2),
                Arguments.arguments(2, 1, 0, 1, 3, 1),
                Arguments.arguments(1, 1, 0, 1, 2, 1),
                Arguments.arguments(1, 0, 1, 20, 1, 21),
                Arguments.arguments(10, 9, 2, 2, 1, 3),
                Arguments.arguments(11, 9, 2, 2, 1, 3),
                Arguments.arguments(10, 10, 10, 10, 11, 1),
                Arguments.arguments(114, 299, 921, 166, 300, 1),
                Arguments.arguments(2, 8, 20, 5, 9, 1),
                Arguments.arguments(3, 8, 20, 4, 9, 1),
                Arguments.arguments(3, 10, 4, 2, 1, 5),
                Arguments.arguments(790, 493, 507, 302, 1, 508),
                Arguments.arguments(10, 5, 7, 2, 1, 8),
                Arguments.arguments(10, 5, 20, 2, 6, 3),
                Arguments.arguments(10, 5, 1, 2, 1, 3),
                Arguments.arguments(2, 5, 5, 20, 6, 1),
                Arguments.arguments(2, 5, 2, 20, 3, 3),
                Arguments.arguments(0, 20, 0, 1, 1, 1),
                Arguments.arguments(3, 20, 0, 3, 4, 1),
                Arguments.arguments(3, 0, 1, 2, 1, 3),
                Arguments.arguments(3, 0, 3, 1, 1, 2),
                Arguments.arguments(46411, 94271, 41855, 95965, 46412, 41856),
                Arguments.arguments(2, 8, 3, 10, 3, 4),
                Arguments.arguments(1_000_000_000, 1_000_000_000, 1_000_000_000, 1_000_000_000, 1_000_000_001, 1)
        );
    }

    private static Map.Entry<Long, Long> getResult(long bSh, long rSh, long bSo, long rSo) {
        if (bSh == 0) {
            return Map.entry(getSum(bSh, rSh), bSo + 1);
        } else if (rSh == 0) {
            return Map.entry(getSum(bSh, rSh), rSo + 1);
        } else if (bSo == 0) {
            return Map.entry(bSh + 1, getSum(bSo, rSo));
        } else if (rSo == 0) {
            return Map.entry(rSh + 1, getSum(bSo, rSo));
        } else {
            if (Math.max(bSh, rSh) <= Math.max(bSo, rSo)) {
                if ((Math.max(bSh, rSh) + 2) <= ((Math.min(bSh, rSh) + 1) + Math.min(bSo,
                        rSo) + 1) || ((bSh - rSh) * (bSo - rSo) <= 0) && bSh != rSh && bSo != rSo) {
                    return Map.entry(Math.max(bSh, rSh) + 1, 1L);
                } else {
                    return Map.entry(getSum(bSh, rSh), getSum(rSo, bSo));
                }
            } else {
                if ((Math.max(rSo, bSo) + 2) <= ((Math.min(bSo, rSo) + 1) + Math.min(bSh,
                        rSh) + 1) || ((bSh - rSh) * (bSo - rSo) <= 0) && bSh != rSh && bSo != rSo) {
                    return Map.entry(1L, Math.max(bSo, rSo) + 1);
                } else {
                    return Map.entry(getSum(bSh, rSh), getSum(rSo, bSo));
                }
            }
        }
    }

    private static long getSum(long num1, long num2) {
        return (num1 == num2) ?
                num1 + 1 :
                Math.min(num1, num2) + 1;
    }
}
