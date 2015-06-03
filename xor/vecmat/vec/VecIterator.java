package xor.vecmat.vec;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class VecIterator<V> implements ListIterator<V> {

	private Vec_base<?, ?, V> vec;

	private final int max;

	private int i;

	private int l = -1;

	public VecIterator(Vec_base<?, ?, V> vec) {
		this.vec = vec;
		max = vec.dim();
		i = 0;
	}

	@Override
	public boolean hasNext() {
		return max > i;
	}

	@Override
	public V next() {
		if (i == max)
			throw new NoSuchElementException();
		l = i;
		return this.vec.getW(i++);
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void add(V f) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean hasPrevious() {
		return i > 0;
	}

	@Override
	public int nextIndex() {
		return i;
	}

	@Override
	public V previous() {
		if (i == 0)
			throw new NoSuchElementException();
		l = --i;
		return this.vec.getW(i);
	}

	@Override
	public int previousIndex() {
		return i - 1;
	}

	@Override
	public void set(V f) {
		if (l == -1)
			throw new IllegalStateException();
		this.vec.set(l, f);
		l = -1;
	}

}