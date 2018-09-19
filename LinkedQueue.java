import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<T> implements Iterable<T> {

	private Node first, last;
	private int size;

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void enqueue(T item) {
		if (isEmpty()) {
			first = new Node();
			first.item = item;

			last = first;
		} else {
			Node newLast = new Node();
			newLast.item = item;
			last.next = newLast;

			last = newLast;
		}

		size++;
	}

	public T dequeue() {
		if (isEmpty())
			throw new NoSuchElementException();

		T item = first.item;
		first = first.next;

		size--;
		return item;
	}

	@Override
	public Iterator<T> iterator() {
		return new QueueIterator();
	}

	private class QueueIterator implements Iterator<T> {

		private Node current = first;

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
