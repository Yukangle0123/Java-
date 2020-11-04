package 排序;

public class Sort {
    private static void swap(long []array,int i,int j){
        long t=array[i];
        array[i]=array[j];
        array[j]=t;
    }
    /**
     * 插入排序
     * 排序的思想:将数组看成两部分，有序和无序，在遍历的时候，依次拿无序区间的第一个数与有序区间的最后一个，当小于这个数时候，将前面的数往后挪，
     * 直到找到那个找到大于该数的数字，循环结束。(大于该数字时循环与之相同)
     * @param array
     */
    public static void insertSort(long[]array){
        //n个数进行排序时外层的排序次数是n-1次。
        for(int i=0;i<array.length-1;i++){
            long key=array[i+1];
            int j=0;
            //第一次循环是现将0号下标位置看做有序
            for( j=i;j>=0;j--){
                if(array[j]>key){
                    array[j+1]=array[j];
                }else{
                    break;
                }
            }
            array[j+1]=key;
        }
    }

    /**
     * 选择排序
     * 思想：每次将0号下标对应的数看成最大的数，然后遍历数组，遇到大于该数的。将maxIndex(最大数的下标)换成大于该数的下标，
     * 每次循环找出一个最大的数字，放在数组末尾
     * @param array
     */
    public static void selectSort(long []array){
        for(int i=0;i<array.length-1;i++){
            int maxIndex=0;
            int j=1;
            for(;j<array.length-i;j++){
                if(array[maxIndex]<array[j]){
                    maxIndex=j;
                }
            }
            swap(array,maxIndex,j-1);
        }
    }
    public static void heapSort(long[]array){
        //建堆
        createHeap(array);
        for(int i=0;i<array.length-1;i++){
            swap(array,array.length-1-i,0);
            adjustDown(array,array.length-i-1,0);
        }
    }

    private static void createHeap(long[] array) {
        int size=array.length;
        int parentIndex=(size-2)/2;
        for(int i=parentIndex;i>=0;i--){
            adjustDown(array,size,i);
        }
    }

    private static void adjustDown(long[] array, int size, int index) {
        while(index*2+1<size){
            int maxIndex=index*2+1;
            if(maxIndex+1<size&&array[maxIndex]<array[maxIndex+1]){
                maxIndex++;
            }
            if(array[index]>=array[maxIndex]){
                break;
            }
            swap(array,index,maxIndex);
            index=maxIndex;
        }
    }

    public static void bulSort(long[]array){
        for(int i=0;i<array.length-1;i++){
            boolean isSorted=true;
            for(int j=0;j<array.length-1-i;j++){
                if(array[j]>array[j+1]){
                    isSorted=false;
                    swap(array,j,j+1);
                }
            }
            if(isSorted){
                break;
            }
        }
    }
    public static void shellSort(long[]array){
        int gap=array.length-1/2;
        while(true){
            if(gap==1){
                break;
            }
            insertGapSort(array,gap);
            gap/=2;
        }
    }

    private static void insertGapSort(long[] array, int gap) {
        for(int i=gap;i<array.length;i++){
            long key=array[i];
            int j=0;
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
