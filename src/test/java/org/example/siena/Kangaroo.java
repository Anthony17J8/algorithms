package org.example.siena;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Kangaroo {

    @Test
    void test() {
        assertEquals("YES", kangaroo(0, 3, 4, 2));
        assertEquals("NO", kangaroo(0, 2, 5, 3));
        assertEquals("YES", kangaroo(43, 5, 49, 3));
        assertEquals("NO", kangaroo(21, 6, 47, 3));

    }

    /*
     * Complete the 'kangaroo' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. INTEGER x1
     *  2. INTEGER v1
     *  3. INTEGER x2
     *  4. INTEGER v2
     */
    public static String kangaroo(int x1, int v1, int x2, int v2) {
        String result = "NO";
        if ((v1 >= v2 && x1 > x2) || (v1 <= v2 && x1 < x2)) {
            return result;
        } else {
            for (int k1 = x1, k2 = x2; ; k1 = k1 + v1, k2 = k2 + v2) {
                if (k1 == k2) {
                    result = "YES";
                    break;
                }
                if ((v1 > v2 && k2 < k1) || (v2 > v1 && k1 < k2)) {
                    break;
                }
            }
        }
        return result;
    }
}
