package xor.vecmat.vec.l;



class LRVec4 extends LVec4 {

	public long x;
	public long y;
	public long z;
	public long w;

	LRVec4(long x, long y, long z, long w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
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
	public long w() {
		return w;
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

	@Override
	public void w(long w) {
		this.w = w;
	}

}
