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
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class G {

    public static void main(String[] args) throws Exception {
        TestHelper fs = new TestHelper();

        int n = fs.nextInt();
        int[][] in = new int[n][2];
        int idx = 0;
        while (n-- > 0) {
            in[idx++] = fs.readStringAsArray();
        }

        fs.write(getResult(in));
        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(int[][] in, int out) throws Exception {
        assertEquals(out, getResult(in));
    }

    private static Stream<Arguments> source() {
        return Stream.of(Arguments.of(new int[][]{
                        {0, 0},
                }, 1),
                Arguments.of(new int[][]{
                        {1, 0},
                }, 0),
                Arguments.of(new int[][]{
                        {2, 0},
                        {0, 2},
                        {2, 2}
                }, 2),
                Arguments.of(new int[][]{
                        {0, 4},
                        {1, 3},
                        {2, 2},
                        {3, 1},
                        {4, 0}
                }, 5),
                Arguments.of(new int[][]{
                        {0, 4},
                        {1, 3},
                        {2, -2},
                        {3, 1},
                        {4, 0}
                }, 5),
                Arguments.of(new int[][]{
                        {9, 1},
                        {8, 1},
                        {7, 2},
                        {6, 2},
                        {5, 3},
                        {4, 4},
                        {3, 6},
                        {2, 7},
                        {3, 6},
                        {0, 8}
                }, 4)
        );
    }

    private static int getResult(int[][] in) {
        Set<String> s = new HashSet<>();
        for (int[] i : in) {
            if (Math.abs(i[0]) + Math.abs(i[1]) == in.length - 1) s.add(hash(Math.abs(i[0]), Math.abs(i[1])));
        }
        return s.size();
    }

    private static String hash(int f, int b) {
        return f + "," + b;
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
            Arrays.fill(in, num);
            return in;
        }

        public static String generate(int size) {
            return Stream.iterate("A", s -> s + "A")
                    .limit(size)
                    .reduce((first, sec) -> sec)
                    .get();
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
