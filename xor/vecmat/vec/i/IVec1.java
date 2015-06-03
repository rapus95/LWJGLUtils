package xor.vecmat.vec.i;

import xor.vecmat.VecMath;
import xor.vecmat.COMP_OPS.Func1_I;
import xor.vecmat.COMP_OPS.Func2_I;
import xor.vecmat.COMP_OPS.Func3_I;
import xor.vecmat.vec.b.BVec;
import xor.vecmat.vec.b.BVec1;

public abstract class IVec1 extends IVec<IVec1, BVec1> {

	public abstract int x();

	public abstract void x(int x);

	@Override
	public int dim() {
		return 1;
	}

	@Override
	public int get(int i) {
		switch (i) {
		case 0:
			return x();
		}
		throw new IndexOutOfBoundsException();
	}

	@Override
	public void set(int i, int v) {
		switch (i) {
		case 0:
			x(v);
			break;
		default:
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public int get(char c) {
		switch (c) {
		case 'x':
		case 'r':
		case 's':
			return x();
		}
		throw new IndexOutOfBoundsException();
	}

	@Override
	public void set(char c, int v) {
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
	public void setTo(IVec1 v) {
		x(v.x());
	}

	public void setTo(int x) {
		x(x);
	}

	@Override
	public IVec1 add(int n) {
		return new IRVec1(x() + n);
	}

	@Override
	public IVec1 add(IVec1 v) {
		return new IRVec1(x() + v.x());
	}

	@Override
	public IVec1 sub(int n) {
		return new IRVec1(x() - n);
	}

	@Override
	public IVec1 sub(IVec1 v) {
		return new IRVec1(x() - v.x());
	}

	@Override
	public IVec1 sub2(int n) {
		return new IRVec1(n - x());
	}

	@Override
	public IVec1 neg() {
		return new IRVec1(-x());
	}

	@Override
	public IVec1 mul(int n) {
		return new IRVec1(x() * n);
	}

	@Override
	public IVec1 mul(IVec1 v) {
		return new IRVec1(x() * v.x());
	}

	@Override
	public IVec1 div(int n) {
		return new IRVec1(x() / n);
	}

	@Override
	public IVec1 div(IVec1 v) {
		return new IRVec1(x() / v.x());
	}

	@Override
	public IVec1 div2(int n) {
		return new IRVec1(n / x());
	}

	@Override
	public IVec1 mod(int n) {
		return new IRVec1(x() % n);
	}

	@Override
	public IVec1 mod(IVec1 v) {
		return new IRVec1(x() % v.x());
	}

	@Override
	public IVec1 mod2(int n) {
		return new IRVec1(n % x());
	}

	@Override
	public IVec1 pow(int n) {
		return new IRVec1((int) VecMath.pow(x(), n));
	}

	@Override
	public IVec1 pow(IVec1 v) {
		return new IRVec1((int) VecMath.pow(x(), v.x()));
	}

	@Override
	public IVec1 pow2(int n) {
		return new IRVec1((int) VecMath.pow(n, x()));
	}

	@Override
	public IVec1 clone() {
		return new IRVec1(x());
	}

	@Override
	protected IVec1 _new() {
		return new IRVec1(0);
	}

	@Override
	public String toString() {
		return "[" + x() + "]";
	}

	@Override
	public BVec1 equals(IVec1 other) {
		return BVec.BVec1(x() == other.x());
	}

	@Override
	public BVec1 notEqual(IVec1 other) {
		return BVec.BVec1(x() != other.x());
	}

	@Override
	public BVec1 bigger(IVec1 other) {
		return BVec.BVec1(x() > other.x());
	}

	@Override
	public BVec1 biggerEqual(IVec1 other) {
		return BVec.BVec1(x() >= other.x());
	}

	@Override
	public BVec1 smaller(IVec1 other) {
		return BVec.BVec1(x() < other.x());
	}

	@Override
	public BVec1 smallerEqual(IVec1 other) {
		return BVec.BVec1(x() <= other.x());
	}
	
	@Override
	protected IVec1 forEach(Func1_I f) {
		int x = f.calc(x());
		return new IRVec1(x);
	}

	@Override
	protected IVec1 forEach(IVec1 v, Func2_I f) {
		int x = f.calc(x(), v.x());
		return new IRVec1(x);
	}

	@Override
	protected IVec1 forEach(int n, Func2_I f) {
		int x = f.calc(x(), n);
		return new IRVec1(x);
	}

	@Override
	protected IVec1 forEach(IVec1 v2, IVec1 v3, Func3_I f) {
		int x = f.calc(x(), v2.x(), v3.x());
		return new IRVec1(x);
	}

	@Override
	protected IVec1 forEach(IVec1 v, int n, Func3_I f) {
		int x = f.calc(x(), v.x(), n);
		return new IRVec1(x);
	}

	@Override
	protected IVec1 forEach(int n1, int n2, Func3_I f) {
		int x = f.calc(x(), n1, n2);
		return new IRVec1(x);
	}

}
