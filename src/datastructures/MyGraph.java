package datastructures;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class MyGraph<E> {
	
	private class Node {
		E name;
		Set<Node> edges;
		
		public Node(E name) {
			this.edges = new HashSet<>();
			this.name = name;
		}
		
		void addEdge(Node edge) {
			this.edges.add(edge);
		}
		
		boolean hasEdge(Node edge) {
			return this.edges.contains(edge);
		}
		
		Set<Node> getEdges() {
			return this.edges;
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
}
