package xor.vecmat.mat.d;

import xor.vecmat.VecMath;
import xor.vecmat.mat.f.Mat4;
import xor.vecmat.vec.d.DVec;
import xor.vecmat.vec.d.DVec3;
import xor.vecmat.vec.d.DVec4;

public abstract class DMat4 extends DMat<DMat4, DMat4, DVec4> {

	@Override
	public int n() {
		return 3;
	}

	@Override
	public int m() {
		return 3;
	}

	@Override
	protected DVec4 getRow(int m) {
		return new RowDVec4(m);
	}

	@Override
	protected DVec4 getColumn(int n) {
		return new ColumnDVec4(n);
	}

	@Override
	public DMat4 mul(DMat4 v) {
		final double t00 = get(0, 0);
		final double t10 = get(1, 0);
		final double t20 = get(2, 0);
		final double t30 = get(3, 0);
		final double t01 = get(0, 1);
		final double t11 = get(1, 1);
		final double t21 = get(2, 1);
		final double t31 = get(3, 1);
		final double t02 = get(0, 2);
		final double t12 = get(1, 2);
		final double t22 = get(2, 2);
		final double t32 = get(3, 2);
		final double t03 = get(0, 3);
		final double t13 = get(1, 3);
		final double t23 = get(2, 3);
		final double t33 = get(3, 3);
		final double v00 = v.get(0, 0);
		final double v10 = v.get(1, 0);
		final double v20 = v.get(2, 0);
		final double v30 = v.get(3, 0);
		final double v01 = v.get(0, 1);
		final double v11 = v.get(1, 1);
		final double v21 = v.get(2, 1);
		final double v31 = v.get(3, 1);
		final double v02 = v.get(0, 2);
		final double v12 = v.get(1, 2);
		final double v22 = v.get(2, 2);
		final double v32 = v.get(3, 2);
		final double v03 = v.get(0, 3);
		final double v13 = v.get(1, 3);
		final double v23 = v.get(2, 3);
		final double v33 = v.get(3, 3);
		double[] mat = new double[16];
		mat[0] = t00 * v00 + t01 * v10 + t02 * v20 + t03 * v30;
		mat[1] = t10 * v00 + t11 * v10 + t12 * v20 + t13 * v30;
		mat[2] = t20 * v00 + t21 * v10 + t22 * v20 + t23 * v30;
		mat[3] = t30 * v00 + t31 * v10 + t32 * v20 + t33 * v30;
		mat[4] = t00 * v01 + t01 * v11 + t02 * v21 + t03 * v31;
		mat[5] = t10 * v01 + t11 * v11 + t12 * v21 + t13 * v31;
		mat[6] = t20 * v01 + t21 * v11 + t22 * v21 + t23 * v31;
		mat[7] = t30 * v01 + t31 * v11 + t32 * v21 + t33 * v31;
		mat[8] = t00 * v02 + t01 * v12 + t02 * v22 + t03 * v32;
		mat[9] = t10 * v02 + t11 * v12 + t12 * v22 + t13 * v32;
		mat[10] = t20 * v02 + t21 * v12 + t22 * v22 + t23 * v32;
		mat[11] = t30 * v02 + t31 * v12 + t32 * v22 + t33 * v32;
		mat[12] = t00 * v03 + t01 * v13 + t02 * v23 + t03 * v33;
		mat[13] = t10 * v03 + t11 * v13 + t12 * v23 + t13 * v33;
		mat[14] = t20 * v03 + t21 * v13 + t22 * v23 + t23 * v33;
		mat[15] = t30 * v03 + t31 * v13 + t32 * v23 + t33 * v33;
		return new DRMat4(mat);
	}

