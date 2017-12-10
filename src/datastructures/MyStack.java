package datastructures;

import java.util.EmptyStackException;
import java.util.Vector;

public class MyStack<E> extends Vector<E> implements MyStackInterface<E> {
	
	public synchronized E push(E item) {
		add(item);
		return item;
	}
	
	public synchronized E pop() {
		peek();
		
		int len = size();
		
		return remove(len-1);
	}
	
	public synchronized E peek() {
		int len = size();
		if (len == 0)
			throw new EmptyStackException();
		
		return get(len-1);
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public int search(E item) {
		int i = lastIndexOf(item);
		
		if (i >= 0)
			return size() - i;
		
		return -1;
	}
	
}
