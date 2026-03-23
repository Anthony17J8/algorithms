package org.example.yandex.algorithms_2_0.B.lesson5;

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


public class B {
    static TestHelper fs = new TestHelper();
    static long seed = System.currentTimeMillis();
    static Random rand = new Random(seed);

    //3804488619
    public static void main(String[] args) throws Exception {
        int n = fs.nextInt();
        long[] arr = fs.readStringAsLongArray();
//        fs.write(getResult(arr));
//        fs.newLine();
        fs.write(getResult(arr));
        fs.close();
    }




    private static long getResult(long[] arr) {
        long[] prfxsum = new long[arr.length + 1];
        prfxsum[0] = 0;
        long maximum = arr[0];
        for (int i = 0; i < prfxsum.length - 1; i++) {
            prfxsum[i + 1] = arr[i] + prfxsum[i];
            maximum = Math.max(Math.max(maximum, arr[i]), Math.max(maximum, prfxsum[i + 1]));
        }
        int left = 1;
        int right = 1;
        long maxSum = getSum(prfxsum, 0, right);
        long prev = prfxsum[left];
        right++;
        while (right < prfxsum.length) {
            long curr = prfxsum[right];
            maxSum = Math.max(maxSum, getSum(prfxsum, left, right));
            if (prev <= curr) {
//
                right++;
            } else {
                while (left <= right - 1) {
                    left++;

                }
                right++;
                prev = prfxsum[left];
            }
        }
        while (left <= right - 1) {
            maxSum = Math.max(maxSum, getSum(prfxsum, left - 1, right - 1));
            left++;
        }

        return Math.max(maxSum, maximum);
    }


    private static long getResultSlow(long[] arr) {
        long[] prfxsum = new long[arr.length + 1];
        prfxsum[0] = 0;
        long result = arr[0];
        for (int i = 0; i < prfxsum.length - 1; i++) {
            prfxsum[i + 1] = arr[i] + prfxsum[i];

        }

        for (int i = 1; i < prfxsum.length; i++) {
            for (int j = i; j < prfxsum.length; j++) {
                result = Math.max(result, getSum(prfxsum, i - 1, j));
            }
        }
        return result;

    }

    private static long getSum(long[] arr, int left, int right) {
        return arr[right] - arr[left];
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

        public static long[] generateRandomLong(int size) {
            long[] in = new long[size];
            int idx = 0;
            while (idx < size) {
                in[idx++] = rand.nextLong(-100L, 100L);
            }
            return in;
        }

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
