package xor.vecmat.vec.l;

import xor.vecmat.VecMath;
import xor.vecmat.COMP_OPS.Func1_L;
import xor.vecmat.COMP_OPS.Func2_L;
import xor.vecmat.COMP_OPS.Func3_L;
import xor.vecmat.vec.b.BVec;
import xor.vecmat.vec.b.BVec1;

public abstract class LVec1 extends LVec<LVec1, BVec1> {

	public abstract long x();

	public abstract void x(long x);

	@Override
	public int dim() {
		return 1;
	}

	@Override
	public long get(int i) {
		switch (i) {
		case 0:
			return x();
		}
		throw new IndexOutOfBoundsException();
	}

	@Override
	public void set(int i, long v) {
		switch (i) {
		case 0:
			x(v);
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
		default:
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public void setTo(LVec1 v) {
		x(v.x());
	}

	public void setTo(long x) {
		x(x);
	}

	@Override
	public LVec1 add(long n) {
		return new LRVec1(x() + n);
	}

	@Override
	public LVec1 add(LVec1 v) {
		return new LRVec1(x() + v.x());
	}

	@Override
	public LVec1 sub(long n) {
		return new LRVec1(x() - n);
	}

	@Override
	public LVec1 sub(LVec1 v) {
		return new LRVec1(x() - v.x());
	}

	@Override
	public LVec1 sub2(long n) {
		return new LRVec1(n - x());
	}

	@Override
	public LVec1 neg() {
		return new LRVec1(-x());
	}

	@Override
	public LVec1 mul(long n) {
		return new LRVec1(x() * n);
	}

	@Override
	public LVec1 mul(LVec1 v) {
		return new LRVec1(x() * v.x());
	}

	@Override
	public LVec1 div(long n) {
		return new LRVec1(x() / n);
	}

	@Override
	public LVec1 div(LVec1 v) {
		return new LRVec1(x() / v.x());
	}

	@Override
	public LVec1 div2(long n) {
		return new LRVec1(n / x());
	}

	@Override
	public LVec1 mod(long n) {
		return new LRVec1(x() % n);
	}

	@Override
	public LVec1 mod(LVec1 v) {
		return new LRVec1(x() % v.x());
	}

	@Override
	public LVec1 mod2(long n) {
		return new LRVec1(n % x());
	}

	@Override
	public LVec1 pow(long n) {
		return new LRVec1((long) VecMath.pow(x(), n));
	}

	@Override
	public LVec1 pow(LVec1 v) {
		return new LRVec1((long) VecMath.pow(x(), v.x()));
	}

	@Override
	public LVec1 pow2(long n) {
		return new LRVec1((long) VecMath.pow(n, x()));
	}

	@Override
	public LVec1 clone() {
		return new LRVec1(x());
	}

	@Override
	protected LVec1 _new() {
		return new LRVec1(0);
	}

	@Override
	public String toString() {
		return "[" + x() + "]";
	}
	
	@Override
	public BVec1 equals(LVec1 other) {
		return BVec.BVec1(x() == other.x());
	}

	@Override
	public BVec1 notEqual(LVec1 other) {
		return BVec.BVec1(x() != other.x());
	}

	@Override
	public BVec1 bigger(LVec1 other) {
		return BVec.BVec1(x() > other.x());
	}

	@Override
	public BVec1 biggerEqual(LVec1 other) {
		return BVec.BVec1(x() >= other.x());
	}

	@Override
	public BVec1 smaller(LVec1 other) {
		return BVec.BVec1(x() < other.x());
	}

	@Override
	public BVec1 smallerEqual(LVec1 other) {
		return BVec.BVec1(x() <= other.x());
	}

	@Override
	protected LVec1 forEach(Func1_L f) {
		long x = f.calc(x());
		return new LRVec1(x);
	}

	@Override
	protected LVec1 forEach(LVec1 v, Func2_L f) {
		long x = f.calc(x(), v.x());
		return new LRVec1(x);
	}

	@Override
	protected LVec1 forEach(long n, Func2_L f) {
		long x = f.calc(x(), n);
		return new LRVec1(x);
	}

	@Override
	protected LVec1 forEach(LVec1 v2, LVec1 v3, Func3_L f) {
		long x = f.calc(x(), v2.x(), v3.x());
		return new LRVec1(x);
	}

	@Override
	protected LVec1 forEach(LVec1 v, long n, Func3_L f) {
		long x = f.calc(x(), v.x(), n);
		return new LRVec1(x);
	}

	@Override
	protected LVec1 forEach(long n1, long n2, Func3_L f) {
		long x = f.calc(x(), n1, n2);
		return new LRVec1(x);
	}

}
