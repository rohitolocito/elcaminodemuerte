package leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class LeetcodeHard {

	public static void main(String[] args) {
	
		LeetcodeHard run = new LeetcodeHard();
		
		System.out.println(run.removeInvalidParentheses("()())()"));
		System.out.println(run.minWindowInefficient("acbbaca", "aba"));
		System.out.println(run.minWindow("acbbaca", "aba"));
		
		System.out.println(run.minWindowInefficient("cabwefgewcwaefgcf", "cae"));
		System.out.println(run.minWindow("cabwefgewcwaefgcf", "cae"));
		
		//int arr[] = {0,3,7,2,5,8,4,6,0,1};
		int arr[] = {100, 4, 200, 1, 3, 2};
		System.out.println(run.longestConsecutive(arr));
		
		System.out.println(run.numberOfPatterns(3, 6));
		
		int arr1[] = {3,4,6,5};
		int	arr2[] = {9,1,2,5,8,3};
		System.out.println(run.maxNumber(arr1, arr2, 5));
	}
	
	
	public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        
        int res[] = new int[k];
        int maxVal[] = {0};
        maxNumber(nums1, 0, nums2, 0, 0, maxVal, k);
        int index = k-1;
        while (index >= 0) {
            int dig = maxVal[0] % 10;
            res[index--] = dig;
            maxVal[0] = maxVal[0]/10;
        }
        
        return res;
    }
    
    private void maxNumber(int[] nums1, int index1, int[] nums2, int index2, int val, int maxVal[], int k) {
        
        if (k == 0) {
            maxVal[0] = Math.max(val, maxVal[0]);
            return;
        }
        
        while (index1 < nums1.length && index2 < nums2.length) {
            int max = Math.max(nums1[index1], nums2[index2]);
            if (nums1[index1] > nums2[index2]) {
                index1++;
            } else if (nums1[index1] < nums2[index2]) {
                index2++;
            } else {
                index1++;
                index2++;
            }
            maxNumber(nums1, index1, nums2, index2, 10*val + max, maxVal, k-1);
        }
        
        while (index1 < nums1.length && k > 0) {
            val = val*10 + nums1[index1++];
            k--;
        }
        
        while (index2 < nums2.length && k > 0) {
            val = val*10 + nums2[index2++];
            k--;
        }
        
        maxNumber(nums1, index1, nums2, index2, val, maxVal, k);
    }
	
	//android lock patterns
    public int numberOfPatterns(int m, int n) {
        
        int count = 0;
        boolean used[] = new boolean[9];
        
        for (int len=m; len<=n; len++) {
            count += getCount(-1, len, used);
        }
        
        return count;
    }
    
    private int getCount(int last, int len, boolean[] used) {
        
        if (len == 0)
            return 1;
        
        int count = 0;
        
        for (int i=0; i<9; i++) {
            if(isValid(last, i, used)) {
                used[i] = true;
                count += getCount(i, len-1, used);
                used[i] = false;
            }
        }
        
        return count;
    }
    
    private boolean isValid(int last, int index, boolean[] used) {
        
        if (used[index])
            return false;
        
        if (last == -1)
            return true;
        
        if ((index + last) % 2 == 1) 
            return true;
        
        int mid = (index + last)/2;
        
        if (mid == 4)
            return used[mid];
        
        if (index%3 != last%3 && index/3 != last/3)
            return true;
        
        return used[mid];
            
    }
	
	public int longestConsecutive(int[] nums) {
	       
        Set<Integer> set = new HashSet<>();
        
        for (int n: nums) {
            set.add(n);
        }
        
        int maxCons = 0;
        
        //[100, 4, 200, 1, 3, 2],
        for (int n: nums) {
            if (!set.contains(n-1)) {
                int len = 0;
                int x = n;
                while (set.contains(x)) {
                    len++;
                    x++;
                }
                maxCons = Math.max(maxCons, len);
            }
        }
        
        return maxCons;
    }
	
    private class ValueIndex {
        int val;
        int i;
        
        public ValueIndex(int val, int i) {
            this.val = val;
            this.i = i;
        }
    }
    
    public int[] maxSlidingWindow(int[] nums, int k) {
        
        if (k > nums.length || nums.length == 0) {
            return new int[]{};
        }
        
        int[] res = new int[nums.length-k+1];
        int index = 0;
        
        PriorityQueue<ValueIndex> queue = new PriorityQueue<ValueIndex>(new Comparator<ValueIndex>() {
            
            @Override
            public int compare(ValueIndex v1, ValueIndex v2) {
                return v2.val - v1.val;    
            }
            
        });
        
        //[1,3,-1,-3,5,3,6,7], k =3
        
        for (int i=0; i<k; i++) {
            queue.add(new ValueIndex(nums[i], i));
        }
        
        for (int i=k; i<nums.length; i++) {
            
            res[index++] = queue.peek().val;
            
            while (!queue.isEmpty() && queue.peek().i <= i-k) {
                queue.poll();
            }
            
            queue.add(new ValueIndex(nums[i], i));
        }
        
        res[index++] = queue.peek().val;
        
        return res;
    }
	
    public String minWindow(String s, String t) {
        
        int tCount[] = new int[256];
        
        for (int i=0; i<t.length(); i++) {
            tCount[t.charAt(i)]++;
        }
        
        Result res = null;
        
        int counter = t.length();
        
        int begin=0, end =0;
        
        while (end < s.length()) {
            
            char c = s.charAt(end++);
            
            if (tCount[c]-- > 0) {
                counter --;
            }
            
            while (counter == 0) {
                int len = end - begin;
                
                if (res == null || len < res.len ) {
                    res = new Result(begin, len);
                }
                
                char x = s.charAt(begin++);
                
                if (tCount[x]++ == 0) {
                    counter++;
                }
            }
            
        }
        
        return res == null ? "" : s.substring(res.start, res.start + res.len);
    }
	
	   private class Result {
	        int start;
	        int len;
	        
	        public Result(int start, int len) {
	            this.start = start;
	            this.len = len;
	        }
	    }
	    
	    public String minWindowInefficient(String s, String t) {
	        
	        int tCount[] = new int[256];
	        
	        for (int i=0; i<t.length(); i++) {
	            tCount[t.charAt(i)]++;
	        }
	        
	        Result res = null;
	        
	        int begin=0, end =0;
	        
	        Map<Character, Integer> map = new HashMap<>();
	        
	        while (end < s.length()) {
	            
	            char c = s.charAt(end);
	            
	            if (tCount[c] > 0) {
	                
	                map.put(c, map.getOrDefault(c,0)+1);   
	                
	                
	                while (!map.containsKey(s.charAt(begin)) || map.containsKey(s.charAt(begin)) && map.get(s.charAt(begin)) > tCount[s.charAt(begin)]) {
	                	if (!map.containsKey(s.charAt(begin))) {
	                		begin++;
	                	} else {
	                	   char b = s.charAt(begin);
	 	                   map.put(b, map.get(b)-1);
	 	                   begin++; 
	                	}
	                   
	                }
	                
	                
	                if (end - begin + 1 >= t.length() && match(t, map, tCount)) {
	                    
	                    int len = end-begin+1;
	                    
	                    if (res == null || len < res.len) {
	                        res = new Result(begin, len);
	                    }
	                }
	                
	            }
	            
	            end ++;
	            
	            
	        }
	        
	        return res == null ? "" : s.substring(res.start, res.start + res.len);
	    }
	    
	    private boolean match(String t, Map<Character, Integer> map, int tCount[]) {
	        
	        for (int i=0; i<t.length(); i++) {
	            
	            if (!map.containsKey(t.charAt(i)))
	                return false;
	            
	            if (map.get(t.charAt(i)) != tCount[t.charAt(i)])
	                return false;
	        }
	        
	        return true;
	    }
	
	public List<String> removeInvalidParentheses(String s) {
	       
        List<String> res = new ArrayList<>();
        
        if (s == null || s.isEmpty())
            return res;
        
        Queue<String> queue = new LinkedList<>();
        queue.add(s);
        
        Set<String> visited = new HashSet<>();
        visited.add(s);
        
        boolean found = false;
        
        while (!queue.isEmpty()) {
            
            String str =  queue.poll();
            
            if (isValid(str)) {
                res.add(str);
                found = true;
            }
            
            if (found)
            	continue;
            
            StringBuilder sb = new StringBuilder();
            
            for (int i=0; i<str.length(); i++) {
                
                if (str.charAt(i) != '(' && str.charAt(i) != ')')
                    continue;
                
                sb.append(str.substring(0,i));
                sb.append(str.substring(i+1));
                
                String t = sb.toString();
                
                if (!visited.contains(t)) {
                    queue.add(t);
                    visited.add(t);
                }
                
                sb.setLength(0);
            }
            
        }
        
        return res;
    }
    
    private boolean isValid(String s) {
        int count = 0;
        
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(')
                count++;
            else if (c == ')')
                count--;
            
            if (count <0)
                return false;
        }
        
        return count == 0;
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
