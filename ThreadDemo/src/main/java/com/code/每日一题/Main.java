package com.code.每日一题;

import jdk.nashorn.internal.ir.CallNode;

import java.util.*;

public class Main {
    public static void main1(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Character> list = new ArrayList<>();
        for(char i = 'A'; i <= 'Z'; i++ ){
            list.add(i);
        }
        while(scanner.hasNext()){
            String str = scanner.nextLine();
            int len = str.length();
            char[] chars = str.toCharArray();
            char[] res = new char[len];
            for(int i = 0; i < len; i++){
                if(chars[i] == ' '){
                    res[i] = ' ';
                }else{
                    int index =chars[i] - 'A' - 5;
                    if(index<0){
                        index += 26;
                    }
                    char c = list.get(index);
                    res[i] = c;
                }
            }
            System.out.println(res);
        }
    }
    public static int getTotalCount1(int month){
        if(month <= 2 ){
            return 1;
        }
        int[] everyCount = new int[month+1];
        everyCount[3] = 1;
        for(int i = 4 ; i <= month; i++){
            everyCount[i] = everyCount[i-1]+ everyCount[i-2];
        }
        int res = 1;
        for(int i =0; i <= month ;i++){
            res += everyCount[i];
        }
        return res;
    }
    public static int getTotalCount(int month){
        if(month == 1 || month ==2 ){
            return 1;
        }
        if(month == 0){
            return 0;
        }
        return getTotalCount(month-1)+getTotalCount(month-2);
    }
    public static void heapSort(int[] nums){
        //先建堆
        createHeap(nums);
        for(int i = 0; i < nums.length-1; i++ ){
            //交换
            swap(nums,0,nums.length-i-1);
            adjustUp(nums,nums.length-i-1,0);
        }
    }

    private static void adjustUp(int[] nums, int size, int index) {
        while(index*2+1<size){
            int maxIndex = index*2+1;
            if(maxIndex+1<size && nums[maxIndex] < nums[maxIndex+1]){
                maxIndex +=1;
            }
            if(nums[index] >= nums[maxIndex]){
                break;
            }
            swap(nums,index,maxIndex);
            index = maxIndex;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private static void createHeap(int[] nums) {
        for(int i = (nums.length-2)/2; i >=0 ;i--){
            adjustUp(nums,nums.length,i);
        }
    }

    public static void main(String[] args) {
//       Scanner scanner = new Scanner(System.in);
//       while(scanner.hasNext()){
//           int month = scanner.nextInt();
//           System.out.println(getTotalCount(month));
//       }
        int[] nums = new int[]{9,9,9,9,9,9,9};
        heapSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
