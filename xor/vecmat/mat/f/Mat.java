package xor.vecmat.mat.f;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import xor.vecmat.COMP_OPS;
import xor.vecmat.Utils;
import xor.vecmat.VecMath;
import xor.vecmat.COMP_OPS.Func1_F;
import xor.vecmat.COMP_OPS.Func2_F;
import xor.vecmat.COMP_OPS.Func3_F;
import xor.vecmat.mat.Mat_base;
import xor.vecmat.vec.f.Vec;

public abstract class Mat<T extends Mat<T, T2, V>, T2 extends Mat<?, ?, ?>, V extends Vec<?, ?>> extends Mat_base<T, T2, V, Float> {

	public abstract float get(int m, int n);

	@Override
	public Float getW(int m, int n) {
		return Float.valueOf(getW(m, n));
	}

	public abstract void set(int m, int n, float v);

	@Override
	public void set(int m, int n, Float v) {
		set(m, n, v.floatValue());
	}

	@Override
	public T add(Float n) {
		return add(n.floatValue());
	}

	public T add(float n) {
		return forEach(n, COMP_OPS.ADD_F);
	}

	@Override
	public T add(T v) {
		return forEach(v, COMP_OPS.ADD_F);
	}

	@Override
	public T sub(Float n) {
		return sub(n.floatValue());
	}

	public T sub(float n) {
		return forEach(n, COMP_OPS.SUB_F);
	}

	@Override
	public T sub(T v) {
		return forEach(v, COMP_OPS.SUB_F);
	}

	@Override
	public T sub2(Float n) {
		return sub2(n.floatValue());
	}

	public T sub2(float n) {
		return forEach(n, COMP_OPS.SUB2_F);
	}

	@Override
	public T neg() {
		return forEach(COMP_OPS.NEG_F);
	}

	@Override
	public T mul(Float n) {
		return mul(n.floatValue());
	}

	public T mul(float n) {
		return forEach(n, COMP_OPS.MUL_F);
	}

	@Override
	public T compMul(T y) {
		return forEach(y, COMP_OPS.MUL_F);
	}

	@Override
	public T compDiv(Float y) {
		return compDiv(y.floatValue());
	}

	public T compDiv(float y) {
		return forEach(y, COMP_OPS.DIV_F);
	}

	@Override
	public T compDiv(T y) {
		return forEach(y, COMP_OPS.DIV_F);
	}

	@Override
	public T compMod(Float y) {
		return compMod(y.floatValue());
	}

	public T compMod(float y) {
		return forEach(y, COMP_OPS.MOD_F);
	}

	@Override
	public T compMod(T y) {
		return forEach(y, COMP_OPS.MOD_F);
	}

	@Override
	public T compPow(Float y) {
		return compPow(y.floatValue());
	}

	public T compPow(float y) {
		return forEach(y, COMP_OPS.POW_F);
	}

	@Override
	public T compPow(T y) {
		return forEach(y, COMP_OPS.POW_F);
	}

