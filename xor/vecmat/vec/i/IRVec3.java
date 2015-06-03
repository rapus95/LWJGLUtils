package xor.vecmat.vec.i;



class IRVec3 extends IVec3 {

	public int x;
	public int y;
	public int z;

	IRVec3(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
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
	public int z() {
		return z;
	}

	@Override
	public void x(int x) {
		this.x = x;
	}

	@Override
	public void y(int y) {
		this.y = y;
	}

	@Override
	public void z(int z) {
		this.z = z;
	}

}
