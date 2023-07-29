import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();

        binaryTree.insert(10);
        binaryTree.insert(40);
        binaryTree.insert(-10);
        binaryTree.insert(30);
        binaryTree.insert(50);
        binaryTree.insert(-20);
        binaryTree.insert(0);
        binaryTree.insert(-1);
        binaryTree.insert(5);

        System.out.println(binaryTree.prettyPrint());

    }
}
