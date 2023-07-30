import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
public class BinaryTree {

    private static final String STRAIGHT_PIPE = "│";
    private static final String MIDDLE_PIPE = "┤";
    private static final String BEFORE_LEFT_PIPE = "┌";
    private static final String BEFORE_RIGHT_PIPE = "└";
    private static final String AFTER_LEFT_PIPE = "┘";
    private static final String AFTER_RIGHT_PIPE = "┐";
    private Node root;

    public BinaryTree(Node root) {
        this.root = root;
    }

    public BinaryTree() {
        this.root = null;
    }

    public void insert(Integer value){
        if ((root == null)) {
            this.root = new Node(value);
        } else {
            root.insert(value);
        }
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public String toString(){
        return root.toString();
    }


    public int size(){
        return root.size(root);
    }
    public int size(Node node){return node.size(node);}

    public Node search(int value){
        return root.search(root, value);
    }

    public List<Integer> getContent(){
        return root.getContent(root);
    }

    public void delete(Integer value) {
        //special cases when trying to delete root
        if (Objects.equals(value, root.getValue())) {
            if (root.getRightNode() == null && root.getLeftNode() == null) {
                root = null;
                return;
            } else if (root.getRightNode() != null && root.getLeftNode() == null) {
                //only right tree
                this.root = root.getRightNode();
                return;
            } else if (root.getRightNode() == null && root.getLeftNode() != null) {
                //only left tree
                this.root = root.getLeftNode();
                return;
            } else {
                //both children case
                Integer newValue = root.getMin(root.getRightNode());
                root.delete(root.getRightNode(), root, newValue);
                root.setValue(newValue);
                return;
            }
        }

        root.delete(root, null, value);
    }

    public Integer getMax(){
        return root.getMax(root);
    }

    public Integer getMin(){
        return root.getMin(root);
    }

    public Node getMaxNode(){
        return root.getMaxNode(root);
    }

    public Node getMinNode(){
        return root.getMinNode(root);
    }

    public String prettyPrint(){
        return prettyPrint(root);
    }

    //PRETTY PRINT METHODS------------------------------------------------------
// Example:
//                                       ┌1
//                                    ┌11┤
//                                    │  └100
//                                 123┤
//                                    │   ┌150
//                                    └200┤
//                                        └2000s
    public String prettyPrint(Node root) {
        List<StringBuilder> lines = new ArrayList<>();

        //insert the first tree element
        StringBuilder firstLine = new StringBuilder();
        firstLine.append(root.getValue())
                .append(getIndent(root));

        //add the first line
        lines.add(firstLine);

        //fill in left tree
        if (root.getLeftNode() != null) {
            branchOutLeft(root.getLeftNode(), lines);
        }

        //fill in right tree
        if (root.getRightNode() != null) {
            branchOutRight(root.getRightNode(), lines);
        }

        return String.join(System.lineSeparator(), lines);
    }

    private void branchOutLeft(Node currentNode, List<StringBuilder> lines){
        //align values correctly
        int spaces = lines.get(0).length() - 1;
        int brakes = (isLeaf(currentNode.getRightNode())) ? 0 : size(currentNode.getRightNode());

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
        line.append(BEFORE_LEFT_PIPE).append(currentNode.getValue())
                .append(getIndent(currentNode));

        //add the line from behind
        lines.add(0, line);

        int index = 0;

        //decide which direction to go
        if (currentNode.getLeftNode() != null && currentNode.getRightNode() == null) {
            //going outward left
            branchOutLeft(currentNode.getLeftNode(), lines);
        } else if (currentNode.getLeftNode() == null && currentNode.getRightNode() != null) {
            //going inward right
            branchInRight(currentNode.getRightNode(), lines, index);
        } else if (currentNode.getLeftNode() != null) {
            //go both ways
            branchInRight(currentNode.getRightNode(), lines, index);
            branchOutLeft(currentNode.getLeftNode(), lines);
        }
    }

    private void branchOutRight(Node currentNode, List<StringBuilder> lines){
        //align values correctly
        int spaces = lines.get(lines.size() - 1).length() - 1;
        int brakes = (isLeaf(currentNode.getLeftNode())) ? 0 : size(currentNode.getLeftNode());

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
        line.append(BEFORE_RIGHT_PIPE).append(currentNode.getValue())
                .append(getIndent(currentNode));

        //add the line in the front
        lines.add(line);

        int index = lines.size() - 1;

        //decide which direction to go
        if (currentNode.getLeftNode() == null && currentNode.getRightNode() != null) {
            //go outward right
            branchOutRight(currentNode.getRightNode(), lines);
        } else if (currentNode.getLeftNode() != null && currentNode.getRightNode() == null) {
            //go inward left
            branchInLeft(currentNode.getLeftNode(), lines, index);
        } else if (currentNode.getLeftNode() != null) {
            //go both ways
            branchInLeft(currentNode.getLeftNode(), lines,  index);
            branchOutRight(currentNode.getRightNode(), lines);
        }
    }

    private void branchInLeft(Node currentNode, List<StringBuilder> lines, int callIndex){
        int index = callIndex;
        int brakes = (isLeaf(currentNode.getRightNode())) ? 0 : size(currentNode.getRightNode());
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
        line.append(BEFORE_LEFT_PIPE).append(currentNode.getValue())
                .append(getIndent(currentNode));


        //decide which way to go
        if (currentNode.getRightNode() == null && currentNode.getLeftNode() != null) {
            //go in left
            branchInLeft(currentNode.getLeftNode(), lines, index);
        } else if (currentNode.getRightNode() != null && currentNode.getLeftNode() == null) {
            //go in right
            branchInRight(currentNode.getRightNode(), lines, index);
        } else if (currentNode.getRightNode() != null) {
            //go both ways
            branchInLeft(currentNode.getLeftNode(), lines, index);
            branchInRight(currentNode.getRightNode(), lines, index);
        }
    }

    private void branchInRight(Node currentNode, List<StringBuilder> lines, int callIndex){
        int index = callIndex;
        int brakes = (isLeaf(currentNode.getLeftNode())) ? 0 : size(currentNode.getLeftNode());
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
        line.append(BEFORE_RIGHT_PIPE).append(currentNode.getValue())
                .append(getIndent(currentNode));

        //decide which way to go
        if (currentNode.getLeftNode() != null && currentNode.getRightNode() == null) {
            //go in left
            branchInLeft(currentNode.getLeftNode(), lines, index);
        } else if (currentNode.getLeftNode() == null && currentNode.getRightNode() != null) {
            //go in right
            branchInRight(currentNode.getRightNode(), lines, index);
        } else if (currentNode.getLeftNode() != null) {
            //go both ways
            branchInLeft(currentNode.getLeftNode(), lines, index);
            branchInRight(currentNode.getRightNode(), lines, index);
        }
    }
    private void appendSpaces(StringBuilder line, int count) {
        line.append(" ".repeat(Math.max(0, count)));
    }

    private String getIndent(Node node){
        if (node.getLeftNode() != null && node.getRightNode() != null) {
            //goes both ways
            return (MIDDLE_PIPE);
        } else if (node.getRightNode() == null && node.getLeftNode()!= null) {
            //go left-up
            return (AFTER_LEFT_PIPE);
        } else if (node.getRightNode() != null) {
            //go right-down
            return (AFTER_RIGHT_PIPE);
        }

        return "";
    }
    public boolean isLeaf(Node node) {
        return node == null;
    }

}
