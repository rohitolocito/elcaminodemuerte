package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
    
    @Override
    public String toString() {
    	return String.valueOf(val);
    }
}

class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
     
     @Override
     public String toString() {
     	return String.valueOf(val);
     }
}

class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;
};

public class LeetcodeEasy {

	public static void main(String[] args) {
		
		LeetcodeEasy run = new LeetcodeEasy();
		
		System.out.println(Math.floor(Math.log(3)) + 1);
		System.out.println(1 << 2);
		
		System.out.println(run.reverseWords("Let's take LeetCode contest"));
		
		int[] candies = {1,1,2,3};
		System.out.println(run.distributeCandies(candies));
		
		int mat[][] = {{1,1},{1,1}};
		System.out.println(run.islandPerimeter(mat));
		
		System.out.println(Math.ceil(Math.log(31)) + 1);
		
		System.out.println(Integer.bitCount(31));
		
		StringBuilder sb = new StringBuilder("abc");
		sb.setLength(1);
		System.out.println(sb.toString());
		
		Character.isUpperCase('C');
		
		System.out.println(('A' + 26));
		
		System.out.println(-1 % 9);
		
		System.out.println(run.countBinarySubstrings("10101"));
		
		int[] arr = {4,3,2,7,8,2,3,1};
		System.out.println(run.findDisappearedNumbers(arr));
		System.out.println("abc".compareTo("bcd"));
		System.out.println(run.sumTwo(321, 123));
		
		TreeNode node1 = new TreeNode(1);
		node1.left = new TreeNode(2);
		node1.right = new TreeNode(3);
		node1.left.right = new TreeNode(4);
		
		System.out.println(run.tree2str(node1));
		
		int[] bits = {0,1,0,0};
		System.out.println(run.isOneBitCharacter(bits));
		
		TreeNode con = new TreeNode(2);
		con.left = new TreeNode(0);
		con.right = new TreeNode(3);
		con.left.left = new TreeNode(-4);
		con.left.right = new TreeNode(1);
		
		run.inOrder(run.convertBST(con));
		System.out.println();
		System.out.println(run.titleToNumber("T"));
		
		int n= 19010;
		int c = 0;
		while (n != 0) {
			c++;
			n = n/26;
		}
		System.out.println(c);
		
		System.out.println(1/Math.pow(26, 10));
		
		System.out.println(run.convertToTitle(1));
		
		int[] input = {1,3,2};
		System.out.println(run.minMoves2(input));
		
		TreeNode node = new TreeNode(1);
		node.left = new TreeNode(2);
		node.right = new TreeNode(3);
		node.left.left = new TreeNode(4);
		node.right.left = new TreeNode(5);
		
		System.out.println(run.sumOfLeftLeaves(node));
		System.out.println(run.findTilt(node));
		
		ListNode listNode = new ListNode(1);
		listNode.next = new ListNode(2);
		listNode.next.next = new ListNode(6);
		listNode.next.next.next = new ListNode(3);
		listNode.next.next.next.next = new ListNode(4);
		listNode.next.next.next.next.next = new ListNode(5);
		listNode.next.next.next.next.next.next = new ListNode(6);
		
		listNode = run.removeElementsIterative(listNode, 6);
//		while (listNode != null) {
//			System.out.print(listNode + " ~>");
//			listNode = listNode.next;
//		}
		
		String list1[] = {"Shogun", "Tapioca Express", "Burger King", "KFC"};
		String list2[] = 	{"KFC", "Shogun", "Burger King"};
		System.out.println(Arrays.toString(run.findRestaurant(list1, list2)));
		
		listNode = run.reverseList(listNode);
		while (listNode != null) {
			System.out.print(listNode + " ~>");
			listNode = listNode.next;
		}
		System.out.println();
		System.out.println(run.validPalindrome("aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"));
		System.out.println(run.validPalindrome("abcab"));
		
		System.out.println(Integer.parseInt("-2"));
		
		int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
		System.out.println(run.minCostClimbingStairs(cost));
		
		System.out.println(run.readBinaryWatch(1));
	}
	
