package xor.vecmat.mat.f;

import xor.vecmat.VecMath;
import xor.vecmat.vec.f.Vec;
import xor.vecmat.vec.f.Vec3;
import xor.vecmat.vec.f.Vec4;


public abstract class Mat4 extends Mat<Mat4, Mat4, Vec4> {

	@Override
	public int n() {
		return 3;
	}

	@Override
	public int m() {
		return 3;
	}

	@Override
	protected Vec4 getRow(int m) {
		return new RowVec4(m);
	}

	@Override
	protected Vec4 getColumn(int n) {
		return new ColumnVec4(n);
	}

	@Override
	public Mat4 mul(Mat4 v) {
		final float t00 = get(0, 0);
		final float t10 = get(1, 0);
		final float t20 = get(2, 0);
		final float t30 = get(3, 0);
		final float t01 = get(0, 1);
		final float t11 = get(1, 1);
		final float t21 = get(2, 1);
		final float t31 = get(3, 1);
		final float t02 = get(0, 2);
		final float t12 = get(1, 2);
		final float t22 = get(2, 2);
		final float t32 = get(3, 2);
		final float t03 = get(0, 3);
		final float t13 = get(1, 3);
		final float t23 = get(2, 3);
		final float t33 = get(3, 3);
		final float v00 = v.get(0, 0);
		final float v10 = v.get(1, 0);
		final float v20 = v.get(2, 0);
		final float v30 = v.get(3, 0);
		final float v01 = v.get(0, 1);
		final float v11 = v.get(1, 1);
		final float v21 = v.get(2, 1);
		final float v31 = v.get(3, 1);
		final float v02 = v.get(0, 2);
		final float v12 = v.get(1, 2);
		final float v22 = v.get(2, 2);
		final float v32 = v.get(3, 2);
		final float v03 = v.get(0, 3);
		final float v13 = v.get(1, 3);
		final float v23 = v.get(2, 3);
		final float v33 = v.get(3, 3);
		float[] mat = new float[16];
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
		return new RMat4(mat);
	}

	@Override
	public Vec4 mul(Vec4 v) {
		final float t00 = get(0, 0);
		final float t10 = get(1, 0);
		final float t20 = get(2, 0);
		final float t30 = get(3, 0);
		final float t01 = get(0, 1);
		final float t11 = get(1, 1);
		final float t21 = get(2, 1);
		final float t31 = get(3, 1);
		final float t02 = get(0, 2);
		final float t12 = get(1, 2);
		final float t22 = get(2, 2);
		final float t32 = get(3, 2);
		final float t03 = get(0, 3);
		final float t13 = get(1, 3);
		final float t23 = get(2, 3);
		final float t33 = get(3, 3);
		final float x = v.get(0);
		final float y = v.get(1);
		final float z = v.get(2);
		final float w = v.get(3);
		final float xx = t00 * x + t10 * y + t20 * z + t30 * w;
		final float yy = t01 * x + t11 * y + t21 * z + t31 * w;
		final float zz = t02 * x + t12 * y + t22 * z + t32 * w;
		final float ww = t03 * x + t13 * y + t23 * z + t33 * w;
		return Vec.Vec4(xx, yy, zz, ww);
	}

	@Override
	public Mat4 pow(int y) {
		if (y == 0)
			return Mat4();
		Mat4 m = this;
		for (int i = 1; i < y; i++) {
			m = m.mul(this);
		}
		return m;
	}

	@Override
	public Mat4 transpose() {
		return new TransposeMat4();
	}

	@Override
	public Mat4 invert() {
		final float t00 = get(0, 0);
		final float t10 = get(1, 0);
		final float t20 = get(2, 0);
		final float t30 = get(3, 0);
		final float t01 = get(0, 1);
		final float t11 = get(1, 1);
		final float t21 = get(2, 1);
		final float t31 = get(3, 1);
		final float t02 = get(0, 2);
		final float t12 = get(1, 2);
		final float t22 = get(2, 2);
		final float t32 = get(3, 2);
		final float t03 = get(0, 3);
		final float t13 = get(1, 3);
		final float t23 = get(2, 3);
		final float t33 = get(3, 3);

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

		final float det = s0 * c5 - s1 * c4 + s2 * c3 + s3 * c2 - s4 * c1 + s5 * c0;
		
		final float invdet = 1 / det;

		float[] mat = new float[16];
		
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

		return new RMat4(mat);
	}

