package java_0131;

import java.math.BigInteger;
import java.util.Arrays;

import java.util.Scanner;


public class Main {
    public static void main1(String[] args) {
        Scanner sc =new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++){
            nums[i] = sc.nextInt();
        }
        System.out.println(count(nums,target));
    }

    private static long count(int[] nums,int target) {
        int len = nums.length;
        long[][] res = new long[len][target+1];
        res[0][0] = 1;
        if(nums[0] <= target){
            res[0][nums[0]] = 1;
        }

        for(int i = 1; i < len; i++){
            for(int j = 0; j <= target; j++){
                res[i][j] = res[i-1][j];

                if(j >= nums[i]){
                    res[i][j] += res[i-1][j-nums[i]];
                }
            }
        }
        return res[len-1][target];
    }

    public static void main3(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int n = sc.nextInt();
            int flag = 0;
            int[] nums = new int[n];
            for(int i = 0; i < n; i++){
                nums[i] = sc.nextInt();
                flag ^= nums[i];
            }
            //找到异或值中二进制值为1的位置
            int index = 1;
            while((flag & index ) ==0){
                index <<= 1;
            }
            int ret1 = 0;
            int ret2 = 0;
            for(int num : nums){
                if((num & index) ==0){
                    ret1 ^= num;
                }else{
                    ret2 ^= num;
                }
            }
            if(ret1 < ret2){
                System.out.println(ret1 +" "+ ret2);
            }else{
                System.out.println(ret2+" " + ret1);
            }
        }
    }
    static class stack{
        int[] arr = null;
        int size = 0;
        stack (){
            arr = new int[10];
        }
        public void capacity(){
            int[] arr1 = new int[arr.length*2];
            System.arraycopy(arr,0,arr1,0,arr.length);
            arr = arr1;
        }
        public void push(int val){
            if(size == arr.length){
                capacity();
            }
            arr[size] = val;
            size++;
        }
        public int peek(){
            return arr[size-1];
        }
        public boolean isEmpty(){
            return size == 0;
        }
        public void pop(){
            size--;
        }

    }

    public static void main4(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int n = sc.nextInt();
            int[] nums = new int[n];
            for(int i = 0; i < n; i++){
                nums[i] = sc.nextInt();
            }
            int[] left = new int[n];
            int[] right = new int[n];
            get(nums,left,right);
            for(int i = 0; i < n; i++){
                System.out.println(left[i] + " " + right[i]);
            }
        }
    }

    private static void get(int[] nums, int[] left, int[] right) {
        stack s = new stack();
        for(int i = 0; i < nums.length; i++){
            while(!s.isEmpty() && nums[s.peek()] >= nums[i]){
                s.pop();
            }
            if(s.isEmpty()){
                left[i] = -1;
            }else{
                left[i] = s.peek();
            }
            s.push(i);
        }
        stack s1 = new stack();
        for(int i = nums.length-1; i >= 0; i--){
            while(!s1.isEmpty() && nums[s1.peek()] >= nums[i]){
                s1.pop();
            }
            if(s1.isEmpty()){
                right[i] = -1;
            }else{
                right[i] = s1.peek();
            }
            s1.push(i);
        }
    }

    public static void main5(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int N = sc.nextInt();
            BigInteger[] b = new BigInteger[N];
            for(int i = 0 ; i < N; i++){
                b[i] = sc.nextBigInteger();
            }
            Arrays.sort(b);
            for(int i = 0; i < N; i++){
                System.out.println(b[i]);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String str = sc.nextLine();
            adjust(str.toCharArray());
        }
    }

    private static void adjust(char[] chars) {
        for(int i = 0; i < chars.length; i++){
            int flag = 1;
            int[] res = new int[8];
            int index = 7;
            int sum = 0;
            while(index > 0){
                if((chars[i] & flag) != 0 ){
                    res[index] = 1;
                    sum++;
                }
                flag <<= 1;
                index--;
            }
            if(sum % 2 == 0){
                res[0] =1;
            }
            for(int j = 0; j <= 7; j++ ){
                System.out.print(res[j]);
            }
            System.out.println();
        }
    }
}
