package xor.vecmat.vec.d;

class DVVec1 extends DVec1 {

	private DVec<?, ?> v;

	private int i1;

	DVVec1(DVec<?, ?> v, int[] indices) {
		this.v = v;
		i1 = indices[0];
	}
	
	DVVec1(DVec<?, ?> v, int i1) {
		this.v = v;
		this.i1 = i1;
		int dim = v.dim();
		if (i1 < 0 || i1 >= dim) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public double x() {
		return v.get(i1);
	}

	@Override
	public void x(double x) {
		v.set(i1, x);
	}

}
