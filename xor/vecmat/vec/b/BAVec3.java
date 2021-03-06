package xor.vecmat.vec.b;



class BAVec3 extends BVec3 {

	private boolean[] array;

	private int i1;

	private int i2;

	private int i3;

	BAVec3(boolean[] array, int[] indices) {
		this.array = array;
		i1 = indices[0];
		i2 = indices[1];
		i3 = indices[2];
	}
	
	BAVec3(boolean[] array, int i1, int i2, int i3) {
		this.array = array;
		this.i1 = i1;
		this.i2 = i2;
		this.i3 = i3;
		if (i1 < 0 || i2 < 0 || i3 < 0 || i1 >= array.length || i2 >= array.length || i3 >= array.length) {
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
	public boolean z() {
		return array[i3];
	}

	@Override
	public void x(boolean x) {
		array[i1] = x;
	}

	@Override
	public void y(boolean y) {
		array[i2] = y;
	}

	@Override
	public void z(boolean z) {
		array[i3] = z;
	}

}
