package datastructures;

import java.util.Random;

public class MyBinaryNode<E> {

	
	// public for object creating and testing
	public E item;
	
	private int size = 1;
	
	private Random r = new Random();
	
	public MyBinaryNode(E item) {
		this.item = item;
	}
	
	// they are public so that i can easily construct a Binary tree for tests
	public MyBinaryNode<E> left; 
	public MyBinaryNode<E> right;
	public MyBinaryNode<E> parent;
	
	public int size() {
		return this.size;
	}
	
	public int leftSize() {
		if (this.left != null)
			return this.left.size();
		return 0;
	}
	
	public int rightSize() {
		if (this.right != null)
			return this.right.size();
		return 0;
	}
	
	public void setLeft(MyBinaryNode<E> left) {
		this.left = left;
		this.size += left.size;
		this.incrementParentSizeBy(left.size);
	}
	
	private void incrementParentSizeBy(int size) {
		if(this.parent != null) {
			this.parent.size += size;
			this.parent.incrementParentSizeBy(size);
		}
	}
	
	public void setRight(MyBinaryNode<E> right) {
		this.right = right;
		this.size += right.size;
		this.incrementParentSizeBy(right.size);
	}
	
	public synchronized MyBinaryNode<E> random() {

		int n = r.nextInt(this.size) + 1;
		return getNthNode(this, n);
	}
	
	public synchronized MyBinaryNode<E> delete(MyBinaryNode<E> nodeToBeDeleted) {
		if (nodeToBeDeleted == null)
			return null;
		
		if (nodeToBeDeleted.left == null && nodeToBeDeleted.right == null) {
			if (nodeToBeDeleted.parent != null && nodeToBeDeleted.parent.left == nodeToBeDeleted)
				nodeToBeDeleted.parent.left = null;
			else if (nodeToBeDeleted.parent != null && nodeToBeDeleted.parent.right == nodeToBeDeleted)
				nodeToBeDeleted.parent.right = null;
			nodeToBeDeleted.parent.incrementParentSizeBy(-1);
			return nodeToBeDeleted.parent;
		}
		
		else if (nodeToBeDeleted.left == null) {
			MyBinaryNode<E> min = minNode(nodeToBeDeleted.right);
			nodeToBeDeleted.item = min.item;
			return delete(min);
		}
		
		else if (nodeToBeDeleted.right == null) {
			MyBinaryNode<E> max = maxNode(nodeToBeDeleted.left);
			nodeToBeDeleted.item = max.item;
			return delete(max);
		}
		
		else {
			int leftSize = nodeToBeDeleted.leftSize();
			int rightSize = nodeToBeDeleted.rightSize();
			MyBinaryNode<E> nodeToBeSwapped = null;
			if (leftSize - rightSize > 1) {
				nodeToBeSwapped = maxNode(nodeToBeDeleted.left);
			} else {
				nodeToBeSwapped = minNode(nodeToBeDeleted.right);
			}
			nodeToBeDeleted.item = nodeToBeSwapped.item;
			return delete(nodeToBeSwapped);
		}
		
		
	}
	
	public MyBinaryNode<E> maxNode(MyBinaryNode<E> node) {
		while (node.right != null)
			node = node.right;
		return node;
	}
	
	public MyBinaryNode<E> minNode(MyBinaryNode<E> node) {
		while (node.left != null)
			node = node.left;
		return node;
	}
	
	private MyBinaryNode<E> getNthNode(MyBinaryNode<E> root, int n) {
		if (root == null)
			return null;
		
		int leftSize = 0;
		if (root.left != null)
			leftSize = root.left.size;
		
		if (n <= leftSize) {
			return getNthNode(root.left, n);
		} else if (n > leftSize + 1) {
			return getNthNode(root.right, n - (leftSize+1));
		} else
			return root;
	}
	
	@Override
	public String toString() {
		return item.toString();
	}
}
