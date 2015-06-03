package xor.vecmat.vec.f;

class VVec4 extends Vec4 {

	private Vec<?, ?> v;

	private int i1;

	private int i2;

	private int i3;

	private int i4;

	VVec4(Vec<?, ?> v, int[] indices) {
		this.v = v;
		i1 = indices[0];
		i2 = indices[1];
		i3 = indices[2];
		i4 = indices[3];
	}

	VVec4(Vec<?, ?> v, int i1, int i2, int i3, int i4) {
		this.v = v;
		this.i1 = i1;
		this.i2 = i2;
		this.i3 = i3;
		this.i4 = i4;
		int dim = v.dim();
		if (i1 < 0 || i2 < 0 || i3 < 0 || i4 < 0 || i1 >= dim || i2 >= dim || i3 >= dim || i4 >= dim) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public float x() {
		return v.get(i1);
	}

	@Override
	public float y() {
		return v.get(i2);
	}

	@Override
	public float z() {
		return v.get(i3);
	}

	@Override
	public float w() {
		return v.get(i4);
	}

	@Override
	public void x(float x) {
		v.set(i1, x);
	}

	@Override
	public void y(float y) {
		v.set(i2, y);
	}

	@Override
	public void z(float z) {
		v.set(i3, z);
	}

	@Override
	public void w(float w) {
		v.set(i4, w);
	}

}
