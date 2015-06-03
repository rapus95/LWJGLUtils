package xor.vecmat.vec.i;

import xor.vecmat.VecMath;
import xor.vecmat.COMP_OPS.Func1_I;
import xor.vecmat.COMP_OPS.Func2_I;
import xor.vecmat.COMP_OPS.Func3_I;
import xor.vecmat.vec.b.BVec;
import xor.vecmat.vec.b.BVec2;

public abstract class IVec2 extends IVec<IVec2, BVec2> {

	public abstract int x();

	public abstract int y();

	public abstract void x(int x);

	public abstract void y(int y);

	@Override
	public int dim() {
		return 2;
	}

	@Override
	public int get(int i) {
		switch (i) {
		case 0:
			return x();
		case 1:
			return y();
		}
		throw new IndexOutOfBoundsException();
	}

	@Override
	public void set(int i, int v) {
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
	public int get(char c) {
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
	public void set(char c, int v) {
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
	public void setTo(IVec2 v) {
		x(v.x());
		y(v.y());
	}

	public void setTo(int x, int y) {
		x(x);
		y(y);
	}

	@Override
	public IVec2 add(int n) {
		return new IRVec2(x() + n, y() + n);
	}

	@Override
	public IVec2 add(IVec2 v) {
		return new IRVec2(x() + v.x(), y() + v.y());
	}

	public IVec2 add(int x, int y) {
		return new IRVec2(x() + x, y() + y);
	}

	@Override
	public IVec2 sub(int n) {
		return new IRVec2(x() - n, y() - n);
	}

	@Override
	public IVec2 sub(IVec2 v) {
		return new IRVec2(x() - v.x(), y() - v.y());
	}

	public IVec2 sub(int x, int y) {
		return new IRVec2(x() - x, y() - y);
	}

	@Override
	public IVec2 sub2(int n) {
		return new IRVec2(n - x(), n - y());
	}

	@Override
	public IVec2 neg() {
		return new IRVec2(-x(), -y());
	}

	@Override
	public IVec2 mul(int n) {
		return new IRVec2(x() * n, y() * n);
	}

	@Override
	public IVec2 mul(IVec2 v) {
		return new IRVec2(x() * v.x(), y() * v.y());
	}

	public IVec2 mul(int x, int y) {
		return new IRVec2(x() * x, y() * y);
	}

	@Override
	public IVec2 div(int n) {
		return new IRVec2(x() / n, y() / n);
	}

	@Override
	public IVec2 div(IVec2 v) {
		return new IRVec2(x() / v.x(), y() / v.y());
	}

	public IVec2 div(int x, int y) {
		return new IRVec2(x() / x, y() / y);
	}

	@Override
	public IVec2 div2(int n) {
		return new IRVec2(n / x(), n / y());
	}

	@Override
	public IVec2 mod(int n) {
		return new IRVec2(x() % n, y() % n);
	}

	@Override
	public IVec2 mod(IVec2 v) {
		return new IRVec2(x() % v.x(), y() % v.y());
	}

	public IVec2 mod(int x, int y) {
		return new IRVec2(x() % x, y() % y);
	}

	@Override
	public IVec2 mod2(int n) {
		return new IRVec2(n % x(), n % y());
	}

	@Override
	public IVec2 pow(int n) {
		return new IRVec2((int) VecMath.pow(x(), n), (int) VecMath.pow(y(), n));
	}

	@Override
	public IVec2 pow(IVec2 v) {
		return new IRVec2((int) VecMath.pow(x(), v.x()), (int) VecMath.pow(y(), v.y()));
	}

	public IVec2 pow(int x, int y) {
		return new IRVec2((int) VecMath.pow(x(), x), (int) VecMath.pow(y(), y));
	}

	@Override
	public IVec2 pow2(int n) {
		return new IRVec2((int) VecMath.pow(n, x()), (int) VecMath.pow(n, y()));
	}

	@Override
	public IVec2 clone() {
		return new IRVec2(x(), y());
	}

	@Override
	protected IVec2 _new() {
		return new IRVec2(0, 0);
	}

	@Override
	public String toString() {
		return "[" + x() + ", " + y() + "]";
	}

	@Override
	public BVec2 equals(IVec2 other) {
		return BVec.BVec2(x() == other.x(), y() == other.y());
	}

	@Override
	public BVec2 notEqual(IVec2 other) {
		return BVec.BVec2(x() != other.x(), y() != other.y());
	}

	@Override
	public BVec2 bigger(IVec2 other) {
		return BVec.BVec2(x() > other.x(), y() > other.y());
	}

	@Override
	public BVec2 biggerEqual(IVec2 other) {
		return BVec.BVec2(x() >= other.x(), y() >= other.y());
	}

	@Override
	public BVec2 smaller(IVec2 other) {
		return BVec.BVec2(x() < other.x(), y() < other.y());
	}

	@Override
	public BVec2 smallerEqual(IVec2 other) {
		return BVec.BVec2(x() <= other.x(), y() <= other.y());
	}
	
	@Override
	protected IVec2 forEach(Func1_I f) {
		int x = f.calc(x());
		int y = f.calc(y());
		return new IRVec2(x, y);
	}

	@Override
	protected IVec2 forEach(IVec2 v, Func2_I f) {
		int x = f.calc(x(), v.x());
		int y = f.calc(y(), v.y());
		return new IRVec2(x, y);
	}

	@Override
	protected IVec2 forEach(int n, Func2_I f) {
		int x = f.calc(x(), n);
		int y = f.calc(y(), n);
		return new IRVec2(x, y);
	}

	@Override
	protected IVec2 forEach(IVec2 v2, IVec2 v3, Func3_I f) {
		int x = f.calc(x(), v2.x(), v3.x());
		int y = f.calc(y(), v2.y(), v3.y());
		return new IRVec2(x, y);
	}

	@Override
	protected IVec2 forEach(IVec2 v, int n, Func3_I f) {
		int x = f.calc(x(), v.x(), n);
		int y = f.calc(y(), v.y(), n);
		return new IRVec2(x, y);
	}

	@Override
	protected IVec2 forEach(int n1, int n2, Func3_I f) {
		int x = f.calc(x(), n1, n2);
		int y = f.calc(y(), n1, n2);
		return new IRVec2(x, y);
	}

}
