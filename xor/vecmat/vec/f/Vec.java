package xor.vecmat.vec.f;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import xor.vecmat.COMP_OPS;
import xor.vecmat.Utils;
import xor.vecmat.VecMath;
import xor.vecmat.COMP_OPS.Func1_F;
import xor.vecmat.COMP_OPS.Func2_F;
import xor.vecmat.COMP_OPS.Func3_F;
import xor.vecmat.vec.VecIterator;
import xor.vecmat.vec.Vec_base;
import xor.vecmat.vec.Vec_float;
import xor.vecmat.vec.b.BVec;

public abstract class Vec<T extends Vec<T, B>, B extends BVec<B>> implements Vec_float<T, B, Float> {

	public abstract float get(int index);

	@Override
	public Float getW(int index) {
		return Float.valueOf(get(index));
	}

	public abstract void set(int index, float value);

	@Override
	public void set(int index, Float value) {
		set(index, value.floatValue());
	}

	public float get(char c) {
		return get(Utils.getIndex(c));
	}

	@Override
	public Float getW(char c) {
		return Float.valueOf(get(c));
	}

	public void set(char c, float value) {
		set(Utils.getIndex(c), value);
	}

	@Override
	public void set(char c, Float value) {
		set(c, value.floatValue());
	}

	public <V extends Vec<V, ?>> V get(CharSequence t) {
		final int size = t.length();
		final int dim = dim();
		int[] indices = new int[size];
		for (int i = 0; i < size; i++) {
			char c = t.charAt(i);
			int id = Utils.getIndex(c);
			if (Utils.CHECKS && (id == -1 || id >= dim))
				throw new IllegalArgumentException("Bad character '" + c + "'");
			indices[i] = id;
		}
		return VecX_checked(this, indices);
	}

	@Override
	public Vec<?, ?> getS(CharSequence t) {
		return get(t);
	}

	@Override
	public void set(CharSequence t, Vec_base<?, ?, Float> value) {
		final int size = t.length();
		if (Utils.CHECKS && size != value.dim()) {
			throw new IllegalArgumentException("String length have to equal vector length");
		}
		if (value instanceof Vec<?, ?>) {
			Vec<?, ?> v = (Vec<?, ?>) value;
			for (int i = 0; i < size; i++) {
				char c = t.charAt(i);
				set(c, v.get(i));
			}
		} else {
			for (int i = 0; i < size; i++) {
				char c = t.charAt(i);
				set(c, value.getW(i).floatValue());
			}
		}
	}

	public <V extends Vec<V, ?>> V getII(int... indices) {
		return VecX(this, indices);
	}

	@Override
	public Vec<?, ?> getI(int... indices) {
		return VecX(this, indices);
	}

	@Override
	public void set(Vec_base<?, ?, Float> value, int... indices) {
		set(indices, value);
	}

	@Override
	public void set(int[] indices, Vec_base<?, ?, Float> value) {
		final int size = indices.length;
		if (Utils.CHECKS && size != value.dim()) {
			throw new IllegalArgumentException("indices length have to equal vector length");
		}
		if (value instanceof Vec<?, ?>) {
			Vec<?, ?> v = (Vec<?, ?>) value;
			for (int i = 0; i < size; i++) {
				set(indices[i], v.get(i));
			}
		} else {
			for (int i = 0; i < size; i++) {
				set(indices[i], value.getW(i).floatValue());
			}
		}
	}

	public void setTo(T value) {
		final int size = dim();
		if (Utils.CHECKS && size != value.dim())
			throw new IllegalArgumentException();
		for (int i = 0; i < size; i++) {
			set(i, value.get(i));
		}
	}

	public T add_scaled(T v, float scale) {
		if (scale == 0)
			return clone();
		return forEach(v, scale, COMP_OPS.ADD_SCALED_F);
	}

	public T add_scaled(T v, Float scale) {
		return add_scaled(v, scale.floatValue());
	}

	public T add_scaled(T v, T scale) {
		return forEach(v, scale, COMP_OPS.ADD_SCALED_F);
	}

	public T addScaledAndZeroIfNull(T v, float scale) {
		if (v == null || scale == 0)
			return clone();
		return forEach(v, scale, COMP_OPS.ADD_SCALED_F);
	}

