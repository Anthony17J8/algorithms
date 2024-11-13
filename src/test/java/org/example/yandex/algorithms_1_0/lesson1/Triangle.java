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

public class Triangle {

    static String yes = "YES", no = "NO";

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int a = Integer.parseInt(reader.readLine());
        int b = Integer.parseInt(reader.readLine());
        int c = Integer.parseInt(reader.readLine());
        writer.write(String.valueOf(getResult(a, b, c)));
        writer.flush();
        reader.close();
        writer.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    void test(int num, int a, int b, int c, String result) {
        assertEquals(result, getResult(a, b, c));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(1, 3, 4, 5, yes),
                Arguments.of(2, 3, 5, 4, yes),
                Arguments.of(3, 4, 5, 3, yes),
                Arguments.of(3, 0, 0, 0, no),
                Arguments.of(3, 0, 1, 2, no),
                Arguments.of(3, 10, 5, 6, yes),
                Arguments.of(3, 10, 4, 6, no)
        );
    }

    private static String getResult(int a, int b, int c) {
        if (a == 0 || b == 0 || c == 0) return no;
        if (a + b > c && a + c > b && b + c > a) return yes;
        return no;
    }
}
