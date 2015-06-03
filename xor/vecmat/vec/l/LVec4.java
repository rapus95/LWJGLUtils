package xor.vecmat.vec.l;

import xor.vecmat.VecMath;
import xor.vecmat.COMP_OPS.Func1_L;
import xor.vecmat.COMP_OPS.Func2_L;
import xor.vecmat.COMP_OPS.Func3_L;
import xor.vecmat.vec.b.BVec;
import xor.vecmat.vec.b.BVec4;

public abstract class LVec4 extends LVec<LVec4, BVec4> {

	public abstract long x();

	public abstract long y();

	public abstract long z();

	public abstract long w();

	public abstract void x(long x);

	public abstract void y(long y);

	public abstract void z(long z);

	public abstract void w(long w);

	@Override
	public int dim() {
		return 4;
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
		case 3:
			return w();
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
		case 3:
			w(v);
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
		case 'w':
		case 'a':
		case 'q':
			return w();
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
	public void setTo(LVec4 v) {
		x(v.x());
		y(v.y());
		z(v.z());
		w(v.w());
	}

	public void setTo(long x, long y, long z, long w) {
		x(x);
		y(y);
		z(z);
		w(w);
	}

	@Override
	public LVec4 add(long n) {
		return new LRVec4(x() + n, y() + n, z() + n, w() + n);
	}

	@Override
	public LVec4 add(LVec4 v) {
		return new LRVec4(x() + v.x(), y() + v.y(), z() + v.z(), w() + v.w());
	}

	public LVec4 add(long x, long y, long z, long w) {
		return new LRVec4(x() + x, y() + y, z() + z, w() + w);
	}

	@Override
	public LVec4 sub(long n) {
		return new LRVec4(x() - n, y() - n, z() - n, w() - n);
	}

	@Override
	public LVec4 sub(LVec4 v) {
		return new LRVec4(x() - v.x(), y() - v.y(), z() - v.z(), w() - v.w());
	}

	public LVec4 sub(long x, long y, long z, long w) {
		return new LRVec4(x() - x, y() - y, z() - z, w() - w);
	}

	@Override
	public LVec4 sub2(long n) {
		return new LRVec4(n - x(), n - y(), n - z(), n - w());
	}

	@Override
	public LVec4 neg() {
		return new LRVec4(-x(), -y(), -z(), -w());
	}

	@Override
	public LVec4 mul(long n) {
		return new LRVec4(x() * n, y() * n, z() * n, w() * n);
	}

	@Override
	public LVec4 mul(LVec4 v) {
		return new LRVec4(x() * v.x(), y() * v.y(), z() * v.z(), w() * v.w());
	}

	public LVec4 mul(long x, long y, long z, long w) {
		return new LRVec4(x() * x, y() * y, z() * z, w() * w);
	}

	@Override
	public LVec4 div(long n) {
		return new LRVec4(x() / n, y() / n, z() / n, w() / n);
	}

	@Override
	public LVec4 div(LVec4 v) {
		return new LRVec4(x() / v.x(), y() / v.y(), z() / v.z(), w() / v.w());
	}

	public LVec4 div(long x, long y, long z, long w) {
		return new LRVec4(x() / x, y() / y, z() / z, w() / w);
	}

	@Override
	public LVec4 div2(long n) {
		return new LRVec4(n / x(), n / y(), n / z(), n / w());
	}

	@Override
	public LVec4 mod(long n) {
		return new LRVec4(x() % n, y() % n, z() % n, w() % n);
	}

	@Override
	public LVec4 mod(LVec4 v) {
		return new LRVec4(x() % v.x(), y() % v.y(), z() % v.z(), w() % v.w());
	}

	public LVec4 mod(long x, long y, long z, long w) {
		return new LRVec4(x() % x, y() % y, z() % z, w() % w);
	}

	@Override
	public LVec4 mod2(long n) {
		return new LRVec4(n % x(), n % y(), n % z(), n % w());
	}

	@Override
	public LVec4 pow(long n) {
		return new LRVec4((long) VecMath.pow(x(), n), (long) VecMath.pow(y(), n), (long) VecMath.pow(z(), n), (long) VecMath.pow(w(), n));
	}

	@Override
	public LVec4 pow(LVec4 v) {
		return new LRVec4((long) VecMath.pow(x(), v.x()), (long) VecMath.pow(y(), v.y()), (long) VecMath.pow(y(), v.z()), (long) VecMath.pow(w(), v.w()));
	}

	public LVec4 pow(long x, long y, long z, long w) {
		return new LRVec4((long) VecMath.pow(x(), x), (long) VecMath.pow(y(), y), (long) VecMath.pow(y(), z), (long) VecMath.pow(w(), w));
	}

	@Override
	public LVec4 pow2(long n) {
		return new LRVec4((long) VecMath.pow(n, x()), (long) VecMath.pow(n, y()), (long) VecMath.pow(n, z()), (long) VecMath.pow(n, w()));
	}

	@Override
	public LVec4 clone() {
		return new LRVec4(x(), y(), z(), w());
	}

	@Override
	protected LVec4 _new() {
		return new LRVec4(0, 0, 0, 0);
	}

	@Override
	public String toString() {
		return "[" + x() + ", " + y() + ", " + z() + ", " + w() + "]";
	}

	@Override
	public BVec4 equals(LVec4 other) {
		return BVec.BVec4(x() == other.x(), y() == other.y(), z() == other.z(), w() == other.w());
	}

	@Override
	public BVec4 notEqual(LVec4 other) {
		return BVec.BVec4(x() != other.x(), y() != other.y(), z() != other.z(), w() != other.w());
	}

	@Override
	public BVec4 bigger(LVec4 other) {
		return BVec.BVec4(x() > other.x(), y() > other.y(), z() > other.z(), w() > other.w());
	}

	@Override
	public BVec4 biggerEqual(LVec4 other) {
		return BVec.BVec4(x() >= other.x(), y() >= other.y(), z() >= other.z(), w() >= other.w());
	}

	@Override
	public BVec4 smaller(LVec4 other) {
		return BVec.BVec4(x() < other.x(), y() < other.y(), z() < other.z(), w() < other.w());
	}

	@Override
	public BVec4 smallerEqual(LVec4 other) {
		return BVec.BVec4(x() <= other.x(), y() <= other.y(), z() <= other.z(), w() <= other.w());
	}
	
	@Override
	protected LVec4 forEach(Func1_L f) {
		long x = f.calc(x());
		long y = f.calc(y());
		long z = f.calc(z());
		long w = f.calc(w());
		return new LRVec4(x, y, z, w);
	}

	@Override
	protected LVec4 forEach(LVec4 v, Func2_L f) {
		long x = f.calc(x(), v.x());
		long y = f.calc(y(), v.y());
		long z = f.calc(z(), v.z());
		long w = f.calc(w(), v.w());
		return new LRVec4(x, y, z, w);
	}

	@Override
	protected LVec4 forEach(long n, Func2_L f) {
		long x = f.calc(x(), n);
		long y = f.calc(y(), n);
		long z = f.calc(z(), n);
		long w = f.calc(w(), n);
		return new LRVec4(x, y, z, w);
	}

	@Override
	protected LVec4 forEach(LVec4 v2, LVec4 v3, Func3_L f) {
		long x = f.calc(x(), v2.x(), v3.x());
		long y = f.calc(y(), v2.y(), v3.y());
		long z = f.calc(z(), v2.z(), v3.z());
		long w = f.calc(w(), v2.w(), v3.w());
		return new LRVec4(x, y, z, w);
	}

	@Override
	protected LVec4 forEach(LVec4 v, long n, Func3_L f) {
		long x = f.calc(x(), v.x(), n);
		long y = f.calc(y(), v.y(), n);
		long z = f.calc(z(), v.z(), n);
		long w = f.calc(w(), v.w(), n);
		return new LRVec4(x, y, z, w);
	}

	@Override
	protected LVec4 forEach(long n1, long n2, Func3_L f) {
		long x = f.calc(x(), n1, n2);
		long y = f.calc(y(), n1, n2);
		long z = f.calc(z(), n1, n2);
		long w = f.calc(w(), n1, n2);
		return new LRVec4(x, y, z, w);
	}

}
