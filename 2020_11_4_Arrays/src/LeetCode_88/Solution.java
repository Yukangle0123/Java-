package LeetCode_88;

public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p=m-1;//nums1有效位置的末尾
        int p1=m+n-1;//nums1的末尾
        int p2=n-1;//nums2的末尾
        while(p>=0&&p2>=0){
            if(nums2[p2]>nums1[p]){
                nums1[p1]=nums2[p2];
                p2--;
                p1--;
            }else if(nums2[p2]<nums1[p]){
                nums1[p1]=nums1[p1-1];
                p--;
                p1--;
            }else{
        }
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }

    public static void main(String[] args) {
        int[]nums1={1,2,3,0,0,0};
        int[]nums2={2,5,6};
        Solution s=new Solution();
        s.merge(nums1,3,nums2,3);
        System.out.println("dada");
    }
}
