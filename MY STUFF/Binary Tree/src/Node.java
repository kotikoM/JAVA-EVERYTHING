import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

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
                Integer newValue = getMin(currentNode.getRightNode());
                delete(currentNode.getRightNode(), currentNode, newValue);
                currentNode.setValue(newValue);
            }
        }


    }

    public boolean isLeftChild(Node parent, Node child) {
        return (parent.getLeftNode() == child);
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

    public List<Node> getChildren(){
        if (isLeaf(this.rightNode) && isLeaf(this.leftNode)) {
            //no child
            return new ArrayList<>();
        } else if (isLeaf(this.leftNode)) {
            //only right child
            return List.of(this.rightNode);
        } else if (isLeaf(this.rightNode)) {
            //only left child
            return List.of(this.leftNode);
        } else {
            return List.of(this.leftNode, this.rightNode);
        }
    }



    //PRETTY PRINT METHODS------------------------------------------------------
    public String prettyPrint(Node root) {
        List<StringBuilder> lines = new ArrayList<>();
        constructPrettyTree(root, 0, lines);
        return String.join(System.lineSeparator(), lines);
    }

    private void constructPrettyTree(Node currentNode, int level, List<StringBuilder> lines){
        StringBuilder line = new StringBuilder();

        //append cosmetics
        line.append("Lvl ")
                .append(level)
                .append(" ->");


    }


    private String getSpaces(Node currentNode){

        return "  ";
    }

}
