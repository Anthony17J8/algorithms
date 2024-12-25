/*
   Copyright 2020 Russian Post
   <p>
   This source code is Russian Post Confidential Proprietary.
   This software is protected by copyright. All rights and titles are reserved.
   You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
   Otherwise this violation would be treated by law and would be subject to legal prosecution.
   Legal use of the software provides receipt of a license from the right holder only.
 */

package org.example.yandex.algorithms_1_0.lesson4;

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
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class H {
    static TestHelper fs = new TestHelper();

    public static void main(String[] args) throws Exception {
        fs.readStringAsIntArray();

        String in = fs.next();
        String txt = fs.next();
        fs.write(getResultSlow(in, txt));
        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(String g, String s, int out) throws Exception {
        assertEquals(out, getResultFast(g, s));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("cAda", "AbrAcadAbRa", 2),
                Arguments.of("Adac", "AbrAcadAbRa", 2),
                Arguments.of("AdacA", "AbrAcadAbRa", 1),
                Arguments.of("AdacA", "AbrAcadAAbRa", 2),
                Arguments.of("AdacA", "AbrAcadAAAbRa", 2),
                Arguments.of("aaaa", "aaaaaaa", 4),
                Arguments.of("aaaa", "vaavv", 0),
                Arguments.of("abc", "cba", 1),
                Arguments.of("abc", "cbacbAbc", 3),
                Arguments.of("MexIcoMe",
                        "MexIcoMexIcoMezFILbMexIcoMezjMexIcoMezSMexIcoMezgMexIcoMezMexIcoMezMexIcoMezmAMexIcoMezMexIcoMezMexIcoMezufygMexIcoMexIcoMezmoIzFuRMexIcoMezMexIcoMezMexIcoMezYMexIcoMezMexIcoMezHtMexIcoMexIcoMezMexIcoMexIcoMez",
                        22),
                Arguments.of("MexIcoMe", "MexIcoMexIcoMe", 2),
                Arguments.of("MexMe", "MexMexMeMexMe", 7),
                Arguments.of(TestHelper.generate(3000), TestHelper.generate(3_000_000), 0)

        );
    }

    private static int getResultFast(String w, String s) {
        int result = 0;
        Map<Character, Integer> mapW = getDict(w);
        int l = 0;
        int r = w.length() - 1;
        Map<Character, Integer> mapS = getDict(s.substring(l, r));

        while (r < s.length()) {
            if (mapS.computeIfPresent(s.charAt(r), (k, v) -> v + 1) == null) {
                mapS.put(s.charAt(r), 1);
            }
            if (isWord(mapW, mapS)) {
                result++;
            }

            int newVal = mapS.computeIfPresent(s.charAt(l), (k, v) -> v - 1);
            if (newVal == 0) {
                mapS.remove(s.charAt(l));
            }
            l++;
            r++;
        }
        return result;
    }

    private static boolean isWord(Map<Character, Integer> mapW, Map<Character, Integer> mapS) {
        boolean result = true;
        for (Map.Entry<Character, Integer> e : mapW.entrySet()) {
            if (!e.getValue().equals(mapS.get(e.getKey()))) {
                result = false;
                break;
            }
        }
        return result;
    }

    private static int getResultSlow(String g, String s) {
        Map<Character, Integer> cache = new HashMap<>();
        fillCache(g, cache);
        int l = 0, r = 0;
        int result = 0;

        char[] chars = s.toCharArray();
        while (r < s.length()) {
            if (cache.containsKey(chars[r])) {
                int newVal = cache.compute(chars[r], (k, v) -> v - 1);
                if (newVal == 0) {
                    cache.remove(chars[r]);
                }
                r++;
            } else {
                fillCache(g, cache);
                l++;
                r = l;
            }
            if (cache.isEmpty()) {
                result++;
                l++;
                r = l;
                fillCache(g, cache);
            }
        }

        return result;
    }

    private static void fillCache(String in, Map<Character, Integer> cache) {
        if (in.length() != cache.size()) {
            cache.clear();
            for (char c : in.toCharArray()) {
                if (cache.computeIfPresent(c, (k, v) -> v + 1) == null) {
                    cache.put(c, 1);
                }
            }
        }
    }

    private static Map<Character, Integer> getDict(String in) {
        Map<Character, Integer> m = new HashMap<>();
        for (char c : in.toCharArray()) {
            if (m.computeIfPresent(c, (k, v) -> v + 1) == null) {
                m.put(c, 1);
            }
        }
        return m;
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
