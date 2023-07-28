import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@SuppressWarnings("unused")
public class Node {
    private Node leftNode;
    private Integer value;
    private Node rightNode;

    public Node(Node leftNode, Integer value, Node rightNode) {
        this.leftNode = leftNode;
        this.value = value;
        this.rightNode = rightNode;
    }

    public Node(Integer value) {
        this.value = value;
    }

    public void insert(Integer insertValue) {
        if (!Objects.equals(insertValue, value)) {
            if (insertValue > value) {
                if (isLeaf(rightNode)) {
                    this.setRightNode(new Node(insertValue));
                } else {
                    rightNode.insert(insertValue);
                }
            } else {
                if (isLeaf(leftNode)) {
                    this.setLeftNode(new Node(insertValue));
                } else {
                    leftNode.insert(insertValue);
                }
            }
        }
    }

    public Node search(Node currentNode, int value) {

        if (currentNode.value == value) {
            return currentNode;
        }

        //value is on the right
        if (value > currentNode.value) {
            if (isLeaf(currentNode.rightNode)) {
                raiseException(value);
            } else {
                return search(currentNode.rightNode, value);
            }
        }

        //value is on the left
        if (isLeaf(currentNode.leftNode)) {
            raiseException(value);
        } else {
            return search(currentNode.leftNode, value);
        }

        return null;
    }

    public int size(Node node) {
        return 1 +
                (isLeaf(node.leftNode) ? 0 : size(node.leftNode)) +
                (isLeaf(node.rightNode) ? 0 : size(node.rightNode));
    }

    public List<Integer> getContent(Node node) {
        List<Integer> contentList = new ArrayList<>();
        inOrderTraversal(node, contentList);
        return contentList;
    }

    public void inOrderTraversal(Node currentNode, List<Integer> content) {
        if (!isLeaf(currentNode)) {
            inOrderTraversal(currentNode.leftNode, content);
            content.add(currentNode.value);
            inOrderTraversal(currentNode.rightNode, content);
        }
    }


    public void delete(Node currentNode, Node parent, Integer value) {
        if (currentNode == null) {
            return;
        }

        //decide which way to go
        if (value > currentNode.getValue()) {
            //go right
            delete(currentNode.getRightNode(), currentNode, value);
        } else if (value < currentNode.getValue()) {
            //go left
            delete(currentNode.getLeftNode(), currentNode, value);
        }

        boolean isLeftChild = isLeftChild(parent, currentNode);

        if (Objects.equals(currentNode.getValue(), value)) {
            if (isLeaf(currentNode.getRightNode()) && isLeaf(currentNode.getLeftNode())) {
                //no children case
                if (isLeftChild) {
                    parent.setLeftNode(null);
                } else {
                    parent.setRightNode(null);
                }
            } else if (isLeaf(currentNode.getRightNode())) {
                //only left child case
                if (isLeftChild) {
                    parent.setLeftNode(currentNode.getLeftNode());
                } else {
                    parent.setRightNode(currentNode.getLeftNode());
                }
            } else if (isLeaf(currentNode.getLeftNode())) {
                //only right child case
                if (isLeftChild) {
                    parent.setLeftNode(currentNode.getRightNode());
                } else {
                    parent.setRightNode(currentNode.getRightNode());
                }
            } else {
                //both children case
                if (isLeftChild) {
                    Integer newValue = getMin(currentNode.getRightNode());
                    delete(currentNode.getRightNode(), currentNode, newValue);
                    currentNode.setValue(newValue);
                } else {
                    Integer newValue = getMin(currentNode.getRightNode());
                    delete(currentNode.getRightNode(), currentNode, newValue);
                    currentNode.setValue(newValue);
                }
            }
        }


    }

    public boolean isLeftChild(Node parent, Node child) {
        return (parent == null) ? true : (parent.getLeftNode() == child);
    }

    public boolean isRightChild(Node parent, Node child) {
        return (parent.getRightNode() == child);
    }

