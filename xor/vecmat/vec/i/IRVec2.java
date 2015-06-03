package xor.vecmat.vec.i;



class IRVec2 extends IVec2 {

	public int x;
	public int y;

	IRVec2(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int x() {
		return x;
	}

	@Override
	public int y() {
		return y;
	}

	@Override
	public void x(int x) {
		this.x = x;
	}

	@Override
	public void y(int y) {
		this.y = y;
	}

}
