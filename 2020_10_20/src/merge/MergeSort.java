package merge;

public class MergeSort {
    public void mergeSort(long[]array){
        mergeSortInternal(array,0,array.length);
    }

    private void mergeSortInternal(long[] array, int lowIndex, int highIndex) {
        int size=highIndex-lowIndex;
        if(size<=1){
            return;
        }
        int midIndex=(lowIndex+highIndex)/2;
        mergeSortInternal(array,lowIndex,midIndex);
        mergeSortInternal(array,midIndex,highIndex);
        mergeArray(array,lowIndex,midIndex,highIndex);
    }

    private void mergeArray(long[] array, int lowIndex, int midIndex, int highIndex) {
        long [] arr=new long[highIndex-lowIndex];
        int index=0;
        int leftIndex=lowIndex;
        int rightIndex=midIndex;
        while(leftIndex<midIndex&&rightIndex<highIndex){
            if(array[leftIndex]<=array[rightIndex]){
                arr[index++]=array[leftIndex++];
            }else{
                arr[index++]=array[rightIndex++];
            }
        }
        //条件不满足
        if(leftIndex>=midIndex){
            while(rightIndex<highIndex){
                arr[index++]=array[rightIndex++];
            }
        }else{
            while(leftIndex<midIndex){
                arr[index++]=array[leftIndex++];
            }
        }
        for(int i=0;i<highIndex-lowIndex;i++){
            array[i+lowIndex]=arr[i];
        }

    }

}
