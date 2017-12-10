package solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

import datastructures.MyStackAsLinkedList;


class SetOfStacks<E> {
	
	private List<MyStackAsLinkedList<E>> list;
	
	private int threshold = 0;
	
	public SetOfStacks(int threshold) {
		this.list = new ArrayList<>();
		this.threshold = threshold;
	}
	
	public E push(E item) {
		int index = list.size() - 1;
		if (index < 0) {
			list.add(new MyStackAsLinkedList<>());
			index++;
		}
		if (list.get(index).size() == this.threshold) {
			list.add(new MyStackAsLinkedList<>());
			index++;
		}
		list.get(index).push(item);
		return item;
	}
	
	public E pop() {
		int index = list.size() - 1;
		if (index < 0 || list.get(index).isEmpty())
			throw new EmptyStackException();
		
		E item = list.get(index).pop();
		if (list.get(index).isEmpty())
			list.remove(index);
		
		return item;
	}
	
	public E pop(int index) {
		if (index < 0 || index >= list.size())
			throw new RuntimeException();
		
		if (list.get(index).isEmpty())
			throw new EmptyStackException();
		
		return leftShift(index, true);
	}
	
	private E leftShift(int index, boolean removeTop) {
		if (index >= list.size())
			return null;
		
		E removedNode = null;
		if (removeTop) {
			removedNode = list.get(index).pop();
		} else {
			removedNode = list.get(index).removeBottom();
		}
		
		E itemRemovedFromNextStack = leftShift(index+1, false);
		if (itemRemovedFromNextStack == null) {
			if (list.get(index).isEmpty())
				list.remove(index);
		} else {
			list.get(index).push(itemRemovedFromNextStack);
		}
		return removedNode;
		
	}
	
	@Override
	public String toString() {
		return list.toString();
	}
 	
}

class MinStack<E> {
	
	private Stack<E> data, min;
	
	public MinStack() {
		data = new Stack<>();
		min = new Stack<>();
	}
	
	public E push(E item) {
		if(data.size() == 0) {
			min.push(item);
		} else {
			if( ((Comparable<E>) item).compareTo(min()) <= 0) 
				min.push(item);
		}
		data.push(item);
		return item;
	}
	
	public E min() {
		if(min.size() == 0)
			throw new EmptyStackException();
		
		return min.peek();
	}
	
	public E pop() {
		if (data.size() == 0)
			throw new EmptyStackException();
		
		E item = data.pop();
		if (item == min.peek())
			min.pop();
		return item;
	}
}

class StackFullException extends RuntimeException {
	
}

class ThreeStacks<E> {
	
	private int stackSize = 16;
	
	private Object[] elementData;
	
	private int[] indices = {-1, -1, -1};
	
	public ThreeStacks(int stackSize) {
		this.stackSize = stackSize;
		this.elementData = new Object[this.stackSize * 3];
	}
	
	public E push(int stack, E item) {
		
		int lastElementIndex = this.indices[stack];
		int currElementIndex = lastElementIndex + 1;
		
		if(currElementIndex >= stackSize)
			throw new StackFullException(); 
		
		int index = stack * stackSize + currElementIndex;
		this.elementData[index] = item;
		this.indices[stack] = currElementIndex;
		return item;
	}
	
	public E pop(int stack) {
		int lastElementIndex = this.indices[stack];
		if(lastElementIndex == -1)
			throw new EmptyStackException();
		
		int index = stack * this.stackSize + lastElementIndex;
		E item = (E) this.elementData[index];
		this.elementData[index] = null;
		this.indices[stack]--;
		return item;
	}
	
	
	public int size(int stack) {
		return this.indices[stack] + 1;
	}
	
	public boolean isEmpty(int stack) {
		return this.indices[stack] == -1;
	}
	
	public E peek(int stack) {
		int lastElementIndex = this.indices[stack];
		if (lastElementIndex == -1)
			throw new EmptyStackException();
		
		int index = stack * this.stackSize + lastElementIndex;
		return (E) this.elementData[index];
	}
	
	@Override
	public String toString() {
		return Arrays.toString(this.elementData);
	}
}

public class CTCI_StacksAndQueues {

	public static void main(String[] args) {
		
		ThreeStacks<Integer> threeStacks = new ThreeStacks(8);
		
		threeStacks.push(1, 1);
		
		threeStacks.push(1, 1);
		threeStacks.push(1, 2);
		
		threeStacks.push(1, 1);
		threeStacks.push(1, 2);
		threeStacks.push(1, 3);
		threeStacks.push(1, 3);
		threeStacks.push(1, 3);
		threeStacks.push(0, 3);
		
		System.out.println(threeStacks);
		
		System.out.println(threeStacks.size(0)+", " + threeStacks.size(1) +", " + threeStacks.size(2));
		
		MinStack<Integer> minStack = new MinStack();
		minStack.push(5);
		System.out.println(minStack.min());
		
		minStack.push(6);
		System.out.println(minStack.min());
		
		minStack.push(3);
		System.out.println(minStack.min());
		
		minStack.push(7);
		System.out.println(minStack.min());
		
		minStack.pop();
		System.out.println(minStack.min());
		
		minStack.pop();
		System.out.println(minStack.min());
		
		SetOfStacks<Integer> set = new SetOfStacks(2);
		
		for (int i=0; i<10; i++)
			set.push(i+1);
		
		System.out.println(set);
		
		System.out.println(set.pop(2));
		
		System.out.println(set);
	}
	
	

}
