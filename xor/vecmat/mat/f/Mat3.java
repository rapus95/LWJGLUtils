package xor.vecmat.mat.f;

import xor.vecmat.VecMath;
import xor.vecmat.vec.f.Vec;
import xor.vecmat.vec.f.Vec2;
import xor.vecmat.vec.f.Vec3;

public abstract class Mat3 extends Mat<Mat3, Mat3, Vec3> {

	@Override
	public int n() {
		return 3;
	}

	@Override
	public int m() {
		return 3;
	}

	@Override
	protected Vec3 getRow(int m) {
		return new RowVec3(m);
	}

	@Override
	protected Vec3 getColumn(int n) {
		return new ColumnVec3(n);
	}

	@Override
	public Mat3 mul(Mat3 v) {
		final float t00 = get(0, 0);
		final float t10 = get(1, 0);
		final float t20 = get(2, 0);
		final float t01 = get(0, 1);
		final float t11 = get(1, 1);
		final float t21 = get(2, 1);
		final float t02 = get(0, 2);
		final float t12 = get(1, 2);
		final float t22 = get(2, 2);
		final float v00 = v.get(0, 0);
		final float v10 = v.get(1, 0);
		final float v20 = v.get(2, 0);
		final float v01 = v.get(0, 1);
		final float v11 = v.get(1, 1);
		final float v21 = v.get(2, 1);
		final float v02 = v.get(0, 2);
		final float v12 = v.get(1, 2);
		final float v22 = v.get(2, 2);
		float[] mat = new float[9];
		mat[0] = t00 * v00 + t01 * v10 + t02 * v20;
		mat[1] = t10 * v00 + t11 * v10 + t12 * v20;
		mat[2] = t20 * v00 + t21 * v10 + t22 * v20;
		mat[3] = t00 * v01 + t01 * v11 + t02 * v21;
		mat[4] = t10 * v01 + t11 * v11 + t12 * v21;
		mat[5] = t20 * v01 + t21 * v11 + t22 * v21;
		mat[6] = t00 * v02 + t01 * v12 + t02 * v22;
		mat[7] = t10 * v02 + t11 * v12 + t12 * v22;
		mat[8] = t20 * v02 + t21 * v12 + t22 * v22;
		return new RMat3(mat);
	}

	@Override
	public Vec3 mul(Vec3 v) {
		final float t00 = get(0, 0);
		final float t10 = get(1, 0);
		final float t20 = get(2, 0);
		final float t01 = get(0, 1);
		final float t11 = get(1, 1);
		final float t21 = get(2, 1);
		final float t02 = get(0, 2);
		final float t12 = get(1, 2);
		final float t22 = get(2, 2);
		final float x = v.get(0);
		final float y = v.get(1);
		final float z = v.get(2);
		final float xx = t00 * x + t10 * y + t20 * z;
		final float yy = t01 * x + t11 * y + t21 * z;
		final float zz = t02 * x + t12 * y + t22 * z;
		return Vec.Vec3(xx, yy, zz);
	}

	@Override
	public Mat3 pow(int y) {
		if(y==0)
			return Mat3();
		Mat3 m = this;
		for(int i=1; i<y; i++){
			m = m.mul(this);
		}
		return m;
	}

	@Override
	public Mat3 transpose() {
		return new TransposeMat3();
	}

	@Override
	public Mat3 invert() {
		final float t00 = get(0, 0);
		final float t10 = get(1, 0);
		final float t20 = get(2, 0);
		final float t01 = get(0, 1);
		final float t11 = get(1, 1);
		final float t21 = get(2, 1);
		final float t02 = get(0, 2);
		final float t12 = get(1, 2);
		final float t22 = get(2, 2);
		final float m1 = t11 * t22 - t21 * t12;
		final float m2 = -t10 * t22 + t12 * t20;
		final float m3 = t10 * t21 - t11 * t20;
		final float det = t00 * m1 + t01 * m2 + t02 * m3;
		final float invdet = 1 / det;
		float[] mat = new float[9];
		mat[0] = m1 * invdet;
		mat[1] = -(t01 * t22 - t02 * t21) * invdet;
		mat[2] = (t01 * t12 - t02 * t11) * invdet;
		mat[3] = m2 * invdet;
		mat[4] = (t00 * t22 - t02 * t20) * invdet;
		mat[5] = -(t00 * t12 - t10 * t02) * invdet;
		mat[6] = m3 * invdet;
		mat[7] = -(t00 * t21 - t20 * t01) * invdet;
		mat[8] = (t00 * t11 - t10 * t01) * invdet;
		return new RMat3(mat);
	}

