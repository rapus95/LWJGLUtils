package xor.vecmat.vec.i;

class IVVec3 extends IVec3 {

	private IVec<?, ?> v;

	private int i1;

	private int i2;

	private int i3;

	IVVec3(IVec<?, ?> v, int[] indices) {
		this.v = v;
		i1 = indices[0];
		i2 = indices[1];
		i3 = indices[2];
	}

	IVVec3(IVec<?, ?> v, int i1, int i2, int i3) {
		this.v = v;
		this.i1 = i1;
		this.i2 = i2;
		this.i3 = i3;
		int dim = v.dim();
		if (i1 < 0 || i2 < 0 || i3 < 0 || i1 >= dim || i2 >= dim || i3 >= dim) {
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

}
