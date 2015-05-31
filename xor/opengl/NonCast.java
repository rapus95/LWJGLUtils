package xor.opengl;

public class NonCast implements ICast {

	@Override
	public int t2t(Type to, Type from, int v) {
		return v;
	}

	@Override
	public float t2f(Type from, int v) {
		return v;
	}

	@Override
	public double t2d(Type from, int v) {
		return v;
	}

	@Override
	public int f2t(Type to, float v) {
		return (int) v;
	}

	@Override
	public int d2t(Type to, double v) {
		return (int) v;
	}

	@Override
	public double f2d(float v) {
		return v;
	}

	@Override
	public float d2f(double v) {
		return (float) v;
	}

}
