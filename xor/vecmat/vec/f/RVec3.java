package xor.vecmat.vec.f;



class RVec3 extends Vec3 {

	public float x;
	public float y;
	public float z;

	RVec3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
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

}
