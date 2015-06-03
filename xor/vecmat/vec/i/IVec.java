package xor.vecmat.vec.i;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import xor.vecmat.COMP_OPS;
import xor.vecmat.Utils;
import xor.vecmat.VecMath;
import xor.vecmat.COMP_OPS.Func1_I;
import xor.vecmat.COMP_OPS.Func2_I;
import xor.vecmat.COMP_OPS.Func3_I;
import xor.vecmat.vec.VecIterator;
import xor.vecmat.vec.Vec_base;
import xor.vecmat.vec.Vec_int;
import xor.vecmat.vec.b.BVec;

public abstract class IVec<T extends IVec<T, B>, B extends BVec<B>> implements Vec_int<T, B, Integer> {

	public abstract int get(int index);

	@Override
	public Integer getW(int index) {
		return Integer.valueOf(get(index));
	}

	public abstract void set(int index, int value);

	@Override
	public void set(int index, Integer value) {
		set(index, value.intValue());
	}

	public int get(char c) {
		return get(Utils.getIndex(c));
	}

	@Override
	public Integer getW(char c) {
		return Integer.valueOf(get(c));
	}

	public void set(char c, int value) {
		set(Utils.getIndex(c), value);
	}

	@Override
	public void set(char c, Integer value) {
		set(c, value.intValue());
	}

	public <V extends IVec<V, ?>> V get(CharSequence t){
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
		return IVecX_checked(this, indices);
	}
	
	@Override
	public IVec<?, ?> getS(CharSequence t) {
		return get(t);
	}

	@Override
	public void set(CharSequence t, Vec_base<?, ?, Integer> value) {
		final int size = t.length();
		if (Utils.CHECKS && size != value.dim()) {
			throw new IllegalArgumentException("String length have to equal vector length");
		}
		if (value instanceof IVec<?, ?>) {
			IVec<?, ?> v = (IVec<?, ?>) value;
			for (int i = 0; i < size; i++) {
				char c = t.charAt(i);
				set(c, v.get(i));
			}
		} else {
			for (int i = 0; i < size; i++) {
				char c = t.charAt(i);
				set(c, value.getW(i).intValue());
			}
		}
	}

	public <V extends IVec<V, ?>> V getII(int... indices) {
		return IVecX(this, indices);
	}
	
	@Override
	public IVec<?, ?> getI(int... indices) {
		return IVecX(this, indices);
	}

	@Override
	public void set(Vec_base<?, ?, Integer> value, int... indices) {
		set(indices, value);
	}

