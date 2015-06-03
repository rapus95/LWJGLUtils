package xor.vecmat.vec.b;

import java.util.Arrays;

class BRVecN extends BVecN {

	public final boolean[] vec;

	BRVecN(int size, boolean fill) {
		vec = new boolean[size];
		Arrays.fill(vec, fill);
	}

	BRVecN(boolean... data) {
		vec = data;
	}

	@Override
	public int dim() {
		return vec.length;
	}

	@Override
	public boolean get(int i) {
		return vec[i];
	}

	@Override
	public void set(int i, boolean v) {
		vec[i] = v;
	}

	@Override
	public BVecN clone() {
		boolean[] data = new boolean[vec.length];
		System.arraycopy(vec, 0, data, 0, vec.length);
		return new BRVecN(data);
	}

}
