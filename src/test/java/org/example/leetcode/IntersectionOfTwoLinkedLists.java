//https://leetcode.com/problems/intersection-of-two-linked-lists/description/?envType=problem-list-v2&envId=two-pointers

package org.example.leetcode;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.HashMap;
import java.util.stream.Stream;

public class IntersectionOfTwoLinkedLists {

    @ParameterizedTest
    @Timeout(1)
    @MethodSource("source")
    void test(ListNode head, int x, int[] out) {

    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(createListNode(new int[]{1, 4, 3, 2, 5, 2}), 3, new int[]{1, 2, 2, 4, 3, 5}),
                Arguments.of(createListNode(new int[]{2, 1}), 2, new int[]{1, 2}),
                Arguments.of(createListNode(new int[]{3, 2, 1}), 4, new int[]{3, 2, 1}),
                Arguments.of(createListNode(new int[]{6, 3, 2, 1,}), 5, new int[]{3, 2, 1, 6}),
                Arguments.of(createListNode(new int[]{2, 3, 4}), 1, new int[]{2, 3, 4}),
                Arguments.of(createListNode(new int[]{}), 1, new int[]{}),
                Arguments.of(createListNode(new int[]{1}), 1, new int[]{1})


        );
    }

    private static ListNode createListNode(int[] values) {
        ListNode current = null;
        for (int i = values.length - 1; i >= 0; i--) {
            current = new ListNode(values[i], current);
        }
        return current;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode intersected = null;
        Map<ListNode, Integer> cache = new HashMap<>();
        ListNode current = headA;
        int idx = 0;
        while (current != null) {
            cache.put(current, idx++);
            current = current.next;
        }
        current = headB;
        idx = 0;
        while (current != null) {
            if (cache.containsKey(current)) {
                intersected = current;
                break;
            }
            current = current.next;
        }
        return intersected;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}