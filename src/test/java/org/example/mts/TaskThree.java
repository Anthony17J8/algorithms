package org.example.mts;

import java.util.Scanner;

public class TaskThree {

    public static void main(String[] args) throws java.lang.Exception {

        Scanner sc = new Scanner(System.in); // System.in is a standard input stream
        int stationsTotal = Integer.parseInt(sc.nextLine());
        int total = 0;
        while (stationsTotal > 0) {
            total += Integer.parseInt(sc.nextLine());
            stationsTotal--;
        }
        System.out.println(String.valueOf(total % 2 == 0 ? 1 : 0));
    }
}
