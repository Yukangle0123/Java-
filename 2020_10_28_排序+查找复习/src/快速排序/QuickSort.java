package 快速排序;

public class QuickSort {
    public static void quickSort(long[]array){
        quickSortInternal(array,0,array.length-1);
    }

    private static void quickSortInternal(long[] array, int lowIndex, int heightIndex) {
        int size=heightIndex-lowIndex+1;
        if(size<=1){
            return;
        }
        int index=partition(array,lowIndex,heightIndex);
        //index左边的数都小于等于array[index] ,右边的数都大于等于array[index]
        quickSortInternal(array,lowIndex,index-1);
        quickSortInternal(array,index+1,heightIndex);
    }

    private static int partition(long[] array, int lowIndex, int heightIndex) {
        long key=array[lowIndex];
        while(lowIndex<heightIndex){
            //这里两个while循环中至少出现一个=号（>=或<=）不然在lowIndex,heightIndex对应两个相同的数时就会死循环
            while(lowIndex<heightIndex&&array[heightIndex]>=key){
                heightIndex--;
            }
            array[lowIndex]=array[heightIndex];
            while(lowIndex<heightIndex&&array[lowIndex]<=key){
                lowIndex++;
            }
            array[heightIndex]=array[lowIndex];
        }
        array[lowIndex]=key;
        return lowIndex;
    }

}
