package org.example.yandex.algorithms_2_0.A.lesson4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class C {
    static TestHelper fs = new TestHelper();
    static long seed = System.currentTimeMillis();
    static Random random = new Random(seed);

    public static void main(String[] args) throws Exception {
        int[] ints = fs.readStringAsIntArray();
        int N = ints[0];
        int K = ints[1];
        String s = fs.next();
        String[] arr = new String[K];
        int i = 0;
        while (i < K) {
            arr[i] = fs.next();
            i++;
        }
        fs.write(getResult(arr, s));
        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(String[] arr, String s, int out) throws Exception {
//        assertEquals(getResult(arr, s), getResultSlow(arr, s));
        assertEquals(out, getResult(arr, s));
    }

    @DisplayName("{0}")
//    @ParameterizedTest
//    @MethodSource(value = "source")
//    @Timeout(1)
    @Test
    void test() throws Exception {
        for (int k = 0; k < 100; k++) {
            String s = generateRandomStr();
            String[] arr = generateRandomArr();
            assertEquals(getResult(arr, s), getResultSlow(arr, s));
        }
//        assertEquals(out, getResultSlow(arr, s));

    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(
                        new String[]{"ab", "ac", "bb"},
                        "abacaba", 7
                ),
                Arguments.of(
                        new String[]{"aa"},
                        "abacaba", 6
                ),
                Arguments.of(
                        new String[]{"aa"},
                        "a", 0
                ),
                Arguments.of(
                        new String[]{"aa"},
                        "aaa", 3
                ),
                Arguments.of(
                        new String[]{"ab"},
                        "a", 0
                )

        );
    }

    private static int getResultSlow(String[] arr, String s) {
        if (s.length() < 2) {
            return 0;
        }
        Map<Map.Entry<Character, Character>, Integer> hash = new HashMap<>();
        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = i + 1; j < s.length(); j++) {
                char first = s.charAt(i);
                char second = s.charAt(j);
                Map.Entry<Character, Character> entry = Map.entry(first, second);
                hash.compute(entry, (k, v) -> v == null ? 1 : v + 1);
            }
        }
        int counter = 0;
        for (String ab : arr) {
            Map.Entry<Character, Character> entry = Map.entry(ab.charAt(0), ab.charAt(ab.length() - 1));
            counter = counter + hash.getOrDefault(entry, 0);
        }
        return counter;
    }

    private static String generateRandomStr() {
        StringJoiner stringJoiner = new StringJoiner("");
        random.ints(1_000, (int) 'a', (int) 'z')
                .forEach(el -> stringJoiner.add(String.valueOf((char) el)));
        return stringJoiner.toString();
    }

    private static String[] generateRandomArr() {
        List<String> collect = IntStream.range(0, 676)
                .mapToObj(idx -> random.ints(2, (int) 'a', (int) 'z')
                        .mapToObj(num -> String.valueOf((char) num))
                        .reduce("", (s1, s2) -> s1 + s2))
                .collect(Collectors.toList());
        return collect.toArray(new String[0]);
    }

    private static long getResult(String[] arr, String s) {
        if (s.length() < 2) {
            return 0;
        }
        Map<Map.Entry<Character, Character>, Long> pairhash = new HashMap<>();
        Set<Character> used = new HashSet<>();
        Map<Character, Long> charHash = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            for (char in : used) {
                Map.Entry<Character, Character> entry = Map.entry(in, c);
                pairhash.compute(entry, (k, v) -> v == null ? charHash.get(in) : v + charHash.get(in));
            }
            charHash.compute(c, (k, v) -> v == null ? 1 : v + 1);
            used.add(c);
        }
        long counter = 0;
        for (String ab : arr) {
            Map.Entry<Character, Character> entry = Map.entry(ab.charAt(0), ab.charAt(ab.length() - 1));
            counter = counter + pairhash.getOrDefault(entry, 0L);
        }
        return counter;
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
                res[i] = generateRandom(col, i);
            }
            return res;
        }
    }
}
