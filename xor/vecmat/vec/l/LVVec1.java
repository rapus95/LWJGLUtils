package xor.vecmat.vec.l;

class LVVec1 extends LVec1 {

	private LVec<?, ?> v;

	private int i1;

	LVVec1(LVec<?, ?> v, int[] indices) {
		this.v = v;
		i1 = indices[0];
	}
	
	LVVec1(LVec<?, ?> v, int i1) {
		this.v = v;
		this.i1 = i1;
		int dim = v.dim();
		if (i1 < 0 || i1 >= dim) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public long x() {
		return v.get(i1);
	}

	@Override
	public void x(long x) {
		v.set(i1, x);
	}

}
