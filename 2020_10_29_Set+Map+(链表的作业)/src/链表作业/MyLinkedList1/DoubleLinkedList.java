package 链表作业.MyLinkedList1;

public class DoubleLinkedList {
    public Node head;
    public Node tail;
    public int size;
    DoubleLinkedList(){
        this.size=0;
    }
    public void addFirst(int data){
        if(head==null){
            head=new Node(data);
            tail=head;
        }else{
            Node node=new Node(data);
            node.next=head;
            head.pre=node;
            head=node;
        }
        size++;
    }

    public void addLast(int data){
        if(head==null){
            addFirst(data);
            return;
        }else{
            Node node=new Node(data);
            tail.next=node;
            node.pre=tail;
            tail=node;
        }
        size++;
    }

    public boolean addIndex(int index,int data){
        if(index<0||index>size){
            return false;
        }else if (index==size){
            addLast(data);
        }else if(index==0){
            addFirst(data);
        } else{
            Node preNode=head;
            for(int i=0;i<index;i++){
                preNode=preNode.next;
            }
            Node node=new Node(data);
            node.next= preNode.next;
            preNode.next.pre=node;
            node.pre=preNode;
            preNode.next=node;
            size++;
        }
        return true;
    }

    public boolean contains(int key){
        Node current=head;
        while(current!=null){
            if(current.val==key){
                return true;
            }
            current=current.next;
        }
        return false;
    }

    public boolean remove(int key){
        Node pre=null;
        Node current=head;
        while(current!=null){
            if(current.val==key){
                removeInternal(pre,current);
                size--;
                return true;
            }
            pre=current;
            current=current.next;
        }
        return false;
    }

    private void removeInternal(Node pre, Node current) {
        if(current==head){
            head=head.next;
            if(head==null){
                tail=null;
                return;
            }
            head.pre=null;

        }else{
            pre.next = current.next;
            if(current==tail){
                tail=pre;
                return;
            }
            current.next.pre = pre;
        }
    }

    public void removeAllKey(int key){
        Node pre=null;
        Node current=head;
        while(current!=null){
            if(current.val==key){
                removeInternal(pre,current);
                size--;
                pre=pre;
            }else {
                pre = current;
            }
            current = current.next;
        }
    }

    public int size(){
        return size;
    }
    public void display(){
        Node current=head;
        while(current!=null){
            System.out.println(current);
            current=current.next;
        }
    }
    public void clear(){
        head=null;
        tail=null;
    }
}