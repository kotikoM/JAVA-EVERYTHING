package com.epam.rd.autocode.bstprettyprint;

public class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
    }

    public int size() {
        return this.sizeTemp(this);
    }

    private int sizeTemp(Node current){
        if (current == null) {
            return 0;
        }

        return 1 + sizeTemp(current.left) + sizeTemp(current.right);

    }


}