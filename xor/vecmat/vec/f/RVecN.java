package xor.vecmat.vec.f;

import java.util.Arrays;

class RVecN extends VecN {

	public final float[] vec;

	RVecN(int size, float fill) {
		vec = new float[size];
		Arrays.fill(vec, fill);
	}

	RVecN(float... data) {
		vec = data;
	}

	@Override
	public int dim() {
		return vec.length;
	}

	@Override
	public float get(int i) {
		return vec[i];
	}

	@Override
	public void set(int i, float v) {
		vec[i] = v;
	}

	@Override
	public VecN clone() {
		float[] data = new float[vec.length];
		System.arraycopy(vec, 0, data, 0, vec.length);
		return new RVecN(data);
	}

}