    public List<String> readBinaryWatch(int n) {
        List<String> list = new ArrayList<>();
        
        if (n < 0)
            return list;
        
        for (int hrs=0; hrs<=11; hrs++) {
            for (int mins=0; mins<=59; mins++) {
                if (numOfOnes(hrs) + numOfOnes(mins) == n) {
                    list.add(buildStr(hrs, mins));
                }
            }
            
        }
        
        return list;
    }
    
    private String buildStr(int hrs, int mins) {
        StringBuilder sb = new StringBuilder();
        
        sb.append(hrs);
        
        sb.append(':');

        if (mins >= 0 && mins <= 9) {
            sb.append('0');
            sb.append(mins);
        } else {
            sb.append(mins);
        }
        
        return sb.toString();
    }
    
    private int numOfOnes(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n-1);
            count++;
        }
        return count;
    	//return Integer.bitCount(n);
    }
	
	   public int climbStairs(int n) {
	        if (n < 1)
	            return 0;
	        
	        int dp[] = new int[n+1];
	        return steps(n, dp);
	    }
	    
	    private int steps(int n, int dp[]) {
	        if (n < 0)
	            return 0;
	        
	        if (n == 0 || n == 1)
	            return 1;
	        
	        if (dp[n] > 0)
	            return dp[n];
	        
	        dp[n] = steps(n-1, dp) + steps(n-2, dp);
	        return dp[n];
	    }
	
	public int minCostClimbingStairs(int[] cost) {
        int oneStep = cost[0];
        int twoStep = cost[1];
        for (int i=2; i<cost.length; i++) {
           int bestWith = oneStep + cost[i];
           int bestWithout = twoStep;
           int best = Math.min(bestWith, bestWithout);
           oneStep = best;
           twoStep = bestWith;
        }
        return oneStep;
    }
	
    public int longestPalindrome(String s) {
        Set<Character> set = new HashSet<>();
        int len = 0;
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (set.contains(c)) {
                len +=2;
                set.remove(c);
            } else {
                set.add(c);
            }
        }
        
        return len + (set.isEmpty() ? 0 : 1);
    }
	
    public boolean validPalindrome(String s) {
        boolean deleted = false;
        int i =0, j = s.length() -1;
        while (i < j) {
            char ci = s.charAt(i);
            char cj = s.charAt(j);
            if (ci != cj && deleted) {
                return false;
            } 
            
            if (ci != cj) {
            	//ab...ab
            	if (s.charAt(i+1) == cj && s.charAt(j-1) == ci) {
            		i++;
            		j--;
            	} else if (s.charAt(i+1) == cj) {
            		deleted = true;
                    i++;
                } else if(s.charAt(j-1) == ci) {
                	deleted = true;
                    j--;
                } else {
                    return false;
                }
                
            } else {
                i++;
                j--;
            }
        }
        return true;
    }
	
    public boolean isPalindrome(String s) {
        int i=0, j = s.length() -1;
        while (i < j) {
            char ci = s.charAt(i);
            char cj = s.charAt(j); 
            if (!isAlphanumeric(ci)) {
                i++;
            } else if (!isAlphanumeric(cj)) {
                j--;
            } else {
                ci = getLowerCase(ci);
                cj = getLowerCase(cj);
                if (ci != cj)
                    return false;
                i++;
                j--;
            }
        }
        return true;
    }
    
    private char getLowerCase(char c) {
        if (c >= 'A' && c <= 'Z') {
            return (char) ('a' + c - 'A');
        }
        return c;
    }
    
    private boolean isAlphanumeric(char c) {
        if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9'))
            return true;
        return false;
    }
	
	  public ListNode reverseList(ListNode head) {
	        if (head == null || head.next == null)
	            return head;
	        
	        ListNode next = head.next;
	        ListNode node = reverseList(head.next);
	        
	        if (next != null) {
	        	next.next = head;
	        	head.next = null;
	        }
	        
	        return node;
	    }
	
	 private Map<Character, Integer> romanMap;
	    
	    public int romanToInt(String s) {
	        int prev = 0, val = 0;
	        for (int i=0; i<s.length(); i++) {
	            char c = s.charAt(i);
	            int curr = romanMap.getOrDefault(c, 0);
	            if (curr > prev) {
	                val = val + curr - prev - prev;
	            } else {
	                val = val + curr;
	            }
	            prev = curr;
	            for (Entry<Character, Integer> entry : romanMap.entrySet()) {
	            	
	            }
	        }
	        return val;
	    }
	
	public int findTilt(TreeNode root) {
        if (root == null)
            return 0;
        
        int left = findTilt(root.left);
        int right = findTilt(root.right);
        return left + right + Math.abs( (root.left != null ? root.left.val : 0) - (root.right != null ? root.right.val : 0));
    }
	
    public int sumOfLeftLeaves(TreeNode root) {
        return sumOfLeftLeaves(root, true);
    }
    
    private int sumOfLeftLeaves(TreeNode root, boolean add) {
        if (root == null)
            return 0;
        
        if (root.left == null && root.right == null && add)
            return root.val;
        
        int left = sumOfLeftLeaves(root.left, true);
        int right = sumOfLeftLeaves(root.right, false);
        return left + right;
    }
	
	//53. Maximum Subarray
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int currSum = 0;
        
        for (int i=0; i<nums.length; i++) {
            currSum += nums[i];
            if (currSum > maxSum) {
                maxSum = currSum;
            }
            if (currSum < 0) {
                currSum = 0;
            }
        }
        
        return maxSum;
    }
	
	//462. Minimum Moves to Equal Array Elements II
	   public int minMoves2(int[] nums) {
	        // Arrays.sort(nums); use quick select algo to find median in linear time
	        int median = median(nums);
	        int moves = 0;
	        for (int n : nums) {
	            moves += Math.abs(n-median);
	        }
	        return moves;
	    }
	    
	    private int median(int[] nums) {
	        if (nums.length % 2 == 0) {
	            return (kthSmallest(nums, (nums.length-1)/2 + 1) + kthSmallest(nums, nums.length/2 + 1))/2;
	        } else {
	            return kthSmallest(nums, nums.length/2 + 1);
	        }
	    }
	    
	    private int kthSmallest(int[] nums, int k) {
	        int low = 0;
	        int high = nums.length-1;
	        while (low <= high) {
	            int index = partition(nums, low, high);
	            if (index == k-1)
	                return nums[index];
	            
	            if (index > k-1) {
	                high = index-1;
	            } else {
	                low = index+1;
	            }
	        }
	        
	        return nums[0];
	    }
	    
	    private int partition(int nums[], int low, int high) {
	        int pivot = high;
	        int j = high-1;
	        while (low <= j) {
	            if (nums[low] >= nums[pivot]) {
	                swap(nums, low, j);
	                j--;
	            } else {
	                low++;
	            }
	        }
	        swap(nums, pivot, j+1);
	        return j+1;
	    }
	    
