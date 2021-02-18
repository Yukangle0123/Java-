package java_0218;

import java.util.*;
public class Solution {
    ArrayList<String> ans = new ArrayList<>();
    char[] c;
    public ArrayList<String> Permutation(String str) {
        c = str.toCharArray();
        if (str != null && str.length() > 0) {
            dfs(0);
            Collections.sort(ans);
        }
        return ans;
    }
    public void dfs(int x){
        if(x == c.length-1){
            ans.add(String.valueOf(c));
            return;
        }
        HashSet<Character> set = new HashSet<>();
        for(int i = x; i < c.length; i++){
            if(set.contains(c[i])) {
                continue;
            }
            set.add(c[i]);
            swap(i,x);
            dfs(x+1);
            swap(i,x);

        }
    }
    public void swap(int i, int j){
        char c1 = c[i];
        c[i] = c[j];
        c[j] = c1;
    }
    public int MoreThanHalfNum_Solution(int [] array) {
        int flag = 0;
        int ans = 0;
        for(int n : array){
            if(flag == 0){
                ans = n;
            }
            flag += (ans == n) ? 1 : -1;
        }
        int count = 0;
        for(int n : array){
            if(n == ans){
                count++;
            }
        }
        return count > array.length/2 ? ans : 0;
    }
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> ans = new ArrayList<>();
        Arrays.sort(input);
        for(int i = 0; i < k; i++){
            ans.add(input[i]);
        }
        return ans;
    }
    public int FirstNotRepeatingChar(String str) {
        char[] chars = str.toCharArray();
        Map<Character,Integer> map = new HashMap<>();
        for(char c : chars){
            int count = map.getOrDefault(c, 0);
            map.put(c,count);
        }
        for(int i = 0; i < chars.length; i++){
            int count = map.get(chars[i]);
            if(count == 1){
                return i;
            }
        }
        return -1;
    }
}
