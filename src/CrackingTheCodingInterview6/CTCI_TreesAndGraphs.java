package CrackingTheCodingInterview6;

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
        
        System.out.println("**** isBST ****");
        
        System.out.println(binaryTree.isBST());
        System.out.println(binaryTree.isBSTEfficient());
        
        System.out.println("**** isBST ****");
        
        System.out.println(tree.isBST());
        System.out.println(tree.isBSTEfficient());
        
        MyBinaryNode<String> nodeString = new MyBinaryNode<String>("jackass");
        nodeString.left = new MyBinaryNode<String>("in");
        nodeString.right = new MyBinaryNode<String>("the");
        
        MyBinaryTree<String> treeString = new MyBinaryTree<>(nodeString);
        System.out.println(treeString.inOrderList());
        
        System.out.println("**** isBST ****");
        
        System.out.println(treeString.isBST());
        System.out.println(treeString.isBSTEfficient());
        
        MyBinaryNode<Integer> numberNode = new MyBinaryNode<Integer>(4);
        numberNode.left = new MyBinaryNode<Integer>(2);
        numberNode.right = new MyBinaryNode<Integer>(5);
        numberNode.left.left = new MyBinaryNode<Integer>(1);
        
        System.out.println("**** isBST ****");
        System.out.println(run.isBSTForIntegerKeys(numberNode));
        
        
        Integer arr1[] = {0,1,1,2,3,5,8,13};
        MyBinaryTree<Integer> toFindSuccessor = new MyBinaryTree<>(arr1);
        MyBinaryNode<Integer> treeConstructed = toFindSuccessor.root();
        System.out.println("Root => "+ treeConstructed);
        System.out.println("Tree => " + toFindSuccessor.listOfDepths(false));
        
        //			2
        //		1		5
        //	  0   1   3   8
        //					 13
        
        
        
        System.out.println("Successor for 2 => " + toFindSuccessor.inOrderSuccessor(treeConstructed));
        System.out.println("Successor for 1 => " + toFindSuccessor.inOrderSuccessor(treeConstructed.left.right));
        System.out.println("Successor for 0 => " + toFindSuccessor.inOrderSuccessor(treeConstructed.left.left));
        System.out.println("Successor for 8 => " + toFindSuccessor.inOrderSuccessor(treeConstructed.right.right));
        System.out.println("Successor for 13 => " + toFindSuccessor.inOrderSuccessor(treeConstructed.right.right.right));
        
        System.out.println("**** LCA ****");
        
        System.out.println("LCA for 1, 3 => " + toFindSuccessor.firstCommonAncestor(treeConstructed.left.right, treeConstructed.right.left));
        System.out.println("LCA for 3, 13 => " + toFindSuccessor.firstCommonAncestor(treeConstructed.right.left, treeConstructed.right.right.right));
        System.out.println("LCA for 1, 1 => " + toFindSuccessor.firstCommonAncestor(treeConstructed.left, treeConstructed.left.right));
        System.out.println("LCA for 3, unknown => " + toFindSuccessor.firstCommonAncestor(treeConstructed.right.left, new MyBinaryNode<Integer>(7)));

        System.out.println("**** LCA ****");
        
        System.out.println("LCA for 1, 3 => " + toFindSuccessor.firstCommonAncestorUsingParent(treeConstructed.left.right, treeConstructed.right.left));
        System.out.println("LCA for 3, 13 => " + toFindSuccessor.firstCommonAncestorUsingParent(treeConstructed.right.left, treeConstructed.right.right.right));
        System.out.println("LCA for 1, 1 => " + toFindSuccessor.firstCommonAncestorUsingParent(treeConstructed.left, treeConstructed.left.right));
        System.out.println("LCA for 3, unknown => " + toFindSuccessor.firstCommonAncestorUsingParent(treeConstructed.right.left, new MyBinaryNode<Integer>(7)));

        
        MyGraph<Character> projectPlanner = new MyGraph<>();
        projectPlanner.addEdge('c', 'a', false);
        projectPlanner.addEdge('b', 'a', false);
        projectPlanner.addEdge('a', 'e', false);
        projectPlanner.addEdge('f', 'a', false);
        projectPlanner.addEdge('b', 'e', false);
        projectPlanner.addEdge('d', 'g', false);
        projectPlanner.addEdge('f', 'c', false);
        projectPlanner.addEdge('f', 'b', false);
        System.out.println(projectPlanner.buildOrder());
        System.out.println(projectPlanner.buildOrderUsingDFS());
        
        MyGraph<Character> projectPlanner1 = new MyGraph<>();
        projectPlanner1.addNode('e');
        projectPlanner1.addEdge('a', 'd', false);
        projectPlanner1.addEdge('f', 'b', false);
        projectPlanner1.addEdge('b', 'd', false);
        projectPlanner1.addEdge('f', 'a', false);
        projectPlanner1.addEdge('d', 'c', false);
        System.out.println(projectPlanner1.buildOrder());
        System.out.println(projectPlanner1.buildOrderUsingDFS());
        
        MyBinaryNode<Integer> root = new MyBinaryNode<Integer>(4);
        root.left = new MyBinaryNode<Integer>(2);
        root.left.left = new MyBinaryNode<Integer>(1);
        root.left.right = new MyBinaryNode<Integer>(3);
        root.right = new MyBinaryNode<Integer>(5);
        MyBinaryTree<Integer> treez = new MyBinaryTree<>(root);
        System.out.println(treez.allPossibleArrays());
        
        MyBinaryNode<Integer> root1 = new MyBinaryNode<Integer>(50);
        root1.left = new MyBinaryNode<Integer>(20);
        root1.left.left = new MyBinaryNode<Integer>(10);
        root1.left.right = new MyBinaryNode<Integer>(25);
        root1.left.left.right = new MyBinaryNode<Integer>(15);
        root1.left.left.left = new MyBinaryNode<Integer>(5);
        
        root1.right = new MyBinaryNode<Integer>(60);
        root1.right.right = new MyBinaryNode<Integer>(70);
        root1.right.right.left = new MyBinaryNode<Integer>(65);
        root1.right.right.right = new MyBinaryNode<Integer>(80);
        
        MyBinaryTree<Integer> treez1 = new MyBinaryTree<>(root1);
        System.out.println(treez1.allPossibleArrays().size());
        
        MyBinaryNode<Integer> root2 = new MyBinaryNode<Integer>(60);
        root2.right = new MyBinaryNode<Integer>(70);
        root2.right.left = new MyBinaryNode<Integer>(65);
        root2.right.right = new MyBinaryNode<Integer>(80);
        
        System.out.println(treez1.isSubTreeBetterWorseCase(root1, root2));
        System.out.println(treez1.isSubTreeBetterAverageCase(root1, root2));
        
        //			2
        //		1		5
        //	  0   1   3   8
        //					 13
        
        MyBinaryTree<Integer> randomTree = new MyBinaryTree<>(arr1);
        MyBinaryNode<Integer> randomTreeRoot = randomTree.root();
        System.out.println(randomTreeRoot.size() + "; leftSize => " + randomTreeRoot.leftSize() + "; rightSize =>"+randomTreeRoot.rightSize());
        System.out.println(randomTreeRoot.random());
        System.out.println(randomTree.inOrderList());
        System.out.println(randomTreeRoot.delete(randomTreeRoot.right));
        System.out.println(randomTree.inOrderList());
        System.out.println(randomTreeRoot.delete(randomTreeRoot.right));
        System.out.println(randomTree.inOrderList());
        System.out.println(randomTreeRoot.delete(randomTreeRoot));
        System.out.println(randomTree.inOrderList());
        System.out.println(randomTreeRoot);
        
        Integer arr2[] = {0,1,5,2,3,5,8,13};
        
        //			2
        //		1		5
        //	  0   5   3   8
        //					 13
        MyBinaryTree<Integer> sumPathsTree = new MyBinaryTree<>(arr2);
        System.out.println("paths with sum 8 => "+ sumPathsTree.pathsWithSumCount(8));
        System.out.println("paths with sum 8 => "+ sumPathsTree.pathsWithSum(8));
        
        MyBinaryNode<Integer> okay = new MyBinaryNode<Integer>(10);
        okay.left = new MyBinaryNode<Integer>(5);
        okay.left.left = new MyBinaryNode<Integer>(3);
        okay.left.left.left = new MyBinaryNode<Integer>(3);
        
        okay.left.right = new MyBinaryNode<Integer>(2);
        okay.left.right.right = new MyBinaryNode<Integer>(1);
        
        okay.left.left.right = new MyBinaryNode<Integer>(-2);
        
        okay.right = new MyBinaryNode<Integer>(-3);
        okay.right.right = new MyBinaryNode<Integer>(11);
        
        MyBinaryTree<Integer> okayTree =new MyBinaryTree<>(okay);
        
        System.out.println("paths with sum 8 => " + okayTree.pathsWithSumCount(6));
        System.out.println("paths with sum 8 => "+ okayTree.pathsWithSum(6));
	}
	
	public boolean hasRouteBetweenNodes(MyGraph<Integer> graph, int from, int to) {
		return graph.hasPath(from, to);
	}
	
	public boolean isBSTForIntegerKeys(MyBinaryNode<Integer> node) {
		return isBSTForIntegerKeys(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	private boolean isBSTForIntegerKeys(MyBinaryNode<Integer> node, int min, int max) {
		if (node == null)
			return true;
		
		if (node.item >= min && node.item < max) {
			return isBSTForIntegerKeys(node.left, min, node.item) &&
					isBSTForIntegerKeys(node.right, node.item, max) ;
		}
		
		return false;
	}
	
	
	

}
