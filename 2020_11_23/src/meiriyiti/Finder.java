package meiriyiti;

import java.util.Arrays;

public class Finder {
    public int findKth(int[] a, int n, int K) {
        quickSort(a);
        return a[n-K];
    }
    private void quickSort(int[] a) {
        quickSortInternal(a,0,a.length-1);
    }
    private void quickSortInternal(int[] a, int lowIndex,int highIndex) {
        int size=highIndex-lowIndex+1;
        if(size<=1){
            return;
        }
        int index=partition(a,lowIndex,highIndex);
        quickSortInternal(a,lowIndex,index-1);
        quickSortInternal(a,index+1,highIndex);
    }

    private int partition(int[] a, int lowIndex, int highIndex) {
        int left=lowIndex;
        int right=highIndex;
        int key=a[lowIndex];
        while(left<right){
            while(left<right&&a[right]>=key){
                right--;
            }
            a[left]=a[right];
            while(left<right&&a[left]<=key){
                left++;
            }
            a[right]=a[left];
        }
        a[left]=key;
       return left;
    }
    public static void main(String[] args) {
        Finder finder=new Finder();
        int[]nums=new int[10];
        for(int i=0;i<10;i++){
            nums[i]=nums.length-i;
        }
        System.out.println(Arrays.toString(nums));
       finder.quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
