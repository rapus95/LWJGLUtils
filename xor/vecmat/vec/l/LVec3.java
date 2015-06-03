package xor.vecmat.vec.l;

import xor.vecmat.VecMath;
import xor.vecmat.COMP_OPS.Func1_L;
import xor.vecmat.COMP_OPS.Func2_L;
import xor.vecmat.COMP_OPS.Func3_L;
import xor.vecmat.vec.Vec_cross;
import xor.vecmat.vec.b.BVec;
import xor.vecmat.vec.b.BVec3;

public abstract class LVec3 extends LVec<LVec3, BVec3> implements Vec_cross<LVec3, BVec3, Long> {

	public abstract long x();

	public abstract long y();

	public abstract long z();

	public abstract void x(long x);

	public abstract void y(long y);

	public abstract void z(long y);

	@Override
	public int dim() {
		return 3;
	}

	@Override
	public long get(int i) {
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
	public void set(int i, long v) {
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
	public long get(char c) {
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
	public void set(char c, long v) {
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
	public void setTo(LVec3 v) {
		x(v.x());
		y(v.y());
		z(v.z());
	}

	public void setTo(long x, long y, long z) {
		x(x);
		y(y);
		z(z);
	}

	@Override
	public LVec3 add(long n) {
		return new LRVec3(x() + n, y() + n, z() + n);
	}

	@Override
	public LVec3 add(LVec3 v) {
		return new LRVec3(x() + v.x(), y() + v.y(), z() + v.z());
	}

	public LVec3 add(long x, long y, long z) {
		return new LRVec3(x() + x, y() + y, z() + z);
	}

	@Override
	public LVec3 sub(long n) {
		return new LRVec3(x() - n, y() - n, z() - n);
	}

	@Override
	public LVec3 sub(LVec3 v) {
		return new LRVec3(x() - v.x(), y() - v.y(), z() - v.z());
	}

	public LVec3 sub(long x, long y, long z) {
		return new LRVec3(x() - x, y() - y, z() - z);
	}

	@Override
	public LVec3 sub2(long n) {
		return new LRVec3(n - x(), n - y(), n - z());
	}

	@Override
	public LVec3 neg() {
		return new LRVec3(-x(), -y(), -z());
	}

	@Override
	public LVec3 mul(long n) {
		return new LRVec3(x() * n, y() * n, z() * n);
	}

	@Override
	public LVec3 mul(LVec3 v) {
		return new LRVec3(x() * v.x(), y() * v.y(), z() * v.z());
	}

	public LVec3 mul(long x, long y, long z) {
		return new LRVec3(x() * x, y() * y, z() * z);
	}

	@Override
	public LVec3 div(long n) {
		return new LRVec3(x() / n, y() / n, z() / n);
	}

	@Override
	public LVec3 div(LVec3 v) {
		return new LRVec3(x() / v.x(), y() / v.y(), z() / v.z());
	}

	public LVec3 div(long x, long y, long z) {
		return new LRVec3(x() / x, y() / y, z() / z);
	}

	@Override
	public LVec3 div2(long n) {
		return new LRVec3(n / x(), n / y(), n / z());
	}

	@Override
	public LVec3 mod(long n) {
		return new LRVec3(x() % n, y() % n, z() % n);
	}

	@Override
	public LVec3 mod(LVec3 v) {
		return new LRVec3(x() % v.x(), y() % v.y(), z() % v.z());
	}

	public LVec3 mod(long x, long y, long z) {
		return new LRVec3(x() % x, y() % y, z() % z);
	}

	@Override
	public LVec3 mod2(long n) {
		return new LRVec3(n % x(), n % y(), n % z());
	}

	@Override
	public LVec3 pow(long n) {
		return new LRVec3((long) VecMath.pow(x(), n), (long) VecMath.pow(y(), n), (long) VecMath.pow(z(), n));
	}

	@Override
	public LVec3 pow(LVec3 v) {
		return new LRVec3((long) VecMath.pow(x(), v.x()), (long) VecMath.pow(y(), v.y()), (long) VecMath.pow(y(), v.z()));
	}

	public LVec3 pow(long x, long y, long z) {
		return new LRVec3((long) VecMath.pow(x(), x), (long) VecMath.pow(y(), y), (long) VecMath.pow(y(), z));
	}

	@Override
	public LVec3 pow2(long n) {
		return new LRVec3((long) VecMath.pow(n, x()), (long) VecMath.pow(n, y()), (long) VecMath.pow(n, z()));
	}

	@Override
	public LVec3 clone() {
		return new LRVec3(x(), y(), z());
	}

	@Override
	protected LVec3 _new() {
		return new LRVec3(0, 0, 0);
	}

	@Override
	public String toString() {
		return "[" + x() + ", " + y() + ", " + z() + "]";
	}

	@Override
	public BVec3 equals(LVec3 other) {
		return BVec.BVec3(x() == other.x(), y() == other.y(), z() == other.z());
	}

	@Override
	public BVec3 notEqual(LVec3 other) {
		return BVec.BVec3(x() != other.x(), y() != other.y(), z() != other.z());
	}

	@Override
	public BVec3 bigger(LVec3 other) {
		return BVec.BVec3(x() > other.x(), y() > other.y(), z() > other.z());
	}

	@Override
	public BVec3 biggerEqual(LVec3 other) {
		return BVec.BVec3(x() >= other.x(), y() >= other.y(), z() >= other.z());
	}

	@Override
	public BVec3 smaller(LVec3 other) {
		return BVec.BVec3(x() < other.x(), y() < other.y(), z() < other.z());
	}

	@Override
	public BVec3 smallerEqual(LVec3 other) {
		return BVec.BVec3(x() <= other.x(), y() <= other.y(), z() <= other.z());
	}
	
	public LVec3 cross(LVec3 v) {
		final long x = x();
		final long y = y();
		final long z = z();
		final long vx = v.x();
		final long vy = v.y();
		final long vz = v.z();
		return new LRVec3(y * vz - z * vy, z * vx - x * vz, x * vy - y * vx);
	}

	@Override
	protected LVec3 forEach(Func1_L f) {
		long x = f.calc(x());
		long y = f.calc(y());
		long z = f.calc(z());
		return new LRVec3(x, y, z);
	}

	@Override
	protected LVec3 forEach(LVec3 v, Func2_L f) {
		long x = f.calc(x(), v.x());
		long y = f.calc(y(), v.y());
		long z = f.calc(z(), v.z());
		return new LRVec3(x, y, z);
	}

	@Override
	protected LVec3 forEach(long n, Func2_L f) {
		long x = f.calc(x(), n);
		long y = f.calc(y(), n);
		long z = f.calc(z(), n);
		return new LRVec3(x, y, z);
	}

	@Override
	protected LVec3 forEach(LVec3 v2, LVec3 v3, Func3_L f) {
		long x = f.calc(x(), v2.x(), v3.x());
		long y = f.calc(y(), v2.y(), v3.y());
		long z = f.calc(z(), v2.z(), v3.z());
		return new LRVec3(x, y, z);
	}

	@Override
	protected LVec3 forEach(LVec3 v, long n, Func3_L f) {
		long x = f.calc(x(), v.x(), n);
		long y = f.calc(y(), v.y(), n);
		long z = f.calc(z(), v.z(), n);
		return new LRVec3(x, y, z);
	}

	@Override
	protected LVec3 forEach(long n1, long n2, Func3_L f) {
		long x = f.calc(x(), n1, n2);
		long y = f.calc(y(), n1, n2);
		long z = f.calc(z(), n1, n2);
		return new LRVec3(x, y, z);
	}

}
