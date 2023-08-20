package pgdp.tree;

import pgdp.penguinDate.Penguin;

public class Main {
    public static void main(String[] args) {



//
//        Tree<String> tree = new Tree<>("adam");
//        tree.insert(michael,adam);
//        tree.insert(robin,adam);
//        tree.insert(dwight,adam);
//        tree.insert(phoebe,michael);
//        tree.insert(levi,phoebe);
//        tree.insert(ted,robin);
//        tree.insert(hange,robin);
//        tree.insert(jim,ted);
//        tree.insert(rachel,jim);
//
//        tree.nodeFinder(michael.getData());
//        System.out.println(michael.isLeaf());
//        rachel.insert(adam);
//        adam.insert(michael);
//        adam.insert(robin);
//        adam.insert(dwight);
//        adam.insert(levi);
//        michael.insert(dwight);
//        michael.insert(hange);
//        hange.insert(jim);
//       System.out.println(rachel.size());
//        rachel.traversal(rachel);
//        System.out.println();
//        rachel.nodes.forEach((Node<String> bruh)-> System.out.println(bruh.getData()));
//
//        String adam = new String("Adam");
//        String michae = new String("Michael");
//        String robin = new String("Robin");
//        String dwight = new String("Dwight");
//        String phoebe = new String("Phoebe");
//        String levi = new String("Levi");
//        String ted = new String("Ted");
//        String hange = new String("Hange");
//        String jim = new String("Jim");
//        String rachel = new String("Rachel");

        Node<String> adam = new Node<>("Adam");
        Node<String> michael = new Node<>("Michael");
        Node<String> robin = new Node<>("Robin");
        Node<String> dwight = new Node<>("Dwight");
        Node<String> phoebe = new Node<>("Phoebe");
        Node<String> levi = new Node<>("Levi");
        Node<String> ted = new Node<>("Ted");
        Node<String> hange = new Node<>("Hange");
        Node<String> jim = new Node<>("Jim");
        Node<String> rachel = new Node<>("Rachel");

        Tree<String> tree = new Tree<>("adam");
       tree.insert("michae","adam");
        tree.insert("robin","adam");
        tree.insert("dwight","adam");
        tree.insert("phoebe","michae");
       tree.insert("levi","phoebe");
        tree.insert("ted","robin");
        tree.insert("hange","robin");
        tree.insert("jim","ted");
        tree.insert("rachel","jim");

        //tree.traversal();
        //System.out.println();
       // System.out.println(tree.LCA("phoebe", "levi"));
        System.out.println(tree.distanceBetweenNodes("rachel", "ted"));
        System.out.println(tree.LCA("rachel", "ted").toString());
    }
}
