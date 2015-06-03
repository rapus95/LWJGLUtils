package xor.vecmat.vec.l;

import xor.vecmat.Utils;
import xor.vecmat.vec.b.BVec;
import xor.vecmat.vec.b.BVecN;



public abstract class LVecN extends LVec<LVecN, BVecN> {

	@Override
	public LVecN clone() {
		final int size = dim();
		long[] data = new long[size];
		for (int i = 0; i < size; i++) {
			data[i] = get(i);
		}
		return new LRVecN(data);
	}

	@Override
	protected LVecN _new() {
		return new LRVecN(dim());
	}
	
	@Override
	public BVecN equals(LVecN other) {
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
	public BVecN notEqual(LVecN other) {
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
	public BVecN bigger(LVecN other) {
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
	public BVecN biggerEqual(LVecN other) {
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
	public BVecN smaller(LVecN other) {
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
	public BVecN smallerEqual(LVecN other) {
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
