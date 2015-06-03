package xor.vecmat.mat.d;


class DRMat2 extends DMat2 {

	public final double[] mat;

	DRMat2() {
		mat = new double[4];
	}

	DRMat2(double[] mat) {
		this.mat = mat;
	}

	@Override
	public double get(int m, int n) {
		checkBounds(m, n);
		return mat[n * 2 + m];
	}

	@Override
	public void set(int m, int n, double v) {
		checkBounds(m, n);
		mat[n * 2 + m] = v;
	}
	
	@Override
	public DMat2 clone() {
		double[] n = new double[4];
		System.arraycopy(mat, 0, n, 0, 4);
		return new DRMat2(n);
	}

	private void checkBounds(int m, int n) {
		if (m < 0 || n < 0 || m > 1 || n > 1)
			throw new IndexOutOfBoundsException();
	}

}
