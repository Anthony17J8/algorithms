package org.example.coderun;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PairOfSymbols {

    /**
     * ABCABC -> BC
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().split(" ");
        Map<String, Integer> resultMap = new HashMap<>();
        for (String in : input) {
            subString(in, resultMap);
        }

        String result = "";
        int max = 0;
        for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {
            if (entry.getValue() > max) {
                result = entry.getKey();
                max = entry.getValue();
            } else if (entry.getValue() == max) {
                if (entry.getKey().compareTo(result) > 0) {
                    result = entry.getKey();
                }
            }
        }
        writer.write(result);

        reader.close();
        writer.close();
    }

    private static Map<String, Integer> subString(String inputStr, Map<String, Integer> result) {
        char[] charArr = inputStr.toCharArray();
        for (int i = 0; i + 1 < inputStr.length(); i++) {
            String key = String.valueOf(charArr, i, 2);
            if (result.computeIfPresent(key, (k, val) -> val + 1) == null) {
                result.putIfAbsent(key, 1);
            }
        }
        return result;
    }
}
