/*
   Copyright 2020 Russian Post
   <p>
   This source code is Russian Post Confidential Proprietary.
   This software is protected by copyright. All rights and titles are reserved.
   You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
   Otherwise this violation would be treated by law and would be subject to legal prosecution.
   Legal use of the software provides receipt of a license from the right holder only.
 */

package org.example.yandex.algorithms_1_0.lesson3;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class I {

    public static void main(String[] args) throws Exception {
        TestHelper fs = new TestHelper();

        int n = fs.nextInt();
        int N = n;
        List<List<String>> lang = new ArrayList<>();
        while (n-- > 0) {
            int m = fs.nextInt();
            List<String> a = new ArrayList<>();
            while (m-- > 0) {
                a.add(fs.next());
            }
            lang.add(a);
        }

        Map<String, Integer> result = getResult(lang);
        var res = result.entrySet().stream()
                .collect(Collectors.groupingBy(entry -> entry.getValue() == N,
                        Collectors.mapping(Map.Entry::getKey, Collectors.toSet())));
        if (res.containsKey(true)) {
            fs.write(res.get(true).size());
            fs.writeAll(res.get(true));
        } else {
            fs.write(0);
        }
        fs.newLine();
        fs.write(result.size());
        fs.writeAll(result.keySet());
        fs.close();
    }

    private static Map<String, Integer> getResult(List<List<String>> l) {
        Map<String, Integer> m = new HashMap<>();
        for (List<String> in : l) {
            for (String k : in) {
                if (m.computeIfPresent(k, (s, cnt) -> cnt + 1) == null) {
                    m.put(k, 1);
                }
            }
        }
        return m;
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(int[][] in, int out) throws Exception {
        assertEquals(out, getResult(in));
    }

    private static Stream<Arguments> source() {
        return Stream.of(Arguments.of(new int[][]{
                        {1, 1},
                        {2, 2},
                        {3, 3},
                        {2, 1},
                        {3, 2},
                        {3, 1},
                }, 3),
                Arguments.of(new int[][]{
                        {1, 1},
                        {2, 2},
                        {3, 3},
                        {2, 1},
                        {3, 2},
                        {3, 4},
                }, 3),
                Arguments.of(new int[][]{
                        {1, 1},
                }, 1)
        );
    }

    private static int getResult(int[][] in) {
        int ans = 0;
        Set<Integer> s = new HashSet<>();
        for (int[] i : in) {
            if (!s.contains(i[0])) {
                s.add(i[0]);
                ans++;
            }
        }
        return ans;
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

        int[] readStringAsArray() {
            try {
                return Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
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
