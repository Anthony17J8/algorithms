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

import static org.junit.jupiter.api.Assertions.assertEquals;


public class If {

    static String yes = "YES", no = "NO";

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int a = Integer.parseInt(reader.readLine());
        int b = Integer.parseInt(reader.readLine());
        int c = Integer.parseInt(reader.readLine());
        int d = Integer.parseInt(reader.readLine());
        int e = Integer.parseInt(reader.readLine());
        writer.write(getResult(a, b, c, d, e));
        writer.flush();
        reader.close();
        writer.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    void test(int num, int a, int b, int c, int d, int e, String out) {
        assertEquals(out, getResult(a, b, c, d, e));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(1, 1, 1, 1, 1, 1, yes),
                Arguments.of(1, 2, 1, 1, 2, 2, yes),
                Arguments.of(1, 3, 1, 1, 2, 2, yes),
                Arguments.of(1, 3, 3, 1, 2, 2, no),
                Arguments.of(1, 2, 2, 2, 1, 1, no),
                Arguments.of(1, 3, 3, 2, 1, 3, no)
        );
    }

    private static String getResult(int a, int b, int c, int d, int e) {
        return (check(a, b, d, e) || check(a, c, d, e) || check(b, c, d, e)) ? yes : no;
    }

    private static boolean check(int a1, int a2, int b1, int b2) {
        return Math.max(b1, b2) - Math.max(a1, a2) >= 0 && Math.min(b1, b2) - Math.min(a1, a2) >= 0;
    }
}
