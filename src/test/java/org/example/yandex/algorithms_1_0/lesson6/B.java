/*
   Copyright 2020 Russian Post
   <p>
   This source code is Russian Post Confidential Proprietary.
   This software is protected by copyright. All rights and titles are reserved.
   You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
   Otherwise this violation would be treated by law and would be subject to legal prosecution.
   Legal use of the software provides receipt of a license from the right holder only.
 */

package org.example.yandex.algorithms_1_0.lesson6;

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

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class B {
    static TestHelper fs = new TestHelper();

    public static void main(String[] args) throws Exception {
        int[] in = fs.readStringAsIntArray();
        int[] nArr = fs.readStringAsIntArray();
        int[] kArr = fs.readStringAsIntArray();
        int[] result = getResult(nArr, kArr);
        fs.writeAll(result, '\n');
        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(int[] nArr, int[] kArr, int[] out) throws Exception {
        assertArrayEquals(out, getResult(nArr, kArr));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new int[]{1, 3, 5, 7, 9},
                        new int[]{2, 4, 8, 1, 6},
                        new int[]{1, 3, 7, 1, 5}
                ),
                Arguments.of(new int[]{1, 1, 4, 4, 8, 120},
                        new int[]{1, 2, 3, 4, 5, 6, 7, 8, 63, 64, 65},
                        new int[]{1, 1, 4, 4, 4, 4, 8, 8, 8, 8, 120}
                ),
                Arguments.of(new int[]{-5, 1, 1, 3, 5, 5, 8, 12, 13, 16},
                        new int[]{0, 3, 7, -17, 23, 11, 0, 11, 15, 7},
                        new int[]{1, 3, 8, -5, 16, 12, 1, 12, 16, 8}
                )
        );
    }

    private static int[] getResult(int[] nArr, int[] kArr) {
        int[] res = new int[kArr.length];
        for (int i = 0; i < kArr.length; i++) {
            res[i] = findNum(nArr, kArr[i]);
        }
        return res;
    }

    private static int findNum(int[] in, int num) {
        int l = 0;
        int r = in.length - 1;
        int rel = in[r];
        int lel = in[l];
        int res = in[0];
        boolean resFound = false;
        while (l <= r) {
            int mid = (r + l) / 2;
            if (num < in[mid]) {
                r = mid - 1;
                rel = in[mid];
            } else if (num > in[mid]) {
                l = mid + 1;
                lel = in[mid];
            } else {
                resFound = true;
                res = in[mid];
                break;
            }
        }
        if (!resFound) {
            if (num - lel > rel - num) {
                res = rel;
            } else if (num - lel < rel - num) {
                res = lel;
            } else if (num - lel == rel - num) {
                res = lel;
            }
        }
        return res;
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

        void writeAll(long[] i, char sep) throws IOException {
            for (long el : i) {
                out.write(el + "" + sep);
            }
        }

        void writeAll(int[] i) throws IOException {
            for (int el : i) {
                out.write(el + " ");
            }
        }

        void writeAll(int[] i, char sep) throws IOException {
            for (int el : i) {
                out.write(el + "" + sep);
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

        void writeAll(String[] w, char sep) throws IOException {
            for (String b : w) {
                out.write(b + sep);
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
            return Stream.generate(() -> "a")
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
