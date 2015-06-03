package xor.vecmat.vec.i;



class IAVec2 extends IVec2 {

	private int[] array;

	private int i1;

	private int i2;

	IAVec2(int[] array, int[] indices) {
		this.array = array;
		i1 = indices[0];
		i2 = indices[1];
	}

	IAVec2(int[] array, int i1, int i2) {
		this.array = array;
		this.i1 = i1;
		this.i2 = i2;
		if (i1 < 0 || i2 < 0 || i1 >= array.length || i2 >= array.length) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public int x() {
		return array[i1];
	}

	@Override
	public int y() {
		return array[i2];
	}

	@Override
	public void x(int x) {
		array[i1] = x;
	}

	@Override
	public void y(int y) {
		array[i2] = y;
	}

}
