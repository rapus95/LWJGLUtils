package xor.vecmat.vec.i;

import xor.vecmat.VecMath;
import xor.vecmat.COMP_OPS.Func1_I;
import xor.vecmat.COMP_OPS.Func2_I;
import xor.vecmat.COMP_OPS.Func3_I;
import xor.vecmat.vec.b.BVec;
import xor.vecmat.vec.b.BVec4;

public abstract class IVec4 extends IVec<IVec4, BVec4> {

	public abstract int x();

	public abstract int y();

	public abstract int z();

	public abstract int w();

	public abstract void x(int x);

	public abstract void y(int y);

	public abstract void z(int z);

	public abstract void w(int w);

	@Override
	public int dim() {
		return 4;
	}

	@Override
	public int get(int i) {
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
	public void set(int i, int v) {
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
	public void setTo(IVec4 v) {
		x(v.x());
		y(v.y());
		z(v.z());
		w(v.w());
	}

	public void setTo(int x, int y, int z, int w) {
		x(x);
		y(y);
		z(z);
		w(w);
	}

	@Override
	public IVec4 add(int n) {
		return new IRVec4(x() + n, y() + n, z() + n, w() + n);
	}

	@Override
	public IVec4 add(IVec4 v) {
		return new IRVec4(x() + v.x(), y() + v.y(), z() + v.z(), w() + v.w());
	}

	public IVec4 add(int x, int y, int z, int w) {
		return new IRVec4(x() + x, y() + y, z() + z, w() + w);
	}

	@Override
	public IVec4 sub(int n) {
		return new IRVec4(x() - n, y() - n, z() - n, w() - n);
	}

	@Override
	public IVec4 sub(IVec4 v) {
		return new IRVec4(x() - v.x(), y() - v.y(), z() - v.z(), w() - v.w());
	}

	public IVec4 sub(int x, int y, int z, int w) {
		return new IRVec4(x() - x, y() - y, z() - z, w() - w);
	}

	@Override
	public IVec4 sub2(int n) {
		return new IRVec4(n - x(), n - y(), n - z(), n - w());
	}

	@Override
	public IVec4 neg() {
		return new IRVec4(-x(), -y(), -z(), -w());
	}

	@Override
	public IVec4 mul(int n) {
		return new IRVec4(x() * n, y() * n, z() * n, w() * n);
	}

	@Override
	public IVec4 mul(IVec4 v) {
		return new IRVec4(x() * v.x(), y() * v.y(), z() * v.z(), w() * v.w());
	}

	public IVec4 mul(int x, int y, int z, int w) {
		return new IRVec4(x() * x, y() * y, z() * z, w() * w);
	}

	@Override
	public IVec4 div(int n) {
		return new IRVec4(x() / n, y() / n, z() / n, w() / n);
	}

	@Override
	public IVec4 div(IVec4 v) {
		return new IRVec4(x() / v.x(), y() / v.y(), z() / v.z(), w() / v.w());
	}

	public IVec4 div(int x, int y, int z, int w) {
		return new IRVec4(x() / x, y() / y, z() / z, w() / w);
	}

	@Override
	public IVec4 div2(int n) {
		return new IRVec4(n / x(), n / y(), n / z(), n / w());
	}

	@Override
	public IVec4 mod(int n) {
		return new IRVec4(x() % n, y() % n, z() % n, w() % n);
	}

	@Override
	public IVec4 mod(IVec4 v) {
		return new IRVec4(x() % v.x(), y() % v.y(), z() % v.z(), w() % v.w());
	}

	public IVec4 mod(int x, int y, int z, int w) {
		return new IRVec4(x() % x, y() % y, z() % z, w() % w);
	}

	@Override
	public IVec4 mod2(int n) {
		return new IRVec4(n % x(), n % y(), n % z(), n % w());
	}

	@Override
	public IVec4 pow(int n) {
		return new IRVec4((int) VecMath.pow(x(), n), (int) VecMath.pow(y(), n), (int) VecMath.pow(z(), n), (int) VecMath.pow(w(), n));
	}

	@Override
	public IVec4 pow(IVec4 v) {
		return new IRVec4((int) VecMath.pow(x(), v.x()), (int) VecMath.pow(y(), v.y()), (int) VecMath.pow(y(), v.z()), (int) VecMath.pow(w(), v.w()));
	}

	public IVec4 pow(int x, int y, int z, int w) {
		return new IRVec4((int) VecMath.pow(x(), x), (int) VecMath.pow(y(), y), (int) VecMath.pow(y(), z), (int) VecMath.pow(w(), w));
	}

	@Override
	public IVec4 pow2(int n) {
		return new IRVec4((int) VecMath.pow(n, x()), (int) VecMath.pow(n, y()), (int) VecMath.pow(n, z()), (int) VecMath.pow(n, w()));
	}

	@Override
	public IVec4 clone() {
		return new IRVec4(x(), y(), z(), w());
	}

	@Override
	protected IVec4 _new() {
		return new IRVec4(0, 0, 0, 0);
	}

	@Override
	public String toString() {
		return "[" + x() + ", " + y() + ", " + z() + ", " + w() + "]";
	}

	@Override
	public BVec4 equals(IVec4 other) {
		return BVec.BVec4(x() == other.x(), y() == other.y(), z() == other.z(), w() == other.w());
	}

	@Override
	public BVec4 notEqual(IVec4 other) {
		return BVec.BVec4(x() != other.x(), y() != other.y(), z() != other.z(), w() != other.w());
	}

	@Override
	public BVec4 bigger(IVec4 other) {
		return BVec.BVec4(x() > other.x(), y() > other.y(), z() > other.z(), w() > other.w());
	}

	@Override
	public BVec4 biggerEqual(IVec4 other) {
		return BVec.BVec4(x() >= other.x(), y() >= other.y(), z() >= other.z(), w() >= other.w());
	}

	@Override
	public BVec4 smaller(IVec4 other) {
		return BVec.BVec4(x() < other.x(), y() < other.y(), z() < other.z(), w() < other.w());
	}

	@Override
	public BVec4 smallerEqual(IVec4 other) {
		return BVec.BVec4(x() <= other.x(), y() <= other.y(), z() <= other.z(), w() <= other.w());
	}
	
	@Override
	protected IVec4 forEach(Func1_I f) {
		int x = f.calc(x());
		int y = f.calc(y());
		int z = f.calc(z());
		int w = f.calc(w());
		return new IRVec4(x, y, z, w);
	}

	@Override
	protected IVec4 forEach(IVec4 v, Func2_I f) {
		int x = f.calc(x(), v.x());
		int y = f.calc(y(), v.y());
		int z = f.calc(z(), v.z());
		int w = f.calc(w(), v.w());
		return new IRVec4(x, y, z, w);
	}

	@Override
	protected IVec4 forEach(int n, Func2_I f) {
		int x = f.calc(x(), n);
		int y = f.calc(y(), n);
		int z = f.calc(z(), n);
		int w = f.calc(w(), n);
		return new IRVec4(x, y, z, w);
	}

	@Override
	protected IVec4 forEach(IVec4 v2, IVec4 v3, Func3_I f) {
		int x = f.calc(x(), v2.x(), v3.x());
		int y = f.calc(y(), v2.y(), v3.y());
		int z = f.calc(z(), v2.z(), v3.z());
		int w = f.calc(w(), v2.w(), v3.w());
		return new IRVec4(x, y, z, w);
	}

	@Override
	protected IVec4 forEach(IVec4 v, int n, Func3_I f) {
		int x = f.calc(x(), v.x(), n);
		int y = f.calc(y(), v.y(), n);
		int z = f.calc(z(), v.z(), n);
		int w = f.calc(w(), v.w(), n);
		return new IRVec4(x, y, z, w);
	}

	@Override
	protected IVec4 forEach(int n1, int n2, Func3_I f) {
		int x = f.calc(x(), n1, n2);
		int y = f.calc(y(), n1, n2);
		int z = f.calc(z(), n1, n2);
		int w = f.calc(w(), n1, n2);
		return new IRVec4(x, y, z, w);
	}

}
