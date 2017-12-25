package datastructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

enum State {
	BLANK,
	PARTIAL,
	VISITED
}

public class MyGraph<E> {
	
	private class Node {
		E name;
		Set<Node> edges; // outgoing edges
		Set<Node> incomingEdges;
		
		public Node(E name) {
			this.edges = new HashSet<>();
			this.incomingEdges = new HashSet<>();
			this.name = name;
		}
		
		void addEdge(Node edge) {
			this.edges.add(edge);
		}
		
		void addIncomingEdge(Node edge) {
			this.incomingEdges.add(edge);
		}
		
		boolean hasEdge(Node edge) {
			return this.edges.contains(edge);
		}
		
		boolean hasIncomingEdge(Node edge) {
			return this.incomingEdges.contains(edge);
		}
		
		Set<Node> getEdges() {
			return this.edges;
		}
		
		Set<Node> getIncomingEdges() {
			return this.incomingEdges;
		}
		
		@Override
		public String toString() {
			return name.toString();
		}
	}
	
	private Map<E, Node> graph;
	
	public MyGraph() {
		this.graph = new HashMap<>();
	}
	
	public void addNode(E name) {
		if (this.graph.containsKey(name))
			throw new RuntimeException("Node already exists");
		
		this.graph.put(name, new Node(name));
	}
	
	public void addEdge(E from, E to, boolean isBidirectional) {
		if (!this.graph.containsKey(from)) {
			addNode(from);
		}
		
		if (!this.graph.containsKey(to)) {
			addNode(to);
		}
		
		this.graph.get(from).addEdge(this.graph.get(to));
		this.graph.get(to).addIncomingEdge(this.graph.get(from));
		if (isBidirectional) 
			this.graph.get(to).addEdge(this.graph.get(from));
	}
	
	public boolean hasNode(E name) {
		return this.graph.containsKey(name);
	}
	
	public boolean hasEdge(E from, E to) {
		return hasNode(from) && hasNode(to) && this.graph.get(from).hasEdge(this.graph.get(to));
	}
	
	public boolean hasPath(E from, E to) {
		if (!hasNode(from) || !hasNode(to))
			return false;
		
		return hasPath(this.graph.get(from), this.graph.get(to));
	}
	
	private boolean hasPath(Node source, Node destination) {
		Queue<Node> queue = new LinkedList<>();
		Set<Node> visited = new HashSet<>();
		
		queue.add(source);
		visited.add(source);
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			if (node == destination)
				return true;
			
			Set<Node> edges = node.getEdges();
			for(Node edge : edges) {
				if (!visited.contains(edge)) {
					visited.add(edge);
					queue.add(edge);
				}
			}
		}
		return false;
	}
	// topological sort
	public List<E> buildOrderUsingDFS() {
		MyStack<E> stack = new MyStack<>();
		Map<Node, State> map = new HashMap<>();
		for (Node node : this.graph.values()) {
			if (!map.getOrDefault(node, State.BLANK).equals(State.VISITED)) {
				buildOrderUsingDFS(node, stack, map);
			}
		}
		
		List<E> result = new ArrayList<>();
		while(!stack.isEmpty()) 
			result.add(stack.pop());
		return result;
	}
	
	private void buildOrderUsingDFS(Node root, MyStack<E> stack, Map<Node, State> states) {
		if (root == null)
			return;
		
		if (states.getOrDefault(root, State.BLANK).equals(State.PARTIAL))
			throw new RuntimeException("Cannot plan the projects due to cyclic dependency");
		
		if (states.getOrDefault(root, State.BLANK).equals(State.VISITED))
			return;
		
		states.put(root, State.PARTIAL);
		
		for(Node child: root.edges) {
			buildOrderUsingDFS(child, stack, states);
		}
		
		states.put(root, State.VISITED);
		stack.push(root.name);
	}
	
	
	public List<E> buildOrder() {
		List<Node> order = new ArrayList<>();
		Map<Node, Integer> mapDependencyCounts = new HashMap<>();
		
		for(Node vertex : this.graph.values()) {
			if (vertex.incomingEdges.isEmpty()) {
				order.add(vertex);
			}
			mapDependencyCounts.put(vertex, vertex.incomingEdges.size());
		}
		
		// at any point in time order should only have projects that have 0 dependencies
		int toBeProcessed = 0;
		while (toBeProcessed < order.size()) {
			Set<Node> children = order.get(toBeProcessed).edges;
			for(Node child : children) {
				mapDependencyCounts.put(child, mapDependencyCounts.get(child)-1);
				if (mapDependencyCounts.get(child) == 0) {
					order.add(child);
				}
			}
			toBeProcessed++;
		}
		
		return order.size() != this.graph.size() ? null : order.stream()
					.map(node -> node.name)
					.collect(Collectors.toList());
	}
}