	@Override
	public double det() {
		final float t00 = get(0, 0);
		final float t10 = get(1, 0);
		final float t20 = get(2, 0);
		final float t01 = get(0, 1);
		final float t11 = get(1, 1);
		final float t21 = get(2, 1);
		final float t02 = get(0, 2);
		final float t12 = get(1, 2);
		final float t22 = get(2, 2);
		return t00 * (t11 * t22 - t21 * t12) - t01 * (t10 * t22 - t12 * t20) + t02 * (t10 * t21 - t11 * t20);
	}

	@Override
	public Mat3 adjunkte() {
		final float t00 = get(0, 0);
		final float t10 = get(1, 0);
		final float t20 = get(2, 0);
		final float t01 = get(0, 1);
		final float t11 = get(1, 1);
		final float t21 = get(2, 1);
		final float t02 = get(0, 2);
		final float t12 = get(1, 2);
		final float t22 = get(2, 2);
		float[] mat = new float[9];
		mat[0] = t11 * t22 - t12 * t21;
		mat[1] = t12 * t20 - t10 * t22;
		mat[2] = t10 * t21 - t11 * t20;
		mat[3] = t02 * t21 - t01 * t22;
		mat[4] = t00 * t22 - t02 * t20;
		mat[5] = t01 * t20 - t00 * t21;
		mat[6] = t01 * t12 - t02 * t11;
		mat[7] = t02 * t10 - t00 * t12;
		mat[8] = t00 * t11 - t01 * t10;
		return new RMat3(mat);
	}

	@Override
	public Mat3 clone() {
		float[] mat = new float[9];
		mat[0] = get(0, 0);
		mat[1] = get(1, 0);
		mat[2] = get(2, 0);
		mat[3] = get(0, 1);
		mat[4] = get(1, 1);
		mat[5] = get(2, 1);
		mat[6] = get(0, 2);
		mat[7] = get(1, 2);
		mat[8] = get(2, 2);
		return new RMat3(mat);
	}

	@Override
	protected Mat3 _new() {
		return new RMat3();
	}

	private class RowVec3 extends Vec3 {

		private final int m;

		RowVec3(int m) {
			this.m = m;
		}

		@Override
		public float x() {
			return Mat3.this.get(m, 0);
		}

		@Override
		public void x(float x) {
			Mat3.this.set(m, 0, x);
		}

		@Override
		public float y() {
			return Mat3.this.get(m, 1);
		}

		@Override
		public void y(float y) {
			Mat3.this.set(m, 1, y);
		}
		
		@Override
		public float z() {
			return Mat3.this.get(m, 2);
		}

		@Override
		public void z(float y) {
			Mat3.this.set(m, 2, y);
		}

		@Override
		public float get(int i) {
			return Mat3.this.get(m, i);
		}

		@Override
		public void set(int i, float v) {
			Mat3.this.set(m, i, v);
		}

	}

	private class ColumnVec3 extends Vec3 {

		private final int n;

		ColumnVec3(int n) {
			this.n = n;
		}

		@Override
		public float x() {
			return Mat3.this.get(0, n);
		}

		@Override
		public void x(float x) {
			Mat3.this.set(0, n, x);
		}

		@Override
		public float y() {
			return Mat3.this.get(1, n);
		}

		@Override
		public void y(float y) {
			Mat3.this.set(1, n, y);
		}
		
		@Override
		public float z() {
			return Mat3.this.get(2, n);
		}

		@Override
		public void z(float y) {
			Mat3.this.set(2, n, y);
		}

		@Override
		public float get(int i) {
			return Mat3.this.get(i, n);
		}

		@Override
		public void set(int i, float v) {
			Mat3.this.set(i, n, v);
		}

	}

	private class TransposeMat3 extends Mat3 {

		@Override
		public float get(int m, int n) {
			return Mat3.this.get(n, m);
		}

		@Override
		public void set(int m, int n, float v) {
			Mat3.this.set(n, m, v);
		}

		@Override
		public Mat3 transpose() {
			return Mat3.this;
		}

	}
	
	public Mat3 translate(Vec2 v) {
		return mul(createTranslationMarix(v));
	}

	public Mat3 translate(float x, float y) {
		return mul(createTranslationMarix(x, y));
	}

	public Mat3 rotate(float a, Vec3 axis) {
		return mul(createRotationMarix(a, axis));
	}
	
