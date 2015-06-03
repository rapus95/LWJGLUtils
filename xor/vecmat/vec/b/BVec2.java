package xor.vecmat.vec.b;

import xor.vecmat.COMP_OPS.Func1_B;
import xor.vecmat.COMP_OPS.Func2_B;
import xor.vecmat.COMP_OPS.Func3_B;

public abstract class BVec2 extends BVec<BVec2> {

	public abstract boolean x();

	public abstract boolean y();

	public abstract void x(boolean x);

	public abstract void y(boolean y);

	@Override
	public int dim() {
		return 2;
	}

	@Override
	public boolean get(int i) {
		switch (i) {
		case 0:
			return x();
		case 1:
			return y();
		}
		throw new IndexOutOfBoundsException();
	}

	@Override
	public void set(int i, boolean v) {
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
	public boolean get(char c) {
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
	public void set(char c, boolean v) {
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
	public void setTo(BVec2 v) {
		x(v.x());
		y(v.y());
	}

	public void setTo(boolean x, boolean y) {
		x(x);
		y(y);
	}

	@Override
	public BVec2 or(boolean n) {
		return new BRVec2(x() || n, y() || n);
	}

	@Override
	public BVec2 or(BVec2 v) {
		return new BRVec2(x() || v.x(), y() || v.y());
	}

	public BVec2 or(boolean x, boolean y) {
		return new BRVec2(x() || x, y() || y);
	}

	@Override
	public BVec2 and(boolean n) {
		return new BRVec2(x() && n, y() && n);
	}

	@Override
	public BVec2 and(BVec2 v) {
		return new BRVec2(x() && v.x(), y() && v.y());
	}

	public BVec2 and(boolean x, boolean y) {
		return new BRVec2(x() && x, y() && y);
	}

	@Override
	public BVec2 xor(boolean n) {
		return new BRVec2(x() != n, y() != n);
	}

	@Override
	public BVec2 xor(BVec2 v) {
		return new BRVec2(x() != v.x(), y() != v.y());
	}

	public BVec2 xor(boolean x, boolean y) {
		return new BRVec2(x() != x, y() != y);
	}

	@Override
	public BVec2 clone() {
		return new BRVec2(x(), y());
	}

	@Override
	protected BVec2 _new() {
		return new BRVec2(false, false);
	}

	@Override
	public String toString() {
		return "[" + x() + ", " + y() + "]";
	}

	@Override
	public BVec2 equals(BVec2 other) {
		return BVec2(x() == other.x(), y() == other.y());
	}

	@Override
	public BVec2 notEqual(BVec2 other) {
		return BVec2(x() != other.x(), y() != other.y());
	}
	
	@Override
	protected BVec2 forEach(Func1_B f) {
		boolean x = f.calc(x());
		boolean y = f.calc(y());
		return new BRVec2(x, y);
	}

	@Override
	protected BVec2 forEach(BVec2 v, Func2_B f) {
		boolean x = f.calc(x(), v.x());
		boolean y = f.calc(y(), v.y());
		return new BRVec2(x, y);
	}

	@Override
	protected BVec2 forEach(boolean n, Func2_B f) {
		boolean x = f.calc(x(), n);
		boolean y = f.calc(y(), n);
		return new BRVec2(x, y);
	}

	@Override
	protected BVec2 forEach(BVec2 v2, BVec2 v3, Func3_B f) {
		boolean x = f.calc(x(), v2.x(), v3.x());
		boolean y = f.calc(y(), v2.y(), v3.y());
		return new BRVec2(x, y);
	}

	@Override
	protected BVec2 forEach(BVec2 v, boolean n, Func3_B f) {
		boolean x = f.calc(x(), v.x(), n);
		boolean y = f.calc(y(), v.y(), n);
		return new BRVec2(x, y);
	}

	@Override
	protected BVec2 forEach(boolean n1, boolean n2, Func3_B f) {
		boolean x = f.calc(x(), n1, n2);
		boolean y = f.calc(y(), n1, n2);
		return new BRVec2(x, y);
	}

}
