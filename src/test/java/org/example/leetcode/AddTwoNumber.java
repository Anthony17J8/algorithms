package org.example.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 * <p>
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 * <p>
 * Example 2:
 * <p>
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 * Example 3:
 * <p>
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 */

public class AddTwoNumber {

    public static void main(String[] args) {
        ListNode result = addTwoNumbers(
            new ListNode(2, new ListNode(4, new ListNode(3, null))),
            new ListNode(5, new ListNode(6, new ListNode(4, null))));
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        List<Integer> cache1 = getCache(l1);
        List<Integer> cache2 = getCache(l2);
        List<Integer> result = new ArrayList<>();
        int remainder = 0;
        for (int i = 0; i < Math.max(cache1.size(), cache2.size()) || remainder > 0; i++) {
            int val1 = 0;
            int val2 = 0;
            if (i <= cache1.size() - 1) {
                val1 = cache1.get(i);
            }
            if (i <= cache2.size() - 1) {
                val2 = cache2.get(i);
            }
            int sum = val1 + val2;
            result.add((sum + remainder) % 10);
            remainder = (sum + remainder) / 10;
        }

        ListNode res = null;
        ListNode current = res;
        for (Integer num : result) {
            if (res == null) {
                res = new ListNode(num);
                current = res;
            } else {
                ListNode next = new ListNode(num);
                current.next = next;
                current = next;
            }
        }
        return res;
    }

    private static List<Integer> getCache(ListNode node) {
        List<Integer> list = new ArrayList<>();
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }
        return list;
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