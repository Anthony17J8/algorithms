package org.example.coderun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class TwoCommands {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int a = getInput(reader);
        int b = getInput(reader);
        int n = getInput(reader);

        System.out.println(getMaxStudents(a, n) > getMinStudents(b, n) ? "Yes" : "No");

        reader.close();
        writer.close();
    }

    private static int getInput(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static int getMinStudents(int num, int taskCount) {
        int min = Integer.MAX_VALUE;
        while (taskCount > 0) {
            min = Math.min(min, (int) Math.ceil(num / (taskCount * 1d)));
            taskCount--;
        }
        return min;
    }

    private static int getMaxStudents(int num, int taskCount) {
        int max = Integer.MIN_VALUE;
        while (taskCount > 0) {
            max = Math.max(max, (int) Math.floor(num / (taskCount * 1d)));
            taskCount--;
        }
        return max;
    }
}
