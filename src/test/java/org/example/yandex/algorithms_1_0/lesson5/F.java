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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class F {
    static TestHelper fs = new TestHelper();

    public static void main(String[] args) throws Exception {
        int n = fs.nextInt();
        int[] minPow = fs.readStringAsIntArray();
        int m = fs.nextInt();
        int[][] models = new int[m][];
        int idx = 0;
        while (m-- > 0) {
            models[idx++] = fs.readStringAsIntArray();
        }
        fs.write(getResult(minPow, models));

        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(int[] in, int[][] models, long out) throws Exception {
        assertEquals(out, getResult(in, models));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3}, new int[][]{
                        {1, 10},
                        {1, 5},
                        {10, 7},
                        {2, 3}
                }, 13),
                Arguments.of(new int[]{5}, new int[][]{
                        {1, 1},
                        {2, 5},
                        {3, 7},
                        {4, 3},
                }, 0),
                Arguments.of(new int[]{5}, new int[][]{
                        {1, 1},
                        {2, 5},
                        {3, 7},
                        {4, 3},
                        {5, 5},
                }, 5),
                Arguments.of(new int[]{5, 5}, new int[][]{
                        {1, 1},
                        {2, 5},
                        {3, 7},
                        {4, 3},
                        {5, 5},
                        {5, 6},
                }, 10),
                Arguments.of(new int[]{1, 2, 3, 4, 5}, new int[][]{
                        {1, 5},
                        {2, 4},
                        {3, 3},
                        {4, 2},
                        {5, 2},
                        {5, 1},
                }, 5),
                Arguments.of(new int[]{9, 1, 5}, new int[][]{
                        {10, 1},
                }, 3),
                Arguments.of(new int[]{5, 6, 7}, new int[][]{
                        {1, 1},
                }, 0),
                Arguments.of(new int[]{5, 6, 7}, new int[][]{
                        {5, 6},
                }, 6),
                Arguments.of(new int[]{800}, new int[][]{
                        {800, 1000}
                }, 1000)
        );
    }

    private static long getResult(int[] in, int[][] models) {
        Map<Integer, Integer> cache = new HashMap<>();
        List<Integer> powers = new ArrayList<>();
        for (int[] model : models) {
            powers.add(model[0]);
            if (cache.computeIfPresent((model[0]),
                    (key, val) -> (model[1] < val) ? model[1] : val) == null) {
                cache.put(model[0], model[1]);
            }
        }

        int max = powers.stream().mapToInt(Integer::intValue).max().getAsInt();
        int cur = cache.get(max);
        for (int i = max; i >= 1; i--) {
            if (cache.containsKey(i)) {
                if (cur > cache.get(i)) {
                    cur = cache.get(i);
                }
            }
            cache.put(i, cur);
        }

        long res = 0L;
        for (int i : in) {
            res += (long) cache.getOrDefault(i, 0);
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
