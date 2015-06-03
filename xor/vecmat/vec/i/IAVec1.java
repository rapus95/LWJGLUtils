package xor.vecmat.vec.i;



class IAVec1 extends IVec1 {

	private int[] array;

	private int i1;

	IAVec1(int[] array, int[] indices) {
		this.array = array;
		i1 = indices[0];
	}

	IAVec1(int[] array, int i1) {
		this.array = array;
		this.i1 = i1;
		if (i1 < 0 || i1 >= array.length) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public int x() {
		return array[i1];
	}

	@Override
	public void x(int x) {
		array[i1] = x;
	}

}
