package xor.vecmat.vec.d;

class DRVec1 extends DVec1 {

	public double x;

	DRVec1(double x) {
		this.x = x;
	}

	@Override
	public double x() {
		return x;
	}

	@Override
	public void x(double x) {
		this.x = x;
	}

}
