package solutions;

import datastructures.MyBinaryNode;
import datastructures.MyBinaryTree;
import datastructures.MyGraph;

public class CTCI_TreesAndGraphs {
	

	public static void main(String[] args) {
		
		CTCI_TreesAndGraphs run = new CTCI_TreesAndGraphs();
	
		MyGraph<Integer> graph = new MyGraph<>();
		graph.addEdge(1, 2, false);
		graph.addEdge(3, 3, false);
		graph.addEdge(2, 3, false);
		graph.addEdge(0, 1, false);
		graph.addEdge(0, 2, false);
		graph.addEdge(2, 0, false);
		
		System.out.println(run.hasRouteBetweenNodes(graph, 1, 3));
		System.out.println(run.hasRouteBetweenNodes(graph, 3, 0));
		
		Integer[] arr = {0, 2, 8, 10, 14, 16, 19};
		
		MyBinaryTree<Integer> binaryTree = new MyBinaryTree<Integer>(arr);
		
		System.out.println(binaryTree.toString());
		System.out.println(binaryTree.listOfDepths(false));
		System.out.println(binaryTree.listOfDepths(true));
		
		
		MyBinaryNode<Integer> node = new MyBinaryNode<Integer>(1);
        node.left = new MyBinaryNode<Integer>(2);
        node.right = new MyBinaryNode<Integer>(3);
        node.left.left = new MyBinaryNode<Integer>(4);
        node.left.right = new MyBinaryNode<Integer>(5);
        node.left.left.left = new MyBinaryNode<Integer>(8);
        MyBinaryTree<Integer> tree = new MyBinaryTree<>(node);
        
        System.out.println(tree.isBalancedInefficient());
        System.out.println(tree.isBalanced());
        
        System.out.println(binaryTree.isBST());
        System.out.println(tree.isBST());
        
        MyBinaryNode<String> nodeString = new MyBinaryNode<String>("jackass");
        nodeString.left = new MyBinaryNode<String>("in");
        nodeString.right = new MyBinaryNode<String>("the");
        
        MyBinaryTree<String> treeString = new MyBinaryTree<>(nodeString);
        System.out.println(treeString.inOrderList());
        System.out.println(treeString.isBST());
        
	}
	
	public boolean hasRouteBetweenNodes(MyGraph<Integer> graph, int from, int to) {
		return graph.hasPath(from, to);
	}
	
	
	

}
