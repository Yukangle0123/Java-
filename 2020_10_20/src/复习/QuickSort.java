package 复习;

import java.util.Arrays;

public class QuickSort {
    public static void quickSort(long[] array){
         quickSortInner(array,0,array.length-1);
    }

    private static void quickSortInner(long[] array, int lowIndex, int highIndex) {
        int size=highIndex-lowIndex+1;
        if(size<=1){
            return ;
        }
        int index=partition(array,lowIndex,highIndex);
        quickSortInner(array,lowIndex,index-1);
        quickSortInner(array,index+1,highIndex);
    }

    private static int partition(long[] array, int lowIndex, int highIndex) {
        long key=array[lowIndex];
        int leftIndex=lowIndex;
        int rightIndex=highIndex;
        while(leftIndex<rightIndex){
            while(leftIndex<rightIndex&&array[rightIndex]>key){
                rightIndex--;
            }
            swap(array,leftIndex,rightIndex);
            while(leftIndex<rightIndex&&array[leftIndex]<=key){
                leftIndex++;
            }
            swap(array,leftIndex,rightIndex);
        }
        array[leftIndex]=key;
        return leftIndex;
    }
    private static void 比大小(long[]array){
        Arrays.sort(array);
    }
    private static int partition1(long[] array, int lowIndex, int highIndex){
        int leftIndex=lowIndex;
        int mid=(lowIndex+highIndex)/2;
        long[] a=new long[]{array[lowIndex],array[mid],array[highIndex]};
        比大小(a);
        赋值(array,lowIndex,mid,highIndex,a);
        long key=array[mid];
        swap(array,mid,highIndex-1);
        int rightIndex=highIndex-2;
        while(leftIndex<rightIndex){
            while(leftIndex<rightIndex&&array[rightIndex]>key){
                rightIndex--;
            }
            while(leftIndex<rightIndex&&array[leftIndex]<key){
                leftIndex++;
            }

            swap(array,leftIndex,rightIndex);
        }
        swap(array,highIndex-2,leftIndex);
        return leftIndex;

    }

    private static void 赋值(long[] array, int lowIndex, int mid, int highIndex, long[] a) {
        array[lowIndex]=a[0];
        array[mid]=a[1];
        array[highIndex]=a[2];
    }

    private static void swap(long[] array, int leftIndex, int rightIndex) {
        long t=array[leftIndex];
        array[leftIndex]=array[rightIndex];
        array[rightIndex]=t;
    }

}
