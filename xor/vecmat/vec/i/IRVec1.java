package xor.vecmat.vec.i;

class IRVec1 extends IVec1 {

	public int x;

	IRVec1(int x) {
		this.x = x;
	}

	@Override
	public int x() {
		return x;
	}

	@Override
	public void x(int x) {
		this.x = x;
	}

}
