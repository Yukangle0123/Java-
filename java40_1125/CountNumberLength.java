package java40_1125;

import java.util.Scanner;

public class CountNumberLength {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String str=scanner.nextLine();
        int count=0;
        int max=0;
        int right=0;
        for(int i=0;i<str.length();i++){
            char c=str.charAt(i);
            if(c>='0'&&c<='9'){
                count++;
                if(count>max){
                    right=i;
                    max=count;
                }
            }else{
                count=0;
            }
        }
        System.out.println(str.substring(right-max+1,right+1));
    }
}
