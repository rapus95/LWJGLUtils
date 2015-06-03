package xor.vecmat.mat.d;

class DRMatMN extends DMatMN {

	public final int m;

	public final int n;

	public final double[] mat;

	DRMatMN(int m, int n) {
		this.m = m;
		this.n = n;
		mat = new double[m * n];
	}

	DRMatMN(int m, int n, double[] mat) {
		this.m = m;
		this.n = n;
		this.mat = mat;
	}

	@Override
	public int n() {
		return n;
	}

	@Override
	public int m() {
		return m;
	}

	@Override
	public double get(int m, int n) {
		checkBounds(m, n);
		return mat[n * this.m + m];
	}

	@Override
	public void set(int m, int n, double v) {
		checkBounds(m, n);
		mat[n * this.m + m] = v;
	}

	@Override
	public DMatMN clone() {
		double[] ne = new double[n * m];
		System.arraycopy(mat, 0, ne, 0, ne.length);
		return new DRMatMN(m, n, ne);
	}

	private void checkBounds(int m, int n) {
		if (m < 0 || n < 0 || m >= this.m || n >= this.n)
			throw new IndexOutOfBoundsException();
	}

}
