
/*
   Copyright 2020 Russian Post
   <p>
   This source code is Russian Post Confidential Proprietary.
   This software is protected by copyright. All rights and titles are reserved.
   You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
   Otherwise this violation would be treated by law and would be subject to legal prosecution.
   Legal use of the software provides receipt of a license from the right holder only.
 */

package org.example.yandex.algorithms_2_0.A.lesson2;

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

public class E {
    static TestHelper fs = new TestHelper();
    static int seed = 2025;

    public static void main(String[] args) throws Exception {
        int[] xy = fs.readStringAsIntArray();
        int[] x3y3r = fs.readStringAsIntArray();
        fs.write(getResultFast(xy, x3y3r));
        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(int[] xy, int[] x3y3r, long out) throws Exception {
//        assertEquals(out, getResult(xy, x3y3r));
//        assertEquals(getResult(xy, x3y3r), getResultFast(xy, x3y3r));
        assertEquals(out, getResultFast(xy, x3y3r));
    }


    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new int[]{-100_000, -100_000, 100_000, 100_000},
                        new int[]{0, 0, 100_000}, 31415925457L),
                Arguments.of(new int[]{0, 0, 5, 4}, new int[]{4, 0, 3}, 14),
                Arguments.of(new int[]{0, 0, 5, 4}, new int[]{10, 0, 3}, 0),
                Arguments.of(new int[]{-10, 0, 0, 12}, new int[]{-5, 6, 7}, 131),
                Arguments.of(new int[]{-2, 0, 0, 6}, new int[]{-1, 3, 2}, 11),
                Arguments.of(new int[]{50000, 50000, 51000, 51000}, new int[]{55000, 54000, 5300}, 91948)
        );
    }

    // slow
    private static long getResult(int[] xy, int[] x3y3r) {
        int x1 = xy[0];
        int y1 = xy[1];
        int x2 = xy[2];
        int y2 = xy[3];
        int x3 = x3y3r[0];
        int y3 = x3y3r[1];
        int r = x3y3r[2];
        long rr = (long) r * r;
        int[] xBound = getX(x1, x2, x3, r);
        int[] yBound = getY(y1, y2, y3, r);
        long counter = 0;
        for (int i = xBound[0]; i <= xBound[1]; i++) {
            for (int j = yBound[0]; j <= yBound[1]; j++) {
                if (dist(i, j, x3, y3) <= rr) {
                    counter++;
                }
//
            }
        }
        return counter;
    }

    private static long getResultFast(int[] xy, int[] x3y3r) {
        int x1 = xy[0];
        int y1 = xy[1];
        int x2 = xy[2];
        int y2 = xy[3];
        int x3 = x3y3r[0];
        int y3 = x3y3r[1];
        int r = x3y3r[2];
        long rr = (long) r * r;
        int[] xBound = getX(x1, x2, x3, r);
        int[] yBound = getY(y1, y2, y3, r);
        long counter = 0;
        for (int i = xBound[0]; i <= xBound[1]; i++) {
            long a = cat(r, Math.abs(i - x3));
            long yup = y3 + a;
            long yd = y3 - a;
            long cnt = Math.min(yup, yBound[1]) - Math.max(yd, yBound[0]);
            if (cnt >=0) {
                counter += cnt + 1;
            }
        }
        return counter;
    }

    private static long dist(int x1, int y1, int x3, int y3) {
        return (long) (x3 - x1) * (x3 - x1) + (long) (y3 - y1) * (y3 - y1);
    }

    private static long cat(long hyp, long cat1) {
        return (long) Math.sqrt(hyp * hyp - cat1 * cat1);
    }

    private static int[] getX(int x1, int x2, int x3, int r) {
        int left = x3 - r;
        int right = x3 + r;
        int min = Math.max(left, Math.min(x1, x2));
        int max = Math.min(right, Math.max(x1, x2));
        return new int[]{min, max};
    }

    private static int[] getY(int y1, int y2, int y3, int r) {
        int down = y3 - r;
        int up = y3 + r;
        int min = Math.max(down, Math.min(y1, y2));
        int max = Math.min(up, Math.max(y1, y2));
        return new int[]{min, max};
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
