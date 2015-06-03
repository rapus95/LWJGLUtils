package xor.vecmat.vec.l;



class LRVec2 extends LVec2 {

	public long x;
	public long y;

	LRVec2(long x, long y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public long x() {
		return x;
	}

	@Override
	public long y() {
		return y;
	}

	@Override
	public void x(long x) {
		this.x = x;
	}

	@Override
	public void y(long y) {
		this.y = y;
	}

}
