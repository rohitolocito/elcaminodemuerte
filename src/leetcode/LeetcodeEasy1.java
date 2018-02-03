package leetcode;

import java.util.Stack;

public class LeetcodeEasy1 {

	public static void main(String[] args) {
		
		LeetcodeEasy1 run = new LeetcodeEasy1();

	}
	
	public void flatten(TreeNode root) {
        
        Stack<TreeNode> stack = new Stack<>();
        
        TreeNode head = null, headCurr = null;
        
        if (root != null)
            stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            
            if (node.right != null)
                stack.push(node.right);
            
            if (node.left != null) 
                stack.push(node.left);
            
            node.left = null;
            node.right = null;
            
            if (head == null) {
                head = node;
                headCurr = node;
            } else {
                headCurr.right = node;
                headCurr = node;
            }
        }
    }

}
