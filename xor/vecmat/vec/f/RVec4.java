package xor.vecmat.vec.f;



class RVec4 extends Vec4 {

	public float x;
	public float y;
	public float z;
	public float w;

	RVec4(float x, float y, float z, float w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}

	@Override
	public float x() {
		return x;
	}

	@Override
	public float y() {
		return y;
	}

	@Override
	public float z() {
		return z;
	}

	@Override
	public float w() {
		return w;
	}

	@Override
	public void x(float x) {
		this.x = x;
	}

	@Override
	public void y(float y) {
		this.y = y;
	}

	@Override
	public void z(float z) {
		this.z = z;
	}

	@Override
	public void w(float w) {
		this.w = w;
	}

}
