package datastructures;

public interface MyStackInterface<E> {
	E push(E item);
	E pop();
	E peek();
	boolean isEmpty();
	int search(E item);
}
