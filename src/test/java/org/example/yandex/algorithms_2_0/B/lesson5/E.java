package org.example.yandex.algorithms_2_0.B.lesson5;

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
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class E {
    static TestHelper fs = new TestHelper();
    static long seed = System.currentTimeMillis();
    static Random rand = new Random(seed);

    public static void main(String[] args) throws Exception {
        int S = fs.nextInt();
        int[] a = fs.readStringAsIntArray();
        int[] b = fs.readStringAsIntArray();
        int[] c = fs.readStringAsIntArray();
//        fs.writeAll(getResultShiftedIdx(a, b, c, S), ' ');
        fs.writeAll(getResultShiftedIdxFast(a, b, c, S), ' ');
        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(int[] a, int[] b, int[] c, int S, int[] out) throws Exception {
        assertArrayEquals(out, getResult(a, b, c, S));
    }

    private static int[] getResult(int[] a, int[] b, int[] c, int S) {
        int aidx = 0;
        int bidx = 0;
        int cidx = 0;
        boolean resultFound = false;
        int[] res = new int[3];
        while (true) {
            while (aidx < a.length && S - a[aidx] < 2) {
                aidx++;
            }
            if (aidx == a.length) {
                break;
            }
            int aEl = a[aidx];
            while (bidx < b.length && S - aEl - b[bidx] < 1) {
                bidx++;
            }
            if (bidx == b.length) {
                aidx++;
                bidx = 0;
                continue;
            }
            int bEl = b[bidx];
            while (cidx < c.length && S - aEl - bEl - c[cidx] != 0) {
                cidx++;
            }
            if (cidx == c.length) {
                bidx++;
                cidx = 0;
                continue;
            }
            int cEl = c[cidx];
            if (aEl + bEl + cEl == S) {
                resultFound = true;
                res[0] = aidx;
                res[1] = bidx;
                res[2] = cidx;
                break;
            } else if (aidx <= a.length - 1) {
                aidx++;
                bidx = 0;
                cidx = 0;
            } else {
                break;
            }
        }
        return resultFound ? res : new int[]{-1};
    }

    private static int[] getResultShiftedIdx(int[] a, int[] b, int[] c, int S) {
        int aidx = 1;
        int bidx = 1;
        int cidx = 1;
        boolean resultFound = false;
        int[] res = new int[3];
        while (true) {
            while (aidx < a.length && S - a[aidx] < 2) {
                aidx++;
            }
            if (aidx == a.length) {
                break;
            }
            int aEl = a[aidx];
            while (bidx < b.length && S - aEl - b[bidx] < 1) {
                bidx++;
            }
            if (bidx == b.length) {
                aidx++;
                bidx = 1;
                continue;
            }
            int bEl = b[bidx];
            while (cidx < c.length && S - aEl - bEl - c[cidx] != 0) {
                cidx++;
            }
            if (cidx == c.length) {
                bidx++;
                cidx = 1;
                continue;
            }
            int cEl = c[cidx];
            if (aEl + bEl + cEl == S) {
                resultFound = true;
                res[0] = aidx - 1;
                res[1] = bidx - 1;
                res[2] = cidx - 1;
                break;
            } else if (aidx <= a.length - 1) {
                aidx++;
                bidx = 1;
                cidx = 1;
            } else {
                break;
            }
        }
        return resultFound ? res : new int[]{-1};
    }

    private static int[] getResultShiftedIdxFast(int[] a, int[] b, int[] c, int S) {
        Map<Integer, Integer> cCache = new HashMap<>();
        for (int i = 1; i < c.length; i++) {
            int cEl = c[i];
            cCache.putIfAbsent(cEl, i);
        }
        boolean resultFound = false;
        int[] res = new int[3];
        for (int i = 1; i < a.length; i++) {
            int aEl = a[i];
            if (aEl + 2 > S) {
                continue;
            }
            for (int j = 1; j < b.length; j++) {
                int bEl = b[j];
                int cand = S - bEl - aEl;
                if (cCache.containsKey(cand)) {
                    resultFound = true;
                    res[0] = i-1;
                    res[1] = j-1;
                    res[2] = cCache.get(cand)-1;
                    break;
                }
            }
            if (resultFound) break;
        }
        return resultFound ? res : new int[]{-1};
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(
                        new int[]{1, 2, 3, 4},
                        new int[]{5, 2, 1},
                        new int[]{5, 3, 2, 2},
                        5,
                        new int[]{0, 1, 2}
                ),
                Arguments.of(
                        new int[]{1, 2},
                        new int[]{3, 1},
                        new int[]{3, 1},
                        3,
                        new int[]{0, 1, 1}
                ),
                Arguments.of(
                        new int[]{5},
                        new int[]{4},
                        new int[]{3},
                        10,
                        new int[]{-1}
                ),
                Arguments.of(
                        new int[]{5, 2, 1},
                        new int[]{5, 3},
                        new int[]{1},
                        7,
                        new int[]{2, 0, 0}
                )
        );
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
            return Files.lines(Path.of("src/test/java/org/example/yandex/algorithms_2_0/B/lesson4/1.txt"));
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

        <T> void writeAll(Iterable<T> l) throws IOException {
            for (T el : l) {
                out.write(el + " ");
            }
        }

        <T> void writeAll(Iterable<T> l, char sep) throws IOException {
            for (T el : l) {
                out.write(el + "" + sep);
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

        void writeOneByOne(long[][] in) throws IOException {
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

//        public static long[] generateRandomLong(int size) {
//            long[] in = new long[size];
//            int idx = 0;
//            while (idx < size) {
//                in[idx++] = rand.nextLong(-100L, 100L);
//            }
//            return in;
//        }

        public static int[] range(int from, int to) {
            return IntStream.range(from, to).toArray();
        }

        public static int[] generateRandomSorted(int size, int seed) {
            int[] array = rand.ints(1, 20).limit(size).toArray();
            Arrays.sort(array);
            return array;
        }

        public static int[] generateRandom(int size, int seed) {
            return rand.ints(1, 10).limit(size).toArray();
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
