/*
   Copyright 2020 Russian Post
   <p>
   This source code is Russian Post Confidential Proprietary.
   This software is protected by copyright. All rights and titles are reserved.
   You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
   Otherwise this violation would be treated by law and would be subject to legal prosecution.
   Legal use of the software provides receipt of a license from the right holder only.
 */

package org.example.yandex.algorithms_6_0;


import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Plot {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int x1 = Integer.parseInt(reader.readLine());
        int y1 = Integer.parseInt(reader.readLine());
        int x2 = Integer.parseInt(reader.readLine());
        int y2 = Integer.parseInt(reader.readLine());
        int x = Integer.parseInt(reader.readLine());
        int y = Integer.parseInt(reader.readLine());
        writer.write(String.valueOf(getResult(x1, y1, x2, y2, x, y)));
        writer.flush();
        reader.close();
        writer.close();
    }

    @Test
    void test() {
        assertEquals("NW", getResult(-1, -2, 5, 3, -4, 6));
        assertEquals("SW", getResult(0, 0, 2, 2, -1, -1));
        assertEquals("N", getResult(-1, 0, 5, 2, 3, 3));
        assertEquals("S", getResult(-1, 0, 5, 2, 3, -3));
        assertEquals("E", getResult(-10, 5, 3, 7, 11, 6));
        assertEquals("W", getResult(-5, -3, 5, 2, -10, -2));
        assertEquals("NE", getResult(-99, 90, 98, 97, 99, 99));
    }

    private static String getResult(int x1, int y1, int x2, int y2, int x, int y) {
        if ((y > y2 && x > x2) || (y > y2 && x < x1) || (y < y1 && x < x1) || (y < y1 && x > x2)) {
            return calcY(y1, y2, y) + calcX(x1, x2, x);
        } else {
            if (x > x2) {
                return "E";
            } else if (x < x1) {
                return "W";
            } else if (y > y2) {
                return "N";
            } else {
                return "S";
            }
        }
    }

    private static String calcX(int x1, int x2, int x) {
        if (Math.sqrt(Math.pow(x - x1, 2)) > Math.sqrt(Math.pow(x - x2, 2))) {
            return "E";
        } else {
            return "W";
        }
    }

    private static String calcY(int y1, int y2, int y) {
        if (Math.sqrt(Math.pow(y - y1, 2)) > Math.sqrt(Math.pow(y - y2, 2))) {
            return "N";
        } else {
            return "S";
        }
    }
}

