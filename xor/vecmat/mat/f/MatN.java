package xor.vecmat.mat.f;

import xor.vecmat.vec.f.Vec;
import xor.vecmat.vec.f.VecN;

public abstract class MatN extends Mat<MatN, MatN, VecN> {

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
	protected VecN getRow(int m) {
		return new RowVecN(m);
	}

	@Override
	protected VecN getColumn(int n) {
		return new ColumnVecN(n);
	}

	@Override
	public MatN mul(MatN v) {
		final int size = size();
		if (size != v.size())
			throw new IllegalArgumentException();
		float[] mat = new float[size * size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < size; k++) {
					mat[j * size + i] += get(i, k) * v.get(k, j);
				}
			}
		}
		return new RMatN(size, mat);
	}

	@Override
	public VecN mul(VecN v) {
		final int size = size();
		if (size != v.dim())
			throw new IllegalArgumentException();
		float[] vec = new float[size];
		for (int i = 0; i < size; i++) {
			float vv = 0;
			for (int j = 0; j < size; j++) {
				vv += get(j, i) * v.get(j);
			}
			vec[i] = vv;
		}
		return Vec.VecX(vec);
	}

	@Override
	public MatN pow(int y) {
		if (y == 0)
			return MatX(size());
		MatN m = this;
		for (int i = 1; i < y; i++) {
			m = m.mul(this);
		}
		return m;
	}

	@Override
	public MatN transpose() {
		return new TransposeMatN();
	}

	@Override
	public MatN invert() {
		final int size = size();
		final float det = (float) (1 / det());
		MatN m = adjunkte();
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
		float[] mat = new float[size];
		int k = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				mat[k++] = get(j, i);
			}
		}
		return det(mat, size);
	}

	private static float det(float[] mat, int s) {
		if (s == 4) {
			return det4(mat);
		}
		final int s1 = s - 1;
		final float[] nmat = new float[s1 * s1];
		float det = 0;
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

	private static float det4(float[] mat) {
		final float t00 = mat[0];
		final float t10 = mat[1];
		final float t20 = mat[2];
		final float t30 = mat[3];
		final float t01 = mat[4];
		final float t11 = mat[5];
		final float t21 = mat[6];
		final float t31 = mat[7];
		final float t02 = mat[8];
		final float t12 = mat[9];
		final float t22 = mat[10];
		final float t32 = mat[11];
		final float t03 = mat[12];
		final float t13 = mat[13];
		final float t23 = mat[14];
		final float t33 = mat[15];

		final float s0 = t00 * t11 - t10 * t01;
		final float s1 = t00 * t12 - t10 * t02;
		final float s2 = t00 * t13 - t10 * t03;
		final float s3 = t01 * t12 - t11 * t02;
		final float s4 = t01 * t13 - t11 * t03;
		final float s5 = t02 * t13 - t12 * t03;

		final float c5 = t22 * t33 - t32 * t23;
		final float c4 = t21 * t33 - t31 * t23;
		final float c3 = t21 * t32 - t31 * t22;
		final float c2 = t20 * t33 - t30 * t23;
		final float c1 = t20 * t32 - t30 * t22;
		final float c0 = t20 * t31 - t30 * t21;

		return s0 * c5 - s1 * c4 + s2 * c3 + s3 * c2 - s4 * c1 + s5 * c0;
	}

	@Override
	public MatN adjunkte() {
		final int size = size();
		final int s1 = size - 1;

		float[] mat2 = new float[s1 * s1];

		float[] mat = new float[size * size];

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

				float det = det(mat2, s1);

				mat[i * size + j] = (i + j) % 2 == 0 ? det : -det;
			}
		}
		return new RMatN(size, mat);
	}

	@Override
	public MatN clone() {
		final int size = size();
		float[] mat = new float[size * size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				mat[i * size + j] = get(j, i);
			}
		}
		return new RMatN(size, mat);
	}

	@Override
	protected MatN _new() {
		return new RMatN(size());
	}

	private class RowVecN extends VecN {

		private final int m;

		RowVecN(int m) {
			this.m = m;
		}

		@Override
		public float get(int i) {
			return MatN.this.get(m, i);
		}

		@Override
		public void set(int i, float v) {
			MatN.this.set(m, i, v);
		}

		@Override
		public int dim() {
			return MatN.this.size();
		}

	}

	private class ColumnVecN extends VecN {

		private final int n;

		ColumnVecN(int n) {
			this.n = n;
		}

		@Override
		public float get(int i) {
			return MatN.this.get(i, n);
		}

		@Override
		public void set(int i, float v) {
			MatN.this.set(i, n, v);
		}

		@Override
		public int dim() {
			return MatN.this.size();
		}

	}

	private class TransposeMatN extends MatN {

		@Override
		public float get(int m, int n) {
			return MatN.this.get(n, m);
		}

		@Override
		public void set(int m, int n, float v) {
			MatN.this.set(n, m, v);
		}

		@Override
		public MatN transpose() {
			return MatN.this;
		}

		@Override
		public int size() {
			return MatN.this.size();
		}

	}

}
