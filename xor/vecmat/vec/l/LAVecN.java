package xor.vecmat.vec.l;



class LAVecN extends LVecN {

	private long[] array;

	private int[] indices;

	LAVecN(long[] array, int[] indices) {
		this.array = array;
		this.indices = indices;
	}

	@Override
	public int dim() {
		return indices.length;
	}

	@Override
	public long get(int i) {
		return array[indices[i]];
	}

	@Override
	public void set(int i, long x) {
		array[indices[i]] = x;
	}

}
