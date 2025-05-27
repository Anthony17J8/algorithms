//https://leetcode.com/problems/reorder-list/?envType=problem-list-v2&envId=two-pointers

package org.example.leetcode;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SortList {

    @ParameterizedTest
    @Timeout(1)
    @MethodSource("source")
    void test(ListNode head, int[] out) {
        ListNode res = sortList(head);
        int[] actual = new int[out.length];
        int idx = 0;
        ListNode h = res;
        while (h != null) {
            actual[idx++] = h.val;
            h = h.next;
        }
        assertArrayEquals(out, actual);
    }


    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(createListNode(new int[]{4, 2, 1, 3}), new int[]{1, 2, 3, 4}),
                Arguments.of(createListNode(new int[]{}), new int[]{}),
                Arguments.of(createListNode(new int[]{1}), new int[]{1}),
                Arguments.of(createListNode(new int[]{-1, 5, 3, 4, 0}), new int[]{-1, 0, 3, 4, 5}));
    }

    private static ListNode createListNode(int[] values) {
        ListNode current = null;
        for (int i = values.length - 1; i >= 0; i--) {
            current = new ListNode(values[i], current);
        }
        return current;
    }

    public ListNode sortList(ListNode head) {
        int total = 0;
        ListNode current = head;
        while (current != null) {
            total++;
            current = current.next;
        }
        return divideAndConquer(head, total);
    }

    private ListNode divideAndConquer(ListNode head, int total) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pivot = findPivot(head, total);
        ListNode current = head;
        ListNode beforeTail = null;
        ListNode beforeHead = null;
        ListNode afterHead = null;
        ListNode afterTail = null;
        int beforeCount = 0;
        int afterCount = 0;
        while (current != null) {
            if (current.val < pivot.val) {
                if (beforeHead == null) {
                    beforeHead = current;
                    beforeTail = beforeHead;
                } else {
                    beforeTail.next = current;
                    beforeTail = current;
                }
                beforeCount++;
            } else if (current.val >= pivot.val && current != pivot) {
                if (afterHead == null) {
                    afterHead = current;
                    afterTail = afterHead;
                } else {
                    afterTail.next = current;
                    afterTail = current;
                }
                afterCount++;
            }
            current = current.next;
        }
        if (afterTail != null) {
            afterTail.next = null;
        }
        if (beforeTail != null) {
            beforeTail.next = null;
        }
        return merge(divideAndConquer(beforeHead, beforeCount), pivot, divideAndConquer(afterHead, afterCount));
    }

    private ListNode merge(ListNode beforeHead, ListNode pivot, ListNode afterHead) {
        if (beforeHead == null) {
            pivot.next = afterHead;
            return pivot;
        }
        ListNode tail = beforeHead;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = pivot;
        pivot.next = afterHead;
        return beforeHead;
    }

    private ListNode findPivot(ListNode head, int total) {
        int idx = 0;
        ListNode current = head;
        while (idx < total / 2) {
            current = current.next;
            idx++;
        }
        return current;
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