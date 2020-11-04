package Heap作业;

import java.util.Arrays;
import java.util.PriorityQueue;

public class heap {
    /**
     * 堆的向下调整的实现(小堆)
     * @param array 堆所在的数组
     * @param size 数组的长度
     * @param index 调整的位置
     */
    public static void smallHeapAdjustDown(int[]array,int size,int index){
        while(index*2+1<size){
            int minIndex=index*2+1;
            int rightIndex=minIndex+1;
            if(rightIndex<size&&array[rightIndex]<array[minIndex]){
                minIndex=rightIndex;
            }
            if(array[index]<=array[minIndex]){
                break;
            }
            swap(array,index,minIndex);
            index=minIndex;
        }
    }
    public static void bigHeapAdjustDown(int[]array,int size,int index){
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
    public static void createHeap(int []array){
        int index=array.length-1;
        index=(index-1)/2;
        for(int i=index;i>=0;i--){
            bigHeapAdjustDown(array,array.length,i);
        }
    }

    private static void swap(int[] array, int index, int minIndex) {
        int t=array[index];
        array[index]=array[minIndex];
        array[minIndex]=t;
    }

    public static void main(String[] args) {
        int[] array = { 1,5,3,8,7,6};
        createHeap(array);
        System.out.println(Arrays.toString(array));

    }
}
