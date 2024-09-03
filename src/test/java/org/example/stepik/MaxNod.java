package org.example.stepik;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MaxNod {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nums = reader.readLine().split(" ");
        int num1 = Integer.parseInt(nums[0]);
        int num2 = Integer.parseInt(nums[1]);

        writer.write(String.valueOf(getNod(num1, num2)));

        reader.close();
        writer.close();
    }

    private static int getNod(int num1, int num2) {
        if (num1 == 0) {
            return num2;
        }
        if (num2 == 0) {
            return num1;
        }

        if (num1 > num2) {
            return getNod(num1 % num2, num2);
        } else {
            return getNod(num2 % num1, num1);
        }
    }
}
