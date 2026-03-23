

package org.example.leetcode;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/merge-two-sorted-lists/
 */
public class MergeTwoSortedLists {

    @ParameterizedTest
    @Timeout(1)
    @MethodSource("source")
    void test(ListNode node1, ListNode node2, ListNode result) {
        ListNode listNode = mergeTwoLists(node1, node2);
        while (result != null) {
            assertEquals(result.val, listNode.val);
            result = result.next;
            listNode = listNode.next;
        }

    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(
                        createListNode(new int[]{1, 2, 4}),
                        createListNode(new int[]{1, 3, 4}),
                        createListNode(new int[]{1, 1, 2, 3, 4, 4})
                ),
                Arguments.of(
                        createListNode(new int[]{}),
                        createListNode(new int[]{}),
                        createListNode(new int[]{})
                ),
                Arguments.of(
                        createListNode(new int[]{}),
                        createListNode(new int[]{0}),
                        createListNode(new int[]{0})
                )
        );
    }

    private static ListNode createListNode(int[] values) {
        ListNode current = null;
        for (int i = values.length - 1; i >= 0; i--) {
            current = new ListNode(values[i], current);
        }
        return current;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = null;
        ListNode tail = null;
        ListNode cur1 = list1;
        ListNode cur2 = list2;
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        while (cur1 != null && cur2 != null) {
            if (cur1.val <= cur2.val) {
                if (head == null) {
                    head = cur1;
                    tail = head;
                } else {
                    tail.next = cur1;
                    tail = cur1;
                }
                cur1 = cur1.next;
            } else {
                if (head == null) {
                    head = cur2;
                    tail = head;
                } else {
                    tail.next = cur2;
                    tail = cur2;
                }
                cur2 = cur2.next;
            }
        }

        while (cur1 != null) {
            tail.next = cur1;
            tail = cur1;
            cur1 = cur1.next;
        }

        while (cur2 != null) {
            tail.next = cur2;
            tail = cur2;
            cur2 = cur2.next;
        }
        return head;
    }


    private static class ListNode {
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