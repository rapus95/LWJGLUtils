package xor.vecmat.vec.d;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import xor.vecmat.COMP_OPS;
import xor.vecmat.Utils;
import xor.vecmat.VecMath;
import xor.vecmat.COMP_OPS.Func1_D;
import xor.vecmat.COMP_OPS.Func2_D;
import xor.vecmat.COMP_OPS.Func3_D;
import xor.vecmat.vec.VecIterator;
import xor.vecmat.vec.Vec_base;
import xor.vecmat.vec.Vec_float;
import xor.vecmat.vec.b.BVec;
import xor.vecmat.vec.f.Vec1;
import xor.vecmat.vec.f.Vec2;
import xor.vecmat.vec.f.Vec3;
import xor.vecmat.vec.f.Vec4;
import xor.vecmat.vec.f.VecN;

public abstract class DVec<T extends DVec<T, B>, B extends BVec<B>> implements Vec_float<T, B, Double> {

	public abstract double get(int index);

	@Override
	public Double getW(int index) {
		return Double.valueOf(get(index));
	}

	public abstract void set(int index, double value);

	@Override
	public void set(int index, Double value) {
		set(index, value.doubleValue());
	}

	public double get(char c) {
		return get(Utils.getIndex(c));
	}

	@Override
	public Double getW(char c) {
		return Double.valueOf(get(c));
	}

	public void set(char c, double value) {
		set(Utils.getIndex(c), value);
	}

	@Override
	public void set(char c, Double value) {
		set(c, value.doubleValue());
	}

	public <V extends DVec<V, ?>> V get(CharSequence t){
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
		return DVecX_checked(this, indices);
	}
	
	@Override
	public DVec<?, ?> getS(CharSequence t) {
		return get(t);
	}

	@Override
	public void set(CharSequence t, Vec_base<?, ?, Double> value) {
		final int size = t.length();
		if (Utils.CHECKS && size != value.dim()) {
			throw new IllegalArgumentException("String length have to equal vector length");
		}
		if (value instanceof DVec<?, ?>) {
			DVec<?, ?> v = (DVec<?, ?>) value;
			for (int i = 0; i < size; i++) {
				char c = t.charAt(i);
				set(c, v.get(i));
			}
		} else {
			for (int i = 0; i < size; i++) {
				char c = t.charAt(i);
				set(c, value.getW(i).doubleValue());
			}
		}
	}

	public <V extends DVec<V, ?>> V getII(int... indices) {
		return DVecX(this, indices);
	}
	
	@Override
	public DVec<?, ?> getI(int... indices) {
		return DVecX(this, indices);
	}

	@Override
	public void set(Vec_base<?, ?, Double> value, int... indices) {
		set(indices, value);
	}

