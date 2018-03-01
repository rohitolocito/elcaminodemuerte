package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    
    private int segment[] = {};
    
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        this.segment = new int[size(nums)];
        constructSegmentTree(nums, 0, 0, nums.length-1);
        return constructMaximumBinaryTree(nums, 0, nums.length-1);
    }
    
    private TreeNode constructMaximumBinaryTree(int[] nums, int low, int high) {
        if (low > high)
            return null;
        
        int maxIndex = getMaxIndex(nums, low, high);
        TreeNode node = new TreeNode(nums[maxIndex]);
        node.left = constructMaximumBinaryTree(nums, low, maxIndex-1);
        node.right = constructMaximumBinaryTree(nums, maxIndex+1, high);
        return node;
        
    }
    
    private int constructSegmentTree(int[] nums, int index, int low, int high) {
        if (low > high)
            return -1;
        
        if (low == high) {
            segment[index] = low;
            return low;
        } else {
            int mid = (low + high) >>> 1;
            int left = constructSegmentTree(nums, 2*index+1, low, mid);
            int right = constructSegmentTree(nums, 2*index+2, mid+1, high);
            int maxIndex = -1;
            
            if (left != -1 && right != -1) {
                maxIndex = nums[left] < nums[right] ? right : left;
            } else if (left != -1) {
                maxIndex = left;
            } else if (right != -1) {
                maxIndex = right;
            }
            
            segment[index] = maxIndex;
            return maxIndex;
        }  
    }
    
    private int getMaxIndex(int nums[], int a, int b) {
        return getMaxIndexUsingSegmentTree(nums, 0, 0, nums.length-1, a, b);
    }
    
    private int getMaxIndexUsingSegmentTree(int[] nums, int index, int low, int high, int a, int b) {
        if (low > high)
            return -1;
        
        if (high < a || low > b)//high < a || low > b
            return -1;
        
        if (low >= a && high <= b)
            return segment[index];
        
        int mid = (low + high) >>> 1;
        
        int left = getMaxIndexUsingSegmentTree(nums, 2*index+1, low, mid, a, b);
        int right = getMaxIndexUsingSegmentTree(nums, 2*index+2, mid+1, high, a, b);
        
        int maxIndex = -1;
            
        if (left != -1 && right != -1) {
            maxIndex = nums[left] < nums[right] ? right : left;
        } else if (left != -1) {
            maxIndex = left;
        } else if (right != -1) {
            maxIndex = right;
        }
        return maxIndex;
    }
    
    private int size(int nums[]) {
        int height = (int) Math.ceil(Math.log(nums.length)/Math.log(2));
        return (int) (2*Math.pow(2, height) - 1);
    }
    
    
}


public class LeetcodeMedium {

	public static void main(String[] args) {
		
		LeetcodeMedium run = new LeetcodeMedium();
		
		int[][] mat = { {1, 0, 0, 1}, {0, 1, 1, 0}, {0, 1, 1, 1}, {1, 0, 1, 1}};
		System.out.println(run.findCircleNum(mat));
		
		List<Character> bucket[] = new List[1];
		
		
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.right.right = new TreeNode(5);
		
		System.out.println(run.zigzagLevelOrder(root));
		System.out.println(run.zigzagLevelOrderIterative(root));
		
		int nums[] = {1,-1,-1,1};
		int nums1[] = {0,0,0,0,0,0,0,0,0,0};
		System.out.println(run.subarraySum(nums1, 0));
		System.out.println(run.subarraySum1(nums1, 0));
		
		int elements[] = {0,0,0,1,3,5,6,7,8,8};
		System.out.println(run.findClosestElements(elements, 2, 2));
		
//		[2,5]
//				[[3,0,5],[1,2,10]]
//				[3,2]
		
		List<Integer> price = new ArrayList<>();
		List<Integer> offer1 = new ArrayList<>();
		List<Integer> offer2 = new ArrayList<>();
		List<Integer> needs = new ArrayList<>();
		
		price.add(2);
		price.add(5);
		
		offer1.add(3);
		offer1.add(0);
		offer1.add(5);
		
		offer2.add(1);
		offer2.add(2);
		offer2.add(10);
		
		List<List<Integer>> special = new ArrayList<>();
		special.add(offer1);
		special.add(offer2);
		
		needs.add(3);
		needs.add(2);
		
		System.out.println(run.shoppingOffers(price, special, needs));
		
		int[] dups = {1,3,4,2,1};
		System.out.println(run.findDuplicate(dups));
		System.out.println(run.checkValidString( "(*))"));
		
		ListNode node = new ListNode(1);
		System.out.println(98 % 1);
		System.out.println(run.rotateRight(node, 99));
		System.out.println(run.uniquePaths(23, 12)); //193536720
		System.out.println(Math.ceil(Math.log(6)/Math.log(2)));
		
		Solution solution = new Solution();
		int nums2[] = {3,2,1,6,0,5};
		solution.constructMaximumBinaryTree(nums2);
//		System.out.println(run.canMeasureWater(4, 6, 8));
	}
	
