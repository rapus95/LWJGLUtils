package xor.vecmat.vec.f;



class RVec1 extends Vec1 {

	public float x;

	RVec1(float x) {
		this.x = x;
	}

	@Override
	public float x() {
		return x;
	}

	@Override
	public void x(float x) {
		this.x = x;
	}

}
