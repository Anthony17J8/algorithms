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
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class G {
    static TestHelper fs = new TestHelper();

    public static void main(String[] args) throws Exception {
        int[] arr = fs.readStringAsIntArray();
        int[] in = fs.readStringAsIntArray();
        fs.write(getResult(arr[1], in));
        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(int k, int[] in, long out) throws Exception {
        assertEquals(out, getResult(k, in));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(2, new int[]{1, 1, 2, 2, 3}, 9),
                Arguments.of(4, new int[]{1, 1, 2, 2, 3}, 18),
                Arguments.of(4, new int[]{1, 1, 2, 3}, 12),
                Arguments.of(4, new int[]{1, 1, 2, 3, 3}, 18),
                Arguments.of(2, new int[]{1, 4, 32,}, 0),
                Arguments.of(2, new int[]{1, 2, 3}, 0),
                Arguments.of(2, new int[]{1, 2, 2}, 3),
                Arguments.of(4, new int[]{1, 2, 3}, 6),
                Arguments.of(2, new int[]{1, 3, 7, 10, 12}, 6),
                Arguments.of(2, new int[]{1, 2, 3, 100, 200}, 0),
                Arguments.of(1, new int[]{1, 2, 3, 4}, 0),
                Arguments.of(1, new int[]{1, 1, 1, 1}, 1),
                Arguments.of(10, new int[]{1, 2, 3, 4}, 24),
                Arguments.of(10, new int[]{1, 1, 1, 1}, 1),
                Arguments.of(500, new int[]{1, 2, 6, 1}, 12),
                Arguments.of(1, new int[]{2, 2, 2, 2}, 1)

        );
    }

    static class Container {
        long num;
        long count;

        public Container(long num, long count) {
            this.num = num;
            this.count = count;
        }

        public void inc() {
            count += 1;
        }
    }

    private static long getResult(int k, int[] in) {
        Arrays.sort(in);
        List<Container> l = new ArrayList<>();
        Container first = new Container(in[0], 1);
        l.add(first);
        long result = 0;
        long prev = first.num;
        for (int i = 1; i < in.length; i++) {
            if (in[i] == prev) {
                l.get(l.size() - 1).inc();
            } else {
                Container newCont = new Container(in[i], 1);
                l.add(newCont);
                prev = newCont.num;
            }
        }
        result += task1(l);
        result += task2(l, k);
        result += task3(l, k);
        return result;
    }

    private static long task1(List<Container> l) {
        long res = 0;
        for (Container c : l) {
            if (c.count >= 3) {
                res += 1;
            }
        }
        return res;
    }

    private static long task2(List<Container> l, int k) {
        long res = 0;
        int j = 0;
        for (int i = 0; i < l.size(); i++) {
            while (j < i && l.get(j).num * k < l.get(i).num) {
                j++;
            }
            res += 6L * (i - j) * (i - j - 1) / 2;
        }
        return res;
    }

    private static long task3(List<Container> l, int k) {
        long res = 0;
        int j = 0;
        for (int i = 1; i < l.size(); i++) {
            while (j < i && l.get(j).num * k < l.get(i).num) {
                j++;
            }

            if (l.get(i).count >= 2) {
                res += 3L * (i - j);
            }
        }

        j = l.size() - 1;
        for (int i = l.size() - 2; i >= 0; i--) {
            while (j > i && l.get(i).num * k < l.get(j).num) {
                j--;
            }

            if (i != j && l.get(i).count >= 2) {
                res += 3L * (j - i);
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
