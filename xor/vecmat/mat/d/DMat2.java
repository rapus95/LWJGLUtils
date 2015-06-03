package xor.vecmat.mat.d;

import xor.vecmat.vec.d.DVec;
import xor.vecmat.vec.d.DVec2;

public abstract class DMat2 extends DMat<DMat2, DMat2, DVec2> {

	@Override
	public int n() {
		return 2;
	}

	@Override
	public int m() {
		return 2;
	}

	@Override
	protected DVec2 getRow(int m) {
		return new RowDVec2(m);
	}

	@Override
	protected DVec2 getColumn(int n) {
		return new ColumnDVec2(n);
	}

	@Override
	public DMat2 mul(DMat2 v) {
		final double t00 = get(0, 0);
		final double t10 = get(1, 0);
		final double t01 = get(0, 1);
		final double t11 = get(1, 1);
		final double v00 = v.get(0, 0);
		final double v10 = v.get(1, 0);
		final double v01 = v.get(0, 1);
		final double v11 = v.get(1, 1);
		final double[] mat = new double[4];
		mat[0] = t00 * v00 + t01 * v10;
		mat[1] = t10 * v00 + t11 * v10;
		mat[2] = t00 * v01 + t01 * v11;
		mat[3] = t10 * v01 + t11 * v11;
		return new DRMat2(mat);
	}

	@Override
	public DVec2 mul(DVec2 v) {
		final double vx = v.x();
		final double vy = v.y();
		final double x = get(0, 0) * vx + get(1, 0) * vy;
		final double y = get(0, 1) * vx + get(1, 1) * vy;
		return DVec.DVec2(x, y);
	}

	@Override
	public DMat2 pow(int y) {
		if(y==0)
			return DMat2();
		DMat2 m = this;
		for(int i=1; i<y; i++){
			m = m.mul(this);
		}
		return m;
	}

	@Override
	public DMat2 transpose() {
		return new TransposeMat2();
	}

	@Override
	public DMat2 invert() {
		final double t00 = get(0, 0);
		final double t10 = get(1, 0);
		final double t01 = get(0, 1);
		final double t11 = get(1, 1);
		final double det = t00 * t11 - t01 * t10;
		final double invert = 1 / det;
		final double[] mat = new double[4];
		mat[0] = invert * t11;
		mat[1] = -invert * t10;
		mat[2] = -invert * t01;
		mat[3] = invert * t00;
		return new DRMat2(mat);
	}

	@Override
	public double det() {
		return get(0, 0) * get(1, 1) - get(0, 1) * get(1, 0);
	}

	@Override
	public DMat2 adjunkte() {
		final double[] mat = new double[4];
		mat[0] = get(1, 1);
		mat[1] = -get(0, 1);
		mat[2] = -get(1, 0);
		mat[3] = get(0, 0);
		return new DRMat2(mat);
	}

	@Override
	public DMat2 clone() {
		double[] mat = new double[4];
		mat[0] = get(0, 0);
		mat[1] = get(1, 0);
		mat[2] = get(0, 1);
		mat[3] = get(1, 1);
		return new DRMat2(mat);
	}

	@Override
	protected DMat2 _new() {
		return new DRMat2();
	}

	private class RowDVec2 extends DVec2 {

		private final int m;

		RowDVec2(int m) {
			this.m = m;
		}

		@Override
		public double x() {
			return DMat2.this.get(m, 0);
		}

		@Override
		public void x(double x) {
			DMat2.this.set(m, 0, x);
		}

		@Override
		public double y() {
			return DMat2.this.get(m, 1);
		}

		@Override
		public void y(double y) {
			DMat2.this.set(m, 1, y);
		}

		@Override
		public double get(int i) {
			return DMat2.this.get(m, i);
		}

		@Override
		public void set(int i, double v) {
			DMat2.this.set(m, i, v);
		}

	}

	private class ColumnDVec2 extends DVec2 {

		private final int n;

		ColumnDVec2(int n) {
			this.n = n;
		}

		@Override
		public double x() {
			return DMat2.this.get(0, n);
		}

		@Override
		public void x(double x) {
			DMat2.this.set(0, n, x);
		}

		@Override
		public double y() {
			return DMat2.this.get(1, n);
		}

		@Override
		public void y(double y) {
			DMat2.this.set(1, n, y);
		}

		@Override
		public double get(int i) {
			return DMat2.this.get(i, n);
		}

		@Override
		public void set(int i, double v) {
			DMat2.this.set(i, n, v);
		}

	}

	private class TransposeMat2 extends DMat2 {

		@Override
		public double get(int m, int n) {
			return DMat2.this.get(n, m);
		}

		@Override
		public void set(int m, int n, double v) {
			DMat2.this.set(n, m, v);
		}

		@Override
		public DMat2 transpose() {
			return DMat2.this;
		}

	}

}
