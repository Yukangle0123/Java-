package Queue;
class  Node{
    Node next;
    int val;
    Node(int val){
        this.val=val;
    }
}
//使用链表实现，数组实现的队列在进行出队列时时间复杂度太大
public class MyQueue {
    private Node head;
    private Node tail;
    private int size;
    MyQueue(){
        this.head=null;
        this.tail=null;
        this.size=0;
    }
    public void offer(int val){
        Node node=new Node(val);
        if(head==null){
            head=node;
        }else{
            tail.next=node;
        }
        tail=node;
        size++;
    }
    public int poll(){
        if(size==0){
            throw new RuntimeException("队列为空！");
        }
        int v=head.val;
        head=head.next;
        //当队列中只有一个元素的时候，将尾结点置空
        if(head==null){
            tail=null;
        }
        size--;
        return v;
    }
    public int peek(){
       if(size==0){
           throw new RuntimeException("队列为空");
       }
       return head.val;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public int size(){
        return size;
    }
}
