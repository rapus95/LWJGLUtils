package xor.vecmat.vec.d;

class DAVec2 extends DVec2 {

	private double[] array;

	private int i1;

	private int i2;

	DAVec2(double[] array, int[] indices) {
		this.array = array;
		i1 = indices[0];
		i2 = indices[1];
	}

	DAVec2(double[] array, int i1, int i2) {
		this.array = array;
		this.i1 = i1;
		this.i2 = i2;
		if (i1 < 0 || i2 < 0 || i1 >= array.length || i2 >= array.length) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public double x() {
		return array[i1];
	}

	@Override
	public double y() {
		return array[i2];
	}

	@Override
	public void x(double x) {
		array[i1] = x;
	}

	@Override
	public void y(double y) {
		array[i2] = y;
	}

}
