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

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class J {

    public static void main(String[] args) throws Exception {
        TestHelper fs = new TestHelper();

        int[] in = fs.readStringAsArray();
        int t = in[0];
        int d = in[1];
        int n = in[2];
        int[][] p = new int[n][2];
        int idx = 0;
        while (n-- > 0) {
            p[idx++] = fs.readStringAsArray();
        }

        int[][] result = getResult(p, t, d);
        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(int[][] in, int t, int d, int[][] out) throws Exception {
        assertArrayEquals(out, getResult(in, t, d));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new int[][]{
                        {0, 1},
                }, 2, 1, new int[][]{
                        {0, 1}, {1, 0}
                }),
                Arguments.of(new int[][]{
                        {0, 1},
                        {-2, 1},
                        {-2, 3},
                        {0, 3},
                        {2, 5},
                }, 2, 1, new int[][]{
                        {1, 5}, {2, 4}
                }),
                Arguments.of(new int[][]{
                        {0, 0},
                }, 1, 1, new int[][]{
                        {-1, 0}, {0, -1}, {0, 0}, {0, 1}, {1, 0}
                }),
                Arguments.of(new int[][]{
                        {0, 0},
                }, 1, 10, new int[][]{
                        {-1, 0}, {0, -1}, {0, 0}, {0, 1}, {1, 0}
                })
        );
    }

    private static RectSet intersect(RectSet s1, RectSet s2) {
        return new RectSet(Math.max(s1.xplusyMin, s2.xplusyMin), Math.min(s1.xplusyMax, s2.xplusyMax),
                Math.max(s1.xminusyMin, s2.xminusyMin), Math.min(s1.xminusyMax, s2.xminusyMax));
    }

    private static int[][] getResult(int[][] in, int t, int d) {

        RectSet rect = new RectSet(0, 0, 0, 0);

        for (int i = 0; i < in.length; i++) {
            int x = in[i][0];
            int y = in[i][1];
            RectSet drect  = new RectSet(0, 0, 0, 0);

        }


        return new int[][]{};
    }

    static class RectSet {
        int xplusyMin;
        int xplusyMax;
        int xminusyMin;
        int xminusyMax;

        public RectSet(int xplusyMin, int xplusyMax, int xminusyMin, int xminusyMax) {
            this.xplusyMin = xplusyMin;
            this.xplusyMax = xplusyMax;
            this.xminusyMin = xminusyMin;
            this.xminusyMax = xminusyMax;
        }

        public void extend(int size) {
            this.xplusyMin -= size;
            this.xplusyMax += size;
            this.xminusyMin -= size;
            this.xminusyMax += size;
        }
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
