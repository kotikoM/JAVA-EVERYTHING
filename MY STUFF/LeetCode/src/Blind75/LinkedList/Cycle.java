package Blind75.LinkedList;

import java.util.HashSet;

public class Cycle {
    //Given head, the head of a linked list, determine if the linked list has a cycle in it.
    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> visitedNodes = new HashSet<>();

        while (head != null) {
            if (visitedNodes.contains(head)) {
                return true;
            } else {
                visitedNodes.add(head);
            }

            head = head.next;
        }
        return false;
    }
}
