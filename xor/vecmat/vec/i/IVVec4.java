package xor.vecmat.vec.i;

class IVVec4 extends IVec4 {

	private IVec<?, ?> v;

	private int i1;

	private int i2;

	private int i3;

	private int i4;

	IVVec4(IVec<?, ?> v, int[] indices) {
		this.v = v;
		i1 = indices[0];
		i2 = indices[1];
		i3 = indices[2];
		i4 = indices[3];
	}

	IVVec4(IVec<?, ?> v, int i1, int i2, int i3, int i4) {
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
	public int x() {
		return v.get(i1);
	}

	@Override
	public int y() {
		return v.get(i2);
	}

	@Override
	public int z() {
		return v.get(i3);
	}

	@Override
	public int w() {
		return v.get(i4);
	}

	@Override
	public void x(int x) {
		v.set(i1, x);
	}

	@Override
	public void y(int y) {
		v.set(i2, y);
	}

	@Override
	public void z(int z) {
		v.set(i3, z);
	}

	@Override
	public void w(int w) {
		v.set(i4, w);
	}

}
