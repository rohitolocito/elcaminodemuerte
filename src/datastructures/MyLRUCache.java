package datastructures;

import java.util.HashMap;
import java.util.Map;

public class MyLRUCache<K, V> {

	private class Node {
		K key;
		V value;
		Node prev;
		Node next;
		
		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}
	
	private Map<K, Node> map;
	private int maxCacheSize;
	private int currCacheSize = 0;
	private Node head = null, tail = null;
	
	public MyLRUCache(int maxCacheSize) {
		this.maxCacheSize = maxCacheSize;
		this.map = new HashMap<>();
	}
	
	public void add(K key, V value) {
		Node node = map.getOrDefault(key, new Node(key, value));
		if (!map.containsKey(key)) {
			if (currCacheSize == maxCacheSize) {
				removeLeastRecentlyUsed();
			}
			map.put(key, node);
			currCacheSize++;
		} else {
			node.value = value;
		}
		moveNodeToFront(node);
	}
	
	private void removeLeastRecentlyUsed() {
		Node prev = tail.prev;
		prev.next = null;
		tail = prev;
		currCacheSize--;
	}
	
	private void moveNodeToFront(Node node) {
		Node prev = node.prev;
		Node next = node.next;
		
		// special case.. only 1 node in list and same node has been accessed
		if (head == node && tail == node) {
			return;
		}
		
		if (prev == null && next == null) { // new node 
			node.next = head;
			if (head != null) {
				head.prev = node;
			}
			head = node;
			
			if (tail == null) {
				tail = node;
			}
		} else if (prev == null) {
			// nothing to be done 
		} else if (next == null) {
			prev.next = null;
			tail = prev;
			node.next = head;
			head = node;
		} else {
			prev.next = next;
			next.prev = prev;
			node.next = head;
			head = node;
		}
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		Node node = head;
		while (node != null) {
			builder.append("("+node.key +":" + node.value+")");
			builder.append("\u219D");
			node = node.next;
		}
		builder.append("Null");
		return builder.toString();
	}
	
}
