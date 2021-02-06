package java_0201;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String[] s = "sda    dad".split(" ");
        for(String str: s){
            System.out.println(str);
        }
//        Scanner sc = new Scanner(System.in);
//        while(sc.hasNext()){
//            String str = sc.nextLine();
//            print(str);
//        }
    }
    private static void print1(String str){
        str = str.trim();
        int i = str.length()-1;
        int j = i;
        StringBuilder sb = new StringBuilder();
        while(i >= 0){
            while(i >= 0 && str.charAt(i) != ' '){
                i--;
            }
            sb.append(str.substring(i+1,j+1)+" ");
            while(i >=0 && str.charAt(i) == ' '){
                i--;
            }
            j = i;
        }
        System.out.println(sb.toString().trim());
    }
    private static void print(String str){
        String[] s = str.split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i = s.length-1; i >=0; i++){
            if(s.equals(" ")){
                continue;
            }
            sb.append(s[i]+" ");
        }
        System.out.println(sb.toString().trim());
    }
}
