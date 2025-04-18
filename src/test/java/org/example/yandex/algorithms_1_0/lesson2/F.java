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

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class F {

    public static void main(String[] args) throws Exception {
        TestHelper fs = new TestHelper();
        int n = fs.nextInt();
        int[] in = Arrays.stream(fs.next().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] result = getResult(in);
        fs.write(result.length);
        fs.write("\n");
        if (result.length > 1) {
            fs.writeAll(result);
        }
        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(int[] in, int[] out) {
        assertArrayEquals(out, getResult(in));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 4, 5, 4, 3, 2, 1}, new int[]{}),
                Arguments.of(new int[]{1, 2, 1, 2, 2}, new int[]{1, 2, 1}),
                Arguments.of(new int[]{1, 2, 3, 4, 5}, new int[]{4, 3, 2, 1}),
                Arguments.of(new int[]{1, 2, 3, 4, 5, 4, 3}, new int[]{2, 1}),
                Arguments.of(new int[]{1, 2, 3, 4, 5, 5, 5}, new int[]{4, 3, 2, 1}),
                Arguments.of(new int[]{1, 2, 3, 4, 5, 1, 5}, new int[]{4, 3, 2, 1}),
                Arguments.of(new int[]{1, 2, 3, 4, 5, 1}, new int[]{5, 4, 3, 2, 1}),
                Arguments.of(new int[]{1, 2}, new int[]{1}),
                Arguments.of(new int[]{1, 2, 1}, new int[]{}),
                Arguments.of(new int[]{1, 2, 2}, new int[]{1}),
                Arguments.of(new int[]{1, 1}, new int[]{}),
                Arguments.of(new int[]{1, 2, 3, 1}, new int[]{3, 2, 1}),
                Arguments.of(new int[]{1, 2, 1, 1, 2, 2, 1}, new int[]{1, 2, 1}),
                Arguments.of(new int[]{1, 2, 1, 3, 4, 2, 2, 1}, new int[]{2, 2, 4, 3, 1, 2, 1})
        );
    }

    private static int[] getResult(int[] in) {
        if (in.length < 2) {
            return new int[]{};
        }

        int endIdx = in.length - 1;
        int startIdx = 0;
        int to = -1;
        while (startIdx < endIdx) {
            if (in[startIdx] != in[endIdx]) {
                to = startIdx;
                endIdx = in.length - 1;
            } else {
                endIdx--;
            }
            startIdx++;
        }
        if (to == -1) {
            return new int[]{};
        }
        int[] result = new int[to + 1];
        int idx = 0;
        while (to >= 0) {
            result[idx] = in[to];
            idx++;
            to--;
        }
        return result;
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

        void writeAll(int[] i) throws IOException {
            for (int el : i) {
                out.write(el + " ");
            }
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
