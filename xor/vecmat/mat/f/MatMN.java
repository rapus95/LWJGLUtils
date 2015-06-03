package xor.vecmat.mat.f;

import xor.vecmat.vec.f.Vec;
import xor.vecmat.vec.f.Vec1;
import xor.vecmat.vec.f.Vec2;
import xor.vecmat.vec.f.Vec3;
import xor.vecmat.vec.f.Vec4;
import xor.vecmat.vec.f.VecN;

public abstract class MatMN extends Mat<MatMN, Mat<?, ?, ?>, Vec<?, ?>> {

	@Override
	protected Vec<?, ?> getRow(int m) {
		switch (n()) {
		case 1:
			return new RowVec1(m);
		case 2:
			return new RowVec2(m);
		case 3:
			return new RowVec3(m);
		case 4:
			return new RowVec4(m);
		}
		return new RowVecN(m);
	}

	@Override
	protected Vec<?, ?> getColumn(int n) {
		switch (m()) {
		case 1:
			return new ColumnVec1(n);
		case 2:
			return new ColumnVec2(n);
		case 3:
			return new ColumnVec3(n);
		case 4:
			return new ColumnVec4(n);
		}
		return new ColumnVecN(n);
	}

	@Override
	public Mat<?, ?, ?> mul(MatMN v) {
		return mulU(v);
	}

	@Override
	public Vec<?, ?> mul(Vec<?, ?> v) {
		final int m = m();
		final int n = n();
		if (m != v.dim())
			throw new IllegalArgumentException();
		float[] vec = new float[n];
		for (int i = 0; i < n; i++) {
			float vv = 0;
			for (int j = 0; j < m; j++) {
				vv += get(j, i) * v.get(j);
			}
			vec[i] = vv;
		}
		return Vec.VecX(vec);
	}

	@Override
	public MatMN pow(int y) {
		throw new UnsupportedOperationException();
	}

	@Override
	public MatMN transpose() {
		return new TransposeMatMN();
	}

	@Override
	public MatMN invert() {
		throw new UnsupportedOperationException();
	}

	@Override
	public double det() {
		throw new UnsupportedOperationException();
	}

	@Override
	public MatMN adjunkte() {
		throw new UnsupportedOperationException();
	}

