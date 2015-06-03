package xor.vecmat.vec.f;

import xor.vecmat.VecMath;
import xor.vecmat.COMP_OPS.Func1_F;
import xor.vecmat.COMP_OPS.Func2_F;
import xor.vecmat.COMP_OPS.Func3_F;
import xor.vecmat.vec.b.BVec;
import xor.vecmat.vec.b.BVec1;

public abstract class Vec1 extends Vec<Vec1, BVec1> {

	public abstract float x();

	public abstract void x(float x);

	@Override
	public int dim() {
		return 1;
	}

	@Override
	public float get(int i) {
		switch (i) {
		case 0:
			return x();
		}
		throw new IndexOutOfBoundsException();
	}

	@Override
	public void set(int i, float v) {
		switch (i) {
		case 0:
			x(v);
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
		default:
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public void setTo(Vec1 v) {
		x(v.x());
	}

	public void setTo(float x) {
		x(x);
	}

	@Override
	public Vec1 add(float n) {
		return new RVec1(x() + n);
	}

	@Override
	public Vec1 add(Vec1 v) {
		return new RVec1(x() + v.x());
	}

	@Override
	public Vec1 sub(float n) {
		return new RVec1(x() - n);
	}

	@Override
	public Vec1 sub(Vec1 v) {
		return new RVec1(x() - v.x());
	}

	@Override
	public Vec1 sub2(float n) {
		return new RVec1(n - x());
	}

	@Override
	public Vec1 neg() {
		return new RVec1(-x());
	}

	@Override
	public Vec1 mul(float n) {
		return new RVec1(x() * n);
	}

	@Override
	public Vec1 mul(Vec1 v) {
		return new RVec1(x() * v.x());
	}

	@Override
	public Vec1 div(float n) {
		return new RVec1(x() / n);
	}

	@Override
	public Vec1 div(Vec1 v) {
		return new RVec1(x() / v.x());
	}

	@Override
	public Vec1 div2(float n) {
		return new RVec1(n / x());
	}

	@Override
	public Vec1 mod(float n) {
		return new RVec1(x() % n);
	}

	@Override
	public Vec1 mod(Vec1 v) {
		return new RVec1(x() % v.x());
	}

	@Override
	public Vec1 mod2(float n) {
		return new RVec1(n % x());
	}

	@Override
	public Vec1 pow(float n) {
		return new RVec1((float) VecMath.pow(x(), n));
	}

	@Override
	public Vec1 pow(Vec1 v) {
		return new RVec1((float) VecMath.pow(x(), v.x()));
	}

	@Override
	public Vec1 pow2(float n) {
		return new RVec1((float) VecMath.pow(n, x()));
	}

	@Override
	public Vec1 clone() {
		return new RVec1(x());
	}

	@Override
	protected Vec1 _new() {
		return new RVec1(0);
	}

	@Override
	public String toString() {
		return "[" + x() + "]";
	}

	@Override
	public BVec1 equals(Vec1 other) {
		return BVec.BVec1(x() == other.x());
	}

	@Override
	public BVec1 notEqual(Vec1 other) {
		return BVec.BVec1(x() != other.x());
	}

	@Override
	public BVec1 bigger(Vec1 other) {
		return BVec.BVec1(x() > other.x());
	}

	@Override
	public BVec1 biggerEqual(Vec1 other) {
		return BVec.BVec1(x() >= other.x());
	}

	@Override
	public BVec1 smaller(Vec1 other) {
		return BVec.BVec1(x() < other.x());
	}

	@Override
	public BVec1 smallerEqual(Vec1 other) {
		return BVec.BVec1(x() <= other.x());
	}
	
	@Override
	protected Vec1 forEach(Func1_F f) {
		float x = f.calc(x());
		return new RVec1(x);
	}

	@Override
	protected Vec1 forEach(Vec1 v, Func2_F f) {
		float x = f.calc(x(), v.x());
		return new RVec1(x);
	}

	@Override
	protected Vec1 forEach(float n, Func2_F f) {
		float x = f.calc(x(), n);
		return new RVec1(x);
	}

	@Override
	protected Vec1 forEach(Vec1 v2, Vec1 v3, Func3_F f) {
		float x = f.calc(x(), v2.x(), v3.x());
		return new RVec1(x);
	}

	@Override
	protected Vec1 forEach(Vec1 v, float n, Func3_F f) {
		float x = f.calc(x(), v.x(), n);
		return new RVec1(x);
	}

	@Override
	protected Vec1 forEach(float n1, float n2, Func3_F f) {
		float x = f.calc(x(), n1, n2);
		return new RVec1(x);
	}

}
