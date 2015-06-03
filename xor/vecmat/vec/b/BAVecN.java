package xor.vecmat.vec.b;



class BAVecN extends BVecN {

	private boolean[] array;

	private int[] indices;

	BAVecN(boolean[] array, int[] indices) {
		this.array = array;
		this.indices = indices;
	}

	@Override
	public int dim() {
		return indices.length;
	}

	@Override
	public boolean get(int i) {
		return array[indices[i]];
	}

	@Override
	public void set(int i, boolean x) {
		array[indices[i]] = x;
	}

}
