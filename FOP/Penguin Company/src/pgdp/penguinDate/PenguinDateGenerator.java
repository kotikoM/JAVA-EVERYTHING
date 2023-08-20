package pgdp.penguinDate;

import pgdp.tree.Node;
import pgdp.tree.Tree;

import java.util.List;

public class PenguinDateGenerator {

    private Tree<Penguin> tree;

    public PenguinDateGenerator(Tree<Penguin> tree) {
        this.tree = tree;
    }

    public boolean canGoOnADate(Penguin p1, Penguin p2) {
        Node<Penguin> a=tree.nodeFinder(p1);
        Node<Penguin> b=tree.nodeFinder(p2);
        a.ancestors.clear();
        a.ancestors.add(p1);
        a.ancestors(a);
        List<Penguin> aAncestors= a.ancestors;
        b.ancestors.clear();
        b.ancestors.add(p2);
        b.ancestors(b);
        List<Penguin> bAncestors= b.ancestors;
        Penguin commonAncestor=tree.LCA(p1, p2);
        int first=aAncestors.indexOf(commonAncestor)+1;
        int second=bAncestors.indexOf(commonAncestor)+1;

        if (first>=p1.getAllowance() && second>=p2.getAllowance()){
            return true;
        }

        return false;

        //if and only if the distance in the genealogy tree between this penguin and
        // lowest common ancestor of these two penguins is greater or equal to allowance (field of class Penguin);
    }

    public Tree<Penguin> getTree() {
        return this.tree;
    }

}
