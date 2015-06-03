package xor.vecmat.vec.d;

import xor.vecmat.VecMath;
import xor.vecmat.COMP_OPS.Func1_D;
import xor.vecmat.COMP_OPS.Func2_D;
import xor.vecmat.COMP_OPS.Func3_D;
import xor.vecmat.vec.Vec_cross;
import xor.vecmat.vec.b.BVec;
import xor.vecmat.vec.b.BVec3;

public abstract class DVec3 extends DVec<DVec3, BVec3> implements Vec_cross<DVec3, BVec3, Double> {

	public abstract double x();

	public abstract double y();

	public abstract double z();

	public abstract void x(double x);

	public abstract void y(double y);

	public abstract void z(double y);

	@Override
	public int dim() {
		return 3;
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
		default:
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public void setTo(DVec3 v) {
		x(v.x());
		y(v.y());
		z(v.z());
	}

	public void setTo(double x, double y, double z) {
		x(x);
		y(y);
		z(z);
	}

	@Override
	public DVec3 add(double n) {
		return new DRVec3(x() + n, y() + n, z() + n);
	}

	@Override
	public DVec3 add(DVec3 v) {
		return new DRVec3(x() + v.x(), y() + v.y(), z() + v.z());
	}

	public DVec3 add(double x, double y, double z) {
		return new DRVec3(x() + x, y() + y, z() + z);
	}

	@Override
	public DVec3 sub(double n) {
		return new DRVec3(x() - n, y() - n, z() - n);
	}

	@Override
	public DVec3 sub(DVec3 v) {
		return new DRVec3(x() - v.x(), y() - v.y(), z() - v.z());
	}

	public DVec3 sub(double x, double y, double z) {
		return new DRVec3(x() - x, y() - y, z() - z);
	}

	@Override
	public DVec3 sub2(double n) {
		return new DRVec3(n - x(), n - y(), n - z());
	}

	@Override
	public DVec3 neg() {
		return new DRVec3(-x(), -y(), -z());
	}

	@Override
	public DVec3 mul(double n) {
		return new DRVec3(x() * n, y() * n, z() * n);
	}

	@Override
	public DVec3 mul(DVec3 v) {
		return new DRVec3(x() * v.x(), y() * v.y(), z() * v.z());
	}

	public DVec3 mul(double x, double y, double z) {
		return new DRVec3(x() * x, y() * y, z() * z);
	}

	@Override
	public DVec3 div(double n) {
		return new DRVec3(x() / n, y() / n, z() / n);
	}

	@Override
	public DVec3 div(DVec3 v) {
		return new DRVec3(x() / v.x(), y() / v.y(), z() / v.z());
	}

	public DVec3 div(double x, double y, double z) {
		return new DRVec3(x() / x, y() / y, z() / z);
	}

	@Override
	public DVec3 div2(double n) {
		return new DRVec3(n / x(), n / y(), n / z());
	}

	@Override
	public DVec3 mod(double n) {
		return new DRVec3(x() % n, y() % n, z() % n);
	}

	@Override
	public DVec3 mod(DVec3 v) {
		return new DRVec3(x() % v.x(), y() % v.y(), z() % v.z());
	}

	public DVec3 mod(double x, double y, double z) {
		return new DRVec3(x() % x, y() % y, z() % z);
	}

	@Override
	public DVec3 mod2(double n) {
		return new DRVec3(n % x(), n % y(), n % z());
	}

	@Override
	public DVec3 pow(double n) {
		return new DRVec3((double) VecMath.pow(x(), n), (double) VecMath.pow(y(), n), (double) VecMath.pow(z(), n));
	}

	@Override
	public DVec3 pow(DVec3 v) {
		return new DRVec3((double) VecMath.pow(x(), v.x()), (double) VecMath.pow(y(), v.y()), (double) VecMath.pow(y(), v.z()));
	}

	public DVec3 pow(double x, double y, double z) {
		return new DRVec3((double) VecMath.pow(x(), x), (double) VecMath.pow(y(), y), (double) VecMath.pow(y(), z));
	}

	@Override
	public DVec3 pow2(double n) {
		return new DRVec3((double) VecMath.pow(n, x()), (double) VecMath.pow(n, y()), (double) VecMath.pow(n, z()));
	}

	@Override
	public DVec3 clone() {
		return new DRVec3(x(), y(), z());
	}

	@Override
	protected DVec3 _new() {
		return new DRVec3(0, 0, 0);
	}

	@Override
	public String toString() {
		return "[" + x() + ", " + y() + ", " + z() + "]";
	}

	@Override
	public BVec3 equals(DVec3 other) {
		return BVec.BVec3(x() == other.x(), y() == other.y(), z() == other.z());
	}

	@Override
	public BVec3 notEqual(DVec3 other) {
		return BVec.BVec3(x() != other.x(), y() != other.y(), z() != other.z());
	}

	@Override
	public BVec3 bigger(DVec3 other) {
		return BVec.BVec3(x() > other.x(), y() > other.y(), z() > other.z());
	}

	@Override
	public BVec3 biggerEqual(DVec3 other) {
		return BVec.BVec3(x() >= other.x(), y() >= other.y(), z() >= other.z());
	}

	@Override
	public BVec3 smaller(DVec3 other) {
		return BVec.BVec3(x() < other.x(), y() < other.y(), z() < other.z());
	}

	@Override
	public BVec3 smallerEqual(DVec3 other) {
		return BVec.BVec3(x() <= other.x(), y() <= other.y(), z() <= other.z());
	}
	
	public DVec3 cross(DVec3 v) {
		final double x = x();
		final double y = y();
		final double z = z();
		final double vx = v.x();
		final double vy = v.y();
		final double vz = v.z();
		return new DRVec3(y * vz - z * vy, z * vx - x * vz, x * vy - y * vx);
	}

	@Override
	protected DVec3 forEach(Func1_D f) {
		double x = f.calc(x());
		double y = f.calc(y());
		double z = f.calc(z());
		return new DRVec3(x, y, z);
	}

	@Override
	protected DVec3 forEach(DVec3 v, Func2_D f) {
		double x = f.calc(x(), v.x());
		double y = f.calc(y(), v.y());
		double z = f.calc(z(), v.z());
		return new DRVec3(x, y, z);
	}

	@Override
	protected DVec3 forEach(double n, Func2_D f) {
		double x = f.calc(x(), n);
		double y = f.calc(y(), n);
		double z = f.calc(z(), n);
		return new DRVec3(x, y, z);
	}

	@Override
	protected DVec3 forEach(DVec3 v2, DVec3 v3, Func3_D f) {
		double x = f.calc(x(), v2.x(), v3.x());
		double y = f.calc(y(), v2.y(), v3.y());
		double z = f.calc(z(), v2.z(), v3.z());
		return new DRVec3(x, y, z);
	}

	@Override
	protected DVec3 forEach(DVec3 v, double n, Func3_D f) {
		double x = f.calc(x(), v.x(), n);
		double y = f.calc(y(), v.y(), n);
		double z = f.calc(z(), v.z(), n);
		return new DRVec3(x, y, z);
	}

	@Override
	protected DVec3 forEach(double n1, double n2, Func3_D f) {
		double x = f.calc(x(), n1, n2);
		double y = f.calc(y(), n1, n2);
		double z = f.calc(z(), n1, n2);
		return new DRVec3(x, y, z);
	}

}
