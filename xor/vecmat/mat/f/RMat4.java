package xor.vecmat.mat.f;


class RMat4 extends Mat4 {

	public final float[] mat;

	RMat4() {
		mat = new float[16];
	}

	RMat4(float[] mat) {
		this.mat = mat;
	}

	@Override
	public float get(int m, int n) {
		checkBounds(m, n);
		return mat[n * 4 + m];
	}

	@Override
	public void set(int m, int n, float v) {
		checkBounds(m, n);
		mat[n * 4 + m] = v;
	}

	@Override
	public Mat4 clone() {
		float[] n = new float[16];
		System.arraycopy(mat, 0, n, 0, 16);
		return new RMat4(n);
	}
	
	private void checkBounds(int m, int n) {
		if (m < 0 || n < 0 || m > 3 || n > 3)
			throw new IndexOutOfBoundsException();
	}

}
