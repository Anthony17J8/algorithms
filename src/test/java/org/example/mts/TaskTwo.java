package org.example.mts;

import java.util.Scanner;

public class TaskTwo {

    static final String TARGET = "MTS";

    public static void main(String[] args) throws java.lang.Exception {

        Scanner sc = new Scanner(System.in); // System.in is a standard input stream
        System.out.println(String.valueOf(isMTSString(sc.nextLine())));
    }

    private static int isMTSString(String input) {
        char[] chars = TARGET.toCharArray();
        int idx = 0;
        if (input.length() == 3) {
            return TARGET.equals(input) ? 1 : 0;
        }
        for (char a : input.toCharArray()) {
            if (chars[idx] == a) {
                if (++idx == 3) {
                    break;
                }
            }
        }
        return idx == 3 ? 1 : 0;
    }
}
