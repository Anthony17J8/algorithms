package org.example.coderun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class NearestBusStop {

    /**
     * 3 2
     * 1 3 5
     * 4 1
     * <p>
     * Если есть остановки, совпадающие с координатой автобуса, то стоит вывести минимальную по номеру среди них.
     * Если есть остановки справа и слева, то стоит вывести максимальную по номеру среди левых.
     * Если есть остановки только слева, то стоит вывести максимальную по номеру среди левых.
     * Если есть остановки только справа, то стоит вывести минимальную по номеру среди правых
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] s = reader.readLine().split(" ");
        // кол-во остановок
        int n = Integer.parseInt(s[0]);
        // кол-во запросов, координата автобуса
        int k = Integer.parseInt(s[1]);
        // остановки
        int[] stops = getIntArray(reader, n);
        int[] coordinates = getIntArray(reader, k);

        for (int i = 0; i < coordinates.length; i++) {
            int idx = searchIndex(coordinates[i], stops);
            System.out.println(idx);
        }

        reader.close();
        writer.close();
    }

    private static int searchIndex(int coordinate, int[] stops) {
        return binSearch(coordinate, stops, 0, stops.length - 1);
    }

    private static int binSearch(int coordinate, int[] stops, int from, int to) {
        if (to - from == 1) {
            if (coordinate <= stops[from] || stops[from] == stops[to]) {
                return from + 1;
            }
            if (coordinate >= stops[to]) {
                return to + 1;
            }
            return 1 + (Math.abs(coordinate - stops[from]) <= Math.abs(coordinate - stops[to]) ? from : to);
        }
        int middle = (to + from) / 2;
        if (coordinate > stops[middle]) {
            return binSearch(coordinate, stops, middle, to);
        } else {
            return binSearch(coordinate, stops, from, middle);

        }
    }

    private static int[] getIntArray(BufferedReader reader, int n) throws IOException {
        String[] sArr = reader.readLine().split(" ");
        int[] res = new int[sArr.length];
        for (int i = 0; i < n; i++) {
            res[i] = Integer.parseInt(sArr[i]);
        }
        return res;
    }

    // too slow
    private static int searchIndexSlow(String coordinate, String[] stops) {
        int idx = 0;
        while (Integer.parseInt(coordinate) > Integer.parseInt(stops[idx]) && idx < stops.length - 1) {
            idx++;
        }
        if (idx == 0 || coordinate.equals(stops[idx])) {
            return idx + 1;
        }

        return idx + (Math.abs(Integer.parseInt(coordinate) - Integer.parseInt(stops[idx])) >= Math.abs(Integer.parseInt(coordinate) - Integer.parseInt(stops[idx - 1])) ? 0 : 1);
    }
}