	@Override
	public DVec4 mul(DVec4 v) {
		final double t00 = get(0, 0);
		final double t10 = get(1, 0);
		final double t20 = get(2, 0);
		final double t30 = get(3, 0);
		final double t01 = get(0, 1);
		final double t11 = get(1, 1);
		final double t21 = get(2, 1);
		final double t31 = get(3, 1);
		final double t02 = get(0, 2);
		final double t12 = get(1, 2);
		final double t22 = get(2, 2);
		final double t32 = get(3, 2);
		final double t03 = get(0, 3);
		final double t13 = get(1, 3);
		final double t23 = get(2, 3);
		final double t33 = get(3, 3);
		final double x = v.get(0);
		final double y = v.get(1);
		final double z = v.get(2);
		final double w = v.get(3);
		final double xx = t00 * x + t10 * y + t20 * z + t30 * w;
		final double yy = t01 * x + t11 * y + t21 * z + t31 * w;
		final double zz = t02 * x + t12 * y + t22 * z + t32 * w;
		final double ww = t03 * x + t13 * y + t23 * z + t33 * w;
		return DVec.DVec4(xx, yy, zz, ww);
	}

	@Override
	public DMat4 pow(int y) {
		if (y == 0)
			return DMat4();
		DMat4 m = this;
		for (int i = 1; i < y; i++) {
			m = m.mul(this);
		}
		return m;
	}

	@Override
	public DMat4 transpose() {
		return new TransposeMat4();
	}

	@Override
	public DMat4 invert() {
		final double t00 = get(0, 0);
		final double t10 = get(1, 0);
		final double t20 = get(2, 0);
		final double t30 = get(3, 0);
		final double t01 = get(0, 1);
		final double t11 = get(1, 1);
		final double t21 = get(2, 1);
		final double t31 = get(3, 1);
		final double t02 = get(0, 2);
		final double t12 = get(1, 2);
		final double t22 = get(2, 2);
		final double t32 = get(3, 2);
		final double t03 = get(0, 3);
		final double t13 = get(1, 3);
		final double t23 = get(2, 3);
		final double t33 = get(3, 3);

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

		final double det = s0 * c5 - s1 * c4 + s2 * c3 + s3 * c2 - s4 * c1 + s5 * c0;
		
		final double invdet = 1 / det;

		double[] mat = new double[16];
		
		mat[0] = (t11 * c5 - t12 * c4 + t13 * c3) * invdet;
		mat[1] = (-t10 * c5 + t12 * c2 - t13 * c1) * invdet;
		mat[2] = (t10 * c4 - t11 * c2 + t13 * c0) * invdet;
		mat[3] = (-t10 * c3 + t11 * c1 - t12 * c0) * invdet;
		
		mat[4] = (-t01 * c5 + t02 * c4 - t03 * c3) * invdet;
		mat[5] = (t00 * c5 - t02 * c2 + t03 * c1) * invdet;
		mat[6] = (-t00 * c4 + t01 * c2 - t03 * c0) * invdet;
		mat[7] = (t00 * c3 - t01 * c1 + t02 * c0) * invdet;
		
		mat[8] = (t31 * s5 - t32 * s4 + t33 * s3) * invdet;
		mat[9] = (-t30 * s5 + t32 * s2 - t33 * s1) * invdet;
		mat[10] = (t30 * s4 - t31 * s2 + t33 * s0) * invdet;
		mat[11] = (-t30 * s3 + t31 * s1 - t32 * s0) * invdet;
		
		mat[12] = (-t21 * s5 + t22 * s4 - t23 * s3) * invdet;
		mat[13] = (t20 * s5 - t22 * s2 + t23 * s1) * invdet;
		mat[14] = (-t20 * s4 + t21 * s2 - t23 * s0) * invdet;
		mat[15] = (t20 * s3 - t21 * s1 + t22 * s0) * invdet;

		return new DRMat4(mat);
	}

