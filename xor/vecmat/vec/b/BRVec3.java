package xor.vecmat.vec.b;



class BRVec3 extends BVec3 {

	public boolean x;
	public boolean y;
	public boolean z;

	BRVec3(boolean x, boolean y, boolean z) {
		this.x = x;
		this.y = y;
		this.z = z;
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

}
