package xor.vecmat.mat.d;

import xor.vecmat.VecMath;
import xor.vecmat.vec.d.DVec;
import xor.vecmat.vec.d.DVec1;

public abstract class DMat1 extends DMat<DMat1, DMat1, DVec1> {

	@Override
	public int n() {
		return 1;
	}

	@Override
	public int m() {
		return 1;
	}

	@Override
	protected DVec1 getRow(int m) {
		return new MDVec1();
	}

	@Override
	protected DVec1 getColumn(int n) {
		return new MDVec1();
	}

	@Override
	public DMat1 mul(DMat1 v) {
		return new DRMat1(get(0, 0) * v.get(0, 0));
	}

	@Override
	public DVec1 mul(DVec1 v) {
		return DVec.DVec1(get(0, 0) * v.x());
	}

	@Override
	public DMat1 pow(int y) {
		return new DRMat1(VecMath.pow(get(0, 0), y));
	}

	@Override
	public DMat1 transpose() {
		return this;
	}

	@Override
	public DMat1 invert() {
		return new DRMat1(1 / get(0, 0));
	}

	@Override
	public double det() {
		return get(0, 0);
	}

	@Override
	public DMat1 adjunkte() {
		return new DRMat1(1);
	}

	@Override
	public DMat1 clone() {
		return new DRMat1(get(0, 0));
	}

	@Override
	protected DMat1 _new() {
		return new DRMat1();
	}

	private class MDVec1 extends DVec1 {

		@Override
		public double x() {
			return DMat1.this.get(0, 0);
		}

		@Override
		public void x(double x) {
			DMat1.this.set(0, 0, x);
		}

	}

}
