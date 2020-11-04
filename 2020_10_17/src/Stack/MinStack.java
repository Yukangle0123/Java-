package Stack;

import java.util.Deque;
import java.util.LinkedList;

public class MinStack {
    Deque<Integer>stack1;
    Deque<Integer>stack2;
    MinStack(){
        stack1=new LinkedList<>();
        stack2=new LinkedList<>();
    }
    public void push(int val){
        stack1.push(val);
        if(stack2.isEmpty()||stack2.peek()>=val){
            stack2.push(val);
        }
    }
    public void pop(int val){
        if(stack1.isEmpty()){
            return;
        }
        int v=stack1.pop();
        if(v==stack2.peek()){
            stack2.pop();
        }
    }
    public int top(){
        return stack1.peek();
    }
    public int getMin(){
        return stack2.peek();
    }
}
