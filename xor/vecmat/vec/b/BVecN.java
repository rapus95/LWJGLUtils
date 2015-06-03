package xor.vecmat.vec.b;



public abstract class BVecN extends BVec<BVecN> {

	@Override
	public BVecN clone() {
		final int size = dim();
		boolean[] data = new boolean[size];
		for (int i = 0; i < size; i++) {
			data[i] = get(i);
		}
		return new BRVecN(data);
	}

	@Override
	protected BVecN _new() {
		return new BRVecN(dim(), false);
	}

}
