package xor.vecmat.vec.d;

import xor.vecmat.Utils;
import xor.vecmat.vec.b.BVec;
import xor.vecmat.vec.b.BVecN;

public abstract class DVecN extends DVec<DVecN, BVecN> {

	@Override
	public DVecN clone() {
		final int size = dim();
		double[] data = new double[size];
		for (int i = 0; i < size; i++) {
			data[i] = get(i);
		}
		return new DRVecN(data);
	}

	@Override
	protected DVecN _new() {
		return new DRVecN(dim());
	}
	
	@Override
	public BVecN equals(DVecN other) {
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
	public BVecN notEqual(DVecN other) {
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
	public BVecN bigger(DVecN other) {
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
	public BVecN biggerEqual(DVecN other) {
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
	public BVecN smaller(DVecN other) {
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
	public BVecN smallerEqual(DVecN other) {
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
