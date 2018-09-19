import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack<T> implements Iterable<T> {

	private static final int DEFAULT_CAPACITY = 8;

	private T[] arr;
	private int size;

	public ArrayStack() {
		this(DEFAULT_CAPACITY);
	}

	public ArrayStack(int capacity) {
		arr = (T[]) new Object[capacity];
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void push(T item) {
		if (arr.length == size)
			resize(size * 2);

		arr[size++] = item;
	}

	public T pop() {
		if (isEmpty())
			throw new NoSuchElementException();

		T item = arr[--size];
		arr[size] = null;

		if (4 * size <= arr.length)
			resize(arr.length / 2);

		return item;
	}

	private void resize(int capacity) {
		System.arraycopy(arr, 0, arr, 0, capacity);
	}

	@Override
	public Iterator<T> iterator() {
		return new StackIterator();
	}

	private class StackIterator implements Iterator<T> {

		private int i = size;

		@Override
		public boolean hasNext() {
			return i > 0;
		}

		@Override
		public T next() {
			return arr[--i];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
