package org.example.leetcode;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/circular-array-loop/description/?envType=problem-list-v2&envId=two-pointers
 */
public class Heaters {
    @ParameterizedTest
    @MethodSource("source")
    void test(int[] houses, int[] heaters, int out) {
        assertEquals(out, findRadius(houses, heaters));
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3}, new int[]{2}, 1),
                Arguments.of(new int[]{1, 2, 3, 4}, new int[]{1, 4}, 1),
                Arguments.of(new int[]{1, 5}, new int[]{2}, 3),
                Arguments.of(new int[]{1, 2, 7, 9}, new int[]{3, 8}, 2),
                Arguments.of(new int[]{1, 2, 7, 9}, new int[]{3, 8}, 2),
                Arguments.of(new int[]{1}, new int[]{1}, 0),
                Arguments.of(new int[]{1, 2, 10, 11}, new int[]{1, 2, 3, 4}, 7),
                Arguments.of(new int[]{1, 2, 5, 6}, new int[]{3}, 3),
                Arguments.of(new int[]{1, 2, 3, 4, 8}, new int[]{2, 7}, 2),
                Arguments.of(new int[]{1, 2, 3, 4, 8}, new int[]{2, 4}, 4),
                Arguments.of(new int[]{1, 2, 6, 7, 8}, new int[]{2, 5}, 3),
                Arguments.of(
                        new int[]{282475249, 622650073, 984943658, 144108930, 470211272, 101027544, 457850878, 458777923},
                        new int[]{823564440, 115438165, 784484492, 74243042, 114807987, 137522503, 441282327, 16531729, 823378840, 143542612},
                        161834419)

        );
    }

    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int heatIdx = 0;
        int houseIdx = 0;
        int result = 0;
        while (houseIdx < houses.length) {
            int house = houses[houseIdx];
            int heater = heaters[heatIdx];
            if (house <= heater) {
                result = Math.max(result, heater - house);
                houseIdx++;
            } else if (heatIdx == heaters.length - 1) {
                result = Math.max(result, Math.abs(house - heater));
                houseIdx++;
            } else {
                int diff1 = Math.abs(house - heater);
                int diff2 = Math.abs(heaters[heatIdx + 1] - house);
                if (diff1 < diff2) {
                    result = Math.max(result, diff1);
                    houseIdx++;
                } else {
                    heatIdx++;
                }
            }
        }
        return result;
    }
}
