package xor.vecmat.quat;

public interface Quat_base<Q extends Quat_base<Q, N>, N extends Number> {

	public abstract N xW();
	
	public abstract N iW();
	
	public abstract N jW();
	
	public abstract N kW();
	
	public abstract void x(N v);
	
	public abstract void i(N v);
	
	public abstract void j(N v);
	
	public abstract void k(N v);
	
	public abstract Q add(Q other);
	
	public abstract Q sub(Q other);
	
	public abstract Q neg();
	
	public abstract Q mul(Q other);
	
	public abstract Q conjugate();
	
	public abstract Q inverse();
	
	public abstract Q normalize();

	public abstract double norm();
	
	public abstract double length();
	
	public abstract double dot(Q other);
	
	public abstract Q cross(Q other);
	
}
