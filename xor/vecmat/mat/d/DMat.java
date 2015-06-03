package xor.vecmat.mat.d;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import xor.vecmat.COMP_OPS;
import xor.vecmat.Utils;
import xor.vecmat.VecMath;
import xor.vecmat.COMP_OPS.Func1_D;
import xor.vecmat.COMP_OPS.Func2_D;
import xor.vecmat.COMP_OPS.Func3_D;
import xor.vecmat.mat.Mat_base;
import xor.vecmat.vec.d.DVec;

public abstract class DMat<T extends DMat<T, T2, V>, T2 extends DMat<?, ?, ?>, V extends DVec<?, ?>> extends Mat_base<T, T2, V, Double> {

	public abstract double get(int m, int n);

	@Override
	public Double getW(int m, int n) {
		return Double.valueOf(getW(m, n));
	}

	public abstract void set(int m, int n, double v);

	@Override
	public void set(int m, int n, Double v) {
		set(m, n, v.doubleValue());
	}

	@Override
	public T add(Double n) {
		return add(n.doubleValue());
	}

	public T add(double n) {
		return forEach(n, COMP_OPS.ADD_D);
	}

	@Override
	public T add(T v) {
		return forEach(v, COMP_OPS.ADD_D);
	}

	@Override
	public T sub(Double n) {
		return sub(n.doubleValue());
	}

	public T sub(double n) {
		return forEach(n, COMP_OPS.SUB_D);
	}

	@Override
	public T sub(T v) {
		return forEach(v, COMP_OPS.SUB_D);
	}

	@Override
	public T sub2(Double n) {
		return sub2(n.doubleValue());
	}

	public T sub2(double n) {
		return forEach(n, COMP_OPS.SUB2_D);
	}

	@Override
	public T neg() {
		return forEach(COMP_OPS.NEG_D);
	}

	@Override
	public T mul(Double n) {
		return mul(n.doubleValue());
	}

	public T mul(double n) {
		return forEach(n, COMP_OPS.MUL_D);
	}

	@Override
	public T compMul(T y) {
		return forEach(y, COMP_OPS.MUL_D);
	}

	@Override
	public T compDiv(Double y) {
		return compDiv(y.doubleValue());
	}

	public T compDiv(double y) {
		return forEach(y, COMP_OPS.DIV_D);
	}

	@Override
	public T compDiv(T y) {
		return forEach(y, COMP_OPS.DIV_D);
	}

	@Override
	public T compMod(Double y) {
		return compMod(y.doubleValue());
	}

	public T compMod(double y) {
		return forEach(y, COMP_OPS.MOD_D);
	}

	@Override
	public T compMod(T y) {
		return forEach(y, COMP_OPS.MOD_D);
	}

	@Override
	public T compPow(Double y) {
		return compPow(y.doubleValue());
	}

	public T compPow(double y) {
		return forEach(y, COMP_OPS.POW_D);
	}

	@Override
	public T compPow(T y) {
		return forEach(y, COMP_OPS.POW_D);
	}

