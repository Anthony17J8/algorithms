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

public class A {
    static TestHelper fs = new TestHelper();
    static String yes = "YES", no = "NO";

    public static void main(String[] args) throws Exception {
        int[] in = fs.readStringAsIntArray();
        int[] nArr = fs.readStringAsIntArray();
        int[] kArr = fs.readStringAsIntArray();
        String[] result = getResult(nArr, kArr);
        fs.writeAll(result, '\n');
        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(int[] nArr, int[] kArr, String[] out) throws Exception {
        assertArrayEquals(out, getResult(nArr, kArr));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new int[]{1, 61, 126, 217, 2876, 6127, 39162, 98126, 712687, 1000000000},
                        new int[]{100, 6127, 1, 61, 200, -10000, 1, 217, 10000, 1000000000},
                        new String[]{no, yes, yes, yes, no, no, yes, yes, no, yes}
                ),
                Arguments.of(new int[]{-8, -6, -4, -4, -2, -1, 0, 2, 3, 3},
                        new int[]{8, 3, -3, -2, 2, -1, 2, 9, -8, 0},
                        new String[]{no, yes, no, yes, yes, yes, yes, no, yes, yes}
                ),
                Arguments.of(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                        new int[]{-2, 0, 4, 9, 12, 10},
                        new String[]{no, no, yes, yes, no, yes}
                )
        );
    }

    private static String[] getResult(int[] nArr, int[] kArr) {
        String[] res = new String[kArr.length];
        for (int i = 0; i < kArr.length; i++) {
            res[i] = findNum(nArr, kArr[i]);
        }
        return res;
    }

    private static String findNum(int[] in, int num) {
        int l = 0;
        int r = in.length - 1;
        String res = no;
        while (l <= r) {
            int mid = (r + l) / 2;
            if (num < in[mid]) {
                r = mid - 1;
            } else if (num > in[mid]) {
                l = mid + 1;
            } else {
                res = yes;
                break;
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
