package Queue;

//    MyCircularQueue(k): 构造器，设置队列长度为 k 。
//    Front: 从队首获取元素。如果队列为空，返回 -1 。
//    Rear: 获取队尾元素。如果队列为空，返回 -1 。
//    enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
//    deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
//    isEmpty(): 检查循环队列是否为空。
//    isFull(): 检查循环队列是否已满。
public class CircularQueue {
    private int []array;
    private int size;
    private int rear;
    private int front;

    CircularQueue(int k){
        this.array=new int [k];//为方便测试将数组的元素初始化为5
        this.size=0;
        this.front=0;
        this.rear=0;
    }
    public int Front(){
        if(size==0){
            return -1;
        }
        return array[rear];
    }
    public int Rear(){
        if(size==0){
            return -1;
        }
        int index=front-1;
        if(index==-1){
            return array[0];
        }
        return array[index];
    }
    public boolean enQueue(int val){
        if(size==this.array.length){
            return false;
        }
        array[size++]=val;
        rear++;
        if(rear==array.length){
            rear=0;
        }
        return true;
    }
    public int poll(){
        if(size==0){
            throw new RuntimeException("队列是空的");
        }
        if(rear==0){
            return array[--size];
        }
        return array[--size];

    }

}
