import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
public class BinaryTree {
    public Node root;

    public BinaryTree(Node root) {
        this.root = root;
    }

    public BinaryTree() {
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

    public String prettyPrint(){
        return root.prettyPrint(root);
    }

    public int size(){
        return root.size(root);
    }

    public Node search(int value){
        return root.search(root, value);
    }

    public List<Integer> getContent(){
        return root.getContent(root);
    }

    public void delete(Integer value) {

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
}
