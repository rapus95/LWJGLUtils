package xor.vecmat.vec.f;

import xor.vecmat.VecMath;
import xor.vecmat.COMP_OPS.Func1_F;
import xor.vecmat.COMP_OPS.Func2_F;
import xor.vecmat.COMP_OPS.Func3_F;
import xor.vecmat.vec.b.BVec;
import xor.vecmat.vec.b.BVec4;

public abstract class Vec4 extends Vec<Vec4, BVec4> {

	public abstract float x();

	public abstract float y();

	public abstract float z();

	public abstract float w();

	public abstract void x(float x);

	public abstract void y(float y);

	public abstract void z(float z);

	public abstract void w(float w);

	@Override
	public int dim() {
		return 4;
	}

	@Override
	public float get(int i) {
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
	public void set(int i, float v) {
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
	public float get(char c) {
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
	public void set(char c, float v) {
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
	public void setTo(Vec4 v) {
		x(v.x());
		y(v.y());
		z(v.z());
		w(v.w());
	}

	public void setTo(float x, float y, float z, float w) {
		x(x);
		y(y);
		z(z);
		w(w);
	}

	@Override
	public Vec4 add(float n) {
		return new RVec4(x() + n, y() + n, z() + n, w() + n);
	}

	@Override
	public Vec4 add(Vec4 v) {
		return new RVec4(x() + v.x(), y() + v.y(), z() + v.z(), w() + v.w());
	}

	public Vec4 add(float x, float y, float z, float w) {
		return new RVec4(x() + x, y() + y, z() + z, w() + w);
	}

	@Override
	public Vec4 sub(float n) {
		return new RVec4(x() - n, y() - n, z() - n, w() - n);
	}

	@Override
	public Vec4 sub(Vec4 v) {
		return new RVec4(x() - v.x(), y() - v.y(), z() - v.z(), w() - v.w());
	}

	public Vec4 sub(float x, float y, float z, float w) {
		return new RVec4(x() - x, y() - y, z() - z, w() - w);
	}

	@Override
	public Vec4 sub2(float n) {
		return new RVec4(n - x(), n - y(), n - z(), n - w());
	}

	@Override
	public Vec4 neg() {
		return new RVec4(-x(), -y(), -z(), -w());
	}

	@Override
	public Vec4 mul(float n) {
		return new RVec4(x() * n, y() * n, z() * n, w() * n);
	}

	@Override
	public Vec4 mul(Vec4 v) {
		return new RVec4(x() * v.x(), y() * v.y(), z() * v.z(), w() * v.w());
	}

	public Vec4 mul(float x, float y, float z, float w) {
		return new RVec4(x() * x, y() * y, z() * z, w() * w);
	}

	@Override
	public Vec4 div(float n) {
		return new RVec4(x() / n, y() / n, z() / n, w() / n);
	}

	@Override
	public Vec4 div(Vec4 v) {
		return new RVec4(x() / v.x(), y() / v.y(), z() / v.z(), w() / v.w());
	}

	public Vec4 div(float x, float y, float z, float w) {
		return new RVec4(x() / x, y() / y, z() / z, w() / w);
	}

	@Override
	public Vec4 div2(float n) {
		return new RVec4(n / x(), n / y(), n / z(), n / w());
	}

	@Override
	public Vec4 mod(float n) {
		return new RVec4(x() % n, y() % n, z() % n, w() % n);
	}

	@Override
	public Vec4 mod(Vec4 v) {
		return new RVec4(x() % v.x(), y() % v.y(), z() % v.z(), w() % v.w());
	}

	public Vec4 mod(float x, float y, float z, float w) {
		return new RVec4(x() % x, y() % y, z() % z, w() % w);
	}

	@Override
	public Vec4 mod2(float n) {
		return new RVec4(n % x(), n % y(), n % z(), n % w());
	}

	@Override
	public Vec4 pow(float n) {
		return new RVec4((float) VecMath.pow(x(), n), (float) VecMath.pow(y(), n), (float) VecMath.pow(z(), n), (float) VecMath.pow(w(), n));
	}

	@Override
	public Vec4 pow(Vec4 v) {
		return new RVec4((float) VecMath.pow(x(), v.x()), (float) VecMath.pow(y(), v.y()), (float) VecMath.pow(y(), v.z()), (float) VecMath.pow(w(), v.w()));
	}

	public Vec4 pow(float x, float y, float z, float w) {
		return new RVec4((float) VecMath.pow(x(), x), (float) VecMath.pow(y(), y), (float) VecMath.pow(y(), z), (float) VecMath.pow(w(), w));
	}

	@Override
	public Vec4 pow2(float n) {
		return new RVec4((float) VecMath.pow(n, x()), (float) VecMath.pow(n, y()), (float) VecMath.pow(n, z()), (float) VecMath.pow(n, w()));
	}

	@Override
	public Vec4 clone() {
		return new RVec4(x(), y(), z(), w());
	}

	@Override
	protected Vec4 _new() {
		return new RVec4(0, 0, 0, 0);
	}

	@Override
	public String toString() {
		return "[" + x() + ", " + y() + ", " + z() + ", " + w() + "]";
	}
	
	@Override
	public BVec4 equals(Vec4 other) {
		return BVec.BVec4(x() == other.x(), y() == other.y(), z() == other.z(), w() == other.w());
	}

	@Override
	public BVec4 notEqual(Vec4 other) {
		return BVec.BVec4(x() != other.x(), y() != other.y(), z() != other.z(), w() != other.w());
	}

	@Override
	public BVec4 bigger(Vec4 other) {
		return BVec.BVec4(x() > other.x(), y() > other.y(), z() > other.z(), w() > other.w());
	}

	@Override
	public BVec4 biggerEqual(Vec4 other) {
		return BVec.BVec4(x() >= other.x(), y() >= other.y(), z() >= other.z(), w() >= other.w());
	}

	@Override
	public BVec4 smaller(Vec4 other) {
		return BVec.BVec4(x() < other.x(), y() < other.y(), z() < other.z(), w() < other.w());
	}

	@Override
	public BVec4 smallerEqual(Vec4 other) {
		return BVec.BVec4(x() <= other.x(), y() <= other.y(), z() <= other.z(), w() <= other.w());
	}

	@Override
	protected Vec4 forEach(Func1_F f) {
		float x = f.calc(x());
		float y = f.calc(y());
		float z = f.calc(z());
		float w = f.calc(w());
		return new RVec4(x, y, z, w);
	}

	@Override
	protected Vec4 forEach(Vec4 v, Func2_F f) {
		float x = f.calc(x(), v.x());
		float y = f.calc(y(), v.y());
		float z = f.calc(z(), v.z());
		float w = f.calc(w(), v.w());
		return new RVec4(x, y, z, w);
	}

	@Override
	protected Vec4 forEach(float n, Func2_F f) {
		float x = f.calc(x(), n);
		float y = f.calc(y(), n);
		float z = f.calc(z(), n);
		float w = f.calc(w(), n);
		return new RVec4(x, y, z, w);
	}

	@Override
	protected Vec4 forEach(Vec4 v2, Vec4 v3, Func3_F f) {
		float x = f.calc(x(), v2.x(), v3.x());
		float y = f.calc(y(), v2.y(), v3.y());
		float z = f.calc(z(), v2.z(), v3.z());
		float w = f.calc(w(), v2.w(), v3.w());
		return new RVec4(x, y, z, w);
	}

	@Override
	protected Vec4 forEach(Vec4 v, float n, Func3_F f) {
		float x = f.calc(x(), v.x(), n);
		float y = f.calc(y(), v.y(), n);
		float z = f.calc(z(), v.z(), n);
		float w = f.calc(w(), v.w(), n);
		return new RVec4(x, y, z, w);
	}

	@Override
	protected Vec4 forEach(float n1, float n2, Func3_F f) {
		float x = f.calc(x(), n1, n2);
		float y = f.calc(y(), n1, n2);
		float z = f.calc(z(), n1, n2);
		float w = f.calc(w(), n1, n2);
		return new RVec4(x, y, z, w);
	}

}