	public Mat3 rotate(float a, float x, float y, float z) {
		return mul(createRotationMarix(a, x, y, z));
	}

	public Mat3 rotateRad(float a, Vec3 axis) {
		return mul(createRotationMarixRad(a, axis));
	}
	
	public Mat3 rotateRad(float a, float x, float y, float z) {
		return mul(createRotationMarixRad(a, x, y, z));
	}

	public Mat3 scale(Vec2 v) {
		return mul(createScaleMarix(v));
	}

	public Mat3 scale(float x, float y) {
		return mul(createScaleMarix(x, y));
	}
	
	public Mat3 scale(Vec3 v) {
		return mul(createScaleMarix(v));
	}

	public Mat3 scale(float x, float y, float z) {
		return mul(createScaleMarix(x, y, z));
	}

	public Mat3 rotateEuler(Vec3 v) {
		return mul(createEulerRotationMarix(v));
	}

	public Mat3 rotateEuler(float x, float y, float z) {
		return mul(createEulerRotationMarix(x, y, z));
	}

	public Mat3 rotateEulerRad(Vec3 v) {
		return mul(createEulerRotationMarixRad(v));
	}

	public Mat3 rotateEulerRad(float x, float y, float z) {
		return mul(createEulerRotationMarixRad(x, y, z));
	}
	
	public static Mat3 createTranslationMarix(Vec2 v) {
		return createTranslationMarix(v.x(), v.y());
	}

	public static Mat3 createTranslationMarix(float x, float y) {
		RMat3 m = new RMat3();
		m.mat[0] = 1;
		m.mat[4] = 1;
		m.mat[8] = 1;
		m.mat[6] = x;
		m.mat[7] = y;
		return m;
	}

	public static Mat3 createRotationMarix(float a, Vec3 axis){
		return createRotationMarixRad((float) Math.toRadians(a), axis.x(), axis.y(), axis.z());
	}
	
	public static Mat3 createRotationMarix(float a, float x, float y, float z) {
		return createRotationMarixRad((float) Math.toRadians(a), x, y, z);
	}

	public static Mat3 createRotationMarixRad(float a, Vec3 axis){
		return createRotationMarixRad(a, axis.x(), axis.y(), axis.z());
	}
	
	public static Mat3 createRotationMarixRad(float a, float x, float y, float z) {
		RMat3 m = new RMat3();
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
		m.mat[3] = x * y * c1 - z * s;
		m.mat[6] = x * z * c1 + y * s;

		m.mat[1] = y * x * c1 + z * s;
		m.mat[4] = y * y * c1 + c;
		m.mat[7] = y * z * c1 - x * s;

		m.mat[2] = x * z * c1 - y * s;
		m.mat[5] = y * z * c1 + x * s;
		m.mat[8] = z * z * c1 + c;
		return m;
	}

	public static Mat3 createScaleMarix(Vec2 v) {
		return createScaleMarix(v.x(), v.y());
	}

	public static Mat3 createScaleMarix(float x, float y) {
		RMat3 m = new RMat3();
		m.mat[0] = x;
		m.mat[4] = y;
		m.mat[8] = 1;
		return m;
	}
	
	public static Mat3 createScaleMarix(Vec3 v) {
		return createScaleMarix(v.x(), v.y(), v.z());
	}

	public static Mat3 createScaleMarix(float x, float y, float z) {
		RMat3 m = new RMat3();
		m.mat[0] = x;
		m.mat[4] = y;
		m.mat[8] = z;
		return m;
	}

	public static Mat3 createEulerRotationMarix(Vec3 v) {
		return createEulerRotationMarix(v.x(), v.y(), v.z());
	}

	public static Mat3 createEulerRotationMarix(float x, float y, float z) {
		return createEulerRotationMarixRad((float) Math.toRadians(x), (float) Math.toRadians(y), (float) Math.toRadians(z));
	}

	public static Mat3 createEulerRotationMarixRad(Vec3 v) {
		return createEulerRotationMarixRad(v.x(), v.y(), v.z());
	}

	public static Mat3 createEulerRotationMarixRad(float x, float y, float z) {
		RMat3 m = new RMat3();
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
		m.mat[3] = cxsy * cz + cx * sz;
		m.mat[4] = -cxsy * sz + cx * cz;
		m.mat[5] = -sx * cy;
		m.mat[6] = -sxsy * cz + sx * sz;
		m.mat[7] = sxsy * sz + sx * cz;
		m.mat[8] = cx * cy;
		return m;
	}

}
