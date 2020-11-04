package 复习;
//插排，冒泡，堆排，选择排序,希尔排序
public class SortReview {
    private static void Swap(long[]array,int startIndex,int endIndex){
        long t=array[startIndex];
        array[startIndex]=array[endIndex];
        array[endIndex]=t;
    }
    /**
     *插入排序，一开始i=0,先将0位置看做有序，记录i+1的值key，如过j位置的值大于key,
     * 就将j位置往后挪，直到j位置小于key，再将j+1变为key ，排序结束
     * @param array 要排序的数组
     */
    public void insertSort(long[]array){
       for(int i=0;i<array.length-1;i++){
           long key=array[i+1];
           int j;
           for(j=i;j>=0;j--){
               if(key<array[j]){
                   array[j+1]=array[j];
               }else{
                   break;
               }
           }
           array[j+1]=key;
       }
    }

    /**
     * 冒泡排序  时间复杂度O(n^2)
     * @param array
     */
    public void bulSort(long[]array){
        for (int i=0;i<array.length-1;i++){
            boolean isSorted=true;
            for(int j=0;j<array.length-i-1;j++){
                if(array[j]>array[j+1]){
                    Swap(array,j,j+1);
                    isSorted=false;
                }
            }
            if(isSorted){
                break;
            }
        }
    }

    /**
     * 堆排
     * @param array
     */
    public void heapSort(long[]array){
        //建堆
        createHeap(array,array.length);
        for(int i=0;i<array.length-1;i++){
            Swap(array,0,array.length-i-1);
            adjustDown(array,array.length-i-1,0);
        }
    }

    /**
     * 创建堆
     * @param array 建堆的数组
     * @param size 数组的长度
     */
    private void createHeap(long[] array,int size) {
        int parentIndex=(size-2)/2;
        for(int i=parentIndex;i>=0;i--){
            adjustDown(array,size,i);
        }
    }
    private void adjustDown(long[] array, int size, int index) {
        while(index*2+1<size){
            int MaxIndex=index*2+1;
            int rightIndex=MaxIndex+1;
            if(rightIndex<size&&array[rightIndex]>array[MaxIndex]){
                MaxIndex=rightIndex;
            }
            if(array[index]>=array[MaxIndex]){
                break;
            }
            Swap(array,index,MaxIndex);
            index=MaxIndex;
        }
    }
    public void selectSort(long[]array){
        for(int i=0;i<array.length-1;i++){
            int maxIndex=0;
            int j;
            for(j=1;j<array.length-i;j++){
                if(array[j]>array[maxIndex]){
                    maxIndex=j;
                }
            }
            Swap(array,maxIndex,array.length-i-1);
        }
    }
    public void shellSort(long[]array){
        int gap=array.length/2;
        while(gap>1){
            gap/=2;
            insertSortGap(array,gap);
        }
    }

    private void insertSortGap(long[] array, int gap) {
        for(int i=gap;i<array.length;i++){
            long key=array[i];
            int j;
            for(j=i-gap;j>=0;j=j-gap){
                if(array[j]>key){
                    array[j+gap]=array[j];
                }else{
                    break;
                }
            }
            array[j+gap]=key;
        }
    }
}
