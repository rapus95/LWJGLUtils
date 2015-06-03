package xor.vecmat.vec.b;

class BVVec1 extends BVec1 {

	private BVec<?> v;

	private int i1;

	BVVec1(BVec<?> v, int[] indices) {
		this.v = v;
		i1 = indices[0];
	}
	
	BVVec1(BVec<?> v, int i1) {
		this.v = v;
		this.i1 = i1;
		int dim = v.dim();
		if (i1 < 0 || i1 >= dim) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public boolean x() {
		return v.get(i1);
	}

	@Override
	public void x(boolean x) {
		v.set(i1, x);
	}

}
