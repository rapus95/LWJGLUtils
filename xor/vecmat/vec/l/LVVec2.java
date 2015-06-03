package xor.vecmat.vec.l;

class LVVec2 extends LVec2 {

	private LVec<?, ?> v;

	private int i1;

	private int i2;

	LVVec2(LVec<?, ?> v, int[] indices) {
		this.v = v;
		i1 = indices[0];
		i2 = indices[1];
	}
	
	LVVec2(LVec<?, ?> v, int i1, int i2) {
		this.v = v;
		this.i1 = i1;
		this.i2 = i2;
		int dim = v.dim();
		if (i1 < 0 || i2 < 0 || i1 >= dim || i2 >= dim) {
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
	public void x(long x) {
		v.set(i1, x);
	}

	@Override
	public void y(long y) {
		v.set(i2, y);
	}

}
