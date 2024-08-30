package org.example.luridas;

import java.util.LinkedList;
import java.util.Objects;

// создание пустого списка
// вставка элемента в голову списка
// вставка в середину
// получение из списка
// извлечение из списка
public class LinkedListImpl {

    private Node root;

    public LinkedListImpl createEmptyList() {
        return new LinkedListImpl();
    }

    public Node insert(Node p, Node target) {
        if (p == null) {
            if (root != null) {
                target.next = root;
            }
            this.root = target;
        } else {
            Node found = root;
            while (found != null) {
                if (p.equals(found)) {
                    Node pNext = p.next;
                    p.next = target;
                    target.next = pNext;
                    break;
                }
                found = found.next;
            }
        }
        return target;
    }

    public Node getNextListNode(Node afterNode) {
        if (afterNode == null) {
            return root;
        }
        Node found = root;
        Node result = null;
        while (found != null) {
            if (afterNode.equals(found)) {
                result = afterNode.next;
                break;
            }
            found = found.next;
        }
        return result;
    }

    public Node removeNode(Node afterNode, Node deletedNode) {
        if (afterNode == null) {
            if (root != null && root.equals(deletedNode)) {
                root = null;
            }
        } else {
            Node found = root;
            while (found != null) {
                if (afterNode.equals(found) && afterNode.next.equals(deletedNode)) {
                    Node forDelete = afterNode.next;
                    afterNode.next = forDelete.next;
                    forDelete.next = null;
                    break;
                }
                found = found.next;
            }
        }
        return deletedNode;
    }

    private static class Node {
        private Integer val;

        private Node next;

        public Node(Integer val) {
            this.val = val;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Integer getVal() {
            return val;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(val, node.val) && Objects.equals(next, node.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(val, next);
        }
    }
}
