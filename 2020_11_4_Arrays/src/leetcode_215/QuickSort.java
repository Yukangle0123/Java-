package leetcode_215;

public class QuickSort {
    public int findKthLargest(int[] nums, int k) {
        quickSort(nums,0,nums.length-1);
        return nums[nums.length-k];
    }

    private void quickSort(int[] nums, int lowIndex, int heightIndex) {
        int size=heightIndex-lowIndex+1;
        if(size<=1){
            return;
        }
        int index=partition(nums,lowIndex,heightIndex);
        quickSort(nums,lowIndex,index-1);
        quickSort(nums,index+1,heightIndex);
    }

    private int partition(int[] nums, int lowIndex, int heightIndex) {
        int leftIndex=lowIndex;
        int rightIndex=heightIndex;
        int key=0;
        while(leftIndex<rightIndex){
            key=nums[leftIndex];
            while(leftIndex<rightIndex&&nums[rightIndex]<=key){
                rightIndex--;
            }
            nums[leftIndex]=nums[rightIndex];
            while(leftIndex<rightIndex&&nums[leftIndex]>=key){
                leftIndex++;
            }
            nums[rightIndex]=nums[leftIndex];
        }
        nums[leftIndex]=key;
        return leftIndex;
    }

    public static void main(String[] args) {
        int[]nums=new int[]{9,1,5,7,3,1,7};
        QuickSort Q=new QuickSort();
        Q.quickSort(nums,0,nums.length-1);
        System.out.println("dada");
    }
}