//	    private void swap(int[] nums, int a, int b) {
//	        int temp = nums[a];
//	        nums[a] = nums[b];
//	        nums[b] = temp;
//	    }
	
	//168. Excel Sheet Column Title
	 public String convertToTitle(int n) {
	       String s = "";
	       while (n != 0) {
	           s = (char)('A' + ( (n-1) % 26)) + s;
	           n = (n-1)/26;
	       }
	       return s;
	    }

	
	
	//171. Excel Sheet Column Number
	   public int titleToNumber(String s) {
	        int sum = 0;
	        // for (int i= s.length()-1; i >= 0; i--) {
	        //     sum = sum + (int) Math.pow(26, s.length()-1-i)*(s.charAt(i) - 'A' + 1);
	        // }
	        for (int i=0; i<s.length(); i++) {
	            sum = sum*26 + (s.charAt(i) - 'A' + 1);
	        }
	        return sum;
	    }
	
	public void inOrder(TreeNode node) {
		if (node == null)
			return;
		
		inOrder(node.left);
		System.out.print(node+" ");
		inOrder(node.right);
	}
	
	//538. Convert BST to Greater Tree
	  public TreeNode convertBST(TreeNode root) {
	        convertBST(root, 0);
	        return root;
	    }
	    
	   private int convertBST(TreeNode root, int toAdd) {
	        if (root == null)
	            return 0;
	        
	        int right = convertBST(root.right, toAdd);
	        int originalRootVal = root.val;
	        root.val += toAdd + right;
	        int left = convertBST(root.left, root.val);

	        return left + originalRootVal + right;
	    }
	
	//717. 1-bit and 2-bit Characters
    public boolean isOneBitCharacter(int[] bits) {
        boolean oneBit = true;
        int val = 0;
        for (int i=0; i<bits.length; i++) {
            val = val*10 + bits[i];
            if (val == 10 || val == 11) {
                if (i == bits.length-1)
                    oneBit = false;
                else
                    oneBit = true;
                val = 0;
            }
            if (i %2 == 1 && val == 01) {
                oneBit = false;
            }
            
        }
        
        return oneBit;
    }
	
	  public String tree2str(TreeNode t) {
	        StringBuilder sb = new StringBuilder();
	        tree2str(t, sb);
	        return sb.toString();
	    }
	    
	    private void tree2str(TreeNode t, StringBuilder sb) {
	        if (t == null)
	            return;
	        
	        sb.append(t.val);
	        
	        if (t.left != null) {
	            sb.append('(');
	            tree2str(t.left, sb);
	            sb.append(')');
	        }
	        
	        if (t.right != null) {
	            sb.append('(');
	            tree2str(t.right, sb);
	            sb.append(')');
	        }
	        
	    }
	
	// 8 + 9
	// 1000
	// 1001
	
	// 1 + 2
	// 0001
	// 0010
	
	public int sumTwo(int a, int b) {
		while (b != 0) {
			int sum = a ^ b;
			int carry = (a & b) << 1;
			a = sum;    
			b = carry; 
		}
		return a;
	}
	
	//720. Longest Word in Dictionary
    public String longestWord(String[] words) {
        PriorityQueue<String> queue = new PriorityQueue<>(new Comparator<String>() {
           @Override
            public int compare(String o1, String o2) {
                if (o1.length() > o2.length())
                    return -1;
                else if (o1.length() == o2.length())
                    return o1.compareTo(o2);
                else
                    return 1;
            }
        });
        
        Set<String> set = new HashSet<>();
        for (String s : words) {
            set.add(s);
            queue.add(s);
        }
        
        while (!queue.isEmpty()) {
            String w = queue.poll();
            boolean res = true;
            for (int i = 1; i < w.length() ; i++) {
                String sub = w.substring(0, i);
                if (!set.contains(sub)) {
                    res = false;
                    break;
                }
            }
            if (res)
                return w;
        }
        
        return "";
    }
	
	//389. Find the Difference better solution
	 public char findTheDifferenceBetter(String s, String t) {
		 int c = t.charAt(t.length()-1);
		 for (int i=0; i<s.length(); i++) {
			 c = c ^ s.charAt(i);
			 c = c ^ t.charAt(i);
		 }
		 return (char)c;
	 }
	//389. Find the Difference
	 public char findTheDifference(String s, String t) {
        int sum = 0;
        for (int i=0; i<s.length(); i++) {
            sum -= s.charAt(i) - 'a';
        }
        
        for (int i=0; i<t.length(); i++) {
            sum += t.charAt(i) - 'a';
        }
        
        return (char)('a' + sum);
    }
	
	//283. Move Zeroes
    public void moveZeroes(int[] nums) {
        int lastNonZero = 0;
        
        for (int i=0; i<nums.length; i++) {
            if (nums[i] != 0) {
                swap(nums, lastNonZero++, i);
            }
        }
    }
    
    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
	
	//448. Find All Numbers Disappeared in an Array
	//
	public List<Integer> findDisappearedNumbers(int[] nums) {
		List<Integer> list = new ArrayList<>();
	       for (int i=0; i<nums.length; ) {
	            int index = nums[i] - 1;
	            if (index >= 0 && nums[index] != -1) {
	                nums[i] = nums[index];
	                nums[index] = -1;
	            } else {
	                i++;
	            }
	        }
	        
	        for (int i=0; i<nums.length; i++) {
	            if (nums[i] != -1) {
	                list.add(i+1);
	            }
	        }
	        
	        return list;
    }
	
	//696. Count Binary Substrings
	 public int countBinarySubstrings(String s) {
	        int total = 0;
	        for (int i=0; i < s.length(); i++) {
	            for (int len=2; i+len <= s.length(); len +=2) {
	                int zeroes=0, ones=0;
	                for (int j=i; j < i+len; j++) {
	                    if (s.charAt(j) == '1') {
	                        ones ++;
	                    } else {
	                        zeroes++;
	                    }
	                }
	                if (zeroes != 0 && zeroes == ones) {
	                    total++;
	                }
	            }
	        }
	        return total;
	    }
	
	public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee emp : employees) {
            map.put(emp.id, emp);
        }
        return getImportanceIterative(id, map);
    }
    
    private int getImportance(int id, Map<Integer,Employee> map) {
        if (!map.containsKey(id))
            return 0;
        
        Employee emp = map.get(id);
        int imp = emp.importance;
        for (int sub : emp.subordinates) {
            imp += getImportance(sub, map);
        }
        return imp;
    }
    
    private int getImportanceIterative(int id, Map<Integer,Employee> map) {
        if (!map.containsKey(id))
            return 0;
        
        Stack<Employee> stack = new Stack<>();
        
        Employee emp = map.get(id);
        stack.push(emp);
        
        int imp = 0;

        while (!stack.isEmpty()) {
            emp = stack.pop();
            imp += emp.importance;
            for (int sub : emp.subordinates) {
                stack.push(map.get(sub));
            }
        }
        
        return imp;
    }
	
	  public List<String> binaryTreePaths(TreeNode root) {
	        List<String> list = new ArrayList<>();
	        binaryTreePaths(root, new StringBuilder(), list);
	        return list;
	    }
	    
	    private void binaryTreePaths(TreeNode root, StringBuilder s, List<String> list) {
	        if (root == null)
	            return;
	        
	        int len = s.length();
	        s.append(root.val);
	        
	        if (root.left == null && root.right == null) {
	            list.add(s.toString());
	        }
	        s.append("->");
	        binaryTreePaths(root.left, s, list);
	        binaryTreePaths(root.right, s, list);
	        s.setLength(len);
	    }
	
	public boolean hasAlternatingBits(int n) {
		return ((n & 0x55555555) == 0) || ((n & 0xaaaaaaaa) == 0); 
	}
	
    public List<Double> averageOfLevels(TreeNode root) {
        
        List<Double> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        
        if (root != null)
            queue.add(root);
        
        int count = 0, numOfNodes = 0;
        while (!queue.isEmpty()) {
            if (count == 0) {
                count = queue.size();
                numOfNodes = count;
            }
            double sum = 0;
            while (count != 0) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                count--;
            }
            list.add(sum/numOfNodes);
        }
        return list;
        
    }
	
    private Set<Character> vowels;
    private Map<Character, Integer> charRow ;
    private Map<Character, Character> openCloseMap;
    
    public LeetcodeEasy() {
        vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        
        vowels.add('A');
        vowels.add('E');
        vowels.add('I');
        vowels.add('O');
        vowels.add('U');
        
        charRow = new HashMap<>();
        charRow.put('q', 1);charRow.put('w', 1);charRow.put('e', 1);charRow.put('r', 1);
        charRow.put('t', 1);charRow.put('y', 1);charRow.put('u', 1);charRow.put('i', 1);
        charRow.put('o', 1);charRow.put('p', 1);
        
        charRow.put('a', 2);charRow.put('s', 2);charRow.put('d', 2);charRow.put('f', 2);
        charRow.put('g', 2);charRow.put('h', 2);charRow.put('j', 2);charRow.put('k', 2);
        charRow.put('l', 2);
        
        charRow.put('z', 3);charRow.put('x', 3);charRow.put('c', 3);charRow.put('v', 3);
        charRow.put('b', 3);charRow.put('n', 3);charRow.put('m', 3);
        

        openCloseMap = new HashMap<>();
        openCloseMap.put('(', ')');
        openCloseMap.put('{', '}');
        openCloseMap.put('[', ']');
        
        romanMap = new HashMap<>();
        romanMap.put('M', 1000);
        romanMap.put('D', 500);
        romanMap.put('C', 100);
        romanMap.put('L', 50);
        romanMap.put('X', 10);
        romanMap.put('V', 5);
        romanMap.put('I', 1);
    }
    
