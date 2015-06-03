package xor.vecmat.vec.l;



class LRVec3 extends LVec3 {

	public long x;
	public long y;
	public long z;

	LRVec3(long x, long y, long z) {
		this.x = x;
		this.y = y;
		this.z = z;
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
	public long z() {
		return z;
	}

	@Override
	public void x(long x) {
		this.x = x;
	}

	@Override
	public void y(long y) {
		this.y = y;
	}

	@Override
	public void z(long z) {
		this.z = z;
	}

}
