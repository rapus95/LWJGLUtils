package xor.vecmat.mat.f;

class RMatMN extends MatMN {

	public final int m;

	public final int n;

	public final float[] mat;

	RMatMN(int m, int n) {
		this.m = m;
		this.n = n;
		mat = new float[m * n];
	}

	RMatMN(int m, int n, float[] mat) {
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
	public float get(int m, int n) {
		checkBounds(m, n);
		return mat[n * this.m + m];
	}

	@Override
	public void set(int m, int n, float v) {
		checkBounds(m, n);
		mat[n * this.m + m] = v;
	}

	@Override
	public MatMN clone() {
		float[] ne = new float[n * m];
		System.arraycopy(mat, 0, ne, 0, ne.length);
		return new RMatMN(m, n, ne);
	}

	private void checkBounds(int m, int n) {
		if (m < 0 || n < 0 || m >= this.m || n >= this.n)
			throw new IndexOutOfBoundsException();
	}

}
