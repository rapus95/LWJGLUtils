package xor.vecmat.mat.f;


class RMat2 extends Mat2 {

	public final float[] mat;

	RMat2() {
		mat = new float[4];
	}

	RMat2(float[] mat) {
		this.mat = mat;
	}

	@Override
	public float get(int m, int n) {
		checkBounds(m, n);
		return mat[n * 2 + m];
	}

	@Override
	public void set(int m, int n, float v) {
		checkBounds(m, n);
		mat[n * 2 + m] = v;
	}
	
	@Override
	public Mat2 clone() {
		float[] n = new float[4];
		System.arraycopy(mat, 0, n, 0, 4);
		return new RMat2(n);
	}

	private void checkBounds(int m, int n) {
		if (m < 0 || n < 0 || m > 1 || n > 1)
			throw new IndexOutOfBoundsException();
	}

}
