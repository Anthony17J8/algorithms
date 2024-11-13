/*
   Copyright 2020 Russian Post
   <p>
   This source code is Russian Post Confidential Proprietary.
   This software is protected by copyright. All rights and titles are reserved.
   You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
   Otherwise this violation would be treated by law and would be subject to legal prosecution.
   Legal use of the software provides receipt of a license from the right holder only.
 */

package org.example.coderun;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxMulti {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(reader.readLine());
        char[][] table = new char[n][n];
        int i = 0;
        while (i < n) {
            String s = reader.readLine();
            table[i] = s.toCharArray();
            i++;
        }

        writer.write(String.valueOf(getResult(table)));
        writer.flush();
        reader.close();
        writer.close();
    }

    private static char[] getResult(char[][] table) {
        return null;
    }

    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    void test(int testNum, char[][] table, String expected) {
        assertEquals(expected, getResult(table));
    }
}
