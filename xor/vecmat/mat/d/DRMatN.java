package xor.vecmat.mat.d;

class DRMatN extends DMatN {

	public final int size;

	public final double[] mat;

	DRMatN(int size) {
		this.size = size;
		mat = new double[size];
	}

	DRMatN(int size, double[] mat) {
		this.size = size;
		this.mat = mat;
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public double get(int m, int n) {
		checkBounds(m, n);
		return mat[n * size + m];
	}

	@Override
	public void set(int m, int n, double v) {
		checkBounds(m, n);
		mat[n * size + m] = v;
	}

	@Override
	public DMatN clone() {
		double[] n = new double[size * size];
		System.arraycopy(mat, 0, n, 0, n.length);
		return new DRMatN(size, n);
	}

	private void checkBounds(int m, int n) {
		if (m < 0 || n < 0 || m >= size || n >= size)
			throw new IndexOutOfBoundsException();
	}

}
