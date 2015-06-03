package xor.vecmat.vec.b;

class BVVec3 extends BVec3 {

	private BVec<?> v;

	private int i1;

	private int i2;

	private int i3;

	BVVec3(BVec<?> v, int[] indices) {
		this.v = v;
		i1 = indices[0];
		i2 = indices[1];
		i3 = indices[2];
	}

	BVVec3(BVec<?> v, int i1, int i2, int i3) {
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
	public boolean x() {
		return v.get(i1);
	}

	@Override
	public boolean y() {
		return v.get(i2);
	}

	@Override
	public boolean z() {
		return v.get(i3);
	}

	@Override
	public void x(boolean x) {
		v.set(i1, x);
	}

	@Override
	public void y(boolean y) {
		v.set(i2, y);
	}

	@Override
	public void z(boolean z) {
		v.set(i3, z);
	}

}
