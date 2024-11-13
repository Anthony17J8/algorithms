/*
   Copyright 2020 Russian Post
   <p>
   This source code is Russian Post Confidential Proprietary.
   This software is protected by copyright. All rights and titles are reserved.
   You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
   Otherwise this violation would be treated by law and would be subject to legal prosecution.
   Legal use of the software provides receipt of a license from the right holder only.
 */

package org.example.yandex.algorithms_1_0.lesson2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class E {

    public static void main(String[] args) throws Exception {
        TestHelper fs = new TestHelper();
        int n = fs.nextInt();
        int[] in = Arrays.stream(fs.next().split(" ")).mapToInt(Integer::parseInt).toArray();
        fs.write(getResult(in));
        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(int[] in, int out) {
        assertEquals(out, getResult(in));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new int[]{10, 20, 15, 10, 30, 5, 1}, 6),
                Arguments.of(new int[]{15, 15, 10}, 1),
                Arguments.of(new int[]{10, 15, 20}, 0),
                Arguments.of(new int[]{15, 10, 15, 15, 5, 2}, 1),
                Arguments.of(new int[]{25, 15, 8, 30, 5, 5}, 0),
                Arguments.of(new int[]{25, 15, 8, 30, 5, 2}, 5),
                Arguments.of(new int[]{25, 25, 25, 25, 5, 2}, 1),
                Arguments.of(new int[]{20, 20, 20, 20, 5, 2}, 5),
                Arguments.of(new int[]{25, 25, 2, 25, 25, 5, 2}, 1),
                Arguments.of(new int[]{1, 3, 8, 5, 2, 10}, 0),
                Arguments.of(new int[]{1, 1, 1, 1, 1, 1, 1, 2}, 0),
                Arguments.of(new int[]{1, 1, 1, 1, 1, 5, 1, 2}, 0),
                Arguments.of(new int[]{1, 5, 1, 1, 1, 5, 1, 2}, 1),
                Arguments.of(TestHelper.generate(100_000, 25), 0)
        );
    }

    private static int getResult(int[] in) {
        if (in.length < 3) {
            return 0;
        }
        int pos = 0;
        int el = 0;

        int maxIdx = findMax(in);
        for (int i = 0; i < in.length - 1; i++) {
            if (String.valueOf(in[i]).endsWith("5") && maxIdx < i && in[i] > in[i + 1] && el < in[i]) {
                el = in[i];
            }
        }

        if (el > 0) {
            for (int j : in) {
                if (j > el) {
                    pos++;
                }
            }
            pos++;
        }
        return pos;
    }

    private static int findMax(int[] in) {
        int maxIdx = 0;
        for (int i = 1; i < in.length; i++) {
            if (in[maxIdx] < in[i]) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    private static class TestHelper {
        BufferedReader in;
        BufferedWriter out;

        TestHelper() {
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        void write(String s) throws IOException {
            out.write(s);
        }

        void write(int i) throws IOException {
            out.write(String.valueOf(i));
        }

        String next() {
            try {
                return in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        void close() throws Exception {
            out.flush();
            in.close();
            out.close();
        }

        public static int[] generate(int size, int num) {
            int[] in = new int[size];
            for (int i = 0; i < size; i++) {
                in[i] = num;
            }
            return in;
        }

        public static int[] generateRandom(int size) {
            //todo
            return new int[]{};
        }
    }
}