//https://leetcode.com/problems/reorder-list/?envType=problem-list-v2&envId=two-pointers

package org.example.leetcode;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ReorderList {

    @ParameterizedTest
    @Timeout(1)
    @MethodSource("source")
    void test(ListNode head, int[] out) {
        reorderList(head);
        int[] actual = new int[out.length];
        int idx = 0;
        ListNode h = head;
        while (h != null) {
            actual[idx++] = h.val;
            h = h.next;
        }
        assertArrayEquals(out, actual);
    }


    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(createListNode(new int[]{1, 2, 3, 4}), new int[]{1, 4, 2, 3}),
                Arguments.of(createListNode(new int[]{1}), new int[]{1}),
                Arguments.of(createListNode(new int[]{1, 2, 3, 4, 5}), new int[]{1, 5, 2, 4, 3}));
    }

    private static ListNode createListNode(int[] values) {
        ListNode current = null;
        for (int i = values.length - 1; i >= 0; i--) {
            current = new ListNode(values[i], current);
        }
        return current;
    }

    public void reorderList(ListNode head) {
        LinkedList<ListNode> stack = new LinkedList<>();
        ListNode current = head;
        while (current != null) {
            stack.addFirst(current);
            current = current.next;
        }
        Set<ListNode> processed = new HashSet<>();
        current = head;
        ListNode prev = null;
        while (current != null) {
            if (processed.contains(current)) {
                current.next = null;
                break;
            }
            processed.add(current);
            if (prev != null) {
                prev.next = current;
            }
            prev = current;
            current = current.next;
            ListNode el = stack.pop();
            if (!processed.contains(el)) {
                processed.add(el);
                prev.next = el;
                prev = el;
            } else {
                el.next = null;
                break;
            }
        }
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