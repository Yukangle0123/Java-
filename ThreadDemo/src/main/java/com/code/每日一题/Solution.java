package com.code.每日一题;

import java.util.*;

class Student{
    String name;
    int score;
    Student(String name,int score){
        this.name = name;
        this.score =score;
    }
}


public class Solution {
    public boolean[] chkSubStr(String[] p, int n, String s) {
        boolean[] exists = new boolean[p.length];
        int index = 0 ;
        for(String str : p){
            exists[index++] = s.contains(str);
        }
        return exists;
    }

    /**
     * 最后一个单词的长度
     * @param s
     * @return
     */
    public static int lengthOfLastWord(String s) {
        int len = s.length();
        char[] chars = s.toCharArray();
        int end = len - 1;
        while(end >= 0 && chars[end] == ' '){
            end--;
        }
        if(end < 0){
            return 0;
        }
        int start = 0;
        while(start < end && chars[start]!= ' '){
            start++;
        }
        return end - start+1;
    }

    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int nums = sc.nextInt();
            int model = sc.nextInt();
            List<Student> list = new ArrayList<>();
            for (int i = 0; i < nums; i++ ) {
                list.add(new Student(sc.next(),sc.nextInt()));
            }
            list.sort(new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    if(model == 0){
                        return o2.score - o1.score;
                    }else{
                        return o1.score - o2.score;
                    }
                }
            });
            for(Student student : list){
                System.out.println(student.name +" "+ student.score);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("a "));
    }
}
