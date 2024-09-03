package org.example.coderun;

import java.io.*;
import java.util.List;

public class MostQueens {

    /**
     * Пример 1 Ввод 3 2 4 1 Вывод 2 Пример 2 Ввод 1 1 1 2 Вывод 1 Пример 3 Ввод 1 1 1 1 Вывод 0
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        if (input.length != 4) {
            writer.write(String.valueOf(0));
        } else {
            int player1 = Integer.parseInt(input[0]);
            int player2 = Integer.parseInt(input[1]);
            int player3 = Integer.parseInt(input[2]);
            int player4 = Integer.parseInt(input[3]);
            int fail1 = validationFailed(player1);
            int fail2 = validationFailed(player2);
            int fail3 = validationFailed(player3);
            int fail4 = validationFailed(player4);
            int fails = fail1 + fail2 + fail3 + fail4;
            if (fails > 0) {
                writer.write(String.valueOf(fails));
            } else {
                List<Integer> players = List.of(player1, player2, player3, player4);
                writer.write(String.valueOf(Math.min(getResult(players, 4), getResult(players, 2))));
            }

        }
        reader.close();
        writer.close();
    }

    private static int validationFailed(int player1) {
        return player1 < 0 || player1 > 9 ? 1 : 0;
    }

    private static int getResult(List<Integer> players, int num) {
        int result = 0;
        int pivot = players.get(0);
        int sum = pivot;
        boolean nullExpected = false;
        for (int i = 1; i < players.size(); i++) {
            if (pivot > num) {
                result++;
                pivot = players.get(i);
                continue;
            }
            int s = pivot + players.get(i);
            sum += players.get(i);
            if (s == num && !nullExpected) {
                nullExpected = true;
            } else if (nullExpected && players.get(i) != 0) {
                result++;
            } else if (s > num) {
                result++;
                pivot = Math.min(pivot, players.get(i));
            } else {
                pivot = Math.max(pivot, players.get(i));
            }

        }
        if (pivot > num) {
            result++;
        }
        if (result == 0 && sum > num) {
            result++;
        }
        return result;
    }
}
