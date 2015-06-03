package xor.vecmat.vec.f;

class VVecN extends VecN {

	private Vec<?, ?> v;

	private int[] indices;

	VVecN(Vec<?, ?> v, int[] indices) {
		this.v = v;
		this.indices = indices;
	}

	@Override
	public int dim() {
		return indices.length;
	}

	@Override
	public float get(int i) {
		return v.get(indices[i]);
	}

	@Override
	public void set(int i, float x) {
		v.set(indices[i], x);
	}

}