	@Override
	public double det() {
		final double t00 = get(0, 0);
		final double t10 = get(1, 0);
		final double t20 = get(2, 0);
		final double t30 = get(3, 0);
		final double t01 = get(0, 1);
		final double t11 = get(1, 1);
		final double t21 = get(2, 1);
		final double t31 = get(3, 1);
		final double t02 = get(0, 2);
		final double t12 = get(1, 2);
		final double t22 = get(2, 2);
		final double t32 = get(3, 2);
		final double t03 = get(0, 3);
		final double t13 = get(1, 3);
		final double t23 = get(2, 3);
		final double t33 = get(3, 3);

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
	public DMat4 adjunkte() {
		final double t00 = get(0, 0);
		final double t10 = get(1, 0);
		final double t20 = get(2, 0);
		final double t30 = get(3, 0);
		final double t01 = get(0, 1);
		final double t11 = get(1, 1);
		final double t21 = get(2, 1);
		final double t31 = get(3, 1);
		final double t02 = get(0, 2);
		final double t12 = get(1, 2);
		final double t22 = get(2, 2);
		final double t32 = get(3, 2);
		final double t03 = get(0, 3);
		final double t13 = get(1, 3);
		final double t23 = get(2, 3);
		final double t33 = get(3, 3);

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

		double[] mat = new double[16];
		
		mat[0] = t11 * c5 - t12 * c4 + t13 * c3;
		mat[4] = -t01 * c5 + t02 * c4 - t03 * c3;
		mat[8] = t31 * s5 - t32 * s4 + t33 * s3;
		mat[12] = -t21 * s5 + t22 * s4 - t23 * s3;

		mat[1] = -t10 * c5 + t12 * c2 - t13 * c1;
		mat[5] = t00 * c5 - t02 * c2 + t03 * c1;
		mat[9] = -t30 * s5 + t32 * s2 - t33 * s1;
		mat[13] = t20 * s5 - t22 * s2 + t23 * s1;

		mat[2] = t10 * c4 - t11 * c2 + t13 * c0;
		mat[6] = -t00 * c4 + t01 * c2 - t03 * c0;
		mat[10] = t30 * s4 - t31 * s2 + t33 * s0;
		mat[14] = -t20 * s4 + t21 * s2 - t23 * s0;

		mat[3] = -t10 * c3 + t11 * c1 - t12 * c0;
		mat[7] = t00 * c3 - t01 * c1 + t02 * c0;
		mat[11] = -t30 * s3 + t31 * s1 - t32 * s0;
		mat[15] = t20 * s3 - t21 * s1 + t22 * s0;

		return new DRMat4(mat);
	}

	@Override
	public DMat4 clone() {
		double[] mat = new double[16];
		mat[0] = get(0, 0);
		mat[1] = get(1, 0);
		mat[2] = get(2, 0);
		mat[3] = get(3, 0);
		mat[4] = get(0, 1);
		mat[5] = get(1, 1);
		mat[6] = get(2, 1);
		mat[7] = get(3, 1);
		mat[8] = get(0, 2);
		mat[9] = get(1, 2);
		mat[10] = get(2, 2);
		mat[11] = get(3, 2);
		mat[12] = get(0, 3);
		mat[13] = get(1, 3);
		mat[14] = get(2, 3);
		mat[15] = get(3, 3);
		return new DRMat4(mat);
	}

	@Override
	protected DMat4 _new() {
		return new DRMat4();
	}

	private class RowDVec4 extends DVec4 {

		private final int m;

		RowDVec4(int m) {
			this.m = m;
		}

		@Override
		public double x() {
			return DMat4.this.get(m, 0);
		}

		@Override
		public void x(double x) {
			DMat4.this.set(m, 0, x);
		}

		@Override
		public double y() {
			return DMat4.this.get(m, 1);
		}

		@Override
		public void y(double y) {
			DMat4.this.set(m, 1, y);
		}

		@Override
		public double z() {
			return DMat4.this.get(m, 2);
		}

		@Override
		public void z(double y) {
			DMat4.this.set(m, 2, y);
		}
		
		@Override
		public double w() {
			return DMat4.this.get(m, 3);
		}

		@Override
		public void w(double y) {
			DMat4.this.set(m, 3, y);
		}

		@Override
		public double get(int i) {
			return DMat4.this.get(m, i);
		}

		@Override
		public void set(int i, double v) {
			DMat4.this.set(m, i, v);
		}

	}

	private class ColumnDVec4 extends DVec4 {

		private final int n;

		ColumnDVec4(int n) {
			this.n = n;
		}

		@Override
		public double x() {
			return DMat4.this.get(0, n);
		}

		@Override
		public void x(double x) {
			DMat4.this.set(0, n, x);
		}

		@Override
		public double y() {
			return DMat4.this.get(1, n);
		}

		@Override
		public void y(double y) {
			DMat4.this.set(1, n, y);
		}

		@Override
		public double z() {
			return DMat4.this.get(2, n);
		}

		@Override
		public void z(double y) {
			DMat4.this.set(2, n, y);
		}
		
		@Override
		public double w() {
			return DMat4.this.get(3, n);
		}

		@Override
		public void w(double y) {
			DMat4.this.set(3, n, y);
		}

		@Override
		public double get(int i) {
			return DMat4.this.get(i, n);
		}

		@Override
		public void set(int i, double v) {
			DMat4.this.set(i, n, v);
		}

	}

	private class TransposeMat4 extends DMat4 {

		@Override
		public double get(int m, int n) {
			return DMat4.this.get(n, m);
		}

		@Override
		public void set(int m, int n, double v) {
			DMat4.this.set(n, m, v);
		}

		@Override
		public DMat4 transpose() {
			return DMat4.this;
		}

	}
	
	public DMat4 translate(DVec3 v) {
		return mul(createTranslationMarix(v));
	}

	public DMat4 translate(double x, double y, double z) {
		return mul(createTranslationMarix(x, y, z));
	}

	public DMat4 rotate(double a, DVec3 axis) {
		return mul(createRotationMarix(a, axis));
	}
	
	public DMat4 rotate(double a, double x, double y, double z) {
		return mul(createRotationMarix(a, x, y, z));
	}

	public DMat4 rotateRad(double a, DVec3 axis) {
		return mul(createRotationMarixRad(a, axis));
	}
	
	public DMat4 rotateRad(double a, double x, double y, double z) {
		return mul(createRotationMarixRad(a, x, y, z));
	}

	public DMat4 scale(DVec3 v) {
		return mul(createScaleMarix(v));
	}

	public DMat4 scale(double x, double y, double z) {
		return mul(createScaleMarix(x, y, z));
	}
	
	public DMat4 scale(DVec4 v) {
		return mul(createScaleMarix(v));
	}

	public DMat4 scale(double x, double y, double z, double w) {
		return mul(createScaleMarix(x, y, z, w));
	}

	public DMat4 rotateEuler(DVec3 v) {
		return mul(createEulerRotationMarix(v));
	}

	public DMat4 rotateEuler(double x, double y, double z) {
		return mul(createEulerRotationMarix(x, y, z));
	}

	public DMat4 rotateEulerRad(DVec3 v) {
		return mul(createEulerRotationMarixRad(v));
	}

	public DMat4 rotateEulerRad(double x, double y, double z) {
		return mul(createEulerRotationMarixRad(x, y, z));
	}

	public DMat4 setTranslation(DVec3 v) {
		return setTranslation(v.x(), v.y(), v.z());
	}

	public DMat4 setTranslation(double x, double y, double z) {
		DMat4 m = clone();
		m.set(0, 3, x);
		m.set(1, 3, y);
		m.set(2, 3, z);
		return m;
	}

	public DMat4 setRotationRad(DVec3 v) {
		return setRotationRad(v.x(), v.y(), v.z());
	}

	public DMat4 setRotationRad(double x, double y, double z) {
		DMat4 m = clone();
		double cr = (double) Math.cos(x);
		double sr = (double) Math.sin(x);
		double cp = (double) Math.cos(y);
		double sp = (double) Math.sin(y);
		double cy = (double) Math.cos(z);
		double sy = (double) Math.sin(z);

		m.set(0, 0, cp * cy);
		m.set(1, 0, cp * sy);
		m.set(2, 0, -sp);

		double srsp = sr * sp;
		double crsp = cr * sp;

		m.set(0, 1, srsp * cy - cr * sy);
		m.set(1, 1, srsp * sy + cr * cy);
		m.set(2, 1, sr * cp);

		m.set(0, 2, crsp * cy + sr * sy);
		m.set(1, 2, crsp * sy - sr * cy);
		m.set(2, 2, cr * cp);
		return m;
	}

	public DVec3 inverseRotateVec(DVec3 v) {
		final double x = v.x();
		final double y = v.y();
		final double z = v.z();
		final double xx = x * get(0, 0) + y * get(1, 0) + z * get(2, 0);
		final double yy = x * get(0, 1) + y * get(1, 1) + z * get(2, 1);
		final double zz = x * get(0, 2) + y * get(1, 2) + z * get(2, 2);
		return DVec.DVec3(xx, yy, zz);
	}

	public DVec3 rotateVec(DVec3 v) {
		final double x = v.x();
		final double y = v.y();
		final double z = v.z();
		final double xx = x * get(0, 0) + y * get(0, 1) + z * get(0, 2);
		final double yy = x * get(1, 0) + y * get(1, 1) + z * get(1, 2);
		final double zz = x * get(2, 0) + y * get(2, 1) + z * get(2, 2);
		return DVec.DVec3(xx, yy, zz);
	}

	public DVec3 translateVec(DVec3 v) {
		return v.add(get(0, 3), get(1, 3), get(2, 3));
	}

	public DVec3 inverseTranslateVec(DVec3 v) {
		return v.sub(get(0, 3), get(1, 3), get(2, 3));
	}

	public DMat4 postMultiply(Mat4 v) {
		final double t00 = get(0, 0);
		final double t10 = get(1, 0);
		final double t20 = get(2, 0);
		final double t01 = get(0, 1);
		final double t11 = get(1, 1);
		final double t21 = get(2, 1);
		final double t02 = get(0, 2);
		final double t12 = get(1, 2);
		final double t22 = get(2, 2);
		final double t03 = get(0, 3);
		final double t13 = get(1, 3);
		final double t23 = get(2, 3);
		final double v00 = v.get(0, 0);
		final double v10 = v.get(1, 0);
		final double v20 = v.get(2, 0);
		final double v01 = v.get(0, 1);
		final double v11 = v.get(1, 1);
		final double v21 = v.get(2, 1);
		final double v02 = v.get(0, 2);
		final double v12 = v.get(1, 2);
		final double v22 = v.get(2, 2);
		final double v03 = v.get(0, 3);
		final double v13 = v.get(1, 3);
		final double v23 = v.get(2, 3);

		DRMat4 m2 = new DRMat4();
		m2.mat[0] = t00 * v00 + t01 * v10 + t02 * v20;
		m2.mat[1] = t10 * v00 + t11 * v10 + t12 * v20;
		m2.mat[2] = t20 * v00 + t21 * v10 + t22 * v20;

		m2.mat[4] = t00 * v01 + t01 * v11 + t02 * v21;
		m2.mat[5] = t10 * v01 + t11 * v11 + t12 * v21;
		m2.mat[6] = t20 * v01 + t21 * v11 + t22 * v21;

		m2.mat[8] = t00 * v02 + t01 * v12 + t02 * v22;
		m2.mat[9] = t10 * v02 + t11 * v12 + t12 * v22;
		m2.mat[10] = t20 * v02 + t21 * v12 + t22 * v22;

		m2.mat[12] = t00 * v03 + t01 * v13 + t02 * v23 + t03;
		m2.mat[13] = t10 * v03 + t11 * v13 + t12 * v23 + t13;
		m2.mat[14] = t20 * v03 + t21 * v13 + t22 * v23 + t23;

		m2.mat[15] = 1;
		return m2;
	}
	
	public static DMat4 createTranslationMarix(DVec3 v) {
		return createTranslationMarix(v.x(), v.y(), v.z());
	}

	public static DMat4 createTranslationMarix(double x, double y, double z) {
		DRMat4 m = new DRMat4();
		m.mat[0] = 1;
		m.mat[5] = 1;
		m.mat[10] = 1;
		m.mat[12] = x;
		m.mat[13] = y;
		m.mat[14] = z;
		m.mat[15] = 1;
		return m;
	}

	public static DMat4 createRotationMarix(double a, DVec3 axis){
		return createRotationMarixRad((double) Math.toRadians(a), axis.x(), axis.y(), axis.z());
	}
	
	public static DMat4 createRotationMarix(double a, double x, double y, double z) {
		return createRotationMarixRad((double) Math.toRadians(a), x, y, z);
	}

	public static DMat4 createRotationMarixRad(double a, DVec3 axis){
		return createRotationMarixRad(a, axis.x(), axis.y(), axis.z());
	}
	
	public static DMat4 createRotationMarixRad(double a, double x, double y, double z) {
		DRMat4 m = new DRMat4();
		double c = (double) Math.cos(a);
		double s = (double) Math.sin(a);
		double c1 = 1 - c;
		double l = x * x + y * y + z * z;
		if (l != 1) {
			l = VecMath.inversesqrt(l);
			x *= l;
			y *= l;
			z *= l;
		}
		m.mat[0] = x * x * c1 + c;
		m.mat[4] = x * y * c1 - z * s;
		m.mat[8] = x * z * c1 + y * s;

		m.mat[1] = y * x * c1 + z * s;
		m.mat[5] = y * y * c1 + c;
		m.mat[9] = y * z * c1 - x * s;

		m.mat[2] = x * z * c1 - y * s;
		m.mat[6] = y * z * c1 + x * s;
		m.mat[10] = z * z * c1 + c;
		m.mat[15] = 1;
		return m;
	}

	public static DMat4 createScaleMarix(DVec3 v) {
		return createScaleMarix(v.x(), v.y(), v.z());
	}

	public static DMat4 createScaleMarix(double x, double y, double z) {
		DRMat4 m = new DRMat4();
		m.mat[0] = x;
		m.mat[5] = y;
		m.mat[10] = z;
		m.mat[15] = 1;
		return m;
	}
	
	public static DMat4 createScaleMarix(DVec4 v) {
		return createScaleMarix(v.x(), v.y(), v.z(), v.w());
	}

	public static DMat4 createScaleMarix(double x, double y, double z, double w) {
		DRMat4 m = new DRMat4();
		m.mat[0] = x;
		m.mat[5] = y;
		m.mat[10] = z;
		m.mat[15] = w;
		return m;
	}

	public static DMat4 createEulerRotationMarix(DVec3 v) {
		return createEulerRotationMarix(v.x(), v.y(), v.z());
	}

	public static DMat4 createEulerRotationMarix(double x, double y, double z) {
		return createEulerRotationMarixRad((double) Math.toRadians(x), (double) Math.toRadians(y), (double) Math.toRadians(z));
	}

	public static DMat4 createEulerRotationMarixRad(DVec3 v) {
		return createEulerRotationMarixRad(v.x(), v.y(), v.z());
	}

	public static DMat4 createEulerRotationMarixRad(double x, double y, double z) {
		DRMat4 m = new DRMat4();
		double cx = (double) Math.cos(x);
		double sx = (double) Math.sin(x);
		double cy = (double) Math.cos(y);
		double sy = (double) Math.sin(y);
		double cz = (double) Math.cos(z);
		double sz = (double) Math.sin(z);
		double cxsy = cx * sy;
		double sxsy = sx * sy;
		m.mat[0] = cy * cz;
		m.mat[1] = -cy * sz;
		m.mat[2] = sy;
		m.mat[4] = cxsy * cz + cx * sz;
		m.mat[5] = -cxsy * sz + cx * cz;
		m.mat[6] = -sx * cy;
		m.mat[8] = -sxsy * cz + sx * sz;
		m.mat[9] = sxsy * sz + sx * cz;
		m.mat[10] = cx * cy;
		m.mat[15] = 1;
		return m;
	}

	public static DMat4 createLookAtMatrix(DVec3 eye, DVec3 center, DVec3 up){
		return createLookAtMatrix(eye.x(), eye.y(), eye.z(), center.x(), center.y(), center.z(), up.x(), up.y(), up.z());
	}
	
	public static DMat4 createLookAtMatrix(double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
		DRMat4 m = new DRMat4();

		double fx = centerX - eyeX;
		double fy = centerY - eyeY;
		double fz = centerZ - eyeZ;

		double l = fx * fx + fy * fy + fz * fz;
		if (l != 1) {
			l = VecMath.inversesqrt(l);
			fx *= l;
			fy *= l;
			fz *= l;
		}

		double sx = fy * upZ - fz * upY;
		double sy = fz * upX - fx * upZ;
		double sz = fx * upY - fy * upX;

		l = sx * sx + sy * sy + sz * sz;
		if (l != 1) {
			l = VecMath.inversesqrt(l);
			sx *= l;
			sy *= l;
			sz *= l;
		}

		double ux = sy * fz - sz * fy;
		double uy = sz * fx - sx * fz;
		double uz = sx * fy - sy * fx;
		m.mat[0] = sx;
		m.mat[1] = ux;
		m.mat[2] = -fx;
		m.mat[4] = sy;
		m.mat[5] = uy;
		m.mat[6] = -fy;
		m.mat[8] = sz;
		m.mat[9] = uz;
		m.mat[10] = -fz;
		m.mat[15] = 1;
		return m.translate(-eyeX, -eyeY, -eyeZ);
	}

	public static DMat4 createPerspectiveMarix(double fovy, double aspect, double zNear, double zFar) {
		DRMat4 m = new DRMat4();
		double f = (double) (1 / Math.tan(Math.toRadians(fovy)/2));
		m.mat[0] = f / aspect;
		m.mat[5] = f;
		m.mat[10] = (zNear + zFar) / (zNear - zFar);
		m.mat[11] = -1;
		m.mat[14] = 2 * zNear * zFar / (zNear - zFar);
		return m;
	}

	public static DMat4 createOrthoMarix(double left, double right, double bottom, double top, double near, double far) {
		DRMat4 m = new DRMat4();
		m.mat[0] = 2 / (right - left);
		m.mat[5] = 2 / (top - bottom);
		m.mat[10] = 2 / (far - near);
		m.mat[12] = -(right + left) / (right - left);
		m.mat[13] = -(top + bottom) / (top - bottom);
		m.mat[14] = -(far + near) / (far - near);
		m.mat[15] = 1;
		return m;
	}

	public static DMat4 createFrustumMarix(double left, double right, double bottom, double top, double near, double far) {
		DRMat4 m = new DRMat4();
		m.mat[0] = 2 * near / (right - left);
		m.mat[5] = 2 * near / (top - bottom);
		m.mat[8] = (right + left) / (right - left);
		m.mat[9] = (top + bottom) / (top - bottom);
		m.mat[10] = -(near - far) / (near - far);
		m.mat[11] = -1;
		m.mat[14] = -2 * near * far / (near - far);
		return m;
	}

}
