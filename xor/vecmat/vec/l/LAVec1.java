package xor.vecmat.vec.l;



class LAVec1 extends LVec1 {

	private long[] array;

	private int i1;

	LAVec1(long[] array, int[] indices) {
		this.array = array;
		i1 = indices[0];
	}

	LAVec1(long[] array, int i1) {
		this.array = array;
		this.i1 = i1;
		if (i1 < 0 || i1 >= array.length) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public long x() {
		return array[i1];
	}

	@Override
	public void x(long x) {
		array[i1] = x;
	}

}
