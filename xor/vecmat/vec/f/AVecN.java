package xor.vecmat.vec.f;



class AVecN extends VecN {

	private float[] array;

	private int[] indices;

	AVecN(float[] array, int[] indices) {
		this.array = array;
		this.indices = indices;
	}

	@Override
	public int dim() {
		return indices.length;
	}

	@Override
	public float get(int i) {
		return array[indices[i]];
	}

	@Override
	public void set(int i, float x) {
		array[indices[i]] = x;
	}

}
