package xor.vecmat.vec.b;



class BRVec4 extends BVec4 {

	public boolean x;
	public boolean y;
	public boolean z;
	public boolean w;

	BRVec4(boolean x, boolean y, boolean z, boolean w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	@Override
	public boolean x() {
		return x;
	}

	@Override
	public boolean y() {
		return y;
	}

	@Override
	public boolean z() {
		return z;
	}

	@Override
	public boolean w() {
		return w;
	}

	@Override
	public void x(boolean x) {
		this.x = x;
	}

	@Override
	public void y(boolean y) {
		this.y = y;
	}

	@Override
	public void z(boolean z) {
		this.z = z;
	}

	@Override
	public void w(boolean w) {
		this.w = w;
	}

}