	public T add(float n) {
		if (n == 0)
			return clone();
		return forEach(n, COMP_OPS.ADD_F);
	}

	public T add(Float n) {
		return add(n.floatValue());
	}

	public T add(T v) {
		return forEach(v, COMP_OPS.ADD_F);
	}

	public T addZeroIfNull(T v) {
		if (v == null)
			return clone();
		return forEach(v, COMP_OPS.ADD_F);
	}

	public T sub(float n) {
		if (n == 0)
			return clone();
		return forEach(n, COMP_OPS.SUB_F);
	}

	public T sub(Float n) {
		return sub(n.floatValue());
	}

	public T sub(T v) {
		return forEach(v, COMP_OPS.SUB_F);
	}

	public T sub2(float n) {
		if (n == 0)
			return clone();
		return forEach(n, COMP_OPS.SUB2_F);
	}

	public T sub2(Float n) {
		return sub2(n.floatValue());
	}

	public T neg() {
		return forEach(COMP_OPS.NEG_F);
	}

	public T mul(float n) {
		if (n == 0)
			return _new();
		if (n == 1)
			return clone();
		return forEach(n, COMP_OPS.MUL_F);
	}

	public T mul(Float n) {
		return mul(n.floatValue());
	}

	public T mul(T v) {
		return forEach(v, COMP_OPS.MUL_F);
	}

	public T div(float n) {
		if (n == 1)
			return clone();
		return forEach(n, COMP_OPS.DIV_F);
	}

	public T div(Float n) {
		return div(n.floatValue());
	}

	public T div(T v) {
		return forEach(v, COMP_OPS.DIV_F);
	}

	public T div2(float n) {
		return forEach(n, COMP_OPS.DIV2_F);
	}

	public T div2(Float n) {
		return div2(n.floatValue());
	}

	public T mod(float n) {
		return forEach(n, COMP_OPS.MOD_F);
	}

	public T mod(Float n) {
		return mod(n.floatValue());
	}

	public T mod(T v) {
		return forEach(v, COMP_OPS.MOD_F);
	}

	public T mod2(float n) {
		return forEach(n, COMP_OPS.MOD2_F);
	}

	public T mod2(Float n) {
		return mod2(n.floatValue());
	}

	public T pow(float n) {
		if (n == 0)
			return VecX(dim(), 1);
		if (n == 1)
			return clone();
		return forEach(n, COMP_OPS.POW_F);
	}

	public T pow(Float n) {
		return pow(n.floatValue());
	}

	public T pow(T v) {
		return forEach(v, COMP_OPS.POW_F);
	}

	public T pow2(float n) {
		if (n == 1)
			return VecX(dim(), 1);
		return forEach(n, COMP_OPS.POW2_F);
	}

	public T pow2(Float n) {
		return pow(n.floatValue());
	}

	public T radians() {
		return forEach(COMP_OPS.RADIANS_F);
	}

	public T degrees() {
		return forEach(COMP_OPS.DEGREES_F);
	}

	public T sin() {
		return forEach(COMP_OPS.SIN_F);
	}

	public T cos() {
		return forEach(COMP_OPS.COS_F);
	}

	public T tan() {
		return forEach(COMP_OPS.TAN_F);
	}

	public T asin() {
		return forEach(COMP_OPS.ASIN_F);
	}

	public T acos() {
		return forEach(COMP_OPS.ACOS_F);
	}

	public T atan(T x) {
		return forEach(x, COMP_OPS.ATAN2_F);
	}

	public T atan() {
		return forEach(COMP_OPS.ATAN_F);
	}

	public T sinh() {
		return forEach(COMP_OPS.SINH_F);
	}

	public T cosh() {
		return forEach(COMP_OPS.COSH_F);
	}

	public T tanh() {
		return forEach(COMP_OPS.TANH_F);
	}

	public T asinh() {
		return forEach(COMP_OPS.ASINH_F);
	}

	public T acosh() {
		return forEach(COMP_OPS.ACOSH_F);
	}

	public T atanh() {
		return forEach(COMP_OPS.ATANH_F);
	}

	public T exp() {
		return forEach(COMP_OPS.EXP_F);
	}

	public T log() {
		return forEach(COMP_OPS.LOG_F);
	}

	public T exp2() {
		return forEach(COMP_OPS.EXP2_F);
	}

