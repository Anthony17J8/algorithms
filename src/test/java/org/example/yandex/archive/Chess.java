/*
   Copyright 2020 Russian Post
   <p>
   This source code is Russian Post Confidential Proprietary.
   This software is protected by copyright. All rights and titles are reserved.
   You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
   Otherwise this violation would be treated by law and would be subject to legal prosecution.
   Legal use of the software provides receipt of a license from the right holder only.
 */

package org.example.yandex.archive;

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

public class Chess {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String whiteQ = reader.readLine();
        String whiteL = reader.readLine();
        String blackQ = reader.readLine();
        String result = getResult(whiteQ, whiteL, blackQ);
        writer.write(String.valueOf(result));
        writer.flush();
        reader.close();
        writer.close();
    }

    @ParameterizedTest
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    @MethodSource(value = {"input"})
    void test(String whiteQ, String whiteL, String blackQ, String result) {
        assertEquals(result, getResult(whiteQ, whiteL, blackQ));
    }

    private static String getResult(String whiteQ, String whiteL, String blackQ) {
        char[] cwq = whiteQ.toCharArray();
        char[] cwl = whiteL.toCharArray();
        char[] cbq = blackQ.toCharArray();

        if (Math.abs(cwq[0] - cbq[0]) == 1 || Math.abs(cwq[1] - cbq[1]) == 1) {
            return "Strange";
        }


        return "Normal";
    }

    private static Stream<Arguments> input() {
        return Stream.of(
                Arguments.of(
                        "b2", "a3", "c4", "Normal"
                ),
                Arguments.of(
                        "a1", "b2", "e2", "Check"
                ),
                Arguments.of(
                        "a1", "c1", "b2", "Strange"
                ),
                Arguments.of(
                        "f2", "f3", "f5", "Check"
                ),
                Arguments.of(
                        "f8", "h6", "h8", "Stalemate"
                ),
                Arguments.of(
                        "a3", "d1", "a1", "Checkmate"
                ),
                Arguments.of(
                        "b3", "a8", "a1", "Checkmate"
                ),
                Arguments.of(
                        "b3", "d1", "a1", "Check"
                ),
                Arguments.of(
                        "f2", "f3", "f5", "Check"
                )
                );
    }
}
