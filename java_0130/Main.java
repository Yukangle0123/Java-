package java_0130;


import java.util.Scanner;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();
        while(sc.hasNext()) {
            int n = sc.nextInt();
            int[] nums = new int[n];
            int[] left = new int[n];
            int[] right = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
               while(!stack.isEmpty() && nums[stack.peek()] >= nums[i]){
                   stack.pop();
               }
               left[i] = stack.isEmpty() ? -1 : stack.peek();
               stack.push(i);
            }
            stack.clear();
            for(int i = nums.length-1;i >= 0; i--){
                while(!stack.isEmpty() && nums[stack.peek()]>= nums[i]){
                    stack.pop();
                }
                right[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(i);
            }
            for(int i = 0; i < n; i++){
                System.out.println(left[i]+" "+right[i]);
            }

        }
    }
    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int n = sc.nextInt();
            int[] nums = new int[n];
            int[] left = new int[n];
            int[] right = new int[n];
            for(int i = 0; i < n; i++){
                nums[i] = sc.nextInt();
            }
            get(nums,left,right);
            for(int i = 0; i < n; i++){
                System.out.println(left[i]+" "+right[i]);
            }
        }

    }

    private static void get(int[] nums,int[] left,int[] right) {
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < nums.length; i++){
            while(!stack.isEmpty() && nums[stack.peek()]>= nums[i]){
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        stack.clear();
        for(int i = nums.length-1;i >= 0; i--){
            while(!stack.isEmpty() && nums[stack.peek()]>= nums[i]){
                stack.pop();
            }
            right[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

    }

    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int n = sc.nextInt();
            int[] nums = new int[n];
            int flg = 0;
            for(int i = 0; i < n; i++){
                nums[i] = sc.nextInt();
                flg ^=nums[i];
            }
            //flg 的值为所有num中出现奇数次的两个数的异或值
            int index = 1;
            while((flg & index) == 0){
                index <<= 1;
            }
            int res1 = 0;
            int res2 = 0;
            for(int num : nums){
                if((num & index) ==0){
                    res1 ^= num;
                }else{
                    res2 ^= num;
                }
            }
            if(res1>res2){
                System.out.println(res2+" "+res1);
            }else{
                System.out.println(res1+" "+res2);
            }
        }
    }

}
