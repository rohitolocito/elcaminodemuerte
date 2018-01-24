package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LeetcodeMedium {

	public static void main(String[] args) {
		
		LeetcodeMedium run = new LeetcodeMedium();
		
		int[][] mat = { {1, 0, 0, 1}, {0, 1, 1, 0}, {0, 1, 1, 1}, {1, 0, 1, 1}};
		System.out.println(run.findCircleNum(mat));
		
		
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.right.right = new TreeNode(5);
		
		System.out.println(run.zigzagLevelOrder(root));
		System.out.println(run.zigzagLevelOrderIterative(root));

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
