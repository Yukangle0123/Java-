package 链表作业;
class Node{
    Node next;
    Integer val;
    Node(Integer val){
        this.val=val;
    }
}

public class MyLinkedList {
//1. get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
//2.addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
//3.addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
// 4.addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。
//如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
//5.deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
    int size;
    Node head;
    Node tail;
    /** Initialize your data structure here. */
    public MyLinkedList() {
        this.head=null;
        this.size=0;
        this.tail=null;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if(index>=size){
            return -1;
        }
        Node cur=head;
        while(index-->0){
            cur=cur.next;
        }
        return cur.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
       if(head==null){
           head=new Node(val);
           tail=head;
           size++;
       }else{
           Node node=new Node(val);
           node.next=head;
           head=node;
           size++;
       }

    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
       if(head==null){
           head=new Node(val);
           tail=head;
           size++;
       }else{
           tail.next=new Node(val);
           tail=tail.next;
           size++;
       }
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
       if(index<=0){
           addAtHead(val);
       }else if(index==size){
           addAtTail(val);
       }else if(index>size){
           return;
       }else{
           Node cur=head;
           while(--index>0){
               cur=cur.next;
           }
           Node node=new Node(val);
           node.next=cur.next;
           cur.next=node;
           size++;
       }
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if(index>size||index<0||head==null){
            return;
        }
        if(index==0){
            head=head.next;
            size--;
            if(size==0){
                tail=null;
            }
            return;
        }
        Node pre=null;
        Node cur=head;
        for(int i=0;i<index;i++){
            pre=cur;
            cur=cur.next;
        }

        pre.next=cur.next;
        if(cur==tail){
            tail=pre;
        }
        size--;
    }
}

