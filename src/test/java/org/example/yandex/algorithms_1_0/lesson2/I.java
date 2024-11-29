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

public class I {

    static char asterisk = '*';

    public static void main(String[] args) throws Exception {
        TestHelper fs = new TestHelper();
        int[] in = Arrays.stream(fs.next().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = in[0];//кол-во строк
        int m = in[1]; // кол-во столбцов
        int k = in[2];
        int[][] bomb = new int[k][2];
        int i = 0;
        char[][] field = new char[n][m];
        while (i < k) {
            String[] b = fs.next().split(" ");
            bomb[i] = new int[]{Integer.parseInt(b[0]), Integer.parseInt(b[1])};
            i++;
        }
        char[][] result = getResult(bomb, field);
        fs.writeOneByOne(result);
        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(int n, int m, int[][] booms, char[][] out) throws Exception {
        char[][] field = new char[n][m];
        assertArrayEquals(out, getResult(booms, field));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(3, 2, new int[][]{
                                {1, 1}, {2, 2}
                        },
                        new char[][]{
                                {'*', '2'},
                                {'2', '*'},
                                {'1', '1'}}),
                Arguments.of(2, 2, new int[][]{},

                        new char[][]{
                                {'0', '0'},
                                {'0', '0'}
                        }),
                Arguments.of(4, 4,
                        new int[][]{
                                {1, 3}, {4, 2}, {4, 4}, {2, 1}
                        },
                        new char[][]{
                                {'1', '2', '*', '1'},
                                {'*', '2', '1', '1'},
                                {'2', '2', '2', '1'},
                                {'1', '*', '2', '*'},
                        }),
                Arguments.of(4, 4,
                        new int[][]{
                                {1, 1}, {1, 2}, {2, 1}, {2, 2}
                        },
                        new char[][]{
                                {'*', '*', '2', '0'},
                                {'*', '*', '2', '0'},
                                {'2', '2', '1', '0'},
                                {'0', '0', '0', '0'},
                        }),
                Arguments.of(100, 100,
                        new int[][]{
                        },
                        generate(100, 100))
        );
    }

    private static char[][] generate(int n, int m) {
        char[][] f = new char[n][m];
        for (int i = 0 ; i < n; i++) {
            for (int j = 0 ; j < m; j++) {
                f[i][j] = '0';
            }
        }
        return f;
    }

    private static char[][] getResult(int[][] boom, char[][] field) {
        for (int i = 0 ; i < field.length; i++) {
            for (int j = 0 ; j < field[0].length; j++) {
                field[i][j] = '0';
            }
        }
        for (int[] ints : boom) {
            field[ints[0]-1][ints[1]-1] = '*';
        }

        for (int[] b : boom) {
            int startRow = ((b[0] - 1) - 1) >= 0 ? ((b[0] - 1) - 1) : b[0] - 1;
            int startCol = ((b[1] - 1) - 1) >= 0 ? ((b[1] - 1) - 1) : b[1] - 1;
            int endRow = ((b[0]) + 1) <= field.length ? ((b[0]) + 1) : b[0];
            int endCol = ((b[1]) + 1) <= field[0].length ? ((b[1]) + 1) : b[1];


            for (int i = startRow; i < endRow; i++) {
                for (int j = startCol; j < endCol; j++) {
                    char c = field[i][j];
                    if (c != asterisk) {
                        field[i][j] = (char)((int) field[i][j] +1);
                    }
                }
            }
        }

        return field;
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

        void writeOneByOne(char[][] in) throws IOException {
            for(int i = 0; i < in.length; i++) {
                for (int j = 0 ; j < in[0].length; j++){
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

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
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
