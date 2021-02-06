package java0206;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String str = sc.nextLine();
            Map<Character,Integer> map = count(str);
            for(Map.Entry<Character,Integer> entry : map.entrySet()){
                System.out.println(entry.getKey()+":"+entry.getValue());
            }
        }
    }

    private static Map<Character, Integer> count(String str) {
        Map<Character,Integer> map = new HashMap<>();
        for(char i = 'A'; i <= 'Z'; i++){
            map.put(i,0);
        }
        for(char c : str.toCharArray()){
            if(c >= 'A' && c <= 'Z'){
                int count = map.getOrDefault(c,0)+1;
                map.put(c,count);
            }
        }
        return map;
    }
}