public String[] findRestaurant(String[] list1, String[] list2) {
        
        Map<String, Integer> map1 = new HashMap<>();
        for (int i=0; i<list1.length; i++) {
            map1.put(list1[i], i);
        }
        List<String> bucket[] = new List[list1.length + list2.length];
        for (int i=0; i<list2.length; i++) {
            if (map1.containsKey(list2[i])) {
                int index1 = map1.get(list2[i]);
                int index2 = i;
                int sum = index1 + index2;
                if (bucket[sum] == null) {
                    bucket[sum] = new ArrayList<>();
                }
                bucket[sum].add(list2[i]);
            }
        }
        String[] res = null;
        for (int i=0; i<bucket.length; i++) {
            if (bucket[i] != null) {
                List<String> list = bucket[i];
                res = new String[list.size()];
                for (int j=0; j<res.length; j++) {
                    res[j] = list.get(j);
                }
                break;
            }
        }
        
        return res;
        
    }
    
    public ListNode removeElementsIterative(ListNode head, int val) {
        if (head == null)
        	return null;
        
        ListNode node = null, nodeCurr = null;
        
        while (head != null) {
        	if (head.val != val) {
        		if (node == null) {
        			node = head;
        			nodeCurr = head;
        		} else {
        			nodeCurr.next = head;
        			nodeCurr = head;
        		}
        	} else {
        		if (nodeCurr != null)
        			nodeCurr.next = null;
        	}
        	head = head.next;
        }
        
        return node;

    }
    
    public ListNode removeElements(ListNode head, int val) {
        if (head == null)
            return null;
        
        ListNode node = removeElements(head.next, val);
        if (head.val == val)
            return node;
        else {
        	head.next = node;
            return head;
        }

    }
    
    //463. island perimeter
    public int islandPerimeter(int[][] grid) {

        int cells = 0, neighbors = 0;
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    cells ++;
                    if (i+1 < grid.length && grid[i+1][j] == 1)
                        neighbors++;
                    if (j+1 < grid[i].length && grid[i][j+1] == 1)
                        neighbors++;
                }
            }
        }
        return cells*4 - neighbors*2;
    }
    
    //463. Island Perimeter
    public int islandPerimeterIncorrect(int[][] grid) {
        boolean visited[][] = new boolean[grid.length][grid[0].length];
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                if (grid[i][j] == 1)
                    return islandPerimeter(grid, visited, i, j);
            }
        }
        return 0;
    }
    
    private int islandPerimeter(int[][] grid, boolean[][] visited, int row, int col) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || grid[row][col] == 0)
            return 0;

        if (visited[row][col])
            return -2;
        
        visited[row][col] = true;
        
        int perimeter = 4;
        
        perimeter += islandPerimeter(grid, visited, row, col+1);
        perimeter += islandPerimeter(grid, visited, row+1, col);
        perimeter += islandPerimeter(grid, visited, row, col-1);
        perimeter += islandPerimeter(grid, visited, row-1, col);
        
        return perimeter;
        
    }
    
    private class CandyCount implements Comparable<CandyCount> {
        int kind;
        int count;
        
        public CandyCount(int kind, int count) {
            this.kind = kind;
            this.count = count;
        }
        
        public int compareTo(CandyCount o) {
            if (this.count > o.count)
                return -1;
            else
                return 1;
        }
    }
    
    //575. Distribute Candies
    public int distributeCandiesSimple(int[] candies) {
        int b = 0, s = 0, skind =0 ;
        
        int total = candies.length;
        int max = total/2;
        
        Map<Integer, Integer> candyCount = new HashMap<>();
        
        for (int candy : candies) {
            candyCount.put(candy, candyCount.getOrDefault(candy, 0) + 1);
        }

        
        for (int kind : candyCount.keySet()) {
            
            int count = candyCount.get(kind);
  
            if (s < max) {
                s++;
                skind++;
                count --;
            }

            while(b < max && count != 0) {
                b += 1;
                count --;
            }  
            
            if (count != 0) {
                s += count;
            }
            
        }
        
        return skind;
    }        
    
    // overkill
    public int distributeCandies(int[] candies) {
        int b = 0, s = 0, skind =0 ;
        
        int total = candies.length;
        int max = total/2;
        
        Map<Integer, Integer> candyCount = new HashMap<>();
        
        for (int candy : candies) {
            candyCount.put(candy, candyCount.getOrDefault(candy, 0) + 1);
        }
        
        PriorityQueue<CandyCount> queue = new PriorityQueue<>();
        
        for (int kind : candyCount.keySet()) {
            queue.add(new CandyCount(kind, candyCount.get(kind)));
        }
        
        while (!queue.isEmpty()) {
            CandyCount obj = queue.poll();
            int count = obj.count;
  
            if (s < max) {
                s++;
                skind++;
                count --;
            }

            while(b < max && count != 0) {
                b += 1;
                count --;
            }  
            
            if (count != 0) {
                s += count;
            }
            
        }
        
        return skind;
        
    }
    
    //669. Trim a Binary Search Tree
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null)
            return null;
        
        if (root.val > R) {
            return trimBST(root.left, L, R);
        }
        
        if (root.val < L) {
            return trimBST(root.right, L, R);
        }
        
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        return root;
    }
    
    
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (openCloseMap.containsKey(c)) {
                stack.add(c);
            } else {
                
                if (stack.isEmpty()) 
                    return false;
                
                char openTop = stack.pop();
                if (openCloseMap.get(openTop) != c)
                    return false;
            }
        }
        
        return stack.isEmpty();
    }
    
    
    public int calPoints(String[] ops) {
       Stack<Integer> stack = new Stack<>();
       int score = 0;
       for (String op : ops) {
    	   switch (op) {
    	   	case "+" :
    	   		int lastPoint = stack.isEmpty() ? 0 : stack.pop();
    	   		int secondLastPoint = stack.isEmpty() ? 0 : stack.peek();
    	   		score += lastPoint + secondLastPoint;
    	   		if (lastPoint != 0) {
    	   			stack.push(lastPoint);
    	   			stack.push(lastPoint + secondLastPoint);  	   			
    	   		}
    	   		break;
    	   	case "C" :
    	   		int invalid = stack.isEmpty() ? 0: stack.pop();
    	   		score -= invalid;
    	   		break;
    	   	case "D" :
    	   		int last = stack.isEmpty() ? 0: stack.peek();
    	   		score += (last * 2);
    	   		stack.push(last * 2);
    	   		break;
    	   	default : 
    	   		int points = Integer.parseInt(op);
    	   		score += points;
    	   		stack.push(points);
    	   }
       }
       
       return score;
    }
    
    
    public int calPointsNotGreatCode(String[] ops) {
        LinkedList<Integer> list = new LinkedList<>();
        int score = 0;
        for (int i=0; i<ops.length; i++) {
            try {
                int points = Integer.parseInt(ops[i]);
                score += points;
                list.add(points);
            } catch(NumberFormatException e) {
                switch (ops[i]) {
                    case "C" : 
                        int invalidPoints = list.size() > 0 ? list.removeLast() : 0;
                        score -= invalidPoints;
                        break;
                    
                    case "D" : 
                        int lastPoints = list.size() > 0 ? list.getLast() : 0;
                        score += (lastPoints*2);
                        list.add(lastPoints*2);
                        break;
                        
                    case "+" :
                        int last = list.size() > 0 ? list.getLast() : 0;
                        int beforeLast = list.size() > 1 ? list.get(list.size()-2) : 0;
                        score += (last + beforeLast);
                        list.add(last + beforeLast);
                        break;
                        
                    default :
                        return -1;
                }
            }
        }
        return score;
    }
    
    public String[] findWords(String[] words) {
        List<String> result = new ArrayList<>();
        
        for (String w : words) {
            if (isSameRow(w)) {
                result.add(w);
            }
        }
        
        String[] res = new String[result.size()];
        for (int i=0; i<result.size(); i++) {
            res[i] = result.get(i);
        }
        
        return res;
    }
    
    private boolean isSameRow(String w) {
        if (w == null || w.isEmpty())
            return false;
        
        int row = charRow.getOrDefault(Character.toLowerCase(w.charAt(0)), -1);
        if (row == -1)
            return false;
        
        for (int i=1; i<w.length(); i++) {
            if (charRow.getOrDefault(Character.toLowerCase(w.charAt(i)), -1) != row)
                return false;
        }
        
        return true;
    }
    
    public String reverseVowels(String s) {
        if (s == null || s.isEmpty())
            return s;
        
        int i=0, j=s.length()-1;
        char arr[] = new char[s.length()];
        while (i <= j) {
            char ci = s.charAt(i);
            char cj = s.charAt(j);
            
            if (vowels.contains(ci) && vowels.contains(cj)) {
                arr[i] = cj;
                arr[j] = ci;
                i++;
                j--;
            } else if (vowels.contains(ci)) {
                arr[j] = cj;
                j--;
            } else if (vowels.contains(cj)) {
                arr[i] = ci;
                i++;
            } else {
                arr[i] = ci;
                arr[j] = cj;
                i++;
                j--;
            }
        }
        
        return new String(arr);
    }
	
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        String[] words = s.split(" ");
        for (int i=0; i<words.length; i++) {
            sb.append(reverse(words[i]));
            if (i != words.length-1)
                sb.append(" ");
        }
        return sb.toString();
    }
    
    private String reverse(String s) {
    	int len = s.length();
        char c[] = new char[len];
        for (int i=0; i<=len/2; i++) {
            c[i] = s.charAt(len-1-i);
            c[s.length()-1-i] = s.charAt(i);
        }
        
        return new String(c);
    }
	
	//476. Number Complement
	public int findComplement(int num) {
        int bits = 1;
        int n = num;
        while (n > 1) {
            n = n >>> 1;
            bits++;
        }
        int total = 0;
        while (bits > 0) {
            total |= (1 << (bits-1));
            bits--;
        }
        
        return total - num;
    }
	
	//561. Array Partition I
	//Given an array of 2n integers, your task is to group these integers into n pairs of integer, say (a1, b1), (a2, b2), ..., (an, bn) 
	//which makes sum of min(ai, bi) for all i from 1 to n as large as possible.
	 public int arrayPairSum(int[] nums) {
	        Arrays.sort(nums);
	        int sum = 0;
	        for (int i=0; i+1<nums.length; i += 2) {
	            sum += nums[i];
	        }
	        return sum;
	    }
	
	// 728. Self Dividing Numbers
	public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> list = new ArrayList<>();
        for (int i=left; i<= right; i++) {
            int n = i;
            boolean selfDividing = true;
            while (n != 0) {
                int digit = n % 10;
                if (digit == 0 || i % digit != 0) {
                    selfDividing = false;
                    break;
                }
                n = n/10;
            }
            if (selfDividing) {
                list.add(i);
            }
        }
        return list;
    }
	
	 public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
	        if (t1 == null && t2 == null)
	            return null;
	        
	        TreeNode root = null;
	        
	        if (t1 == null)
	            root = new TreeNode(t2.val);
	        else if (t2 == null)
	            root = new TreeNode(t1.val);
	        else 
	            root = new TreeNode(t1.val + t2.val);
	        
	        root.left = mergeTrees(t1 == null ? null : t1.left, t2 == null ? null : t2.left);
	        root.right = mergeTrees(t1 == null ? null : t1.right, t2 == null ? null : t2.right);
	        return root;
	    }
	
	// 461 The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
	 public int hammingDistance(int x, int y) {
        int count =0;
        while (x != 0 || y != 0) {
            count += (x & 1) ^ (y & 1);
            x = x >>> 1;
            y = y >>> 1;
        }
        return count;
	}
	
	
	//209. Minimum Size Subarray Sum
	public int minSubArrayLen(int s, int[] nums) {
        int from = 0, to = Integer.MAX_VALUE;
		int currSum = 0;
		int currFrom = -1, currTo = -1;
		
		for (int i=0; i<nums.length; i++) {
			
			currSum += nums[i];
			
			if (currFrom == -1) {
				currFrom = i;
			}
			
			currTo = i;

            while (currSum >= s) {
                if (currTo-currFrom < to-from) {
                    from = currFrom;
                    to = currTo;
                }
                currSum -= nums[currFrom];
                currFrom++;
            }
			
		}
        
        return to == Integer.MAX_VALUE ? 0 : to - from + 1;
		
    }
	
	//674. Longest Continuous Increasing Subsequence
	public int findLengthOfLCIS(int[] arr) {
	        
        if (arr.length == 0)
            return 0;
        
        int currFrom = -1, currTo = -1;
		int from = 0, to = 0;
		
		for (int i=1; i<arr.length; i++) {
			if (arr[i] > arr[i-1]) {
				if (currFrom == -1) {
					currFrom = i-1;
				}
				currTo = i;
			} else {
				currFrom = -1;
				currTo = -1;
			}
			
			if (currTo - currFrom > to - from) {
				from = currFrom;
				to = currTo;
			}
		}
		
        return to - from + 1;
	  }

}
