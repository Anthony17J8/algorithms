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

public class C {

    public static void main(String[] args) throws Exception {
        TestHelper fs = new TestHelper();
        int n = fs.nextInt();
        int[] in = Arrays.stream(fs.next().split(" ")).mapToInt(Integer::parseInt).toArray();
        int x = fs.nextInt();
        fs.write(getResult(in, x));

        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(int[] in, int x, int out) {
        assertEquals(out, getResult(in, x));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 4, 6}, 5, 4),
                Arguments.of(new int[]{0, 0, 0, 0, 0}, 0, 0),
                Arguments.of(new int[]{0, 0, 0, 0, 1}, -1, 0),
                Arguments.of(new int[]{1, 2, 3, 4, 5}, 6, 5),
                Arguments.of(new int[]{5, 4, 3, 2, 1}, 3, 3),
                Arguments.of(new int[]{-5, -2, 3, -1, 1}, -3, -2),
                Arguments.of(new int[]{-5, -2, 3, -1, 1}, 8, 3),
                Arguments.of(new int[]{1}, 2, 1),
                Arguments.of(TestHelper.generate(1000, 0), 2, 0)

        );
    }

    private static int getResult(int[] in, int x) {
        if (in.length == 1) {
            return in[0];
        }
        int min = in[0];
        for (int i = 1; i < in.length; i++) {
            if (Math.abs(x - in[i]) < Math.abs(x - min)) {
                min = in[i];
            }
        }
        return min;
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
