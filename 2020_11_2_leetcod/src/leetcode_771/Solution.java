package leetcode_771;

import java.util.Set;
import java.util.TreeSet;

public class Solution {
    public int numJewelsInStones(String J, String S) {

        Set<Character> set=new TreeSet<>();
        for(int i=0;i<J.length();i++){
            set.add(J.charAt(i));
        }
        int ans=0;
        for(int i=0;i<S.length();i++){
            if(set.contains(S.charAt(i))){
                ans++;
            }
        }
        return ans;

    }
}
