

package org.example.leetcode;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://leetcode.com/problems/palindrome-linked-list/description/?envType=problem-list-v2&envId=two-pointers
 */
public class PalindromeLinkedList {

    @ParameterizedTest
    @Timeout(1)
    @MethodSource("source")
    void test(ListNode head, boolean result) {
        assertEquals(result, isPalindrome(head));

    }

    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(createListNode(new int[]{1, 2, 2, 1}), true),
                Arguments.of(createListNode(new int[]{1, 2}), false),
                Arguments.of(createListNode(new int[]{1, 2, 1}), true),
                Arguments.of(createListNode(new int[]{1, 2, 1, 1, 2, 1}), true)
        );
    }

    private static ListNode createListNode(int[] values) {
        ListNode current = null;
        for (int i = values.length - 1; i >= 0; i--) {
            current = new ListNode(values[i], current);
        }
        return current;
    }

    public boolean isPalindrome(ListNode head) {
        LinkedList<ListNode> nodes = new LinkedList<>();
        ListNode current = head;
        while (current != null) {
            nodes.addFirst(current);
            current = current.next;
        }

        boolean result = true;
        current = head;
        while (current != null) {
            ListNode n1 = nodes.pollFirst();
            if (current.val != n1.val) {
                result = false;
                break;
            }
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