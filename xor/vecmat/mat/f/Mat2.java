package xor.vecmat.mat.f;

import xor.vecmat.vec.f.Vec;
import xor.vecmat.vec.f.Vec2;

public abstract class Mat2 extends Mat<Mat2, Mat2, Vec2> {

	@Override
	public int n() {
		return 2;
	}

	@Override
	public int m() {
		return 2;
	}

	@Override
	protected Vec2 getRow(int m) {
		return new RowVec2(m);
	}

	@Override
	protected Vec2 getColumn(int n) {
		return new ColumnVec2(n);
	}

	@Override
	public Mat2 mul(Mat2 v) {
		final float t00 = get(0, 0);
		final float t10 = get(1, 0);
		final float t01 = get(0, 1);
		final float t11 = get(1, 1);
		final float v00 = v.get(0, 0);
		final float v10 = v.get(1, 0);
		final float v01 = v.get(0, 1);
		final float v11 = v.get(1, 1);
		final float[] mat = new float[4];
		mat[0] = t00 * v00 + t01 * v10;
		mat[1] = t10 * v00 + t11 * v10;
		mat[2] = t00 * v01 + t01 * v11;
		mat[3] = t10 * v01 + t11 * v11;
		return new RMat2(mat);
	}

	@Override
	public Vec2 mul(Vec2 v) {
		final float vx = v.x();
		final float vy = v.y();
		final float x = get(0, 0) * vx + get(1, 0) * vy;
		final float y = get(0, 1) * vx + get(1, 1) * vy;
		return Vec.Vec2(x, y);
	}

	@Override
	public Mat2 pow(int y) {
		if(y==0)
			return Mat2();
		Mat2 m = this;
		for(int i=1; i<y; i++){
			m = m.mul(this);
		}
		return m;
	}

	@Override
	public Mat2 transpose() {
		return new TransposeMat2();
	}

	@Override
	public Mat2 invert() {
		final float t00 = get(0, 0);
		final float t10 = get(1, 0);
		final float t01 = get(0, 1);
		final float t11 = get(1, 1);
		final float det = t00 * t11 - t01 * t10;
		final float invert = 1 / det;
		final float[] mat = new float[4];
		mat[0] = invert * t11;
		mat[1] = -invert * t10;
		mat[2] = -invert * t01;
		mat[3] = invert * t00;
		return new RMat2(mat);
	}

	@Override
	public double det() {
		return get(0, 0) * get(1, 1) - get(0, 1) * get(1, 0);
	}

	@Override
	public Mat2 adjunkte() {
		final float[] mat = new float[4];
		mat[0] = get(1, 1);
		mat[1] = -get(0, 1);
		mat[2] = -get(1, 0);
		mat[3] = get(0, 0);
		return new RMat2(mat);
	}

	@Override
	public Mat2 clone() {
		float[] mat = new float[4];
		mat[0] = get(0, 0);
		mat[1] = get(1, 0);
		mat[2] = get(0, 1);
		mat[3] = get(1, 1);
		return new RMat2(mat);
	}

	@Override
	protected Mat2 _new() {
		return new RMat2();
	}

	private class RowVec2 extends Vec2 {

		private final int m;

		RowVec2(int m) {
			this.m = m;
		}

		@Override
		public float x() {
			return Mat2.this.get(m, 0);
		}

		@Override
		public void x(float x) {
			Mat2.this.set(m, 0, x);
		}

		@Override
		public float y() {
			return Mat2.this.get(m, 1);
		}

		@Override
		public void y(float y) {
			Mat2.this.set(m, 1, y);
		}

		@Override
		public float get(int i) {
			return Mat2.this.get(m, i);
		}

		@Override
		public void set(int i, float v) {
			Mat2.this.set(m, i, v);
		}

	}

	private class ColumnVec2 extends Vec2 {

		private final int n;

		ColumnVec2(int n) {
			this.n = n;
		}

		@Override
		public float x() {
			return Mat2.this.get(0, n);
		}

		@Override
		public void x(float x) {
			Mat2.this.set(0, n, x);
		}

		@Override
		public float y() {
			return Mat2.this.get(1, n);
		}

		@Override
		public void y(float y) {
			Mat2.this.set(1, n, y);
		}

		@Override
		public float get(int i) {
			return Mat2.this.get(i, n);
		}

		@Override
		public void set(int i, float v) {
			Mat2.this.set(i, n, v);
		}

	}

	private class TransposeMat2 extends Mat2 {

		@Override
		public float get(int m, int n) {
			return Mat2.this.get(n, m);
		}

		@Override
		public void set(int m, int n, float v) {
			Mat2.this.set(n, m, v);
		}

		@Override
		public Mat2 transpose() {
			return Mat2.this;
		}

	}

}