	public T log2() {
		return forEach(COMP_OPS.LOG2_F);
	}

	public T sqrt() {
		return forEach(COMP_OPS.SQRT_F);
	}

	public T inversesqrt() {
		return forEach(COMP_OPS.INVERSESQRT_F);
	}

	public T abs() {
		return forEach(COMP_OPS.ABS_F);
	}

	public T sign() {
		return forEach(COMP_OPS.SIGN_F);
	}

	public T roundeven() {
		return forEach(COMP_OPS.ROUNDEVEN_F);
	}

	public T round() {
		return forEach(COMP_OPS.ROUND_F);
	}

	public T trunc() {
		return forEach(COMP_OPS.TRUNC_F);
	}

	public T floor() {
		return forEach(COMP_OPS.FLOOR_F);
	}

	public T ceil() {
		return forEach(COMP_OPS.CEIL_F);
	}

	public T fract() {
		return forEach(COMP_OPS.FRACT_F);
	}

	public T min(float n) {
		return forEach(n, COMP_OPS.MIN_F);
	}

	public T min(Float n) {
		return min(n.floatValue());
	}

	public T min(T v) {
		return forEach(v, COMP_OPS.MIN_F);
	}

	public T max(float n) {
		return forEach(n, COMP_OPS.MAX_F);
	}

	public T max(Float n) {
		return max(n.floatValue());
	}

	public T max(T v) {
		return forEach(v, COMP_OPS.MAX_F);
	}

	public T clamp(float min, float max) {
		return forEach(min, max, COMP_OPS.CLAMP_F);
	}

	public T clamp(Float min, Float max) {
		return clamp(min.floatValue(), max.floatValue());
	}

	public T clamp(T min, T max) {
		return forEach(min, max, COMP_OPS.CLAMP_F);
	}

	public T mix(T v, float p) {
		return forEach(v, p, COMP_OPS.MIX_F);
	}

	public T mix(T v, Float p) {
		return mix(v, p.floatValue());
	}

	public T mix(T v, T p) {
		return forEach(v, p, COMP_OPS.MIX_F);
	}

	public T step(float edge) {
		return forEach(edge, COMP_OPS.STEP_F);
	}

	public T step(Float edge) {
		return step(edge.floatValue());
	}

	public T step(T edge) {
		return forEach(edge, COMP_OPS.STEP_F);
	}

	public T smoothstep(float edge0, float edge1) {
		return forEach(edge0, edge1, COMP_OPS.SMOOTHSTEP_F);
	}

	public T smoothstep(Float edge0, Float edge1) {
		return smoothstep(edge0.floatValue(), edge1.floatValue());
	}

	public T smoothstep(T edge0, T edge1) {
		return forEach(edge0, edge1, COMP_OPS.SMOOTHSTEP_F);
	}

	public T inc() {
		return forEach(COMP_OPS.INC_F);
	}

	public T dec() {
		return forEach(COMP_OPS.DEC_F);
	}

	public double length() {
		final int size = dim();
		float l = 0;
		for (int i = 0; i < size; i++) {
			final float g = get(i);
			l += g * g;
		}
		return VecMath.sqrt(l);
	}

	public double distance(T v) {
		return sub(v).length();
	}

	public boolean distanceSmaller(T v, double dist) {
		T tmp = sub(v);
		return tmp.dot(tmp) < dist * dist;
	}

	public double dot(T v) {
		final int size = dim();
		if (Utils.CHECKS) {
			if (size != v.dim())
				throw new IllegalArgumentException("In order to combine three Vectors they have to be of the same dimension!");
		}
		float ret = 0;
		for (int i = 0; i < size; i++) {
			ret += get(i) * v.get(i);
		}
		return ret;
	}

	public T normalize() {
		return div((float) length());
	}

	@Override
	public ListIterator<Float> iterator() {
		return new VecIterator<Float>(this);
	}

	@Override
	public String toString() {
		String s = "[";
		final int size = dim();
		if (size > 0) {
			s += get(0);
			for (int i = 1; i < size; i++) {
				s += ", " + get(i);
			}
		}
		return s + "]";
	}

	public float[] toArray() {
		final int size = dim();
		float[] a = new float[size];
		for (int i = 0; i < size; i++) {
			a[i] = get(i);
		}
		return a;
	}

