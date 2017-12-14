package datastructures;

public class MyBinaryNode<E> {

	
	E item;
	
	public MyBinaryNode(E item) {
		this.item = item;
	}
	
	// they are public so that i can easily construct a Binary tree for tests
	public MyBinaryNode<E> left; 
	public MyBinaryNode<E> right;
	
	@Override
	public String toString() {
		return item.toString();
	}
}
