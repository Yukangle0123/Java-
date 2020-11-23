package meiriyiti;

import java.util.Scanner;

public class Main {
    private static boolean isPalindromeString(String s){
        int left=0;
        int right=s.length()-1;
        while(left<right){
            if(s.charAt(left)!=s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    public static int countAll(String A,String B){
        int len=A.length();
        int count=0;
        for(int i=0;i<=len;i++){
          String s= A.substring(0,i)+B+A.substring(i,len);
          if(isPalindromeString(s)){
              count++;
          }
        }
        return count;
    }
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNext()) {
            String A = scanner.next();
            String B = scanner.next();
            System.out.println(countAll(A, B));
        }

    }
}

