package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetcodeMedium2 {
	
	public static void main(String[] args) {
		
		LeetcodeMedium2 run = new LeetcodeMedium2();
		
		int[] arr = {4,3,2,7,8,2,3,1};
		System.out.println(run.findDuplicates(arr));
		
		System.out.println(run.numDecodings("12"));
		
	}
	
    public int numDecodings(String s) {
    	if (s == null || s.isEmpty())
    		return 0;
    	
        return decodeWays(s);
    }
    
    private int decodeWays(String s) {
   
        if (s == null)
            return 0;
        
        if (s.isEmpty())
        	return 1;
        
        int count = 0;
        
        for (int i=0; i<s.length(); i++) {
        	if (isValid(s.substring(0, i+1)))
        		count += decodeWays(s.substring(i+1));
        }
        
        return count;
    }
    
    private boolean isValid(String s) {
        
        if (s == null || s.isEmpty())
            return false; 
        
        if (s.length() > 2)
        	return false;
        
        int v = Integer.parseInt(s);
        return v >= 1 && v <= 26;
    }
	
	public List<Integer> findDuplicates(int[] nums) {
        
        List<Integer> list = new ArrayList<>();
        
        for (int i=0; i<nums.length; i++) {
            int index = Math.abs(nums[i]) -1;
            if (nums[index] < 0) { //index+1 has already appeared in the array
                list.add(index+1);
            } else {
                nums[index] = -nums[index];
            }
        }
        
        return list;
    }
}
