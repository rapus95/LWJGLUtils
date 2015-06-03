package xor.vecmat.vec.l;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.LongBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import xor.vecmat.COMP_OPS;
import xor.vecmat.Utils;
import xor.vecmat.VecMath;
import xor.vecmat.COMP_OPS.Func1_L;
import xor.vecmat.COMP_OPS.Func2_L;
import xor.vecmat.COMP_OPS.Func3_L;
import xor.vecmat.vec.VecIterator;
import xor.vecmat.vec.Vec_base;
import xor.vecmat.vec.Vec_int;
import xor.vecmat.vec.b.BVec;

public abstract class LVec<T extends LVec<T, B>, B extends BVec<B>> implements Vec_int<T, B, Long> {

	public abstract long get(int index);

	@Override
	public Long getW(int index) {
		return Long.valueOf(get(index));
	}

	public abstract void set(int index, long value);

	@Override
	public void set(int index, Long value) {
		set(index, value.longValue());
	}

	public long get(char c) {
		return get(Utils.getIndex(c));
	}

	@Override
	public Long getW(char c) {
		return Long.valueOf(get(c));
	}

	public void set(char c, long value) {
		set(Utils.getIndex(c), value);
	}

	@Override
	public void set(char c, Long value) {
		set(c, value.longValue());
	}

	public <V extends LVec<V, ?>> V get(CharSequence t){
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
		return LVecX_checked(this, indices);
	}
	
	@Override
	public LVec<?, ?> getS(CharSequence t) {
		return get(t);
	}

	@Override
	public void set(CharSequence t, Vec_base<?, ?, Long> value) {
		final int size = t.length();
		if (Utils.CHECKS && size != value.dim()) {
			throw new IllegalArgumentException("String length have to equal vector length");
		}
		if (value instanceof LVec<?, ?>) {
			LVec<?, ?> v = (LVec<?, ?>) value;
			for (int i = 0; i < size; i++) {
				char c = t.charAt(i);
				set(c, v.get(i));
			}
		} else {
			for (int i = 0; i < size; i++) {
				char c = t.charAt(i);
				set(c, value.getW(i).longValue());
			}
		}
	}

	public <V extends LVec<V, ?>> V getII(int... indices) {
		return LVecX(this, indices);
	}
	
	@Override
	public LVec<?, ?> getI(int... indices) {
		return LVecX(this, indices);
	}

	@Override
	public void set(Vec_base<?, ?, Long> value, int... indices) {
		set(indices, value);
	}

