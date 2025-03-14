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

public class I {
    static TestHelper fs = new TestHelper();

    public static void main(String[] args) throws Exception {
        int k = fs.nextInt();
        String text = fs.next();
        fs.write(getResultSlow(text, k));
        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(4)
    void test(String text, int k, long out) throws Exception {
        assertEquals(out, getResult(text, k));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("zabacabab", 2, 5),
                Arguments.of("abc", 2, 0),
                Arguments.of("ab", 1, 0),
                Arguments.of("abcde", 1, 0),
                Arguments.of("abcabcdaabca", 3, 7),
                Arguments.of("aaaaaaaa", 1, 28),
                Arguments.of(
                        "abbabbababbabbababbababbabbababbabbababbababbabbababbababbabbababbabbababbababbabbababbab", 7,
                        40),
                Arguments.of("abbabbababbabbababbababbabbababbabbababbababbabbababbab", 7, 22),
                Arguments.of("abbabbababbabbababbababbabbababb", 7, 11),
                // abbabbababbab_bababba+babbab
                Arguments.of("abbabbababbabbababbababbab", 7, 10),
                Arguments.of(TestHelper.generate(200000), 1, 19999900000L)

        );
    }

    private static long getResult(String text, int k) {
        long result = 0;
        int head = 0;

        for (int i = 0; i < text.length(); ) {
            head = i;
            int r = head + k;
            int cur = head;
            long temp = 0;
            while (r < text.length() && text.charAt(r) == text.charAt(cur)) {
                result++;
                temp++;
                if (temp > 1) {
                    result += temp - 1;
                    i = r - k;
                }
                if ((cur - head) + 1 == k) {
                    cur = head;
                } else {
                    cur++;
                }
                r++;
            }
            i++;
        }
        return result;
    }

    private static long getResultSlow(String text, int k) {
        long result = 0;
        int head = 0;

        for (int i = 0; i < text.length(); i++) {
            int r = head + k;
            int cur = head;
            while (r < text.length() && text.charAt(r) == text.charAt(cur)) {
                result++;
                if ((cur - head) + 1 == k) {
                    cur = head;
                } else {
                    cur++;
                }
                r++;
            }
            head++;
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
