package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

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
