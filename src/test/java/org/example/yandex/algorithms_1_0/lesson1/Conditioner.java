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

public class Conditioner {

    static String auto = "auto", heat = "heat", fan = "fan", freeze = "freeze";

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] in = reader.readLine().split(" ");
        int troom = Integer.parseInt(in[0]);
        int tcond = Integer.parseInt(in[1]);
        String mode = reader.readLine();
        writer.write(String.valueOf(getResult(troom, tcond, mode)));
        writer.flush();
        reader.close();
        writer.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    void test(int num, int tRoom, int tCond, String mode, int result) {
        assertEquals(result, getResult(tRoom, tCond, mode));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(1, 10, 20, freeze, 10),
                Arguments.of(2, 20, 10, freeze, 10),
                Arguments.of(3, 10, 20, heat, 20),
                Arguments.of(4, 20, 10, heat, 20),
                Arguments.of(5, 20, 20, fan, 20),
                Arguments.of(6, 20, -10, auto, -10),
                Arguments.of(7, 1, 0, auto, 0),
                Arguments.of(8, 50, -50, fan, 50)
        );
    }

    private static int getResult(int tRoom, int tCond, String mode) {
        if (mode.equals(freeze)) {
            return Math.min(tRoom, tCond);
        }
        if (mode.equals(heat)) {
            return Math.max(tRoom, tCond);
        }
        if (mode.equals(auto)) {
            return tCond;
        }
        return tRoom;
    }
}
