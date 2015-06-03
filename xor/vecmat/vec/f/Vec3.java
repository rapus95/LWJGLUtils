package xor.vecmat.vec.f;

import xor.vecmat.VecMath;
import xor.vecmat.COMP_OPS.Func1_F;
import xor.vecmat.COMP_OPS.Func2_F;
import xor.vecmat.COMP_OPS.Func3_F;
import xor.vecmat.vec.Vec_cross;
import xor.vecmat.vec.b.BVec;
import xor.vecmat.vec.b.BVec3;

public abstract class Vec3 extends Vec<Vec3, BVec3> implements Vec_cross<Vec3, BVec3, Float> {

	public abstract float x();

	public abstract float y();

	public abstract float z();

	public abstract void x(float x);

	public abstract void y(float y);

	public abstract void z(float y);

	@Override
	public int dim() {
		return 3;
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
		default:
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public void setTo(Vec3 v) {
		x(v.x());
		y(v.y());
		z(v.z());
	}

	public void setTo(float x, float y, float z) {
		x(x);
		y(y);
		z(z);
	}

	@Override
	public Vec3 add(float n) {
		return new RVec3(x() + n, y() + n, z() + n);
	}

	@Override
	public Vec3 add(Vec3 v) {
		return new RVec3(x() + v.x(), y() + v.y(), z() + v.z());
	}

	public Vec3 add(float x, float y, float z) {
		return new RVec3(x() + x, y() + y, z() + z);
	}

	@Override
	public Vec3 sub(float n) {
		return new RVec3(x() - n, y() - n, z() - n);
	}

	@Override
	public Vec3 sub(Vec3 v) {
		return new RVec3(x() - v.x(), y() - v.y(), z() - v.z());
	}

	public Vec3 sub(float x, float y, float z) {
		return new RVec3(x() - x, y() - y, z() - z);
	}

	@Override
	public Vec3 sub2(float n) {
		return new RVec3(n - x(), n - y(), n - z());
	}

	@Override
	public Vec3 neg() {
		return new RVec3(-x(), -y(), -z());
	}

	@Override
	public Vec3 mul(float n) {
		return new RVec3(x() * n, y() * n, z() * n);
	}

	@Override
	public Vec3 mul(Vec3 v) {
		return new RVec3(x() * v.x(), y() * v.y(), z() * v.z());
	}

	public Vec3 mul(float x, float y, float z) {
		return new RVec3(x() * x, y() * y, z() * z);
	}

	@Override
	public Vec3 div(float n) {
		return new RVec3(x() / n, y() / n, z() / n);
	}

	@Override
	public Vec3 div(Vec3 v) {
		return new RVec3(x() / v.x(), y() / v.y(), z() / v.z());
	}

	public Vec3 div(float x, float y, float z) {
		return new RVec3(x() / x, y() / y, z() / z);
	}

	@Override
	public Vec3 div2(float n) {
		return new RVec3(n / x(), n / y(), n / z());
	}

	@Override
	public Vec3 mod(float n) {
		return new RVec3(x() % n, y() % n, z() % n);
	}

	@Override
	public Vec3 mod(Vec3 v) {
		return new RVec3(x() % v.x(), y() % v.y(), z() % v.z());
	}

	public Vec3 mod(float x, float y, float z) {
		return new RVec3(x() % x, y() % y, z() % z);
	}

	@Override
	public Vec3 mod2(float n) {
		return new RVec3(n % x(), n % y(), n % z());
	}

	@Override
	public Vec3 pow(float n) {
		return new RVec3((float) VecMath.pow(x(), n), (float) VecMath.pow(y(), n), (float) VecMath.pow(z(), n));
	}

	@Override
	public Vec3 pow(Vec3 v) {
		return new RVec3((float) VecMath.pow(x(), v.x()), (float) VecMath.pow(y(), v.y()), (float) VecMath.pow(y(), v.z()));
	}

	public Vec3 pow(float x, float y, float z) {
		return new RVec3((float) VecMath.pow(x(), x), (float) VecMath.pow(y(), y), (float) VecMath.pow(y(), z));
	}

	@Override
	public Vec3 pow2(float n) {
		return new RVec3((float) VecMath.pow(n, x()), (float) VecMath.pow(n, y()), (float) VecMath.pow(n, z()));
	}

	@Override
	public Vec3 clone() {
		return new RVec3(x(), y(), z());
	}

	@Override
	protected Vec3 _new() {
		return new RVec3(0, 0, 0);
	}

	@Override
	public String toString() {
		return "[" + x() + ", " + y() + ", " + z() + "]";
	}
	
	@Override
	public BVec3 equals(Vec3 other) {
		return BVec.BVec3(x() == other.x(), y() == other.y(), z() == other.z());
	}

	@Override
	public BVec3 notEqual(Vec3 other) {
		return BVec.BVec3(x() != other.x(), y() != other.y(), z() != other.z());
	}

	@Override
	public BVec3 bigger(Vec3 other) {
		return BVec.BVec3(x() > other.x(), y() > other.y(), z() > other.z());
	}

	@Override
	public BVec3 biggerEqual(Vec3 other) {
		return BVec.BVec3(x() >= other.x(), y() >= other.y(), z() >= other.z());
	}

	@Override
	public BVec3 smaller(Vec3 other) {
		return BVec.BVec3(x() < other.x(), y() < other.y(), z() < other.z());
	}

	@Override
	public BVec3 smallerEqual(Vec3 other) {
		return BVec.BVec3(x() <= other.x(), y() <= other.y(), z() <= other.z());
	}

	public Vec3 cross(Vec3 v) {
		final float x = x();
		final float y = y();
		final float z = z();
		final float vx = v.x();
		final float vy = v.y();
		final float vz = v.z();
		return new RVec3(y * vz - z * vy, z * vx - x * vz, x * vy - y * vx);
	}

	@Override
	protected Vec3 forEach(Func1_F f) {
		float x = f.calc(x());
		float y = f.calc(y());
		float z = f.calc(z());
		return new RVec3(x, y, z);
	}

	@Override
	protected Vec3 forEach(Vec3 v, Func2_F f) {
		float x = f.calc(x(), v.x());
		float y = f.calc(y(), v.y());
		float z = f.calc(z(), v.z());
		return new RVec3(x, y, z);
	}

	@Override
	protected Vec3 forEach(float n, Func2_F f) {
		float x = f.calc(x(), n);
		float y = f.calc(y(), n);
		float z = f.calc(z(), n);
		return new RVec3(x, y, z);
	}

	@Override
	protected Vec3 forEach(Vec3 v2, Vec3 v3, Func3_F f) {
		float x = f.calc(x(), v2.x(), v3.x());
		float y = f.calc(y(), v2.y(), v3.y());
		float z = f.calc(z(), v2.z(), v3.z());
		return new RVec3(x, y, z);
	}

	@Override
	protected Vec3 forEach(Vec3 v, float n, Func3_F f) {
		float x = f.calc(x(), v.x(), n);
		float y = f.calc(y(), v.y(), n);
		float z = f.calc(z(), v.z(), n);
		return new RVec3(x, y, z);
	}

	@Override
	protected Vec3 forEach(float n1, float n2, Func3_F f) {
		float x = f.calc(x(), n1, n2);
		float y = f.calc(y(), n1, n2);
		float z = f.calc(z(), n1, n2);
		return new RVec3(x, y, z);
	}

}
