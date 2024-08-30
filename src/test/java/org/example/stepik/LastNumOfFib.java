package org.example.stepik;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Дано число 1 ≤ n ≤ 1 0 7 1≤n≤10 7 , необходимо найти последнюю цифру n n-го числа Фибоначчи.
 * <p>
 * Как мы помним, числа Фибоначчи растут очень быстро, поэтому при их вычислении нужно быть аккуратным с переполнением.
 * В данной задаче, впрочем, этой проблемы можно избежать, поскольку нас интересует только последняя цифра числа
 * Фибоначчи: если 0 ≤ a , b ≤ 9 0≤a,b≤9 — последние цифры чисел F i F i ​ и F i + 1 F i+1 ​ соответственно, то ( a + b
 * ) m o d 10 (a+b)mod10 — последняя цифра числа F i + 2 F i+2 ​ .
 */
public class LastNumOfFib {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(reader.readLine());
        long[] arr = new long[n + 1];

        if (n == 1) {
            writer.write("1");
        } else {
            arr[0] = 0;
            arr[1] = 1 % 10;
            for (int i = 2; i <= n; i++) {
                arr[i] = (arr[i - 1] + arr[i - 2]) % 10;
            }
            writer.write(String.valueOf(arr[n]));
        }

        reader.close();
        writer.close();
    }
}