	@Override
	public <M extends Mat_base<?, ?, ?, Double>> M mulU(Mat_base<?, ?, ?, Double> v) {
		final int m = m();
		final int n = v.n();
		final int size = v.m();
		if (size != n())
			throw new IllegalArgumentException();
		DMat<?, ?, ?> mat = DMatX(m, n);
		if(v instanceof DMat){
			DMat<?, ?, ?> mv = (DMat<?, ?, ?>)v;
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					for (int k = 0; k < size; k++) {
						mat.set(i, j, mat.get(i, j) + get(i, k) * mv.get(k, j));
					}
				}
			}
		}else{
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					for (int k = 0; k < size; k++) {
						mat.set(i, j, mat.get(i, j) + get(i, k) * v.getW(k, j));
					}
				}
			}
		}
		return Utils.unchecked(mat);
	}
	
	protected T forEach(Func1_D f) {
		T ret = _new();
		final int m = m();
		final int n = n();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				ret.set(j, i, f.calc(get(j, i)));
			}
		}
		return ret;
	}

	protected T forEach(T v, Func2_D f) {
		T ret = _new();
		final int m = m();
		final int n = n();
		if (Utils.CHECKS) {
			if (m != v.m() || n != v.n())
				throw new IllegalArgumentException("Incompatible matrix sizes!");
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				ret.set(j, i, f.calc(get(j, i), v.get(j, i)));
			}
		}
		return ret;
	}

	protected T forEach(double n1, Func2_D f) {
		T ret = _new();
		final int m = m();
		final int n = n();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				ret.set(j, i, f.calc(get(j, i), n1));
			}
		}
		return ret;
	}

	protected T forEach(T v2, T v3, Func3_D f) {
		T ret = _new();
		final int m = m();
		final int n = n();
		if (Utils.CHECKS) {
			if (m != v2.m() || n != v2.n() || m != v3.m() || n != v3.n())
				throw new IllegalArgumentException("Incompatible matrix sizes!");
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				ret.set(j, i, f.calc(get(j, i), v2.get(j, i), v3.get(j, i)));
			}
		}
		return ret;
	}

	protected T forEach(T v, double n1, Func3_D f) {
		T ret = _new();
		final int m = m();
		final int n = n();
		if (Utils.CHECKS) {
			if (m != v.m() || n != v.n())
				throw new IllegalArgumentException("Incompatible matrix sizes!");
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				ret.set(j, i, f.calc(get(j, i), v.get(j, i), n1));
			}
		}
		return ret;
	}

	protected T forEach(double n1, double n2, Func3_D f) {
		T ret = _new();
		final int m = m();
		final int n = n();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				ret.set(j, i, f.calc(get(j, i), n1, n2));
			}
		}
		return ret;
	}

	@Override
	public void writeTo(ByteBuffer byteBuffer) {
		writeTo(byteBuffer.asDoubleBuffer());
	}

	@Override
	public void writeToGL(ByteBuffer byteBuffer) {
		writeToGL(byteBuffer.asDoubleBuffer());
	}

	public void writeTo(DoubleBuffer doubleBuffer) {
		final int m = m();
		final int n = n();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				doubleBuffer.put(get(i, j));
			}
		}
	}

	public void writeToGL(DoubleBuffer doubleBuffer) {
		final int m = m();
		final int n = n();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				doubleBuffer.put(get(j, i));
			}
		}
	}

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
				String nu = Double.toString(get(i, 0));
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
					String nu = Double.toString(get(i, j));
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		final int m = m();
		final int n = n();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				result = prime * result + Double.hashCode(get(i, j));
			}
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof DMat))
			return false;
		DMat<?, ?, ?> other = (DMat<?, ?, ?>) obj;
		final int m = m();
		if (m != other.m())
			return false;
		final int n = n();
		if (n != other.n())
			return false;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (get(i, j) != other.get(i, j))
					return false;
			}
		}
		return true;
	}

	
	public static DMat1 DMat1() {
		return new DRMat1(1);
	}

	public static DMat1 DMat1(double scale) {
		return new DRMat1(scale);
	}
	
	public static DMat2 DMat2() {
		return new DRMat2(new double[]{1, 0, 0, 1});
	}

	public static DMat2 DMat2(double scale) {
		return new DRMat2(new double[]{scale, 0, 0, scale});
	}

	public static DMat2 Mat2F(double fill) {
		return new DRMat2(new double[]{fill, fill, fill, fill});
	}

	public static DMat2 DMat2(double m00, double m10, double m01, double m11) {
		return new DRMat2(new double[]{m00, m10, m01, m11});
	}

	public static DMat2 Mat2T(double m00, double m01, double m10, double m11) {
		return new DRMat2(new double[]{m00, m10, m01, m11});
	}

	public static DMat2 DMat2(Object... o) {
		double[] f = new double[4];
		Utils.fill(f, o);
		return new DRMat2(f);
	}

	public static DMat2 Mat2T(Object... o) {
		double[] f = new double[4];
		Utils.fill(f, o);
		return new DRMat2(new double[]{f[0], f[2], f[1], f[3]});
	}

	public static DMat2 DMat2(DMat2 m) {
		return m.clone();
	}

	public static DMat3 DMat3() {
		return new DRMat3(new double[]{1, 0, 0, 0, 1, 0, 0, 0, 1});
	}

	public static DMat3 DMat3(double scale) {
		return new DRMat3(new double[]{scale, 0, 0, 0, scale, 0, 0, 0, scale});
	}

	public static DMat3 Mat3F(double fill) {
		return new DRMat3(new double[]{fill, fill, fill, fill, fill, fill, fill, fill, fill});
	}

	public static DMat3 DMat3(double m00, double m10, double m20, double m01, double m11, double m21, double m02, double m12, double m22) {
		return new DRMat3(new double[]{m00, m10, m20, m01, m11, m21, m02, m12, m22});
	}

	public static DMat3 Mat3T(double m00, double m01, double m02, double m10, double m11, double m12, double m20, double m21, double m22) {
		return new DRMat3(new double[]{m00, m10, m20, m01, m11, m21, m02, m12, m22});
	}

	public static DMat3 DMat3(Object... o) {
		double[] f = new double[9];
		Utils.fill(f, o);
		return new DRMat3(f);
	}

	public static DMat3 Mat3T(Object... o) {
		double[] f = new double[9];
		Utils.fill(f, o);
		return new DRMat3(new double[]{f[0], f[3], f[6], f[1], f[4], f[7], f[2], f[5], f[8]});
	}

	public static DMat3 DMat3(DMat3 m) {
		return m.clone();
	}

	public static DMat4 DMat4() {
		return new DRMat4(new double[]{1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1});
	}

	public static DMat4 DMat4(double scale) {
		return new DRMat4(new double[]{scale, 0, 0, 0, 0, scale, 0, 0, 0, 0, scale, 0, 0, 0, 0, scale});
	}

	public static DMat4 Mat4F(double fill) {
		return new DRMat4(new double[]{fill, fill, fill, fill, fill, fill, fill, fill, fill, fill, fill, fill, fill, fill, fill, fill});
	}

	public static DMat4 DMat4(double m00, double m10, double m20, double m30, double m01, double m11, double m21, double m31, double m02, double m12, double m22, double m32, double m03, double m13, double m23, double m33) {
		return new DRMat4(new double[]{m00, m10, m20, m30, m01, m11, m21, m31, m02, m12, m22, m32, m03, m13, m23, m33});
	}

	public static DMat4 Mat4T(double m00, double m01, double m02, double m03, double m10, double m11, double m12, double m13, double m20, double m21, double m22, double m23, double m30, double m31, double m32, double m33) {
		return new DRMat4(new double[]{m00, m10, m20, m30, m01, m11, m21, m31, m02, m12, m22, m32, m03, m13, m23, m33});
	}

	public static DMat4 DMat4(Object... o) {
		double[] f = new double[16];
		Utils.fill(f, o);
		return new DRMat4(f);
	}

	public static DMat4 Mat4T(Object... o) {
		double[] f = new double[16];
		Utils.fill(f, o);
		return new DRMat4(new double[]{f[0], f[4], f[8], f[12], f[1], f[5], f[9], f[13], f[2], f[6], f[10], f[14], f[3], f[7], f[11], f[15]});
	}

	public static DMat4 DMat4(DMat4 m) {
		return m.clone();
	}

	public static <M extends DMat<M, M, ?>> M DMatX(int size) {
		switch (size) {
			case 2 :
				return Utils.unchecked(DMat2());
			case 3 :
				return Utils.unchecked(DMat3());
			case 4 :
				return Utils.unchecked(DMat4());
		}
		double[] mat = new double[size * size];
		int s1 = size + 1;
		for (int i = 0; i < size; i++) {
			mat[i * s1] = 1;
		}
		return Utils.unchecked(new DRMatN(size, mat));
	}

	public static <M extends DMat<M, M, ?>> M DMatX(int size, double scale) {
		switch (size) {
			case 2 :
				return Utils.unchecked(DMat2(scale));
			case 3 :
				return Utils.unchecked(DMat3(scale));
			case 4 :
				return Utils.unchecked(DMat4(scale));
		}
		double[] mat = new double[size * size];
		int s1 = size + 1;
		for (int i = 0; i < size; i++) {
			mat[i * s1] = scale;
		}
		return Utils.unchecked(new DRMatN(size, mat));
	}

	public static <M extends DMat<M, M, ?>> M MatXF(int size, double fill) {
		switch (size) {
			case 2 :
				return Utils.unchecked(Mat2F(fill));
			case 3 :
				return Utils.unchecked(Mat3F(fill));
			case 4 :
				return Utils.unchecked(Mat4F(fill));
		}
		double[] mat = new double[size * size];
		Arrays.fill(mat, fill);
		return Utils.unchecked(new DRMatN(size, mat));
	}

	public static <M extends DMat<M, M, ?>> M DMatX(double... mat) {
		final int l = mat.length;
		final int size = (int) (VecMath.sqrt(l) + 0.5);
		if (size * size != l)
			throw new IllegalArgumentException();
		switch (size) {
			case 2 :
				return Utils.unchecked(new DRMat2(mat));
			case 3 :
				return Utils.unchecked(new DRMat3(mat));
			case 4 :
				return Utils.unchecked(new DRMat4(mat));
		}
		return Utils.unchecked(new DRMatN(size, mat));
	}

	public static <M extends DMat<M, M, ?>> M MatXT(double... mat) {
		final int l = mat.length;
		final int size = (int) (VecMath.sqrt(l) + 0.5);
		if (size * size != l)
			throw new IllegalArgumentException();
		double[] t = new double[l];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				t[i + j * size] = mat[i * size + j];
			}
		}
		switch (size) {
			case 2 :
				return Utils.unchecked(new DRMat2(t));
			case 3 :
				return Utils.unchecked(new DRMat3(t));
			case 4 :
				return Utils.unchecked(new DRMat4(t));
		}
		return Utils.unchecked(new DRMatN(size, t));
	}

	public static <M extends DMat<M, M, ?>> M DMatX(Object... o) {
		List<Number> ll = new ArrayList<Number>();
		Utils.fill(ll, o);
		final int l = ll.size();
		final int size = (int) (VecMath.sqrt(l) + 0.5);
		if (size * size != l)
			throw new IllegalArgumentException();
		double[] mat = new double[l];
		for (int i = 0; i < l; i++) {
			mat[i] = ll.get(i).doubleValue();
		}
		switch (size) {
			case 2 :
				return Utils.unchecked(new DRMat2(mat));
			case 3 :
				return Utils.unchecked(new DRMat3(mat));
			case 4 :
				return Utils.unchecked(new DRMat4(mat));
		}
		return Utils.unchecked(new DRMatN(size, mat));
	}

	public static <M extends DMat<M, M, ?>> M MatXT(Object... o) {
		List<Number> ll = new ArrayList<Number>();
		Utils.fill(ll, o);
		final int l = ll.size();
		final int size = (int) (VecMath.sqrt(l) + 0.5);
		if (size * size != l)
			throw new IllegalArgumentException();
		double[] mat = new double[l];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				mat[i + j * size] = ll.get(i * size + j).doubleValue();
			}
		}
		switch (size) {
			case 2 :
				return Utils.unchecked(new DRMat2(mat));
			case 3 :
				return Utils.unchecked(new DRMat3(mat));
			case 4 :
				return Utils.unchecked(new DRMat4(mat));
		}
		return Utils.unchecked(new DRMatN(size, mat));
	}

	public static <M extends DMat<M, ?, ?>> M DMatX(M m) {
		return m.clone();
	}

	public static <M extends DMat<M, ?, ?>> M DMatX(int m, int n) {
		if (m == n) {
			return Utils.unchecked(DMatX(m));
		}
		return Utils.unchecked(new DRMatMN(m, n));
	}

	public static <M extends DMat<M, ?, ?>> M DMatX(int m, int n, double... mat) {
		if (m == n) {
			return Utils.unchecked(DMatX(m, mat));
		}
		return Utils.unchecked(new DRMatMN(m, n, mat));
	}
	
}
