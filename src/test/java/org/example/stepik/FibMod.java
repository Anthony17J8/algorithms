package org.example.stepik;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FibMod {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] array = reader.readLine().split(" ");
        String n = array[0];
        long l = Long.parseLong(n);
        int m = Integer.parseInt(array[1]);
        long[] arr = new long[n.length() + 1];

        if (n.length() == 1) {
            writer.write("1");
        } else {
        }

        reader.close();
        writer.close();
    }
}
