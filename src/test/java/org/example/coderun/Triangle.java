package org.example.coderun;

import java.io.*;

public class Triangle {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int a = Integer.parseInt(reader.readLine());
        int b = Integer.parseInt(reader.readLine());
        int c = Integer.parseInt(reader.readLine());
        String res = "NO";
        if (checkTriangle(a+b, c) && checkTriangle(a+c,b) && checkTriangle(b+c, a)) {
            res = "YES";
        }
        writer.write(res);
        reader.close();
        writer.close();
    }

    private static boolean checkTriangle(int sum, int third) {
        return sum > third;
    }
}
