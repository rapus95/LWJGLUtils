package xor.vecmat.mat.d;


class DRMat4 extends DMat4 {

	public final double[] mat;

	DRMat4() {
		mat = new double[16];
	}

	DRMat4(double[] mat) {
		this.mat = mat;
	}

	@Override
	public double get(int m, int n) {
		checkBounds(m, n);
		return mat[n * 4 + m];
	}

	@Override
	public void set(int m, int n, double v) {
		checkBounds(m, n);
		mat[n * 4 + m] = v;
	}

	@Override
	public DMat4 clone() {
		double[] n = new double[16];
		System.arraycopy(mat, 0, n, 0, 16);
		return new DRMat4(n);
	}
	
	private void checkBounds(int m, int n) {
		if (m < 0 || n < 0 || m > 3 || n > 3)
			throw new IndexOutOfBoundsException();
	}

}
