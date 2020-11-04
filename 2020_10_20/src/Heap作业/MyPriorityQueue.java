package Heap作业;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.PriorityQueue;

public class MyPriorityQueue {
    private int []array;
    private int size;
    MyPriorityQueue(){
        this.array=new int[100];
        size=0;
    }
    public boolean add(int val){
        if(size>=array.length){
            return false;
        }
        array[size++]=val;
        adjustUp(size-1);
        return true;
    }
    private  void swap(int[] array, int index, int minIndex) {
        int t=array[index];
        array[index]=array[minIndex];
        array[minIndex]=t;
    }
    private void adjustUp(int index) {
        while(index>0){
            int parentIndex=(index-1)/2;
            if(array[parentIndex]>array[index]){
                break;
            }
            swap(array,parentIndex,index);
            index=parentIndex;
        }
    }
    public int poll() {
        if (size == 0) {
            return -1;
        }
        int val = this.array[0];
        array[0] = array[size-1];
        size--;
        bigHeapAdjustDown(0);
        return val;
    }
    public void bigHeapAdjustDown(int index){
        while(index*2+1<size){
            int maxIndex=index*2+1;
            if(maxIndex+1<size&&array[maxIndex+1]>array[maxIndex]){
                maxIndex++;
            }
            if(array[index]>=array[maxIndex]){
                break;
            }
            swap(array,index,maxIndex);
            index=maxIndex;
        }
    }

    public static void main(String[] args) {
        MyPriorityQueue m=new MyPriorityQueue();
        m.add(1);
        m.add(5);
        m.add(3);
        m.add(8);
        m.add(7);
        m.add(6);
        System.out.println(m.poll());
        System.out.println(m.poll());
        System.out.println(m.poll());
        System.out.println(m.poll());
        System.out.println(m.poll());
        System.out.println(m.poll());

    }
}
