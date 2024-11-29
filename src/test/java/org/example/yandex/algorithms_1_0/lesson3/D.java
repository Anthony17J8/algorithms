/*
   Copyright 2020 Russian Post
   <p>
   This source code is Russian Post Confidential Proprietary.
   This software is protected by copyright. All rights and titles are reserved.
   You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
   Otherwise this violation would be treated by law and would be subject to legal prosecution.
   Legal use of the software provides receipt of a license from the right holder only.
 */

package org.example.yandex.algorithms_1_0.lesson3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class D {

    public static void main(String[] args) throws Exception {
        TestHelper fs = new TestHelper();

        String collect = fs.readFromFile()
                .collect(Collectors.joining("\n"));
        fs.write(getResult(collect));
        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(String text, int out) throws Exception {
        assertEquals(out, getResult(text));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of("She sells sea shells on the sea shore;\n" +
                        "The shells that she sells are sea shells I'm sure.\n" +
                        "So if she sells sea shells on the sea shore,\n" +
                        "I'm sure that the shells are sea shore shells.\n" +
                        "\n", 19),
                Arguments.of("She      sells sea shells on the sea shore;\n" +
                        "The shells that she sells are sea      shells I'm sure.\n" +
                        "So if she sells sea shells on the     sea shore,\n" +
                        "I'm sure that the shells are sea shore shells.\n" +
                        "\n", 19),
                Arguments.of("   a\n" +
                        "a aa a\n" +
                        "a", 2),
                Arguments.of("b   a\n" +
                        "a aa a\n" +
                        "a", 3)


        );
    }

    private static int getResult(String text) {
        int c = Character.codePointAt(new char[]{' '}, 0);

        return Arrays.stream(text.split("[\u0020]+|[\n]+"))
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toSet())
                .size();
    }

    private static class TestHelper {
        BufferedReader in;
        BufferedWriter out;

        TestHelper() throws FileNotFoundException {
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        Stream<String> readFromFile() throws IOException {
            return Files.lines(Path.of("input.txt"));
        }

        void write(String s) throws IOException {
            out.write(s);
        }

        void write(long i) throws IOException {
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

        void writeAll(String[] w) throws IOException {
            for (String b : w) {
                out.write(b + " ");
            }
        }

        void writeOneByOne(char[][] in) throws IOException {
            for (int i = 0; i < in.length; i++) {
                for (int j = 0; j < in[0].length; j++) {
                    out.write(in[i][j] + " ");
                }
                out.write("\n");
            }
        }

        String next() {
            try {
                return in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        int[] readStringAsArray() {
            try {
                return Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
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
            for (int i = 0; i < size; i++) {
                in[i] = num;
            }
            return in;
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
