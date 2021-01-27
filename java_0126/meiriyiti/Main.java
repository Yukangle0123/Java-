package java_0126.meiriyiti;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int num = sc.nextInt();
            sc.nextLine();
            LinkedHashMap<String,Integer> map = new LinkedHashMap<>();
            for(int i = 0; i < num; i++ ){
                String name = sc.next();
                map.put(name,0);
            }
            int sum = sc.nextInt();
            sc.nextLine();
            int invalid = 0;
            for(int i = 0; i < sum; i++){
                String name = sc.next();
                if(map.containsKey(name)){
                    map.put(name,map.get(name)+1);
                }else{
                    invalid++;
                }
            }

            for(Map.Entry<String, Integer> entry : map.entrySet()){
                System.out.println(entry.getKey()+" : "+ entry.getValue());
            }
            System.out.println("invalid : " + invalid );
        }
    }
}
