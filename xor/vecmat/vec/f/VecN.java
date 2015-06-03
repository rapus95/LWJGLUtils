package xor.vecmat.vec.f;

import xor.vecmat.Utils;
import xor.vecmat.vec.b.BVec;
import xor.vecmat.vec.b.BVecN;

public abstract class VecN extends Vec<VecN, BVecN> {

	@Override
	public VecN clone() {
		final int size = dim();
		float[] data = new float[size];
		for (int i = 0; i < size; i++) {
			data[i] = get(i);
		}
		return new RVecN(data);
	}

	@Override
	protected VecN _new() {
		return new RVecN(dim());
	}

	@Override
	public BVecN equals(VecN other) {
		final int size = dim();
		if (Utils.CHECKS) {
			if (size != other.dim())
				throw new IllegalArgumentException(
						"In order to compare two Vectors they have to be of the same dimension!");
		}
		BVecN ret = BVec.BVecX(size);
		for (int i = 0; i < size; i++) {
			ret.set(i, get(i) == other.get(i));
		}
		return ret;
	}

	@Override
	public BVecN notEqual(VecN other) {
		final int size = dim();
		if (Utils.CHECKS) {
			if (size != other.dim())
				throw new IllegalArgumentException(
						"In order to compare two Vectors they have to be of the same dimension!");
		}
		BVecN ret = BVec.BVecX(size);
		for (int i = 0; i < size; i++) {
			ret.set(i, get(i) != other.get(i));
		}
		return ret;
	}

	@Override
	public BVecN bigger(VecN other) {
		final int size = dim();
		if (Utils.CHECKS) {
			if (size != other.dim())
				throw new IllegalArgumentException(
						"In order to compare two Vectors they have to be of the same dimension!");
		}
		BVecN ret = BVec.BVecX(size);
		for (int i = 0; i < size; i++) {
			ret.set(i, get(i) > other.get(i));
		}
		return ret;
	}

	@Override
	public BVecN biggerEqual(VecN other) {
		final int size = dim();
		if (Utils.CHECKS) {
			if (size != other.dim())
				throw new IllegalArgumentException(
						"In order to compare two Vectors they have to be of the same dimension!");
		}
		BVecN ret = BVec.BVecX(size);
		for (int i = 0; i < size; i++) {
			ret.set(i, get(i) >= other.get(i));
		}
		return ret;
	}

	@Override
	public BVecN smaller(VecN other) {
		final int size = dim();
		if (Utils.CHECKS) {
			if (size != other.dim())
				throw new IllegalArgumentException(
						"In order to compare two Vectors they have to be of the same dimension!");
		}
		BVecN ret = BVec.BVecX(size);
		for (int i = 0; i < size; i++) {
			ret.set(i, get(i) < other.get(i));
		}
		return ret;
	}

	@Override
	public BVecN smallerEqual(VecN other) {
		final int size = dim();
		if (Utils.CHECKS) {
			if (size != other.dim())
				throw new IllegalArgumentException(
						"In order to compare two Vectors they have to be of the same dimension!");
		}
		BVecN ret = BVec.BVecX(size);
		for (int i = 0; i < size; i++) {
			ret.set(i, get(i) <= other.get(i));
		}
		return ret;
	}

}
