package com.epam.rd.autocode.bstprettyprint;

import java.util.ArrayList;
import java.util.List;

public class PrintableTreeImpl implements PrintableTree {

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
//                                        └2000
    private void toPrettyTree(Node node, List<StringBuilder> lines) {

        //insert the first tree element
        StringBuilder firstLine = new StringBuilder();
        firstLine.append(node.value);

        if (node.left != null && node.right != null) {
            //goes both ways
            firstLine.append("┤");
        } else if (node.right == null) {
            //go left-up
            firstLine.append("┘");
        } else {
            //go right-down
            firstLine.append("┐");
        }

        //add the first line
        lines.add(firstLine);


        //fill in left tree
        if (node.left != null) {
            branchOutLeft(node.left, lines, (node.left.right == null) ? 0 : node.left.right.size());
        }

        //fill in right tree
        if (node.right != null) {
            branchOutRight(node.right, lines, (node.right.left == null) ? 0 : node.right.left.size());
        }

    }

    private void branchOutLeft(Node current, List<StringBuilder> lines, int brakes) {

        //align values correctly
        int spaces = lines.get(0).length() - 1;

        //add brakes "│"
        for (int i = 0; i < brakes; i++) {
            StringBuilder line = new StringBuilder();
            appendSpaces(line, spaces);
            line.append("│");
            lines.add(0, line);
        }

        //add the corner and the value
        StringBuilder line = new StringBuilder();
        appendSpaces(line, spaces);
        line.append("┌").append(current.value);

        //add appropriate pipe
        if (current.left != null && current.right != null) {
            line.append("┤");
        } else if (current.right == null && current.left != null) {
            line.append("┘");
        } else if (current.right != null) {
            line.append("┐");
        }

        //add the line from behind
        lines.add(0, line);

        //decide which direction to go
        if (current.left != null && current.right == null) {
            //going outward left
            branchOutLeft(current.left, lines, (current.left.right == null) ? 0 : current.left.right.size());
        } else if (current.left == null && current.right != null) {
            //going inward right
            branchInRight(current, lines, (current.right.left == null) ? 0 : current.right.left.size());
        } else if (current.left != null) {
            //go both ways
            branchOutLeft(current.left, lines, (current.left.right == null) ? 0 : current.left.right.size());
            branchInRight(current, lines, (current.right.left == null) ? 0 : current.right.left.size());
        }

    }

    private void branchOutRight(Node current, List<StringBuilder> lines, int brakes) {
        //align values correctly
        int spaces = lines.get(lines.size() - 1).length() - 1;

        //add brakes "│"
        for (int i = 0; i < brakes; i++) {
            StringBuilder line = new StringBuilder();
            appendSpaces(line, spaces);
            line.append("│");
            lines.add(line);
        }

        //add the corner and the value
        StringBuilder line = new StringBuilder();
        appendSpaces(line, spaces);
        line.append("└").append(current.value);

        //add appropriate pipe
        if (current.left != null && current.right != null) {
            line.append("┤");
        } else if (current.right == null && current.left != null) {
            line.append("┘");
        } else if (current.right != null) {
            line.append("┐");
        }

        //add the line in the front
        lines.add(line);

        //decide which direction to go
        if (current.left == null && current.right != null) {
            //go outward right
            branchOutRight(current.right, lines, (current.right.left == null) ? 0 : current.right.left.size());
        } else if (current.left != null && current.right == null) {
            //go inward left
            branchInLeft(current, lines, (current.left.right == null) ? 0 : current.left.right.size());
        } else if (current.left != null) {
            //go both ways
            branchOutRight(current.right, lines, (current.right.left == null) ? 0 : current.right.left.size());
            branchInLeft(current, lines, (current.left.right == null) ? 0 : current.left.right.size());
        }

    }

    private void branchInLeft(Node previous, List<StringBuilder> lines, int brakes) {
        int callValue = previous.value;
        int leftValue = previous.left.value;
        int index = -1;


        //find the line that called the function
        for (int i = 0; i < lines.size(); i++) {
            String trimedLine = lines.get(i).toString().trim();
            if (!trimedLine.endsWith("│")) {
                int value = extractValueFromLine(trimedLine);

                if (value == callValue) {
                    index = i;
                }
            }
        }

        int lineLengthToMatch = lines.get(index).length();

        while (brakes != 0) {
            index--;

            StringBuilder lineBeforeCallValue = lines.get(index);
            appendSpaces(lineBeforeCallValue, (lineLengthToMatch - lineBeforeCallValue.length() - 1));
            lineBeforeCallValue.append("│");
            lines.set(index, lineBeforeCallValue);

            brakes--;
        }

        //line to add leftValue
        index--;
        StringBuilder line = lines.get(index);
        appendSpaces(line, (lineLengthToMatch - line.length() - 1));
        line.append("┌").append(leftValue);

        //add appropriate corner
        if (previous.left.right != null && previous.left.left != null) {
            line.append("┤");
        } else if (previous.left.right == null && previous.left.left != null) {
            line.append("┘");
        } else if (previous.left.right != null) {
            line.append("┐");
        }

        //add the updated line
        lines.set(index, line);

        //decide which way to go
        if (previous.left.right == null && previous.left.left != null) {
            //go in left
            branchInLeft(previous.left, lines, (previous.left.left.right == null) ? 0 : previous.left.left.right.size());
        } else if (previous.left.right != null && previous.left.left == null) {
            //go in right
            branchInRight(previous.left, lines, (previous.left.right.left == null) ? 0 : previous.left.right.left.size());
        } else if (previous.left.right != null) {
            //go both ways
            branchInLeft(previous.left, lines, (previous.left.left.right == null) ? 0 : previous.left.left.right.size());
            branchInRight(previous.left, lines, (previous.left.right.left == null) ? 0 : previous.left.right.left.size());
        }
    }

