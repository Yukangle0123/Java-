package Stack;
import java.util.LinkedList;
import java.util.Queue;

//使用队列实现栈
// push(x) -- 元素 x 入栈
//pop() -- 移除栈顶元素
// top() -- 获取栈顶元素
//empty() -- 返回栈是否为空
public class Stack {
    private Queue<Integer>queue;
    Stack(){
        this.queue=new LinkedList<>();
    }
    public void push(int val){
        queue.add(val);
    }
    public int pop(){
        int size=queue.size();
        for(int i=0;i<size-1;i++){
            Integer integer=queue.remove();
            queue.add(integer);
        }
        return queue.remove();
    }
    public int top(){
        int size=queue.size();
        for(int i=0;i<size-1;i++){
            Integer integer=queue.remove();
            queue.add(integer);
        }
        Integer v=queue.remove();
        queue.add(v);
        return v;
    }
    public boolean empty(){
        return queue.isEmpty();
    }
}