	@Override
	public MatMN clone() {
		final int m = m();
		final int n = n();
		float[] mat = new float[m * n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				mat[i * m + j] = get(j, i);
			}
		}
		return new RMatMN(m, n, mat);
	}

	@Override
	protected MatMN _new() {
		return new RMatMN(m(), n());
	}

	private class RowVec1 extends Vec1 {

		private final int m;

		RowVec1(int m) {
			this.m = m;
		}

		@Override
		public float x() {
			return MatMN.this.get(m, 0);
		}

		@Override
		public void x(float x) {
			MatMN.this.set(m, 0, x);
		}

		@Override
		public float get(int i) {
			return MatMN.this.get(m, i);
		}

		@Override
		public void set(int i, float v) {
			MatMN.this.set(m, i, v);
		}

	}

	private class RowVec2 extends Vec2 {

		private final int m;

		RowVec2(int m) {
			this.m = m;
		}

		@Override
		public float x() {
			return MatMN.this.get(m, 0);
		}

		@Override
		public void x(float x) {
			MatMN.this.set(m, 0, x);
		}

		@Override
		public float y() {
			return MatMN.this.get(m, 1);
		}

		@Override
		public void y(float y) {
			MatMN.this.set(m, 1, y);
		}

		@Override
		public float get(int i) {
			return MatMN.this.get(m, i);
		}

		@Override
		public void set(int i, float v) {
			MatMN.this.set(m, i, v);
		}

	}

	private class RowVec3 extends Vec3 {

		private final int m;

		RowVec3(int m) {
			this.m = m;
		}

		@Override
		public float x() {
			return MatMN.this.get(m, 0);
		}

		@Override
		public void x(float x) {
			MatMN.this.set(m, 0, x);
		}

		@Override
		public float y() {
			return MatMN.this.get(m, 1);
		}

		@Override
		public void y(float y) {
			MatMN.this.set(m, 1, y);
		}

		@Override
		public float z() {
			return MatMN.this.get(m, 2);
		}

		@Override
		public void z(float y) {
			MatMN.this.set(m, 2, y);
		}

		@Override
		public float get(int i) {
			return MatMN.this.get(m, i);
		}

		@Override
		public void set(int i, float v) {
			MatMN.this.set(m, i, v);
		}

	}

	private class RowVec4 extends Vec4 {

		private final int m;

		RowVec4(int m) {
			this.m = m;
		}

		@Override
		public float x() {
			return MatMN.this.get(m, 0);
		}

		@Override
		public void x(float x) {
			MatMN.this.set(m, 0, x);
		}

		@Override
		public float y() {
			return MatMN.this.get(m, 1);
		}

		@Override
		public void y(float y) {
			MatMN.this.set(m, 1, y);
		}

		@Override
		public float z() {
			return MatMN.this.get(m, 2);
		}

		@Override
		public void z(float y) {
			MatMN.this.set(m, 2, y);
		}

		@Override
		public float w() {
			return MatMN.this.get(m, 3);
		}

		@Override
		public void w(float y) {
			MatMN.this.set(m, 3, y);
		}

		@Override
		public float get(int i) {
			return MatMN.this.get(m, i);
		}

		@Override
		public void set(int i, float v) {
			MatMN.this.set(m, i, v);
		}

	}

	private class RowVecN extends VecN {

		private final int m;

		RowVecN(int m) {
			this.m = m;
		}

		@Override
		public float get(int i) {
			return MatMN.this.get(m, i);
		}

		@Override
		public void set(int i, float v) {
			MatMN.this.set(m, i, v);
		}

		@Override
		public int dim() {
			return MatMN.this.n();
		}

	}

	private class ColumnVec1 extends Vec1 {

		private final int n;

		ColumnVec1(int n) {
			this.n = n;
		}

		@Override
		public float x() {
			return MatMN.this.get(0, n);
		}

		@Override
		public void x(float x) {
			MatMN.this.set(0, n, x);
		}

		@Override
		public float get(int i) {
			return MatMN.this.get(i, n);
		}

		@Override
		public void set(int i, float v) {
			MatMN.this.set(i, n, v);
		}

	}

	private class ColumnVec2 extends Vec2 {

		private final int n;

		ColumnVec2(int n) {
			this.n = n;
		}

		@Override
		public float x() {
			return MatMN.this.get(0, n);
		}

		@Override
		public void x(float x) {
			MatMN.this.set(0, n, x);
		}

		@Override
		public float y() {
			return MatMN.this.get(1, n);
		}

		@Override
		public void y(float y) {
			MatMN.this.set(1, n, y);
		}

		@Override
		public float get(int i) {
			return MatMN.this.get(i, n);
		}

		@Override
		public void set(int i, float v) {
			MatMN.this.set(i, n, v);
		}

	}

	private class ColumnVec3 extends Vec3 {

		private final int n;

		ColumnVec3(int n) {
			this.n = n;
		}

		@Override
		public float x() {
			return MatMN.this.get(0, n);
		}

		@Override
		public void x(float x) {
			MatMN.this.set(0, n, x);
		}

		@Override
		public float y() {
			return MatMN.this.get(1, n);
		}

		@Override
		public void y(float y) {
			MatMN.this.set(1, n, y);
		}

		@Override
		public float z() {
			return MatMN.this.get(2, n);
		}

		@Override
		public void z(float y) {
			MatMN.this.set(2, n, y);
		}

		@Override
		public float get(int i) {
			return MatMN.this.get(i, n);
		}

		@Override
		public void set(int i, float v) {
			MatMN.this.set(i, n, v);
		}

	}

	private class ColumnVec4 extends Vec4 {

		private final int n;

		ColumnVec4(int n) {
			this.n = n;
		}

		@Override
		public float x() {
			return MatMN.this.get(0, n);
		}

		@Override
		public void x(float x) {
			MatMN.this.set(0, n, x);
		}

		@Override
		public float y() {
			return MatMN.this.get(1, n);
		}

		@Override
		public void y(float y) {
			MatMN.this.set(1, n, y);
		}

		@Override
		public float z() {
			return MatMN.this.get(2, n);
		}

		@Override
		public void z(float y) {
			MatMN.this.set(2, n, y);
		}

		@Override
		public float w() {
			return MatMN.this.get(3, n);
		}

		@Override
		public void w(float y) {
			MatMN.this.set(3, n, y);
		}

		@Override
		public float get(int i) {
			return MatMN.this.get(i, n);
		}

		@Override
		public void set(int i, float v) {
			MatMN.this.set(i, n, v);
		}

	}

	private class ColumnVecN extends VecN {

		private final int n;

		ColumnVecN(int n) {
			this.n = n;
		}

		@Override
		public float get(int i) {
			return MatMN.this.get(i, n);
		}

		@Override
		public void set(int i, float v) {
			MatMN.this.set(i, n, v);
		}

		@Override
		public int dim() {
			return MatMN.this.m();
		}

	}

	private class TransposeMatMN extends MatMN {

		@Override
		public float get(int m, int n) {
			return MatMN.this.get(n, m);
		}

		@Override
		public void set(int m, int n, float v) {
			MatMN.this.set(n, m, v);
		}

		@Override
		public MatMN transpose() {
			return MatMN.this;
		}

		@Override
		public int n() {
			return MatMN.this.m();
		}

		@Override
		public int m() {
			return MatMN.this.n();
		}

	}

}
