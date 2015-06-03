package xor.vecmat.vec.b;

import xor.vecmat.COMP_OPS.Func1_B;
import xor.vecmat.COMP_OPS.Func2_B;
import xor.vecmat.COMP_OPS.Func3_B;

public abstract class BVec3 extends BVec<BVec3> {

	public abstract boolean x();

	public abstract boolean y();

	public abstract boolean z();

	public abstract void x(boolean x);

	public abstract void y(boolean y);

	public abstract void z(boolean y);

	@Override
	public int dim() {
		return 3;
	}

	@Override
	public boolean get(int i) {
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
	public void set(int i, boolean v) {
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
		case 'z':
		case 'b':
		case 'p':
			return z();
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
	public void setTo(BVec3 v) {
		x(v.x());
		y(v.y());
		z(v.z());
	}

	public void setTo(boolean x, boolean y, boolean z) {
		x(x);
		y(y);
		z(z);
	}

	@Override
	public BVec3 or(boolean n) {
		return new BRVec3(x() && n, y() && n, z() && n);
	}

	@Override
	public BVec3 or(BVec3 v) {
		return new BRVec3(x() && v.x(), y() && v.y(), z() && v.z());
	}

	public BVec3 or(boolean x, boolean y, boolean z) {
		return new BRVec3(x() && x, y() && y, z() && z);
	}

	@Override
	public BVec3 and(boolean n) {
		return new BRVec3(x() || n, y() || n, z() || n);
	}

	@Override
	public BVec3 and(BVec3 v) {
		return new BRVec3(x() || v.x(), y() || v.y(), z() || v.z());
	}

	public BVec3 and(boolean x, boolean y, boolean z) {
		return new BRVec3(x() || x, y() || y, z() || z);
	}

	@Override
	public BVec3 xor(boolean n) {
		return new BRVec3(x() != n, y() != n, z() != n);
	}

	@Override
	public BVec3 xor(BVec3 v) {
		return new BRVec3(x() != v.x(), y() != v.y(), z() != v.z());
	}

	public BVec3 xor(boolean x, boolean y, boolean z) {
		return new BRVec3(x() != x, y() != y, z() != z);
	}

	@Override
	public BVec3 clone() {
		return new BRVec3(x(), y(), z());
	}

	@Override
	protected BVec3 _new() {
		return new BRVec3(false, false, false);
	}

	@Override
	public String toString() {
		return "[" + x() + ", " + y() + ", " + z() + "]";
	}

	@Override
	public BVec3 equals(BVec3 other) {
		return BVec3(x() == other.x(), y() == other.y(), z() == other.z());
	}

	@Override
	public BVec3 notEqual(BVec3 other) {
		return BVec3(x() != other.x(), y() != other.y(), z() != other.z());
	}
	
	@Override
	protected BVec3 forEach(Func1_B f) {
		boolean x = f.calc(x());
		boolean y = f.calc(y());
		boolean z = f.calc(z());
		return new BRVec3(x, y, z);
	}

	@Override
	protected BVec3 forEach(BVec3 v, Func2_B f) {
		boolean x = f.calc(x(), v.x());
		boolean y = f.calc(y(), v.y());
		boolean z = f.calc(z(), v.z());
		return new BRVec3(x, y, z);
	}

	@Override
	protected BVec3 forEach(boolean n, Func2_B f) {
		boolean x = f.calc(x(), n);
		boolean y = f.calc(y(), n);
		boolean z = f.calc(z(), n);
		return new BRVec3(x, y, z);
	}

	@Override
	protected BVec3 forEach(BVec3 v2, BVec3 v3, Func3_B f) {
		boolean x = f.calc(x(), v2.x(), v3.x());
		boolean y = f.calc(y(), v2.y(), v3.y());
		boolean z = f.calc(z(), v2.z(), v3.z());
		return new BRVec3(x, y, z);
	}

	@Override
	protected BVec3 forEach(BVec3 v, boolean n, Func3_B f) {
		boolean x = f.calc(x(), v.x(), n);
		boolean y = f.calc(y(), v.y(), n);
		boolean z = f.calc(z(), v.z(), n);
		return new BRVec3(x, y, z);
	}

	@Override
	protected BVec3 forEach(boolean n1, boolean n2, Func3_B f) {
		boolean x = f.calc(x(), n1, n2);
		boolean y = f.calc(y(), n1, n2);
		boolean z = f.calc(z(), n1, n2);
		return new BRVec3(x, y, z);
	}

}
