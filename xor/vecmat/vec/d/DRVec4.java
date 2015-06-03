package xor.vecmat.vec.d;

class DRVec4 extends DVec4 {

	public double x;
	public double y;
	public double z;
	public double w;

	DRVec4(double x, double y, double z, double w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
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
	public double w() {
		return w;
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

	@Override
	public void w(double w) {
		this.w = w;
	}

}
