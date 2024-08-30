package org.example.siena;

import org.junit.jupiter.api.Test;

/**
 * Given the head of a linked list, rotate the list to the right by k places.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [4,5,1,2,3]
 * Example 2:
 * <p>
 * <p>
 * Input: head = [0,1,2], k = 4
 * Output: [2,0,1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range [0, 500].
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 109
 */

public class RotateList {

    @Test
    void test() {
        ListNode n1 = createTestData1();
        ListNode n2 = createTestData2();
//        System.out.println(printNode(rotateRight(n1, 3)));
        System.out.println(printNode(rotateRight(n2, 6)));

    }

    private ListNode createTestData1() {
        ListNode n5 = new ListNode(5); // 1
        ListNode n4 = new ListNode(4, n5);
        ListNode n3 = new ListNode(3, n4);
        ListNode n2 = new ListNode(2, n3);
        ListNode n1 = new ListNode(1, n2);
        return n1;
    }

    private ListNode createTestData2() {
        ListNode n3 = new ListNode(2); // 1
        ListNode n2 = new ListNode(1, n3);
        ListNode n1 = new ListNode(0, n2);
        return n1;
    }

    private String printNode(ListNode rotateRight) {
        StringBuilder res = new StringBuilder();
        while (rotateRight != null) {
            res.append(rotateRight.val);
            rotateRight = rotateRight.next;
        }
        return res.toString();
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null && k == 0) {
            return head;
        }

        ListNode node = head;
        int size = 1;
        ListNode tail2;
        while (node.next != null) {
            node = node.next;
            size++;
        }
        tail2 = node;
        if (size == k) {
            return head;
        }

        ListNode head2 = null;
        ListNode tail1 = null;
        int count = 1;
        node = head;
        int limit = size > k ? k : k % size;

        if (limit == 0) {
            return head;
        }

        while (node.next != null) {
            if (count == size - limit) {
                tail1 = node;
                head2 = node.next;
                break;
            } else {
                node = node.next;
                count++;
            }
        }
        tail2.next = head;
        if (tail1 != null) {
            tail1.next = null;
        }
        return head2;
    }

    /**
     * Definition for singly-linked list.
     */
    public class ListNode {
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
