package xor.opengl;

public class Cast implements ICast {

	private static final double UINT_MAX_VALUE = 1L << 32 - 1;
	public static final ICast INSTANCE = new Cast();

	@Override
	public int t2t(Type to, Type from, int v) {
		if (to == from)
			return v;
		if (from.isUnsigned() && !to.isUnsigned()) {
			v += 1 << from.getBits();
		}
		if (!from.isUnsigned() && to.isUnsigned()) {
			v -= 1 << to.getBits();
		}
		return v;
	}

	@Override
	public float t2f(Type from, int v) {
		if (from == Type.UINT) {
			if (v < 0) {
				return (float) ((v & Integer.MAX_VALUE) / UINT_MAX_VALUE) + 0.5f;
			}
			return (float) (v / UINT_MAX_VALUE);
		}
		return (v - from.getMin()) / ((1 << from.getBits()) - 1.0f);
	}

	@Override
	public double t2d(Type from, int v) {
		if (from == Type.UINT) {
			if (v < 0) {
				return (v & Integer.MAX_VALUE) / UINT_MAX_VALUE + 0.5;
			}
			return v / UINT_MAX_VALUE;
		}
		return (v - from.getMin()) / ((1 << from.getBits()) - 1.0);
	}

	@Override
	public int f2t(Type to, float v) {
		if (to == Type.UINT) {
			if (v >= 0.5) {
				return (int) ((v - 0.5) * UINT_MAX_VALUE) | 1<<32;
			} else {
				return (int) (v * UINT_MAX_VALUE);
			}
		}
		return (int) (v * ((1 << to.getBits()) - 1)) + to.getMin();
	}

	@Override
	public int d2t(Type to, double v) {
		if (to == Type.UINT) {

		}
		return (int) (v * ((1 << to.getBits()) - 1)) + to.getMin();
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
