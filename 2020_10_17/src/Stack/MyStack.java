package Stack;
//利用顺序表实现
public class MyStack {
    int [] array;
    int size;
    MyStack(){
        //不考虑扩容问题
        this.array=new int[100];
        this.size=0;
    }
    public void push(int val){
        this.array[size++]=val;
    }
    public int pop(){
        return array[--size];
    }
    public int peek(){
        return array[size-1];
    }
    public boolean isEmpty(){
        return size==0;
    }
    public int size(){
        return size;
    }

}
