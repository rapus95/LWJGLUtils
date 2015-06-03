package xor.vecmat.vec.i;

import xor.vecmat.VecMath;
import xor.vecmat.COMP_OPS.Func1_I;
import xor.vecmat.COMP_OPS.Func2_I;
import xor.vecmat.COMP_OPS.Func3_I;
import xor.vecmat.vec.Vec_cross;
import xor.vecmat.vec.b.BVec;
import xor.vecmat.vec.b.BVec3;

public abstract class IVec3 extends IVec<IVec3, BVec3> implements Vec_cross<IVec3, BVec3, Integer> {

	public abstract int x();

	public abstract int y();

	public abstract int z();

	public abstract void x(int x);

	public abstract void y(int y);

	public abstract void z(int y);

	@Override
	public int dim() {
		return 3;
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
		default:
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public void setTo(IVec3 v) {
		x(v.x());
		y(v.y());
		z(v.z());
	}

	public void setTo(int x, int y, int z) {
		x(x);
		y(y);
		z(z);
	}

	@Override
	public IVec3 add(int n) {
		return new IRVec3(x() + n, y() + n, z() + n);
	}

	@Override
	public IVec3 add(IVec3 v) {
		return new IRVec3(x() + v.x(), y() + v.y(), z() + v.z());
	}

	public IVec3 add(int x, int y, int z) {
		return new IRVec3(x() + x, y() + y, z() + z);
	}

	@Override
	public IVec3 sub(int n) {
		return new IRVec3(x() - n, y() - n, z() - n);
	}

	@Override
	public IVec3 sub(IVec3 v) {
		return new IRVec3(x() - v.x(), y() - v.y(), z() - v.z());
	}

	public IVec3 sub(int x, int y, int z) {
		return new IRVec3(x() - x, y() - y, z() - z);
	}

	@Override
	public IVec3 sub2(int n) {
		return new IRVec3(n - x(), n - y(), n - z());
	}

	@Override
	public IVec3 neg() {
		return new IRVec3(-x(), -y(), -z());
	}

	@Override
	public IVec3 mul(int n) {
		return new IRVec3(x() * n, y() * n, z() * n);
	}

	@Override
	public IVec3 mul(IVec3 v) {
		return new IRVec3(x() * v.x(), y() * v.y(), z() * v.z());
	}

	public IVec3 mul(int x, int y, int z) {
		return new IRVec3(x() * x, y() * y, z() * z);
	}

	@Override
	public IVec3 div(int n) {
		return new IRVec3(x() / n, y() / n, z() / n);
	}

	@Override
	public IVec3 div(IVec3 v) {
		return new IRVec3(x() / v.x(), y() / v.y(), z() / v.z());
	}

	public IVec3 div(int x, int y, int z) {
		return new IRVec3(x() / x, y() / y, z() / z);
	}

	@Override
	public IVec3 div2(int n) {
		return new IRVec3(n / x(), n / y(), n / z());
	}

	@Override
	public IVec3 mod(int n) {
		return new IRVec3(x() % n, y() % n, z() % n);
	}

	@Override
	public IVec3 mod(IVec3 v) {
		return new IRVec3(x() % v.x(), y() % v.y(), z() % v.z());
	}

	public IVec3 mod(int x, int y, int z) {
		return new IRVec3(x() % x, y() % y, z() % z);
	}

	@Override
	public IVec3 mod2(int n) {
		return new IRVec3(n % x(), n % y(), n % z());
	}

	@Override
	public IVec3 pow(int n) {
		return new IRVec3((int) VecMath.pow(x(), n), (int) VecMath.pow(y(), n), (int) VecMath.pow(z(), n));
	}

	@Override
	public IVec3 pow(IVec3 v) {
		return new IRVec3((int) VecMath.pow(x(), v.x()), (int) VecMath.pow(y(), v.y()), (int) VecMath.pow(y(), v.z()));
	}

	public IVec3 pow(int x, int y, int z) {
		return new IRVec3((int) VecMath.pow(x(), x), (int) VecMath.pow(y(), y), (int) VecMath.pow(y(), z));
	}

	@Override
	public IVec3 pow2(int n) {
		return new IRVec3((int) VecMath.pow(n, x()), (int) VecMath.pow(n, y()), (int) VecMath.pow(n, z()));
	}

	@Override
	public IVec3 clone() {
		return new IRVec3(x(), y(), z());
	}

	@Override
	protected IVec3 _new() {
		return new IRVec3(0, 0, 0);
	}

	@Override
	public String toString() {
		return "[" + x() + ", " + y() + ", " + z() + "]";
	}
	
	@Override
	public BVec3 equals(IVec3 other) {
		return BVec.BVec3(x() == other.x(), y() == other.y(), z() == other.z());
	}

	@Override
	public BVec3 notEqual(IVec3 other) {
		return BVec.BVec3(x() != other.x(), y() != other.y(), z() != other.z());
	}

	@Override
	public BVec3 bigger(IVec3 other) {
		return BVec.BVec3(x() > other.x(), y() > other.y(), z() > other.z());
	}

	@Override
	public BVec3 biggerEqual(IVec3 other) {
		return BVec.BVec3(x() >= other.x(), y() >= other.y(), z() >= other.z());
	}

	@Override
	public BVec3 smaller(IVec3 other) {
		return BVec.BVec3(x() < other.x(), y() < other.y(), z() < other.z());
	}

	@Override
	public BVec3 smallerEqual(IVec3 other) {
		return BVec.BVec3(x() <= other.x(), y() <= other.y(), z() <= other.z());
	}

	public IVec3 cross(IVec3 v) {
		final int x = x();
		final int y = y();
		final int z = z();
		final int vx = v.x();
		final int vy = v.y();
		final int vz = v.z();
		return new IRVec3(y * vz - z * vy, z * vx - x * vz, x * vy - y * vx);
	}

	@Override
	protected IVec3 forEach(Func1_I f) {
		int x = f.calc(x());
		int y = f.calc(y());
		int z = f.calc(z());
		return new IRVec3(x, y, z);
	}

	@Override
	protected IVec3 forEach(IVec3 v, Func2_I f) {
		int x = f.calc(x(), v.x());
		int y = f.calc(y(), v.y());
		int z = f.calc(z(), v.z());
		return new IRVec3(x, y, z);
	}

	@Override
	protected IVec3 forEach(int n, Func2_I f) {
		int x = f.calc(x(), n);
		int y = f.calc(y(), n);
		int z = f.calc(z(), n);
		return new IRVec3(x, y, z);
	}

	@Override
	protected IVec3 forEach(IVec3 v2, IVec3 v3, Func3_I f) {
		int x = f.calc(x(), v2.x(), v3.x());
		int y = f.calc(y(), v2.y(), v3.y());
		int z = f.calc(z(), v2.z(), v3.z());
		return new IRVec3(x, y, z);
	}

	@Override
	protected IVec3 forEach(IVec3 v, int n, Func3_I f) {
		int x = f.calc(x(), v.x(), n);
		int y = f.calc(y(), v.y(), n);
		int z = f.calc(z(), v.z(), n);
		return new IRVec3(x, y, z);
	}

	@Override
	protected IVec3 forEach(int n1, int n2, Func3_I f) {
		int x = f.calc(x(), n1, n2);
		int y = f.calc(y(), n1, n2);
		int z = f.calc(z(), n1, n2);
		return new IRVec3(x, y, z);
	}

}
