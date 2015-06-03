package xor.vecmat.vec.l;

class LVVecN extends LVecN {

	private LVec<?, ?> v;

	private int[] indices;

	LVVecN(LVec<?, ?> v, int[] indices) {
		this.v = v;
		this.indices = indices;
	}

	@Override
	public int dim() {
		return indices.length;
	}

	@Override
	public long get(int i) {
		return v.get(indices[i]);
	}

	@Override
	public void set(int i, long x) {
		v.set(indices[i], x);
	}

}
