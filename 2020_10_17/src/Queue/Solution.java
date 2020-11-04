package Queue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

//使用栈实现队列
//push(x) -- 将一个元素放入队列的尾部。
//pop() -- 从队列首部移除元素。
//peek() -- 返回队列首部的元素。
//empty() -- 返回队列是否为空。
public class Solution {
   private Deque<Integer> d1;
   private Deque<Integer> d2;
   Solution(){
       this.d1=new LinkedList<>();
       this.d1=new LinkedList<>();
   }
   public void push(int val){
       d1.push(val);
   }
   public int pop(){
       if(d2.isEmpty()){
           while(!d1.isEmpty()){
               d2.push(d1.pop());
           }
       }
       return d2.pop();
   }
   public int peek(){
       if(d2.isEmpty()){
           while(!d1.isEmpty()){
               d2.push(d1.pop());
           }
       }
       return d2.peek();
   }
   public boolean empty(){
       return d1.isEmpty()&&d2.isEmpty();
   }
}
