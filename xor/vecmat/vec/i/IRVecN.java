package xor.vecmat.vec.i;

import java.util.Arrays;

class IRVecN extends IVecN {

	public final int[] vec;

	IRVecN(int size, int fill) {
		vec = new int[size];
		Arrays.fill(vec, fill);
	}

	IRVecN(int... data) {
		vec = data;
	}

	@Override
	public int dim() {
		return vec.length;
	}

	@Override
	public int get(int i) {
		return vec[i];
	}

	@Override
	public void set(int i, int v) {
		vec[i] = v;
	}

	@Override
	public IVecN clone() {
		int[] data = new int[vec.length];
		System.arraycopy(vec, 0, data, 0, vec.length);
		return new IRVecN(data);
	}

}
