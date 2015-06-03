package xor.vecmat.vec.l;



class LAVec2 extends LVec2 {

	private long[] array;

	private int i1;

	private int i2;

	LAVec2(long[] array, int[] indices) {
		this.array = array;
		i1 = indices[0];
		i2 = indices[1];
	}

	LAVec2(long[] array, int i1, int i2) {
		this.array = array;
		this.i1 = i1;
		this.i2 = i2;
		if (i1 < 0 || i2 < 0 || i1 >= array.length || i2 >= array.length) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public long x() {
		return array[i1];
	}

	@Override
	public long y() {
		return array[i2];
	}

	@Override
	public void x(long x) {
		array[i1] = x;
	}

	@Override
	public void y(long y) {
		array[i2] = y;
	}

}
