package xor.vecmat.vec.d;

class DRVec3 extends DVec3 {

	public double x;
	public double y;
	public double z;

	DRVec3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public double x() {
		return x;
	}

	@Override
	public double y() {
		return y;
	}

	@Override
	public double z() {
		return z;
	}

	@Override
	public void x(double x) {
		this.x = x;
	}

	@Override
	public void y(double y) {
		this.y = y;
	}

	@Override
	public void z(double z) {
		this.z = z;
	}

}
