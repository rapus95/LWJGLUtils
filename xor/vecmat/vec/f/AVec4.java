package xor.vecmat.vec.f;



class AVec4 extends Vec4 {

	private float[] array;

	private int i1;

	private int i2;

	private int i3;

	private int i4;

	AVec4(float[] array, int[] indices) {
		if (indices.length != 4)
			throw new IllegalArgumentException();
		this.array = array;
		i1 = indices[0];
		i2 = indices[1];
		i3 = indices[2];
		i4 = indices[3];
		if (i1 < 0 || i2 < 0 || i3 < 0 || i4 < 0 || i1 >= array.length || i2 >= array.length || i3 >= array.length || i4 >= array.length) {
			throw new IllegalArgumentException();
		}
	}

	AVec4(float[] array, int i1, int i2, int i3, int i4) {
		this.array = array;
		this.i1 = i1;
		this.i2 = i2;
		this.i3 = i3;
		this.i4 = i4;
		if (i1 < 0 || i2 < 0 || i3 < 0 || i4 < 0 || i1 >= array.length || i2 >= array.length || i3 >= array.length || i4 >= array.length) {
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
	public float z() {
		return array[i3];
	}

	@Override
	public float w() {
		return array[i4];
	}

	@Override
	public void x(float x) {
		array[i1] = x;
	}

	@Override
	public void y(float y) {
		array[i2] = y;
	}

	@Override
	public void z(float z) {
		array[i3] = z;
	}

	@Override
	public void w(float w) {
		array[i4] = w;
	}

}
