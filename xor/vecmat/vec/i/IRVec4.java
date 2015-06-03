package xor.vecmat.vec.i;



class IRVec4 extends IVec4 {

	public int x;
	public int y;
	public int z;
	public int w;

	IRVec4(int x, int y, int z, int w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
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
	public int w() {
		return w;
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

	@Override
	public void w(int w) {
		this.w = w;
	}

}
