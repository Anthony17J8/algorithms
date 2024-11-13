/*
   Copyright 2020 Russian Post
   <p>
   This source code is Russian Post Confidential Proprietary.
   This software is protected by copyright. All rights and titles are reserved.
   You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
   Otherwise this violation would be treated by law and would be subject to legal prosecution.
   Legal use of the software provides receipt of a license from the right holder only.
 */

package org.example.yandex.algorithms_1_0.lesson2;

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
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class B {
    static String constant = "CONSTANT", asc = "ASCENDING", weakly_asc = "WEAKLY ASCENDING", desc = "DESCENDING", weakly_desc = "WEAKLY DESCENDING", rand = "RANDOM";

    private static int FINISH = -2 * (int) Math.pow(10, 9);

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        List<Integer> in = new ArrayList<>();
        int i;
        while ((i = fs.nextInt()) != FINISH) {
            in.add(i);
        }
        fs.write(getResult(in.stream()
                .mapToInt(Integer::intValue).toArray()));
        fs.close();
    }

    @DisplayName("{0}")
    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(1)
    void test(int[] in, String out) {
        assertEquals(out, getResult(in));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new int[]{1, 1, 1, 1, 1, 2}, weakly_asc),
                Arguments.of(new int[]{0, 1, 2, 3}, asc),
                Arguments.of(new int[]{0, 0, 0, 0}, constant),
                Arguments.of(new int[]{1, 3, 5, 2}, rand),
                Arguments.of(new int[]{1, 3, 3, 5, 6}, weakly_asc),
                Arguments.of(new int[]{1, 3, 3, 5, 6, 3, 3}, rand),
                Arguments.of(new int[]{2, -1, -2, -3}, desc),
                Arguments.of(new int[]{2, -1, -1, -10}, weakly_desc),
                Arguments.of(new int[]{3, 1, 1, 1, 1}, weakly_desc),
                Arguments.of(new int[]{3, 3, 3, 3, 3, 1}, weakly_desc),
                Arguments.of(new int[]{1, 3, 3, 3, 3}, weakly_asc)
        );
    }

    private static String getResult(int[] in) {
        String result = "";
        int prev = in[0];

        for (int i = 1; i < in.length; i++) {
            if (result.isEmpty()) {
                if (prev > in[i]) {
                    result = desc;
                }
                if (prev < in[i]) {
                    result = asc;
                }
                if (prev == in[i]) {
                    result = constant;
                }
            } else {
                if (prev > in[i]) {
                    if (result.equals(asc) || result.equals(weakly_asc)) {
                        result = rand;
                    }
                    if (result.equals(constant)) {
                        result = weakly_desc;
                    }
                }
                if (prev < in[i]) {
                    if (result.equals(desc) || result.equals(weakly_desc)) {
                        result = rand;
                    }
                    if (result.equals(constant)) {
                        result = weakly_asc;
                    }
                }
                if (prev == in[i] && result.equals(asc)) {
                    result = weakly_asc;
                }
                if (prev == in[i] && result.equals(desc)) {
                    result = weakly_desc;
                }

            }
            prev = in[i];
        }
        return result;
    }

    private static class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;
        BufferedWriter out;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        void write(String s) throws IOException {
            out.write(s);
        }

        String next() {
            while (!tok.hasMoreElements()) {
                try {
                    tok = new StringTokenizer(in.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tok.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        void close() throws Exception {
            out.flush();
            in.close();
            out.close();
        }
    }
}
