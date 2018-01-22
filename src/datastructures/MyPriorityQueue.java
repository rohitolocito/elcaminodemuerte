package datastructures;

import java.util.Arrays;
import java.util.Comparator;

public class MyPriorityQueue<E> {
	
	private Object[] queue;
	
	private Comparator<E> comparator;
	
	private static final int INITIAL_CAPACITY = 16;
	
	private int size = 0;
	
	public MyPriorityQueue() {
		this(INITIAL_CAPACITY, null);
	}
	
	public MyPriorityQueue(Comparator<E> comparator) {
		this(INITIAL_CAPACITY, comparator);
	}
	
	public MyPriorityQueue(int initialCapacity, Comparator<E> comparator) {
		this.queue = new Object[initialCapacity];
		this.comparator = comparator;
	}
	
	
	private int parent(int index) {
		return index >>> 1;
	}
	
	private int leftChild(int index) {
		return 2*index + 1;
	}
	
	private int rightChild(int index) {
		return 2*(index + 1);
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public boolean add(E item) {
		if (item == null)
			return false;
		
		if (size >= queue.length) {
			grow(size+1);
		}
		
		if (size == 0) {
			queue[0] = item;
		} else {
			siftUp(size, item);
		}
		
		size++;
		return true;
	}
	
	public E poll() {
		if (size == 0)
			return null;
		
		E item = (E)queue[0];
		queue[0] = queue[size-1];
		queue[size-1] = null;
		size--;
		if (size != 0)
			siftDown(0, (E)queue[0]);
		
		return item;
	}
	
	private void siftUp(int index, E item) {
		if (this.comparator != null) {
			siftUpComparator(index, item);
		} else {
			siftUpComparable(index, item);
		}
	}
	
	private void siftUpComparator(int index, E item) {
		while(index > 0) {
			int parent = parent(index);
			E parentObj = (E) queue[parent];
			if (comparator.compare(item, parentObj) > 0)
				break;
			queue[index] = parentObj;
			index = parent;
		}
		queue[index] = item;
	}
	
	private void siftUpComparable(int index, E item) {
		Comparable<E> key = (Comparable<E>) item;
		while(index > 0) {
			int parent = parent(index);
			E parentObj = (E) queue[parent];
			if (key.compareTo(parentObj) > 0)
				break;
			queue[index] = parentObj;
			index = parent;
		}
		queue[index] = item;
	}
	
	private void siftDown(int index, E item) {
		if (this.comparator != null) {
			siftDownComparator(index, item);
		} else {
			siftDownComparable(index, item);
		}
	}
	
	private void siftDownComparator(int index, E item) {
		while (index < (size >>> 1)) { // no need to visit leaf nodes
			int child = leftChild(index); 
			E childItem = (E)queue[child];
			
			int right = rightChild(index);
			if (right < size && comparator.compare((E)queue[right], childItem) <= 0)
				childItem = (E)queue[child = right];
			
			if (comparator.compare(item, childItem) <= 0)
				break;
			
			queue[index] = childItem;
			index = child;
		}
		queue[index] = item;
	}
	
	private void siftDownComparable(int index, E item) {
		Comparable<E> key = (Comparable<E>) item;
		while (index < (size >>> 1)) { // no need to visit leaf nodes
			int child = leftChild(index); 
			E childItem = (E)queue[child];
			
			int right = rightChild(index);
			if (right < size && ((Comparable<E>)queue[right]).compareTo(childItem) <= 0)
				childItem = (E)queue[child = right];
			
			if (key.compareTo(childItem) <= 0)
				break;
			
			queue[index] = childItem;
			index = child;
		}
		queue[index] = item;
	}
	
	private void grow(int minCapacity) {
		int oldCapacity = size;
		
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		
		if (newCapacity < 0) { // overflow ?
			newCapacity = hugeCapacity(minCapacity);
		}
		
		queue = Arrays.copyOf(queue, newCapacity);
	}
	
	private int hugeCapacity(int minCapacity) {
		if (minCapacity < 0)
			throw new OutOfMemoryError();
		
		return minCapacity > Integer.MAX_VALUE-8 ? Integer.MAX_VALUE : Integer.MAX_VALUE-8;
	}
}