	@Override
	public double det() {
		final float t00 = get(0, 0);
		final float t10 = get(1, 0);
		final float t20 = get(2, 0);
		final float t30 = get(3, 0);
		final float t01 = get(0, 1);
		final float t11 = get(1, 1);
		final float t21 = get(2, 1);
		final float t31 = get(3, 1);
		final float t02 = get(0, 2);
		final float t12 = get(1, 2);
		final float t22 = get(2, 2);
		final float t32 = get(3, 2);
		final float t03 = get(0, 3);
		final float t13 = get(1, 3);
		final float t23 = get(2, 3);
		final float t33 = get(3, 3);

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
	public Mat4 adjunkte() {
		final float t00 = get(0, 0);
		final float t10 = get(1, 0);
		final float t20 = get(2, 0);
		final float t30 = get(3, 0);
		final float t01 = get(0, 1);
		final float t11 = get(1, 1);
		final float t21 = get(2, 1);
		final float t31 = get(3, 1);
		final float t02 = get(0, 2);
		final float t12 = get(1, 2);
		final float t22 = get(2, 2);
		final float t32 = get(3, 2);
		final float t03 = get(0, 3);
		final float t13 = get(1, 3);
		final float t23 = get(2, 3);
		final float t33 = get(3, 3);

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

		float[] mat = new float[16];
		
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

		return new RMat4(mat);
	}

	@Override
	public Mat4 clone() {
		float[] mat = new float[16];
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
		return new RMat4(mat);
	}

	@Override
	protected Mat4 _new() {
		return new RMat4();
	}

	private class RowVec4 extends Vec4 {

		private final int m;

		RowVec4(int m) {
			this.m = m;
		}

		@Override
		public float x() {
			return Mat4.this.get(m, 0);
		}

		@Override
		public void x(float x) {
			Mat4.this.set(m, 0, x);
		}

		@Override
		public float y() {
			return Mat4.this.get(m, 1);
		}

		@Override
		public void y(float y) {
			Mat4.this.set(m, 1, y);
		}

		@Override
		public float z() {
			return Mat4.this.get(m, 2);
		}

		@Override
		public void z(float y) {
			Mat4.this.set(m, 2, y);
		}
		
		@Override
		public float w() {
			return Mat4.this.get(m, 3);
		}

		@Override
		public void w(float y) {
			Mat4.this.set(m, 3, y);
		}

		@Override
		public float get(int i) {
			return Mat4.this.get(m, i);
		}

		@Override
		public void set(int i, float v) {
			Mat4.this.set(m, i, v);
		}

	}

	private class ColumnVec4 extends Vec4 {

		private final int n;

		ColumnVec4(int n) {
			this.n = n;
		}

		@Override
		public float x() {
			return Mat4.this.get(0, n);
		}

		@Override
		public void x(float x) {
			Mat4.this.set(0, n, x);
		}

		@Override
		public float y() {
			return Mat4.this.get(1, n);
		}

		@Override
		public void y(float y) {
			Mat4.this.set(1, n, y);
		}

		@Override
		public float z() {
			return Mat4.this.get(2, n);
		}

		@Override
		public void z(float y) {
			Mat4.this.set(2, n, y);
		}
		
		@Override
		public float w() {
			return Mat4.this.get(3, n);
		}

		@Override
		public void w(float y) {
			Mat4.this.set(3, n, y);
		}

		@Override
		public float get(int i) {
			return Mat4.this.get(i, n);
		}

		@Override
		public void set(int i, float v) {
			Mat4.this.set(i, n, v);
		}

	}

	private class TransposeMat4 extends Mat4 {

		@Override
		public float get(int m, int n) {
			return Mat4.this.get(n, m);
		}

		@Override
		public void set(int m, int n, float v) {
			Mat4.this.set(n, m, v);
		}

		@Override
		public Mat4 transpose() {
			return Mat4.this;
		}

	}

	public Mat4 translate(Vec3 v) {
		return mul(createTranslationMarix(v));
	}

	public Mat4 translate(float x, float y, float z) {
		return mul(createTranslationMarix(x, y, z));
	}

	public Mat4 rotate(float a, Vec3 axis) {
		return mul(createRotationMarix(a, axis));
	}
	
	public Mat4 rotate(float a, float x, float y, float z) {
		return mul(createRotationMarix(a, x, y, z));
	}

	public Mat4 rotateRad(float a, Vec3 axis) {
		return mul(createRotationMarixRad(a, axis));
	}
	
	public Mat4 rotateRad(float a, float x, float y, float z) {
		return mul(createRotationMarixRad(a, x, y, z));
	}

	public Mat4 scale(Vec3 v) {
		return mul(createScaleMarix(v));
	}

	public Mat4 scale(float x, float y, float z) {
		return mul(createScaleMarix(x, y, z));
	}
	
	public Mat4 scale(Vec4 v) {
		return mul(createScaleMarix(v));
	}

	public Mat4 scale(float x, float y, float z, float w) {
		return mul(createScaleMarix(x, y, z, w));
	}

	public Mat4 rotateEuler(Vec3 v) {
		return mul(createEulerRotationMarix(v));
	}

	public Mat4 rotateEuler(float x, float y, float z) {
		return mul(createEulerRotationMarix(x, y, z));
	}

	public Mat4 rotateEulerRad(Vec3 v) {
		return mul(createEulerRotationMarixRad(v));
	}

	public Mat4 rotateEulerRad(float x, float y, float z) {
		return mul(createEulerRotationMarixRad(x, y, z));
	}

	public Mat4 setTranslation(Vec3 v) {
		return setTranslation(v.x(), v.y(), v.z());
	}

	public Mat4 setTranslation(float x, float y, float z) {
		Mat4 m = clone();
		m.set(0, 3, x);
		m.set(1, 3, y);
		m.set(2, 3, z);
		return m;
	}

	public Mat4 setRotationRad(Vec3 v) {
		return setRotationRad(v.x(), v.y(), v.z());
	}

	public Mat4 setRotationRad(float x, float y, float z) {
		Mat4 m = clone();
		float cr = (float) Math.cos(x);
		float sr = (float) Math.sin(x);
		float cp = (float) Math.cos(y);
		float sp = (float) Math.sin(y);
		float cy = (float) Math.cos(z);
		float sy = (float) Math.sin(z);

		m.set(0, 0, cp * cy);
		m.set(1, 0, cp * sy);
		m.set(2, 0, -sp);

		float srsp = sr * sp;
		float crsp = cr * sp;

		m.set(0, 1, srsp * cy - cr * sy);
		m.set(1, 1, srsp * sy + cr * cy);
		m.set(2, 1, sr * cp);

		m.set(0, 2, crsp * cy + sr * sy);
		m.set(1, 2, crsp * sy - sr * cy);
		m.set(2, 2, cr * cp);
		return m;
	}

	public Vec3 inverseRotateVec(Vec3 v) {
		final float x = v.x();
		final float y = v.y();
		final float z = v.z();
		final float xx = x * get(0, 0) + y * get(1, 0) + z * get(2, 0);
		final float yy = x * get(0, 1) + y * get(1, 1) + z * get(2, 1);
		final float zz = x * get(0, 2) + y * get(1, 2) + z * get(2, 2);
		return Vec.Vec3(xx, yy, zz);
	}

	public Vec3 rotateVec(Vec3 v) {
		final float x = v.x();
		final float y = v.y();
		final float z = v.z();
		final float xx = x * get(0, 0) + y * get(0, 1) + z * get(0, 2);
		final float yy = x * get(1, 0) + y * get(1, 1) + z * get(1, 2);
		final float zz = x * get(2, 0) + y * get(2, 1) + z * get(2, 2);
		return Vec.Vec3(xx, yy, zz);
	}

	public Vec3 translateVec(Vec3 v) {
		return v.add(get(0, 3), get(1, 3), get(2, 3));
	}

	public Vec3 inverseTranslateVec(Vec3 v) {
		return v.sub(get(0, 3), get(1, 3), get(2, 3));
	}

	public Mat4 postMultiply(Mat4 v) {
		final float t00 = get(0, 0);
		final float t10 = get(1, 0);
		final float t20 = get(2, 0);
		final float t01 = get(0, 1);
		final float t11 = get(1, 1);
		final float t21 = get(2, 1);
		final float t02 = get(0, 2);
		final float t12 = get(1, 2);
		final float t22 = get(2, 2);
		final float t03 = get(0, 3);
		final float t13 = get(1, 3);
		final float t23 = get(2, 3);
		final float v00 = v.get(0, 0);
		final float v10 = v.get(1, 0);
		final float v20 = v.get(2, 0);
		final float v01 = v.get(0, 1);
		final float v11 = v.get(1, 1);
		final float v21 = v.get(2, 1);
		final float v02 = v.get(0, 2);
		final float v12 = v.get(1, 2);
		final float v22 = v.get(2, 2);
		final float v03 = v.get(0, 3);
		final float v13 = v.get(1, 3);
		final float v23 = v.get(2, 3);

		RMat4 m2 = new RMat4();
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
	
	public static Mat4 createTranslationMarix(Vec3 v) {
		return createTranslationMarix(v.x(), v.y(), v.z());
	}

	public static Mat4 createTranslationMarix(float x, float y, float z) {
		RMat4 m = new RMat4();
		m.mat[0] = 1;
		m.mat[5] = 1;
		m.mat[10] = 1;
		m.mat[12] = x;
		m.mat[13] = y;
		m.mat[14] = z;
		m.mat[15] = 1;
		return m;
	}

	public static Mat4 createRotationMarix(float a, Vec3 axis){
		return createRotationMarixRad((float) Math.toRadians(a), axis.x(), axis.y(), axis.z());
	}
	
	public static Mat4 createRotationMarix(float a, float x, float y, float z) {
		return createRotationMarixRad((float) Math.toRadians(a), x, y, z);
	}

	public static Mat4 createRotationMarixRad(float a, Vec3 axis){
		return createRotationMarixRad(a, axis.x(), axis.y(), axis.z());
	}
	
	public static Mat4 createRotationMarixRad(float a, float x, float y, float z) {
		RMat4 m = new RMat4();
		float c = (float) Math.cos(a);
		float s = (float) Math.sin(a);
		float c1 = 1 - c;
		float l = x * x + y * y + z * z;
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

	public static Mat4 createScaleMarix(Vec3 v) {
		return createScaleMarix(v.x(), v.y(), v.z());
	}

	public static Mat4 createScaleMarix(float x, float y, float z) {
		RMat4 m = new RMat4();
		m.mat[0] = x;
		m.mat[5] = y;
		m.mat[10] = z;
		m.mat[15] = 1;
		return m;
	}
	
	public static Mat4 createScaleMarix(Vec4 v) {
		return createScaleMarix(v.x(), v.y(), v.z(), v.w());
	}

	public static Mat4 createScaleMarix(float x, float y, float z, float w) {
		RMat4 m = new RMat4();
		m.mat[0] = x;
		m.mat[5] = y;
		m.mat[10] = z;
		m.mat[15] = w;
		return m;
	}

	public static Mat4 createEulerRotationMarix(Vec3 v) {
		return createEulerRotationMarix(v.x(), v.y(), v.z());
	}

	public static Mat4 createEulerRotationMarix(float x, float y, float z) {
		return createEulerRotationMarixRad((float) Math.toRadians(x), (float) Math.toRadians(y), (float) Math.toRadians(z));
	}

	public static Mat4 createEulerRotationMarixRad(Vec3 v) {
		return createEulerRotationMarixRad(v.x(), v.y(), v.z());
	}

	public static Mat4 createEulerRotationMarixRad(float x, float y, float z) {
		RMat4 m = new RMat4();
		float cx = (float) Math.cos(x);
		float sx = (float) Math.sin(x);
		float cy = (float) Math.cos(y);
		float sy = (float) Math.sin(y);
		float cz = (float) Math.cos(z);
		float sz = (float) Math.sin(z);
		float cxsy = cx * sy;
		float sxsy = sx * sy;
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

	public static Mat4 createLookAtMatrix(Vec3 eye, Vec3 center, Vec3 up){
		return createLookAtMatrix(eye.x(), eye.y(), eye.z(), center.x(), center.y(), center.z(), up.x(), up.y(), up.z());
	}
	
	public static Mat4 createLookAtMatrix(float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
		RMat4 m = new RMat4();

		float fx = centerX - eyeX;
		float fy = centerY - eyeY;
		float fz = centerZ - eyeZ;

		float l = fx * fx + fy * fy + fz * fz;
		if (l != 1) {
			l = VecMath.inversesqrt(l);
			fx *= l;
			fy *= l;
			fz *= l;
		}

		float sx = fy * upZ - fz * upY;
		float sy = fz * upX - fx * upZ;
		float sz = fx * upY - fy * upX;

		l = sx * sx + sy * sy + sz * sz;
		if (l != 1) {
			l = VecMath.inversesqrt(l);
			sx *= l;
			sy *= l;
			sz *= l;
		}

		float ux = sy * fz - sz * fy;
		float uy = sz * fx - sx * fz;
		float uz = sx * fy - sy * fx;
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

	public static Mat4 createPerspectiveMarix(float fovy, float aspect, float zNear, float zFar) {
		RMat4 m = new RMat4();
		float f = (float) (1 / Math.tan(Math.toRadians(fovy)/2));
		m.mat[0] = f / aspect;
		m.mat[5] = f;
		m.mat[10] = (zNear + zFar) / (zNear - zFar);
		m.mat[11] = -1;
		m.mat[14] = 2 * zNear * zFar / (zNear - zFar);
		return m;
	}

	public static Mat4 createOrthoMarix(float left, float right, float bottom, float top, float near, float far) {
		RMat4 m = new RMat4();
		m.mat[0] = 2 / (right - left);
		m.mat[5] = 2 / (top - bottom);
		m.mat[10] = 2 / (far - near);
		m.mat[12] = -(right + left) / (right - left);
		m.mat[13] = -(top + bottom) / (top - bottom);
		m.mat[14] = -(far + near) / (far - near);
		m.mat[15] = 1;
		return m;
	}

	public static Mat4 createFrustumMarix(float left, float right, float bottom, float top, float near, float far) {
		RMat4 m = new RMat4();
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
