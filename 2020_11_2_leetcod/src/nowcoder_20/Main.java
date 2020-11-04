package nowcoder_20;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);

        String input=s.nextLine();//想输入的
        String realInput=s.nextLine();//实际输入

        input=input.toUpperCase();
        realInput=realInput.toUpperCase();

        Set<Character> set=new HashSet<>();
        Set<Character>remDuplicate=new HashSet<>();//去重

        for(int i=0;i<realInput.length();i++){
            set.add(realInput.charAt(i));
        }
        for(int i=0;i<input.length();i++) {
            if (!set.contains(input.charAt(i))) {
                if (!remDuplicate.contains(input.charAt(i))) {
                    System.out.print(input.charAt(i));
                    remDuplicate.add(input.charAt(i));
                }
            }

        }
        System.out.println();
    }
}
