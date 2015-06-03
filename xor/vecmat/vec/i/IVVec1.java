package xor.vecmat.vec.i;

class IVVec1 extends IVec1 {

	private IVec<?, ?> v;

	private int i1;

	IVVec1(IVec<?, ?> v, int[] indices) {
		this.v = v;
		i1 = indices[0];
	}
	
	IVVec1(IVec<?, ?> v, int i1) {
		this.v = v;
		this.i1 = i1;
		int dim = v.dim();
		if (i1 < 0 || i1 >= dim) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public int x() {
		return v.get(i1);
	}

	@Override
	public void x(int x) {
		v.set(i1, x);
	}

}
