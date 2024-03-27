package Blind75.LinkedList;

public class Reverse {
    //Given the head of a singly linked list, reverse the list, and return the reversed list.
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        ListNode next;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }
}