    private class Range {
        int a;
        int b;
        
        public Range(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
    
    public List<Integer> partitionLabels(String S) {
        
        List<Integer> list = new ArrayList<>();
        
        if (S == null || S.isEmpty())
            return list;
        
        Map<Character, Range> map = new HashMap<>(); 
        
        for (int i=0; i<S.length(); i++) {
            char c = S.charAt(i);
            
            if (map.containsKey(c)) {
                Range r = map.get(c);
                r.b = i;
            } else {
                map.put(c, new Range(i,i));
            }
        }
        
        Range range = null;
        
        for (int i=0; i<S.length(); i++) {
            
            char c = S.charAt(i);
            if (range == null) {
                range = map.get(c);
            } else {
                Range r = map.get(c);
                if (r.a >= range.a && r.b <= range.b) {
                    continue;
                } else if (r.a > range.b) {
                    list.add(range.b - range.a + 1);
                    range = r;
                } else if (r.b > range.b) {
                    range.b = r.b;
                }
            }
            
            
        }
        
        list.add(range.b - range.a + 1);
        return list;
        
    }
	
    // BUGGY & INCORRECT
//    public boolean canMeasureWater(int x, int y, int z) {
//        
//        if (x == 0)
//            return y == z;
//        if (y == 0)
//            return x == z;
//        
//        int min = Math.min(x,y);
//        int max = Math.max(x,y);
//        
//        if (min + max == z)
//            return true;
//        
//        int a =min , b= 0;
//        
//        while (a + b < z) {
//            b = b + a;
//            if (b > max) {
//                a = b - max;   
//                b = 0;
//            }
//            if (b == z)
//                return true;
//        }
//        
//        return false;
//    }
	
	public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        Integer min[][] = new Integer[m][n];
        min[m-1][n-1] = grid[m-1][n-1];
        
        return minPathSum(grid, 0, 0, min);
    }
    
    private int minPathSum(int[][] grid, int r, int c, Integer[][] min) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length)
            return Integer.MAX_VALUE;
        
        if (min[r][c] != null)
            return min[r][c];
        
