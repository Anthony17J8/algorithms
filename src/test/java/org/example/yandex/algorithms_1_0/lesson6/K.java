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

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class K {
    static TestHelper fs = new TestHelper();
    static int seed = 1;

    public static void main(String[] args) throws Exception {

        int[] in = fs.readStringAsIntArray();
//        long l1 = System.nanoTime();
        int n = in[0];
        int l = in[1];
        int[][] arr = new int[n][5];
        for (int i = 0; i < n; i++) {
            arr[i] = fs.readStringAsIntArray();
        }
        fs.writeAll(getResultSmallOptimization(arr, l), '\n');
//        long l2 = System.nanoTime();
//        System.out.println("TIME: " + (l2 - l1) / 1_000_000_000);
        fs.close();

    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(int[][] in, int l, long[] out) throws Exception {
        assertArrayEquals(out, getResultSmallOptimization(in, l));
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "randomSource")
    @Timeout(1)
    void randomTest(int[][] in, int l) throws Exception {
        assertArrayEquals(getResultSlow(in, l), getResultSmallOptimization(in, l));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new int[][]{
//                        {7, 8, 5, 3, 9},
//                        {5, 7, 3, 2, 7},
//                        {9, 8, 4, 2, 4},
//                        {4, 8, 7, 4, 4},
//                        {7, 8, 5, 3, 9},
//                        {8, 7, 9, 1, 2},
//                        {7, 8, 5, 3, 9}
                        {7, 8, 5, 3, 9},
                        {9, 8, 1, 7, 6}
//                        {5, 2, 5, 7, 6},
//                        {9, 8, 1, 7, 6},
//                        {7, 2, 2, 7, 9},
//                        {9, 8, 4, 2, 4},
                }, 6, new long[]{15})
        );
    }

    private static Stream<Arguments> randomSource() {
        return Stream.of(
                Arguments.of(TestHelper.generateTwoDimensionalRandom(100, 5), 10)
        );
    }

    private static long[] getResultSlow(int[][] arr, int l) {
        long[] result = new long[getSize(arr.length)];
        int idx = 0;
        for (int i = 0; i < arr.length; i++) {
            long[] items = getItems(arr[i], l);
            for (int j = i + 1; j < arr.length; j++) {
                long[] items2 = getItems(arr[j], l);
                result[idx++] = getNum(items, items2);
            }
        }
        return result;
    }

    private static long[] getResultSmallOptimization(int[][] arr, int l) {
        long[] result = new long[getSize(arr.length)];
        long[][] in = new long[arr.length][];
        for (int i = 0; i < arr.length; i++) {
            in[i] = getItems(arr[i], l);
        }

        int idx = 0;
        for (int i = 0; i < in.length; i++) {
            for (int j = i + 1; j < in.length; j++) {
                long result1 = getResult(in[i], in[j]);
                result[idx++] = result1 == -1 ? getResult(in[j], in[i]) : result1;

            }
        }
        return result;
    }

    private static long getResult(long[] a1, long[] a2) {
//        System.out.println(Arrays.toString(a1));
//        System.out.println(Arrays.toString(a2));
        int L = a1.length;
        int left = 0;
        int right = a2.length - 1;
        long result = -1L;
        while (left <= right) {
            int mid = (left + right) / 2;
            int lower = findLowerIndex(a1, a2[mid]);
            int upper = findUpperIndex(a1, a2[mid]);
//            System.out.println(Arrays.toString(a1) + ": lower[" + lower + "]" + ", upper[" + upper + "]");
            if (lower == upper) {
                if (1 + lower + mid > L) {
                    right = mid - 1;
                } else if (1 + lower + mid < L) {
                    left = mid + 1;
                } else {
                    result = a2[mid];
                    break;
                }
            } else {
                int expectedIdx = L - mid - 1;
                if (lower <= expectedIdx && expectedIdx <= upper) {
                    result = a1[lower];
                    break;
                } else if (expectedIdx < lower) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return result;
    }

    private static int findLowerIndex(long[] a1, long item) {
        int left = 0;
        int right = a1.length - 1;
        int tempIdx = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            long pivot = a1[mid];
            if (pivot < item) {
                tempIdx = mid + 1;
                left = mid + 1;
            } else if (pivot > item) {
                tempIdx = mid;
                right = mid - 1;
            } else {
                tempIdx = mid;
                right = mid - 1;
            }
        }
        return tempIdx;
    }

    private static int findUpperIndex(long[] a1, long item) {
        int left = 0;
        int right = a1.length - 1;
        int tempIdx = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            long pivot = a1[mid];
            if (pivot < item) {
                tempIdx = mid + 1;
                left = mid + 1;
            } else if (pivot > item) {
                tempIdx = mid;
                right = mid - 1;
            } else {
                tempIdx = mid + 1;
                left = mid + 1;
            }
        }
        return tempIdx;
    }

    private static long getItems2(long[] a1, long[] a2, long l) {
        int idx1 = 0;
        int idx2 = 0;
        int idx = 0;

        long result = 0;
        while (idx1 < a1.length && idx2 < a2.length) {
            if (a1[idx1] > a2[idx2]) {
                idx++;
                if (idx == l) {
                    result = a2[idx2];
                    break;
                }
                idx2++;
            } else if (a1[idx1] < a2[idx2]) {
                idx++;
                if (idx == l) {
                    result = a1[idx1];
                    break;
                }
                idx1++;
            } else {
                idx++;
                if (idx == l) {
                    result = a2[idx2];
                    break;
                }
                idx++;
                if (idx == l) {
                    result = a1[idx1];
                    break;
                }
                idx2++;
                idx1++;
            }
        }

        if (idx1 < a1.length) {
            for (int i = idx1; i < a1.length; i++) {
                idx++;
                if (idx == l) {
                    result = a1[idx1];
                    break;
                }
            }
        }
        if (idx2 < a2.length) {
            for (int i = idx2; i < a2.length; i++) {
                idx++;
                if (idx == l) {
                    result = a2[idx2];
                    break;
                }
            }
        }
        return result;
    }

    private static long[] getItems(int[] in, int l) {
//        return TestHelper.generateRandomLong(5);
        long[] items = new long[l];
        long x = in[0];
        long d = in[1];
        int a = in[2];
        int c = in[3];
        int m = in[4];
        items[0] = x;
        for (int i = 1; i < l; i++) {
            items[i] = items[i - 1] + d;
            d = (((long) a * d) + c) % m;
        }
        return items;
    }

    private static int getSize(int s) {
        int r = 0;
        while (s-- > 0) {
            r += s;
        }
        return r;
    }

    private static long getNum(long[] a1, long[] a2) {
        int idx1 = 0;
        int idx2 = 0;
        int idx = 0;
        long[] sum = new long[a1.length + a2.length];
        while (idx1 < a1.length && idx2 < a2.length) {
            if (a1[idx1] > a2[idx2]) {
                sum[idx++] = a2[idx2++];
            } else if (a1[idx1] < a2[idx2]) {
                sum[idx++] = a1[idx1++];
            } else {
                sum[idx++] = a2[idx2++];
                sum[idx++] = a1[idx1++];
            }
        }
        if (idx1 < a1.length) {
            for (int i = idx1; i < a1.length; i++) {
                sum[idx++] = a1[i];
            }
        }
        if (idx2 < a2.length) {
            for (int i = idx2; i < a2.length; i++) {
                sum[idx++] = a2[i];
            }
        }
        return sum[a1.length - 1];
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
                return Arrays.stream(in.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
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

        public static long[] generateRandomLong(int size) {
            Random random = new Random(seed++);
            long[] in = random.longs(1, 100).limit(size).toArray();
            Arrays.sort(in);
            return in;
        }

        public static int[] range(int from, int to) {
            return IntStream.range(from, to).toArray();
        }

        public static int[] generateRandomSorted(int size, int seed) {
            Random random = new Random(seed);
            int[] array = random.ints(1, 20).limit(size).toArray();
            Arrays.sort(array);
            return array;
        }

        public static int[] generateRandom(int size, int seed) {
            Random random = new Random(seed);
            return random.ints(1, 10).limit(size).toArray();
        }

        public static int[][] generateTwoDimensionalRandom(int row, int col) {
            int[][] res = new int[row][col];
            for (int i = 0; i < row; i++) {
                res[i] = generateRandom(col, i);
            }
            return res;
        }
    }
}
