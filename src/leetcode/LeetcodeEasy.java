package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
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
