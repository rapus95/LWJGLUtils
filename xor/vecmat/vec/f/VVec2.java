package xor.vecmat.vec.f;

class VVec2 extends Vec2 {

	private Vec<?, ?> v;

	private int i1;

	private int i2;

	VVec2(Vec<?, ?> v, int[] indices) {
		this.v = v;
		i1 = indices[0];
		i2 = indices[1];
	}
	
	VVec2(Vec<?, ?> v, int i1, int i2) {
		this.v = v;
		this.i1 = i1;
		this.i2 = i2;
		int dim = v.dim();
		if (i1 < 0 || i2 < 0 || i1 >= dim || i2 >= dim) {
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
	public void x(float x) {
		v.set(i1, x);
	}

	@Override
	public void y(float y) {
		v.set(i2, y);
	}

}