	@Override
	public <M extends Mat_base<?, ?, ?, Float>> M mulU(Mat_base<?, ?, ?, Float> v) {
		final int m = m();
		final int n = v.n();
		final int size = v.m();
		if (size != n())
			throw new IllegalArgumentException();
		Mat<?, ?, ?> mat = MatX(m, n);
		if(v instanceof Mat){
			Mat<?, ?, ?> mv = (Mat<?, ?, ?>)v;
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
	
	protected T forEach(Func1_F f) {
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

	protected T forEach(T v, Func2_F f) {
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

	protected T forEach(float n1, Func2_F f) {
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

	protected T forEach(T v2, T v3, Func3_F f) {
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

	protected T forEach(T v, float n1, Func3_F f) {
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

	protected T forEach(float n1, float n2, Func3_F f) {
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
		writeTo(byteBuffer.asFloatBuffer());
	}

	@Override
	public void writeToGL(ByteBuffer byteBuffer) {
		writeToGL(byteBuffer.asFloatBuffer());
	}

	public void writeTo(FloatBuffer floatBuffer) {
		final int m = m();
		final int n = n();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				floatBuffer.put(get(i, j));
			}
		}
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

	public void writeToGL(FloatBuffer floatBuffer) {
		final int m = m();
		final int n = n();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				floatBuffer.put(get(j, i));
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
				String nu = Float.toString(get(i, 0));
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
					String nu = Float.toString(get(i, j));
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
				result = prime * result + Float.hashCode(get(i, j));
			}
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Mat))
			return false;
		Mat<?, ?, ?> other = (Mat<?, ?, ?>) obj;
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

	
	public static Mat1 Mat1() {
		return new RMat1(1);
	}

	public static Mat1 Mat1(float scale) {
		return new RMat1(scale);
	}
	
	public static Mat2 Mat2() {
		return new RMat2(new float[]{1, 0, 0, 1});
	}

	public static Mat2 Mat2(float scale) {
		return new RMat2(new float[]{scale, 0, 0, scale});
	}

	public static Mat2 Mat2F(float fill) {
		return new RMat2(new float[]{fill, fill, fill, fill});
	}

	public static Mat2 Mat2(float m00, float m10, float m01, float m11) {
		return new RMat2(new float[]{m00, m10, m01, m11});
	}

	public static Mat2 Mat2T(float m00, float m01, float m10, float m11) {
		return new RMat2(new float[]{m00, m10, m01, m11});
	}

	public static Mat2 Mat2(Object... o) {
		float[] f = new float[4];
		Utils.fill(f, o);
		return new RMat2(f);
	}

	public static Mat2 Mat2T(Object... o) {
		float[] f = new float[4];
		Utils.fill(f, o);
		return new RMat2(new float[]{f[0], f[2], f[1], f[3]});
	}

	public static Mat2 Mat2(Mat2 m) {
		return m.clone();
	}

	public static Mat3 Mat3() {
		return new RMat3(new float[]{1, 0, 0, 0, 1, 0, 0, 0, 1});
	}

	public static Mat3 Mat3(float scale) {
		return new RMat3(new float[]{scale, 0, 0, 0, scale, 0, 0, 0, scale});
	}

	public static Mat3 Mat3F(float fill) {
		return new RMat3(new float[]{fill, fill, fill, fill, fill, fill, fill, fill, fill});
	}

	public static Mat3 Mat3(float m00, float m10, float m20, float m01, float m11, float m21, float m02, float m12, float m22) {
		return new RMat3(new float[]{m00, m10, m20, m01, m11, m21, m02, m12, m22});
	}

	public static Mat3 Mat3T(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22) {
		return new RMat3(new float[]{m00, m10, m20, m01, m11, m21, m02, m12, m22});
	}

	public static Mat3 Mat3(Object... o) {
		float[] f = new float[9];
		Utils.fill(f, o);
		return new RMat3(f);
	}

	public static Mat3 Mat3T(Object... o) {
		float[] f = new float[9];
		Utils.fill(f, o);
		return new RMat3(new float[]{f[0], f[3], f[6], f[1], f[4], f[7], f[2], f[5], f[8]});
	}

	public static Mat3 Mat3(Mat3 m) {
		return m.clone();
	}

	public static Mat4 Mat4() {
		return new RMat4(new float[]{1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1});
	}

	public static Mat4 Mat4(float scale) {
		return new RMat4(new float[]{scale, 0, 0, 0, 0, scale, 0, 0, 0, 0, scale, 0, 0, 0, 0, scale});
	}

	public static Mat4 Mat4F(float fill) {
		return new RMat4(new float[]{fill, fill, fill, fill, fill, fill, fill, fill, fill, fill, fill, fill, fill, fill, fill, fill});
	}

	public static Mat4 Mat4(float m00, float m10, float m20, float m30, float m01, float m11, float m21, float m31, float m02, float m12, float m22, float m32, float m03, float m13, float m23, float m33) {
		return new RMat4(new float[]{m00, m10, m20, m30, m01, m11, m21, m31, m02, m12, m22, m32, m03, m13, m23, m33});
	}

	public static Mat4 Mat4T(float m00, float m01, float m02, float m03, float m10, float m11, float m12, float m13, float m20, float m21, float m22, float m23, float m30, float m31, float m32, float m33) {
		return new RMat4(new float[]{m00, m10, m20, m30, m01, m11, m21, m31, m02, m12, m22, m32, m03, m13, m23, m33});
	}

	public static Mat4 Mat4(Object... o) {
		float[] f = new float[16];
		Utils.fill(f, o);
		return new RMat4(f);
	}

	public static Mat4 Mat4T(Object... o) {
		float[] f = new float[16];
		Utils.fill(f, o);
		return new RMat4(new float[]{f[0], f[4], f[8], f[12], f[1], f[5], f[9], f[13], f[2], f[6], f[10], f[14], f[3], f[7], f[11], f[15]});
	}

	public static Mat4 Mat4(Mat4 m) {
		return m.clone();
	}

	public static <M extends Mat<M, M, ?>> M MatX(int size) {
		switch (size) {
			case 2 :
				return Utils.unchecked(Mat2());
			case 3 :
				return Utils.unchecked(Mat3());
			case 4 :
				return Utils.unchecked(Mat4());
		}
		float[] mat = new float[size * size];
		int s1 = size + 1;
		for (int i = 0; i < size; i++) {
			mat[i * s1] = 1;
		}
		return Utils.unchecked(new RMatN(size, mat));
	}

	public static <M extends Mat<M, M, ?>> M MatX(int size, float scale) {
		switch (size) {
			case 2 :
				return Utils.unchecked(Mat2(scale));
			case 3 :
				return Utils.unchecked(Mat3(scale));
			case 4 :
				return Utils.unchecked(Mat4(scale));
		}
		float[] mat = new float[size * size];
		int s1 = size + 1;
		for (int i = 0; i < size; i++) {
			mat[i * s1] = scale;
		}
		return Utils.unchecked(new RMatN(size, mat));
	}

	public static <M extends Mat<M, M, ?>> M MatXF(int size, float fill) {
		switch (size) {
			case 2 :
				return Utils.unchecked(Mat2F(fill));
			case 3 :
				return Utils.unchecked(Mat3F(fill));
			case 4 :
				return Utils.unchecked(Mat4F(fill));
		}
		float[] mat = new float[size * size];
		Arrays.fill(mat, fill);
		return Utils.unchecked(new RMatN(size, mat));
	}

	public static <M extends Mat<M, M, ?>> M MatX(float... mat) {
		final int l = mat.length;
		final int size = (int) (VecMath.sqrt(l) + 0.5);
		if (size * size != l)
			throw new IllegalArgumentException();
		switch (size) {
			case 2 :
				return Utils.unchecked(new RMat2(mat));
			case 3 :
				return Utils.unchecked(new RMat3(mat));
			case 4 :
				return Utils.unchecked(new RMat4(mat));
		}
		return Utils.unchecked(new RMatN(size, mat));
	}

	public static <M extends Mat<M, M, ?>> M MatXT(float... mat) {
		final int l = mat.length;
		final int size = (int) (VecMath.sqrt(l) + 0.5);
		if (size * size != l)
			throw new IllegalArgumentException();
		float[] t = new float[l];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				t[i + j * size] = mat[i * size + j];
			}
		}
		switch (size) {
			case 2 :
				return Utils.unchecked(new RMat2(t));
			case 3 :
				return Utils.unchecked(new RMat3(t));
			case 4 :
				return Utils.unchecked(new RMat4(t));
		}
		return Utils.unchecked(new RMatN(size, t));
	}

	public static <M extends Mat<M, M, ?>> M MatX(Object... o) {
		List<Number> ll = new ArrayList<Number>();
		Utils.fill(ll, o);
		final int l = ll.size();
		final int size = (int) (VecMath.sqrt(l) + 0.5);
		if (size * size != l)
			throw new IllegalArgumentException();
		float[] mat = new float[l];
		for (int i = 0; i < l; i++) {
			mat[i] = ll.get(i).floatValue();
		}
		switch (size) {
			case 2 :
				return Utils.unchecked(new RMat2(mat));
			case 3 :
				return Utils.unchecked(new RMat3(mat));
			case 4 :
				return Utils.unchecked(new RMat4(mat));
		}
		return Utils.unchecked(new RMatN(size, mat));
	}

	public static <M extends Mat<M, M, ?>> M MatXT(Object... o) {
		List<Number> ll = new ArrayList<Number>();
		Utils.fill(ll, o);
		final int l = ll.size();
		final int size = (int) (VecMath.sqrt(l) + 0.5);
		if (size * size != l)
			throw new IllegalArgumentException();
		float[] mat = new float[l];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				mat[i + j * size] = ll.get(i * size + j).floatValue();
			}
		}
		switch (size) {
			case 2 :
				return Utils.unchecked(new RMat2(mat));
			case 3 :
				return Utils.unchecked(new RMat3(mat));
			case 4 :
				return Utils.unchecked(new RMat4(mat));
		}
		return Utils.unchecked(new RMatN(size, mat));
	}

	public static <M extends Mat<M, ?, ?>> M MatX(M m) {
		return m.clone();
	}

	public static <M extends Mat<M, ?, ?>> M MatX(int m, int n) {
		if (m == n) {
			return Utils.unchecked(MatX(m));
		}
		return Utils.unchecked(new RMatMN(m, n));
	}

	public static <M extends Mat<M, ?, ?>> M MatX(int m, int n, float... mat) {
		if (m == n) {
			return Utils.unchecked(MatX(m, mat));
		}
		return Utils.unchecked(new RMatMN(m, n, mat));
	}
	
}
