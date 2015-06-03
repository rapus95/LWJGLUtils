package xor.vecmat.vec.i;

class IVVec2 extends IVec2 {

	private IVec<?, ?> v;

	private int i1;

	private int i2;

	IVVec2(IVec<?, ?> v, int[] indices) {
		this.v = v;
		i1 = indices[0];
		i2 = indices[1];
	}
	
	IVVec2(IVec<?, ?> v, int i1, int i2) {
		this.v = v;
		this.i1 = i1;
		this.i2 = i2;
		int dim = v.dim();
		if (i1 < 0 || i2 < 0 || i1 >= dim || i2 >= dim) {
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
	public void x(int x) {
		v.set(i1, x);
	}

	@Override
	public void y(int y) {
		v.set(i2, y);
	}

}