	@Override
	public void writeTo(ByteBuffer byteBuffer) {
		writeTo(byteBuffer.asFloatBuffer());
	}

	public void writeTo(DoubleBuffer doubleBuffer) {
		final int size = dim();
		for (int i = 0; i < size; i++) {
			doubleBuffer.put(get(i));
		}
	}

	public void writeTo(FloatBuffer floatBuffer) {
		final int size = dim();
		for (int i = 0; i < size; i++) {
			floatBuffer.put(get(i));
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		final int size = dim();
		for (int i = 0; i < size; i++)
			result = prime * result + Float.hashCode(get(i));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Vec))
			return false;
		Vec<?, ?> other = (Vec<?, ?>) obj;
		final int size = dim();
		if (size != other.dim())
			return false;
		for (int i = 0; i < size; i++)
			if (get(i) != other.get(i))
				return false;
		return true;
	}

	@Override
	public abstract T clone();

	protected abstract T _new();

	protected T forEach(Func1_F f) {
		T ret = _new();
		final int size = dim();
		for (int i = 0; i < size; i++) {
			ret.set(i, f.calc(get(i)));
		}
		return ret;
	}

	protected T forEach(T v, Func2_F f) {
		T ret = _new();
		final int size = dim();
		if (Utils.CHECKS) {
			if (size != v.dim())
				throw new IllegalArgumentException("In order to combine two Vectors they have to be of the same dimension!");
		}
		for (int i = 0; i < size; i++) {
			ret.set(i, f.calc(get(i), v.get(i)));
		}
		return ret;
	}

	protected T forEach(float n, Func2_F f) {
		T ret = _new();
		final int size = dim();
		for (int i = 0; i < size; i++) {
			ret.set(i, f.calc(get(i), n));
		}
		return ret;
	}

	protected T forEach(T v2, T v3, Func3_F f) {
		T ret = _new();
		final int size = dim();
		if (Utils.CHECKS) {
			if (size != v2.dim() || size != v3.dim())
				throw new IllegalArgumentException("In order to combine three Vectors they have to be of the same dimension!");
		}
		for (int i = 0; i < size; i++) {
			ret.set(i, f.calc(get(i), v2.get(i), v3.get(i)));
		}
		return ret;
	}

	protected T forEach(T v, float n, Func3_F f) {
		T ret = _new();
		final int size = dim();
		if (Utils.CHECKS) {
			if (size != v.dim())
				throw new IllegalArgumentException("In order to combine two Vectors they have to be of the same dimension!");
		}
		for (int i = 0; i < size; i++) {
			ret.set(i, f.calc(get(i), v.get(i), n));
		}
		return ret;
	}

	protected T forEach(float n1, float n2, Func3_F f) {
		T ret = _new();
		final int size = dim();
		for (int i = 0; i < size; i++) {
			ret.set(i, f.calc(get(i), n1, n2));
		}
		return ret;
	}

	public static AVec1 Vec1(float[] array, int i1) {
		return new AVec1(array, i1);
	}

	public static VVec1 Vec1(Vec<?, ?> v, int i1) {
		return new VVec1(v, i1);
	}

	public static RVec1 Vec1() {
		return Vec1(0);
	}

	public static RVec1 Vec1(float x) {
		return new RVec1(x);
	}

	public static RVec1 Vec1(Vec1 v) {
		return new RVec1(v.x());
	}

	public static RVec1 Vec1(Vec2 v) {
		return new RVec1(v.x());
	}

	public static RVec1 Vec1(Vec3 v) {
		return new RVec1(v.x());
	}

	public static RVec1 Vec1(Vec4 v) {
		return new RVec1(v.x());
	}

	public static RVec1 Vec1(VecN v) {
		return new RVec1(v.get(0));
	}

	public static AVec2 Vec2(float[] array, int i1, int i2) {
		return new AVec2(array, i1, i2);
	}

	public static VVec2 Vec2(Vec<?, ?> v, int i1, int i2) {
		return new VVec2(v, i1, i2);
	}

	public static RVec2 Vec2() {
		return Vec2(0);
	}

	public static RVec2 Vec2(float x) {
		return new RVec2(x, x);
	}

	public static RVec2 Vec2(float x, float y) {
		return new RVec2(x, y);
	}

	public static RVec2 Vec2(float x, Vec1 y) {
		return new RVec2(x, y.x());
	}

	public static RVec2 Vec2(Vec1 x, float y) {
		return new RVec2(x.x(), y);
	}

	public static RVec2 Vec2(Vec1 x, Vec1 y) {
		return new RVec2(x.x(), y.x());
	}

	public static RVec2 Vec2(Vec2 v) {
		return new RVec2(v.x(), v.y());
	}

	public static RVec2 Vec2(Vec3 v) {
		return new RVec2(v.x(), v.y());
	}

	public static RVec2 Vec2(Vec4 v) {
		return new RVec2(v.x(), v.y());
	}

	public static RVec2 Vec2(VecN v) {
		return new RVec2(v.get(0), v.get(1));
	}

	public static AVec3 Vec3(float[] array, int i1, int i2, int i3) {
		return new AVec3(array, i1, i2, i3);
	}

	public static VVec3 Vec3(Vec<?, ?> v, int i1, int i2, int i3) {
		return new VVec3(v, i1, i2, i3);
	}

	public static RVec3 Vec3() {
		return Vec3(0);
	}

	public static RVec3 Vec3(float x) {
		return new RVec3(x, x, x);
	}

	public static RVec3 Vec3(float x, float y, float z) {
		return new RVec3(x, y, z);
	}

	public static RVec3 Vec3(Vec1 x, float y, float z) {
		return new RVec3(x.x(), y, z);
	}

	public static RVec3 Vec3(float x, Vec1 y, float z) {
		return new RVec3(x, y.x(), z);
	}

	public static RVec3 Vec3(float x, float y, Vec1 z) {
		return new RVec3(x, y, z.x());
	}

	public static RVec3 Vec3(Vec1 x, float y, Vec1 z) {
		return new RVec3(x.x(), y, z.x());
	}

	public static RVec3 Vec3(Vec1 x, Vec1 y, float z) {
		return new RVec3(x.x(), y.x(), z);
	}

	public static RVec3 Vec3(float x, Vec1 y, Vec1 z) {
		return new RVec3(x, y.x(), z.x());
	}

	public static RVec3 Vec3(Vec1 x, Vec1 y, Vec1 z) {
		return new RVec3(x.x(), y.x(), z.x());
	}

	public static RVec3 Vec3(Vec2 v, float z) {
		return new RVec3(v.x(), v.y(), z);
	}

	public static RVec3 Vec3(Vec2 v, Vec1 z) {
		return new RVec3(v.x(), v.y(), z.x());
	}

	public static RVec3 Vec3(float x, Vec2 v) {
		return new RVec3(x, v.x(), v.y());
	}

	public static RVec3 Vec3(Vec1 x, Vec2 v) {
		return new RVec3(x.x(), v.x(), v.y());
	}

	public static RVec3 Vec3(Vec3 v) {
		return new RVec3(v.x(), v.y(), v.z());
	}

	public static RVec3 Vec3(Vec4 v) {
		return new RVec3(v.x(), v.y(), v.z());
	}

	public static RVec3 Vec3(VecN v) {
		return new RVec3(v.get(0), v.get(1), v.get(2));
	}

	public static AVec4 Vec4(float[] array, int i1, int i2, int i3, int i4) {
		return new AVec4(array, i1, i2, i3, i4);
	}

	public static VVec4 Vec4(Vec<?, ?> v, int i1, int i2, int i3, int i4) {
		return new VVec4(v, i1, i2, i3, i4);
	}

	public static Vec4 Vec4() {
		return Vec4(0);
	}

	public static Vec4 Vec4(float x) {
		return new RVec4(x, x, x, x);
	}

	public static Vec4 Vec4(float x, float y, float z, float w) {
		return new RVec4(x, y, z, w);
	}

	public static Vec4 Vec4(Vec1 x, float y, float z, float w) {
		return new RVec4(x.x(), y, z, w);
	}

	public static Vec4 Vec4(float x, Vec1 y, float z, float w) {
		return new RVec4(x, y.x(), z, w);
	}

	public static Vec4 Vec4(float x, float y, Vec1 z, float w) {
		return new RVec4(x, y, z.x(), w);
	}

	public static Vec4 Vec4(float x, float y, float z, Vec1 w) {
		return new RVec4(x, y, z, w.x());
	}

	public static Vec4 Vec4(Vec1 x, Vec1 y, float z, float w) {
		return new RVec4(x.x(), y.x(), z, w);
	}

	public static Vec4 Vec4(Vec1 x, float y, Vec1 z, float w) {
		return new RVec4(x.x(), y, z.x(), w);
	}

	public static Vec4 Vec4(Vec1 x, float y, float z, Vec1 w) {
		return new RVec4(x.x(), y, z, w.x());
	}

	public static Vec4 Vec4(float x, Vec1 y, Vec1 z, float w) {
		return new RVec4(x, y.x(), z.x(), w);
	}

	public static Vec4 Vec4(float x, Vec1 y, float z, Vec1 w) {
		return new RVec4(x, y.x(), z, w.x());
	}

	public static Vec4 Vec4(float x, float y, Vec1 z, Vec1 w) {
		return new RVec4(x, y, z.x(), w.x());
	}

	public static Vec4 Vec4(Vec1 x, Vec1 y, Vec1 z, float w) {
		return new RVec4(x.x(), y.x(), z.x(), w);
	}

	public static Vec4 Vec4(Vec1 x, Vec1 y, float z, Vec1 w) {
		return new RVec4(x.x(), y.x(), z, w.x());
	}

	public static Vec4 Vec4(Vec1 x, float y, Vec1 z, Vec1 w) {
		return new RVec4(x.x(), y, z.x(), w.x());
	}

	public static Vec4 Vec4(float x, Vec1 y, Vec1 z, Vec1 w) {
		return new RVec4(x, y.x(), z.x(), w.x());
	}

	public static Vec4 Vec4(Vec1 x, Vec1 y, Vec1 z, Vec1 w) {
		return new RVec4(x.x(), y.x(), z.x(), w.x());
	}

	public static Vec4 Vec4(Vec2 v, float z, float w) {
		return new RVec4(v.x(), v.y(), z, w);
	}

	public static Vec4 Vec4(Vec2 v, Vec1 z, float w) {
		return new RVec4(v.x(), v.y(), z.x(), w);
	}

	public static Vec4 Vec4(Vec2 v, float z, Vec1 w) {
		return new RVec4(v.x(), v.y(), z, w.x());
	}

	public static Vec4 Vec4(Vec2 v, Vec1 z, Vec1 w) {
		return new RVec4(v.x(), v.y(), z.x(), w.x());
	}

	public static Vec4 Vec4(float x, Vec2 v, float w) {
		return new RVec4(x, v.x(), v.y(), w);
	}

	public static Vec4 Vec4(Vec1 x, Vec2 v, float w) {
		return new RVec4(x.x(), v.x(), v.y(), w);
	}

	public static Vec4 Vec4(float x, Vec2 v, Vec1 w) {
		return new RVec4(x, v.x(), v.y(), w.x());
	}

	public static Vec4 Vec4(Vec1 x, Vec2 v, Vec1 w) {
		return new RVec4(x.x(), v.x(), v.y(), w.x());
	}

	public static Vec4 Vec4(float x, float y, Vec2 v) {
		return new RVec4(x, y, v.x(), v.y());
	}

	public static Vec4 Vec4(Vec1 x, float y, Vec2 v) {
		return new RVec4(x.x(), y, v.x(), v.y());
	}

	public static Vec4 Vec4(float x, Vec1 y, Vec2 v) {
		return new RVec4(x, y.x(), v.x(), v.y());
	}

	public static Vec4 Vec4(Vec1 x, Vec1 y, Vec2 v) {
		return new RVec4(x.x(), y.x(), v.x(), v.y());
	}

	public static Vec4 Vec4(Vec2 v1, Vec2 v2) {
		return new RVec4(v1.x(), v1.y(), v2.x(), v2.y());
	}

	public static Vec4 Vec4(Vec3 v, float w) {
		return new RVec4(v.x(), v.y(), v.z(), w);
	}

	public static Vec4 Vec4(Vec3 v, Vec1 w) {
		return new RVec4(v.x(), v.y(), v.z(), w.x());
	}

	public static Vec4 Vec4(float x, Vec3 v) {
		return new RVec4(x, v.x(), v.y(), v.z());
	}

	public static Vec4 Vec4(Vec1 x, Vec3 v) {
		return new RVec4(x.x(), v.x(), v.y(), v.z());
	}

	public static Vec4 Vec4(Vec4 v) {
		return new RVec4(v.x(), v.y(), v.z(), v.w());
	}

	public static RVec4 Vec4(VecN v) {
		return new RVec4(v.get(0), v.get(1), v.get(2), v.get(3));
	}

	public static <V extends Vec<V, ?>> V VecX(int dimension) {
		return VecX(dimension, 0);
	}

	public static <V extends Vec<V, ?>> V VecX(int dimension, float pad) {
		switch (dimension) {
		case 0:
			throw new IllegalArgumentException();
		case 1:
			return Utils.unchecked(Vec1(pad));
		case 2:
			return Utils.unchecked(Vec2(pad));
		case 3:
			return Utils.unchecked(Vec3(pad));
		case 4:
			return Utils.unchecked(Vec4(pad));
		default:
			return Utils.unchecked(new RVecN(dimension, pad));
		}
	}

	public static <V extends Vec<V, ?>> V VecXfixed(int dimension, Vec<?, ?> vec) {
		float[] a = new float[dimension];
		int min = vec.dim();
		if (min > dimension) {
			min = dimension;
		}
		for (int i = 0; i < min; i++) {
			a[i] = vec.get(i);
		}
		return VecX(a);
	}

	public static <V extends Vec<V, ?>> V VecXfixed(int dimension, float... initial) {
		float[] a = new float[dimension];
		int b = initial.length;
		System.arraycopy(initial, 0, a, 0, (dimension <= b) ? dimension : b);
		return VecX(a);
	}

	public static <V extends Vec<V, ?>> V VecX(float... data) {
		switch (data.length) {
		case 0:
			throw new IllegalArgumentException();
		case 1:
			return Utils.unchecked(Vec1(data[0]));
		case 2:
			return Utils.unchecked(Vec2(data[0], data[1]));
		case 3:
			return Utils.unchecked(Vec3(data[0], data[1], data[2]));
		case 4:
			return Utils.unchecked(Vec4(data[0], data[1], data[2], data[3]));
		default:
			return Utils.unchecked(new RVecN(data));
		}
	}

	public static <V extends Vec<V, ?>> V VecX(Vec<?, ?> v, int... indices) {
		final int size = indices.length;
		final int size1 = v.dim();
		for (int i = 0; i < size; i++) {
			if (indices[i] < 0 || indices[i] >= size1) {
				throw new IllegalArgumentException();
			}
		}
		return VecX_checked(v, indices);
	}

	protected static <V extends Vec<V, ?>> V VecX_checked(Vec<?, ?> v, int[] indices) {
		switch (indices.length) {
		case 0:
			throw new IllegalArgumentException();
		case 1:
			return Utils.unchecked(new VVec1(v, indices));
		case 2:
			return Utils.unchecked(new VVec2(v, indices));
		case 3:
			return Utils.unchecked(new VVec3(v, indices));
		case 4:
			return Utils.unchecked(new VVec4(v, indices));
		default:
			return Utils.unchecked(new VVecN(v, indices));
		}
	}

	public static <V extends Vec<V, ?>> V VecX(float[] array, int... indices) {
		final int size = indices.length;
		final int size1 = array.length;
		for (int i = 0; i < size; i++) {
			if (indices[i] < 0 || indices[i] >= size1) {
				throw new IllegalArgumentException();
			}
		}
		return VecX_checked(array, indices);
	}

	protected static <V extends Vec<V, ?>> V VecX_checked(float[] array, int[] indices) {
		switch (indices.length) {
		case 0:
			throw new IllegalArgumentException();
		case 1:
			return Utils.unchecked(new AVec1(array, indices));
		case 2:
			return Utils.unchecked(new AVec2(array, indices));
		case 3:
			return Utils.unchecked(new AVec3(array, indices));
		case 4:
			return Utils.unchecked(new AVec4(array, indices));
		default:
			return Utils.unchecked(new AVecN(array, indices));
		}
	}

	public static <V extends Vec<V, ?>> V VecX(Object... o) {
		List<Number> list = new ArrayList<Number>();
		Utils.fill(list, o);
		int size = list.size();
		float[] a = new float[size];
		for (int i = 0; i < size; i++) {
			a[i] = list.get(i).floatValue();
		}
		return VecX(a);
	}

}