	@Override
	public void set(int[] indices, Vec_base<?, ?, Integer> value) {
		final int size = indices.length;
		if (Utils.CHECKS && size != value.dim()) {
			throw new IllegalArgumentException("indices length have to equal vector length");
		}
		if (value instanceof IVec<?, ?>) {
			IVec<?, ?> v = (IVec<?, ?>) value;
			for (int i = 0; i < size; i++) {
				set(indices[i], v.get(i));
			}
		} else {
			for (int i = 0; i < size; i++) {
				set(indices[i], value.getW(i).intValue());
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
	
	public T add(int n) {
		if (n == 0)
			return clone();
		return forEach(n, COMP_OPS.ADD_I);
	}
	
	public T add(Integer n) {
		return add(n.intValue());
	}

	public T add(T v) {
		return forEach(v, COMP_OPS.ADD_I);
	}

	public T addZeroIfNull(T v) {
		if (v == null)
			return clone();
		return forEach(v, COMP_OPS.ADD_I);
	}

	public T sub(int n) {
		if (n == 0)
			return clone();
		return forEach(n, COMP_OPS.SUB_I);
	}

	public T sub(Integer n) {
		return sub(n.intValue());
	}
	
	public T sub(T v) {
		return forEach(v, COMP_OPS.SUB_I);
	}

	public T sub2(int n) {
		if (n == 0)
			return clone();
		return forEach(n, COMP_OPS.SUB2_I);
	}

	public T sub2(Integer n) {
		return sub2(n.intValue());
	}
	
	public T neg() {
		return forEach(COMP_OPS.NEG_I);
	}

	public T mul(int n) {
		if (n == 0)
			return _new();
		if (n == 1)
			return clone();
		return forEach(n, COMP_OPS.MUL_I);
	}

	public T mul(Integer n) {
		return mul(n.intValue());
	}
	
	public T mul(T v) {
		return forEach(v, COMP_OPS.MUL_I);
	}

	public T div(int n) {
		if (n == 1)
			return clone();
		return forEach(n, COMP_OPS.DIV_I);
	}

	public T div(Integer n) {
		return div(n.intValue());
	}
	
	public T div(T v) {
		return forEach(v, COMP_OPS.DIV_I);
	}

	public T div2(int n) {
		return forEach(n, COMP_OPS.DIV2_I);
	}
	
	public T div2(Integer n) {
		return div2(n.intValue());
	}

	public T mod(int n) {
		return forEach(n, COMP_OPS.MOD_I);
	}
	
	public T mod(Integer n) {
		return mod(n.intValue());
	}

	public T mod(T v) {
		return forEach(v, COMP_OPS.MOD_I);
	}

	public T mod2(int n) {
		return forEach(n, COMP_OPS.MOD2_I);
	}
	
	public T mod2(Integer n) {
		return mod2(n.intValue());
	}

	public T pow(int n) {
		if (n == 0)
			return IVecX(dim(), 1);
		if (n == 1)
			return clone();
		return forEach(n, COMP_OPS.POW_I);
	}

	public T pow(Integer n) {
		return pow(n.intValue());
	}
	
	public T pow(T v) {
		return forEach(v, COMP_OPS.POW_I);
	}

	public T pow2(int n) {
		if (n == 1)
			return IVecX(dim(), 1);
		return forEach(n, COMP_OPS.POW2_I);
	}

	public T pow2(Integer n) {
		return pow(n.intValue());
	}

	public T abs() {
		return forEach(COMP_OPS.ABS_I);
	}

	public T sign() {
		return forEach(COMP_OPS.SIGN_I);
	}

	public T min(int n) {
		return forEach(n, COMP_OPS.MIN_I);
	}
	
	public T min(Integer n) {
		return min(n.intValue());
	}

	public T min(T v) {
		return forEach(v, COMP_OPS.MIN_I);
	}

	public T max(int n) {
		return forEach(n, COMP_OPS.MAX_I);
	}
	
	public T max(Integer n) {
		return max(n.intValue());
	}

	public T max(T v) {
		return forEach(v, COMP_OPS.MAX_I);
	}

	public T clamp(int min, int max) {
		return forEach(min, max, COMP_OPS.CLAMP_I);
	}

	public T clamp(Integer min, Integer max) {
		return clamp(min.intValue(), max.intValue());
	}
	
	public T clamp(T min, T max) {
		return forEach(min, max, COMP_OPS.CLAMP_I);
	}

	public T step(int edge) {
		return forEach(edge, COMP_OPS.STEP_I);
	}

	public T step(Integer edge) {
		return step(edge.intValue());
	}
	
	public T step(T edge) {
		return forEach(edge, COMP_OPS.STEP_I);
	}

	public T inc() {
		return forEach(COMP_OPS.INC_I);
	}
	
	public T dec() {
		return forEach(COMP_OPS.DEC_I);
	}
	
	public T or(T other){
		return forEach(other, COMP_OPS.OR_I);
	}
	
	public T or(int other){
		return forEach(other, COMP_OPS.OR_I);
	}
	
	public T or(Integer other){
		return or(other.intValue());
	}
	
	public T and(T other){
		return forEach(other, COMP_OPS.AND_I);
	}
	
	public T and(int other){
		return forEach(other, COMP_OPS.AND_I);
	}
	
	public T and(Integer other){
		return and(other.intValue());
	}
	
	public T xor(T other){
		return forEach(other, COMP_OPS.XOR_I);
	}
	
	public T xor(int other){
		return forEach(other, COMP_OPS.XOR_I);
	}
	
	public T xor(Integer other){
		return xor(other.intValue());
	}
	
	public T shl(T other){
		return forEach(other, COMP_OPS.SHL_I);
	}
	
	public T shl(int other){
		return forEach(other, COMP_OPS.SHL_I);
	}
	
	public T shl(Integer other){
		return shl(other.intValue());
	}
	
	public T shl2(int other){
		return forEach(other, COMP_OPS.SHL2_I);
	}
	
	public T shl2(Integer other){
		return shl2(other.intValue());
	}
	
	public T shr(T other){
		return forEach(other, COMP_OPS.SHR_I);
	}
	
	public T shr(int other){
		return forEach(other, COMP_OPS.SHR_I);
	}
	
	public T shr(Integer other){
		return shr(other.intValue());
	}
	
	public T shr2(int other){
		return forEach(other, COMP_OPS.SHR2_I);
	}
	
	public T shr2(Integer other){
		return shr2(other.intValue());
	}
	
	public T ushr(T other){
		return forEach(other, COMP_OPS.USHR_I);
	}
	
	public T ushr(int other){
		return forEach(other, COMP_OPS.USHR_I);
	}
	
	public T ushr(Integer other){
		return ushr(other.intValue());
	}
	
	public T ushr2(int other){
		return forEach(other, COMP_OPS.USHR2_I);
	}
	
	public T ushr2(Integer other){
		return ushr2(other.intValue());
	}
	
	public T invert(){
		return forEach(COMP_OPS.INVERT_I);
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
		int ret = 0;
		for (int i = 0; i < size; i++) {
			ret += get(i) * v.get(i);
		}
		return ret;
	}
	
	@Override
	public ListIterator<Integer> iterator() {
		return new VecIterator<Integer>(this);
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

	public int[] toArray() {
		final int size = dim();
		int[] a = new int[size];
		for (int i = 0; i < size; i++) {
			a[i] = get(i);
		}
		return a;
	}

	@Override
	public void writeTo(ByteBuffer byteBuffer) {
		writeTo(byteBuffer.asIntBuffer());
	}

	public void writeTo(IntBuffer intBuffer) {
		final int size = dim();
		for (int i = 0; i < size; i++) {
			intBuffer.put(get(i));
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		final int size = dim();
		for (int i = 0; i < size; i++)
			result = prime * result + Integer.hashCode(get(i));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof IVec))
			return false;
		IVec<?, ?> other = (IVec<?, ?>) obj;
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

	protected T forEach(Func1_I f) {
		T ret = _new();
		final int size = dim();
		for (int i = 0; i < size; i++) {
			ret.set(i, f.calc(get(i)));
		}
		return ret;
	}

	protected T forEach(T v, Func2_I f) {
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

	protected T forEach(int n, Func2_I f) {
		T ret = _new();
		final int size = dim();
		for (int i = 0; i < size; i++) {
			ret.set(i, f.calc(get(i), n));
		}
		return ret;
	}

	protected T forEach(T v2, T v3, Func3_I f) {
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

	protected T forEach(T v, int n, Func3_I f) {
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

	protected T forEach(int n1, int n2, Func3_I f) {
		T ret = _new();
		final int size = dim();
		for (int i = 0; i < size; i++) {
			ret.set(i, f.calc(get(i), n1, n2));
		}
		return ret;
	}
	
	
	
	

	public static IAVec1 IVec1(int[] array, int i1) {
		return new IAVec1(array, i1);
	}

	public static IVVec1 IVec1(IVec<?, ?> v, int i1) {
		return new IVVec1(v, i1);
	}
	
	public static IRVec1 IVec1() {
		return IVec1(0);
	}

	public static IRVec1 IVec1(int x) {
		return new IRVec1(x);
	}

	public static IRVec1 IVec1(IVec1 v) {
		return new IRVec1(v.x());
	}
	
	public static IRVec1 IVec1(IVec2 v) {
		return new IRVec1(v.x());
	}

	public static IRVec1 IVec1(IVec3 v) {
		return new IRVec1(v.x());
	}

	public static IRVec1 IVec1(IVec4 v) {
		return new IRVec1(v.x());
	}

	public static IRVec1 IVec1(IVecN v) {
		return new IRVec1(v.get(0));
	}
	
	public static IAVec2 IVec2(int[] array, int i1, int i2) {
		return new IAVec2(array, i1, i2);
	}

	public static IVVec2 IVec2(IVec<?, ?> v, int i1, int i2) {
		return new IVVec2(v, i1, i2);
	}

	public static IRVec2 IVec2() {
		return IVec2(0);
	}

	public static IRVec2 IVec2(int x) {
		return new IRVec2(x, x);
	}

	public static IRVec2 IVec2(int x, int y) {
		return new IRVec2(x, y);
	}

	public static IRVec2 IVec2(int x, IVec1 y) {
		return new IRVec2(x, y.x());
	}
	
	public static IRVec2 IVec2(IVec1 x, int y) {
		return new IRVec2(x.x(), y);
	}
	
	public static IRVec2 IVec2(IVec1 x, IVec1 y) {
		return new IRVec2(x.x(), y.x());
	}
	
	public static IRVec2 IVec2(IVec2 v) {
		return new IRVec2(v.x(), v.y());
	}

	public static IRVec2 IVec2(IVec3 v) {
		return new IRVec2(v.x(), v.y());
	}

	public static IRVec2 IVec2(IVec4 v) {
		return new IRVec2(v.x(), v.y());
	}

	public static IRVec2 IVec2(IVecN v) {
		return new IRVec2(v.get(0), v.get(1));
	}

	public static IAVec3 IVec3(int[] array, int i1, int i2, int i3) {
		return new IAVec3(array, i1, i2, i3);
	}

	public static IVVec3 IVec3(IVec<?, ?> v, int i1, int i2, int i3) {
		return new IVVec3(v, i1, i2, i3);
	}

	public static IRVec3 IVec3() {
		return IVec3(0);
	}

	public static IRVec3 IVec3(int x) {
		return new IRVec3(x, x, x);
	}

	public static IRVec3 IVec3(int x, int y, int z) {
		return new IRVec3(x, y, z);
	}
	
	public static IRVec3 IVec3(IVec1 x, int y, int z) {
		return new IRVec3(x.x(), y, z);
	}
	
	public static IRVec3 IVec3(int x, IVec1 y, int z) {
		return new IRVec3(x, y.x(), z);
	}
	
	public static IRVec3 IVec3(int x, int y, IVec1 z) {
		return new IRVec3(x, y, z.x());
	}
	
	public static IRVec3 IVec3(IVec1 x, int y, IVec1 z) {
		return new IRVec3(x.x(), y, z.x());
	}
	
	public static IRVec3 IVec3(IVec1 x, IVec1 y, int z) {
		return new IRVec3(x.x(), y.x(), z);
	}
	
	public static IRVec3 IVec3(int x, IVec1 y, IVec1 z) {
		return new IRVec3(x, y.x(), z.x());
	}
	
	public static IRVec3 IVec3(IVec1 x, IVec1 y, IVec1 z) {
		return new IRVec3(x.x(), y.x(), z.x());
	}

	public static IRVec3 IVec3(IVec2 v, int z) {
		return new IRVec3(v.x(), v.y(), z);
	}
	
	public static IRVec3 IVec3(IVec2 v, IVec1 z) {
		return new IRVec3(v.x(), v.y(), z.x());
	}

	public static IRVec3 IVec3(int x, IVec2 v) {
		return new IRVec3(x, v.x(), v.y());
	}
	
	public static IRVec3 IVec3(IVec1 x, IVec2 v) {
		return new IRVec3(x.x(), v.x(), v.y());
	}

	public static IRVec3 IVec3(IVec3 v) {
		return new IRVec3(v.x(), v.y(), v.z());
	}

	public static IRVec3 IVec3(IVec4 v) {
		return new IRVec3(v.x(), v.y(), v.z());
	}

	public static IRVec3 IVec3(IVecN v) {
		return new IRVec3(v.get(0), v.get(1), v.get(2));
	}

	public static IAVec4 IVec4(int[] array, int i1, int i2, int i3, int i4) {
		return new IAVec4(array, i1, i2, i3, i4);
	}

	public static IVVec4 IVec4(IVec<?, ?> v, int i1, int i2, int i3, int i4) {
		return new IVVec4(v, i1, i2, i3, i4);
	}

	public static IVec4 IVec4() {
		return IVec4(0);
	}

	public static IVec4 IVec4(int x) {
		return new IRVec4(x, x, x, x);
	}

	public static IVec4 IVec4(int x, int y, int z, int w) {
		return new IRVec4(x, y, z, w);
	}
	
	public static IVec4 IVec4(IVec1 x, int y, int z, int w) {
		return new IRVec4(x.x(), y, z, w);
	}
	
	public static IVec4 IVec4(int x, IVec1 y, int z, int w) {
		return new IRVec4(x, y.x(), z, w);
	}
	
	public static IVec4 IVec4(int x, int y, IVec1 z, int w) {
		return new IRVec4(x, y, z.x(), w);
	}
	
	public static IVec4 IVec4(int x, int y, int z, IVec1 w) {
		return new IRVec4(x, y, z, w.x());
	}
	
	public static IVec4 IVec4(IVec1 x, IVec1 y, int z, int w) {
		return new IRVec4(x.x(), y.x(), z, w);
	}
	
	public static IVec4 IVec4(IVec1 x, int y, IVec1 z, int w) {
		return new IRVec4(x.x(), y, z.x(), w);
	}
	
	public static IVec4 IVec4(IVec1 x, int y, int z, IVec1 w) {
		return new IRVec4(x.x(), y, z, w.x());
	}
	
	public static IVec4 IVec4(int x, IVec1 y, IVec1 z, int w) {
		return new IRVec4(x, y.x(), z.x(), w);
	}
	
	public static IVec4 IVec4(int x, IVec1 y, int z, IVec1 w) {
		return new IRVec4(x, y.x(), z, w.x());
	}
	
	public static IVec4 IVec4(int x, int y, IVec1 z, IVec1 w) {
		return new IRVec4(x, y, z.x(), w.x());
	}
	
	public static IVec4 IVec4(IVec1 x, IVec1 y, IVec1 z, int w) {
		return new IRVec4(x.x(), y.x(), z.x(), w);
	}
	
	public static IVec4 IVec4(IVec1 x, IVec1 y, int z, IVec1 w) {
		return new IRVec4(x.x(), y.x(), z, w.x());
	}
	
	public static IVec4 IVec4(IVec1 x, int y, IVec1 z, IVec1 w) {
		return new IRVec4(x.x(), y, z.x(), w.x());
	}
	
	public static IVec4 IVec4(int x, IVec1 y, IVec1 z, IVec1 w) {
		return new IRVec4(x, y.x(), z.x(), w.x());
	}
	
	public static IVec4 IVec4(IVec1 x, IVec1 y, IVec1 z, IVec1 w) {
		return new IRVec4(x.x(), y.x(), z.x(), w.x());
	}

	public static IVec4 IVec4(IVec2 v, int z, int w) {
		return new IRVec4(v.x(), v.y(), z, w);
	}
	
	public static IVec4 IVec4(IVec2 v, IVec1 z, int w) {
		return new IRVec4(v.x(), v.y(), z.x(), w);
	}
	
	public static IVec4 IVec4(IVec2 v, int z, IVec1 w) {
		return new IRVec4(v.x(), v.y(), z, w.x());
	}
	
	public static IVec4 IVec4(IVec2 v, IVec1 z, IVec1 w) {
		return new IRVec4(v.x(), v.y(), z.x(), w.x());
	}

	public static IVec4 IVec4(int x, IVec2 v, int w) {
		return new IRVec4(x, v.x(), v.y(), w);
	}
	
	public static IVec4 IVec4(IVec1 x, IVec2 v, int w) {
		return new IRVec4(x.x(), v.x(), v.y(), w);
	}
	
	public static IVec4 IVec4(int x, IVec2 v, IVec1 w) {
		return new IRVec4(x, v.x(), v.y(), w.x());
	}
	
	public static IVec4 IVec4(IVec1 x, IVec2 v, IVec1 w) {
		return new IRVec4(x.x(), v.x(), v.y(), w.x());
	}

	public static IVec4 IVec4(int x, int y, IVec2 v) {
		return new IRVec4(x, y, v.x(), v.y());
	}
	
	public static IVec4 IVec4(IVec1 x, int y, IVec2 v) {
		return new IRVec4(x.x(), y, v.x(), v.y());
	}
	
	public static IVec4 IVec4(int x, IVec1 y, IVec2 v) {
		return new IRVec4(x, y.x(), v.x(), v.y());
	}
	
	public static IVec4 IVec4(IVec1 x, IVec1 y, IVec2 v) {
		return new IRVec4(x.x(), y.x(), v.x(), v.y());
	}

	public static IVec4 IVec4(IVec2 v1, IVec2 v2) {
		return new IRVec4(v1.x(), v1.y(), v2.x(), v2.y());
	}

	public static IVec4 IVec4(IVec3 v, int w) {
		return new IRVec4(v.x(), v.y(), v.z(), w);
	}
	
	public static IVec4 IVec4(IVec3 v, IVec1 w) {
		return new IRVec4(v.x(), v.y(), v.z(), w.x());
	}

	public static IVec4 IVec4(int x, IVec3 v) {
		return new IRVec4(x, v.x(), v.y(), v.z());
	}
	
	public static IVec4 IVec4(IVec1 x, IVec3 v) {
		return new IRVec4(x.x(), v.x(), v.y(), v.z());
	}

	public static IVec4 IVec4(IVec4 v) {
		return new IRVec4(v.x(), v.y(), v.z(), v.w());
	}

	public static IRVec4 IVec4(IVecN v) {
		return new IRVec4(v.get(0), v.get(1), v.get(2), v.get(3));
	}
	
	public static <V extends IVec<V, ?>> V IVecX(int dimension) {
		return IVecX(dimension, 0);
	}

	public static <V extends IVec<V, ?>> V IVecX(int dimension, int pad) {
		switch (dimension) {
		case 0:
			throw new IllegalArgumentException();
		case 1:
			return Utils.unchecked(IVec1(pad));
		case 2:
			return Utils.unchecked(IVec2(pad));
		case 3:
			return Utils.unchecked(IVec3(pad));
		case 4:
			return Utils.unchecked(IVec4(pad));
		default:
			return Utils.unchecked(new IRVecN(dimension, pad));
		}
	}

	public static <V extends IVec<V, ?>> V IVecXfixed(int dimension, IVec<?, ?> vec) {
		int[] a = new int[dimension];
		int min = vec.dim();
		if (min > dimension) {
			min = dimension;
		}
		for (int i = 0; i < min; i++) {
			a[i] = vec.get(i);
		}
		return IVecX(a);
	}

	public static <V extends IVec<V, ?>> V IVecXfixed(int dimension, int... initial) {
		int[] a = new int[dimension];
		int b = initial.length;
		System.arraycopy(initial, 0, a, 0, (dimension <= b) ? dimension : b);
		return IVecX(a);
	}

	public static <V extends IVec<V, ?>> V IVecX(int... data) {
		switch (data.length) {
		case 0:
			throw new IllegalArgumentException();
		case 1:
			return Utils.unchecked(IVec1(data[0]));
		case 2:
			return Utils.unchecked(IVec2(data[0], data[1]));
		case 3:
			return Utils.unchecked(IVec3(data[0], data[1], data[2]));
		case 4:
			return Utils.unchecked(IVec4(data[0], data[1], data[2], data[3]));
		default:
			return Utils.unchecked(new IRVecN(data));
		}
	}

	public static <V extends IVec<V, ?>> V IVecX(IVec<?, ?> v, int... indices) {
		final int size = indices.length;
		final int size1 = v.dim();
		for (int i = 0; i < size; i++) {
			if (indices[i] < 0 || indices[i] >= size1) {
				throw new IllegalArgumentException();
			}
		}
		return IVecX_checked(v, indices);
	}

	protected static <V extends IVec<V, ?>> V IVecX_checked(IVec<?, ?> v, int[] indices) {
		switch (indices.length) {
		case 0:
			throw new IllegalArgumentException();
		case 1:
			return Utils.unchecked(new IVVec1(v, indices));
		case 2:
			return Utils.unchecked(new IVVec2(v, indices));
		case 3:
			return Utils.unchecked(new IVVec3(v, indices));
		case 4:
			return Utils.unchecked(new IVVec4(v, indices));
		default:
			return Utils.unchecked(new IVVecN(v, indices));
		}
	}

	public static <V extends IVec<V, ?>> V IVecX(int[] array, int... indices) {
		final int size = indices.length;
		final int size1 = array.length;
		for (int i = 0; i < size; i++) {
			if (indices[i] < 0 || indices[i] >= size1) {
				throw new IllegalArgumentException();
			}
		}
		return IVecX_checked(array, indices);
	}

	protected static <V extends IVec<V, ?>> V IVecX_checked(int[] array, int[] indices) {
		switch (indices.length) {
		case 0:
			throw new IllegalArgumentException();
		case 1:
			return Utils.unchecked(new IAVec1(array, indices));
		case 2:
			return Utils.unchecked(new IAVec2(array, indices));
		case 3:
			return Utils.unchecked(new IAVec3(array, indices));
		case 4:
			return Utils.unchecked(new IAVec4(array, indices));
		default:
			return Utils.unchecked(new IAVecN(array, indices));
		}
	}
	
	public static <V extends IVec<V, ?>> V IVecX(Object... o) {
		List<Number> list = new ArrayList<Number>();
		Utils.fill(list, o);
		int size = list.size();
		int[] a = new int[size];
		for(int i=0; i<size; i++){
			a[i] = list.get(i).intValue();
		}
		return IVecX(a);
	}

}
