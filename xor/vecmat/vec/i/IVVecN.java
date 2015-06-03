package xor.vecmat.vec.i;

class IVVecN extends IVecN {

	private IVec<?, ?> v;

	private int[] indices;

	IVVecN(IVec<?, ?> v, int[] indices) {
		this.v = v;
		this.indices = indices;
	}

	@Override
	public int dim() {
		return indices.length;
	}

	@Override
	public int get(int i) {
		return v.get(indices[i]);
	}

	@Override
	public void set(int i, int x) {
		v.set(indices[i], x);
	}

}
