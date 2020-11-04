package 排序;

import 归并排序.MergeSort;
import 快速排序.QuickSort;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static long[] 创建无序的数组(int size){
        long[]array=new long[size];
        Random r=new Random(20201028);
        for(int i=0;i<size;i++){
            array[i]=r.nextInt(99)+1;
        }
        return array;
    }
    public static long[] 创建有序的数组(int size){
       long []array=创建无序的数组(size);
       Arrays.sort(array);
       return array;
    }
    public static long[] 创建相同的数组(int size){
        long[]array=new long[size];
        Random r=new Random();
        int val=r.nextInt(99)+1;
        for(int i=0;i<size;i++){
            array[i]=val;
        }
        return array;
    }
    //排序方法的测试
    public static void main(String[] args) {
        long[]array=创建无序的数组(10);
        System.out.println(Arrays.toString(array));
        Sort.shellSort(array);
        System.out.println(Arrays.toString(array));

    }
}
