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

public class H {
    static TestHelper fs = new TestHelper();

    public static void main(String[] args) throws Exception {
        int[] in = fs.readStringAsIntArray();
        int n = in[0];
        int k = in[1];
        String text = fs.next();
        fs.writeAll(getResult(text, k));
        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(String text, int k, int[] out) throws Exception {
        assertArrayEquals(out, getResult(text, k));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("abb", 1, new int[]{2, 1}),
                Arguments.of("ababa", 2, new int[]{4, 1}),
                Arguments.of("ababa", 1, new int[]{2, 1}),
                Arguments.of("aaabac", 1, new int[]{3, 4}),
                Arguments.of("a", 1, new int[]{1, 1}),
                Arguments.of("a", 2, new int[]{1, 1}),
                Arguments.of("ababac", 1, new int[]{3, 4}),
                Arguments.of("abaabaca", 1, new int[]{3, 5}),
                Arguments.of("abcad", 1, new int[]{4, 2}),
                Arguments.of("abcdc", 1, new int[]{4, 1}),
                Arguments.of("abcdc", 4, new int[]{5, 1})

        );
    }

    private static int[] getResult(String text, int k) {
        Map<Character, Integer> m = new HashMap<>();
        int l = 0;
        int r = 1;
        int max = 1;
        int idx = 1;
        m.put(text.charAt(l), 1);
        while (r < text.length()) {
            char rc = text.charAt(r);
            Integer newVal = m.computeIfPresent(rc, (key, val) -> val + 1);
            if (newVal == null) {
                m.put(rc, 1);
            } else if (newVal > k) {
                int diff = r - l;
                if (max < diff) {
                    idx = l + 1;
                    max = diff;
                }
                Character lc = null;
                while (l < r && (lc = text.charAt(l)) != rc) {
                    Integer i = m.computeIfPresent(lc, (key, val) -> val - 1);
                    if (i == null || i.equals(0)) {
                        m.remove(lc);
                    }
                    l++;
                }
                if (lc != null) {
                    Integer i = m.computeIfPresent(lc, (key, val) -> val - 1);
                    if (i == null || i.equals(0)) {
                        m.remove(lc);
                    }
                }
                l++;
            }
            r++;
        }
        int diff = r - l;
        if (max < diff) {
            idx = l + 1;
            max = diff;
        }

        return new int[]{max, idx};
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
