package xor.vecmat.mat;

import java.nio.ByteBuffer;

import xor.vecmat.Utils;
import xor.vecmat.vec.Vec_numeric;

public abstract class Mat_base<T extends Mat_base<T, T2, V, N>, T2 extends Mat_base<?, ?, ?, N>, V extends Vec_numeric<?, ?, N>, N extends Number> {

	public abstract int n();

	public abstract int m();

	public abstract N getW(int m, int n);

	public abstract void set(int m, int n, N v);

	protected abstract V getRow(int m);

	protected abstract V getColumn(int n);

	public <R extends V> R row(int m) {
		if (m < 0 || m >= m())
			throw new IndexOutOfBoundsException();
		return Utils.unchecked(getRow(m));
	}

	public <R extends V> R column(int n) {
		if (n < 0 || n >= n())
			throw new IndexOutOfBoundsException();
		return Utils.unchecked(getColumn(n));
	}

	public void row(int m, V v) {
		final int n = n();
		if (m < 0 || m >= m())
			throw new IndexOutOfBoundsException();
		if (n != v.dim()) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < n; i++) {
			set(m, i, v.getW(i));
		}
	}

	public void column(int n, V v) {
		final int m = m();
		if (n < 0 || n >= n())
			throw new IndexOutOfBoundsException();
		if (m != v.dim()) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < m; i++) {
			set(i, n, v.getW(i));
		}
	}

	public void set(T v) {
		final int m = m();
		final int n = n();
		if (m != v.m() || n != v.n())
			throw new IllegalArgumentException();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				set(i, j, v.getW(i, j));
			}
		}
	}

	public abstract T add(N n);

	public abstract T add(T v);

	public abstract T sub(N n);

	public abstract T sub(T v);

	public abstract T sub2(N n);

	public abstract T neg();

	public abstract T mul(N n);

	public abstract T2 mul(T v);

	public abstract <M extends Mat_base<?, ?, ?, N>> M mulU(Mat_base<?, ?, ?, N> v);

	public abstract V mul(V v);

	public <R extends V> R mulV(V v){
		return Utils.unchecked(mulV(v));
	}

	public abstract T compMul(T y);

	public abstract T compDiv(N y);

	public abstract T compDiv(T y);

	public abstract T compMod(N y);

	public abstract T compMod(T y);

	public abstract T pow(int y);

	public abstract T compPow(N y);

	public abstract T compPow(T y);

	public abstract T transpose();

	public abstract T invert();

	public abstract double det();

	public abstract T adjunkte();

	@Override
	public abstract T clone();

	protected abstract T _new();

	@Override
	public String toString() {
		final int n = n();
		if (n > 0) {
			final int m = m();
			StringBuilder[] lines = new StringBuilder[m];
			String[] num = new String[m];
			int max = 0;
			for (int i = 0; i < m; i++) {
				lines[i] = new StringBuilder();
				String nu = getW(i, 0).toString();
				int l = nu.length();
				if (l > max)
					max = l;
				num[i] = nu;
			}
			for (int i = 0; i < m; i++) {
				StringBuilder sb = lines[i];
				for (int k = num[i].length(); k < max; k++) {
					sb.append(' ');
				}
				sb.append(num[i]);
			}
			int ll = max;
			for (int j = 1; j < n; j++) {
				max = 0;
				for (int i = 0; i < m; i++) {
					String nu = getW(i, j).toString();
					int l = nu.length();
					if (l > max)
						max = l;
					num[i] = nu;
				}
				for (int i = 0; i < m; i++) {
					StringBuilder sb = lines[i];
					sb.append(" ,");
					for (int k = num[i].length(); k < max; k++) {
						sb.append(' ');
					}
					sb.append(num[i]);
				}
				ll += 2 + max;
			}
			StringBuilder ret = new StringBuilder((ll + 5) * (m + 2) - 1);
			ret.append("+-");
			for (int i = 0; i < ll; i++) {
				ret.append(' ');
			}
			ret.append("-+\n");
			for (int i = 0; i < m; i++) {
				ret.append("| ");
				ret.append(lines[i]);
				ret.append(" |\n");
			}
			ret.append("+-");
			for (int i = 0; i < ll; i++) {
				ret.append(' ');
			}
			ret.append("-+");
			return ret.toString();
		}
		return "+- -+\n|   |\n+- -+";
	}

	public abstract void writeTo(ByteBuffer byteBuffer);

	public void writeToGL(ByteBuffer byteBuffer){
		transpose().writeTo(byteBuffer);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		final int m = m();
		final int n = n();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				result = prime * result + getW(i, j).hashCode();
			}
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Mat_base))
			return false;
		Mat_base<?, ?, ?, ?> other = (Mat_base<?, ?, ?, ?>) obj;
		final int m = m();
		if (m != other.m())
			return false;
		final int n = n();
		if (n != other.n())
			return false;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (!getW(i, j).equals(other.getW(i, j)))
					return false;
			}
		}
		return true;
	}

}
