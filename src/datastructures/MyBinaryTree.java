package datastructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyBinaryTree<E> {
	
	private class Result {
		MyBinaryNode<E> node;
		boolean isLca;
		
		public Result(MyBinaryNode<E> node, boolean isLca) {
			this.node = node;
			this.isLca = isLca;
		}
	}
	
	private MyBinaryNode<E> root;
	
	public MyBinaryTree(MyBinaryNode<E> tree) {
		this.root = tree;
	}
	
	public MyBinaryTree(E arr[]) {
		this.root = createBinaryTree(arr, 0, arr.length-1);
	}
	
	// in real-world, this should not be exposed as it would expose the complex DS underneath this simple class
	public MyBinaryNode<E> root() {
		return this.root;
	}
	
	private MyBinaryNode<E> createBinaryTree(E arr[], int low, int high) {
		if (low > high)
			return null;
		
		int mid = (low + high) >>> 1;
		
		MyBinaryNode<E> root = new MyBinaryNode<>(arr[mid]);
		
		MyBinaryNode<E> left = createBinaryTree(arr, low, mid-1);
		MyBinaryNode<E> right = createBinaryTree(arr, mid+1, high);
		
		// setting parent nodes
		if (left != null) {
			root.setLeft(left);
			root.left.parent = root;
		}
		
		if (right != null) {
			root.setRight(right);
			root.right.parent = root;
		}
		
		return root;
	}
	
	public MyBinaryNode<E> inOrderSuccessor(MyBinaryNode<E> node) {
		if (node == null)
			return null;
		
		if (node.right != null) {
			node = node.right;
			while (node.left != null)
				node = node.left;
			return node;
		}
		
		if (node.parent == null)
			return null;
		
		if (node.parent.left == node) 
			return node.parent;
		
		while(node.parent != null && node.parent.left != node)
			node = node.parent;
		
		return node.parent;
	}
	
	public boolean isSubTreeBetterWorseCase(MyBinaryNode<E> t1, MyBinaryNode<E> t2) {
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		
		preOrderString(t1, sb1, true);
		preOrderString(t2, sb2, true);
		return sb1.toString().indexOf(sb2.toString()) >= 0;
	}
	
	private void preOrderString(MyBinaryNode<E> root, StringBuilder sb, boolean fillNull) {
		if (root == null) {
			if(fillNull)
				sb.append('X');
			return;
		}
		
		sb.append(root.item);
		preOrderString(root.left, sb, fillNull);
		preOrderString(root.right, sb, fillNull);
	}
	
	public boolean isSubTreeBetterAverageCase(MyBinaryNode<E> t1, MyBinaryNode<E> t2) {
		if (t1 == null || t2 == null)
			return false;
		
		if (t1.item.equals(t2.item)) {
			boolean isMatch = exactMatch(t1, t2);
			if(isMatch) return true;
		}
		
		return isSubTreeBetterAverageCase(t1.left, t2) || isSubTreeBetterAverageCase(t1.right, t2);
	}
	
	private boolean exactMatch(MyBinaryNode<E> t1, MyBinaryNode<E> t2) {
		if (t1 == null && t2 == null)
			return true;
		
		if (t1 == null || t2 == null)
			return false;
		
		return t1.item.equals(t2.item) && exactMatch(t1.left, t2.left) && exactMatch(t1.right, t2.right);
	}
	
	
	//			3
	//		1		4
	//	  0   2
	
	// {3,1,4,0,2} {3,1,4,2,0} {3,4,1,0,2} {3,4,1,2,0}
	
	// time complexity
	// https://stackoverflow.com/questions/17119116/how-many-ways-can-you-insert-a-series-of-values-into-a-bst-to-form-a-specific-tr
	
	public List<LinkedList<E>> allPossibleArrays() {
		return allPossibleArrays(this.root);
	}
	
	private List<LinkedList<E>> allPossibleArrays(MyBinaryNode<E> root) {
		
		List<LinkedList<E>> result = new ArrayList<>();
		
		if (root == null) {
			result.add(new LinkedList<>());
			return result;
		}
		
		LinkedList<E> list = new LinkedList<>();
		list.add(root.item);
		
		List<LinkedList<E>> left = allPossibleArrays(root.left);
		List<LinkedList<E>> right = allPossibleArrays(root.right);
		
		for(LinkedList l : left) {
			for(LinkedList r : right) {
				weave(l, r, list, result);
			}
		}
		
		return result;
		
	}

	private void weave(LinkedList<E> first, LinkedList<E> second, LinkedList<E> prefix, List<LinkedList<E>> result) {
		
		if(first.size() == 0 || second.size() == 0) {
			LinkedList<E> list = (LinkedList<E>)prefix.clone();
			list.addAll(first);
			list.addAll(second);
			result.add(list);
			return;
		}

		
		E item = first.removeFirst();
		prefix.add(item);
		weave(first, second, prefix, result);
		prefix.removeLast();
		first.addFirst(item);
		
		item = second.removeFirst();
		prefix.add(item);
		weave(first, second, prefix, result);
		prefix.removeLast();
		second.addFirst(item);
	}
	
	
	//			20
	//		10		30
	//	 5		15
	//3		7		17
	
	// 5 and 30
	public MyBinaryNode<E> firstCommonAncestorUsingParent(MyBinaryNode<E> node1, MyBinaryNode<E> node2) {
		
		if (node1 == null || node2 == null)
			return null;
		
		if (isInSubtree(node1, node2))
			return node1;
		
		if (isInSubtree(node2, node1))
			return node2;
		
		
		MyBinaryNode<E> parent = node1.parent;
		MyBinaryNode<E> brother = getSibling(node1);
		
		while(brother != null) {
			if (isInSubtree(brother, node2))
				return parent;
			brother = getSibling(parent);
			parent = parent.parent;
		}
		
		return null;
		
		
	}
	
	public MyBinaryNode<E> getSibling(MyBinaryNode<E> node) {
		if (node == null || node.parent == null)
			return null;
		
		MyBinaryNode<E> parent = node.parent;
		return parent.left == node ? parent.right : parent.left;
	}
	
	
	
	public boolean isInSubtree(MyBinaryNode<E> root, MyBinaryNode<E> node) {
		if(root == null)
			return false;
		
		if (root == node)
			return true;
		
		return isInSubtree(root.left, node) || isInSubtree(root.right, node);
		
	}
	
	public MyBinaryNode<E> firstCommonAncestor(MyBinaryNode<E> node1, MyBinaryNode<E> node2) {
		Result r= firstCommonAncestor(this.root, node1, node2);
		return r.isLca ? r.node : null;
	}
	
	private Result firstCommonAncestor(MyBinaryNode<E> root, MyBinaryNode<E> node1, MyBinaryNode<E> node2) { 
		if (root == null)
			return new Result(null, false);
		
		Result left = firstCommonAncestor(root.left, node1, node2);
		if (left.isLca)
			return left;
		
		Result right = firstCommonAncestor(root.right, node1, node2);
		if (right.isLca)
			return right;
		
		if (root == node1 || root == node2) {
			Result curr = new Result(root, false);
			if (left.node != null || right.node != null)
				curr.isLca = true;
			return curr;
		}
		
		if (left.node != null && right.node!= null) {
			return new Result(root,true);
		}
		
		return left.node != null ? left : right;
	}
	
	public boolean isBSTEfficient() {
		Object prev[] = new Object[1];
		return isBSTEfficient(this.root, prev);
	}
	
	private boolean isBSTEfficient(MyBinaryNode<E> root, Object prev[]) {
		if (root == null)
			return true;
		
		if (!isBSTEfficient(root.left, prev))
			return false;
		
		if (prev[0] != null) {
			E item = ((MyBinaryNode<E>)prev[0]).item;
			if ( ((Comparable<E>) item).compareTo(root.item) > 0 )
				return false;
		}
		
		prev[0] = root;
		return isBSTEfficient(root.right, prev);
	}
	
	// this is inefficient.. we should't have to iterate through the array again
	// in fact we shouldn't even use an array
	// just keep track of the last element
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
	
	public List<LinkedList<Integer>> pathsWithSum(int sum) {
		List<LinkedList<Integer>> list = new ArrayList<>();
		pathsWithSum((MyBinaryNode<Integer>)this.root, sum, list);
		return list;
	}
	
	private void pathsWithSum(MyBinaryNode<Integer> root, int sum, List<LinkedList<Integer>> list) {
		if (root == null)
			return ;
		
		findPathsWithSumFrom(root, sum, 0, new LinkedList<Integer>(), list);
		
		pathsWithSum(root.left, sum, list);
		pathsWithSum(root.right, sum, list);
	}
	
	private void findPathsWithSumFrom(MyBinaryNode<Integer> root, int sum, int sumSoFar, LinkedList<Integer> curr, List<LinkedList<Integer>> list) {
		if (root == null)
			return ;
		
		curr.add(root.item);
		
		if (sumSoFar + root.item == sum) {
			list.add((LinkedList<Integer>)curr.clone());
		}
		
		findPathsWithSumFrom(root.left, sum, sumSoFar + root.item, curr, list);
		findPathsWithSumFrom(root.right, sum, sumSoFar + root.item, curr, list);
		
		curr.removeLast();
	}
	
	public int pathsWithSumCount(int sum) {
		Map<Integer,Integer> sumCount = new HashMap<>();
		return pathsWithSumCount((MyBinaryNode<Integer>)this.root, sum, 0, sumCount);
	}
	
	private int pathsWithSumCount(MyBinaryNode<Integer> root, int sum, int sumSoFar, Map<Integer, Integer> sumCount) {
		if (root == null)
			return 0;
		
		int paths = 0;

		if (sumSoFar + root.item == sum)
			paths++;
		
		int val = sumSoFar + root.item - sum;
		if (sumCount.containsKey(val)) {
			paths += sumCount.get(val);
		}
		
		sumCount.put(sumSoFar + root.item, sumCount.getOrDefault(sum + root.item, 0)+1);
		
		paths += pathsWithSumCount(root.left, sum, sumSoFar + root.item, sumCount);
		paths += pathsWithSumCount(root.right, sum, sumSoFar + root.item, sumCount);
		
		sumCount.put(sumSoFar + root.item, sumCount.get(sumSoFar + root.item)-1);
		
		return paths;
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
