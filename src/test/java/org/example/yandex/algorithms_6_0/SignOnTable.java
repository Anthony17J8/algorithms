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

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignOnTable {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(reader.readLine());
        char[][] table = new char[n][n];
        int i = 0;
        while (i < n) {
            String s = reader.readLine();
            table[i] = s.toCharArray();
            i++;
        }

        writer.write(String.valueOf(getResult(table)));
        writer.flush();
        reader.close();
        writer.close();
    }

    @ParameterizedTest
    @MethodSource(value = "source")
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    void test(int testNum, char[][] table, String expected) {
        assertEquals(expected, getResult(table));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(1, new char[][]{
                        {'#'}
                }, "I"),
                Arguments.of(2, new char[][]{
                        {'.'}
                }, "X"),
                Arguments.of(3, new char[][]{
                        {'.', '#', '#', '.'},
                        {'.', '#', '#', '.'},
                        {'.', '#', '#', '.'},
                        {'.', '.', '.', '.'},
                }, "I"),
                Arguments.of(4, new char[][]{
                        {'#', '.', '.', '.', '#'},
                        {'.', '#', '.', '#', '.'},
                        {'.', '.', '#', '.', '.'},
                        {'.', '#', '.', '#', '.'},
                        {'#', '.', '.', '.', '#'},

                }, "X"),
                Arguments.of(5, new char[][]{
                        {'#', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                }, "I"),
                Arguments.of(6, new char[][]{
                        {'#', '.', '.', '.', '.'},
                        {'#', '.', '.', '.', '.'},
                        {'#', '.', '.', '.', '.'},
                        {'#', '.', '.', '.', '.'},
                        {'#', '.', '.', '.', '.'},
                }, "I"),
                Arguments.of(7, new char[][]{
                        {'#', '#', '#', '#', '#'},
                        {'#', '#', '#', '#', '#'},
                        {'#', '#', '#', '#', '#'},
                        {'#', '#', '#', '#', '#'},
                        {'#', '#', '#', '#', '#'},
                }, "I"),
                Arguments.of(8, new char[][]{
                        {'.', '#', '#', '#', '.'},
                        {'.', '.', ',', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                }, "I"),
                Arguments.of(9, new char[][]{
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', ',', '.', '.'},
                        {'.', '.', '#', '#', '.'},
                        {'.', '.', '#', '#', '.'},
                        {'.', '.', '.', '.', '.'},
                }, "I"),
                Arguments.of(10, new char[][]{
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '#'},
                        {'.', '.', '.', '.', '#'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '#'},
                }, "X"),
                Arguments.of(11, new char[][]{
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '#'},
                        {'.', '.', '.', '.', '#'},
                        {'.', '.', '.', '.', '#'},
                }, "I"),
                Arguments.of(12, new char[][]{
                        {'.', '.', '.', '.', '#'},
                        {'.', '.', '.', '.', '#'},
                        {'.', '.', '.', '.', '#'},
                        {'.', '.', '.', '.', '#'},
                        {'.', '.', '.', '.', '#'},
                }, "I"),
                Arguments.of(13, new char[][]{
                        {'.', '.', '.', '.', '#'},
                        {'.', '.', '.', '.', '#'},
                        {'.', '.', '.', '.', '#'},
                        {'.', '.', '.', '.', '#'},
                        {'#', '#', '#', '#', '#'},
                }, "X"),
                Arguments.of(14, new char[][]{
                        {'#', '#', '#', '#', '#'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                }, "I"),
                Arguments.of(15, new char[][]{
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'#', '.', '.', '.', '.'},
                }, "I"),
                Arguments.of(16, new char[][]{
                        {'.', '.', '.', '.', '#'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                }, "I"),
                Arguments.of(17, new char[][]{
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '#'},
                }, "I"),
                Arguments.of(18, new char[][]{
                        {'.', '#', '.', '#', '#'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                }, "X"),
                Arguments.of(19, new char[][]{
                        {'.', '.', '.', '#', '#'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'#', '.', '.', '.', '.'},
                }, "X"),
                Arguments.of(20, new char[][]{
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'#', '#', '#', '#', '#'},
                }, "I"),
                Arguments.of(21, new char[][]{
                        {'.', '.', '.', '.', '.'},
                        {'.', '#', '#', '#', '#'},
                        {'.', '#', '.', '.', '#'},
                        {'.', '#', '.', '.', '#'},
                        {'.', '#', '#', '#', '#'},
                }, "O"),
                Arguments.of(22, new char[][]{
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                }, "X"),
                Arguments.of(23, new char[][]{
                        {'#', '#', '#', '.', '.'},
                        {'#', '.', '#', '.', '.'},
                        {'#', '#', '#', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                }, "O"),
                Arguments.of(24, new char[][]{
                        {'#', '#', '.', '.', '.'},
                        {'#', '#', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.'},
                }, "I"),
                Arguments.of(25, new char[][]{
                        {'.', '.', '.', '.', '.'},
                        {'.', '#', '#', '.', '.'},
                        {'.', '#', '.', '.', '.'},
                        {'.', '#', '.', '.', '.'},
                        {'.', '#', '#', '.', '.'},
                }, "C"),
                Arguments.of(26, new char[][]{
                        {'.', '.', '.', '.', '.'},
                        {'.', '#', '#', '.', '.'},
                        {'.', '#', '.', '.', '.'},
                        {'.', '#', '#', '.', '.'},
                        {'.', '#', '#', '.', '.'},
                }, "C"),
                Arguments.of(27, new char[][]{
                        {'.', '.', '.', '.', '.'},
                        {'.', '#', '#', '.', '.'},
                        {'.', '.', '#', '.', '.'},
                        {'.', '#', '#', '.', '.'},
                        {'.', '#', '#', '.', '.'},
                }, "X"),
                Arguments.of(28, new char[][]{
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '#', '#'},
                        {'.', '.', '.', '#', '.'},
                        {'.', '.', '.', '#', '.'},
                        {'.', '.', '.', '#', '#'},
                }, "C"),
                Arguments.of(29, new char[][]{
                        {'.', '.', '.', '.', '.'},
                        {'.', '.', '.', '#', '#'},
                        {'.', '.', '.', '#', '.'},
                        {'.', '.', '.', '#', '.'},
                        {'.', '.', '.', '#', '.'},
                }, "X"),
                Arguments.of(30, new char[][]{
                        {'.', '.', '.', '#', '.'},
                        {'.', '.', '.', '#', '.'},
                        {'.', '.', '.', '#', '.'},
                        {'.', '.', '.', '#', '#'},
                        {'.', '.', '.', '#', '#'},
                }, "L"),
                Arguments.of(31, new char[][]{
                        {'.', '#', '.', '.', '.'},
                        {'.', '#', '.', '.', '.'},
                        {',', '#', '.', '.', '.'},
                        {'#', '#', '.', '.', '.'},
                        {'#', '#', '#', '.', '.'},
                }, "X"),
                Arguments.of(32, new char[][]{
                        {'.', '.', '.', '.', '.'},
                        {'.', '#', '.', '.', '.'},
                        {'.', '#', '.', '.', '.'},
                        {'.', '#', '.', '.', '.'},
                        {'.', '#', '#', '.', '.'},
                }, "L"),
                Arguments.of(33, new char[][]{
                        {'.', '.', '.', '.', '.'},
                        {'.', '#', '.', '.', '.'},
                        {'.', '#', '.', '.', '.'},
                        {'.', '#', '.', '.', '.'},
                        {'.', '#', '#', '#', '#'},
                }, "L"),
                Arguments.of(34, new char[][]{
                        {'.', '#', '#', '#', '#', '.'},
                        {'.', '#', '.', '.', '#', '.'},
                        {'.', '#', '.', '.', '#', '.'},
                        {'.', '#', '.', '.', '.', '.'},
                        {'.', '#', '.', '.', '.', '.'},
                        {'.', '#', '#', '#', '#', '.'},
                }, "X"),
                Arguments.of(35, new char[][]{
                        {'.', '#', '.', '.', '#', '.'},
                        {'.', '#', '#', '#', '#', '.'},
                        {'.', '#', '.', '.', '#', '.'},
                        {'.', '#', '.', '.', '#', '.'},
                        {'.', '#', '.', '.', '#', '.'},
                        {'.', '#', '.', '.', '#', '.'},
                }, "H"),
                Arguments.of(36, new char[][]{
                        {'.', '#', '.', '.', '#', '.'},
                        {'.', '#', '#', '#', '#', '.'},
                        {'.', '#', '.', '.', '#', '.'},
                        {'.', '#', '.', '.', '#', '.'},
                        {'.', '#', '.', '.', '#', '.'},
                        {'.', '#', '#', '#', '#', '.'},
                }, "X"),
                Arguments.of(37, new char[][]{
                        {'.', '#', '.', '.', '#', '.'},
                        {'.', '#', '#', '#', '#', '.'},
                        {'.', '#', '.', '.', '#', '.'},
                        {'.', '#', '.', '.', '#', '.'},
                        {'.', '#', '.', '#', '#', '.'},
                        {'.', '#', '.', '.', '#', '.'},
                }, "X"),
                Arguments.of(38, new char[][]{
                        {'.', '#', '.', '.', '#', '.'},
                        {'.', '#', '#', '#', '#', '.'},
                        {'.', '#', '.', '.', '#', '.'},
                        {'.', '#', '.', '.', '.', '.'},
                        {'.', '#', '.', '.', '.', '.'},
                        {'.', '#', '.', '.', '#', '.'},
                }, "X"),
                Arguments.of(39, new char[][]{
                        {'.', '#', '.', '.', '#', '.'},
                        {'.', '#', '#', '#', '#', '#'},
                        {'.', '#', '.', '.', '#', '.'},
                        {'.', '#', '.', '.', '#', '.'},
                        {'.', '#', '.', '.', '#', '.'},
                        {'.', '#', '.', '.', '#', '.'},
                }, "X"),
                Arguments.of(38, new char[][]{
                        {'.', '#', '.', '.', '#', '.'},
                        {'.', '#', '#', '#', '#', '.'},
                        {'.', '.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.', '.'},
                        {'.', '.', '.', '.', '.', '.'},
                }, "X")
        );
    }

    private static String getResult(char[][] table) {
        if (table.length == 1) {
            return (table[0][0] == '#') ? "I" : "X";
        }
        Rectangle rec = getOnlyOneRectangle(table, '#', '.', new StartingPoint(0, 0, 0, 0));
        if (rec == null) {
            System.out.println("not found");
        } else {
            System.out.println("found");
            boolean isOnly = noOtherRectangles(rec.original);
            if (isOnly) {
                rec.analyzeRectangle();
                if (rec.innerRectangles.isEmpty()) {
                    return "I";
                }
                if (rec.isO()) {
                    return "O";
                }
                if (rec.isC()) {
                    return "C";
                }
                if (rec.isL()) {
                    return "L";
                }
                if (rec.isH()) {
                    return "H";
                }
                if (rec.isP()) {
                    return "P";
                }
            } else {
                return "X";
            }
        }
        return "X";
    }

    private static boolean isL(char[][] out) {
        boolean result = true;
        for (int i = 0; i < out.length; i++) {
            if ((i == 0 && out[i][out[i].length - 1] != '#')) {
                result = false;
                break;
            }
        }
        return result;
    }

    private static boolean isP(char[][] out) {
        return false;
    }

    private static boolean isH(char[][] out) {
        return false;
    }

    private static boolean isC(char[][] out) {
        boolean result = true;
        for (int i = 0; i < out.length; i++) {
            if ((i == 0 && out[i][out[i].length - 1] != '#') || (i == out.length - 1 && out[i][out[i].length - 1] != '#')) {
                result = false;
                break;
            }
        }
        return result;
    }

    private static boolean isO(char[][] out) {
        boolean result = true;
        for (int i = 0; i < out.length; i++) {
            if (out[0][i] != '#' || out[i][0] != '#' || out[out[i].length - 1][i] != '#' || out[i][out[i].length - 1] != '#') {
                result = false;
                break;
            }
        }
        return result;
    }

    private static boolean isFullFilled(char[][] table, char symbol) {
        boolean result = true;
        for (char[] chars : table) {
            if (!result) {
                break;
            }
            for (char aChar : chars) {
                if (aChar != symbol) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    private static boolean noOtherRectangles(char[][] table) {
        boolean result = true;
        for (char[] chars : table) {
            if (!result) {
                break;
            }
            for (char aChar : chars) {
                if (aChar == '#') {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    private static Rectangle getOnlyOneRectangle(char[][] table, char symbol, char replace, StartingPoint sp) {
        int startX = Integer.MAX_VALUE;
        int startY = Integer.MAX_VALUE;
        int endX = -1;
        int endY = -1;
        boolean found = false;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                char l = table[i][j];
                if (l == symbol) {
                    found = true;
                    if (j < startX) {
                        startX = j;
                    }
                    if (j > endX) {
                        endX = j;
                    }
                    if (i < startY) {
                        startY = i;
                    }
                    if (i > endY) {
                        endY = i;
                    }
//                    endX = getEndX(startX, table[startY], symbol);
//                    endY = getEndY(startY, startX, table, symbol);
                }
            }
        }
        if (!found) {
            return null;
        }
        return copyTableAndMark(table, startX, startY, endX, endY, replace, sp);
    }

    private static Rectangle copyTableAndMark(char[][] table, int startX, int startY, int endX, int endY, char symbol,
                                              StartingPoint sp) {
        if (startX == -1 || startY == -1 || endY == -1 || endX == -1) {
            return null;
        }
        char[][] copy1 = new char[endY - startY + 1][endX - startX + 1];
        char[][] copy2 = new char[endY - startY + 1][endX - startX + 1];
        for (int i = 0; i < endY - startY + 1; i++) {
            for (int j = 0; j < endX - startX + 1; j++) {
                copy1[i][j] = table[i + startY][j + startX];
                copy2[i][j] = table[i + startY][j + startX];
                table[i + startY][j + startX] = symbol;
            }
        }
        return new Rectangle(startX + sp.x1, startY + sp.y1, endX + sp.x1, endY + sp.y1, copy1, copy2, table, sp);
    }

    private static int getEndX(int startX, char[] row, char symbol) {
        int endX = startX;
        for (int j = startX; j < row.length; j++) {
            if (row[j] == symbol) {
                endX = j;
            } else {
                break;
            }
        }
        return endX;
    }

    private static int getEndY(int startY, int startX, char[][] table, char symbol) {
        int endY = startY;
        for (int i = startY; i < table.length; i++) {
            if (table[i][startX] == symbol) {
                endY = i;
            } else {
                break;
            }
        }
        return endY;
    }

    static class Rectangle {
        int x1;
        int y1;
        int x2;
        int y2;
        char[][] immutable;
        char[][] mutable;
        char[][] original;
        List<Rectangle> innerRectangles = new ArrayList<>();
        StartingPoint point;
        boolean isRectangle = true;

        public Rectangle(int x1, int y1, int x2, int y2, char[][] immutable, char[][] mutable, char[][] original,
                         StartingPoint sp) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.immutable = immutable;
            this.mutable = mutable;
            this.original = original;
            this.point = sp;
        }

        public void analyzeRectangle() {
            Rectangle inner;
            while ((inner = getOnlyOneRectangle(mutable, '.', '#', new StartingPoint(x1, y1, x2, y2))) != null) {
                mutable = inner.original;
                if (!isFullFilled(inner.mutable, '.'))
                    inner.isRectangle = false;
                innerRectangles.add(inner);
            }
        }

        public int countInnerRectangles() {
            return innerRectangles.size();
        }

        public boolean isO() {
            if (innerRectangles.size() != 1) {
                return false;
            }
            Rectangle inner = innerRectangles.iterator().next();
            return inner.isRectangle && x1 < inner.x1 && inner.x2 < x2 && y1 < inner.y1 && inner.y2 < y2;
        }

        public boolean isC() {
            if (innerRectangles.size() != 1) {
                return false;
            }
            Rectangle inner = innerRectangles.iterator().next();
            return inner.isRectangle && x1 < inner.x1 && inner.x2 == x2 && y1 < inner.y1 && inner.y2 < y2;
        }

        public boolean isL() {
            if (innerRectangles.size() != 1) {
                return false;
            }
            Rectangle inner = innerRectangles.iterator().next();
            return x1 < inner.x1 && inner.x2 == x2 && y1 == inner.y1 && y2 > inner.y2;
        }

        public boolean isH() {
            if (innerRectangles.size() != 1) {
                return false;
            }
            Set<Integer> idx = new HashSet<>();
            Rectangle inner1 = innerRectangles.iterator().next();
            for (int i = 0; i < inner1.mutable.length; i++) {
                for (int j = 0; j < inner1.mutable[i].length; j++) {
                    if (inner1.mutable[i][j] == '#') {
                        idx.add(i);
                    }
                }
            }
            boolean b = idx.size() == 1;
            boolean result = true;
            if (b) {
                char[] chars = mutable[idx.iterator().next()];
                for (char aChar : chars) {
                    if (aChar != '#') {
                        result = false;
                        break;
                    }
                }
            }
            return result;
        }

        public boolean isP() {
            if (innerRectangles.size() != 1) {
                return false;
            }
            Rectangle inner = innerRectangles.iterator().next();
            return x1 < inner.x1 && inner.x2 == x2 && y1 < inner.y1 && inner.y2 == y2;
        }
    }

    static class StartingPoint {
        int x1;
        int y1;
        int x2;
        int y2;

        public StartingPoint(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }
}
