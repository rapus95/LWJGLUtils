package xor.vecmat.vec.b;



class BRVec1 extends BVec1 {

	public boolean x;

	BRVec1(boolean x) {
		this.x = x;
	}

	@Override
	public boolean x() {
		return x;
	}

	@Override
	public void x(boolean x) {
		this.x = x;
	}

}
