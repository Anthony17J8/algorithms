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

public class H {

    public static void main(String[] args) throws Exception {
        TestHelper fs = new TestHelper();
        long[] in = Arrays.stream(fs.next().split(" ")).mapToLong(Long::parseLong).toArray();
        long[] result = getResult(in);
        fs.writeAll(result);
        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(long[] in, long[] out) {
        assertArrayEquals(out, getResult(in));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new long[]{3, 5, 1, 7, 9, 0, 9, -3, 10}, new long[]{10, 9, 9}),
                Arguments.of(new long[]{4, 3, 5, 2, 5}, new long[]{5, 5, 4}),
                Arguments.of(new long[]{4, 5, 5, 2, 5}, new long[]{5, 5, 5}),
                Arguments.of(new long[]{4, 4, 5, 2, 5}, new long[]{5, 5, 4}),
                Arguments.of(new long[]{5, 4, 5, 2, 5}, new long[]{5, 5, 5}),
                Arguments.of(new long[]{5, 5, 5, 5, 5}, new long[]{5, 5, 5}),
                Arguments.of(new long[]{5, 5, 1, 1, 1}, new long[]{5, 5, 1}),
                Arguments.of(new long[]{5, 4, 1, 5, 5}, new long[]{5, 5, 5}),
                Arguments.of(new long[]{6, 4, 1, 5, 5}, new long[]{6, 5, 5}),
                Arguments.of(new long[]{-9, 4, 5, 5, 5}, new long[]{5, 5, 5}),
                Arguments.of(new long[]{-9, -4, 5, 5, 5}, new long[]{5,-4,-9}),
                Arguments.of(new long[]{-5, -30000, -12}, new long[]{-5, -12, -30000}),
                Arguments.of(new long[]{1, 2, 3}, new long[]{3, 2, 1}));
    }

    private static long[] getResult(long[] in) {
        if (in.length < 3) {
            return new long[]{};
        }
        long max = Math.max(in[2], Math.max(in[0], in[1]));
        long min = Math.min(in[2], Math.min(in[0], in[1]));
        long max3 = min;
        long max2 = getMax2(in);
        long min2 = max2;

        for (int i = 3; i < in.length; i++) {
            if (in[i] > max) {
                max3 = max2;
                max2 = max;
                max = in[i];
            } else if (in[i] > max2) {
                max3 = max2;
                max2 = in[i];
            } else if (in[i] > max3) {
                max3 = in[i];
            } else if (in[i] < min) {
                min2 = min;
                min = in[i];
            } else if (in[i] < min2) {
                min2 = in[i];
            }
        }
        if (max * max2 * max3 > min2 * min * max) {
            return new long[]{max, max2, max3};
        } else {
            return new long[]{max, min2, min};
        }
    }

    private static long getMax2(long[] arr) {
        long max = Math.max(arr[0], arr[1]);
        long max2 = Math.min(arr[0], arr[1]);
        if (arr[2] > max) {
            max2 = max;
        } else if (arr[2] > max2) {
            max2 = arr[2];
        }
        return max2;
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

        void write(long i) throws IOException {
            out.write(String.valueOf(i));
        }

        void writeAll(long[] i) throws IOException {
            for (long el : i) {
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

        long nextInt() {
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
