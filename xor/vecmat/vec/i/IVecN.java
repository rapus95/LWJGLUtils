package xor.vecmat.vec.i;

import xor.vecmat.Utils;
import xor.vecmat.vec.b.BVec;
import xor.vecmat.vec.b.BVecN;



public abstract class IVecN extends IVec<IVecN, BVecN> {

	@Override
	public IVecN clone() {
		final int size = dim();
		int[] data = new int[size];
		for (int i = 0; i < size; i++) {
			data[i] = get(i);
		}
		return new IRVecN(data);
	}

	@Override
	protected IVecN _new() {
		return new IRVecN(dim());
	}
	
	@Override
	public BVecN equals(IVecN other) {
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
	public BVecN notEqual(IVecN other) {
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
	public BVecN bigger(IVecN other) {
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
	public BVecN biggerEqual(IVecN other) {
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
	public BVecN smaller(IVecN other) {
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
	public BVecN smallerEqual(IVecN other) {
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
