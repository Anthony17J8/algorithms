package org.example.stepik;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Fibonacci {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(reader.readLine());
        long[] arr = new long[n + 1];
        if (n == 1) {
            writer.write("1");
        } else {
            arr[0] = 0;
            arr[1] = 1;
            for (int i = 2; i <= n; i++) {
                arr[i] = arr[i - 1] + arr[i - 2];
            }
            writer.write(String.valueOf(arr[n]));
        }

        reader.close();
        writer.close();
    }
}
