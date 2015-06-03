package xor.vecmat.vec.f;

import xor.vecmat.VecMath;
import xor.vecmat.COMP_OPS.Func1_F;
import xor.vecmat.COMP_OPS.Func2_F;
import xor.vecmat.COMP_OPS.Func3_F;
import xor.vecmat.vec.b.BVec;
import xor.vecmat.vec.b.BVec2;

public abstract class Vec2 extends Vec<Vec2, BVec2> {

	public abstract float x();

	public abstract float y();

	public abstract void x(float x);

	public abstract void y(float y);

	@Override
	public int dim() {
		return 2;
	}

	@Override
	public float get(int i) {
		switch (i) {
		case 0:
			return x();
		case 1:
			return y();
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
		default:
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public void setTo(Vec2 v) {
		x(v.x());
		y(v.y());
	}

	public void setTo(float x, float y) {
		x(x);
		y(y);
	}

	@Override
	public Vec2 add(float n) {
		return new RVec2(x() + n, y() + n);
	}

	@Override
	public Vec2 add(Vec2 v) {
		return new RVec2(x() + v.x(), y() + v.y());
	}

	public Vec2 add(float x, float y) {
		return new RVec2(x() + x, y() + y);
	}

	@Override
	public Vec2 sub(float n) {
		return new RVec2(x() - n, y() - n);
	}

	@Override
	public Vec2 sub(Vec2 v) {
		return new RVec2(x() - v.x(), y() - v.y());
	}

	public Vec2 sub(float x, float y) {
		return new RVec2(x() - x, y() - y);
	}

	@Override
	public Vec2 sub2(float n) {
		return new RVec2(n - x(), n - y());
	}

	@Override
	public Vec2 neg() {
		return new RVec2(-x(), -y());
	}

	@Override
	public Vec2 mul(float n) {
		return new RVec2(x() * n, y() * n);
	}

	@Override
	public Vec2 mul(Vec2 v) {
		return new RVec2(x() * v.x(), y() * v.y());
	}

	public Vec2 mul(float x, float y) {
		return new RVec2(x() * x, y() * y);
	}

	@Override
	public Vec2 div(float n) {
		return new RVec2(x() / n, y() / n);
	}

	@Override
	public Vec2 div(Vec2 v) {
		return new RVec2(x() / v.x(), y() / v.y());
	}

	public Vec2 div(float x, float y) {
		return new RVec2(x() / x, y() / y);
	}

	@Override
	public Vec2 div2(float n) {
		return new RVec2(n / x(), n / y());
	}

	@Override
	public Vec2 mod(float n) {
		return new RVec2(x() % n, y() % n);
	}

	@Override
	public Vec2 mod(Vec2 v) {
		return new RVec2(x() % v.x(), y() % v.y());
	}

	public Vec2 mod(float x, float y) {
		return new RVec2(x() % x, y() % y);
	}

	@Override
	public Vec2 mod2(float n) {
		return new RVec2(n % x(), n % y());
	}

	@Override
	public Vec2 pow(float n) {
		return new RVec2((float) VecMath.pow(x(), n), (float) VecMath.pow(y(),
				n));
	}

	@Override
	public Vec2 pow(Vec2 v) {
		return new RVec2((float) VecMath.pow(x(), v.x()), (float) VecMath.pow(
				y(), v.y()));
	}

	public Vec2 pow(float x, float y) {
		return new RVec2((float) VecMath.pow(x(), x), (float) VecMath.pow(y(),
				y));
	}

	@Override
	public Vec2 pow2(float n) {
		return new RVec2((float) VecMath.pow(n, x()), (float) VecMath.pow(n,
				y()));
	}

	@Override
	public Vec2 clone() {
		return new RVec2(x(), y());
	}

	@Override
	protected Vec2 _new() {
		return new RVec2(0, 0);
	}

	@Override
	public String toString() {
		return "[" + x() + ", " + y() + "]";
	}

	@Override
	public BVec2 equals(Vec2 other) {
		return BVec.BVec2(x() == other.x(), y() == other.y());
	}

	@Override
	public BVec2 notEqual(Vec2 other) {
		return BVec.BVec2(x() != other.x(), y() != other.y());
	}

	@Override
	public BVec2 bigger(Vec2 other) {
		return BVec.BVec2(x() > other.x(), y() > other.y());
	}

	@Override
	public BVec2 biggerEqual(Vec2 other) {
		return BVec.BVec2(x() >= other.x(), y() >= other.y());
	}

	@Override
	public BVec2 smaller(Vec2 other) {
		return BVec.BVec2(x() < other.x(), y() < other.y());
	}

	@Override
	public BVec2 smallerEqual(Vec2 other) {
		return BVec.BVec2(x() <= other.x(), y() <= other.y());
	}

	@Override
	protected Vec2 forEach(Func1_F f) {
		float x = f.calc(x());
		float y = f.calc(y());
		return new RVec2(x, y);
	}

	@Override
	protected Vec2 forEach(Vec2 v, Func2_F f) {
		float x = f.calc(x(), v.x());
		float y = f.calc(y(), v.y());
		return new RVec2(x, y);
	}

	@Override
	protected Vec2 forEach(float n, Func2_F f) {
		float x = f.calc(x(), n);
		float y = f.calc(y(), n);
		return new RVec2(x, y);
	}

	@Override
	protected Vec2 forEach(Vec2 v2, Vec2 v3, Func3_F f) {
		float x = f.calc(x(), v2.x(), v3.x());
		float y = f.calc(y(), v2.y(), v3.y());
		return new RVec2(x, y);
	}

	@Override
	protected Vec2 forEach(Vec2 v, float n, Func3_F f) {
		float x = f.calc(x(), v.x(), n);
		float y = f.calc(y(), v.y(), n);
		return new RVec2(x, y);
	}

	@Override
	protected Vec2 forEach(float n1, float n2, Func3_F f) {
		float x = f.calc(x(), n1, n2);
		float y = f.calc(y(), n1, n2);
		return new RVec2(x, y);
	}

}
