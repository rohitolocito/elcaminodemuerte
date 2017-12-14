package datastructures;

public class MyNode<K> {
	public K val;
	public MyNode next;
	public MyNode(K val) {
		this.val = val;
	}
	
	public K getVal() {
		return this.val;
	}
	
	public MyNode next() {
		return this.next;
	}
	
	public void setNext(MyNode next) {
		this.next = next;
	}
	
	public int len() {
		MyNode curr = this;
		int len = 0;
		while(curr != null) {
			len++;
			curr = curr.next;
		}
		return len;
	}
	
	@Override
	public String toString() {
		MyNode curr = this;
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
