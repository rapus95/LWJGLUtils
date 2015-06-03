package xor.vecmat.vec;

import xor.vecmat.vec.b.BVec;

public interface Vec_numeric<T extends Vec_numeric<T, B, N>, B extends BVec<B>, N extends Number> extends Vec_base<T, B, N> {
	
	@Override
	public abstract Vec_numeric<?, ?, N> getS(CharSequence t);
	
	@Override
	public abstract Vec_numeric<?, ?, N> getI(int... indices);
	
	public abstract T add(T other);
	
	public abstract T add(N other);
	
	public abstract T sub(T other);
	
	public abstract T sub(N other);
	
	public abstract T sub2(N other);
	
	public abstract T neg();
	
	public abstract T mul(T other);
	
	public abstract T mul(N other);
	
	public abstract T div(T other);
	
	public abstract T div(N other);
	
	public abstract T div2(N other);
	
	public abstract T mod(T other);
	
	public abstract T mod(N other);
	
	public abstract T mod2(N other);
	
	public abstract T pow(T other);
	
	public abstract T pow(N other);
	
	public abstract T pow2(N other);
	
	public abstract T abs();
	
	public abstract T sign();
	
	public abstract T min(T other);
	
	public abstract T min(N other);
	
	public abstract T max(T other);
	
	public abstract T max(N other);
	
	public abstract T clamp(T min, T max);
	
	public abstract T clamp(N min, N max);
	
	public abstract T step(T step);
	
	public abstract T step(N step);
	
	public abstract T inc();
	
	public abstract T dec();
	
	public abstract double length();
	
	public abstract double distance(T v);
	
	public abstract double dot(T v);
	
	public abstract B bigger(T other);
	
	public abstract B biggerEqual(T other);
	
	public abstract B smaller(T other);
	
	public abstract B smallerEqual(T other);
	
}
