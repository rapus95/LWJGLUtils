package xor.vecmat.mat.d;

import xor.vecmat.vec.d.DVec;
import xor.vecmat.vec.d.DVecN;

public abstract class DMatN extends DMat<DMatN, DMatN, DVecN> {

	public abstract int size();

	@Override
	public int n() {
		return size();
	}

	@Override
	public int m() {
		return size();
	}

	@Override
	protected DVecN getRow(int m) {
		return new RowDVecN(m);
	}

	@Override
	protected DVecN getColumn(int n) {
		return new ColumnDVecN(n);
	}

	@Override
	public DMatN mul(DMatN v) {
		final int size = size();
		if (size != v.size())
			throw new IllegalArgumentException();
		double[] mat = new double[size * size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < size; k++) {
					mat[j * size + i] += get(i, k) * v.get(k, j);
				}
			}
		}
		return new DRMatN(size, mat);
	}

	@Override
	public DVecN mul(DVecN v) {
		final int size = size();
		if (size != v.dim())
			throw new IllegalArgumentException();
		double[] vec = new double[size];
		for (int i = 0; i < size; i++) {
			double vv = 0;
			for (int j = 0; j < size; j++) {
				vv += get(j, i) * v.get(j);
			}
			vec[i] = vv;
		}
		return DVec.DVecX(vec);
	}

	@Override
	public DMatN pow(int y) {
		if (y == 0)
			return DMatX(size());
		DMatN m = this;
		for (int i = 1; i < y; i++) {
			m = m.mul(this);
		}
		return m;
	}

	@Override
	public DMatN transpose() {
		return new TransposeMatN();
	}

	@Override
	public DMatN invert() {
		final int size = size();
		final double det = (double) (1 / det());
		DMatN m = adjunkte();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				m.set(i, j, get(j, i) * det);
			}
		}
		return m;
	}

	@Override
	public double det() {
		final int size = size();
		double[] mat = new double[size];
		int k = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				mat[k++] = get(j, i);
			}
		}
		return det(mat, size);
	}

	private static double det(double[] mat, int s) {
		if (s == 4) {
			return det4(mat);
		}
		final int s1 = s - 1;
		final double[] nmat = new double[s1 * s1];
		double det = 0;
		for (int j1 = 0; j1 < s; j1++) {
			for (int i = 1; i < s; i++) {
				int j2 = 0;
				for (int j = 0; j < s; j++) {
					if (j == j1)
						continue;
					nmat[j2 * s1 + i - 1] = mat[i + j * s];
					j2++;
				}
			}
			det += (j1 % 2 == 0 ? 1 : -1) * mat[j1 * s] * det(nmat, s1);
		}
		return det;
	}

	private static double det4(double[] mat) {
		final double t00 = mat[0];
		final double t10 = mat[1];
		final double t20 = mat[2];
		final double t30 = mat[3];
		final double t01 = mat[4];
		final double t11 = mat[5];
		final double t21 = mat[6];
		final double t31 = mat[7];
		final double t02 = mat[8];
		final double t12 = mat[9];
		final double t22 = mat[10];
		final double t32 = mat[11];
		final double t03 = mat[12];
		final double t13 = mat[13];
		final double t23 = mat[14];
		final double t33 = mat[15];

		final double s0 = t00 * t11 - t10 * t01;
		final double s1 = t00 * t12 - t10 * t02;
		final double s2 = t00 * t13 - t10 * t03;
		final double s3 = t01 * t12 - t11 * t02;
		final double s4 = t01 * t13 - t11 * t03;
		final double s5 = t02 * t13 - t12 * t03;

		final double c5 = t22 * t33 - t32 * t23;
		final double c4 = t21 * t33 - t31 * t23;
		final double c3 = t21 * t32 - t31 * t22;
		final double c2 = t20 * t33 - t30 * t23;
		final double c1 = t20 * t32 - t30 * t22;
		final double c0 = t20 * t31 - t30 * t21;

		return s0 * c5 - s1 * c4 + s2 * c3 + s3 * c2 - s4 * c1 + s5 * c0;
	}

	@Override
	public DMatN adjunkte() {
		final int size = size();
		final int s1 = size - 1;

		double[] mat2 = new double[s1 * s1];

		double[] mat = new double[size * size];

		for (int j = 0; j < size; j++) {
			for (int i = 0; i < size; i++) {

				int i1 = 0;
				for (int ii = 0; ii < size; ii++) {
					if (ii == i)
						continue;
					int j1 = 0;
					for (int jj = 0; jj < size; jj++) {
						if (jj == j)
							continue;
						mat2[i1 + j1 * s1] = get(ii, jj);
						j1++;
					}
					i1++;
				}

				double det = det(mat2, s1);

				mat[i * size + j] = (i + j) % 2 == 0 ? det : -det;
			}
		}
		return new DRMatN(size, mat);
	}

	@Override
	public DMatN clone() {
		final int size = size();
		double[] mat = new double[size * size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				mat[i * size + j] = get(j, i);
			}
		}
		return new DRMatN(size, mat);
	}

	@Override
	protected DMatN _new() {
		return new DRMatN(size());
	}

	private class RowDVecN extends DVecN {

		private final int m;

		RowDVecN(int m) {
			this.m = m;
		}

		@Override
		public double get(int i) {
			return DMatN.this.get(m, i);
		}

		@Override
		public void set(int i, double v) {
			DMatN.this.set(m, i, v);
		}

		@Override
		public int dim() {
			return DMatN.this.size();
		}

	}

	private class ColumnDVecN extends DVecN {

		private final int n;

		ColumnDVecN(int n) {
			this.n = n;
		}

		@Override
		public double get(int i) {
			return DMatN.this.get(i, n);
		}

		@Override
		public void set(int i, double v) {
			DMatN.this.set(i, n, v);
		}

		@Override
		public int dim() {
			return DMatN.this.size();
		}

	}

	private class TransposeMatN extends DMatN {

		@Override
		public double get(int m, int n) {
			return DMatN.this.get(n, m);
		}

		@Override
		public void set(int m, int n, double v) {
			DMatN.this.set(n, m, v);
		}

		@Override
		public DMatN transpose() {
			return DMatN.this;
		}

		@Override
		public int size() {
			return DMatN.this.size();
		}

	}

}
