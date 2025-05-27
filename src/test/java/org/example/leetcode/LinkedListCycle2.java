//https://leetcode.com/problems/partition-list/description/?envType=problem-list-v2&envId=two-pointers

package org.example.leetcode;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkedListCycle2 {

    @ParameterizedTest
    @Timeout(1)
    @MethodSource("source")
    void test(ListNode head, ListNode result) {
        assertEquals(result, detectCycle(head));
    }


    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(createListNode(new int[]{3, 2, 0, -4}), 1, true),
                Arguments.of(createListNode(new int[]{1, 2}), 0, true),
                Arguments.of(createListNode(new int[]{1}), -1, false));
    }

    private static ListNode createListNode(int[] values) {
        ListNode current = null;
        for (int i = values.length - 1; i >= 0; i--) {
            current = new ListNode(values[i], current);
        }
        return current;
    }

    public ListNode detectCycle(ListNode head) {
        ListNode current = head;
        Set<ListNode> hash = new HashSet<>();
        ListNode result = null;
        while (current != null) {
            if (hash.contains(current)) {
                result = current;
                break;
            }
            hash.add(current);
            current = current.next;
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