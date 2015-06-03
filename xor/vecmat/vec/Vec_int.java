package xor.vecmat.vec;

import xor.vecmat.vec.b.BVec;


public interface Vec_int<T extends Vec_int<T, B, N>, B extends BVec<B>, N extends Number> extends Vec_numeric<T, B, N> {

	@Override
	public abstract Vec_int<?, ?, N> getS(CharSequence t);
	
	@Override
	public abstract Vec_int<?, ?, N> getI(int... indices);
	
	public abstract T or(T other);
	
	public abstract T or(N other);
	
	public abstract T and(T other);
	
	public abstract T and(N other);
	
	public abstract T xor(T other);
	
	public abstract T xor(N other);
	
	public abstract T shl(T other);
	
	public abstract T shl(N other);
	
	public abstract T shl2(N other);
	
	public abstract T shr(T other);
	
	public abstract T shr(N other);
	
	public abstract T shr2(N other);
	
	public abstract T ushr(T other);
	
	public abstract T ushr(N other);
	
	public abstract T ushr2(N other);
	
	public abstract T invert();
	
}
