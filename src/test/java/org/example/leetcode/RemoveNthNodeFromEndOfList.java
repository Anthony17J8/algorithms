//https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/?envType=problem-list-v2&envId=two-pointers

package org.example.leetcode;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class RemoveNthNodeFromEndOfList {

    @ParameterizedTest
    @Timeout(1)
    @MethodSource("source")
    void test(ListNode listNode, int n, int[] out) {
        ListNode actual = removeNthFromEnd(listNode, n);
        int[] values = new int[out.length];
        int idx = 0;
        while (actual != null) {
            values[idx++] = actual.val;
            actual = actual.next;
        }
        assertArrayEquals(out, values);
    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(createListNode(new int[]{1, 2, 3, 4, 5}), 2, new int[]{1, 2, 3, 5}),
                Arguments.of(createListNode(new int[]{1}), 1, new int[]{})
        );
    }

    private static ListNode createListNode(int[] values) {
        ListNode current = null;
        for (int i = values.length - 1; i >= 0; i--) {
            current = new ListNode(values[i], current);
        }
        return current;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int total = 0;
        ListNode current = head;
        while (current != null) {
            total++;
            current = current.next;
        }
        int target = total - n;
        int idx = 0;
        ListNode result = head;
        ListNode previous = null;
        current = head;
        while (current != null) {
            if (idx == target) {
                if (previous == null) {
                    result = current.next;
                    break;
                } else {
                    previous.next = current.next;
                    break;
                }
            } else {
                previous = current;
            }
            idx++;
            current = current.next;
        }

        return result;
    }

    public static class ListNode {
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