package 复习;

import merge.MergeSort;

import java.util.Arrays;
import java.util.Random;

public class Test {
    public static long[]无序数组(int n){
        Random random=new Random(20201020);
        long[]array=new long[n];
        for(int i=0;i<n;i++){
            array[i]=random.nextInt(100);
        }
        return array;
    }
    public static void main(String[] args) {
        long []array=无序数组(10);
        System.out.println(Arrays.toString(array));
        MergeSort m=new MergeSort();
        m.mergeSort(array);
        System.out.println(Arrays.toString(array));

    }
}
