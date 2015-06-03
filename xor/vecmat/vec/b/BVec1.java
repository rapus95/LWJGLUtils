package xor.vecmat.vec.b;

import xor.vecmat.COMP_OPS.Func1_B;
import xor.vecmat.COMP_OPS.Func2_B;
import xor.vecmat.COMP_OPS.Func3_B;

public abstract class BVec1 extends BVec<BVec1> {

	public abstract boolean x();

	public abstract void x(boolean x);

	@Override
	public int dim() {
		return 1;
	}

	@Override
	public boolean get(int i) {
		switch (i) {
		case 0:
			return x();
		}
		throw new IndexOutOfBoundsException();
	}

	@Override
	public void set(int i, boolean v) {
		switch (i) {
		case 0:
			x(v);
			break;
		default:
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public boolean get(char c) {
		switch (c) {
		case 'x':
		case 'r':
		case 's':
			return x();
		}
		throw new IndexOutOfBoundsException();
	}

	@Override
	public void set(char c, boolean v) {
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
	public void setTo(BVec1 v) {
		x(v.x());
	}

	public void setTo(boolean x) {
		x(x);
	}

	@Override
	public BVec1 or(boolean n) {
		return new BRVec1(x() || n);
	}

	@Override
	public BVec1 or(BVec1 v) {
		return new BRVec1(x() || v.x());
	}

	@Override
	public BVec1 and(boolean n) {
		return new BRVec1(x() && n);
	}

	@Override
	public BVec1 and(BVec1 v) {
		return new BRVec1(x() && v.x());
	}

	@Override
	public BVec1 xor(boolean n) {
		return new BRVec1(x() != n);
	}

	@Override
	public BVec1 xor(BVec1 v) {
		return new BRVec1(x() != v.x());
	}

	@Override
	public BVec1 clone() {
		return new BRVec1(x());
	}

	@Override
	protected BVec1 _new() {
		return new BRVec1(false);
	}

	@Override
	public String toString() {
		return "[" + x() + "]";
	}

	@Override
	public BVec1 equals(BVec1 other) {
		return BVec1(x() == other.x());
	}

	@Override
	public BVec1 notEqual(BVec1 other) {
		return BVec1(x() != other.x());
	}

	@Override
	protected BVec1 forEach(Func1_B f) {
		boolean x = f.calc(x());
		return new BRVec1(x);
	}

	@Override
	protected BVec1 forEach(BVec1 v, Func2_B f) {
		boolean x = f.calc(x(), v.x());
		return new BRVec1(x);
	}

	@Override
	protected BVec1 forEach(boolean n, Func2_B f) {
		boolean x = f.calc(x(), n);
		return new BRVec1(x);
	}

	@Override
	protected BVec1 forEach(BVec1 v2, BVec1 v3, Func3_B f) {
		boolean x = f.calc(x(), v2.x(), v3.x());
		return new BRVec1(x);
	}

	@Override
	protected BVec1 forEach(BVec1 v, boolean n, Func3_B f) {
		boolean x = f.calc(x(), v.x(), n);
		return new BRVec1(x);
	}

	@Override
	protected BVec1 forEach(boolean n1, boolean n2, Func3_B f) {
		boolean x = f.calc(x(), n1, n2);
		return new BRVec1(x);
	}

}
