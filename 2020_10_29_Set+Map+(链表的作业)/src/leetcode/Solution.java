package leetcode;

import java.util.*;

public class Solution {
    public int carFleet(int target, int[] position, int[] speed){
        int len=position.length;
        cars[] c=new cars[len];
        for(int i=0;i<len;i++){
            c[i]=new cars(position[i],(double) (target-position[i])/speed[i]);
        }
        Arrays.sort(c, (a, b) -> Integer.compare(b.position,a.position));
        int ans=1;
        for(int i=0;i<len-1;i++){
            if(c[i].time<c[i+1].time){
                ans++;
            }else{
                c[i+1]=c[i];
            }
        }
        return ans;
    }
    public void sortColors(int[] nums) {
        int left=0;
        int right=nums.length-1;
        int[]copy=new int[nums.length];
        for(int i=0;i<nums.length;i++){
            if(nums[i]==0){
                copy[left++]=0;
            }else if(nums[i]==2){
                copy[right--]=2;
            }
        }
        for(int i=left;i<=right;i++){
            copy[i]=1;
        }
        for(int i=0;i<nums.length;i++){
            nums[i]=copy[i];
        }
    }
    public String mostCommonWord(String paragraph, String[] banned) {
        paragraph+=".";
        Map<String,Integer> map=new HashMap<>();
        Set<String>set=new HashSet<>();
        String ans="";
        int count=0;
        for(String str:banned){
            set.add(str);
        }
        StringBuffer sb=new StringBuffer();
        for(Character c:paragraph.toCharArray()){
            if(Character.isLetter(c)){
                sb.append(c);
            }else if(sb.length()>0){
                String s = sb.toString().toLowerCase();
                System.out.println(s);
                if(!set.contains(s)){
                    map.put(s,map.getOrDefault(s,0)+1);
                    if(map.get(s)>count){
                        count=map.get(s);
                        ans=s;
                    }
                }
                sb=new StringBuffer();
            }
        }
        return ans;
    }
    public int findMaxLength(int[] nums) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>(len);
        // 下标 0 之前的位置是 -1
        map.put(0, -1);
        int res = 0;
        int preSum = 0;
        // 把数组中的 0 都看成 -1
        for (int i = 0; i < len; i++) {
            // 把 0 视为 -1，pre 是先加了，所以后面计算的时候是 i - map.get(preSum)，注意下标 + 1 和不加 1 的差别
            if (nums[i] == 1) {
                preSum += 1;
            } else {
                preSum += -1;
            }

            if (map.containsKey(preSum)) {
                res = Math.max(res, i - map.get(preSum));
            } else {
                // 只记录这个数字第 1 次出现的下标
                map.put(preSum, i);
            }
        }
        return res;
    }
    public int maxArea(int[] height) {
//        int minHeight=0;
//        int maxArea1=0;
//        for(int i=0;i<height.length-1;i++){
//            for(int j=i+1;j<height.length;j++){
//                minHeight=Math.min(height[i],height[j]);
//                maxArea1=Math.max(maxArea1,minHeight*(j-i));
//            }
//        }
//        return maxArea1;
        int area=0;
        int minHeight=0;
        int maxarea=0;
        int left=0 ,right=height.length-1;
        while(left<right){
            area=Math.min(height[left],height[right])*(right-left);
            maxarea=Math.max(area,maxarea);
            if(height[left]<height[right]){
                left++;
            }else{
                right--;
            }
        }
        return maxarea;

    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>>list=new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            int right=nums.length-1;
            int target=-nums[i];
            for(int j=i+1;j<nums.length;j++){
                if(j>i+1&&nums[j]==nums[j-1]){
                    continue;
                }
                while(right>j&&nums[j]+nums[right]>target){
                    right--;
                }
                if(right==j){
                    break;
                }
                if(nums[j]+nums[right]==target){
                    List<Integer>list1=new ArrayList<>();
                    list1.add(nums[i]);
                    list1.add(nums[j]);
                    list1.add(nums[right]);
                    list.add(list1);
                }
            }
        }
        return list;
    }
    public Node copyRandomList(Node head) {
        if(head==null){
            return null;
        }
        Map<Node,Node>map=new TreeMap<>(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return o1.hashCode()-o2.hashCode();
            }
        });
        Node current=head;
        while(current!=null){
            Node newNode=new Node(current.val);

            newNode.next=current.next;
            current.next=newNode;

            current=newNode.next;
        }
        Node oldCurrent=head;
        while(oldCurrent!=null){
            Node newCurrent=oldCurrent.next;
            if(oldCurrent.random!=null){
                newCurrent.random=oldCurrent.random.next;
            }
            oldCurrent=newCurrent.next;
        }
        oldCurrent=head;
        Node newHead=head.next;
        while(oldCurrent!=null){
            Node newCurrent=oldCurrent.next;

            oldCurrent.next=newCurrent.next;
            if(newCurrent.next!=null){
                newCurrent.next=newCurrent.next.next;
            }

            oldCurrent=oldCurrent.next;

        }
        return newHead;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
       int []height={1,8,6,2,5,4,8,3,7};
        System.out.println(solution.maxArea(height));
    }
}
class cars {
    double time;
    int position;

    public cars(int position, double time) {
        this.time = time;
        this.position = position;
    }

    @Override
    public String toString() {
        return "cars{" +
                "time=" + time +
                ", position=" + position +
                '}';
    }

}
