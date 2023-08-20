package pgdp.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Tree<T> {

    private Node<T> root;

    public Tree (T data) {
        root = new Node(data);
    }

    private boolean contains(Node<T> thing){
        if (root.getData()==thing.getData()) return true;
        return root.contains(thing);
    }

    public Node<T> nodeFinder(T value){
        if (root.getData()==value) return root;
        return root.nodeFinder(value, root);
    }
    public void insert(T value, T parent) {
       if (!containsKey(value) && containsKey(parent)){
           Node<T> val=new Node<T>(value);
           nodeFinder(parent).setChildren(val);
           val.setParent(nodeFinder(parent));
       }

       // insert(T value, T parent) - If the value is already present in the tree or parent object is not present in the tree,
        // then the method should do nothing. Otherwise, it should add value to the tree and add them as parents child.
    }

    public void remove(T value) {
      if (containsKey(value)){
          if (root.getData()!=value){
              Node<T>node=nodeFinder(value);
              node.remove();
          }
      }
        // remove(T value) - If the value is not present in the tree, then the method should do nothing.
        // Otherwise, it should remove node with the corresponding value from the tree and assign it’s direct children to it’s parent.
        // Also, once the root of the tree is captured, it should never be removed from the tree.
        // Therefore, method should also do nothing if passed parameter is root data.
    }


    public T LCA(T a, T b) {
        //we have two lists.
        //first contains all the ancestors of a including itself
        //Second contains all the ancestors of b including itself
        //first common element would be their LCA

        //I am assuming that none of the value (a or b) are the values of root, since it would not have an ancestor 
        //and program would have nullpointer error 


        if (root.getData()==a || root.getData()==b)
            return root.getData();

        Node<T> a1=nodeFinder(a);
        Node<T> b1=nodeFinder(b);
        Node<T> commonAncestor=new Node<>(a);
        a1.ancestors.clear();
        a1.ancestors(a1);
        List<T> a1Ancestors= a1.ancestors;
        b1.ancestors.clear();
        b1.ancestors(b1);
        List<T> b1Ancestors= b1.ancestors;

        for (T thing: a1Ancestors) {
            if (b1Ancestors.contains(thing)){
                commonAncestor.setData(thing);
                break;
            }
        }

        return commonAncestor.getData();
       // T LCA(T a, T b) - this method returns lowest common ancestor of a and b in the tree, provided these two values are present.
    }


    public int distanceBetweenNodes(T a, T b) {
        //edges would simply be the index of LCA in first list plus index in the second one
        Node<T> a1=nodeFinder(a);
        Node<T> b1=nodeFinder(b);
        a1.ancestors.clear();
        a1.ancestors(a1);
        List<T> a1Ancestors= a1.ancestors;
        b1.ancestors.clear();
        b1.ancestors(b1);
        List<T> b1Ancestors= b1.ancestors;
        T commonAncestor=LCA(a, b);

        if (a1.ancestors.contains(b) || b1.ancestors.contains(a)){
            return Math.abs(a1.ancestors.indexOf(commonAncestor)-b1.ancestors.indexOf(commonAncestor));
        }
        return a1.ancestors.indexOf(commonAncestor)+b1.ancestors.indexOf(commonAncestor)+2;

        // distanceBetweenNodes(T a, T b) - returns the number of edges on the path from a to b (or b to a, they are the same, anyway)
    }

    public Node<T> getRoot() {
        return this.root;
    }

    public boolean containsKey(T key) {
        if (root.getData()==key) return true;
        return root.containsKey(key, root);
        //containsKey(T key) - checks if the given key exists in the tree;
    }

    public void traversal() {
        root.traversal(root);
        // traverse() - Like Node’s traversal method, but starts traversing from the root of the tree.
        // Avoid code duplication while implementing this method.
        // You may add arbitrary methods as you see fit that will help you to write the perfect implementation,
        // but make sure to mark them as private, so internal implementation is not visible to the outer world.
    }

}
