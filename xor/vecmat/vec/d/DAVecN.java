package xor.vecmat.vec.d;

class DAVecN extends DVecN {

	private double[] array;

	private int[] indices;

	DAVecN(double[] array, int[] indices) {
		this.array = array;
		this.indices = indices;
	}

	@Override
	public int dim() {
		return indices.length;
	}

	@Override
	public double get(int i) {
		return array[indices[i]];
	}

	@Override
	public void set(int i, double x) {
		array[indices[i]] = x;
	}

}
