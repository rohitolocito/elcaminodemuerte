package datastructures;

import java.util.EmptyStackException;
import java.util.LinkedList;

public class MyStackAsLinkedList<E> implements MyStackInterface<E> {
	
	private LinkedList<E> list ;

	public MyStackAsLinkedList() {
		list = new LinkedList<>();
	}
	
	@Override
	public E push(E item) {
		list.addFirst(item);
		return item;
	}

	@Override
	public E pop() {
		
		peek();
		
		return list.removeFirst();
	}

	@Override
	public E peek() {
		if (list.isEmpty())
			throw new EmptyStackException();
		
		return list.getFirst();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public int search(E item) {
		return list.indexOf(item);
	}
	
	public E removeBottom() {
		peek();
		
		return list.removeLast();
	}
	
	public int size() {
		return list.size();
	}
	
	@Override
	public String toString() {
		return list.toString();
	}

}
