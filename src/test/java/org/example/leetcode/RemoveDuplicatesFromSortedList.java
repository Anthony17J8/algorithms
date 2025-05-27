//https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/?envType=problem-list-v2&envId=two-pointers

package org.example.leetcode;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class RemoveDuplicatesFromSortedList {

    @ParameterizedTest
    @Timeout(1)
    @MethodSource("source")
    void test(ListNode head, int[] out) {
        ListNode result = deleteDuplicates(head);
        int[] actual = new int[out.length];
        int idx = 0;
        ListNode h = result;
        while (h != null) {
            actual[idx++] = h.val;
            h = h.next;
        }
        assertArrayEquals(out, actual);
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(createListNode(new int[]{1, 2, 3, 3, 4, 4, 5}), new int[]{1, 2, 5}),
                Arguments.of(createListNode(new int[]{1, 1, 1, 2, 3}), new int[]{2, 3}),
                Arguments.of(createListNode(new int[]{1, 1, 2, 2, 3}), new int[]{3}),
                Arguments.of(createListNode(new int[]{1, 2, 2, 2, 3}), new int[]{1, 3}),
                Arguments.of(createListNode(new int[]{1, 2, 3, 3}), new int[]{1, 2}),
                Arguments.of(createListNode(new int[]{1, 1, 2, 2, 3, 3}), new int[]{}),
                Arguments.of(createListNode(new int[]{1, 1}), new int[]{}),
                Arguments.of(createListNode(new int[]{1}), new int[]{1})

        );
    }

    private static ListNode createListNode(int[] values) {
        ListNode current = null;
        for (int i = values.length - 1; i >= 0; i--) {
            current = new ListNode(values[i], current);
        }
        return current;
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode newHead = null;
        ListNode uq = null;
        ListNode current = head;

        while (current != null) {
            if (current.next == null || current.val != current.next.val) {
                if (newHead == null) {
                    newHead = current;
                    uq = current;
                } else {
                    uq.next = current;
                    uq = current;
                }
                current = current.next;
            } else {
                if (uq != null) {
                    uq.next = null;
                }
                int val = current.val;
                while (current != null && val == current.val) {
                    current = current.next;
                }
            }

        }
        return newHead;
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