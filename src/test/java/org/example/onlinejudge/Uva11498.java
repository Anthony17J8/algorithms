/*
   Copyright 2020 Russian Post
   <p>
   This source code is Russian Post Confidential Proprietary.
   This software is protected by copyright. All rights and titles are reserved.
   You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
   Otherwise this violation would be treated by law and would be subject to legal prosecution.
   Legal use of the software provides receipt of a license from the right holder only.
 */

package org.example.onlinejudge;

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
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Uva11498 {

    static String divisa = "divisa", NW = "NO", NE = "NE", SE = "SE", SW = "SO";

    public static void main(String[] args) throws Exception {
        TestHelper fs = new TestHelper();
        int k;
        StringBuilder sb = new StringBuilder();
        while ((k = fs.nextInt()) != 0) {
            int idx = k;
            int[] center = fs.readStringAsIntArray();
            while (idx-- > 0) {
                int[] p = fs.readStringAsIntArray();
                sb.append(getResult(center, p)).append("\n");
            }
        }
        fs.write(sb.toString());
        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(int[] n, int[] m, String out) throws Exception {
        assertEquals(out, getResult(n, m));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new int[]{2, 1}, new int[]{10, 10}, NE),
                Arguments.of(new int[]{2, 1}, new int[]{-10, 1}, divisa),
                Arguments.of(new int[]{2, 1}, new int[]{0, 33}, NW),
                Arguments.of(new int[]{-1000, -1000}, new int[]{-1000, -1000}, divisa),
                Arguments.of(new int[]{-1000, -1000}, new int[]{0, 0}, NE),
                Arguments.of(new int[]{-1000, -1000}, new int[]{-2000, -10000}, SW),
                Arguments.of(new int[]{-1000, -1000}, new int[]{-999, -1001}, SE)
        );
    }

    private static String getResult(int[] center, int[] p) {
        int cx = center[0];
        int cy = center[1];
        int x = p[0];
        int y = p[1];
        if (x == cx || y == cy) {
            return divisa;
        }
        if (x > cx && y > cy) {
            return NE;
        } else if (x < cx && y < cy) {
            return SW;
        } else if (x < cx) {
            return NW;
        } else {
            return SE;
        }
    }

    private static class TestHelper {
        BufferedReader in;
        BufferedWriter out;

        TestHelper() {
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        boolean ready() throws IOException {
            return in.ready();
        }

        void write(String s) throws IOException {
            out.write(s);
            out.flush();
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

        void writeAll(Iterable<String> it) {
            it.forEach(o -> {
                try {
                    newLine();
                    write(o);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
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

        void newLine() throws IOException {
            out.newLine();
        }

        String next() {
            try {
                return in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        int[] readStringAsIntArray() {
            try {
                return Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        String[] readStringAsStringArray() {
            try {
                return in.readLine().split(" ");
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
