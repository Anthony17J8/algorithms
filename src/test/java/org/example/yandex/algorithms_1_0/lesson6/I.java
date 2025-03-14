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
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class I {
    static TestHelper fs = new TestHelper();

    public static void main(String[] args) throws Exception {
        int[] in = fs.readStringAsIntArray();
        int idx = 0;
        int n = in[0];
        int r = in[1];
        int c = in[2];
        long[] N = new long[n];
        while (n-- > 0) {
            N[idx++] = fs.nextLong();
        }
        fs.write(getResult(N, r, c));
        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(long[] n, int r, int c, long out) throws Exception {
        assertEquals(out, getResult(n, r, c));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new long[]{1, 3, 6, 9, 2, 4, 5, 9, 1}, 2, 2, 0),
                Arguments.of(new long[]{1, 3, 6, 9, 2, 4, 5, 9, 1}, 2, 3, 2),
                Arguments.of(new long[]{170, 205, 225, 190, 260, 130, 225, 160}, 2, 3, 30),
                Arguments.of(new long[]{1}, 1, 1, 0),
                Arguments.of(new long[]{1, 3}, 1, 2, 2),
                Arguments.of(new long[]{1, 3}, 2, 1, 0),
                Arguments.of(new long[]{1, 3, 7, 4}, 2, 2, 3)

        );
    }

    private static long getResult(long[] n, long r, long c) {
        Arrays.sort(n);
        long kefmin = 0;
        long kefmax = n[n.length - 1] - n[0];
        long res = 0;
        while (kefmin <= kefmax) {
            long mid = (kefmax + kefmin) / 2;
            long count = count(n, mid, c);
            if (count >= r) {
                kefmax = mid - 1;
                res = mid;
            } else {
                kefmin = mid + 1;
            }
        }
        return res;
    }

    // кол-во бригад с максимальным коэф. неудобства
    private static long count(long[] n, long kmax, long c) {
        long left = 0;
        long right = left;
        long cur = 0;
        while (right < n.length) {
            long koef = n[(int) right] - n[(int) left];
            if (kmax >= koef) {
                if (right - left + 1 == c) {
                    cur++;
                    right++;
                    left = right;
                } else {
                    right++;
                }
            } else {
                left++;
            }
        }
        return cur;
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

        long[] readStringAsLongArray() {
            try {
                return Arrays.stream(in.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
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

        public static int[] generateInt(int size, int num) {
            int[] in = new int[size];
            Arrays.fill(in, num);
            return in;
        }

        public static String generate(int size) {
            return Stream.generate(() -> "a")
                    .limit(size)
                    .collect(Collectors.joining());
        }

        public static long[] generateLong(int size, int num) {
            long[] in = new long[size];
            Arrays.fill(in, num);
            return in;
        }

        public static long[] generateRandomLong(int size, int seed) {
            long[] in = new long[size];
            Random random = new Random(seed);
            Arrays.fill(in, random.nextLong());
            return in;
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
