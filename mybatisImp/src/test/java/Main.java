public class Main {
    public static int count(int[] nums,int num){
        if( nums == null ||nums.length == 0 || num > nums[nums.length - 1] || num < nums[0]){
            return 0;
        }
        int left = 0;
        int right = nums.length;
        int mid = 0;
        while(left < right){
            mid = (left + right) >> 1;
            if(nums[mid] < num){
                left = mid + 1;
            }else if(nums[mid] > num){
                right = mid - 1;
            }else{
                break;
            }
        }
        int res = 0;
        int index = mid - 1;
        while(mid < right){
            if(nums[mid] != num){
               break;
            }
            res++;
            mid++;
        }
        while(index >= 0){
            if(nums[index] != num){
                break;
            }
            index--;
            res++;
        }
        return res;
    }
    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,1,1,1,1,1,1,1,1,1};
        int count = count(nums, 1);
        System.out.println(nums.length);
        System.out.println(count);
    }
}
