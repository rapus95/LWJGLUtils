package xor.opengl;

public interface ICast {

	public abstract int t2t(Type to, Type from, int v);

	public abstract float t2f(Type from, int v);

	public abstract double t2d(Type from, int v);

	public abstract int f2t(Type to, float v);

	public abstract int d2t(Type to, double v);

	public abstract double f2d(float v);

	public abstract float d2f(double v);

}