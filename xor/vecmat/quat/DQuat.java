package xor.vecmat.quat;

import xor.vecmat.VecMath;

public class DQuat implements Quat_base<DQuat, Double> {

	public double x;
	public double i;
	public double j;
	public double k;

	public DQuat(double x, double i, double j, double k) {
		this.x = x;
		this.i = i;
		this.j = j;
		this.k = k;
	}

	@Override
	public Double xW() {
		return x;
	}

	public double x() {
		return x;
	}

	@Override
	public Double iW() {
		return i;
	}

	public double i() {
		return i;
	}

	@Override
	public Double jW() {
		return j;
	}

	public double j() {
		return j;
	}

	@Override
	public Double kW() {
		return k;
	}

	public double k() {
		return k;
	}

	@Override
	public void x(Double v) {
		this.x = v.doubleValue();
	}

	public void x(double v) {
		this.x = v;
	}

	@Override
	public void i(Double v) {
		this.i = v.doubleValue();
	}

	public void i(double v) {
		this.i = v;
	}

	@Override
	public void j(Double v) {
		this.j = v.doubleValue();
	}

	public void j(double v) {
		this.j = v;
	}

	@Override
	public void k(Double v) {
		this.k = v.doubleValue();
	}

	public void k(double v) {
		this.k = v;
	}

	@Override
	public DQuat add(DQuat other) {
		return new DQuat(x + other.x, i + other.i, j + other.j, k + other.k);
	}

	@Override
	public DQuat sub(DQuat other) {
		return new DQuat(x - other.x, i - other.i, j - other.j, k - other.k);
	}
	
	@Override
	public DQuat neg() {
		return new DQuat(-x, -i, -j, -k);
	}

	@Override
	public DQuat mul(DQuat other) {
		double nx = k * other.x + other.k * x + i * other.j - j * other.i;
		double ni = k * other.i + other.k * i - x * other.j + j * other.x;
		double nj = k * other.j + other.k * j + x * other.i - i * other.x;
		double nk = k * other.k - other.x * x - i * other.i - j * other.j;
		return new DQuat(nx, ni, nj, nk);
	}

	@Override
	public DQuat conjugate() {
		return new DQuat(x, -i, -j, -k);
	}

	@Override
	public DQuat inverse() {
		double norm = 1 / norm();
		return new DQuat(x * norm, -i * norm, -j * norm, -k * norm);
	}

	@Override
	public DQuat normalize() {
		double norm = norm();
		if (norm > 0.0) {
			norm = 1.0f / VecMath.sqrt(norm);
			return new DQuat(x * norm, i * norm, j * norm, k * norm);
		} else {
			return new DQuat(0, 0, 0, 0);
		}
	}

	@Override
	public double norm() {
		return x * x + i * i + j * j + k * k;
	}

	@Override
	public double length() {
		return VecMath.sqrt(norm());
	}

	@Override
	public double dot(DQuat other) {
		return x * other.x + i * other.i + j * other.j + k * other.k;
	}
	
	@Override
	public DQuat cross(DQuat other) {
		double ni = j * other.k + other.k * j;
		double nj = k * other.i + other.i * k;
		double nk = i * other.j + other.i * j;
		return new DQuat(0, ni, nj, nk);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Double.hashCode(x);
		result = prime * result + Double.hashCode(i);
		result = prime * result + Double.hashCode(j);
		result = prime * result + Double.hashCode(k);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof DQuat))
			return false;
		DQuat other = (DQuat) obj;
		return x==other.x && i==other.i && j==other.j && k==other.k;
	}

	@Override
	public String toString() {
		return "[" + x + ", " + i + ", " + j + ", " + k + "]";
	}
	
}
