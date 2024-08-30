package org.example.coderun;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PairOfCodes {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int total = Integer.parseInt(reader.readLine());
        String[] nums = reader.readLine().split(" ");
        if (total != nums.length) {
            writer.write("0");
        } else {
            writer.write(String.valueOf(getResult(nums)));
        }
        reader.close();
        writer.close();
    }

    private static long getResult(String[] nums) {
        Map<String, Integer> map = getBinaryCountMap(nums);
        List<String> integers = new ArrayList<>(map.keySet());
        long result = 0;
        if (integers.size() == 1) {
            result += getSum(map.get(integers.get(0)));
        } else {
            for (int i = 0; i < integers.size(); i++) {
                Integer num1 = Integer.valueOf(integers.get(i), 2);
                boolean used = false;
                for (int j = i + 1; j < integers.size(); j++) {
                    Integer num2 = Integer.valueOf(integers.get(j), 2);
                    if ((num1 & num2) != 0) {
                        if (used) {
                            result += (long) map.get(integers.get(i)) * map.get(integers.get(j));
                        } else {
                            result += getSum(map.get(integers.get(i))) + ((long) map.get(integers.get(i)) * map.get(integers.get(j)));
                            used = true;
                        }
                    }
                }
                if (!used) {
                    result += getSum(map.get(integers.get(i)));
                }
            }
        }
        return result;
    }

    private static long getSum(int n) {
        return (long) (((--n + 1) / 2.0) * n);
    }

    private static Map<String, Integer> getBinaryCountMap(String[] nums) {
        Map<String, Integer> map = new HashMap<>();
        for (String num : nums) {
            String s = transformToBinary(num);
            if (map.computeIfPresent(s, (key, val) -> val + 1) == null) {
                map.put(s, 1);
            }
        }
        return map;
    }

    private static String transformToBinary(String s) {
        byte[] arr = new byte[10];
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            arr[Byte.parseByte(String.valueOf(chars[i]))] = 1;
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : arr) {
            sb.append(b);
        }
        return sb.toString();
    }

    @Test
    void test() {
        assertEquals(4, getResult(new String[]{"102", "203", "455", "201", "333"}));
        assertEquals(124999750000L, getResult(generateStr(500_000)));
        assertEquals(0, getResult(new String[]{"1"}));
        assertEquals(10, getResult(new String[]{"11", "11", "11", "1", "11111"}));
        assertEquals(45, getResult(new String[]{"1", "1", "1", "1", "1", "1", "1", "1", "1", "1"}));
        assertEquals(6, getResult(new String[]{"1", "2", "21", "1", "2"}));
        assertEquals(15, getResult(new String[]{"123", "123", "22", "32", "321", "321", "55"}));
        assertEquals(9, getResult(new String[]{"123", "55", "556", "555", "123", "321", "231"}));
        assertEquals(9, getResult(new String[]{"123", "654", "1", "10", "29", "566", "231"}));
        assertEquals(18, getResult(new String[]{"123", "9873", "987", "98731", "9872", "897", "880"}));
        assertEquals(0, getResult(new String[]{"12", "57", "8", "9", "30", "4", "6"}));
        assertEquals(9, getResult(new String[]{"1", "12", "123", "1234", "456", "678", "890"}));
        assertEquals(13, getResult(new String[]{"1", "12", "123", "1234", "456", "678", "1234"}));
        assertEquals(15, getResult(new String[]{"0", "0", "0", "1", "0", "0", "0"}));
        assertEquals(10, getResult(new String[]{"10", "22", "77", "7", "77777777", "777777", "777777"}));
        assertEquals(4, getResult(new String[]{"1354345", "222222222", "8989809700", "5453543", "32135453"}));
        assertEquals(7, getResult(new String[]{"123", "555", "15", "901", "109"}));
        assertEquals(4, getResult(new String[]{"10", "29", "92", "29", "10"}));
        assertEquals(6, getResult(new String[]{"123123123123", "2", "54", "321321321312312", "233333"}));
        assertEquals(10, getResult(new String[]{"123", "321", "3456", "5463", "2231"}));
        assertEquals(1, getResult(new String[]{"8800", "97", "123", "21111111111", "4556"}));
        assertEquals(7, getResult(new String[]{"11", "33", "11", "21", "31"}));
        assertEquals(5, getResult(new String[]{"0", "12", "10", "2", "32"}));
        assertEquals(10, getResult(new String[]{"112", "221", "2222221", "121", "3233333333"}));
        assertEquals(10, getResult(new String[]{"1230000", "332445510000", "3456", "5463", "2231"}));
        assertEquals(10, getResult(new String[]{"1234567890", "123456789023123546564", "12345678903234353445", "12345678903423423423435345646", "43425656512345678902321312312454656"}));
        assertEquals(8, getResult(new String[]{"243487346875648375683476587346587364756348623874632382374598346578436584376583765873428734678234", "384739563487658734658734657834657982364982367459832748923649865973465947938472", "11111111222222222221212121222121212212121", "900000203030300000200000002020202020202020200000000000000", "1010101010101010101010101010101010101010101010101010110"}));
    }

    private String[] generateStr(int i) {
        return Stream.generate(() -> "1")
                .limit(500_000)
                .toList().toArray(new String[]{});
    }
}
