import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<T> implements Iterable<T> {

	private Node head;
	private int size;

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void push(T item) {
		Node newHead = new Node();
		newHead.item = item;
		newHead.next = head;
		head = newHead;

		size++;
	}

	public T pop() {
		if (isEmpty())
			throw new NoSuchElementException();

		T item = head.item;
		head = head.next;

		size--;
		return item;
	}

	@Override
	public Iterator<T> iterator() {
		return new StackIterator();
	}

	private class StackIterator implements Iterator<T> {

		private Node current = head;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			T item = current.item;
			current = current.next;

			return item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	private class Node {
		private T item;
		private Node next;
	}
}
