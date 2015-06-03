package xor.vecmat.vec.b;



class BAVec2 extends BVec2 {

	private boolean[] array;

	private int i1;

	private int i2;

	BAVec2(boolean[] array, int[] indices) {
		this.array = array;
		i1 = indices[0];
		i2 = indices[1];
	}

	BAVec2(boolean[] array, int i1, int i2) {
		this.array = array;
		this.i1 = i1;
		this.i2 = i2;
		if (i1 < 0 || i2 < 0 || i1 >= array.length || i2 >= array.length) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public boolean x() {
		return array[i1];
	}

	@Override
	public boolean y() {
		return array[i2];
	}

	@Override
	public void x(boolean x) {
		array[i1] = x;
	}

	@Override
	public void y(boolean y) {
		array[i2] = y;
	}

}
