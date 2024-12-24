/*
   Copyright 2020 Russian Post
   <p>
   This source code is Russian Post Confidential Proprietary.
   This software is protected by copyright. All rights and titles are reserved.
   You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
   Otherwise this violation would be treated by law and would be subject to legal prosecution.
   Legal use of the software provides receipt of a license from the right holder only.
 */

package org.example.yandex.algorithms_1_0.lesson4;

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
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class F {

    public static void main(String[] args) throws Exception {
        TestHelper fs = new TestHelper();
        Map<String, Map<String, Long>> table = new TreeMap<>();

        fs.readFromFile()
                .forEach(s -> {
                    String[] in = s.split(" ");
                    getResult(in, table);
                });
        //                .sorted(Map.Entry.comparingByKey())
        table.forEach((key1, value1) -> {
            try {
                fs.write(key1 + ":\n");
                //                                .sorted()
                value1
                        .forEach((key, value) -> {
                            try {
                                fs.write(key + " " + value + "\n");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        fs.close();
    }


    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(int[][] size, long out) throws Exception {
//        assertEquals(out, getResult(size));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new int[][]{
                        {3, 1}, {2, 2}, {3, 3}
                }, 5),
                Arguments.of(new int[][]{
                        {1, 2}
                }, 2)
        );
    }

    private static void getResult(String[] in, Map<String, Map<String, Long>> table) {
        String name = in[0];
        String item = in[1];
        Long total = Long.parseLong(in[2]);
        if (table.computeIfPresent(name, (k, v) -> {
            if (v.computeIfPresent(item, (k1, v1) -> v1 + total) == null) {
                v.put(item, total);
            }
            return v;
        }) == null) {
            Map<String, Long> m = new TreeMap<>();
            m.put(item, total);
            table.put(name, m);
        }
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
