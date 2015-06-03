package xor.vecmat.vec.b;



class BRVec2 extends BVec2 {

	public boolean x;
	public boolean y;

	BRVec2(boolean x, boolean y) {
		this.x = x;
		this.y = y;
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
	public void x(boolean x) {
		this.x = x;
	}

	@Override
	public void y(boolean y) {
		this.y = y;
	}

}
