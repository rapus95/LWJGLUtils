package xor.vecmat.vec.d;

import xor.vecmat.VecMath;
import xor.vecmat.COMP_OPS.Func1_D;
import xor.vecmat.COMP_OPS.Func2_D;
import xor.vecmat.COMP_OPS.Func3_D;
import xor.vecmat.vec.b.BVec;
import xor.vecmat.vec.b.BVec2;

public abstract class DVec2 extends DVec<DVec2, BVec2> {

	public abstract double x();

	public abstract double y();

	public abstract void x(double x);

	public abstract void y(double y);

	@Override
	public int dim() {
		return 2;
	}

	@Override
	public double get(int i) {
		switch (i) {
		case 0:
			return x();
		case 1:
			return y();
		}
		throw new IndexOutOfBoundsException();
	}

	@Override
	public void set(int i, double v) {
		switch (i) {
		case 0:
			x(v);
			break;
		case 1:
			y(v);
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
		case 'y':
		case 'g':
		case 't':
			return y();
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
		case 'y':
		case 'g':
		case 't':
			y(v);
			break;
		default:
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public void setTo(DVec2 v) {
		x(v.x());
		y(v.y());
	}

	public void setTo(double x, double y) {
		x(x);
		y(y);
	}

	@Override
	public DVec2 add(double n) {
		return new DRVec2(x() + n, y() + n);
	}

	@Override
	public DVec2 add(DVec2 v) {
		return new DRVec2(x() + v.x(), y() + v.y());
	}

	public DVec2 add(double x, double y) {
		return new DRVec2(x() + x, y() + y);
	}

	@Override
	public DVec2 sub(double n) {
		return new DRVec2(x() - n, y() - n);
	}

	@Override
	public DVec2 sub(DVec2 v) {
		return new DRVec2(x() - v.x(), y() - v.y());
	}

	public DVec2 sub(double x, double y) {
		return new DRVec2(x() - x, y() - y);
	}

	@Override
	public DVec2 sub2(double n) {
		return new DRVec2(n - x(), n - y());
	}

	@Override
	public DVec2 neg() {
		return new DRVec2(-x(), -y());
	}

	@Override
	public DVec2 mul(double n) {
		return new DRVec2(x() * n, y() * n);
	}

	@Override
	public DVec2 mul(DVec2 v) {
		return new DRVec2(x() * v.x(), y() * v.y());
	}

	public DVec2 mul(double x, double y) {
		return new DRVec2(x() * x, y() * y);
	}

	@Override
	public DVec2 div(double n) {
		return new DRVec2(x() / n, y() / n);
	}

	@Override
	public DVec2 div(DVec2 v) {
		return new DRVec2(x() / v.x(), y() / v.y());
	}

	public DVec2 div(double x, double y) {
		return new DRVec2(x() / x, y() / y);
	}

	@Override
	public DVec2 div2(double n) {
		return new DRVec2(n / x(), n / y());
	}

	@Override
	public DVec2 mod(double n) {
		return new DRVec2(x() % n, y() % n);
	}

	@Override
	public DVec2 mod(DVec2 v) {
		return new DRVec2(x() % v.x(), y() % v.y());
	}

	public DVec2 mod(double x, double y) {
		return new DRVec2(x() % x, y() % y);
	}

	@Override
	public DVec2 mod2(double n) {
		return new DRVec2(n % x(), n % y());
	}

	@Override
	public DVec2 pow(double n) {
		return new DRVec2((double) VecMath.pow(x(), n), (double) VecMath.pow(y(), n));
	}

	@Override
	public DVec2 pow(DVec2 v) {
		return new DRVec2((double) VecMath.pow(x(), v.x()), (double) VecMath.pow(y(), v.y()));
	}

	public DVec2 pow(double x, double y) {
		return new DRVec2((double) VecMath.pow(x(), x), (double) VecMath.pow(y(), y));
	}

	@Override
	public DVec2 pow2(double n) {
		return new DRVec2((double) VecMath.pow(n, x()), (double) VecMath.pow(n, y()));
	}

	@Override
	public DVec2 clone() {
		return new DRVec2(x(), y());
	}

	@Override
	protected DVec2 _new() {
		return new DRVec2(0, 0);
	}

	@Override
	public String toString() {
		return "[" + x() + ", " + y() + "]";
	}
	
	@Override
	public BVec2 equals(DVec2 other) {
		return BVec.BVec2(x() == other.x(), y() == other.y());
	}

	@Override
	public BVec2 notEqual(DVec2 other) {
		return BVec.BVec2(x() != other.x(), y() != other.y());
	}

	@Override
	public BVec2 bigger(DVec2 other) {
		return BVec.BVec2(x() > other.x(), y() > other.y());
	}

	@Override
	public BVec2 biggerEqual(DVec2 other) {
		return BVec.BVec2(x() >= other.x(), y() >= other.y());
	}

	@Override
	public BVec2 smaller(DVec2 other) {
		return BVec.BVec2(x() < other.x(), y() < other.y());
	}

	@Override
	public BVec2 smallerEqual(DVec2 other) {
		return BVec.BVec2(x() <= other.x(), y() <= other.y());
	}

	@Override
	protected DVec2 forEach(Func1_D f) {
		double x = f.calc(x());
		double y = f.calc(y());
		return new DRVec2(x, y);
	}

	@Override
	protected DVec2 forEach(DVec2 v, Func2_D f) {
		double x = f.calc(x(), v.x());
		double y = f.calc(y(), v.y());
		return new DRVec2(x, y);
	}

	@Override
	protected DVec2 forEach(double n, Func2_D f) {
		double x = f.calc(x(), n);
		double y = f.calc(y(), n);
		return new DRVec2(x, y);
	}

	@Override
	protected DVec2 forEach(DVec2 v2, DVec2 v3, Func3_D f) {
		double x = f.calc(x(), v2.x(), v3.x());
		double y = f.calc(y(), v2.y(), v3.y());
		return new DRVec2(x, y);
	}

	@Override
	protected DVec2 forEach(DVec2 v, double n, Func3_D f) {
		double x = f.calc(x(), v.x(), n);
		double y = f.calc(y(), v.y(), n);
		return new DRVec2(x, y);
	}

	@Override
	protected DVec2 forEach(double n1, double n2, Func3_D f) {
		double x = f.calc(x(), n1, n2);
		double y = f.calc(y(), n1, n2);
		return new DRVec2(x, y);
	}

}
