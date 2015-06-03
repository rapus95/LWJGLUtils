package xor.vecmat.mat.d;

import xor.vecmat.vec.d.DVec;
import xor.vecmat.vec.d.DVec1;
import xor.vecmat.vec.d.DVec2;
import xor.vecmat.vec.d.DVec3;
import xor.vecmat.vec.d.DVec4;
import xor.vecmat.vec.d.DVecN;

public abstract class DMatMN extends DMat<DMatMN, DMat<?, ?, ?>, DVec<?, ?>> {

	@Override
	protected DVec<?, ?> getRow(int m) {
		switch (n()) {
		case 1:
			return new RowDVec1(m);
		case 2:
			return new RowDVec2(m);
		case 3:
			return new RowDVec3(m);
		case 4:
			return new RowDVec4(m);
		}
		return new RowDVecN(m);
	}

	@Override
	protected DVec<?, ?> getColumn(int n) {
		switch (m()) {
		case 1:
			return new ColumnDVec1(n);
		case 2:
			return new ColumnDVec2(n);
		case 3:
			return new ColumnDVec3(n);
		case 4:
			return new ColumnDVec4(n);
		}
		return new ColumnDVecN(n);
	}

	@Override
	public DMat<?, ?, ?> mul(DMatMN v) {
		return mulU(v);
	}

	@Override
	public DVec<?, ?> mul(DVec<?, ?> v) {
		final int m = m();
		final int n = n();
		if (m != v.dim())
			throw new IllegalArgumentException();
		double[] vec = new double[n];
		for (int i = 0; i < n; i++) {
			double vv = 0;
			for (int j = 0; j < m; j++) {
				vv += get(j, i) * v.get(j);
			}
			vec[i] = vv;
		}
		return DVec.DVecX(vec);
	}

	@Override
	public DMatMN pow(int y) {
		throw new UnsupportedOperationException();
	}

	@Override
	public DMatMN transpose() {
		return new TransposeMatMN();
	}

	@Override
	public DMatMN invert() {
		throw new UnsupportedOperationException();
	}

	@Override
	public double det() {
		throw new UnsupportedOperationException();
	}

	@Override
	public DMatMN adjunkte() {
		throw new UnsupportedOperationException();
	}

