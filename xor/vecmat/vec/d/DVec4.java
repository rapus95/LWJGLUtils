package xor.vecmat.vec.d;

import xor.vecmat.VecMath;
import xor.vecmat.COMP_OPS.Func1_D;
import xor.vecmat.COMP_OPS.Func2_D;
import xor.vecmat.COMP_OPS.Func3_D;
import xor.vecmat.vec.b.BVec;
import xor.vecmat.vec.b.BVec4;

public abstract class DVec4 extends DVec<DVec4, BVec4> {

	public abstract double x();

	public abstract double y();

	public abstract double z();

	public abstract double w();

	public abstract void x(double x);

	public abstract void y(double y);

	public abstract void z(double z);

	public abstract void w(double w);

	@Override
	public int dim() {
		return 4;
	}

	@Override
	public double get(int i) {
		switch (i) {
		case 0:
			return x();
		case 1:
			return y();
		case 2:
			return z();
		case 3:
			return w();
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
		case 2:
			z(v);
			break;
		case 3:
			w(v);
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
		case 'z':
		case 'b':
		case 'p':
			return z();
		case 'w':
		case 'a':
		case 'q':
			return w();
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
		case 'z':
		case 'b':
		case 'p':
			z(v);
			break;
		case 'w':
		case 'a':
		case 'q':
			w(v);
			break;
		default:
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public void setTo(DVec4 v) {
		x(v.x());
		y(v.y());
		z(v.z());
		w(v.w());
	}

	public void setTo(double x, double y, double z, double w) {
		x(x);
		y(y);
		z(z);
		w(w);
	}

	@Override
	public DVec4 add(double n) {
		return new DRVec4(x() + n, y() + n, z() + n, w() + n);
	}

	@Override
	public DVec4 add(DVec4 v) {
		return new DRVec4(x() + v.x(), y() + v.y(), z() + v.z(), w() + v.w());
	}

	public DVec4 add(double x, double y, double z, double w) {
		return new DRVec4(x() + x, y() + y, z() + z, w() + w);
	}

	@Override
	public DVec4 sub(double n) {
		return new DRVec4(x() - n, y() - n, z() - n, w() - n);
	}

	@Override
	public DVec4 sub(DVec4 v) {
		return new DRVec4(x() - v.x(), y() - v.y(), z() - v.z(), w() - v.w());
	}

	public DVec4 sub(double x, double y, double z, double w) {
		return new DRVec4(x() - x, y() - y, z() - z, w() - w);
	}

	@Override
	public DVec4 sub2(double n) {
		return new DRVec4(n - x(), n - y(), n - z(), n - w());
	}

	@Override
	public DVec4 neg() {
		return new DRVec4(-x(), -y(), -z(), -w());
	}

	@Override
	public DVec4 mul(double n) {
		return new DRVec4(x() * n, y() * n, z() * n, w() * n);
	}

	@Override
	public DVec4 mul(DVec4 v) {
		return new DRVec4(x() * v.x(), y() * v.y(), z() * v.z(), w() * v.w());
	}

	public DVec4 mul(double x, double y, double z, double w) {
		return new DRVec4(x() * x, y() * y, z() * z, w() * w);
	}

	@Override
	public DVec4 div(double n) {
		return new DRVec4(x() / n, y() / n, z() / n, w() / n);
	}

	@Override
	public DVec4 div(DVec4 v) {
		return new DRVec4(x() / v.x(), y() / v.y(), z() / v.z(), w() / v.w());
	}

	public DVec4 div(double x, double y, double z, double w) {
		return new DRVec4(x() / x, y() / y, z() / z, w() / w);
	}

	@Override
	public DVec4 div2(double n) {
		return new DRVec4(n / x(), n / y(), n / z(), n / w());
	}

	@Override
	public DVec4 mod(double n) {
		return new DRVec4(x() % n, y() % n, z() % n, w() % n);
	}

	@Override
	public DVec4 mod(DVec4 v) {
		return new DRVec4(x() % v.x(), y() % v.y(), z() % v.z(), w() % v.w());
	}

	public DVec4 mod(double x, double y, double z, double w) {
		return new DRVec4(x() % x, y() % y, z() % z, w() % w);
	}

	@Override
	public DVec4 mod2(double n) {
		return new DRVec4(n % x(), n % y(), n % z(), n % w());
	}

	@Override
	public DVec4 pow(double n) {
		return new DRVec4((double) VecMath.pow(x(), n), (double) VecMath.pow(y(), n), (double) VecMath.pow(z(), n), (double) VecMath.pow(w(), n));
	}

	@Override
	public DVec4 pow(DVec4 v) {
		return new DRVec4((double) VecMath.pow(x(), v.x()), (double) VecMath.pow(y(), v.y()), (double) VecMath.pow(y(), v.z()), (double) VecMath.pow(w(), v.w()));
	}

	public DVec4 pow(double x, double y, double z, double w) {
		return new DRVec4((double) VecMath.pow(x(), x), (double) VecMath.pow(y(), y), (double) VecMath.pow(y(), z), (double) VecMath.pow(w(), w));
	}

	@Override
	public DVec4 pow2(double n) {
		return new DRVec4((double) VecMath.pow(n, x()), (double) VecMath.pow(n, y()), (double) VecMath.pow(n, z()), (double) VecMath.pow(n, w()));
	}

	@Override
	public DVec4 clone() {
		return new DRVec4(x(), y(), z(), w());
	}

	@Override
	protected DVec4 _new() {
		return new DRVec4(0, 0, 0, 0);
	}

	@Override
	public String toString() {
		return "[" + x() + ", " + y() + ", " + z() + ", " + w() + "]";
	}

	@Override
	public BVec4 equals(DVec4 other) {
		return BVec.BVec4(x() == other.x(), y() == other.y(), z() == other.z(), w() == other.w());
	}

	@Override
	public BVec4 notEqual(DVec4 other) {
		return BVec.BVec4(x() != other.x(), y() != other.y(), z() != other.z(), w() != other.w());
	}

	@Override
	public BVec4 bigger(DVec4 other) {
		return BVec.BVec4(x() > other.x(), y() > other.y(), z() > other.z(), w() > other.w());
	}

	@Override
	public BVec4 biggerEqual(DVec4 other) {
		return BVec.BVec4(x() >= other.x(), y() >= other.y(), z() >= other.z(), w() >= other.w());
	}

	@Override
	public BVec4 smaller(DVec4 other) {
		return BVec.BVec4(x() < other.x(), y() < other.y(), z() < other.z(), w() < other.w());
	}

	@Override
	public BVec4 smallerEqual(DVec4 other) {
		return BVec.BVec4(x() <= other.x(), y() <= other.y(), z() <= other.z(), w() <= other.w());
	}
	
	@Override
	protected DVec4 forEach(Func1_D f) {
		double x = f.calc(x());
		double y = f.calc(y());
		double z = f.calc(z());
		double w = f.calc(w());
		return new DRVec4(x, y, z, w);
	}

	@Override
	protected DVec4 forEach(DVec4 v, Func2_D f) {
		double x = f.calc(x(), v.x());
		double y = f.calc(y(), v.y());
		double z = f.calc(z(), v.z());
		double w = f.calc(w(), v.w());
		return new DRVec4(x, y, z, w);
	}

	@Override
	protected DVec4 forEach(double n, Func2_D f) {
		double x = f.calc(x(), n);
		double y = f.calc(y(), n);
		double z = f.calc(z(), n);
		double w = f.calc(w(), n);
		return new DRVec4(x, y, z, w);
	}

	@Override
	protected DVec4 forEach(DVec4 v2, DVec4 v3, Func3_D f) {
		double x = f.calc(x(), v2.x(), v3.x());
		double y = f.calc(y(), v2.y(), v3.y());
		double z = f.calc(z(), v2.z(), v3.z());
		double w = f.calc(w(), v2.w(), v3.w());
		return new DRVec4(x, y, z, w);
	}

	@Override
	protected DVec4 forEach(DVec4 v, double n, Func3_D f) {
		double x = f.calc(x(), v.x(), n);
		double y = f.calc(y(), v.y(), n);
		double z = f.calc(z(), v.z(), n);
		double w = f.calc(w(), v.w(), n);
		return new DRVec4(x, y, z, w);
	}

	@Override
	protected DVec4 forEach(double n1, double n2, Func3_D f) {
		double x = f.calc(x(), n1, n2);
		double y = f.calc(y(), n1, n2);
		double z = f.calc(z(), n1, n2);
		double w = f.calc(w(), n1, n2);
		return new DRVec4(x, y, z, w);
	}

}
