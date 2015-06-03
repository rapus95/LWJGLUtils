package xor.vecmat.vec.l;



class LRVec1 extends LVec1 {

	public long x;

	LRVec1(long x) {
		this.x = x;
	}

	@Override
	public long x() {
		return x;
	}

	@Override
	public void x(long x) {
		this.x = x;
	}

}
