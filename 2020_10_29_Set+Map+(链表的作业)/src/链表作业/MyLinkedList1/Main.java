package 链表作业.MyLinkedList1;

public class Main {
    public static void main(String[] args) {
        DoubleLinkedList d=new DoubleLinkedList();
        d.addLast(1);

        d.addLast(2);
        d.addLast(1);
        d.addLast(2);
        d.addLast(1);
        d.addLast(2);
        d.addLast(1);
        d.addLast(2);
        d.addLast(1);
        d.addLast(2);
        d.removeAllKey(2);
        System.out.println(d.size());
        d.display();
    }
}
