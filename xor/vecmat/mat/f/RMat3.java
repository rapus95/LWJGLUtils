package xor.vecmat.mat.f;


class RMat3 extends Mat3 {

	public final float[] mat;

	RMat3() {
		mat = new float[9];
	}

	RMat3(float[] mat) {
		this.mat = mat;
	}

	@Override
	public float get(int m, int n) {
		checkBounds(m, n);
		return mat[n * 3 + m];
	}

	@Override
	public void set(int m, int n, float v) {
		checkBounds(m, n);
		mat[n * 3 + m] = v;
	}

	@Override
	public Mat3 clone() {
		float[] n = new float[9];
		System.arraycopy(mat, 0, n, 0, 9);
		return new RMat3(n);
	}
	
	private void checkBounds(int m, int n) {
		if (m < 0 || n < 0 || m > 2 || n > 2)
			throw new IndexOutOfBoundsException();
	}

}
