package leetcode_692;

import java.util.*;

public class Solution {
    private static class wordsAndCount implements Comparable<wordsAndCount>{
        String str;
        int count;
        wordsAndCount(String str,int count){
            this.str=str;
            this.count=count;
        }


        @Override
        public int compareTo(wordsAndCount o) {
            if(this.count<o.count){
                return 1;
            }else if(this.count>o.count){
                return -1;
            }else{
                return this.str.compareTo(o.str);
            }
        }
    }
    public List<String> topKFrequent(String[] words, int k) {
        Map<String,Integer>map=new HashMap<>();
        for(String s:words){
            int count=map.getOrDefault(s,0)+1;
            map.put(s,count);
        }

        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        wordsAndCount[]wordsAndCounts=new wordsAndCount[map.size()];
        int index=0;
        for(Map.Entry<String, Integer> e:entries){
            String key = e.getKey();
            Integer value = e.getValue();
            wordsAndCounts[index++]=new wordsAndCount(key,value);
        }
        Arrays.sort(wordsAndCounts);
        List<String>list=new LinkedList<>();
        for(int i=0;i<k;i++){
            list.add(wordsAndCounts[i].str);
        }
        return list;
    }

    public static void main(String[] args) {
        String[]str=new String[]{"i", "love", "leetcode", "i", "love", "coding"};
        Solution s=new Solution();
        List<String> list = s.topKFrequent(str, 2);
        for(String s1:list){
            System.out.println(s1);
        }
    }
}
