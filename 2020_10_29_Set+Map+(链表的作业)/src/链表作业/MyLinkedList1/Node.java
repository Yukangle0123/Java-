package 链表作业.MyLinkedList1;

public class Node {
    public  Node pre;
    public  Node next;
    public  int val;
    public Node(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }
}
