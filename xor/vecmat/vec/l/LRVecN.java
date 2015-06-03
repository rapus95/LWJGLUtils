package xor.vecmat.vec.l;

import java.util.Arrays;

class LRVecN extends LVecN {

	public final long[] vec;

	LRVecN(int size, long fill) {
		vec = new long[size];
		Arrays.fill(vec, fill);
	}

	LRVecN(long... data) {
		vec = data;
	}

	@Override
	public int dim() {
		return vec.length;
	}

	@Override
	public long get(int i) {
		return vec[i];
	}

	@Override
	public void set(int i, long v) {
		vec[i] = v;
	}

	@Override
	public LVecN clone() {
		long[] data = new long[vec.length];
		System.arraycopy(vec, 0, data, 0, vec.length);
		return new LRVecN(data);
	}

}
