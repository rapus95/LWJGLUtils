package xor.vecmat.vec.d;

class DVVecN extends DVecN {

	private DVec<?, ?> v;

	private int[] indices;

	DVVecN(DVec<?, ?> v, int[] indices) {
		this.v = v;
		this.indices = indices;
	}

	@Override
	public int dim() {
		return indices.length;
	}

	@Override
	public double get(int i) {
		return v.get(indices[i]);
	}

	@Override
	public void set(int i, double x) {
		v.set(indices[i], x);
	}

}
