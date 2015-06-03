package xor.vecmat.vec.b;

class BVVecN extends BVecN {

	private BVec<?> v;

	private int[] indices;

	BVVecN(BVec<?> v, int[] indices) {
		this.v = v;
		this.indices = indices;
	}

	@Override
	public int dim() {
		return indices.length;
	}

	@Override
	public boolean get(int i) {
		return v.get(indices[i]);
	}

	@Override
	public void set(int i, boolean x) {
		v.set(indices[i], x);
	}

}
