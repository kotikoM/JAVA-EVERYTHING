public class LinkedNode {
    public int value;

    public LinkedNode nextNode;

    public LinkedNode(int value, LinkedNode node) {
        this.value = value;
        this.nextNode = node;
    }

    public LinkedNode(int value) {
        this.value = value;
    }
    public LinkedNode() {

    }
}
