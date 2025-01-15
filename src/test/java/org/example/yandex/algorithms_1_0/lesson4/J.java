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
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class J {
    static TestHelper fs = new TestHelper();
    static String C;
    static String D;
    static String yes = "yes", no = "no";
    static Integer max;
    static String result;

    public static void main(String[] args) throws Exception {

        List<String> input = fs.readFromFile().collect(Collectors.toList());
        String[] s = input.get(0).split(" ");
        int n = Integer.parseInt(s[0]); // keywords, 0<=n<=50
        // если yes - идентификаторы и ключевые слова регистрозависимые
        C = s[1];
        //если yes - идентификаторы могут начинаться с цифры
        D = s[2];

        Set<String> keywords = new HashSet<>();
        int idx = 1;
        while (n-- > 0) {
            String s1 = input.get(idx++);
            keywords.add(C.equals(no) ? s1.toLowerCase() : s1);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = idx; i < input.size(); i++) {
            sb.append(input.get(i))
                    .append("\n");
        }

        fs.write(getResult(keywords, sb.toString()));
        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(String c, String d, Set<String> set, String txt, String out) throws Exception {
        C = c;
        D = d;
        max = null;
        result = null;
        assertEquals(out, getResult(set, txt));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(yes, no, Set.of(), """
                        int main() {
                          int a;
                          int b;
                          scanf("%d%d", &a, &b);
                          printf("%d", a + b);
                        }
                        """, "int"),
                Arguments.of(yes, no, Set.of(), """
                        #define INT int
                         int main() {
                           INT a, b;
                           scanf("%d%d", &a, &b);
                           printf("%d %d", a + b, 0);
                         }
                        """, "d"),
                Arguments.of(no, no, Set.of("program", "var", "begin", "end", "while", "for"), """
                        program sum;
                         var
                           A, B: integer;
                         begin
                           read(A, b);
                           writeln(a + b);
                         end.
                        """, "a"),
                Arguments.of(yes, yes, Set.of("_"), """
                         a = 0h
                         b = 0h
                         c = 0h
                        """, "0h"),
                Arguments.of(no, yes, Set.of("a", "0h", "b"), """
                         a = 0h
                         b = 0h
                         c = 0h
                        """, "c"),
                Arguments.of(yes, no, Set.of("a"), """
                        A b a a a
                        b b b B a
                        A A A
                        """, "A"),
                Arguments.of(yes, no, Set.of("a", "A"), """
                        A b a a a
                        b b b B a
                        A A A
                        """, "b"),
                Arguments.of(no, no, Set.of("a"), """
                        A b a a a
                        b b b B a
                        A A A
                        """, "b"),
                Arguments.of(no, no, Set.of("a"), """
                        A b a a a
                        b b b B a
                        A A A
                        """, "b")

        );
    }

    private static String getResult(Set<String> keywords, String txt) {
        Map<String, Integer> ids = new LinkedHashMap<>();
        asWordsStream(txt)
                .filter(i -> isIdentifier(i))
                .map(i -> {
                    if (C.equals(no)) {
                        return i.toLowerCase();
                    } else {
                        return i;
                    }
                })
                .filter(i -> !keywords.contains(i))
                .forEach(i -> {
                    if (ids.computeIfPresent(i, (k, v) -> {
                        v = v + 1;
                        if (max < v) {
                            max = v;
                        }
                        return v;
                    }) == null) {
                        if (max == null) max = 1;
                        ids.put(i, 1);
                    }
                });

        for (Map.Entry<String, Integer> e : ids.entrySet()) {
            if (e.getValue().equals(max)) {
                result = e.getKey();
                break;
            } else {
                result = "";
            }
        }
        return result;
    }

    private static boolean isIdentifier(String in) {
        try {
            Long.parseLong(in);
            return false;
        } catch (NumberFormatException exc) {
            if (D.equals(no) && Character.isDigit(in.charAt(0))){
                return false;
            }
            return true;
        }
    }

    private static Stream<String> asWordsStream(String txt) {
        return Arrays.stream(txt.split("[^\\p{Lower}\\p{Upper}_\\p{Digit}]"))
                .filter(s -> !s.isEmpty());
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
