package datastructures;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class MyBinaryTree<E> {
	
	private MyBinaryNode root;
	
	public MyBinaryTree(MyBinaryNode<E> tree) {
		this.root = tree;
	}
	
	public MyBinaryTree(E arr[]) {
		this.root = createBinaryTree(arr, 0, arr.length-1);
	}
	
	private MyBinaryNode<E> createBinaryTree(E arr[], int low, int high) {
		if (low > high)
			return null;
		
		int mid = (low + high) >>> 1;
		
		MyBinaryNode<E> root = new MyBinaryNode<>(arr[mid]);
		
		root.left = createBinaryTree(arr, low, mid-1);
		root.right = createBinaryTree(arr, mid+1, high);
		
		return root;
	}
	
	public boolean isBST() {
		List<E> list = inOrderList(this.root);
		return isBST(list);
	}
	
	private boolean isBST(List<E> list) {
		for(int i=1; i<list.size(); i++) {
			E prev = list.get(i-1);
			E curr = list.get(i);
			if ( ((Comparable<E>)prev).compareTo(curr) > 0) {
				return false;
			}
		}
		return true;
	}
	
	public List<E> inOrderList() {
		return inOrderList(root);
	}
	
	private List<E> inOrderList(MyBinaryNode<E> root) {
		List<E> list = new ArrayList<>();
		inOrderList(this.root, list);
		return list;
	}
	
	private void inOrderList(MyBinaryNode<E> root, List<E> list) {
		if (root == null)
			return;
		
		inOrderList(root.left, list);
		list.add(root.item);
		inOrderList(root.right, list);
	}
	
	public boolean isBalancedInefficient() {
		return isBalancedInefficient(this.root);
	}
	
	private boolean isBalancedInefficient(MyBinaryNode<E> node) {
		if (node == null)
			return true;
		
		int lh = height(node.left);
		int rh = height(node.right);
		
		return Math.abs(lh-rh) <= 1 && isBalancedInefficient(node.left)
				&& isBalancedInefficient(node.right);
	}
 	
	public boolean isBalanced() {
		boolean[] isBalanced = {true};
		isBalanced(this.root, isBalanced);
		return isBalanced[0];
	}
	
	private int isBalanced(MyBinaryNode<E> root, boolean[] isBalanced) {
		if(root == null)
			return 0;
		
		int lh = isBalanced(root.left, isBalanced);
		if(!isBalanced[0]) {
			return lh;
		}
		int rh = isBalanced(root.right, isBalanced);
		if (Math.abs(lh -rh) > 1)
			isBalanced[0] = false;
		return 1 + Math.max(lh, rh);
	}
	
	public int height() {
		return this.height(this.root);
	}
	
	private int height(MyBinaryNode<E> root) {
		if (root == null)
			return 0;
		
		return 1 + Math.max(height(root.left), height(root.right));
	}
	
	public List<LinkedList<MyBinaryNode<E>>> listOfDepths(boolean couldBeInsaneDepths) {
		List<LinkedList<MyBinaryNode<E>>> list = new ArrayList<>();
		
		if (couldBeInsaneDepths) {
			listOfInsaneDepths(this.root, list);
		} else {
			listOfDepths(this.root, 0, list);			
		}

		return list;
	}
	
	private void listOfInsaneDepths(MyBinaryNode<E> node, List<LinkedList<MyBinaryNode<E>>> list) {
		if (node == null)
			return;
		
		LinkedList<MyBinaryNode<E>> linkedList = new LinkedList<>();
		linkedList.add(node);
		
		while(!linkedList.isEmpty()) {
			list.add(linkedList);
			LinkedList<MyBinaryNode<E>> parents = linkedList;
			linkedList = new LinkedList<>();
			
			for(MyBinaryNode<E> parent : parents) {
				if (parent.left != null) {
					linkedList.add(parent.left);
				}
				
				if (parent.right != null) {
					linkedList.add(parent.right);
				}
			}
		}
		
	}
	
	private void listOfDepths(MyBinaryNode<E> node, int level, List<LinkedList<MyBinaryNode<E>>> list) {
		if (node == null)
			return;
		
		if (level == list.size()) {
			list.add(new LinkedList<>());
		}
		
		list.get(level).add(node);

		listOfDepths(node.left, level+1, list);
		listOfDepths(node.right, level+1, list);

	}
	
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		inOrderTraversal(this.root, sb);
		return sb.toString();
	}
	
	private void inOrderTraversal(MyBinaryNode<E> root, StringBuilder sb) {
		if(root == null)
			return;
		
		inOrderTraversal(root.left, sb);
		sb.append(root.item.toString()+", ");
		inOrderTraversal(root.right, sb);
	}
}
