package java_0127.meiriyiti;

import java.util.Scanner;

public class Main {
    public static String tenToTwo(long num){
        StringBuilder sb = new StringBuilder();
        while(num > 0){
            sb.append(num%2);
            num /= 2;
        }
        return sb.reverse().toString();
    }
    public static long twoToTen(String str){
        long res = 0;
        int len = str.length() - 1;
        int flg = 0;
        for(int i = len; i >= 0; i--){
            int num = str.charAt(i) - '0';
            if (num !=0) {
                res += Math.pow(2,flg)*num;
            }
            flg++;
        }
        return res;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String IP = sc.nextLine();
            long nums = sc.nextLong();
            String[] split = splits(IP);
            StringBuilder sb = new StringBuilder();
            for(String str : split){
                int num = Integer.parseInt(str);
                sb.append(tenToTwo(num));
            }
            String str = tenToTwo(nums);
            StringBuilder sb2 =new StringBuilder();
            int index = 0;
            int i = 9;
            while(i >= 33){
                if (i<33) {
                    sb2.append(str.substring(index,i)+".");
                }else {
                    sb2.append(str.substring(index,i));
                }
                i += 8;
                index +=8;
            }
            System.out.println(sb.toString());
            System.out.println(sb2.toString());


        }

    }

    private static String[] splits(String ip) {
        ip = ip+".";
        String[] strings = new String[4];
        char[] chars = ip.toCharArray();
        int index = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < chars.length; i++){
            if(chars[i] == '.'){
                strings[index] = sb.toString();
                index++;
                sb = new StringBuilder();
                continue;
            }
            sb.append(chars[i]);
        }
        return strings;
    }
}