        int down = minPathSum(grid, r+1, c, min);
        int right = minPathSum(grid, r, c+1, min);
        min[r][c] = grid[r][c] + Math.min(down, right);
        return min[r][c];
    }
	
	//62. Unique Paths
	 public int uniquePaths(int m, int n) {
		 	int[][] paths = new int[m][n];
	        return uniquePaths(0, 0, m, n, paths);
	    }
	    
	    private int uniquePaths(int r, int c, int m, int n, int[][] paths) {
	        
	        if (r < 0 || r >= m || c < 0 || c >= n)
	            return 0;
	        
	        if (paths[r][c] != 0)
	        	return paths[r][c];
	        
	        if (r == m-1 && c == n-1) {
	            return 1;
	        }
	        
	        int down = uniquePaths(r+1, c, m, n, paths);
	        int right = uniquePaths(r, c+1, m, n, paths);
	        paths[r][c] = down + right;
	        return paths[r][c];
	        
	    }
	
	//61. Rotate List
	public ListNode rotateRight(ListNode head, int k) {
        if (k <= 0 || head == null)
            return head;
        
        ListNode fast = head;
        int len = 1;
        
        while (k-- != 0) {
            if (fast.next != null) {
                fast = fast.next;
                len ++;
            } else {
                k = k%len;
                fast = head;
            }
        }
        
        if (fast == head)
            return head;
        
        ListNode slow = head;
        
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        
        ListNode newHead = slow.next;
        slow.next = null;
        fast.next= head;
        return newHead;
        
    }
	
	//678. Valid Parenthesis String
	  public boolean checkValidString(String s) {
	        Stack<Character> stack = new Stack<>();
	        int star = 0;
	        
	        for (int i=0; i<s.length(); i++) {
	            char c = s.charAt(i);
	            if (c == '(') {
	                stack.push('(');
	            } else if (c == ')') {
	                if (!stack.isEmpty()) {
	                    stack.pop();
	                } else if (star > 0) {
	                    star --;
	                } else {
	                    return false;
	                }
	            } else {
	                star ++;
	            }
	        }
	        
	        return stack.isEmpty();
	    }
	
	//80. Remove Duplicates from Sorted Array II
	 public int removeDuplicates(int[] nums) {
	        Map<Integer, Integer> count = new HashMap<>();
	        
	        for (int n : nums) {
	            count.put(n , count.getOrDefault(n, 0) + 1);
	        }
	        
	        int index = 0;
	        for (int i=0; i<nums.length; ) {
	            int cnt = count.get(nums[i]);
	            if (cnt >= 2) {
	                nums[index++] = nums[i];
	                nums[index++] = nums[i];
	            } else {
	                nums[index++] = nums[i];
	            }
	            i += cnt;
	        }
	        
	        return index;
	    }
	
    private Map<Integer, Integer> mapInorder = new HashMap<>();
    private int pre = 0;
    
    // 105. Construct Binary Tree from Preorder and Inorder Traversal
    public TreeNode buildTree(int[] preorder, int[] inorder) {
    	mapInorder = new HashMap<>();
        pre = 0;
        
        for (int i=0; i<inorder.length; i++) 
        	mapInorder.put(inorder[i], i);
        
        return buildTree(preorder, inorder, 0, inorder.length-1);
    }
    
    //preorder = [3,9,20,15,7]
    //inorder = [9,3,15,20,7]
    
    private TreeNode buildTree(int[] preorder, int[] inorder, int low, int high) {
        if (low > high)
            return null;
        
        int index = mapInorder.get(preorder[pre++]);
        
        TreeNode root = new TreeNode(inorder[index]);
        root.left = buildTree(preorder, inorder, low, index-1);
        root.right = buildTree(preorder, inorder, index+1, high);
        return root;
    }
	
	//287. Find the Duplicate Number
	 public int findDuplicate(int[] nums) {
	        int slow = nums[0];
	        int fast = nums[0];
	        
	        do  {
	            slow = nums[slow];
	            fast = nums[nums[fast]];
	        } while (slow != fast);
	        
	        
	        int p1 = nums[0];
	        int p2 = slow;
	        
	        while (p1 != p2) {
	            p1 = nums[p1];
	            p2 = nums[p2];
	        }
	             
	        return p1;
	    }
	
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        
        int minCost = 0;
        
        for (int i=0; i<needs.size(); i++) 
                minCost += price.get(i) * needs.get(i);
        
        for (int i=0; i<special.size(); i++) {
            List<Integer> offer = special.get(i);
            boolean matches = true;
            
            List<Integer> needsClone = new ArrayList<>(needs);
            
            for (int j=0; j<needsClone.size(); j++) {
                if (offer.get(j) > needsClone.get(j)) {
                    matches = false;
                    break;
                } else {
                    needsClone.set(j, needsClone.get(j) - offer.get(j));
                }
            }
            
            if (matches) {
                int cost = offer.get(offer.size()-1);
                minCost = Math.min(minCost, cost + shoppingOffers(price, special, needsClone));
            }
        }
        
        return minCost;
    }
	
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> list = new ArrayList<>();
        
        int closestIndex[] = {-1};
        findClosestIndex(arr, 0, arr.length-1, x, closestIndex);
        
        if (closestIndex[0] == -1)
            return list;
        
        int i = closestIndex[0];
        int j = i-k+1;
        
        while (j <= i) {
            if (j >= 0) {
                list.add(arr[j]);
            }
            j++;
        }
        
        if (list.size() < k) {
            j= i+1;
            while (j < arr.length) {
                if (list.size() == k)
                    break;
                list.add(arr[j]);
                j++;
            }
        }
        
        
        return list;
        
    }
    
    private void findClosestIndex(int[] arr, int low, int high, int x, int closestIndex[]) {
        if (low > high)
            return;
        
        int mid = (low + high) >>> 1;
        
        if (arr[mid] == x) {
            closestIndex[0] = mid;
            return;
        }
        
        if (closestIndex[0] == -1 || Math.abs(arr[mid] - x) < Math.abs(x-arr[closestIndex[0]])) {
            closestIndex[0] = mid;
        }
        
        if (x > arr[mid]) {
            findClosestIndex(arr, mid+1, high, x, closestIndex);
        } else {
            findClosestIndex(arr, low, mid-1, x, closestIndex);
        }
        
    }
	
	  private static final Map<Character, Character[]> map = new HashMap<>();
	    
	    static {
	        map.put('2', new Character[] {'a', 'b', 'c'});
	        map.put('3', new Character[] {'d', 'e', 'f'});
	        map.put('4', new Character[] {'g', 'h', 'i'});
	        map.put('5', new Character[] {'j', 'k', 'l'});
	        map.put('6', new Character[] {'m', 'n', 'o'});
	        map.put('7', new Character[] {'p', 'q', 'r', 's'});
	        map.put('8', new Character[] {'t', 'u', 'v'});
	        map.put('9', new Character[] {'w', 'x', 'y', 'z'});
	        
	    }
	    
	    public List<String> letterCombinations(String digits) {
	        List<String> result = new ArrayList<>();
	        
	        if (digits.isEmpty())
	            return result;
	        
	        StringBuilder sb = new StringBuilder();
	        letterCombinations(digits, 0, sb, result);
	        return result;
	    }
	    
	    private void letterCombinations(String digits, int index, StringBuilder sb, List<String> result) {
	        if (index >= digits.length()) {
	            result.add(sb.toString());
	            return;
	        }
	        
	        Character[] arr = map.getOrDefault(digits.charAt(index), new Character[]{});
	        int len = sb.length();
	        
	        for (int i=0; i<arr.length; i++) {
	            sb.append(arr[i]);
	            letterCombinations(digits, index+1, sb, result);
	            sb.setLength(len);
	        }
	    }
	
    public int subarraySum1(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap < Integer, Integer > map = new HashMap < > ();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
	
	public int subarraySum(int[] nums, int k) {
        int count = 0;
        int sum = 0;
        
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i=0; i<nums.length; i++) {
            sum += nums[i];
            if (sum == k)
                count++;
            
            if (map.containsKey(sum-k)) {
                count++;
            }
            map.put(sum, i);
        }
        
        return count;
    }
	
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
                
        while (root != null || !stack.isEmpty()) {
            
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        
            TreeNode node = stack.pop();
            list.add(node.val);
            root = node.right;
        }
        
        return list;
    }
	
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                list.add(root.val);
                if (root.right != null)
                    stack.push(root.right);
                root = root.left;
            } else {
                root = stack.pop();
            }
        }
        return list;
    }
	
	public List<List<Integer>> zigzagLevelOrderIterative(TreeNode root) {
		Stack<TreeNode> ltr = new Stack<>();
		Stack<TreeNode> rtl = new Stack<>();
		List<List<Integer>> list = new ArrayList<>();
		
		ltr.add(root);
		
		while (!ltr.isEmpty() || !rtl.isEmpty()) {
			if (!ltr.isEmpty()) {
				list.add(new ArrayList<>());
				while (!ltr.isEmpty()) {
					
					TreeNode node = ltr.pop();
					list.get(list.size()-1).add(node.val);
					
					if (node.left != null)
						rtl.push(node.left);
					
					if (node.right != null)
						rtl.push(node.right);
				}		
			}
	
			if (!rtl.isEmpty()) {
				list.add(new ArrayList<>());
				while (!rtl.isEmpty()) {
					TreeNode node = rtl.pop();
					list.get(list.size()-1).add(node.val);
					
					if (node.right != null)
						ltr.push(node.right);
					
					if (node.left != null)
						ltr.push(node.left);
				}			
			}
		}
		
		return list;

	}
	
    //103. Binary Tree Zigzag Level Order Traversal
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        int h = height(root);
        boolean lr = true;
        for (int level = 0; level < h; level++) {
           result.add(new ArrayList<>());
           zigzagLevelOrder(root, level, lr, result);    
           lr = !lr;
        }
        return result;
    }
    
    private int height(TreeNode root) {
        if (root == null)
            return 0;
        
        return 1 + Math.max(height(root.left), height(root.right));
    }
    
    private void zigzagLevelOrder(TreeNode root, int level, boolean lr, List<List<Integer>> result) {
        if (root == null || level < 0)
            return;

        if (level == 0) {
            result.get(result.size()-1).add(root.val);  
            return;
        }
 
        if (lr) {
            zigzagLevelOrder(root.left, level - 1, lr, result);
            zigzagLevelOrder(root.right, level - 1, lr, result);
        } else {
            zigzagLevelOrder(root.right, level - 1, lr, result);
            zigzagLevelOrder(root.left, level - 1, lr, result);
        }
    }
	
	public int findCircleNum(int[][] M) {
        int[][] groups = new int[M.length][M.length];
        int g = 0;
        for (int i=0; i<M.length; i++) {
            if (groups[i][i] == 0) {
                g++;
                groups[i][i] = g;
            }
            for (int j=i+1; j<M.length; j++) {
                if (M[i][j] == 1 && groups[i][j] == 0) {
                   visitFriends(M, groups, j, g);
                }
            }
        }
        return g;
    }
    
    private void visitFriends(int[][] M, int[][] groups, int friend, int g) {
        if (friend >= M.length || groups[friend][friend] != 0)
            return;
        
        groups[friend][friend] = g;
        
        for (int col=0; col < M.length; col++) {
            if (M[friend][col] == 1) {
                visitFriends(M, groups, col, g);
            }
        }
    }

}
