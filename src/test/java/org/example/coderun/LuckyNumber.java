package org.example.coderun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Eto trash
 */
public class LuckyNumber {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = reader.readLine();
        int len = s.length();
        long[] left = toArray(s.substring(0, s.length() / 2));
        long[] right = toArray(s.substring(s.length() / 2));
        right = add(right, 1);

        while (true) {
            if (toStr(right).length() > len / 2) {
                right = new long[s.length() / 2];
                left = add(left, 1);
            }
            if (toStr(left).length() > len / 2) {
                left = new long[s.length() / 2];
            }
            long sumLeft = getSum(left);
            long sumRight = getSum(right);
            if (sumLeft == sumRight && sumLeft != 0) {
                break;
            } else {
                if (sumLeft > sumRight) {
                    right = calculateRight(sumLeft, sumRight, toStr(right));
                } else {
                    if (sumLeft <= right[0]) {
                        left = add(left, 1);
                        right = toArray(prependZeros(getMin(getSum(left)), len / 2));
                    } else {
                        right = add(right, 1);
                    }
                }
            }
        }

        writer.write(toStr(left) + toStr(right));
        reader.close();
        writer.close();
    }

    private static long getMin(long sum) {
        if (sum < 9) {
            return sum;
        } else {
            return Integer.parseInt(String.valueOf(getMin(sum - 9)) + 9);
        }
    }

    private static long[] calculateRight(long sumLeft, long sumRight, String right) {
        long diff = sumLeft - sumRight;
        int[] arr = new int[right.length()];
        char[] chars = right.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(String.valueOf(chars[i]));
        }

        int cur = arr.length - 1;
        while (diff > 0 && cur >= 0) {
            if (arr[cur] == 9) {
                cur--;
            } else {
                arr[cur]++;
                diff--;
            }
        }
        if (diff > 0) {
            return new long[right.length()];
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i : arr) {
                sb.append(i);
            }
            return toArray(sb.toString());
        }
    }

    private static String prependZeros(long num, int len) {
        StringBuilder res = new StringBuilder(String.valueOf(num));
        while (res.length() < len) {
            res.insert(0, "0");
        }
        return res.toString();
    }

    private static long[] add(long[] arr, long num) {
        for (int i = arr.length - 1; i >= 0 && num > 0; i--) {
            long sum = arr[i] + num;
            if (sum > 10) {
                arr[i] = 0;
                num = sum - 10;
            } else if (sum == 10) {
                arr[i] = 0;
                num = 1;
            } else {
                arr[i] = sum;
                num -= sum;
            }
        }
        if (num > 0) {
            long[] newArr = new long[arr.length + 1];
            newArr[0] = num;
            System.arraycopy(arr, 0, newArr, 1, arr.length - 1);
            return newArr;
        }
        return arr;
    }

    private static long[] toArray(String num) {
        long[] arr = new long[num.length()];
        char[] chars = num.toCharArray();
        for (int i = 0; i < num.length(); i++) {
            arr[i] = Long.parseLong(String.valueOf(chars[i]));
        }
        return arr;
    }

    private static String toStr(long[] arr) {
        StringBuilder sb = new StringBuilder();
        for (long n : arr) {
            sb.append(n);
        }
        return sb.toString();
    }

    private static long getSum(long[] num) {
        long sum = 0;
        for (long l : num) {
            sum += l;
        }
        return sum;
    }
}

