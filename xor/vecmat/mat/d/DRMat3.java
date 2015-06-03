package xor.vecmat.mat.d;


class DRMat3 extends DMat3 {

	public final double[] mat;

	DRMat3() {
		mat = new double[9];
	}

	DRMat3(double[] mat) {
		this.mat = mat;
	}

	@Override
	public double get(int m, int n) {
		checkBounds(m, n);
		return mat[n * 3 + m];
	}

	@Override
	public void set(int m, int n, double v) {
		checkBounds(m, n);
		mat[n * 3 + m] = v;
	}

	@Override
	public DMat3 clone() {
		double[] n = new double[9];
		System.arraycopy(mat, 0, n, 0, 9);
		return new DRMat3(n);
	}
	
	private void checkBounds(int m, int n) {
		if (m < 0 || n < 0 || m > 2 || n > 2)
			throw new IndexOutOfBoundsException();
	}

}
