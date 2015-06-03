package xor.vecmat.vec.f;



class AVec1 extends Vec1 {

	private float[] array;

	private int i1;

	AVec1(float[] array, int[] indices) {
		this.array = array;
		i1 = indices[0];
	}

	AVec1(float[] array, int i1) {
		this.array = array;
		this.i1 = i1;
		if (i1 < 0 || i1 >= array.length) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public float x() {
		return array[i1];
	}

	@Override
	public void x(float x) {
		array[i1] = x;
	}

}
