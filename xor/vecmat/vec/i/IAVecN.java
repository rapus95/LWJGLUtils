package xor.vecmat.vec.i;



class IAVecN extends IVecN {

	private int[] array;

	private int[] indices;

	IAVecN(int[] array, int[] indices) {
		this.array = array;
		this.indices = indices;
	}

	@Override
	public int dim() {
		return indices.length;
	}

	@Override
	public int get(int i) {
		return array[indices[i]];
	}

	@Override
	public void set(int i, int x) {
		array[indices[i]] = x;
	}

}
