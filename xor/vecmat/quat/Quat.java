package xor.vecmat.quat;

import xor.vecmat.VecMath;

public class Quat implements Quat_base<Quat, Float> {

	public float x;
	public float i;
	public float j;
	public float k;

	public Quat(float x, float i, float j, float k) {
		this.x = x;
		this.i = i;
		this.j = j;
		this.k = k;
	}

	@Override
	public Float xW() {
		return x;
	}

	public float x() {
		return x;
	}

	@Override
	public Float iW() {
		return i;
	}

	public float i() {
		return i;
	}

	@Override
	public Float jW() {
		return j;
	}

	public float j() {
		return j;
	}

	@Override
	public Float kW() {
		return k;
	}

	public float k() {
		return k;
	}

	@Override
	public void x(Float v) {
		this.x = v.floatValue();
	}

	public void x(float v) {
		this.x = v;
	}

	@Override
	public void i(Float v) {
		this.i = v.floatValue();
	}

	public void i(float v) {
		this.i = v;
	}

	@Override
	public void j(Float v) {
		this.j = v.floatValue();
	}

	public void j(float v) {
		this.j = v;
	}

	@Override
	public void k(Float v) {
		this.k = v.floatValue();
	}

	public void k(float v) {
		this.k = v;
	}

	@Override
	public Quat add(Quat other) {
		return new Quat(x + other.x, i + other.i, j + other.j, k + other.k);
	}

	@Override
	public Quat sub(Quat other) {
		return new Quat(x - other.x, i - other.i, j - other.j, k - other.k);
	}

	@Override
	public Quat neg() {
		return new Quat(-x, -i, -j, -k);
	}

	@Override
	public Quat mul(Quat other) {
		float nx = k * other.x + other.k * x + i * other.j - j * other.i;
		float ni = k * other.i + other.k * i - x * other.j + j * other.x;
		float nj = k * other.j + other.k * j + x * other.i - i * other.x;
		float nk = k * other.k - other.x * x - i * other.i - j * other.j;
		return new Quat(nx, ni, nj, nk);
	}

	@Override
	public Quat conjugate() {
		return new Quat(x, -i, -j, -k);
	}

	@Override
	public Quat inverse() {
		float norm = 1 / (float) norm();
		return new Quat(x * norm, -i * norm, -j * norm, -k * norm);
	}

	@Override
	public Quat normalize() {
		float norm = (float) norm();
		if (norm > 0.0) {
			norm = 1.0f / VecMath.sqrt(norm);
			return new Quat(x * norm, i * norm, j * norm, k * norm);
		} else {
			return new Quat(0, 0, 0, 0);
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
	public double dot(Quat other) {
		return x * other.x + i * other.i + j * other.j + k * other.k;
	}

	@Override
	public Quat cross(Quat other) {
		float ni = j * other.k + other.k * j;
		float nj = k * other.i + other.i * k;
		float nk = i * other.j + other.i * j;
		return new Quat(0, ni, nj, nk);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.hashCode(x);
		result = prime * result + Float.hashCode(i);
		result = prime * result + Float.hashCode(j);
		result = prime * result + Float.hashCode(k);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Quat))
			return false;
		Quat other = (Quat) obj;
		return x == other.x && i == other.i && j == other.j && k == other.k;
	}

	@Override
	public String toString() {
		return "[" + x + ", " + i + ", " + j + ", " + k + "]";
	}

}
