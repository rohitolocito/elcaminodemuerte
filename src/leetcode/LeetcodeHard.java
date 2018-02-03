package leetcode;

public class LeetcodeHard {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	   public int firstMissingPositive(int[] nums) {
	        if (nums == null || nums.length == 0)
	            return 1;
	        
	        int index=0;
	        while(index < nums.length) {
	            if (nums[index] > 0 && nums[index] < nums.length && nums[index] != index+1) {
	                if (nums[index] == nums[nums[index]-1]) {
	                    index++;
	                } else {
	                    int temp = nums[nums[index]-1];
	                    nums[nums[index]-1] = nums[index];
	                    nums[index] = temp;
	                }
	            } else {
	                index++;
	            }
	        }
	        
	        for (int i=0; i<nums.length; i++) {
	            if (nums[i] != i+1) 
	                return i+1;
	        }
	        
	        return nums[nums.length-1] + 1;
	    }

}