	@Override
	public void set(int[] indices, Vec_base<?, ?, Double> value) {
		final int size = indices.length;
		if (Utils.CHECKS && size != value.dim()) {
			throw new IllegalArgumentException("indices length have to equal vector length");
		}
		if (value instanceof DVec<?, ?>) {
			DVec<?, ?> v = (DVec<?, ?>) value;
			for (int i = 0; i < size; i++) {
				set(indices[i], v.get(i));
			}
		} else {
			for (int i = 0; i < size; i++) {
				set(indices[i], value.getW(i).doubleValue());
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

	public T add_scaled(T v, double scale) {
		if (scale == 0)
			return clone();
		return forEach(v, scale, COMP_OPS.ADD_SCALED_D);
	}
	
	public T add_scaled(T v, Double scale) {
		return add_scaled(v, scale.doubleValue());
	}

	public T add_scaled(T v, T scale) {
		return forEach(v, scale, COMP_OPS.ADD_SCALED_D);
	}

	
	public T addScaledAndZeroIfNull(T v, double scale) {
		if (v == null || scale == 0)
			return clone();
		return forEach(v, scale, COMP_OPS.ADD_SCALED_D);
	}
	
	public T add(double n) {
		if (n == 0)
			return clone();
		return forEach(n, COMP_OPS.ADD_D);
	}
	
	public T add(Double n) {
		return add(n.doubleValue());
	}

	public T add(T v) {
		return forEach(v, COMP_OPS.ADD_D);
	}

	public T addZeroIfNull(T v) {
		if (v == null)
			return clone();
		return forEach(v, COMP_OPS.ADD_D);
	}

	public T sub(double n) {
		if (n == 0)
			return clone();
		return forEach(n, COMP_OPS.SUB_D);
	}

	public T sub(Double n) {
		return sub(n.doubleValue());
	}
	
	public T sub(T v) {
		return forEach(v, COMP_OPS.SUB_D);
	}

	public T sub2(double n) {
		if (n == 0)
			return clone();
		return forEach(n, COMP_OPS.SUB2_D);
	}

	public T sub2(Double n) {
		return sub2(n.doubleValue());
	}
	
	public T neg() {
		return forEach(COMP_OPS.NEG_D);
	}

	public T mul(double n) {
		if (n == 0)
			return _new();
		if (n == 1)
			return clone();
		return forEach(n, COMP_OPS.MUL_D);
	}

	public T mul(Double n) {
		return mul(n.doubleValue());
	}
	
	public T mul(T v) {
		return forEach(v, COMP_OPS.MUL_D);
	}

	public T div(double n) {
		if (n == 1)
			return clone();
		return forEach(n, COMP_OPS.DIV_D);
	}

	public T div(Double n) {
		return div(n.doubleValue());
	}
	
	public T div(T v) {
		return forEach(v, COMP_OPS.DIV_D);
	}

	public T div2(double n) {
		return forEach(n, COMP_OPS.DIV2_D);
	}
	
	public T div2(Double n) {
		return div2(n.doubleValue());
	}

	public T mod(double n) {
		return forEach(n, COMP_OPS.MOD_D);
	}
	
	public T mod(Double n) {
		return mod(n.doubleValue());
	}

	public T mod(T v) {
		return forEach(v, COMP_OPS.MOD_D);
	}

	public T mod2(double n) {
		return forEach(n, COMP_OPS.MOD2_D);
	}
	
	public T mod2(Double n) {
		return mod2(n.doubleValue());
	}

	public T pow(double n) {
		if (n == 0)
			return DVecX(dim(), 1);
		if (n == 1)
			return clone();
		return forEach(n, COMP_OPS.POW_D);
	}

	public T pow(Double n) {
		return pow(n.doubleValue());
	}
	
	public T pow(T v) {
		return forEach(v, COMP_OPS.POW_D);
	}

	public T pow2(double n) {
		if (n == 1)
			return DVecX(dim(), 1);
		return forEach(n, COMP_OPS.POW2_D);
	}

	public T pow2(Double n) {
		return pow(n.doubleValue());
	}
	
	public T radians() {
		return forEach(COMP_OPS.RADIANS_D);
	}

	public T degrees() {
		return forEach(COMP_OPS.DEGREES_D);
	}

	public T sin() {
		return forEach(COMP_OPS.SIN_D);
	}

	public T cos() {
		return forEach(COMP_OPS.COS_D);
	}

	public T tan() {
		return forEach(COMP_OPS.TAN_D);
	}

	public T asin() {
		return forEach(COMP_OPS.ASIN_D);
	}

	public T acos() {
		return forEach(COMP_OPS.ACOS_D);
	}

	public T atan(T x) {
		return forEach(x, COMP_OPS.ATAN2_D);
	}

	public T atan() {
		return forEach(COMP_OPS.ATAN_D);
	}

	public T sinh() {
		return forEach(COMP_OPS.SINH_D);
	}

	public T cosh() {
		return forEach(COMP_OPS.COSH_D);
	}

	public T tanh() {
		return forEach(COMP_OPS.TANH_D);
	}

	public T asinh() {
		return forEach(COMP_OPS.ASINH_D);
	}

	public T acosh() {
		return forEach(COMP_OPS.ACOSH_D);
	}

	public T atanh() {
		return forEach(COMP_OPS.ATANH_D);
	}

	public T exp() {
		return forEach(COMP_OPS.EXP_D);
	}

	public T log() {
		return forEach(COMP_OPS.LOG_D);
	}

	public T exp2() {
		return forEach(COMP_OPS.EXP2_D);
	}

	public T log2() {
		return forEach(COMP_OPS.LOG2_D);
	}

	public T sqrt() {
		return forEach(COMP_OPS.SQRT_D);
	}

	public T inversesqrt() {
		return forEach(COMP_OPS.INVERSESQRT_D);
	}

	public T abs() {
		return forEach(COMP_OPS.ABS_D);
	}

	public T sign() {
		return forEach(COMP_OPS.SIGN_D);
	}

	public T roundeven() {
		return forEach(COMP_OPS.ROUNDEVEN_D);
	}

	public T round() {
		return forEach(COMP_OPS.ROUND_D);
	}

	public T trunc() {
		return forEach(COMP_OPS.TRUNC_D);
	}

	public T floor() {
		return forEach(COMP_OPS.FLOOR_D);
	}

	public T ceil() {
		return forEach(COMP_OPS.CEIL_D);
	}

	public T fract() {
		return forEach(COMP_OPS.FRACT_D);
	}

	public T min(double n) {
		return forEach(n, COMP_OPS.MIN_D);
	}
	
	public T min(Double n) {
		return min(n.doubleValue());
	}

	public T min(T v) {
		return forEach(v, COMP_OPS.MIN_D);
	}

	public T max(double n) {
		return forEach(n, COMP_OPS.MAX_D);
	}
	
	public T max(Double n) {
		return max(n.doubleValue());
	}

	public T max(T v) {
		return forEach(v, COMP_OPS.MAX_D);
	}

	public T clamp(double min, double max) {
		return forEach(min, max, COMP_OPS.CLAMP_D);
	}

	public T clamp(Double min, Double max) {
		return clamp(min.doubleValue(), max.doubleValue());
	}
	
	public T clamp(T min, T max) {
		return forEach(min, max, COMP_OPS.CLAMP_D);
	}

	public T mix(T v, double p) {
		return forEach(v, p, COMP_OPS.MIX_D);
	}
	
	public T mix(T v, Double p) {
		return mix(v, p.doubleValue());
	}

	public T mix(T v, T p) {
		return forEach(v, p, COMP_OPS.MIX_D);
	}

	public T step(double edge) {
		return forEach(edge, COMP_OPS.STEP_D);
	}

	public T step(Double edge) {
		return step(edge.doubleValue());
	}
	
	public T step(T edge) {
		return forEach(edge, COMP_OPS.STEP_D);
	}

	public T smoothstep(double edge0, double edge1) {
		return forEach(edge0, edge1, COMP_OPS.SMOOTHSTEP_D);
	}

	public T smoothstep(Double edge0, Double edge1) {
		return smoothstep(edge0.doubleValue(), edge1.doubleValue());
	}
	
	public T smoothstep(T edge0, T edge1) {
		return forEach(edge0, edge1, COMP_OPS.SMOOTHSTEP_D);
	}

	public T inc() {
		return forEach(COMP_OPS.INC_D);
	}
	
	public T dec() {
		return forEach(COMP_OPS.DEC_D);
	}
	
	public double length() {
		final int size = dim();
		double l = 0;
		for (int i = 0; i < size; i++) {
			final double g = get(i);
			l += g * g;
		}
		return (double) VecMath.sqrt(l);
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
		double ret = 0;
		for (int i = 0; i < size; i++) {
			ret += get(i) * v.get(i);
		}
		return ret;
	}

	public T normalize() {
		return div(length());
	}
	
	@Override
	public ListIterator<Double> iterator() {
		return new VecIterator<Double>(this);
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

	public double[] toArray() {
		final int size = dim();
		double[] a = new double[size];
		for (int i = 0; i < size; i++) {
			a[i] = get(i);
		}
		return a;
	}

	@Override
	public void writeTo(ByteBuffer byteBuffer) {
		writeTo(byteBuffer.asDoubleBuffer());
	}

	public void writeTo(DoubleBuffer doubleBuffer) {
		final int size = dim();
		for (int i = 0; i < size; i++) {
			doubleBuffer.put(get(i));
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		final int size = dim();
		for (int i = 0; i < size; i++)
			result = prime * result + Double.hashCode(get(i));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof DVec))
			return false;
		DVec<?, ?> other = (DVec<?, ?>) obj;
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

	protected T forEach(Func1_D f) {
		T ret = _new();
		final int size = dim();
		for (int i = 0; i < size; i++) {
			ret.set(i, f.calc(get(i)));
		}
		return ret;
	}

	protected T forEach(T v, Func2_D f) {
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

	protected T forEach(double n, Func2_D f) {
		T ret = _new();
		final int size = dim();
		for (int i = 0; i < size; i++) {
			ret.set(i, f.calc(get(i), n));
		}
		return ret;
	}

	protected T forEach(T v2, T v3, Func3_D f) {
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

	protected T forEach(T v, double n, Func3_D f) {
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

	protected T forEach(double n1, double n2, Func3_D f) {
		T ret = _new();
		final int size = dim();
		for (int i = 0; i < size; i++) {
			ret.set(i, f.calc(get(i), n1, n2));
		}
		return ret;
	}
	
	
	
	
	
	public static DAVec1 DVec1(double[] array, int i1) {
		return new DAVec1(array, i1);
	}

	public static DVVec1 DVec1(DVec<?, ?> v, int i1) {
		return new DVVec1(v, i1);
	}
	
	public static DRVec1 DVec1() {
		return DVec1(0);
	}

	public static DRVec1 DVec1(double x) {
		return new DRVec1(x);
	}

	public static DRVec1 DVec1(Vec1 v) {
		return new DRVec1(v.x());
	}
	
	public static DRVec1 DVec1(Vec2 v) {
		return new DRVec1(v.x());
	}

	public static DRVec1 DVec1(Vec3 v) {
		return new DRVec1(v.x());
	}

	public static DRVec1 DVec1(Vec4 v) {
		return new DRVec1(v.x());
	}

	public static DRVec1 DVec1(VecN v) {
		return new DRVec1(v.get(0));
	}
	
	public static DAVec2 DVec2(double[] array, int i1, int i2) {
		return new DAVec2(array, i1, i2);
	}

	public static DVVec2 DVec2(DVec<?, ?> v, int i1, int i2) {
		return new DVVec2(v, i1, i2);
	}

	public static DRVec2 DVec2() {
		return DVec2(0);
	}

	public static DRVec2 DVec2(double x) {
		return new DRVec2(x, x);
	}

	public static DRVec2 DVec2(double x, double y) {
		return new DRVec2(x, y);
	}

	public static DRVec2 DVec2(double x, DVec1 y) {
		return new DRVec2(x, y.x());
	}
	
	public static DRVec2 DVec2(Vec1 x, double y) {
		return new DRVec2(x.x(), y);
	}
	
	public static DRVec2 DVec2(Vec1 x, DVec1 y) {
		return new DRVec2(x.x(), y.x());
	}
	
	public static DRVec2 DVec2(Vec2 v) {
		return new DRVec2(v.x(), v.y());
	}

	public static DRVec2 DVec2(Vec3 v) {
		return new DRVec2(v.x(), v.y());
	}

	public static DRVec2 DVec2(Vec4 v) {
		return new DRVec2(v.x(), v.y());
	}

	public static DRVec2 DVec2(VecN v) {
		return new DRVec2(v.get(0), v.get(1));
	}

	public static DAVec3 DVec3(double[] array, int i1, int i2, int i3) {
		return new DAVec3(array, i1, i2, i3);
	}

	public static DVVec3 DVec3(DVec<?, ?> v, int i1, int i2, int i3) {
		return new DVVec3(v, i1, i2, i3);
	}

	public static DRVec3 DVec3() {
		return DVec3(0);
	}

	public static DRVec3 DVec3(double x) {
		return new DRVec3(x, x, x);
	}

	public static DRVec3 DVec3(double x, double y, double z) {
		return new DRVec3(x, y, z);
	}
	
	public static DRVec3 DVec3(Vec1 x, double y, double z) {
		return new DRVec3(x.x(), y, z);
	}
	
	public static DRVec3 DVec3(double x, DVec1 y, double z) {
		return new DRVec3(x, y.x(), z);
	}
	
	public static DRVec3 DVec3(double x, double y, DVec1 z) {
		return new DRVec3(x, y, z.x());
	}
	
	public static DRVec3 DVec3(Vec1 x, double y, DVec1 z) {
		return new DRVec3(x.x(), y, z.x());
	}
	
	public static DRVec3 DVec3(Vec1 x, DVec1 y, double z) {
		return new DRVec3(x.x(), y.x(), z);
	}
	
	public static DRVec3 DVec3(double x, DVec1 y, DVec1 z) {
		return new DRVec3(x, y.x(), z.x());
	}
	
	public static DRVec3 DVec3(Vec1 x, DVec1 y, DVec1 z) {
		return new DRVec3(x.x(), y.x(), z.x());
	}

	public static DRVec3 DVec3(Vec2 v, double z) {
		return new DRVec3(v.x(), v.y(), z);
	}
	
	public static DRVec3 DVec3(Vec2 v, DVec1 z) {
		return new DRVec3(v.x(), v.y(), z.x());
	}

	public static DRVec3 DVec3(double x, DVec2 v) {
		return new DRVec3(x, v.x(), v.y());
	}
	
	public static DRVec3 DVec3(Vec1 x, DVec2 v) {
		return new DRVec3(x.x(), v.x(), v.y());
	}

	public static DRVec3 DVec3(Vec3 v) {
		return new DRVec3(v.x(), v.y(), v.z());
	}

	public static DRVec3 DVec3(Vec4 v) {
		return new DRVec3(v.x(), v.y(), v.z());
	}

	public static DRVec3 DVec3(VecN v) {
		return new DRVec3(v.get(0), v.get(1), v.get(2));
	}

	public static DAVec4 DVec4(double[] array, int i1, int i2, int i3, int i4) {
		return new DAVec4(array, i1, i2, i3, i4);
	}

	public static DVVec4 DVec4(DVec<?, ?> v, int i1, int i2, int i3, int i4) {
		return new DVVec4(v, i1, i2, i3, i4);
	}

	public static DVec4 DVec4() {
		return DVec4(0);
	}

	public static DVec4 DVec4(double x) {
		return new DRVec4(x, x, x, x);
	}

	public static DVec4 DVec4(double x, double y, double z, double w) {
		return new DRVec4(x, y, z, w);
	}
	
	public static DVec4 DVec4(Vec1 x, double y, double z, double w) {
		return new DRVec4(x.x(), y, z, w);
	}
	
	public static DVec4 DVec4(double x, DVec1 y, double z, double w) {
		return new DRVec4(x, y.x(), z, w);
	}
	
	public static DVec4 DVec4(double x, double y, DVec1 z, double w) {
		return new DRVec4(x, y, z.x(), w);
	}
	
	public static DVec4 DVec4(double x, double y, double z, DVec1 w) {
		return new DRVec4(x, y, z, w.x());
	}
	
	public static DVec4 DVec4(Vec1 x, DVec1 y, double z, double w) {
		return new DRVec4(x.x(), y.x(), z, w);
	}
	
	public static DVec4 DVec4(Vec1 x, double y, DVec1 z, double w) {
		return new DRVec4(x.x(), y, z.x(), w);
	}
	
	public static DVec4 DVec4(Vec1 x, double y, double z, DVec1 w) {
		return new DRVec4(x.x(), y, z, w.x());
	}
	
	public static DVec4 DVec4(double x, DVec1 y, DVec1 z, double w) {
		return new DRVec4(x, y.x(), z.x(), w);
	}
	
	public static DVec4 DVec4(double x, DVec1 y, double z, DVec1 w) {
		return new DRVec4(x, y.x(), z, w.x());
	}
	
	public static DVec4 DVec4(double x, double y, DVec1 z, DVec1 w) {
		return new DRVec4(x, y, z.x(), w.x());
	}
	
	public static DVec4 DVec4(Vec1 x, DVec1 y, DVec1 z, double w) {
		return new DRVec4(x.x(), y.x(), z.x(), w);
	}
	
	public static DVec4 DVec4(Vec1 x, DVec1 y, double z, DVec1 w) {
		return new DRVec4(x.x(), y.x(), z, w.x());
	}
	
	public static DVec4 DVec4(Vec1 x, double y, DVec1 z, DVec1 w) {
		return new DRVec4(x.x(), y, z.x(), w.x());
	}
	
	public static DVec4 DVec4(double x, DVec1 y, DVec1 z, DVec1 w) {
		return new DRVec4(x, y.x(), z.x(), w.x());
	}
	
	public static DVec4 DVec4(Vec1 x, DVec1 y, DVec1 z, DVec1 w) {
		return new DRVec4(x.x(), y.x(), z.x(), w.x());
	}

	public static DVec4 DVec4(Vec2 v, double z, double w) {
		return new DRVec4(v.x(), v.y(), z, w);
	}
	
	public static DVec4 DVec4(Vec2 v, DVec1 z, double w) {
		return new DRVec4(v.x(), v.y(), z.x(), w);
	}
	
	public static DVec4 DVec4(Vec2 v, double z, DVec1 w) {
		return new DRVec4(v.x(), v.y(), z, w.x());
	}
	
	public static DVec4 DVec4(Vec2 v, DVec1 z, DVec1 w) {
		return new DRVec4(v.x(), v.y(), z.x(), w.x());
	}

	public static DVec4 DVec4(double x, DVec2 v, double w) {
		return new DRVec4(x, v.x(), v.y(), w);
	}
	
	public static DVec4 DVec4(Vec1 x, DVec2 v, double w) {
		return new DRVec4(x.x(), v.x(), v.y(), w);
	}
	
	public static DVec4 DVec4(double x, DVec2 v, DVec1 w) {
		return new DRVec4(x, v.x(), v.y(), w.x());
	}
	
	public static DVec4 DVec4(Vec1 x, DVec2 v, DVec1 w) {
		return new DRVec4(x.x(), v.x(), v.y(), w.x());
	}

	public static DVec4 DVec4(double x, double y, DVec2 v) {
		return new DRVec4(x, y, v.x(), v.y());
	}
	
	public static DVec4 DVec4(Vec1 x, double y, DVec2 v) {
		return new DRVec4(x.x(), y, v.x(), v.y());
	}
	
	public static DVec4 DVec4(double x, DVec1 y, DVec2 v) {
		return new DRVec4(x, y.x(), v.x(), v.y());
	}
	
	public static DVec4 DVec4(Vec1 x, DVec1 y, DVec2 v) {
		return new DRVec4(x.x(), y.x(), v.x(), v.y());
	}

	public static DVec4 DVec4(Vec2 v1, DVec2 v2) {
		return new DRVec4(v1.x(), v1.y(), v2.x(), v2.y());
	}

	public static DVec4 DVec4(Vec3 v, double w) {
		return new DRVec4(v.x(), v.y(), v.z(), w);
	}
	
	public static DVec4 DVec4(Vec3 v, DVec1 w) {
		return new DRVec4(v.x(), v.y(), v.z(), w.x());
	}

	public static DVec4 DVec4(double x, DVec3 v) {
		return new DRVec4(x, v.x(), v.y(), v.z());
	}
	
	public static DVec4 DVec4(Vec1 x, DVec3 v) {
		return new DRVec4(x.x(), v.x(), v.y(), v.z());
	}

	public static DVec4 DVec4(Vec4 v) {
		return new DRVec4(v.x(), v.y(), v.z(), v.w());
	}

	public static DRVec4 DVec4(VecN v) {
		return new DRVec4(v.get(0), v.get(1), v.get(2), v.get(3));
	}
	
	public static <V extends DVec<V, ?>> V DVecX(int dimension) {
		return DVecX(dimension, 0);
	}

	public static <V extends DVec<V, ?>> V DVecX(int dimension, double pad) {
		switch (dimension) {
		case 0:
			throw new IllegalArgumentException();
		case 1:
			return Utils.unchecked(DVec1(pad));
		case 2:
			return Utils.unchecked(DVec2(pad));
		case 3:
			return Utils.unchecked(DVec3(pad));
		case 4:
			return Utils.unchecked(DVec4(pad));
		default:
			return Utils.unchecked(new DRVecN(dimension, pad));
		}
	}

	public static <V extends DVec<V, ?>> V DVecXfixed(int dimension, DVec<?, ?> vec) {
		double[] a = new double[dimension];
		int min = vec.dim();
		if (min > dimension) {
			min = dimension;
		}
		for (int i = 0; i < min; i++) {
			a[i] = vec.get(i);
		}
		return DVecX(a);
	}

	public static <V extends DVec<V, ?>> V VecXfixed(int dimension, double... initial) {
		double[] a = new double[dimension];
		int b = initial.length;
		System.arraycopy(initial, 0, a, 0, (dimension <= b) ? dimension : b);
		return DVecX(a);
	}

	public static <V extends DVec<V, ?>> V DVecX(double... data) {
		switch (data.length) {
		case 0:
			throw new IllegalArgumentException();
		case 1:
			return Utils.unchecked(DVec1(data[0]));
		case 2:
			return Utils.unchecked(DVec2(data[0], data[1]));
		case 3:
			return Utils.unchecked(DVec3(data[0], data[1], data[2]));
		case 4:
			return Utils.unchecked(DVec4(data[0], data[1], data[2], data[3]));
		default:
			return Utils.unchecked(new DRVecN(data));
		}
	}

	public static <V extends DVec<V, ?>> V DVecX(DVec<?, ?> v, int... indices) {
		final int size = indices.length;
		final int size1 = v.dim();
		for (int i = 0; i < size; i++) {
			if (indices[i] < 0 || indices[i] >= size1) {
				throw new IllegalArgumentException();
			}
		}
		return DVecX_checked(v, indices);
	}

	protected static <V extends DVec<V, ?>> V DVecX_checked(DVec<?, ?> v, int[] indices) {
		switch (indices.length) {
		case 0:
			throw new IllegalArgumentException();
		case 1:
			return Utils.unchecked(new DVVec1(v, indices));
		case 2:
			return Utils.unchecked(new DVVec2(v, indices));
		case 3:
			return Utils.unchecked(new DVVec3(v, indices));
		case 4:
			return Utils.unchecked(new DVVec4(v, indices));
		default:
			return Utils.unchecked(new DVVecN(v, indices));
		}
	}

	public static <V extends DVec<V, ?>> V DVecX(double[] array, int... indices) {
		final int size = indices.length;
		final int size1 = array.length;
		for (int i = 0; i < size; i++) {
			if (indices[i] < 0 || indices[i] >= size1) {
				throw new IllegalArgumentException();
			}
		}
		return DVecX_checked(array, indices);
	}

	protected static <V extends DVec<V, ?>> V DVecX_checked(double[] array, int[] indices) {
		switch (indices.length) {
		case 0:
			throw new IllegalArgumentException();
		case 1:
			return Utils.unchecked(new DAVec1(array, indices));
		case 2:
			return Utils.unchecked(new DAVec2(array, indices));
		case 3:
			return Utils.unchecked(new DAVec3(array, indices));
		case 4:
			return Utils.unchecked(new DAVec4(array, indices));
		default:
			return Utils.unchecked(new DAVecN(array, indices));
		}
	}
	
	public static <V extends DVec<V, ?>> V DVecX(Object... o) {
		List<Number> list = new ArrayList<Number>();
		Utils.fill(list, o);
		int size = list.size();
		double[] a = new double[size];
		for(int i=0; i<size; i++){
			a[i] = list.get(i).doubleValue();
		}
		return DVecX(a);
	}
	
}
