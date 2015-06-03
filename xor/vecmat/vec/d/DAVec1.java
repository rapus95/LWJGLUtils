package xor.vecmat.vec.d;

class DAVec1 extends DVec1 {

	private double[] array;

	private int i1;

	DAVec1(double[] array, int[] indices) {
		this.array = array;
		i1 = indices[0];
	}

	DAVec1(double[] array, int i1) {
		this.array = array;
		this.i1 = i1;
		if (i1 < 0 || i1 >= array.length) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public double x() {
		return array[i1];
	}

	@Override
	public void x(double x) {
		array[i1] = x;
	}

}
