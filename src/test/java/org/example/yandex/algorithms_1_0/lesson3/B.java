/*
   Copyright 2020 Russian Post
   <p>
   This source code is Russian Post Confidential Proprietary.
   This software is protected by copyright. All rights and titles are reserved.
   You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
   Otherwise this violation would be treated by law and would be subject to legal prosecution.
   Legal use of the software provides receipt of a license from the right holder only.
 */

package org.example.yandex.algorithms_1_0.lesson3;

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
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class B {

    public static void main(String[] args) throws Exception {
        TestHelper fs = new TestHelper();

        int[] in1 = fs.readStringAsArray();
        int[] in2 = fs.readStringAsArray();
        fs.writeAll(getResult(in1, in2));
        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(int[] in1, int[] in2, int[] out) throws Exception {
        assertArrayEquals(out, getResult(in1, in2));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3}, new int[]{4, 3, 2}, new int[]{2, 3}),
                Arguments.of(new int[]{1, 2, 1, 1, 1, 1, 3}, new int[]{1, 4, 3, 2}, new int[]{1, 2, 3}),
                Arguments.of(new int[]{3}, new int[]{1}, new int[]{}),
                Arguments.of(new int[]{10, 9, 8}, new int[]{8, 11, 10, 2, 3}, new int[]{8, 10}),
                Arguments.of(new int[]{1}, new int[]{1}, new int[]{1}),
                Arguments.of(new int[]{}, new int[]{}, new int[]{}),
                Arguments.of(new int[]{10, 1, 2, 222, 5555, 1111}, new int[]{1, 2, 1111, 5555, 2214, 453},
                        new int[]{1, 2, 1111, 5555}),
                Arguments.of(new int[]{1, 2, 6, 4, 5, 7}, new int[]{10, 2, 3, 4, 8}, new int[]{2, 4}),
                Arguments.of(TestHelper.range(0, 100_000), TestHelper.generate(0, 100_000), new int[]{})

        );
    }

    private static int[] getResult(int[] in1, int[] in2) {
        Set<Integer> s2 = Arrays.stream(in2).boxed().collect(Collectors.toSet());
        return Arrays.stream(in1)
                .boxed()
                .collect(Collectors.toSet())
                .stream()
                .filter(s2::contains)
                .mapToInt(Integer::intValue)
                .sorted().toArray();
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

        void writeAll(int[] i) throws IOException {
            for (int el : i) {
                out.write(el + " ");
            }
        }

        void writeAll(String[] w) throws IOException {
            for (String b : w) {
                out.write(b + " ");
            }
        }

        void writeOneByOne(char[][] in) throws IOException {
            for (int i = 0; i < in.length; i++) {
                for (int j = 0; j < in[0].length; j++) {
                    out.write(in[i][j] + " ");
                }
                out.write("\n");
            }
        }

        String next() {
            try {
                return in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        int[] readStringAsArray() {
            try {
                return Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
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

        public static int[] range(int from, int to) {
            return IntStream.range(from, to).toArray();
        }

        public static int[] generateRandom(int size) {
            //todo
            return new int[]{};
        }
    }
}
