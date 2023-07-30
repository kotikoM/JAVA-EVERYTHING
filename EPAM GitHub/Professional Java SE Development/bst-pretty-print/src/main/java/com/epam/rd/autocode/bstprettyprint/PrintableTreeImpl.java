package com.epam.rd.autocode.bstprettyprint;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class PrintableTreeImpl implements PrintableTree {

    private static final String STRAIGHT_PIPE = "│";
    private static final String MIDDLE_PIPE = "┤";
    private static final String BEFORE_LEFT_PIPE = "┌";
    private static final String BEFORE_RIGHT_PIPE = "└";
    private static final String AFTER_LEFT_PIPE = "┘";
    private static final String AFTER_RIGHT_PIPE = "┐";
    private Node root;

    @Override
    public void add(int i) {
        this.root = insert(this.root, i);
    }

    public Node insert(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }

        //go left or right
        if (value < current.value) {
            //left
            current.left = insert(current.left, value);
        } else if (value > current.value) {
            current.right = insert(current.right, value);
        }

        return current;
    }

    @Override
    public String prettyPrint() {
        List<StringBuilder> lines = new ArrayList<>();
        toPrettyTree(root, lines);
        return linesToString(lines);
    }

    // Example:
//                                       ┌1
//                                    ┌11┤
//                                    │  └100
//                                 123┤
//                                    │   ┌150
//                                    └200┤
//                                        └2000s
    private void toPrettyTree(Node node, List<StringBuilder> lines) {
        //insert the first tree element
        StringBuilder firstLine = new StringBuilder();
        firstLine.append(node.value)
                .append(getIndent(node));

        //add the first line
        lines.add(firstLine);

        //fill in left tree
        if (node.left != null) {
            branchOutLeft(node.left, lines);
        }

        //fill in right tree
        if (node.right != null) {
            branchOutRight(node.right, lines);
        }

    }

    private void branchOutLeft(Node currentNode, List<StringBuilder> lines){
        //align values correctly
        int spaces = lines.get(0).length() - 1;
        int brakes = (isLeaf(currentNode.right)) ? 0 : size(currentNode.right);

        //add brakes "│"
        for (int i = 0; i < brakes; i++) {
            StringBuilder line = new StringBuilder();
            appendSpaces(line, spaces);
            line.append(STRAIGHT_PIPE);
            lines.add(0, line);
        }

        //add the corner and the value
        StringBuilder line = new StringBuilder();
        appendSpaces(line, spaces);
        line.append(BEFORE_LEFT_PIPE).append(currentNode.value)
                .append(getIndent(currentNode));

        //add the line from behind
        lines.add(0, line);

        int index = 0;

        //decide which direction to go
        if (currentNode.left != null && currentNode.right == null) {
            //going outward left
            branchOutLeft(currentNode.left, lines);
        } else if (currentNode.left == null && currentNode.right != null) {
            //going inward right
            branchInRight(currentNode.right, lines, index);
        } else if (currentNode.left != null) {
            //go both ways
            branchInRight(currentNode.right, lines, index);
            branchOutLeft(currentNode.left, lines);
        }
    }

    private void branchOutRight(Node currentNode, List<StringBuilder> lines){
        //align values correctly
        int spaces = lines.get(lines.size() - 1).length() - 1;
        int brakes = (isLeaf(currentNode.left)) ? 0 : size(currentNode.left);

        //add brakes "│"
        for (int i = 0; i < brakes; i++) {
            StringBuilder line = new StringBuilder();
            appendSpaces(line, spaces);
            line.append(STRAIGHT_PIPE);
            lines.add(line);
        }

        //add the corner and the value
        StringBuilder line = new StringBuilder();
        appendSpaces(line, spaces);
        line.append(BEFORE_RIGHT_PIPE).append(currentNode.value)
                .append(getIndent(currentNode));

        //add the line in the front
        lines.add(line);

        int index = lines.size() - 1;

        //decide which direction to go
        if (currentNode.left == null && currentNode.right != null) {
            //go outward right
            branchOutRight(currentNode.right, lines);
        } else if (currentNode.left != null && currentNode.right == null) {
            //go inward left
            branchInLeft(currentNode.left, lines, index);
        } else if (currentNode.left != null) {
            //go both ways
            branchInLeft(currentNode.left, lines,  index);
            branchOutRight(currentNode.right, lines);
        }
    }

    private void branchInLeft(Node currentNode, List<StringBuilder> lines, int callIndex){
        int index = callIndex;
        int brakes = (isLeaf(currentNode.right)) ? 0 : size(currentNode.right);
        int lengthToMatch = lines.get(index).length();

        //insert brakes "|"
        for (int i = 0; i < brakes; i++) {
            index--;

            StringBuilder currentLine = lines.get(index);
            appendSpaces(currentLine, (lengthToMatch - currentLine.length() - 1));
            currentLine.append(STRAIGHT_PIPE);
            lines.set(index, currentLine);
        }

        //line to add leftValue
        index--;
        StringBuilder line = lines.get(index);
        appendSpaces(line, (lengthToMatch - line.length() - 1));
        line.append(BEFORE_LEFT_PIPE).append(currentNode.value)
                .append(getIndent(currentNode));


        //decide which way to go
        if (currentNode.right == null && currentNode.left != null) {
            //go in left
            branchInLeft(currentNode.left, lines, index);
        } else if (currentNode.right != null && currentNode.left == null) {
            //go in right
            branchInRight(currentNode.right, lines, index);
        } else if (currentNode.right != null) {
            //go both ways
            branchInLeft(currentNode.left, lines, index);
            branchInRight(currentNode.right, lines, index);
        }
    }

    private void branchInRight(Node currentNode, List<StringBuilder> lines, int callIndex){
        int index = callIndex;
        int brakes = (isLeaf(currentNode.left)) ? 0 : size(currentNode.left);
        int lengthToMatch = lines.get(index).length();

        //insert brakes "|"
        for (int i = 0; i < brakes; i++) {
            index++;

            StringBuilder currentLine = lines.get(index);
            appendSpaces(currentLine, (lengthToMatch - currentLine.length() - 1));
            currentLine.append(STRAIGHT_PIPE);
            lines.set(index, currentLine);
        }

        //line to add leftValue
        index++;
        StringBuilder line = lines.get(index);
        appendSpaces(line, (lengthToMatch - line.length() - 1));
        line.append(BEFORE_RIGHT_PIPE).append(currentNode.value)
                .append(getIndent(currentNode));

        //decide which way to go
        if (currentNode.left != null && currentNode.right == null) {
            //go in left
            branchInLeft(currentNode.left, lines, index);
        } else if (currentNode.left == null && currentNode.right != null) {
            //go in right
            branchInRight(currentNode.right, lines, index);
        } else if (currentNode.left != null) {
            //go both ways
            branchInLeft(currentNode.left, lines, index);
            branchInRight(currentNode.right, lines, index);
        }
    }

    private String getIndent(Node node){
        if (node.left != null && node.right != null) {
            //goes both ways
            return (MIDDLE_PIPE);
        } else if (node.right == null && node.left!= null) {
            //go left-up
            return (AFTER_LEFT_PIPE);
        } else if (node.right != null) {
            //go right-down
            return (AFTER_RIGHT_PIPE);
        }

        return "";
    }

    private String linesToString(List<StringBuilder> lines) {
        StringBuilder prettyTree = new StringBuilder();

        for (StringBuilder line : lines) {
            prettyTree.append(line).append("\n");
        }

        return prettyTree.toString();
    }

    private void appendSpaces(StringBuilder line, int count) {
        line.append(" ".repeat(Math.max(0, count)));
    }

    public String toString(Node current) {
        if (current == null) {
            return "";
        }

        return current.value + " " + toString(current.left) + " " + toString(current.right);
    }

    public int size(Node current) {
        return current.size();
    }

    private boolean isLeaf(Node node){
        return node == null;
    }

    public static void main(String[] args) {
        PrintableTreeImpl tree = new PrintableTreeImpl();

        int[] elements1 = {901, 292, 247, 997, 457, 468, 216, 82, 530, 524, 793, 952, 730, 764, 820, 460, 424};

        int[] elements2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10};
        int[] elements3 = {405, 704, 320, 152, 230, 44, 52, 979, 781, 71, 881, 515, 170, 928,
                753, 437, 237, 522, 208, 9, 87, 157, 689, 5, 143, 345, 699, 386, 726, 650, 171, 229, 56, 615, 98};


        for (int elem : elements3) {
            tree.add(elem);
        }


        long start = System.currentTimeMillis();

        System.out.println(tree.prettyPrint());

        long end = System.currentTimeMillis();

        System.out.println("it took " + (end - start) + " ms");
    }
}