	@Override
	public void set(int[] indices, Vec_base<?, ?, Long> value) {
		final int size = indices.length;
		if (Utils.CHECKS && size != value.dim()) {
			throw new IllegalArgumentException("indices length have to equal vector length");
		}
		if (value instanceof LVec<?, ?>) {
			LVec<?, ?> v = (LVec<?, ?>) value;
			for (int i = 0; i < size; i++) {
				set(indices[i], v.get(i));
			}
		} else {
			for (int i = 0; i < size; i++) {
				set(indices[i], value.getW(i).longValue());
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
	
	public T add(long n) {
		if (n == 0)
			return clone();
		return forEach(n, COMP_OPS.ADD_L);
	}
	
	public T add(Long n) {
		return add(n.longValue());
	}

	public T add(T v) {
		return forEach(v, COMP_OPS.ADD_L);
	}

	public T addZeroIfNull(T v) {
		if (v == null)
			return clone();
		return forEach(v, COMP_OPS.ADD_L);
	}

	public T sub(long n) {
		if (n == 0)
			return clone();
		return forEach(n, COMP_OPS.SUB_L);
	}

	public T sub(Long n) {
		return sub(n.longValue());
	}
	
	public T sub(T v) {
		return forEach(v, COMP_OPS.SUB_L);
	}

	public T sub2(long n) {
		if (n == 0)
			return clone();
		return forEach(n, COMP_OPS.SUB2_L);
	}

	public T sub2(Long n) {
		return sub2(n.longValue());
	}
	
	public T neg() {
		return forEach(COMP_OPS.NEG_L);
	}

	public T mul(long n) {
		if (n == 0)
			return _new();
		if (n == 1)
			return clone();
		return forEach(n, COMP_OPS.MUL_L);
	}

	public T mul(Long n) {
		return mul(n.longValue());
	}
	
	public T mul(T v) {
		return forEach(v, COMP_OPS.MUL_L);
	}

	public T div(long n) {
		if (n == 1)
			return clone();
		return forEach(n, COMP_OPS.DIV_L);
	}

	public T div(Long n) {
		return div(n.longValue());
	}
	
	public T div(T v) {
		return forEach(v, COMP_OPS.DIV_L);
	}

	public T div2(long n) {
		return forEach(n, COMP_OPS.DIV2_L);
	}
	
	public T div2(Long n) {
		return div2(n.longValue());
	}

	public T mod(long n) {
		return forEach(n, COMP_OPS.MOD_L);
	}
	
	public T mod(Long n) {
		return mod(n.longValue());
	}

	public T mod(T v) {
		return forEach(v, COMP_OPS.MOD_L);
	}

	public T mod2(long n) {
		return forEach(n, COMP_OPS.MOD2_L);
	}
	
	public T mod2(Long n) {
		return mod2(n.longValue());
	}

	public T pow(long n) {
		if (n == 0)
			return LVecX(dim(), 1);
		if (n == 1)
			return clone();
		return forEach(n, COMP_OPS.POW_L);
	}

	public T pow(Long n) {
		return pow(n.longValue());
	}
	
	public T pow(T v) {
		return forEach(v, COMP_OPS.POW_L);
	}

	public T pow2(long n) {
		if (n == 1)
			return LVecX(dim(), 1);
		return forEach(n, COMP_OPS.POW2_L);
	}

	public T pow2(Long n) {
		return pow(n.longValue());
	}
	
	public T abs() {
		return forEach(COMP_OPS.ABS_L);
	}

	public T sign() {
		return forEach(COMP_OPS.SIGN_L);
	}

	public T min(long n) {
		return forEach(n, COMP_OPS.MIN_L);
	}
	
	public T min(Long n) {
		return min(n.longValue());
	}

	public T min(T v) {
		return forEach(v, COMP_OPS.MIN_L);
	}

	public T max(long n) {
		return forEach(n, COMP_OPS.MAX_L);
	}
	
	public T max(Long n) {
		return max(n.longValue());
	}

	public T max(T v) {
		return forEach(v, COMP_OPS.MAX_L);
	}

	public T clamp(long min, long max) {
		return forEach(min, max, COMP_OPS.CLAMP_L);
	}

	public T clamp(Long min, Long max) {
		return clamp(min.longValue(), max.longValue());
	}
	
	public T clamp(T min, T max) {
		return forEach(min, max, COMP_OPS.CLAMP_L);
	}

	public T step(long edge) {
		return forEach(edge, COMP_OPS.STEP_L);
	}

	public T step(Long edge) {
		return step(edge.longValue());
	}
	
	public T step(T edge) {
		return forEach(edge, COMP_OPS.STEP_L);
	}

	public T inc() {
		return forEach(COMP_OPS.INC_L);
	}
	
	public T dec() {
		return forEach(COMP_OPS.DEC_L);
	}
	
	public T or(T other){
		return forEach(other, COMP_OPS.OR_L);
	}
	
	public T or(long other){
		return forEach(other, COMP_OPS.OR_L);
	}
	
	public T or(Long other){
		return or(other.longValue());
	}
	
	public T and(T other){
		return forEach(other, COMP_OPS.AND_L);
	}
	
	public T and(long other){
		return forEach(other, COMP_OPS.AND_L);
	}
	
	public T and(Long other){
		return and(other.longValue());
	}
	
	public T xor(T other){
		return forEach(other, COMP_OPS.XOR_L);
	}
	
	public T xor(long other){
		return forEach(other, COMP_OPS.XOR_L);
	}
	
	public T xor(Long other){
		return xor(other.longValue());
	}
	
	public T shl(T other){
		return forEach(other, COMP_OPS.SHL_L);
	}
	
	public T shl(long other){
		return forEach(other, COMP_OPS.SHL_L);
	}
	
	public T shl(Long other){
		return shl(other.longValue());
	}
	
	public T shl2(long other){
		return forEach(other, COMP_OPS.SHL2_L);
	}
	
	public T shl2(Long other){
		return shl2(other.longValue());
	}
	
	public T shr(T other){
		return forEach(other, COMP_OPS.SHR_L);
	}
	
	public T shr(long other){
		return forEach(other, COMP_OPS.SHR_L);
	}
	
	public T shr(Long other){
		return shr(other.longValue());
	}
	
	public T shr2(long other){
		return forEach(other, COMP_OPS.SHR2_L);
	}
	
	public T shr2(Long other){
		return shr2(other.longValue());
	}
	
	public T ushr(T other){
		return forEach(other, COMP_OPS.USHR_L);
	}
	
	public T ushr(long other){
		return forEach(other, COMP_OPS.USHR_L);
	}
	
	public T ushr(Long other){
		return ushr(other.longValue());
	}
	
	public T ushr2(long other){
		return forEach(other, COMP_OPS.USHR2_L);
	}
	
	public T ushr2(Long other){
		return ushr2(other.longValue());
	}
	
	public T invert(){
		return forEach(COMP_OPS.INVERT_L);
	}
	
	public double length() {
		final int size = dim();
		long l = 0;
		for (int i = 0; i < size; i++) {
			final long g = get(i);
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
				throw new IllegalArgumentException("In order to combine three LVectors they have to be of the same dimension!");
		}
		long ret = 0;
		for (int i = 0; i < size; i++) {
			ret += get(i) * v.get(i);
		}
		return ret;
	}

	public T normalize() {
		return div((long)length());
	}
	
	@Override
	public ListIterator<Long> iterator() {
		return new VecIterator<Long>(this);
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

	public long[] toArray() {
		final int size = dim();
		long[] a = new long[size];
		for (int i = 0; i < size; i++) {
			a[i] = get(i);
		}
		return a;
	}

	@Override
	public void writeTo(ByteBuffer byteBuffer) {
		writeTo(byteBuffer.asLongBuffer());
	}

	public void writeTo(DoubleBuffer doubleBuffer) {
		final int size = dim();
		for (int i = 0; i < size; i++) {
			doubleBuffer.put(get(i));
		}
	}

	public void writeTo(LongBuffer longBuffer) {
		final int size = dim();
		for (int i = 0; i < size; i++) {
			longBuffer.put(get(i));
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		final int size = dim();
		for (int i = 0; i < size; i++)
			result = prime * result + Long.hashCode(get(i));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof LVec))
			return false;
		LVec<?, ?> other = (LVec<?, ?>) obj;
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
	
	protected T forEach(Func1_L f) {
		T ret = _new();
		final int size = dim();
		for (int i = 0; i < size; i++) {
			ret.set(i, f.calc(get(i)));
		}
		return ret;
	}

	protected T forEach(T v, Func2_L f) {
		T ret = _new();
		final int size = dim();
		if (Utils.CHECKS) {
			if (size != v.dim())
				throw new IllegalArgumentException("In order to combine two LVectors they have to be of the same dimension!");
		}
		for (int i = 0; i < size; i++) {
			ret.set(i, f.calc(get(i), v.get(i)));
		}
		return ret;
	}

	protected T forEach(long n, Func2_L f) {
		T ret = _new();
		final int size = dim();
		for (int i = 0; i < size; i++) {
			ret.set(i, f.calc(get(i), n));
		}
		return ret;
	}

	protected T forEach(T v2, T v3, Func3_L f) {
		T ret = _new();
		final int size = dim();
		if (Utils.CHECKS) {
			if (size != v2.dim() || size != v3.dim())
				throw new IllegalArgumentException("In order to combine three LVectors they have to be of the same dimension!");
		}
		for (int i = 0; i < size; i++) {
			ret.set(i, f.calc(get(i), v2.get(i), v3.get(i)));
		}
		return ret;
	}

	protected T forEach(T v, long n, Func3_L f) {
		T ret = _new();
		final int size = dim();
		if (Utils.CHECKS) {
			if (size != v.dim())
				throw new IllegalArgumentException("In order to combine two LVectors they have to be of the same dimension!");
		}
		for (int i = 0; i < size; i++) {
			ret.set(i, f.calc(get(i), v.get(i), n));
		}
		return ret;
	}

	protected T forEach(long n1, long n2, Func3_L f) {
		T ret = _new();
		final int size = dim();
		for (int i = 0; i < size; i++) {
			ret.set(i, f.calc(get(i), n1, n2));
		}
		return ret;
	}
	
	
	
	
	
	
	
	public static LAVec1 LVec1(long[] array, int i1) {
		return new LAVec1(array, i1);
	}

	public static LVVec1 LVec1(LVec<?, ?> v, int i1) {
		return new LVVec1(v, i1);
	}
	
	public static LRVec1 LVec1() {
		return LVec1(0);
	}

	public static LRVec1 LVec1(long x) {
		return new LRVec1(x);
	}

	public static LRVec1 LVec1(LVec1 v) {
		return new LRVec1(v.x());
	}
	
	public static LRVec1 LVec1(LVec2 v) {
		return new LRVec1(v.x());
	}

	public static LRVec1 LVec1(LVec3 v) {
		return new LRVec1(v.x());
	}

	public static LRVec1 LVec1(LVec4 v) {
		return new LRVec1(v.x());
	}

	public static LRVec1 LVec1(LVecN v) {
		return new LRVec1(v.get(0));
	}
	
	public static LAVec2 LVec2(long[] array, int i1, int i2) {
		return new LAVec2(array, i1, i2);
	}

	public static LVVec2 LVec2(LVec<?, ?> v, int i1, int i2) {
		return new LVVec2(v, i1, i2);
	}

	public static LRVec2 LVec2() {
		return LVec2(0);
	}

	public static LRVec2 LVec2(long x) {
		return new LRVec2(x, x);
	}

	public static LRVec2 LVec2(long x, long y) {
		return new LRVec2(x, y);
	}

	public static LRVec2 LVec2(long x, LVec1 y) {
		return new LRVec2(x, y.x());
	}
	
	public static LRVec2 LVec2(LVec1 x, long y) {
		return new LRVec2(x.x(), y);
	}
	
	public static LRVec2 LVec2(LVec1 x, LVec1 y) {
		return new LRVec2(x.x(), y.x());
	}
	
	public static LRVec2 LVec2(LVec2 v) {
		return new LRVec2(v.x(), v.y());
	}

	public static LRVec2 LVec2(LVec3 v) {
		return new LRVec2(v.x(), v.y());
	}

	public static LRVec2 LVec2(LVec4 v) {
		return new LRVec2(v.x(), v.y());
	}

	public static LRVec2 LVec2(LVecN v) {
		return new LRVec2(v.get(0), v.get(1));
	}

	public static LAVec3 LVec3(long[] array, int i1, int i2, int i3) {
		return new LAVec3(array, i1, i2, i3);
	}

	public static LVVec3 LVec3(LVec<?, ?> v, int i1, int i2, int i3) {
		return new LVVec3(v, i1, i2, i3);
	}

	public static LRVec3 LVec3() {
		return LVec3(0);
	}

	public static LRVec3 LVec3(long x) {
		return new LRVec3(x, x, x);
	}

	public static LRVec3 LVec3(long x, long y, long z) {
		return new LRVec3(x, y, z);
	}
	
	public static LRVec3 LVec3(LVec1 x, long y, long z) {
		return new LRVec3(x.x(), y, z);
	}
	
	public static LRVec3 LVec3(long x, LVec1 y, long z) {
		return new LRVec3(x, y.x(), z);
	}
	
	public static LRVec3 LVec3(long x, long y, LVec1 z) {
		return new LRVec3(x, y, z.x());
	}
	
	public static LRVec3 LVec3(LVec1 x, long y, LVec1 z) {
		return new LRVec3(x.x(), y, z.x());
	}
	
	public static LRVec3 LVec3(LVec1 x, LVec1 y, long z) {
		return new LRVec3(x.x(), y.x(), z);
	}
	
	public static LRVec3 LVec3(long x, LVec1 y, LVec1 z) {
		return new LRVec3(x, y.x(), z.x());
	}
	
	public static LRVec3 LVec3(LVec1 x, LVec1 y, LVec1 z) {
		return new LRVec3(x.x(), y.x(), z.x());
	}

	public static LRVec3 LVec3(LVec2 v, long z) {
		return new LRVec3(v.x(), v.y(), z);
	}
	
	public static LRVec3 LVec3(LVec2 v, LVec1 z) {
		return new LRVec3(v.x(), v.y(), z.x());
	}

	public static LRVec3 LVec3(long x, LVec2 v) {
		return new LRVec3(x, v.x(), v.y());
	}
	
	public static LRVec3 LVec3(LVec1 x, LVec2 v) {
		return new LRVec3(x.x(), v.x(), v.y());
	}

	public static LRVec3 LVec3(LVec3 v) {
		return new LRVec3(v.x(), v.y(), v.z());
	}

	public static LRVec3 LVec3(LVec4 v) {
		return new LRVec3(v.x(), v.y(), v.z());
	}

	public static LRVec3 LVec3(LVecN v) {
		return new LRVec3(v.get(0), v.get(1), v.get(2));
	}

	public static LAVec4 LVec4(long[] array, int i1, int i2, int i3, int i4) {
		return new LAVec4(array, i1, i2, i3, i4);
	}

	public static LVVec4 LVec4(LVec<?, ?> v, int i1, int i2, int i3, int i4) {
		return new LVVec4(v, i1, i2, i3, i4);
	}

	public static LVec4 LVec4() {
		return LVec4(0);
	}

	public static LVec4 LVec4(long x) {
		return new LRVec4(x, x, x, x);
	}

	public static LVec4 LVec4(long x, long y, long z, long w) {
		return new LRVec4(x, y, z, w);
	}
	
	public static LVec4 LVec4(LVec1 x, long y, long z, long w) {
		return new LRVec4(x.x(), y, z, w);
	}
	
	public static LVec4 LVec4(long x, LVec1 y, long z, long w) {
		return new LRVec4(x, y.x(), z, w);
	}
	
	public static LVec4 LVec4(long x, long y, LVec1 z, long w) {
		return new LRVec4(x, y, z.x(), w);
	}
	
	public static LVec4 LVec4(long x, long y, long z, LVec1 w) {
		return new LRVec4(x, y, z, w.x());
	}
	
	public static LVec4 LVec4(LVec1 x, LVec1 y, long z, long w) {
		return new LRVec4(x.x(), y.x(), z, w);
	}
	
	public static LVec4 LVec4(LVec1 x, long y, LVec1 z, long w) {
		return new LRVec4(x.x(), y, z.x(), w);
	}
	
	public static LVec4 LVec4(LVec1 x, long y, long z, LVec1 w) {
		return new LRVec4(x.x(), y, z, w.x());
	}
	
	public static LVec4 LVec4(long x, LVec1 y, LVec1 z, long w) {
		return new LRVec4(x, y.x(), z.x(), w);
	}
	
	public static LVec4 LVec4(long x, LVec1 y, long z, LVec1 w) {
		return new LRVec4(x, y.x(), z, w.x());
	}
	
	public static LVec4 LVec4(long x, long y, LVec1 z, LVec1 w) {
		return new LRVec4(x, y, z.x(), w.x());
	}
	
	public static LVec4 LVec4(LVec1 x, LVec1 y, LVec1 z, long w) {
		return new LRVec4(x.x(), y.x(), z.x(), w);
	}
	
	public static LVec4 LVec4(LVec1 x, LVec1 y, long z, LVec1 w) {
		return new LRVec4(x.x(), y.x(), z, w.x());
	}
	
	public static LVec4 LVec4(LVec1 x, long y, LVec1 z, LVec1 w) {
		return new LRVec4(x.x(), y, z.x(), w.x());
	}
	
	public static LVec4 LVec4(long x, LVec1 y, LVec1 z, LVec1 w) {
		return new LRVec4(x, y.x(), z.x(), w.x());
	}
	
	public static LVec4 LVec4(LVec1 x, LVec1 y, LVec1 z, LVec1 w) {
		return new LRVec4(x.x(), y.x(), z.x(), w.x());
	}

	public static LVec4 LVec4(LVec2 v, long z, long w) {
		return new LRVec4(v.x(), v.y(), z, w);
	}
	
	public static LVec4 LVec4(LVec2 v, LVec1 z, long w) {
		return new LRVec4(v.x(), v.y(), z.x(), w);
	}
	
	public static LVec4 LVec4(LVec2 v, long z, LVec1 w) {
		return new LRVec4(v.x(), v.y(), z, w.x());
	}
	
	public static LVec4 LVec4(LVec2 v, LVec1 z, LVec1 w) {
		return new LRVec4(v.x(), v.y(), z.x(), w.x());
	}

	public static LVec4 LVec4(long x, LVec2 v, long w) {
		return new LRVec4(x, v.x(), v.y(), w);
	}
	
	public static LVec4 LVec4(LVec1 x, LVec2 v, long w) {
		return new LRVec4(x.x(), v.x(), v.y(), w);
	}
	
	public static LVec4 LVec4(long x, LVec2 v, LVec1 w) {
		return new LRVec4(x, v.x(), v.y(), w.x());
	}
	
	public static LVec4 LVec4(LVec1 x, LVec2 v, LVec1 w) {
		return new LRVec4(x.x(), v.x(), v.y(), w.x());
	}

	public static LVec4 LVec4(long x, long y, LVec2 v) {
		return new LRVec4(x, y, v.x(), v.y());
	}
	
	public static LVec4 LVec4(LVec1 x, long y, LVec2 v) {
		return new LRVec4(x.x(), y, v.x(), v.y());
	}
	
	public static LVec4 LVec4(long x, LVec1 y, LVec2 v) {
		return new LRVec4(x, y.x(), v.x(), v.y());
	}
	
	public static LVec4 LVec4(LVec1 x, LVec1 y, LVec2 v) {
		return new LRVec4(x.x(), y.x(), v.x(), v.y());
	}

	public static LVec4 LVec4(LVec2 v1, LVec2 v2) {
		return new LRVec4(v1.x(), v1.y(), v2.x(), v2.y());
	}

	public static LVec4 LVec4(LVec3 v, long w) {
		return new LRVec4(v.x(), v.y(), v.z(), w);
	}
	
	public static LVec4 LVec4(LVec3 v, LVec1 w) {
		return new LRVec4(v.x(), v.y(), v.z(), w.x());
	}

	public static LVec4 LVec4(long x, LVec3 v) {
		return new LRVec4(x, v.x(), v.y(), v.z());
	}
	
	public static LVec4 LVec4(LVec1 x, LVec3 v) {
		return new LRVec4(x.x(), v.x(), v.y(), v.z());
	}

	public static LVec4 LVec4(LVec4 v) {
		return new LRVec4(v.x(), v.y(), v.z(), v.w());
	}

	public static LRVec4 LVec4(LVecN v) {
		return new LRVec4(v.get(0), v.get(1), v.get(2), v.get(3));
	}
	
	public static <V extends LVec<V, ?>> V LVecX(int dimension) {
		return LVecX(dimension, 0);
	}

	public static <V extends LVec<V, ?>> V LVecX(int dimension, long pad) {
		switch (dimension) {
		case 0:
			throw new IllegalArgumentException();
		case 1:
			return Utils.unchecked(LVec1(pad));
		case 2:
			return Utils.unchecked(LVec2(pad));
		case 3:
			return Utils.unchecked(LVec3(pad));
		case 4:
			return Utils.unchecked(LVec4(pad));
		default:
			return Utils.unchecked(new LRVecN(dimension, pad));
		}
	}

	public static <V extends LVec<V, ?>> V LVecXfixed(int dimension, LVec<?, ?> vec) {
		long[] a = new long[dimension];
		int min = vec.dim();
		if (min > dimension) {
			min = dimension;
		}
		for (int i = 0; i < min; i++) {
			a[i] = vec.get(i);
		}
		return LVecX(a);
	}

	public static <V extends LVec<V, ?>> V LVecXfixed(int dimension, long... initial) {
		long[] a = new long[dimension];
		int b = initial.length;
		System.arraycopy(initial, 0, a, 0, (dimension <= b) ? dimension : b);
		return LVecX(a);
	}

	public static <V extends LVec<V, ?>> V LVecX(long... data) {
		switch (data.length) {
		case 0:
			throw new IllegalArgumentException();
		case 1:
			return Utils.unchecked(LVec1(data[0]));
		case 2:
			return Utils.unchecked(LVec2(data[0], data[1]));
		case 3:
			return Utils.unchecked(LVec3(data[0], data[1], data[2]));
		case 4:
			return Utils.unchecked(LVec4(data[0], data[1], data[2], data[3]));
		default:
			return Utils.unchecked(new LRVecN(data));
		}
	}

	public static <V extends LVec<V, ?>> V LVecX(LVec<?, ?> v, int... indices) {
		final int size = indices.length;
		final int size1 = v.dim();
		for (int i = 0; i < size; i++) {
			if (indices[i] < 0 || indices[i] >= size1) {
				throw new IllegalArgumentException();
			}
		}
		return LVecX_checked(v, indices);
	}

	protected static <V extends LVec<V, ?>> V LVecX_checked(LVec<?, ?> v, int[] indices) {
		switch (indices.length) {
		case 0:
			throw new IllegalArgumentException();
		case 1:
			return Utils.unchecked(new LVVec1(v, indices));
		case 2:
			return Utils.unchecked(new LVVec2(v, indices));
		case 3:
			return Utils.unchecked(new LVVec3(v, indices));
		case 4:
			return Utils.unchecked(new LVVec4(v, indices));
		default:
			return Utils.unchecked(new LVVecN(v, indices));
		}
	}

	public static <V extends LVec<V, ?>> V LVecX(long[] array, int... indices) {
		final int size = indices.length;
		final int size1 = array.length;
		for (int i = 0; i < size; i++) {
			if (indices[i] < 0 || indices[i] >= size1) {
				throw new IllegalArgumentException();
			}
		}
		return LVecX_checked(array, indices);
	}

	protected static <V extends LVec<V, ?>> V LVecX_checked(long[] array, int[] indices) {
		switch (indices.length) {
		case 0:
			throw new IllegalArgumentException();
		case 1:
			return Utils.unchecked(new LAVec1(array, indices));
		case 2:
			return Utils.unchecked(new LAVec2(array, indices));
		case 3:
			return Utils.unchecked(new LAVec3(array, indices));
		case 4:
			return Utils.unchecked(new LAVec4(array, indices));
		default:
			return Utils.unchecked(new LAVecN(array, indices));
		}
	}
	
	public static <V extends LVec<V, ?>> V LVecX(Object... o) {
		List<Number> list = new ArrayList<Number>();
		Utils.fill(list, o);
		int size = list.size();
		long[] a = new long[size];
		for(int i=0; i<size; i++){
			a[i] = list.get(i).longValue();
		}
		return LVecX(a);
	}

}
