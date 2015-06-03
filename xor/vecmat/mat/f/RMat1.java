package xor.vecmat.mat.f;

class RMat1 extends Mat1 {

	public float m11;

	RMat1() {
		m11 = 1;
	}

	RMat1(float m11) {
		this.m11 = m11;
	}

	@Override
	public float get(int m, int n) {
		if (m != 0 || n != 0)
			throw new IndexOutOfBoundsException();
		return m11;
	}

	@Override
	public void set(int m, int n, float v) {
		if (m != 0 || n != 0)
			throw new IndexOutOfBoundsException();
		m11 = v;
	}

}
