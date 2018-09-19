import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayQueue<T> implements Iterable<T> {

	private static final int DEFAULT_CAPACITY = 8;

	private T[] arr;
	private int size, head, tail;

	public ArrayQueue() {
		this(DEFAULT_CAPACITY);
	}

	public ArrayQueue(int capacity) {
		arr = (T[]) new Object[capacity];
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void enqueue(T item) {
		if (arr.length == size)
			resize(size * 2);

		arr[tail++ % arr.length] = item;
		size++;
	}

	public T dequeue() {
		if (isEmpty())
			throw new NoSuchElementException();

		T item = arr[head];
		arr[head++] = null;
		size--;

		if (4 * size <= arr.length)
			resize(arr.length / 2);

		return item;
	}

	private void resize(int capacity) {
		T[] newArr = (T[]) new Object[capacity];

		for (int i = head; i < tail; i++)
			newArr[i - head] = arr[i % arr.length];

		arr = newArr;
		head = 0;
		tail = size;
	}

	@Override
	public Iterator<T> iterator() {
		return new QueueIterator();
	}

	private class QueueIterator implements Iterator<T> {

		private int i = head;

		@Override
		public boolean hasNext() {
			return i < tail;
		}

		@Override
		public T next() {
			return arr[i++ % arr.length];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
