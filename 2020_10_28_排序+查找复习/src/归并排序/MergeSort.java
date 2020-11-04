package 归并排序;

public class MergeSort {
    public static void mergeSort(long[]array){
        mergeSortInternal(array,0,array.length);//半闭半开
    }

    private static void mergeSortInternal(long[] array, int lowIndex, int heightIndex) {
        int size=heightIndex-lowIndex;
        if(size<=1){
            return;
        }
        int midIndex=(heightIndex+lowIndex)/2;
        mergeSortInternal(array,lowIndex,midIndex);
        mergeSortInternal(array,midIndex,heightIndex);
        mergeArray(array,lowIndex,midIndex,heightIndex);

    }

    private static void mergeArray(long[] array, int lowIndex, int midIndex, int heightIndex) {
        int size=heightIndex-lowIndex;
        long[]arrays=new long[size];
        int leftIndex=lowIndex;
        int rightIndex=midIndex;
        int index=0;
        while(leftIndex<midIndex&&rightIndex<heightIndex){
            if(array[leftIndex]<array[midIndex]){
                arrays[index++]=array[leftIndex++];
            }else{
                arrays[index++]=array[rightIndex++];
            }
        }
        if(leftIndex>=midIndex){
            while(rightIndex<heightIndex){
                arrays[index++]=array[rightIndex++];
            }
        }else{
            while(leftIndex<midIndex){
                arrays[index++]=array[leftIndex++];
            }
        }
        for(int i=0;i<size;i++){
            array[i+lowIndex]=arrays[i];
        }
    }
}
