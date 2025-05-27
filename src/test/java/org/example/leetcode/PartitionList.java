//https://leetcode.com/problems/partition-list/description/?envType=problem-list-v2&envId=two-pointers

package org.example.leetcode;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class PartitionList {

    @ParameterizedTest
    @Timeout(1)
    @MethodSource("source")
    void test(ListNode head, int x, int[] out) {
        ListNode result = partition(head, x);
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

    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return head;
        }
        ListNode afterHead = null;
        ListNode beforeHead = null;
        ListNode beforeNode = null;
        ListNode afterNode = null;
        ListNode current = head;
        ListNode result = head;
        while (current != null) {
            if (current.val < x) {
                if (beforeNode == null) {
                    beforeNode = current;
                    beforeHead = beforeNode;
                } else {
                    beforeNode.next = current;
                    beforeNode = current;
                }
            } else {
                if (afterNode == null) {
                    afterNode = current;
                    afterHead = afterNode;
                } else {
                    afterNode.next = current;
                    afterNode = current;
                }
            }
            current = current.next;
        }
        if (beforeNode != null) {
            beforeNode.next = afterHead;
            result = beforeHead;
        } else {
            result = afterHead;
        }
        if (afterNode != null) {
            afterNode.next = null;
        }
        return result;
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