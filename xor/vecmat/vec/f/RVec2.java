package xor.vecmat.vec.f;



class RVec2 extends Vec2 {

	public float x;
	public float y;

	RVec2(float x, float y) {
		this.x = x;
		this.y = y;
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
	public void x(float x) {
		this.x = x;
	}

	@Override
	public void y(float y) {
		this.y = y;
	}

}
