package xor.vecmat.vec.d;

import xor.vecmat.VecMath;
import xor.vecmat.COMP_OPS.Func1_D;
import xor.vecmat.COMP_OPS.Func2_D;
import xor.vecmat.COMP_OPS.Func3_D;
import xor.vecmat.vec.b.BVec;
import xor.vecmat.vec.b.BVec1;

public abstract class DVec1 extends DVec<DVec1, BVec1> {

	public abstract double x();

	public abstract void x(double x);

	@Override
	public int dim() {
		return 1;
	}

	@Override
	public double get(int i) {
		switch (i) {
		case 0:
			return x();
		}
		throw new IndexOutOfBoundsException();
	}

	@Override
	public void set(int i, double v) {
		switch (i) {
		case 0:
			x(v);
			break;
		default:
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public double get(char c) {
		switch (c) {
		case 'x':
		case 'r':
		case 's':
			return x();
		}
		throw new IndexOutOfBoundsException();
	}

	@Override
	public void set(char c, double v) {
		switch (c) {
		case 'x':
		case 'r':
		case 's':
			x(v);
			break;
		default:
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public void setTo(DVec1 v) {
		x(v.x());
	}

	public void setTo(double x, double y) {
		x(x);
	}

	@Override
	public DVec1 add(double n) {
		return new DRVec1(x() + n);
	}

	@Override
	public DVec1 add(DVec1 v) {
		return new DRVec1(x() + v.x());
	}

	@Override
	public DVec1 sub(double n) {
		return new DRVec1(x() - n);
	}

	@Override
	public DVec1 sub(DVec1 v) {
		return new DRVec1(x() - v.x());
	}

	@Override
	public DVec1 sub2(double n) {
		return new DRVec1(n - x());
	}

	@Override
	public DVec1 neg() {
		return new DRVec1(-x());
	}

	@Override
	public DVec1 mul(double n) {
		return new DRVec1(x() * n);
	}

	@Override
	public DVec1 mul(DVec1 v) {
		return new DRVec1(x() * v.x());
	}

	@Override
	public DVec1 div(double n) {
		return new DRVec1(x() / n);
	}

	@Override
	public DVec1 div(DVec1 v) {
		return new DRVec1(x() / v.x());
	}

	@Override
	public DVec1 div2(double n) {
		return new DRVec1(n / x());
	}

	@Override
	public DVec1 mod(double n) {
		return new DRVec1(x() % n);
	}

	@Override
	public DVec1 mod(DVec1 v) {
		return new DRVec1(x() % v.x());
	}

	@Override
	public DVec1 mod2(double n) {
		return new DRVec1(n % x());
	}

	@Override
	public DVec1 pow(double n) {
		return new DRVec1((double) VecMath.pow(x(), n));
	}

	@Override
	public DVec1 pow(DVec1 v) {
		return new DRVec1((double) VecMath.pow(x(), v.x()));
	}

	@Override
	public DVec1 pow2(double n) {
		return new DRVec1((double) VecMath.pow(n, x()));
	}

	@Override
	public DVec1 clone() {
		return new DRVec1(x());
	}

	@Override
	protected DVec1 _new() {
		return new DRVec1(0);
	}

	@Override
	public String toString() {
		return "[" + x() + "]";
	}

	@Override
	public BVec1 equals(DVec1 other) {
		return BVec.BVec1(x() == other.x());
	}

	@Override
	public BVec1 notEqual(DVec1 other) {
		return BVec.BVec1(x() != other.x());
	}

	@Override
	public BVec1 bigger(DVec1 other) {
		return BVec.BVec1(x() > other.x());
	}

	@Override
	public BVec1 biggerEqual(DVec1 other) {
		return BVec.BVec1(x() >= other.x());
	}

	@Override
	public BVec1 smaller(DVec1 other) {
		return BVec.BVec1(x() < other.x());
	}

	@Override
	public BVec1 smallerEqual(DVec1 other) {
		return BVec.BVec1(x() <= other.x());
	}

	@Override
	protected DVec1 forEach(Func1_D f) {
		double x = f.calc(x());
		return new DRVec1(x);
	}

	@Override
	protected DVec1 forEach(DVec1 v, Func2_D f) {
		double x = f.calc(x(), v.x());
		return new DRVec1(x);
	}

	@Override
	protected DVec1 forEach(double n, Func2_D f) {
		double x = f.calc(x(), n);
		return new DRVec1(x);
	}

	@Override
	protected DVec1 forEach(DVec1 v2, DVec1 v3, Func3_D f) {
		double x = f.calc(x(), v2.x(), v3.x());
		return new DRVec1(x);
	}

	@Override
	protected DVec1 forEach(DVec1 v, double n, Func3_D f) {
		double x = f.calc(x(), v.x(), n);
		return new DRVec1(x);
	}

	@Override
	protected DVec1 forEach(double n1, double n2, Func3_D f) {
		double x = f.calc(x(), n1, n2);
		return new DRVec1(x);
	}

}
