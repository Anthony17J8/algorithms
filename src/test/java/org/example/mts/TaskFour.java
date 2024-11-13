/*
   Copyright 2020 Russian Post
   <p>
   This source code is Russian Post Confidential Proprietary.
   This software is protected by copyright. All rights and titles are reserved.
   You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
   Otherwise this violation would be treated by law and would be subject to legal prosecution.
   Legal use of the software provides receipt of a license from the right holder only.
 */

package org.example.mts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TaskFour {
    public static void main(String[] args) throws java.lang.Exception {
        FastScanner sc = new FastScanner(); // System.in is a standard input stream
        int stationsTotal = sc.nextInt();
        int current = 0;
        while (stationsTotal > 0) {
            int i1 = sc.nextInt();
            if (current != 0) {
                current = Math.abs(current - i1);
            } else {
                current = i1;
            }
            stationsTotal--;
        }
        System.out.println(String.valueOf(current / 2));
    }

    private static class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
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
    }
}
