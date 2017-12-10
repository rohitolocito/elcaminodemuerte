package datastructures;

public class Node<K> {
	public K val;
	public Node next;
	public Node(K val) {
		this.val = val;
	}
	
	public K getVal() {
		return this.val;
	}
	
	public Node next() {
		return this.next;
	}
	
	public void setNext(Node next) {
		this.next = next;
	}
	
	public int len() {
		Node curr = this;
		int len = 0;
		while(curr != null) {
			len++;
			curr = curr.next;
		}
		return len;
	}
	
	@Override
	public String toString() {
		Node curr = this;
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		while(curr != null) {
			sb.append(curr.val);
			curr = curr.next;
			if(curr != null)
				sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}
}
