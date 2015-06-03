package xor.vecmat.vec;

import xor.vecmat.vec.b.BVec;

public interface Vec_float<T extends Vec_float<T, B, N>, B extends BVec<B>, N extends Number> extends Vec_numeric<T, B, N> {

	@Override
	public abstract Vec_float<?, ?, N> getS(CharSequence t);
	
	@Override
	public abstract Vec_float<?, ?, N> getI(int... indices);
	
	public abstract T radians();
	
	public abstract T degrees();
	
	public abstract T sin();
	
	public abstract T cos();
	
	public abstract T tan();

	public abstract T asin();
	
	public abstract T acos();
	
	public abstract T atan();
	
	public abstract T atan(T other);

	public abstract T sinh();
	
	public abstract T cosh();
	
	public abstract T tanh();
	
	public abstract T asinh();
	
	public abstract T acosh();
	
	public abstract T atanh();
	
	public abstract T exp();
	
	public abstract T log();

	public abstract T exp2();
	
	public abstract T log2();
	
	public abstract T sqrt();

	public abstract T inversesqrt();

	public abstract T roundeven();
	
	public abstract T round();
	
	public abstract T trunc();
	
	public abstract T floor();

	public abstract T ceil();

	public abstract T fract();

	public abstract T mix(T other, T v);
	
	public abstract T mix(T other, N v);
	
	public abstract T smoothstep(T start, T end);
	
	public abstract T smoothstep(N start, N end);
	
	public abstract T add_scaled(T other, T scale);
	
	public abstract T add_scaled(T other, N scale);
	
	public abstract T normalize();
	
}
