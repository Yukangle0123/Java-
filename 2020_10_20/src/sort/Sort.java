package sort;

public class Sort {
    public static void quickSort(long[]array){
        quickSortInternal(array,0,array.length-1);
    }

    private static void quickSortInternal(long[] array, int lowIndex, int highIndex) {
        int size=highIndex-lowIndex+1;
        if(size<=1){
            return;
        }
        int index=partition(array,lowIndex,highIndex);
        quickSortInternal(array,lowIndex,index-1);
        quickSortInternal(array,index+1,highIndex);
    }
    private static int partition(long[] array, int lowIndex, int highIndex) {
        // 选择合适的方法
        return partition挖坑(array, lowIndex, highIndex);
    }

    private static int partitionHover(long[] array, int lowIndex, int highIndex) {
        int leftIndex = lowIndex;
        int rightIndex = highIndex;
        // 选择的数是最左边的一个
        long key = array[lowIndex];
        // 选择了最左边，从右边先走

        // 停止条件 leftIndex == rightIndex
        // 循环的继续的条件 leftIndex < rightIndex
        while (leftIndex < rightIndex) {

            while (leftIndex < rightIndex && array[rightIndex] >= key) {
                rightIndex--;
            }
            // 说明 [rightIndex] 遇到了小的了

            while (leftIndex < rightIndex && array[leftIndex] <= key) {
                leftIndex++;
            }
            // 说明 [leftIndex] 遇到了大的了

            swap(array, leftIndex, rightIndex);
        }

        swap(array, lowIndex, leftIndex);

        return leftIndex;
    }
    private static int partition挖坑(long[] array, int lowIndex, int highIndex){
        int leftIndex = lowIndex;
        int rightIndex = highIndex;
        // 选择的数是最左边的一个
        long key = array[lowIndex];

        // 停止条件 leftIndex == rightIndex
        // 循环的继续的条件 leftIndex < rightIndex
        while (leftIndex < rightIndex) {

            while (leftIndex < rightIndex && array[rightIndex] >= key) {
                rightIndex--;
            }
            swap(array,leftIndex,rightIndex);

            while (leftIndex < rightIndex && array[leftIndex] <= key) {
                leftIndex++;
            }
            // 说明 [leftIndex] 遇到了大的了

            swap(array, leftIndex, rightIndex);
        }
        array[leftIndex]=key;
        return leftIndex;
    }

    private static void swap(long[] array, int leftIndex, int rightIndex) {
        long t=array[leftIndex];
        array[leftIndex]=array[rightIndex];
        array[rightIndex]=t;
    }
    public static void 分割(long[]array){
        int left=0;
        int right=array.length-1;
        while(left<right){
            while(left<right&&array[left]%2!=0){
                left++;
            }
            while(left<right&&array[right]%2==0){
                right--;
            }
            swap(array,left,right);
        }
    }
}

