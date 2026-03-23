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
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class C {
    static TestHelper fs = new TestHelper();
    static long seed = System.currentTimeMillis();
    static Random rand = new Random(seed);

    public static void main(String[] args) throws Exception {
        int[] NM = fs.readStringAsIntArray();
        int n = NM[0];
        int m = NM[1];
        int[] X = fs.readStringAsIntArray();
        int[] Y = fs.readStringAsIntArray();
        Result result = getResult(X, Y);
        fs.write(result.P);
        fs.newLine();
        for (int i = 0; i < result.distrib.length; i++) {
            fs.write(result.distrib[i] + (i == result.distrib.length - 1 ? "" : " "));
        }

        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(int[] X, int[] Y, Result res) throws Exception {
        Result result = getResult(X, Y);
        assertEquals(res.P, result.P);
        assertArrayEquals(res.distrib, result.distrib);

    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new int[]{1}, new int[]{2}, new Result(1, new int[]{1})),
                Arguments.of(new int[]{1}, new int[]{1}, new Result(0, new int[]{0})),
                Arguments.of(new int[]{1, 2, 3, 4}, new int[]{5, 4, 3, 2}, new Result(4, new int[]{4, 3, 2, 1})),
                Arguments.of(new int[]{1, 2, 3}, new int[]{2, 5, 2}, new Result(2, new int[]{1, 2, 0})),
                Arguments.of(new int[]{1, 2}, new int[]{2, 3}, new Result(2, new int[]{1, 2})),
                Arguments.of(new int[]{2, 2}, new int[]{6, 5}, new Result(2, new int[]{2, 1}))
        );
    }


    private static Result getResult(int[] X, int[] Y) {
        int[] distrib = new int[X.length];
        int counter = 0;
        Set<Integer> used = new HashSet<>();
        for (int i = 0; i < X.length; i++) {
            int users = X[i];
            int best = -1;
            int diff = Integer.MAX_VALUE;
            for (int j = 0; j < Y.length; j++) {
                int computers = Y[j];
                if (!used.contains(j) && computers - users >= 1 && computers - users < diff) {
                    diff = computers - users;
                    best = j;
                }
            }
            if (best == -1) {
                distrib[i] = 0;
            } else {
                used.add(best);
                counter++;
                distrib[i] = best + 1;
            }
        }
        return new Result(counter, distrib);
    }

    static class Result {
        int P;
        int[] distrib;

        public Result(int p, int[] distrib) {
            P = p;
            this.distrib = distrib;
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
