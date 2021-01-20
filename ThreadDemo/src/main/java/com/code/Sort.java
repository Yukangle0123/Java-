package com.code;

import java.util.Arrays;

public class Sort {
    //插入排序

    /**
     *排序的思想，将数组看成两个部分：有序，和无序 ，每次从无序部分中拿出一个数字，放在有序部分的合适位置
     * @param nums
     */
    public static int[] insertSort(int[] nums){//5,1,3,7,8,2,1,7,9
        for(int i = 1; i < nums.length; i++){
            int j = i-1;
            int key = nums[i];
            for(; j >=0; j--){
                if(key>=nums[j]){
                    break;
                }else{
                    nums[j+1] = nums[j];
                }
            }
            nums[j+1] = key;
        }
        return nums;
    }
    //冒泡排序

    /**
     * 排序思想每次从无序部分拿一个最大的放在有序部分的首位
     * @param nums
     * @return
     */
    public static int[] bulSort(int[] nums){
        for(int i = 0; i < nums.length-1; i++){
            boolean flg = false;
            for(int j = 0; j < nums.length-1-i; j++){
                if(nums[j] > nums[j+1]){
                    swap(nums,j,j+1);
                    flg = true;
                }
            }
            if(!flg){
                break;
            }
        }
        return nums;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    //选择排序

    /**
     * 选择排序
     * @param nums
     * @return
     */
    public static int[] selectSort(int[] nums){
        for(int i = 0; i < nums.length-1; i++){
            int index = 0;
            int j = 1;
            for(; j < nums.length-i; j++){
                if(nums[index]< nums[j]){
                    index = j;
                }
            }
            swap(nums,index,j-1);
        }
        return nums;
    }
    //快速排序
    public static int[] quickSort(int[] nums){
        quickSortHelper(nums,0,nums.length-1);
        return nums;
    }

    private static void quickSortHelper(int[] nums, int leftIndex, int rightIndex) {
        int size = rightIndex - leftIndex + 1;
        if(size <= 1){
            return ;
        }
        int index = partition(nums,leftIndex,rightIndex);
        quickSortHelper(nums,0,index-1);
        quickSortHelper(nums,index+1,rightIndex);
    }

    private static int partition(int[] nums, int leftIndex, int rightIndex) {
        int key = nums[leftIndex];//以此为基准进行排序
        while(leftIndex < rightIndex){
            while(leftIndex < rightIndex &&nums[rightIndex] >= key){
                rightIndex--;
            }
            nums[leftIndex] = nums[rightIndex];
            while(leftIndex < rightIndex && nums[leftIndex] <= key){
                leftIndex++;
            }
            nums[rightIndex] = nums[leftIndex];
        }
        nums[leftIndex] = key;
        return leftIndex;
    }

    //归并排序
    public static int[] mergeSort(int[] nums){
        mergeSortHelper(nums,0,nums.length);
        return nums;
    }

    private static void mergeSortHelper(int[] nums, int leftIndex, int rightIndex ) {
        int size = rightIndex - leftIndex;
        if(size <= 1){
            return;
        }
        int midIndex = (rightIndex + leftIndex) /2;
        mergeSortHelper(nums,leftIndex,midIndex);
        mergeSortHelper(nums,midIndex,rightIndex);
        mergeArray(nums,leftIndex,midIndex,rightIndex);
    }

    private static void mergeArray(int[] nums, int lowIndex, int midIndex, int heightIndex) {
        int size = heightIndex - lowIndex;
        int[] array = new int[size];
        int index = 0;
        int leftIndex = lowIndex;
        int rightIndex = midIndex;
        while(leftIndex < midIndex && rightIndex < heightIndex){
            if(nums[leftIndex] < nums[rightIndex]){
                array[index++] = nums[leftIndex++];
            }else{
                array[index++] = nums[rightIndex++];
            }
        }
        if(leftIndex >= midIndex){
            while(rightIndex < heightIndex){
                array[index++] = nums[rightIndex++];
            }
        }else{
            while(leftIndex < midIndex){
                array[index++] = nums[leftIndex++];
            }
        }
        //将数据保存到原数组中
        for(int i = 0; i < size; i++){
            nums[i + lowIndex] = array[i];
        }
    }

    //堆排

    public static void main(String[] args) {
        int[] nums =new int[]{5,1,3,7,9,2,6,8};
        System.out.println("排序前:"+Arrays.toString(nums));
        System.out.println("排序后:"+Arrays.toString(mergeSort(nums)));
    }
}
