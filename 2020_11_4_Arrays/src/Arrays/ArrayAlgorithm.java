package Arrays;

public class ArrayAlgorithm {
    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * @param nums 输入的数组
     */
    public void moveZeroes(int[] nums) {
        int j = 0;
        for(int i=0;i<nums.length;i++) {
            //当前元素!=0，就把其交换到左边，等于0的交换到右边
            if(nums[i]!=0) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j++] = tmp;
            }
        }
    }
    public int removeDuplicates(int[] nums) {
        int i=0;
        for(int j=0;j<nums.length;j++){
            if(nums[j]!=nums[i]){
                i++;
                nums[i]=nums[j];
            }
        }
        System.out.println("caca");
        return i+1;
    }
//    我们使用了两个指针，i 是遍历指针，指向当前遍历的元素；j 指向下一个要覆盖元素的位置。
//    同样，我们用 count 记录当前数字出现的次数。count 的最小计数始终为 1。
//    我们从索引 1 开始一次处理一个数组元素。
//    若当前元素与前一个元素相同，即 nums[i]==nums[i-1]，则 count++。若 count > 2，则说明遇到了多余的重复项。在这种情况下，我们只向前移动 i，而 j 不动。
//    若 count <=2，则我们将 i 所指向的元素移动到 j 位置，并同时增加 i 和 j。
//    若当前元素与前一个元素不相同，即 nums[i] != nums[i - 1]，说明遇到了新元素，则我们更新 count = 1，并且将该元素移动到 j 位置，并同时增加 i 和 j。
//    当数组遍历完成，则返回 j。


    public int removeDuplicates2(int[] nums) {
        int j=1;int count=1;
        for(int i=1;i<nums.length;i++){
            if(nums[i]==nums[i-1]){
                count++;
            }else{
                count=1;
            }
            if(count<=2){
                nums[j++]=nums[i];
            }

        }
        return j;
    }
    public void sortColors(int[] nums){
        int p0=0;int p2=nums.length-1;
        for(int i=0;i<nums.length;i++){
            while(i<=p2&&nums[i]==2){
                int tmp=nums[p2];
                nums[p2]=nums[i];
                nums[i]=tmp;
                --p2;
            }
            if(nums[i]==0){
                int tmp=nums[p0];
                nums[p0]=nums[i];
                nums[i]=tmp;
                ++p0;
            }
        }
    }

    public static void main(String[] args) {
        int[]nums=new int[]{1,1,1,2,2,3};
        ArrayAlgorithm a=new ArrayAlgorithm();
        a.removeDuplicates2(nums);
        System.out.println("dadad");
    }
}
