package xor.vecmat.mat.f;

import xor.vecmat.VecMath;
import xor.vecmat.vec.f.Vec;
import xor.vecmat.vec.f.Vec1;

public abstract class Mat1 extends Mat<Mat1, Mat1, Vec1> {

	@Override
	public int n() {
		return 1;
	}

	@Override
	public int m() {
		return 1;
	}

	@Override
	protected Vec1 getRow(int m) {
		return new MVec1();
	}

	@Override
	protected Vec1 getColumn(int n) {
		return new MVec1();
	}

	@Override
	public Mat1 mul(Mat1 v) {
		return new RMat1(get(0, 0) * v.get(0, 0));
	}

	@Override
	public Vec1 mul(Vec1 v) {
		return Vec.Vec1(get(0, 0) * v.x());
	}

	@Override
	public Mat1 pow(int y) {
		return new RMat1(VecMath.pow(get(0, 0), y));
	}

	@Override
	public Mat1 transpose() {
		return this;
	}

	@Override
	public Mat1 invert() {
		return new RMat1(1 / get(0, 0));
	}

	@Override
	public double det() {
		return get(0, 0);
	}

	@Override
	public Mat1 adjunkte() {
		return new RMat1(1);
	}

	@Override
	public Mat1 clone() {
		return new RMat1(get(0, 0));
	}

	@Override
	protected Mat1 _new() {
		return new RMat1();
	}

	private class MVec1 extends Vec1 {

		@Override
		public float x() {
			return Mat1.this.get(0, 0);
		}

		@Override
		public void x(float x) {
			Mat1.this.set(0, 0, x);
		}

	}

}
