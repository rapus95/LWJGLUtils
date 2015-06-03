package xor.vecmat.vec.d;

import java.util.Arrays;

class DRVecN extends DVecN {

	public final double[] vec;

	DRVecN(int size, double fill) {
		vec = new double[size];
		Arrays.fill(vec, fill);
	}

	DRVecN(double... data) {
		vec = data;
	}

	@Override
	public int dim() {
		return vec.length;
	}

	@Override
	public double get(int i) {
		return vec[i];
	}

	@Override
	public void set(int i, double v) {
		vec[i] = v;
	}

	@Override
	public DVecN clone() {
		double[] data = new double[vec.length];
		System.arraycopy(vec, 0, data, 0, vec.length);
		return new DRVecN(data);
	}

}
