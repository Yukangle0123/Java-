package com.code.每日一题;

import com.code.leetcode.TreeNode;

import java.util.*;

class Student{
    String name;
    int score;
    Student(String name,int score){
        this.name = name;
        this.score =score;
    }
}


public class Solution {
    public boolean[] chkSubStr(String[] p, int n, String s) {
        boolean[] exists = new boolean[p.length];
        int index = 0 ;
        for(String str : p){
            exists[index++] = s.contains(str);
        }
        return exists;
    }

    /**
     * 最后一个单词的长度
     * @param s
     * @return
     */
    public static int lengthOfLastWord(String s) {
        int len = s.length();
        char[] chars = s.toCharArray();
        int end = len - 1;
        while(end >= 0 && chars[end] == ' '){
            end--;
        }
        if(end < 0){
            return 0;
        }
        int start = 0;
        while(start < end && chars[start]!= ' '){
            start++;
        }
        return end - start+1;
    }

    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int nums = sc.nextInt();
            int model = sc.nextInt();
            List<Student> list = new ArrayList<>();
            for (int i = 0; i < nums; i++ ) {
                list.add(new Student(sc.next(),sc.nextInt()));
            }
            list.sort(new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    if(model == 0){
                        return o2.score - o1.score;
                    }else{
                        return o1.score - o2.score;
                    }
                }
            });
            for(Student student : list){
                System.out.println(student.name +" "+ student.score);
            }
        }
    }

    /**
     * 判断数组中是否有重复元素
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for(int i = 0; i < nums.length-1; i++){
            if(nums[i] == nums[i+1]){
                return true;
            }
        }
        return false;
    }
    public boolean isLongPressedName(String name, String typed) {
        int i = 0;
        int j = 0;
        while(j < typed.length()){
            if(i < name.length() && name.charAt(i) == typed.charAt(j)){
                i++;
                j++;
            }else if(j < typed.length() && typed.charAt(j)== name.charAt(i-1)){
                j++;
            }else{
                return false;
            }
        }
        return i == name.length();
    }
    public static int[] sortedSquares(int[] A){//{5,3,2,-2,-3,-5}
        int index = 0;
        for(int i = 0; i < A.length; i++){
            if(A[i]<0){
                index++;
            }
            A[i] = (int)Math.pow(A[i],2);
        }
        int len = A.length;
        if(index == 0|| len == 1 ){
            return A;
        }
        int leftIndex = 0;
        int rightIndex = len-1;
        int[] res = new int[len];
        int i = len-1;
        while(leftIndex < index && rightIndex >=index){
            if(A[leftIndex]<A[rightIndex]){
                res[i--] = A[rightIndex--];
            }else{
                res[i--] = A[leftIndex++];
            }
        }
        if(leftIndex < index){
            while(leftIndex < index ){
                res[i--] = A[leftIndex++];
            }
        }
        if(rightIndex >= index){
            while(rightIndex >= index){
                res[i--] = A[rightIndex--];
            }
        }
        System.out.println("de");
        return res;
    }
    public String reverseOnlyLetters(String S) {
        if( S .equals("") || S.length() == 1){
            return S;
        }
        char[] chars = S.toCharArray();
        int i = 0;
        int j = S.length()-1;
        while(i <= j){
            if(Character.isLetter(chars[i]) && Character.isLetter(chars[j])){
                char tmp = chars[i];
                chars[i] = chars[j];
                chars[j] = tmp;
                i++;
                j--;
            }
            if(!Character.isLetter(chars[i])){
                i++;
            }
            if(!Character.isLetter(chars[j])){
                j--;
            }
        }
        return new String(chars);
    }
    public boolean isBalance(TreeNode root) {
        if(root == null){
            return true;
        }
        return Math.abs(getDepth(root.left)-getDepth(root.right)) <= 1 && isBalance(root.left) && isBalance(root.right);
    }

    private int getDepth(TreeNode root) {
        if(root == null){
           return 0;
        }
        return 1+Math.max(getDepth(root.left),getDepth(root.right));
    }
    public int[] sortArrayByParity(int[] A) {
        int i = 0;
        int j = A.length-1;
        while(i < j){
            while(i < j&&A[i] % 2 ==0){
                i++;
            }
            while(i < j && A[j] % 2 != 0){
                j--;
            }
            int tmp = A[i];
            A[i] = A[j];
            A[j] = tmp;
        }
        return A;
    }
    public static int pivotIndex(int[] nums){
        int len = nums.length;
        if(len == 0){
            return -1;
        }
        int sum = nums[0];
        int res = 0;
        int[] dp = new int[len];//左侧的和
        for(int i = 1; i < len; i++ ){
            dp[i] = nums[i-1] + dp[i-1];
            sum += nums[i];
        }
        for(int i = 0; i < len; i++){
            if(dp[i] == sum-dp[i]-nums[i]){
                return i;
            }
        }
        return -1;
    }
    public static  int maxComDiv(int num1 , int num2){
        if( num1 % num2 ==0){
            return num2;
        }
        return maxComDiv(num2,num1%num2);
    }

    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       while(sc.hasNext()){
           int n = sc.nextInt();//怪物的个数
           int res = sc.nextInt();//小易的初始能力值
           int[] nums = new int[n];
           for(int i = 0; i < n; i++){
               nums[i] = sc.nextInt();
               if(nums[i] <= res){
                   res += nums[i];
               }else{
                   res += maxComDiv(res, nums[i]);
               }
           }
           System.out.println(res);
       }
    }

    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int nums[] =new  int[N];//n个整数
        int num1=0,num2=0,num3=0,num4=0,num5=0,flag =1,count=0;

        for(int i =0;i<N;i++){
            nums[i] = sc.nextInt();
            //A1
            if(nums[i] % 5 == 0){
                if(nums[i] % 2 == 0) {
                    num1 = num1 + nums[i];
                }
            }
            //A2
            if(nums[i] % 5 == 1){
                num2=num2+flag*nums[i];
                flag = -flag;
            }
            //A3
            if(nums[i] % 5 == 2){
                num3++;
            }
            //A4
            if(nums[i] % 5 == 3){
                num4=num4+nums[i];
                count++;
            }
            //A5
            if(nums[i] % 5 == 4){
                if(nums[i] > num5) {
                    num5 = nums[i];
                }
            }
        }
        if(num1 != 0){
            System.out.print(num1+" ");
        }else{
            System.out.print("N"+" ");
        }
        if(num2 != 0){
            System.out.print(num2+" ");
        }else{
            System.out.print("N"+" ");
        }
        if(num3 != 0){
            System.out.print(num3+" ");
        }else{
            System.out.print("N"+" ");
        }
        if(num4 != 0){
            System.out.print(num4/count+"."+(int)((num4%count*100/count+5)/10)+" ");
        }else{
            System.out.print("N"+" ");
        }
        if(num5 != 0){
            System.out.print(num5);
        }else{
            System.out.print("N");
        }
    }
}
