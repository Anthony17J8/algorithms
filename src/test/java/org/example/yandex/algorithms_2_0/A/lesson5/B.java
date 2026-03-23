package org.example.yandex.algorithms_2_0.A.lesson5;

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
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class B {
    static TestHelper fs = new TestHelper();
    static long seed = System.currentTimeMillis();
    static Random random = new Random(seed);

    public static void main(String[] args) throws Exception {
        int K = fs.nextInt();
        int[][] matrix = new int[K][];
        for (int i = 0; i < K; i++) {
            matrix[i] = fs.readStringAsIntArray();
        }
        Result result = getResult(matrix);
        fs.write(result.counter);
        fs.newLine();
        fs.writeAll(result.arr, ' ');
        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(2)
    void test(int[][] in, int count, long[] expectedArr) throws Exception {
        Result result = getResult(in);
        assertEquals(count, result.counter);
        assertArrayEquals(expectedArr, result.arr);
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new int[][]{
                        {2, 2},
                        {3, 2},
                        {2, 1}
                }, 2, new long[]{11, 1}),
                Arguments.of(new int[][]{
                        {1, 7},
                }, 1, new long[]{7}),
                Arguments.of(new int[][]{
                        {10, 1},
                }, 1, new long[]{10}),
                Arguments.of(new int[][]{
                        {1, 1},
                        {2, 1},
                        {3, 1},
                        {4, 1},
                        {5, 2}
                }, 2, new long[]{15, 5}),
                Arguments.of(new int[][]{
                        {1, 3},
                        {2, 1},
                        {5, 1}
                }, 2, new long[]{1, 9})
//                Arguments.of(TestHelper.generateTwoDimensionalRandom(100_000, 2)
//                        , 2, new long[]{0, 0})
        );
    }

    private static Result getResult(int[][] in) {
        List<Integer> input = new ArrayList<>();
        long cnt = 0;
        for (int[] row : in) {
            int A = row[0];
            int N = row[1];
            IntStream.range(0, N)
                    .forEach(el -> input.add(A));
        }
        int left = 0;
        int right = input.size() - 1;

        while (right - left > 1) {
            Integer leftHeap = input.get(left);
            Integer rightHeap = input.get(right);
            int min = Math.min(leftHeap, rightHeap);
            if (min != 0) {
                input.set(left + 1, input.get(left + 1) + min);
                input.set(right - 1, input.get(right - 1) + min);
            }
            if (leftHeap - min == 0) {
                left++;
            } else {
                input.set(left, input.get(left) - min);
            }
            if (rightHeap - min == 0) {
                right--;
            } else {
                input.set(right, input.get(right) - min);
            }

        }
        Integer l = input.get(left);
        Integer r = input.get(right);

        return new Result(left == right ? 1 : 2, left == right ? new long[]{l} : new long[]{l, r});
    }

    static class Result {
        final int counter;
        final long[] arr;

        public Result(int counter, long[] arr) {
            this.counter = counter;
            this.arr = arr;
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

        <T> void writeAll(List<T> l) throws IOException {
            for (T el : l) {
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
                res[i] = new int[]{100, 100_000_000};
            }
            return res;
        }
    }
}
