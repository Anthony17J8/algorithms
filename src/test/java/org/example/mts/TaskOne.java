package org.example.mts;

import java.util.Scanner;

public class TaskOne {

    public static void main(String[] args) throws java.lang.Exception {
        Scanner sc = new Scanner(System.in); // System.in is a standard input stream
        int fixedPayment = Integer.parseInt(sc.nextLine());
        int addPayment = Integer.parseInt(sc.nextLine());
        int totalTraffic = Integer.parseInt(sc.nextLine());
        int result = calculateTotal(fixedPayment, addPayment, totalTraffic);
        System.out.println(String.valueOf(result));
    }

    private static int calculateTotal(int fixed, int add, int total) {
        if (total <= 100) {
            return fixed;
        }
        return (total - 100) * add + fixed;
    }
}
