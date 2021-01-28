package java_0127.meiriyiti;

import java.util.*;


public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int num = sc.nextInt();
            List<Integer> list =new LinkedList<>();
            for(int i = 0; i < num; i++){
                list.add(sc.nextInt());
            }
            int target = sc.nextInt();
            int res = list.indexOf(Integer.valueOf(target));
            System.out.println(res);
        }
    }
}
