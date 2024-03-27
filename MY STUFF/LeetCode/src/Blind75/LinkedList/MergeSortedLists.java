package Blind75.LinkedList;

public class MergeSortedLists {
//Merge the two lists into one sorted list.
// The list should be made by splicing together
// the nodes of the first two lists.
//Return the head of the merged linked list.
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        }

        if (list1.val <= list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }
}
