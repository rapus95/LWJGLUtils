package xor.vecmat.vec.b;



class BAVec1 extends BVec1 {

	private boolean[] array;

	private int i1;

	BAVec1(boolean[] array, int[] indices) {
		this.array = array;
		i1 = indices[0];
	}

	BAVec1(boolean[] array, int i1) {
		this.array = array;
		this.i1 = i1;
		if (i1 < 0 || i1 >= array.length) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public boolean x() {
		return array[i1];
	}

	@Override
	public void x(boolean x) {
		array[i1] = x;
	}

}
