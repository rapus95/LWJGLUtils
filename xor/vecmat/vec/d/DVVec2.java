package xor.vecmat.vec.d;

class DVVec2 extends DVec2 {

	private DVec<?, ?> v;

	private int i1;

	private int i2;

	DVVec2(DVec<?, ?> v, int[] indices) {
		this.v = v;
		i1 = indices[0];
		i2 = indices[1];
	}
	
	DVVec2(DVec<?, ?> v, int i1, int i2) {
		this.v = v;
		this.i1 = i1;
		this.i2 = i2;
		int dim = v.dim();
		if (i1 < 0 || i2 < 0 || i1 >= dim || i2 >= dim) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public double x() {
		return v.get(i1);
	}

	@Override
	public double y() {
		return v.get(i2);
	}

	@Override
	public void x(double x) {
		v.set(i1, x);
	}

	@Override
	public void y(double y) {
		v.set(i2, y);
	}

}
