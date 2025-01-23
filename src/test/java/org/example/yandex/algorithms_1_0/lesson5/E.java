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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class E {
    static TestHelper fs = new TestHelper();

    public static void main(String[] args) throws Exception {
        int[] arr = fs.readStringAsIntArray();
        int r = arr[1];
        int[] in = fs.readStringAsIntArray();
        fs.writeAll(getResult(in, r));
        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(int[] in, int k, int[] out) throws Exception {
        assertArrayEquals(out, getResult(in, k));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 1, 3, 2}, 3, new int[]{2, 4}),
                Arguments.of(new int[]{3, 2, 1, 1, 1}, 3, new int[]{1, 3}),
                Arguments.of(new int[]{1, 2, 2, 1, 1, 1, 2, 1, 2, 1}, 2, new int[]{1, 2}),
                Arguments.of(new int[]{2, 4, 2, 3, 3, 1}, 4, new int[]{2, 6}),
                Arguments.of(new int[]{1, 1, 1}, 1, new int[]{2, 2}),
                Arguments.of(new int[]{1, 2, 3, 4, 5, 6}, 6, new int[]{1, 6}),
                Arguments.of(new int[]{1, 2, 2, 2, 3, 2, 1}, 3, new int[]{5, 7})
        );
    }

    private static int[] getResult(int[] in, int r) {
        if (in.length == 1) {
            return new int[]{1, 1};
        }
        Map<Integer, Integer> cache = new HashMap<>();
        int left = 0;
        cache.put(in[left], 1);
        int right = 1;
        int diff = in.length;
        int resL = 0;
        int resR = 0;
        while (right < in.length) {

            if (!cache.containsKey(in[right])) {
                cache.put(in[right], 1);
            } else {
                cache.computeIfPresent(in[right], (k, v) -> v + 1);
            }

            if (cache.size() == r) {
                while (cache.size() == r) {
                    int newDiff = right - left;
                    if (newDiff < diff) {
                        diff = newDiff;
                        resL = left;
                        resR = right;
                    }
                    if (cache.computeIfPresent(in[left], (k, v) -> v - 1) == 0) {
                        cache.remove(in[left]);
                    }
                    left++;
                }
            }
            right++;
        }
        return new int[]{resL + 1, resR + 1};
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
