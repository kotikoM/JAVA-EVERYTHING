public class MergeSortLoops {
    public static LinkedNode mergeSort(LinkedNode lst) {
        if (lst == null || lst.nextNode == null) {
            return lst;
        }

        int size = size(lst);
        int middle = size / 2;

        LinkedNode prev = null;
        LinkedNode current = lst;

        for (int i = 0; i < middle; i++) {
            prev = current;
            current = current.nextNode;
        }

        prev.nextNode = null;

        LinkedNode leftList = mergeSort(lst);
        LinkedNode rightList = mergeSort(current);

        return merge(leftList, rightList);
    }

    public static LinkedNode merge(LinkedNode left, LinkedNode right) {
        LinkedNode headPointer = new LinkedNode();
        LinkedNode current = headPointer;

        while (left != null && right != null) {
            if (left.value <= right.value) {
                current.nextNode = left;
                left = left.nextNode;
            } else {
                current.nextNode = right;
                right = right.nextNode;
            }
            current = current.nextNode;
        }

        if (left != null) {
            current.nextNode = left;
        } else {
            current.nextNode = right;
        }

        return headPointer.nextNode;
    }

    public static int size(LinkedNode lst) {
        int count = 0;

        while (lst != null) {
            lst = lst.nextNode;
            count++;
        }
        return count;
    }

    public static String toString(LinkedNode node) {
        if (node == null) {
            return "";
        }
        return node.value + " " + toString(node.nextNode);
    }

    public static void main(String[] args) {
        LinkedNode lst = new LinkedNode(
                100, new LinkedNode(
                90, new LinkedNode(
                50, new LinkedNode(
                50, new LinkedNode(
                60)))));

        LinkedNode lst1 = new LinkedNode(
                20, new LinkedNode(
                60));

        LinkedNode lst2 = new LinkedNode(
                10, new LinkedNode(
                30, new LinkedNode(
                40)));


//        String res = toString(
//                merge(lst1, lst2));
//        String res = "" + size(lst);

        String res = toString(
                mergeSort(lst)
        );

        System.out.println(res);
    }
}
