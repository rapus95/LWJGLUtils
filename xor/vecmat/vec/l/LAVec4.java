package xor.vecmat.vec.l;



class LAVec4 extends LVec4 {

	private long[] array;

	private int i1;

	private int i2;

	private int i3;

	private int i4;

	LAVec4(long[] array, int[] indices) {
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

	LAVec4(long[] array, int i1, int i2, int i3, int i4) {
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
	public long x() {
		return array[i1];
	}

	@Override
	public long y() {
		return array[i2];
	}

	@Override
	public long z() {
		return array[i3];
	}

	@Override
	public long w() {
		return array[i4];
	}

	@Override
	public void x(long x) {
		array[i1] = x;
	}

	@Override
	public void y(long y) {
		array[i2] = y;
	}

	@Override
	public void z(long z) {
		array[i3] = z;
	}

	@Override
	public void w(long w) {
		array[i4] = w;
	}

}
