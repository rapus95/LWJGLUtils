package xor.vecmat.vec.f;



class AVec2 extends Vec2 {

	private float[] array;

	private int i1;

	private int i2;

	AVec2(float[] array, int[] indices) {
		this.array = array;
		i1 = indices[0];
		i2 = indices[1];
	}

	AVec2(float[] array, int i1, int i2) {
		this.array = array;
		this.i1 = i1;
		this.i2 = i2;
		if (i1 < 0 || i2 < 0 || i1 >= array.length || i2 >= array.length) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public float x() {
		return array[i1];
	}

	@Override
	public float y() {
		return array[i2];
	}

	@Override
	public void x(float x) {
		array[i1] = x;
	}

	@Override
	public void y(float y) {
		array[i2] = y;
	}

}
