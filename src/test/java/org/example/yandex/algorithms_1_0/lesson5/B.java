/*
   Copyright 2020 Russian Post
   <p>
   This source code is Russian Post Confidential Proprietary.
   This software is protected by copyright. All rights and titles are reserved.
   You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
   Otherwise this violation would be treated by law and would be subject to legal prosecution.
   Legal use of the software provides receipt of a license from the right holder only.
 */

package org.example.yandex.algorithms_1_0.lesson5;

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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class B {
    static TestHelper fs = new TestHelper();


    public static void main(String[] args) throws Exception {
        int[] ints = fs.readStringAsIntArray();
        int num = ints[1];
        fs.write(getResult(fs.readStringAsIntArray(), num));
        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(int[] in, int num, int out) throws Exception {
        assertEquals(out, getResult(in, num));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new int[]{17, 7, 10, 7, 10}, 17, 4),
                Arguments.of(new int[]{17, 17, 1, 17, 17}, 17, 4),
                Arguments.of(new int[]{8, 8, 7, 8, 8}, 8, 4),
                Arguments.of(new int[]{1, 2, 3, 4, 1}, 10, 2),
                Arguments.of(new int[]{1, 2, 3, 2, 1, 3, 1, 3, 2, 1}, 3, 6),
                Arguments.of(new int[]{1, 2, 3, 4, 1}, 100, 0),
                Arguments.of(new int[]{1, 2, 3, 4, 1, 15}, 15, 1),
                Arguments.of(new int[]{2, 4, 2, 4, 6, 7, 8, 2, 11, 2, 3, 5}, 6, 4),
                Arguments.of(new int[]{1}, 1, 1),
                Arguments.of(new int[]{1, 6, 15, 63, 25, 26, 63, 31, 56, 45, 16, 2, 25, 63, 16}, 25, 2),
                Arguments.of(new int[]{1, 6, 15, 63, 25}, 25, 1),
                Arguments.of(new int[]{1}, 999_999, 0),
                Arguments.of(new int[]{1, 1, 1, 1, 1}, 3, 3),
                Arguments.of(
                        new int[]{5, 33, 69, 66, 49, 32, 32, 74, 9, 81, 44, 60, 12, 79, 58, 57, 34, 42, 33, 55, 85, 26, 11, 57, 79, 1, 22, 37, 26, 36, 22, 20, 100, 10, 29, 7, 53, 31, 3, 70, 43, 99, 26, 55, 78, 81, 70, 21, 3, 40, 48, 60, 7, 78, 62, 73, 10, 57, 70, 87, 98, 13, 58, 94, 47, 55, 25, 92, 92, 67, 7, 42, 12, 71, 46, 82, 39, 5, 44, 64, 13, 48, 78, 61, 52, 38, 7, 38, 21, 96, 3, 16, 80, 94, 71, 91, 38, 24, 60, 12},
                        38, 4),
                Arguments.of(TestHelper.generate(100_000, 1_000_000_000), 1_000_000_000, 100000)
        );
    }

    private static int getResult(int[] in, int num) {
        int[] prfx = new int[in.length + 1];
        prfx[0] = 0;
        for (int i = 1; i < prfx.length; i++) {
            prfx[i] = prfx[i - 1] + in[i - 1];
        }

        int left = 0;
        int right = 1;
        int result = 0;
        while (right < prfx.length) {
            if (left == right) {
                right++;
                continue;
            }
            if (prfx[right] - prfx[left] > num) {
                left++;
            } else if (prfx[right] - prfx[left] < num) {
                right++;
            } else if (prfx[right] - prfx[left] == num) {
                result++;
                left++;
                right++;
            }
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

        boolean isReady() throws Exception {
            return in.ready();
        }

        Stream<String> readFromFile() throws IOException {
            return Files.lines(Path.of("src/test/java/org/example/yandex/algorithms_1_0/lesson4/input.txt"));
//            return Files.lines(Path.of("input.txt"));
        }

        void write(String s) throws IOException {
            out.write(s);
        }

        void write(long l) throws IOException {
            out.write(String.valueOf(l));
        }

        void write(int i) throws IOException {
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

        void writeAllMultiline(String[] s) {
            Arrays.stream(s)
                    .filter(Objects::nonNull)
                    .forEach(System.out::println);
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
            return Stream.generate(() -> "Me")
                    .limit(size)
                    .collect(Collectors.joining());
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
