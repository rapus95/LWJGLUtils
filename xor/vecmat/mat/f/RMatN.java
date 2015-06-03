package xor.vecmat.mat.f;

class RMatN extends MatN {

	public final int size;

	public final float[] mat;

	RMatN(int size) {
		this.size = size;
		mat = new float[size];
	}

	RMatN(int size, float[] mat) {
		this.size = size;
		this.mat = mat;
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public float get(int m, int n) {
		checkBounds(m, n);
		return mat[n * size + m];
	}

	@Override
	public void set(int m, int n, float v) {
		checkBounds(m, n);
		mat[n * size + m] = v;
	}

	@Override
	public MatN clone() {
		float[] n = new float[size * size];
		System.arraycopy(mat, 0, n, 0, n.length);
		return new RMatN(size, n);
	}

	private void checkBounds(int m, int n) {
		if (m < 0 || n < 0 || m >= size || n >= size)
			throw new IndexOutOfBoundsException();
	}

}
