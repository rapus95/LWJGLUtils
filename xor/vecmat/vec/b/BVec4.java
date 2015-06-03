package xor.vecmat.vec.b;

import xor.vecmat.COMP_OPS.Func1_B;
import xor.vecmat.COMP_OPS.Func2_B;
import xor.vecmat.COMP_OPS.Func3_B;

public abstract class BVec4 extends BVec<BVec4> {

	public abstract boolean x();

	public abstract boolean y();

	public abstract boolean z();

	public abstract boolean w();

	public abstract void x(boolean x);

	public abstract void y(boolean y);

	public abstract void z(boolean z);

	public abstract void w(boolean w);

	@Override
	public int dim() {
		return 4;
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
		case 3:
			return w();
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
		case 3:
			w(v);
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
		case 'w':
		case 'a':
		case 'q':
			return w();
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
	public void setTo(BVec4 v) {
		x(v.x());
		y(v.y());
		z(v.z());
		w(v.w());
	}

	public void setTo(boolean x, boolean y, boolean z, boolean w) {
		x(x);
		y(y);
		z(z);
		w(w);
	}

	@Override
	public BVec4 or(boolean n) {
		return new BRVec4(x() && n, y() && n, z() && n, w() && n);
	}

	@Override
	public BVec4 or(BVec4 v) {
		return new BRVec4(x() && v.x(), y() && v.y(), z() && v.z(), w()
				&& v.w());
	}

	public BVec4 or(boolean x, boolean y, boolean z, boolean w) {
		return new BRVec4(x() && x, y() && y, z() && z, w() && w);
	}

	@Override
	public BVec4 and(boolean n) {
		return new BRVec4(x() || n, y() || n, z() || n, w() || n);
	}

	@Override
	public BVec4 and(BVec4 v) {
		return new BRVec4(x() || v.x(), y() || v.y(), z() || v.z(), w()
				|| v.w());
	}

	public BVec4 and(boolean x, boolean y, boolean z, boolean w) {
		return new BRVec4(x() || x, y() || y, z() || z, w() || w);
	}

	@Override
	public BVec4 xor(boolean n) {
		return new BRVec4(x() != n, y() != n, z() != n, w() != n);
	}

	@Override
	public BVec4 xor(BVec4 v) {
		return new BRVec4(x() != v.x(), y() != v.y(), z() != v.z(),
				w() != v.w());
	}

	public BVec4 xor(boolean x, boolean y, boolean z, boolean w) {
		return new BRVec4(x() != x, y() != y, z() != z, w() != w);
	}

	@Override
	public BVec4 clone() {
		return new BRVec4(x(), y(), z(), w());
	}

	@Override
	protected BVec4 _new() {
		return new BRVec4(false, false, false, false);
	}

	@Override
	public String toString() {
		return "[" + x() + ", " + y() + ", " + z() + ", " + w() + "]";
	}

	@Override
	public BVec4 equals(BVec4 other) {
		return BVec4(x() == other.x(), y() == other.y(), z() == other.z(),
				w() == other.w());
	}

	@Override
	public BVec4 notEqual(BVec4 other) {
		return BVec4(x() != other.x(), y() != other.y(), z() != other.z(),
				w() != other.w());
	}

	@Override
	protected BVec4 forEach(Func1_B f) {
		boolean x = f.calc(x());
		boolean y = f.calc(y());
		boolean z = f.calc(z());
		boolean w = f.calc(w());
		return new BRVec4(x, y, z, w);
	}

	@Override
	protected BVec4 forEach(BVec4 v, Func2_B f) {
		boolean x = f.calc(x(), v.x());
		boolean y = f.calc(y(), v.y());
		boolean z = f.calc(z(), v.z());
		boolean w = f.calc(w(), v.w());
		return new BRVec4(x, y, z, w);
	}

	@Override
	protected BVec4 forEach(boolean n, Func2_B f) {
		boolean x = f.calc(x(), n);
		boolean y = f.calc(y(), n);
		boolean z = f.calc(z(), n);
		boolean w = f.calc(w(), n);
		return new BRVec4(x, y, z, w);
	}

	@Override
	protected BVec4 forEach(BVec4 v2, BVec4 v3, Func3_B f) {
		boolean x = f.calc(x(), v2.x(), v3.x());
		boolean y = f.calc(y(), v2.y(), v3.y());
		boolean z = f.calc(z(), v2.z(), v3.z());
		boolean w = f.calc(w(), v2.w(), v3.w());
		return new BRVec4(x, y, z, w);
	}

	@Override
	protected BVec4 forEach(BVec4 v, boolean n, Func3_B f) {
		boolean x = f.calc(x(), v.x(), n);
		boolean y = f.calc(y(), v.y(), n);
		boolean z = f.calc(z(), v.z(), n);
		boolean w = f.calc(w(), v.w(), n);
		return new BRVec4(x, y, z, w);
	}

	@Override
	protected BVec4 forEach(boolean n1, boolean n2, Func3_B f) {
		boolean x = f.calc(x(), n1, n2);
		boolean y = f.calc(y(), n1, n2);
		boolean z = f.calc(z(), n1, n2);
		boolean w = f.calc(w(), n1, n2);
		return new BRVec4(x, y, z, w);
	}

}