    private void branchInRight(Node previous, List<StringBuilder> lines, int brakes) {
        int callValue = previous.value;
        int rightValue = previous.right.value;
        int index = -1;


        //find the line that called the function
        for (int i = 0; i < lines.size(); i++) {
            String trimedLine = lines.get(i).toString().trim();
            if (!trimedLine.endsWith("│")) {
                int value = extractValueFromLine(trimedLine);

                if (value == callValue) {
                    index = i;
                }
            }
        }

        int lineLengthToMatch = lines.get(index).length();

        while (brakes != 0) {
            index++;

            StringBuilder lineAfterCallValue = lines.get(index);
            appendSpaces(lineAfterCallValue, (lineLengthToMatch - lineAfterCallValue.length() - 1));
            lineAfterCallValue.append("│");
            lines.set(index, lineAfterCallValue);

            brakes--;
        }

        //line to add rightValue
        index++;
        StringBuilder line = lines.get(index);
        appendSpaces(line, (lineLengthToMatch - line.length() - 1));
        line.append("└").append(rightValue);

        //add appropriate corner
        if (previous.right.left != null && previous.right.right != null) {
            line.append("┤");
        } else if (previous.right.right == null && previous.right.left != null) {
            line.append("┘");
        } else if (previous.right.right != null) {
            line.append("┐");
        }

        //add the updated line
        lines.set(index, line);

        //decide which way to go
        if (previous.right.left != null && previous.right.right == null) {
            //go in left
            branchInLeft(previous.right, lines, (previous.right.left.right == null) ? 0 : previous.right.left.right.size());
        } else if (previous.right.left == null && previous.right.right != null) {
            //go in right
            branchInRight(previous.right, lines, (previous.right.right.left == null) ? 0 : previous.right.right.left.size());
        } else if (previous.right.left != null) {
            //go both ways
            branchInLeft(previous.right, lines, (previous.right.left.right == null) ? 0 : previous.right.left.right.size());
            branchInRight(previous.right, lines, (previous.right.right.left == null) ? 0 : previous.right.right.left.size());
        }
    }

    private int extractValueFromLine(String line) {
        //string has a from of ... "corner" + value + "corner"
        //this function extracts the value

        String corner1 = "┘";
        String corner2 = "└";
        String corner3 = "┐";
        String corner4 = "┌";

        //remove last corner
        if (line.endsWith(corner1) || line.endsWith(corner2) || line.endsWith(corner3) || line.endsWith(corner4) || line.endsWith("┤")) {
            line = line.substring(0, line.length() - 1);
        }

        //find the index of the left corner
        //to isolate the value

        int cornerIndex = -1;

        if (line.contains(corner1)) {
            cornerIndex = line.indexOf(corner1);
        } else if (line.contains(corner2)) {
            cornerIndex = line.indexOf(corner2);
        } else if (line.contains(corner3)) {
            cornerIndex = line.indexOf(corner3);
        } else if (line.contains(corner4)) {
            cornerIndex = line.indexOf(corner4);
        }

        //isolate value
        line = line.substring(cornerIndex + 1);

        return Integer.parseInt(line);
    }

    private String linesToString(List<StringBuilder> lines) {
        StringBuilder prettyTree = new StringBuilder();

        for (StringBuilder line : lines) {
            prettyTree.append(line).append("\n");
        }

        return prettyTree.toString();
    }

    private void appendSpaces(StringBuilder line, int count) {
        for (int i = 0; i < count; i++) {
            line.append(" ");
        }
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

    public static void main(String[] args) {
        PrintableTreeImpl tree = new PrintableTreeImpl();

        int[] elements1 = {901, 292, 247, 997, 457, 468, 216, 82, 530, 524, 793, 952, 730, 764, 820, 460, 424};

        int[] elements2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10};

        for (int element : elements2) {
            tree.add(element);
        }

        System.out.println(tree.toString(tree.root));

        System.out.println(tree.size(tree.root));

        System.out.println(tree.prettyPrint());
    }
}
