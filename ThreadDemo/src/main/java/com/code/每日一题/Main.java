package com.code.每日一题;






import java.util.*;
class ListNode{
    int val;
    ListNode next;
    ListNode(int val){
        this.val = val;
    }
}

public class Main {
    public static void main1(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Character> list = new ArrayList<>();
        for(char i = 'A'; i <= 'Z'; i++ ){
            list.add(i);
        }
        while(scanner.hasNext()){
            String str = scanner.nextLine();
            int len = str.length();
            char[] chars = str.toCharArray();
            char[] res = new char[len];
            for(int i = 0; i < len; i++){
                if(chars[i] == ' '){
                    res[i] = ' ';
                }else{
                    int index =chars[i] - 'A' - 5;
                    if(index<0){
                        index += 26;
                    }
                    char c = list.get(index);
                    res[i] = c;
                }
            }
            System.out.println(res);
        }
    }
    public static int getTotalCount1(int month){
        if(month <= 2 ){
            return 1;
        }
        int[] everyCount = new int[month+1];
        everyCount[3] = 1;
        for(int i = 4 ; i <= month; i++){
            everyCount[i] = everyCount[i-1]+ everyCount[i-2];
        }
        int res = 1;
        for(int i =0; i <= month ;i++){
            res += everyCount[i];
        }
        return res;
    }
    public static int getTotalCount(int month){
        if(month == 1 || month ==2 ){
            return 1;
        }
        if(month == 0){
            return 0;
        }
        return getTotalCount(month-1)+getTotalCount(month-2);
    }
    public ListNode plusAB(ListNode a, ListNode b) {
            ListNode fakeNode = new ListNode(-1);
            ListNode cur = fakeNode;
            int sum =0;
            int carry = 0;//进位
            while(a!=null && b!=null){
                sum = a.val + b.val + carry;
                cur.next = new ListNode(sum%10);
                cur = cur.next;
                carry = sum/10;
                a = a.next;
                b = b.next;
            }
            if(a == null && b == null){
                if(carry != 0 ){
                    cur.next =new ListNode(carry);
                    return fakeNode.next;
                }
            }
            if(a == null && b !=null){
                while(b!=null) {
                    sum = b.val + carry;
                    cur.next = new ListNode(sum%10);
                    carry = sum/10;
                    b = b.next;
                    cur = cur.next;
                }
            }
            if(a!=null){
                while(a!=null){
                    sum = a.val + carry;
                    cur.next = new ListNode(sum%10);
                    carry = sum/10;
                    a = a.next;
                    cur = cur.next;
                }
            }
            if(carry!=0){
                cur.next = new ListNode(carry);
            }
            return fakeNode.next;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String response = sc.nextLine();
            String request = sc.nextLine();
            Map<Character,Integer> map = new HashMap<>();
            for(int i = 0; i < response.length(); i++){
                int count = map.getOrDefault(response.charAt(i),0)+1;
                map.put(response.charAt(i),count);
            }
            int res =0;
           for(int i = 0; i < request.length(); i++){
               int count = map.getOrDefault(request.charAt(i),0);
               if(count<=0){
                   res++;
               }
               map.put(request.charAt(i),count-1);
           }
           if(res == 0){
               System.out.println("Yes"+" "+(response.length()-request.length()));
           }else{
               System.out.println("No"+" "+res);
           }
        }
    }
}
