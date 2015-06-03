package xor.vecmat.vec.l;

class LVVec3 extends LVec3 {

	private LVec<?, ?> v;

	private int i1;

	private int i2;

	private int i3;

	LVVec3(LVec<?, ?> v, int[] indices) {
		this.v = v;
		i1 = indices[0];
		i2 = indices[1];
		i3 = indices[2];
	}

	LVVec3(LVec<?, ?> v, int i1, int i2, int i3) {
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
	public long x() {
		return v.get(i1);
	}

	@Override
	public long y() {
		return v.get(i2);
	}

	@Override
	public long z() {
		return v.get(i3);
	}

	@Override
	public void x(long x) {
		v.set(i1, x);
	}

	@Override
	public void y(long y) {
		v.set(i2, y);
	}

	@Override
	public void z(long z) {
		v.set(i3, z);
	}

}
