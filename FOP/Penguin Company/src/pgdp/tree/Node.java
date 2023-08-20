package pgdp.tree;

import java.util.ArrayList;
import java.util.List;

public class Node<T>{

    private List<Node<T>> children;
    private Node<T> parent;
    private T data;
    public Node(T data) {
        this.data = data;
        children = new ArrayList<>();
    }
    public boolean contains(Node<T> thing){
        //this=root, thing =search for
        nodes.clear();
        nodes.add(this);
        nodesToList(this);
        for (Node<T> node : nodes) {
            if (node.getData() == thing.getData()) return true;
        }
        return false;
    }

    public boolean containsKey(T key, Node<T> root){
        nodes.clear();
        nodes.add(root);
        nodesToList(root);
        for (Node<T> node : nodes) {
            if (node.getData() == key) return true;
        }
        return false;
    }
    public Node<T> nodeFinder(T value, Node<T> root){
        nodes.clear();
        nodes.add(root);
        nodesToList(root);
        for (Node<T> node : nodes) {
            if (node.getData() == value) return node;
        }
        return null;
    }

    public List<T> ancestors=new ArrayList<>();
    public void ancestors(Node<T> thing){
        //creating a list of ancestors for thing
        if (thing.getParent()!=null){
            ancestors.add(thing.getParent().getData());
            ancestors(thing.getParent());
        }
    }


    public void insert(Node<T> value) {
        if (value.getData() != null) {
            children.add(value);
            value.parent = this;
            //insert(Node<T> value) - this method adds new node as a direct child of a Node object on which it was called.
        }
    }


    public boolean isLeaf() {
        return children.isEmpty();
    }


    protected List<Node<T>> nodes=new ArrayList<>();
    public void nodesToList(Node<T> thing){
        //create list from the tree to check things quickly without recursion
        if (!thing.isLeaf()){
        nodes.addAll(thing.children);
        thing.children.stream().forEach((Node<T> bruh)-> nodesToList(bruh));}
    }

    public int size() {
        nodes.clear();
        nodes.add(this);
        nodesToList(this);
        return nodes.size();
    }
    //size() - this method returns the size of the tree rooted with a given node;

    public void remove() {
        if (this.getParent()!=null) {
            this.parent.children.addAll(this.children);
            this.parent.children.remove(this);
        }
       // remove() -this method removes aNode object on which it was called from the tree and assigns its direct children to its parent;
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public void setChildren(Node<T> child) {
        this.children.add( child);
    }

    public void setChildren(List<Node<T>> child) {
        this.children.addAll(child);
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent){
        this.parent=parent;
    }

    public T getData() {
        return data;
    }

    public void setData(T data){
        this.data=data;
    }
    private void traversal1(Node<T> root) {
        if (!root.isLeaf()) {
            root.children.stream().forEach((Node<T> bruh) -> System.out.print(bruh.data+" "));
           // System.out.println();
            root.children.stream().forEach((Node<T> bruh) -> bruh.traversal1(bruh));
        }

    }
    public void traversal(Node<T> root) {
            System.out.print(root.data+" ");
           traversal1(root);
    }
    // traverse() - this method traverses through the tree rooted with a Node object on which it was called and prints the data (T data) onto the console.
    // You may use InOrder, PreOrder or PostOrder traversals. (en.wikipedia.org/wiki/Tree_traversal)
}