    public Integer getMax(Node currentNode) {
        return (isLeaf(currentNode.getRightNode())) ?
                currentNode.getValue() : getMax(currentNode.getRightNode());
    }

    public Integer getMin(Node currentNode) {
        return (isLeaf(currentNode.getLeftNode())) ?
                currentNode.getValue() : getMin(currentNode.getLeftNode());
    }

    public Node getMaxNode(Node currentNode) {
        return (isLeaf(currentNode.getRightNode())) ?
                currentNode : getMaxNode(currentNode.getRightNode());
    }

    public Node getMinNode(Node currentNode) {
        return (isLeaf(currentNode.getLeftNode())) ?
                currentNode : getMinNode(currentNode.getLeftNode());
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    public String toString() {
        return this.value + " " +
                ((isLeaf(leftNode)) ? " " : leftNode.toString()) +
                ((isLeaf(rightNode)) ? " " : rightNode.toString());
    }

    private void raiseException(int value) {
        throw new NoSuchElementException(String.format("Value: %d is not in the tree", value));
    }

    public boolean isLeaf(Node node) {
        return node == null;
    }


    //PRETTY PRINT METHODS------------------------------------------------------
    public String prettyPrint(Node root) {
        // Example:
//                                       ┌1
//                                    ┌11┤
//                                    │  └100
//                                 123┤
//                                    │   ┌150
//                                    └200┤
//                                        └2000
        //add root

        List<StringBuilder> lines = new ArrayList<>();
        toPrettyTree(root, lines);
        return String.join(System.lineSeparator(), lines);
    }

    private void toPrettyTree(Node currentNode, List<StringBuilder> lines){
        //insert the first tree element
        StringBuilder firstLine = new StringBuilder();
        firstLine.append(currentNode.value)
                .append(getIndent(currentNode));

        //add the first line
        lines.add(firstLine);

        //fill in left tree
        if (currentNode.leftNode != null) {
            branchOutLeft(currentNode.leftNode, lines, (currentNode.leftNode.rightNode == null) ? 0 : size(currentNode.leftNode.rightNode));
        }

        //fill in right tree
        if (currentNode.rightNode != null) {
            branchOutRight(currentNode.rightNode, lines, (currentNode.rightNode.leftNode == null) ? 0 : size(currentNode.rightNode.leftNode));
        }

    }
    private void branchOutLeft(Node currentNode, List<StringBuilder> lines, int brakes){
        //align values correctly
        int spaces = lines.get(0).length() - 1;

        //add brakes "│"
        for (int i = 0; i < brakes; i++) {
            StringBuilder line = new StringBuilder();
            appendSpaces(line, spaces);
            line.append("│");
            lines.add(0, line);
        }

        StringBuilder line = new StringBuilder();
        appendSpaces(line, spaces);
        line.append("┌")
                .append(currentNode.value)
                .append(getIndent(currentNode));

        //add the line from behind
        lines.add(0, line);

        //decide which direction to go
        if (currentNode.leftNode != null && currentNode.rightNode == null) {
            //going outward left
            branchOutLeft(currentNode.leftNode, lines, (currentNode.leftNode.rightNode == null) ? 0 : size(currentNode.leftNode.rightNode));
        } else if (currentNode.leftNode == null && currentNode.rightNode != null) {
            //going inward right
            branchInRight(currentNode, lines, (currentNode.rightNode.leftNode == null) ? 0 : size(currentNode.rightNode.leftNode));
        } else if (currentNode.leftNode != null) {
            //go both ways
            branchOutLeft(currentNode.leftNode, lines, (currentNode.leftNode.rightNode == null) ? 0 : size(currentNode.leftNode.rightNode));
            branchInRight(currentNode, lines, (currentNode.rightNode.leftNode == null) ? 0 : size(currentNode.rightNode.leftNode));
        }

    }

    private void branchOutRight(Node currentNode, List<StringBuilder> lines, int brakes){
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
        line.append("└")
                .append(currentNode.value)
                .append(getIndent(currentNode));

        //add the line in the front
        lines.add(line);

        //decide which direction to go
        if (currentNode.leftNode == null && currentNode.rightNode != null) {
            //go outward right
            branchOutRight(currentNode.rightNode, lines, (currentNode.rightNode.leftNode == null) ? 0 : size(currentNode.rightNode.leftNode));
        } else if (currentNode.leftNode != null && currentNode.rightNode == null) {
            //go inward left
            branchInLeft(currentNode, lines, (currentNode.leftNode.rightNode == null) ? 0 : size(currentNode.leftNode.rightNode));
        } else if (currentNode.leftNode != null) {
            //go both ways
            branchOutRight(currentNode.rightNode, lines, (currentNode.rightNode.leftNode == null) ? 0 : size(currentNode.rightNode.leftNode));
            branchInLeft(currentNode, lines, (currentNode.leftNode.rightNode == null) ? 0 : size(currentNode.leftNode.rightNode));
        }
    }

    private void branchInLeft(Node previous, List<StringBuilder> lines, int brakes){
        int callValue = previous.value;
        int leftValue = previous.leftNode.value;
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
        line.append("┌").append(leftValue).append(getIndent(previous.leftNode));

        lines.set(index, line);

        //decide which way to go
        if (previous.leftNode.rightNode == null && previous.leftNode.leftNode != null) {
            //go in left
            branchInLeft(previous.leftNode, lines, (previous.leftNode.leftNode.rightNode == null) ? 0 : size(previous.leftNode.leftNode.rightNode));
        } else if (previous.leftNode.rightNode != null && previous.leftNode.leftNode == null) {
            //go in right
            branchInRight(previous.leftNode, lines, (previous.leftNode.rightNode.leftNode == null) ? 0 :size( previous.leftNode.rightNode.leftNode));
        } else if (previous.leftNode.rightNode != null) {
            //go both ways
            branchInLeft(previous.leftNode, lines, (previous.leftNode.leftNode.rightNode == null) ? 0 : size(previous.leftNode.leftNode.rightNode));
            branchInRight(previous.leftNode, lines, (previous.leftNode.rightNode.leftNode == null) ? 0 : size(previous.leftNode.rightNode.leftNode));
        }
    }

    private void branchInRight(Node previous, List<StringBuilder> lines, int brakes){
        int callValue = previous.value;
        int rightValue = previous.rightNode.value;
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
        line.append("└").append(rightValue).append(getIndent(previous.rightNode));

        lines.set(index, line);

        //decide which way to go
        if (previous.rightNode.leftNode != null && previous.rightNode.rightNode == null) {
            //go in left
            branchInLeft(previous.rightNode, lines, (previous.rightNode.leftNode.rightNode == null) ? 0 : size(previous.rightNode.leftNode.rightNode));
        } else if (previous.rightNode.leftNode == null && previous.rightNode.rightNode != null) {
            //go in right
            branchInRight(previous.rightNode, lines, (previous.rightNode.rightNode.leftNode == null) ? 0 : size(previous.rightNode.rightNode.leftNode));
        } else if (previous.rightNode.leftNode != null) {
            //go both ways
            branchInLeft(previous.rightNode, lines, (previous.rightNode.leftNode.rightNode == null) ? 0 : size(previous.rightNode.leftNode.rightNode));
            branchInRight(previous.rightNode, lines, (previous.rightNode.rightNode.leftNode == null) ? 0 : size(previous.rightNode.rightNode.leftNode));
        }
    }

    private String getIndent(Node node){
        if (node.leftNode != null && node.rightNode != null) {
            //goes both ways
            return ("┤");
        } else if (node.rightNode == null && node.leftNode!=null) {
            //go left-up
            return ("┘");
        } else if (node.rightNode != null && node.leftNode == null) {
            //go right-down
            return ("┐");
        }

        return "";
    }
    private void appendSpaces(StringBuilder line, int count) {
        line.append(" ".repeat(Math.max(0, count)));
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
}
