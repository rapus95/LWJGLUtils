package xor.vecmat.mat.d;

class DRMat1 extends DMat1 {

	public double m11;

	DRMat1() {
		m11 = 1;
	}

	DRMat1(double m11) {
		this.m11 = m11;
	}

	@Override
	public double get(int m, int n) {
		if (m != 0 || n != 0)
			throw new IndexOutOfBoundsException();
		return m11;
	}

	@Override
	public void set(int m, int n, double v) {
		if (m != 0 || n != 0)
			throw new IndexOutOfBoundsException();
		m11 = v;
	}

}
