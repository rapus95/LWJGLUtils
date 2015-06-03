package xor.vecmat.vec.f;

class VVec1 extends Vec1 {

	private Vec<?, ?> v;

	private int i1;

	VVec1(Vec<?, ?> v, int[] indices) {
		this.v = v;
		i1 = indices[0];
	}
	
	VVec1(Vec<?, ?> v, int i1) {
		this.v = v;
		this.i1 = i1;
		int dim = v.dim();
		if (i1 < 0 || i1 >= dim) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public float x() {
		return v.get(i1);
	}

	@Override
	public void x(float x) {
		v.set(i1, x);
	}

}
