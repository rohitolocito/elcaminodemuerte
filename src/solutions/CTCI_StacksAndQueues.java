package solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import datastructures.MyStackAsLinkedList;

class Animal {
	
}

class Dog extends Animal {
	
}

class Cat extends Animal {
	
}

class AnimalShelter {
	
	private class AnimalTimestamp {
		Animal animal;
		Date date;
		
		public AnimalTimestamp(Animal animal) {
			this.animal = animal;
			this.date = new Date();
		}
	}
	
	private Queue<AnimalTimestamp> dogs, cats;
	
	public AnimalShelter() {
		dogs = new LinkedList<>();
		cats = new LinkedList<>();
	}
	
	public void addAnimal(Animal animal) {
		AnimalTimestamp animalEntry = new AnimalTimestamp(animal);
		if (isDog(animal)) {
			dogs.add(animalEntry);
		}
		else if (isCat(animal)) {
			cats.add(animalEntry);
		}
		else 
			throw new RuntimeException("We only accepts cats & dogs !");
	}
	
	public Animal takeHome() {
		if (size() == 0)
			throw new RuntimeException("No animal found");
		
		if (dogs.isEmpty()) {
			return cats.remove().animal;
		}
		
		if (cats.isEmpty()) {
			return dogs.remove().animal;
		}
		
		AnimalTimestamp dog = dogs.peek();
		AnimalTimestamp cat = cats.peek();
		
		if (dog.date.before(cat.date)) {
			return dogs.poll().animal;
		} else {
			return cats.poll().animal;
		}
	}
	
	public Animal takeHomeDog() {
		if (dogs.isEmpty()) 
			throw new RuntimeException("No Dogs found");
		
		return dogs.poll().animal;
	}
	
	public Animal takeHomeCat() {
		if (cats.isEmpty()) 
			throw new RuntimeException("No Dogs found");
		
		return cats.poll().animal;
	}
	
	public int size() {
		return dogs.size() + cats.size();
	}
	
	public boolean isDog(Animal animal) {
		return animal instanceof Dog;
	}
	
	public boolean isCat(Animal animal) {
		return animal instanceof Cat;
	}
	
	
	
}


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
	
	// using two stacks
		private static class MyQueue<E> {
			
			Stack<E> stack1, stack2;
			
			public MyQueue() {
				this.stack1 = new Stack<>();
				this.stack2 = new Stack<>();
			}
			
			public E add(E item) {
				this.stack1.add(item);
				return item;
			}
			
			public E remove() {
				if (isEmpty()) 
					throw new RuntimeException();
				
				if (!this.stack2.isEmpty())
					return this.stack2.pop();
				
				while(!this.stack1.isEmpty()) {
					this.stack2.push(this.stack1.pop());
				}
				
				return this.stack2.pop();
			}
			
			public boolean isEmpty() {
				return size() == 0;
			}
			
			public int size() {
				return this.stack1.size() + this.stack2.size();
			}
		}
		

	public static void main(String[] args) {
		
		CTCI_StacksAndQueues run = new CTCI_StacksAndQueues();
		
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
		
		MyQueue<Integer> queue = new MyQueue<>();
		queue.add(1);
		queue.add(2);
		queue.add(3);
		queue.add(4);
		System.out.println(queue);
		System.out.println(queue.remove());
		
		Stack<Integer> toBeSorted = new Stack<>();
		toBeSorted.push(4);
		toBeSorted.push(3);
		toBeSorted.push(6);
		toBeSorted.push(2);
		toBeSorted.push(5);
		
		run.sortStack(toBeSorted);
		System.out.println(toBeSorted);
	}
	
	public void sortStack(Stack<Integer> stack) {
		Stack<Integer> temp = new Stack<>();
		
		while (!stack.isEmpty()) {
			Integer item = stack.pop();
			while (!temp.isEmpty() && temp.peek() > item) {
				stack.push(temp.pop());
			}
			temp.push(item);
		}
		
		while (!temp.isEmpty()) {
			stack.push(temp.pop());
		}
	}
	
	

}
