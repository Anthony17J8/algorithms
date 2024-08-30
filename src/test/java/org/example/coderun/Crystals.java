package org.example.coderun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Crystals {

    /**
     * Ввод
     * aaaza
     * aazzaa
     * azzza
     * <p>
     * Вывод
     * aazza
     */

    /**
     * aaaza
     * aazzaa
     * azzzaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            char[] s1 = reader.readLine().trim().toCharArray();
            char[] s2 = reader.readLine().trim().toCharArray();
            char[] s3 = reader.readLine().trim().toCharArray();
            StringBuilder sb = new StringBuilder();
            boolean fail = false;
            if (s1.length == 0 || s2.length == 0 || s3.length == 0) {
                writer.write("IMPOSSIBLE");
            } else {
                List<Node> l1 = getList(s1);
                List<Node> l2 = getList(s2);
                List<Node> l3 = getList(s3);

                if (l1.size() != l2.size() || l1.size() != l3.size()) {
                    writer.write("IMPOSSIBLE");
                } else {
                    for (int i = 0; i < l1.size(); i++) {
                        if (l1.get(i).val == l2.get(i).val && l1.get(i).val == l3.get(i).val) {
                            sb.append(getMedian(l1.get(i).val, l1.get(i).count, l2.get(i).count, l3.get(i).count));
                        } else {
                            writer.write("IMPOSSIBLE");
                            fail = true;
                            break;
                        }
                    }
                }
            }
            if (!fail && !sb.isEmpty()) {
                writer.write(sb.toString());
            }
        }
    }

    private static String getMedian(char c, int length1, int length2, int length3) {
        int count = IntStream.of(length1, length2, length3).sorted().toArray()[1];
        return Stream.generate(() -> c).limit(count).collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
    }

    private static List<Node> getList(char[] chars) {
        List<Node> l = new ArrayList<>();
        char target = chars[0];
        int count = 1;
        for (int i = 1; i < chars.length; i++) {
            if (target == chars[i]) {
                count++;
            } else {
                l.add(new Node(target, count));
                count = 1;
                target = chars[i];
            }
        }
        l.add(new Node(target, count));
        return l;
    }

    static class Node {
        private char val;
        private int count;

        public Node(char val, int count) {
            this.val = val;
            this.count = count;
        }
    }
}

/**
 * aaaza
 * aazzaa
 * azzzaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
 * aaaaa
 * aaaaa
 * aaa
 */