

public class MergeSortRecursion {
    public static LinkedNode mergeSort(LinkedNode lst){
        if (lst == null || lst.nextNode == null) {
            return lst;
        }
        int middle = size(lst) / 2;

        LinkedNode rightList = copyRightList(lst, middle);
        LinkedNode leftList = copyLeftList(lst, middle);

        rightList = mergeSort(rightList);
        leftList = mergeSort(leftList);


        return merge(leftList, rightList);
    }

    public static LinkedNode merge(LinkedNode left, LinkedNode right){
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        if (left.value <= right.value) {
            left.nextNode = merge(left.nextNode, right);
            return left;
        } else {
            right.nextNode = merge(left, right.nextNode);
            return right;
        }
    }

    public static LinkedNode copyLeftList(LinkedNode lst, int size){
        if (size == 0) {
            return null;
        }
        lst.nextNode = copyLeftList(lst.nextNode, size - 1);
        return lst;
    }

    public static LinkedNode copyRightList(LinkedNode lst, int start){
        if (start == 0) {
            return copyLeftList(lst, size(lst));
        }
        return copyRightList(lst.nextNode, start - 1);
    }

    public static int size(LinkedNode lst){
        if (lst == null) {
            return 0;
        }
        return 1 + size(lst.nextNode);
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
//        String res = toString(
//                copyLeftList(lst, 3)
//        ) + "\n" + toString(
//                copyRightList(lst, 3)
//        );

        String res = toString(
                mergeSort(lst)
        );

        System.out.println(res);
    }
}
