package xor.vecmat.vec.l;

import xor.vecmat.VecMath;
import xor.vecmat.COMP_OPS.Func1_L;
import xor.vecmat.COMP_OPS.Func2_L;
import xor.vecmat.COMP_OPS.Func3_L;
import xor.vecmat.vec.b.BVec;
import xor.vecmat.vec.b.BVec2;

public abstract class LVec2 extends LVec<LVec2, BVec2> {

	public abstract long x();

	public abstract long y();

	public abstract void x(long x);

	public abstract void y(long y);

	@Override
	public int dim() {
		return 2;
	}

	@Override
	public long get(int i) {
		switch (i) {
		case 0:
			return x();
		case 1:
			return y();
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
		default:
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public void setTo(LVec2 v) {
		x(v.x());
		y(v.y());
	}

	public void setTo(long x, long y) {
		x(x);
		y(y);
	}

	@Override
	public LVec2 add(long n) {
		return new LRVec2(x() + n, y() + n);
	}

	@Override
	public LVec2 add(LVec2 v) {
		return new LRVec2(x() + v.x(), y() + v.y());
	}

	public LVec2 add(long x, long y) {
		return new LRVec2(x() + x, y() + y);
	}

	@Override
	public LVec2 sub(long n) {
		return new LRVec2(x() - n, y() - n);
	}

	@Override
	public LVec2 sub(LVec2 v) {
		return new LRVec2(x() - v.x(), y() - v.y());
	}

	public LVec2 sub(long x, long y) {
		return new LRVec2(x() - x, y() - y);
	}

	@Override
	public LVec2 sub2(long n) {
		return new LRVec2(n - x(), n - y());
	}

	@Override
	public LVec2 neg() {
		return new LRVec2(-x(), -y());
	}

	@Override
	public LVec2 mul(long n) {
		return new LRVec2(x() * n, y() * n);
	}

	@Override
	public LVec2 mul(LVec2 v) {
		return new LRVec2(x() * v.x(), y() * v.y());
	}

	public LVec2 mul(long x, long y) {
		return new LRVec2(x() * x, y() * y);
	}

	@Override
	public LVec2 div(long n) {
		return new LRVec2(x() / n, y() / n);
	}

	@Override
	public LVec2 div(LVec2 v) {
		return new LRVec2(x() / v.x(), y() / v.y());
	}

	public LVec2 div(long x, long y) {
		return new LRVec2(x() / x, y() / y);
	}

	@Override
	public LVec2 div2(long n) {
		return new LRVec2(n / x(), n / y());
	}

	@Override
	public LVec2 mod(long n) {
		return new LRVec2(x() % n, y() % n);
	}

	@Override
	public LVec2 mod(LVec2 v) {
		return new LRVec2(x() % v.x(), y() % v.y());
	}

	public LVec2 mod(long x, long y) {
		return new LRVec2(x() % x, y() % y);
	}

	@Override
	public LVec2 mod2(long n) {
		return new LRVec2(n % x(), n % y());
	}

	@Override
	public LVec2 pow(long n) {
		return new LRVec2((long) VecMath.pow(x(), n), (long) VecMath.pow(y(), n));
	}

	@Override
	public LVec2 pow(LVec2 v) {
		return new LRVec2((long) VecMath.pow(x(), v.x()), (long) VecMath.pow(y(), v.y()));
	}

	public LVec2 pow(long x, long y) {
		return new LRVec2((long) VecMath.pow(x(), x), (long) VecMath.pow(y(), y));
	}

	@Override
	public LVec2 pow2(long n) {
		return new LRVec2((long) VecMath.pow(n, x()), (long) VecMath.pow(n, y()));
	}

	@Override
	public LVec2 clone() {
		return new LRVec2(x(), y());
	}

	@Override
	protected LVec2 _new() {
		return new LRVec2(0, 0);
	}

	@Override
	public String toString() {
		return "[" + x() + ", " + y() + "]";
	}

	@Override
	public BVec2 equals(LVec2 other) {
		return BVec.BVec2(x() == other.x(), y() == other.y());
	}

	@Override
	public BVec2 notEqual(LVec2 other) {
		return BVec.BVec2(x() != other.x(), y() != other.y());
	}

	@Override
	public BVec2 bigger(LVec2 other) {
		return BVec.BVec2(x() > other.x(), y() > other.y());
	}

	@Override
	public BVec2 biggerEqual(LVec2 other) {
		return BVec.BVec2(x() >= other.x(), y() >= other.y());
	}

	@Override
	public BVec2 smaller(LVec2 other) {
		return BVec.BVec2(x() < other.x(), y() < other.y());
	}

	@Override
	public BVec2 smallerEqual(LVec2 other) {
		return BVec.BVec2(x() <= other.x(), y() <= other.y());
	}
	
	@Override
	protected LVec2 forEach(Func1_L f) {
		long x = f.calc(x());
		long y = f.calc(y());
		return new LRVec2(x, y);
	}

	@Override
	protected LVec2 forEach(LVec2 v, Func2_L f) {
		long x = f.calc(x(), v.x());
		long y = f.calc(y(), v.y());
		return new LRVec2(x, y);
	}

	@Override
	protected LVec2 forEach(long n, Func2_L f) {
		long x = f.calc(x(), n);
		long y = f.calc(y(), n);
		return new LRVec2(x, y);
	}

	@Override
	protected LVec2 forEach(LVec2 v2, LVec2 v3, Func3_L f) {
		long x = f.calc(x(), v2.x(), v3.x());
		long y = f.calc(y(), v2.y(), v3.y());
		return new LRVec2(x, y);
	}

	@Override
	protected LVec2 forEach(LVec2 v, long n, Func3_L f) {
		long x = f.calc(x(), v.x(), n);
		long y = f.calc(y(), v.y(), n);
		return new LRVec2(x, y);
	}

	@Override
	protected LVec2 forEach(long n1, long n2, Func3_L f) {
		long x = f.calc(x(), n1, n2);
		long y = f.calc(y(), n1, n2);
		return new LRVec2(x, y);
	}

}
