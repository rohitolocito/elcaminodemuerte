package datastructures;

import java.util.HashMap;
import java.util.Map;

public class MyTrie {
	
	private class TrieNode {
		char c;
		boolean isWord = false;
		int count = 0;
		Map<Character, TrieNode> map;
		
		public TrieNode(char c) {
			this.c = c;
			this.map = new HashMap<>();
		}
		
		public boolean contains(char c) {
			return this.map.containsKey(c);
		}
		
	}
	
	private TrieNode root;
	
	public MyTrie() {
		this.root = new TrieNode('\0');
	}
	
	public void add(String s) {
		TrieNode node = this.root;
		
		for (int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			if (node.contains(c)) {
				node = node.map.get(c);
			} else {
				TrieNode charNode = new TrieNode(c);
				node.map.put(c, charNode);
				node = charNode;
			}
		}
		
		node.isWord = true;
		node.count++;
	}
	
	public int getCount(String s) {
		TrieNode node = this.root;
		
		for (int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			if (!node.contains(c)) {
				return 0;
			} else {
				node = node.map.get(c);
			}
		}
		
		return node.isWord ? node.count + node.map.size() :  node.map.size();
	}
	
	public boolean isWord(String s) {
		TrieNode node = containsString(s);
		return node != null ? node.isWord : false;
	}
	
	public boolean contains(String s) {
		return containsString(s) != null;
	}
	
	private TrieNode containsString(String s) {
		TrieNode node = this.root;
		
		for(int i=0; i<s.length(); i++) {
			node = node.map.getOrDefault(s.charAt(i), null);
			if (node == null)
				return node;
		}
		
		return node;
	}
}