	@Override
	public DMatMN clone() {
		final int m = m();
		final int n = n();
		double[] mat = new double[m * n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				mat[i * m + j] = get(j, i);
			}
		}
		return new DRMatMN(m, n, mat);
	}

	@Override
	protected DMatMN _new() {
		return new DRMatMN(m(), n());
	}

	private class RowDVec1 extends DVec1 {

		private final int m;

		RowDVec1(int m) {
			this.m = m;
		}

		@Override
		public double x() {
			return DMatMN.this.get(m, 0);
		}

		@Override
		public void x(double x) {
			DMatMN.this.set(m, 0, x);
		}

		@Override
		public double get(int i) {
			return DMatMN.this.get(m, i);
		}

		@Override
		public void set(int i, double v) {
			DMatMN.this.set(m, i, v);
		}

	}

	private class RowDVec2 extends DVec2 {

		private final int m;

		RowDVec2(int m) {
			this.m = m;
		}

		@Override
		public double x() {
			return DMatMN.this.get(m, 0);
		}

		@Override
		public void x(double x) {
			DMatMN.this.set(m, 0, x);
		}

		@Override
		public double y() {
			return DMatMN.this.get(m, 1);
		}

		@Override
		public void y(double y) {
			DMatMN.this.set(m, 1, y);
		}

		@Override
		public double get(int i) {
			return DMatMN.this.get(m, i);
		}

		@Override
		public void set(int i, double v) {
			DMatMN.this.set(m, i, v);
		}

	}

	private class RowDVec3 extends DVec3 {

		private final int m;

		RowDVec3(int m) {
			this.m = m;
		}

		@Override
		public double x() {
			return DMatMN.this.get(m, 0);
		}

		@Override
		public void x(double x) {
			DMatMN.this.set(m, 0, x);
		}

		@Override
		public double y() {
			return DMatMN.this.get(m, 1);
		}

		@Override
		public void y(double y) {
			DMatMN.this.set(m, 1, y);
		}

		@Override
		public double z() {
			return DMatMN.this.get(m, 2);
		}

		@Override
		public void z(double y) {
			DMatMN.this.set(m, 2, y);
		}

		@Override
		public double get(int i) {
			return DMatMN.this.get(m, i);
		}

		@Override
		public void set(int i, double v) {
			DMatMN.this.set(m, i, v);
		}

	}

	private class RowDVec4 extends DVec4 {

		private final int m;

		RowDVec4(int m) {
			this.m = m;
		}

		@Override
		public double x() {
			return DMatMN.this.get(m, 0);
		}

		@Override
		public void x(double x) {
			DMatMN.this.set(m, 0, x);
		}

		@Override
		public double y() {
			return DMatMN.this.get(m, 1);
		}

		@Override
		public void y(double y) {
			DMatMN.this.set(m, 1, y);
		}

		@Override
		public double z() {
			return DMatMN.this.get(m, 2);
		}

		@Override
		public void z(double y) {
			DMatMN.this.set(m, 2, y);
		}

		@Override
		public double w() {
			return DMatMN.this.get(m, 3);
		}

		@Override
		public void w(double y) {
			DMatMN.this.set(m, 3, y);
		}

		@Override
		public double get(int i) {
			return DMatMN.this.get(m, i);
		}

		@Override
		public void set(int i, double v) {
			DMatMN.this.set(m, i, v);
		}

	}

	private class RowDVecN extends DVecN {

		private final int m;

		RowDVecN(int m) {
			this.m = m;
		}

		@Override
		public double get(int i) {
			return DMatMN.this.get(m, i);
		}

		@Override
		public void set(int i, double v) {
			DMatMN.this.set(m, i, v);
		}

		@Override
		public int dim() {
			return DMatMN.this.n();
		}

	}

	private class ColumnDVec1 extends DVec1 {

		private final int n;

		ColumnDVec1(int n) {
			this.n = n;
		}

		@Override
		public double x() {
			return DMatMN.this.get(0, n);
		}

		@Override
		public void x(double x) {
			DMatMN.this.set(0, n, x);
		}

		@Override
		public double get(int i) {
			return DMatMN.this.get(i, n);
		}

		@Override
		public void set(int i, double v) {
			DMatMN.this.set(i, n, v);
		}

	}

	private class ColumnDVec2 extends DVec2 {

		private final int n;

		ColumnDVec2(int n) {
			this.n = n;
		}

		@Override
		public double x() {
			return DMatMN.this.get(0, n);
		}

		@Override
		public void x(double x) {
			DMatMN.this.set(0, n, x);
		}

		@Override
		public double y() {
			return DMatMN.this.get(1, n);
		}

		@Override
		public void y(double y) {
			DMatMN.this.set(1, n, y);
		}

		@Override
		public double get(int i) {
			return DMatMN.this.get(i, n);
		}

		@Override
		public void set(int i, double v) {
			DMatMN.this.set(i, n, v);
		}

	}

	private class ColumnDVec3 extends DVec3 {

		private final int n;

		ColumnDVec3(int n) {
			this.n = n;
		}

		@Override
		public double x() {
			return DMatMN.this.get(0, n);
		}

		@Override
		public void x(double x) {
			DMatMN.this.set(0, n, x);
		}

		@Override
		public double y() {
			return DMatMN.this.get(1, n);
		}

		@Override
		public void y(double y) {
			DMatMN.this.set(1, n, y);
		}

		@Override
		public double z() {
			return DMatMN.this.get(2, n);
		}

		@Override
		public void z(double y) {
			DMatMN.this.set(2, n, y);
		}

		@Override
		public double get(int i) {
			return DMatMN.this.get(i, n);
		}

		@Override
		public void set(int i, double v) {
			DMatMN.this.set(i, n, v);
		}

	}

	private class ColumnDVec4 extends DVec4 {

		private final int n;

		ColumnDVec4(int n) {
			this.n = n;
		}

		@Override
		public double x() {
			return DMatMN.this.get(0, n);
		}

		@Override
		public void x(double x) {
			DMatMN.this.set(0, n, x);
		}

		@Override
		public double y() {
			return DMatMN.this.get(1, n);
		}

		@Override
		public void y(double y) {
			DMatMN.this.set(1, n, y);
		}

		@Override
		public double z() {
			return DMatMN.this.get(2, n);
		}

		@Override
		public void z(double y) {
			DMatMN.this.set(2, n, y);
		}

		@Override
		public double w() {
			return DMatMN.this.get(3, n);
		}

		@Override
		public void w(double y) {
			DMatMN.this.set(3, n, y);
		}

		@Override
		public double get(int i) {
			return DMatMN.this.get(i, n);
		}

		@Override
		public void set(int i, double v) {
			DMatMN.this.set(i, n, v);
		}

	}

	private class ColumnDVecN extends DVecN {

		private final int n;

		ColumnDVecN(int n) {
			this.n = n;
		}

		@Override
		public double get(int i) {
			return DMatMN.this.get(i, n);
		}

		@Override
		public void set(int i, double v) {
			DMatMN.this.set(i, n, v);
		}

		@Override
		public int dim() {
			return DMatMN.this.m();
		}

	}

	private class TransposeMatMN extends DMatMN {

		@Override
		public double get(int m, int n) {
			return DMatMN.this.get(n, m);
		}

		@Override
		public void set(int m, int n, double v) {
			DMatMN.this.set(n, m, v);
		}

		@Override
		public DMatMN transpose() {
			return DMatMN.this;
		}

		@Override
		public int n() {
			return DMatMN.this.m();
		}

		@Override
		public int m() {
			return DMatMN.this.n();
		}

	}

}
