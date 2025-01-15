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

public class C {
    static TestHelper fs = new TestHelper();

    static int[] prefx;
    static int[] prefxRev;

    public static void main(String[] args) throws Exception {
        int n = fs.nextInt();
        int[][] points = new int[n][];
        int idx = 0;
        StringBuilder sb = new StringBuilder();
        while (n-- > 0) {
            int[] p = fs.readStringAsIntArray();
            points[idx++] = p;
        }
        int m = fs.nextInt();
        prefx = createPref(points);
        prefxRev = createPrefRev(points);
        while (m-- > 0) {
            int[] traces = fs.readStringAsIntArray();
            if (traces[0] <= traces[1]) {
                sb.append(getResult(prefx, traces[0], traces[1]));
            } else {
                sb.append(
                        getResult(prefxRev, prefxRev.length - (traces[0] - 1), prefxRev.length - (traces[1] - 1)));
            }
            sb.append("\n");
        }
        fs.write(sb.toString());
        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(int[][] points, int[] traces, int out) throws Exception {
        prefx = createPref(points);
        prefxRev = createPrefRev(points);
        if (traces[0] <= traces[1]) {
            assertEquals(out, getResult(prefx, traces[0], traces[1]));
        } else {
            assertEquals(out,
                    getResult(prefxRev, prefxRev.length - (traces[0] - 1), prefxRev.length - (traces[1] - 1)));
        }
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new int[][]{
                                {2, 1},
                                {4, 5},
                                {7, 4},
                                {8, 2},
                                {9, 6},
                                {11, 3},
                                {15, 3},
                        }, new int[]{2, 6},
                        4),
                Arguments.of(new int[][]{
                                {1, 1},
                                {2, 1},
                                {3, 1},
                                {4, 1},
                                {5, 1},
                                {6, 1},
                                {7, 1},
                        }, new int[]{3, 5},
                        0),
                Arguments.of(new int[][]{
                                {1, 1},
                                {2, 1},
                                {3, 1},
                                {4, 2},
                                {5, 1},
                                {6, 1},
                                {7, 1},
                        }, new int[]{2, 4},
                        1),
                Arguments.of(new int[][]{
                                {1, 1},
                                {2, 1},
                                {3, 1},
                                {4, 2},
                                {5, 1},
                                {6, 1},
                                {7, 1},
                        }, new int[]{2, 5},
                        1),
                Arguments.of(new int[][]{
                                {1, 1},
                                {2, 1},
                                {3, 1},
                                {4, 2},
                                {5, 2},
                                {6, 1},
                                {7, 1},
                        }, new int[]{2, 5},
                        1),
                Arguments.of(new int[][]{
                                {2, 1},
                                {4, 5},
                                {7, 4},
                                {8, 2},
                                {9, 7},
                                {11, 3},
                                {15, 3},
                        }, new int[]{1, 7},
                        9),
                Arguments.of(new int[][]{
                                {2, 1},
                                {4, 5},
                                {7, 4},
                                {8, 2},
                                {9, 7},
                                {11, 3},
                                {15, 3},


                        }, new int[]{1, 1},
                        0),
                Arguments.of(new int[][]{
                                {2, 1},
                                {4, 5},
                                {7, 4},
                                {8, 2},
                                {9, 7},
                                {11, 3},
                                {15, 3},


                        }, new int[]{3, 1},
                        1),
                Arguments.of(new int[][]{
                                {1, 1},
                                {3, 2},
                                {5, 6},
                                {7, 2},
                                {10, 4},
                                {11, 1},
                        }, new int[]{5, 6},
                        0),
                Arguments.of(new int[][]{
                                {1, 1},
                                {3, 2},
                                {5, 6},
                                {7, 2},
                                {10, 4},
                                {11, 1},
                        }, new int[]{1, 4},
                        5),
                Arguments.of(new int[][]{
                                {1, 1},
                                {3, 2},
                                {5, 6},
                                {7, 2},
                                {10, 4},
                                {11, 1},
                        }, new int[]{4, 2},
                        4),
                Arguments.of(new int[][]{
                                {1, 1},
                                {3, 3},
                                {5, 1},
                                {7, 3},
                                {10, 1},
                                {11, 4},
                        }, new int[]{1, 5},
                        4),
                Arguments.of(new int[][]{
                                {1, 1},
                                {3, 3},
                                {5, 1},
                                {7, 3},
                                {10, 1},
                                {11, 4},
                        }, new int[]{5, 1},
                        4),
                Arguments.of(new int[][]{
                                {1, 1},
                                {3, 9},
                                {5, 1},
                                {7, 10},
                                {10, 1},
                                {11, 4},
                        }, new int[]{1, 4},
                        17),
                Arguments.of(new int[][]{
                                {1, 1},
                                {3, 4},
                                {5, 2},
                                {7, 6},
                                {10, 1},
                                {11, 4},
                        }, new int[]{1, 4},
                        7),
                Arguments.of(new int[][]{
                                {1, 1},
                                {3, 2},
                                {5, 3},
                                {7, 5},
                                {10, 5},
                                {11, 9},
                        }, new int[]{1, 6},
                        8),
                Arguments.of(new int[][]{
                                {1, 9},
                                {3, 8},
                                {5, 7},
                                {7, 6},
                                {10, 5},
                                {11, 1},
                        }, new int[]{1, 6},
                        0),
                Arguments.of(new int[][]{
                                {1, 9},
                                {3, 8},
                                {5, 7},
                                {7, 6},
                                {10, 5},
                                {11, 1},
                        }, new int[]{6, 1},
                        8)

        );
    }

    private static int[] createPref(int[][] points) {
        int[] pref = new int[points.length];
        pref[0] = 0;
        for (int i = 1; i < points.length; i++) {
            int y = points[i][1];
            if (y - points[i - 1][1] > 0) {
                pref[i] = pref[i - 1] + (y - points[i - 1][1]);
            } else {
                pref[i] = pref[i - 1];
            }
        }
        return pref;
    }

    private static int[] createPrefRev(int[][] points) {
        int[] prefRev = new int[points.length];
        prefRev[0] = 0;
        int idx = 1;
        for (int i = points.length - 2; i >= 0; i--) {
            int y = points[i][1];
            if (y - points[i + 1][1] > 0) {
                prefRev[idx] = prefRev[idx - 1] + (y - points[i + 1][1]);
            } else {
                prefRev[idx] = prefRev[idx - 1];
            }
            idx++;
        }
        return prefRev;
    }

    private static int getResult(int[] pref, int y1, int y2) {
        return pref[y2 - 1] - pref[y1 - 1];
    }

    private static int getResultSlow(int[][] points, int[] traces) {
        int cur = 0;
        if (traces[0] <= traces[1]) {
            int l = traces[0] - 1;
            int r = l;
            while (l <= r && r <= traces[1] - 1) {
                int y1 = points[l][1];
                int y2 = points[r][1];
                if (y2 - y1 > 0) {
                    cur += y2 - y1;
                    l++;
                } else if (y2 - y1 < 0) {
                    l = r;
                } else {
                    l = r;
                }
                r++;
            }
        } else {
            int r = traces[0] - 1;
            int l = r;
            while (r >= l && l >= traces[1] - 1) {
                int y1 = points[r][1];
                int y2 = points[l][1];
                if (y2 - y1 > 0) {
                    cur += y2 - y1;
                    r--;
                } else if (y2 - y1 < 0) {
                    r = l;
                } else {
                    r = l;
                }
                l--;
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
