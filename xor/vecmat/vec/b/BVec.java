package xor.vecmat.vec.b;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import xor.vecmat.COMP_OPS;
import xor.vecmat.Utils;
import xor.vecmat.COMP_OPS.Func1_B;
import xor.vecmat.COMP_OPS.Func2_B;
import xor.vecmat.COMP_OPS.Func3_B;
import xor.vecmat.vec.VecIterator;
import xor.vecmat.vec.Vec_base;

public abstract class BVec<T extends BVec<T>> implements
		Vec_base<T, T, Boolean> {

	public abstract boolean get(int index);

	@Override
	public Boolean getW(int index) {
		return Boolean.valueOf(get(index));
	}

	public abstract void set(int index, boolean value);

	@Override
	public void set(int index, Boolean value) {
		set(index, value.booleanValue());
	}

	public boolean get(char c) {
		return get(Utils.getIndex(c));
	}

	@Override
	public Boolean getW(char c) {
		return Boolean.valueOf(get(c));
	}

	public void set(char c, boolean value) {
		set(Utils.getIndex(c), value);
	}

	@Override
	public void set(char c, Boolean value) {
		set(c, value.booleanValue());
	}

	public <V extends BVec<V>> V get(CharSequence t) {
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
		return BVecX_checked(this, indices);
	}

	@Override
	public BVec<?> getS(CharSequence t) {
		return get(t);
	}

	@Override
	public void set(CharSequence t, Vec_base<?, ?, Boolean> value) {
		final int size = t.length();
		if (Utils.CHECKS && size != value.dim()) {
			throw new IllegalArgumentException(
					"String length have to equal vector length");
		}
		if (value instanceof BVec<?>) {
			BVec<?> v = (BVec<?>) value;
			for (int i = 0; i < size; i++) {
				char c = t.charAt(i);
				set(c, v.get(i));
			}
		} else {
			for (int i = 0; i < size; i++) {
				char c = t.charAt(i);
				set(c, value.getW(i).booleanValue());
			}
		}
	}

	public <V extends BVec<V>> V getII(int... indices) {
		return BVecX(this, indices);
	}

	@Override
	public BVec<?> getI(int... indices) {
		return BVecX(this, indices);
	}

	@Override
	public void set(Vec_base<?, ?, Boolean> value, int... indices) {
		set(indices, value);
	}

	@Override
	public void set(int[] indices, Vec_base<?, ?, Boolean> value) {
		final int size = indices.length;
		if (Utils.CHECKS && size != value.dim()) {
			throw new IllegalArgumentException(
					"indices length have to equal vector length");
		}
		if (value instanceof BVec<?>) {
			BVec<?> v = (BVec<?>) value;
			for (int i = 0; i < size; i++) {
				set(indices[i], v.get(i));
			}
		} else {
			for (int i = 0; i < size; i++) {
				set(indices[i], value.getW(i).booleanValue());
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

	public T or(T other) {
		return forEach(other, COMP_OPS.OR_B);
	}

	public T or(boolean other) {
		return forEach(other, COMP_OPS.OR_B);
	}

	public T and(T other) {
		return forEach(other, COMP_OPS.AND_B);
	}

	public T and(boolean other) {
		return forEach(other, COMP_OPS.AND_B);
	}

	public T xor(T other) {
		return forEach(other, COMP_OPS.XOR_B);
	}

	public T xor(boolean other) {
		return forEach(other, COMP_OPS.XOR_B);
	}

	public T invert() {
		return forEach(COMP_OPS.INVERT_B);
	}

	public boolean any() {
		final int size = dim();
		for (int i = 0; i < size; i++) {
			if (get(i))
				return true;
		}
		return false;
	}

	public boolean all() {
		final int size = dim();
		for (int i = 0; i < size; i++) {
			if (!get(i))
				return false;
		}
		return true;
	}

	@Override
	public ListIterator<Boolean> iterator() {
		return new VecIterator<Boolean>(this);
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

	public boolean[] toArray() {
		final int size = dim();
		boolean[] a = new boolean[size];
		for (int i = 0; i < size; i++) {
			a[i] = get(i);
		}
		return a;
	}

	@Override
	public void writeTo(ByteBuffer byteBuffer) {
		final int size = dim();
		for (int i = 0; i < size; i++) {
			byteBuffer.put(get(i) ? (byte) -1 : 0);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		final int size = dim();
		for (int i = 0; i < size; i++)
			result = prime * result + Boolean.hashCode(get(i));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof BVec))
			return false;
		BVec<?> other = (BVec<?>) obj;
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

	@Override
	public T equals(T other) {
		final int size = dim();
		if (Utils.CHECKS) {
			if (size != other.dim())
				throw new IllegalArgumentException(
						"In order to compare two BVectors they have to be of the same dimension!");
		}
		T ret = _new();
		for (int i = 0; i < size; i++) {
			ret.set(i, get(i) == other.get(i));
		}
		return ret;
	}

	@Override
	public T notEqual(T other) {
		final int size = dim();
		if (Utils.CHECKS) {
			if (size != other.dim())
				throw new IllegalArgumentException(
						"In order to compare two BVectors they have to be of the same dimension!");
		}
		T ret = _new();
		for (int i = 0; i < size; i++) {
			ret.set(i, get(i) != other.get(i));
		}
		return ret;
	}

	protected T forEach(Func1_B f) {
		T ret = _new();
		final int size = dim();
		for (int i = 0; i < size; i++) {
			ret.set(i, f.calc(get(i)));
		}
		return ret;
	}

	protected T forEach(T v, Func2_B f) {
		T ret = _new();
		final int size = dim();
		if (Utils.CHECKS) {
			if (size != v.dim())
				throw new IllegalArgumentException(
						"In order to combine two BVectors they have to be of the same dimension!");
		}
		for (int i = 0; i < size; i++) {
			ret.set(i, f.calc(get(i), v.get(i)));
		}
		return ret;
	}

	protected T forEach(boolean n, Func2_B f) {
		T ret = _new();
		final int size = dim();
		for (int i = 0; i < size; i++) {
			ret.set(i, f.calc(get(i), n));
		}
		return ret;
	}

	protected T forEach(T v2, T v3, Func3_B f) {
		T ret = _new();
		final int size = dim();
		if (Utils.CHECKS) {
			if (size != v2.dim() || size != v3.dim())
				throw new IllegalArgumentException(
						"In order to combine three BVectors they have to be of the same dimension!");
		}
		for (int i = 0; i < size; i++) {
			ret.set(i, f.calc(get(i), v2.get(i), v3.get(i)));
		}
		return ret;
	}

	protected T forEach(T v, boolean n, Func3_B f) {
		T ret = _new();
		final int size = dim();
		if (Utils.CHECKS) {
			if (size != v.dim())
				throw new IllegalArgumentException(
						"In order to combine two BVectors they have to be of the same dimension!");
		}
		for (int i = 0; i < size; i++) {
			ret.set(i, f.calc(get(i), v.get(i), n));
		}
		return ret;
	}

	protected T forEach(boolean n1, boolean n2, Func3_B f) {
		T ret = _new();
		final int size = dim();
		for (int i = 0; i < size; i++) {
			ret.set(i, f.calc(get(i), n1, n2));
		}
		return ret;
	}

	public static BAVec1 BVec1(boolean[] array, int i1) {
		return new BAVec1(array, i1);
	}

	public static BVVec1 BVec1(BVec<?> v, int i1) {
		return new BVVec1(v, i1);
	}

	public static BRVec1 BVec1() {
		return BVec1(false);
	}

	public static BRVec1 BVec1(boolean x) {
		return new BRVec1(x);
	}

	public static BRVec1 BVec1(BVec1 v) {
		return new BRVec1(v.x());
	}

	public static BRVec1 BVec1(BVec2 v) {
		return new BRVec1(v.x());
	}

	public static BRVec1 BVec1(BVec3 v) {
		return new BRVec1(v.x());
	}

	public static BRVec1 BVec1(BVec4 v) {
		return new BRVec1(v.x());
	}

	public static BRVec1 BVec1(BVecN v) {
		return new BRVec1(v.get(0));
	}

	public static BAVec2 BVec2(boolean[] array, int i1, int i2) {
		return new BAVec2(array, i1, i2);
	}

	public static BVVec2 BVec2(BVec<?> v, int i1, int i2) {
		return new BVVec2(v, i1, i2);
	}

	public static BRVec2 BVec2() {
		return BVec2(false);
	}

	public static BRVec2 BVec2(boolean x) {
		return new BRVec2(x, x);
	}

	public static BRVec2 BVec2(boolean x, boolean y) {
		return new BRVec2(x, y);
	}

	public static BRVec2 BVec2(boolean x, BVec1 y) {
		return new BRVec2(x, y.x());
	}

	public static BRVec2 BVec2(BVec1 x, boolean y) {
		return new BRVec2(x.x(), y);
	}

	public static BRVec2 BVec2(BVec1 x, BVec1 y) {
		return new BRVec2(x.x(), y.x());
	}

	public static BRVec2 BVec2(BVec2 v) {
		return new BRVec2(v.x(), v.y());
	}

	public static BRVec2 BVec2(BVec3 v) {
		return new BRVec2(v.x(), v.y());
	}

	public static BRVec2 BVec2(BVec4 v) {
		return new BRVec2(v.x(), v.y());
	}

	public static BRVec2 BVec2(BVecN v) {
		return new BRVec2(v.get(0), v.get(1));
	}

	public static BAVec3 BVec3(boolean[] array, int i1, int i2, int i3) {
		return new BAVec3(array, i1, i2, i3);
	}

	public static BVVec3 BVec3(BVec<?> v, int i1, int i2, int i3) {
		return new BVVec3(v, i1, i2, i3);
	}

	public static BRVec3 BVec3() {
		return BVec3(false);
	}

	public static BRVec3 BVec3(boolean x) {
		return new BRVec3(x, x, x);
	}

	public static BRVec3 BVec3(boolean x, boolean y, boolean z) {
		return new BRVec3(x, y, z);
	}

	public static BRVec3 BVec3(BVec1 x, boolean y, boolean z) {
		return new BRVec3(x.x(), y, z);
	}

	public static BRVec3 BVec3(boolean x, BVec1 y, boolean z) {
		return new BRVec3(x, y.x(), z);
	}

	public static BRVec3 BVec3(boolean x, boolean y, BVec1 z) {
		return new BRVec3(x, y, z.x());
	}

	public static BRVec3 BVec3(BVec1 x, boolean y, BVec1 z) {
		return new BRVec3(x.x(), y, z.x());
	}

	public static BRVec3 BVec3(BVec1 x, BVec1 y, boolean z) {
		return new BRVec3(x.x(), y.x(), z);
	}

	public static BRVec3 BVec3(boolean x, BVec1 y, BVec1 z) {
		return new BRVec3(x, y.x(), z.x());
	}

	public static BRVec3 BVec3(BVec1 x, BVec1 y, BVec1 z) {
		return new BRVec3(x.x(), y.x(), z.x());
	}

	public static BRVec3 BVec3(BVec2 v, boolean z) {
		return new BRVec3(v.x(), v.y(), z);
	}

	public static BRVec3 BVec3(BVec2 v, BVec1 z) {
		return new BRVec3(v.x(), v.y(), z.x());
	}

	public static BRVec3 BVec3(boolean x, BVec2 v) {
		return new BRVec3(x, v.x(), v.y());
	}

	public static BRVec3 BVec3(BVec1 x, BVec2 v) {
		return new BRVec3(x.x(), v.x(), v.y());
	}

	public static BRVec3 BVec3(BVec3 v) {
		return new BRVec3(v.x(), v.y(), v.z());
	}

	public static BRVec3 BVec3(BVec4 v) {
		return new BRVec3(v.x(), v.y(), v.z());
	}

	public static BRVec3 BVec3(BVecN v) {
		return new BRVec3(v.get(0), v.get(1), v.get(2));
	}

	public static BAVec4 BVec4(boolean[] array, int i1, int i2, int i3, int i4) {
		return new BAVec4(array, i1, i2, i3, i4);
	}

	public static BVVec4 BVec4(BVec<?> v, int i1, int i2, int i3, int i4) {
		return new BVVec4(v, i1, i2, i3, i4);
	}

	public static BVec4 BVec4() {
		return BVec4(false);
	}

	public static BVec4 BVec4(boolean x) {
		return new BRVec4(x, x, x, x);
	}

	public static BVec4 BVec4(boolean x, boolean y, boolean z, boolean w) {
		return new BRVec4(x, y, z, w);
	}

	public static BVec4 BVec4(BVec1 x, boolean y, boolean z, boolean w) {
		return new BRVec4(x.x(), y, z, w);
	}

	public static BVec4 BVec4(boolean x, BVec1 y, boolean z, boolean w) {
		return new BRVec4(x, y.x(), z, w);
	}

	public static BVec4 BVec4(boolean x, boolean y, BVec1 z, boolean w) {
		return new BRVec4(x, y, z.x(), w);
	}

	public static BVec4 BVec4(boolean x, boolean y, boolean z, BVec1 w) {
		return new BRVec4(x, y, z, w.x());
	}

	public static BVec4 BVec4(BVec1 x, BVec1 y, boolean z, boolean w) {
		return new BRVec4(x.x(), y.x(), z, w);
	}

	public static BVec4 BVec4(BVec1 x, boolean y, BVec1 z, boolean w) {
		return new BRVec4(x.x(), y, z.x(), w);
	}

	public static BVec4 BVec4(BVec1 x, boolean y, boolean z, BVec1 w) {
		return new BRVec4(x.x(), y, z, w.x());
	}

	public static BVec4 BVec4(boolean x, BVec1 y, BVec1 z, boolean w) {
		return new BRVec4(x, y.x(), z.x(), w);
	}

	public static BVec4 BVec4(boolean x, BVec1 y, boolean z, BVec1 w) {
		return new BRVec4(x, y.x(), z, w.x());
	}

	public static BVec4 BVec4(boolean x, boolean y, BVec1 z, BVec1 w) {
		return new BRVec4(x, y, z.x(), w.x());
	}

	public static BVec4 BVec4(BVec1 x, BVec1 y, BVec1 z, boolean w) {
		return new BRVec4(x.x(), y.x(), z.x(), w);
	}

	public static BVec4 BVec4(BVec1 x, BVec1 y, boolean z, BVec1 w) {
		return new BRVec4(x.x(), y.x(), z, w.x());
	}

	public static BVec4 BVec4(BVec1 x, boolean y, BVec1 z, BVec1 w) {
		return new BRVec4(x.x(), y, z.x(), w.x());
	}

	public static BVec4 BVec4(boolean x, BVec1 y, BVec1 z, BVec1 w) {
		return new BRVec4(x, y.x(), z.x(), w.x());
	}

	public static BVec4 BVec4(BVec1 x, BVec1 y, BVec1 z, BVec1 w) {
		return new BRVec4(x.x(), y.x(), z.x(), w.x());
	}

	public static BVec4 BVec4(BVec2 v, boolean z, boolean w) {
		return new BRVec4(v.x(), v.y(), z, w);
	}

	public static BVec4 BVec4(BVec2 v, BVec1 z, boolean w) {
		return new BRVec4(v.x(), v.y(), z.x(), w);
	}

	public static BVec4 BVec4(BVec2 v, boolean z, BVec1 w) {
		return new BRVec4(v.x(), v.y(), z, w.x());
	}

	public static BVec4 BVec4(BVec2 v, BVec1 z, BVec1 w) {
		return new BRVec4(v.x(), v.y(), z.x(), w.x());
	}

	public static BVec4 BVec4(boolean x, BVec2 v, boolean w) {
		return new BRVec4(x, v.x(), v.y(), w);
	}

	public static BVec4 BVec4(BVec1 x, BVec2 v, boolean w) {
		return new BRVec4(x.x(), v.x(), v.y(), w);
	}

	public static BVec4 BVec4(boolean x, BVec2 v, BVec1 w) {
		return new BRVec4(x, v.x(), v.y(), w.x());
	}

	public static BVec4 BVec4(BVec1 x, BVec2 v, BVec1 w) {
		return new BRVec4(x.x(), v.x(), v.y(), w.x());
	}

	public static BVec4 BVec4(boolean x, boolean y, BVec2 v) {
		return new BRVec4(x, y, v.x(), v.y());
	}

	public static BVec4 BVec4(BVec1 x, boolean y, BVec2 v) {
		return new BRVec4(x.x(), y, v.x(), v.y());
	}

	public static BVec4 BVec4(boolean x, BVec1 y, BVec2 v) {
		return new BRVec4(x, y.x(), v.x(), v.y());
	}

	public static BVec4 BVec4(BVec1 x, BVec1 y, BVec2 v) {
		return new BRVec4(x.x(), y.x(), v.x(), v.y());
	}

	public static BVec4 BVec4(BVec2 v1, BVec2 v2) {
		return new BRVec4(v1.x(), v1.y(), v2.x(), v2.y());
	}

	public static BVec4 BVec4(BVec3 v, boolean w) {
		return new BRVec4(v.x(), v.y(), v.z(), w);
	}

	public static BVec4 BVec4(BVec3 v, BVec1 w) {
		return new BRVec4(v.x(), v.y(), v.z(), w.x());
	}

	public static BVec4 BVec4(boolean x, BVec3 v) {
		return new BRVec4(x, v.x(), v.y(), v.z());
	}

	public static BVec4 BVec4(BVec1 x, BVec3 v) {
		return new BRVec4(x.x(), v.x(), v.y(), v.z());
	}

	public static BVec4 BVec4(BVec4 v) {
		return new BRVec4(v.x(), v.y(), v.z(), v.w());
	}

	public static BRVec4 BVec4(BVecN v) {
		return new BRVec4(v.get(0), v.get(1), v.get(2), v.get(3));
	}

	public static <V extends BVec<V>> V BVecX(int dimension) {
		return BVecX(dimension, 0);
	}

	public static <V extends BVec<V>> V BVecX(int dimension, boolean pad) {
		switch (dimension) {
		case 0:
			throw new IllegalArgumentException();
		case 1:
			return Utils.unchecked(BVec1(pad));
		case 2:
			return Utils.unchecked(BVec2(pad));
		case 3:
			return Utils.unchecked(BVec3(pad));
		case 4:
			return Utils.unchecked(BVec4(pad));
		default:
			return Utils.unchecked(new BRVecN(dimension, pad));
		}
	}

	public static <V extends BVec<V>> V BVecXfixed(int dimension, BVec<?> vec) {
		boolean[] a = new boolean[dimension];
		int min = vec.dim();
		if (min > dimension) {
			min = dimension;
		}
		for (int i = 0; i < min; i++) {
			a[i] = vec.get(i);
		}
		return BVecX(a);
	}

	public static <V extends BVec<V>> V BVecXfixed(int dimension,
			boolean... initial) {
		boolean[] a = new boolean[dimension];
		int b = initial.length;
		System.arraycopy(initial, 0, a, 0, (dimension <= b) ? dimension : b);
		return BVecX(a);
	}

	public static <V extends BVec<V>> V BVecX(boolean... data) {
		switch (data.length) {
		case 0:
			throw new IllegalArgumentException();
		case 1:
			return Utils.unchecked(BVec1(data[0]));
		case 2:
			return Utils.unchecked(BVec2(data[0], data[1]));
		case 3:
			return Utils.unchecked(BVec3(data[0], data[1], data[2]));
		case 4:
			return Utils.unchecked(BVec4(data[0], data[1], data[2], data[3]));
		default:
			return Utils.unchecked(new BRVecN(data));
		}
	}

	public static <V extends BVec<V>> V BVecX(BVec<?> v, int... indices) {
		final int size = indices.length;
		final int size1 = v.dim();
		for (int i = 0; i < size; i++) {
			if (indices[i] < 0 || indices[i] >= size1) {
				throw new IllegalArgumentException();
			}
		}
		return BVecX_checked(v, indices);
	}

	protected static <V extends BVec<V>> V BVecX_checked(BVec<?> v,
			int[] indices) {
		switch (indices.length) {
		case 0:
			throw new IllegalArgumentException();
		case 1:
			return Utils.unchecked(new BVVec1(v, indices));
		case 2:
			return Utils.unchecked(new BVVec2(v, indices));
		case 3:
			return Utils.unchecked(new BVVec3(v, indices));
		case 4:
			return Utils.unchecked(new BVVec4(v, indices));
		default:
			return Utils.unchecked(new BVVecN(v, indices));
		}
	}

	public static <V extends BVec<V>> V BVecX(boolean[] array, int... indices) {
		final int size = indices.length;
		final int size1 = array.length;
		for (int i = 0; i < size; i++) {
			if (indices[i] < 0 || indices[i] >= size1) {
				throw new IllegalArgumentException();
			}
		}
		return BVecX_checked(array, indices);
	}

	protected static <V extends BVec<V>> V BVecX_checked(boolean[] array,
			int[] indices) {
		switch (indices.length) {
		case 0:
			throw new IllegalArgumentException();
		case 1:
			return Utils.unchecked(new BAVec1(array, indices));
		case 2:
			return Utils.unchecked(new BAVec2(array, indices));
		case 3:
			return Utils.unchecked(new BAVec3(array, indices));
		case 4:
			return Utils.unchecked(new BAVec4(array, indices));
		default:
			return Utils.unchecked(new BAVecN(array, indices));
		}
	}

	public static <V extends BVec<V>> V BVecX(Object... o) {
		List<Boolean> list = new ArrayList<Boolean>();
		Utils.fillB(list, o);
		int size = list.size();
		boolean[] a = new boolean[size];
		for (int i = 0; i < size; i++) {
			a[i] = list.get(i).booleanValue();
		}
		return BVecX(a);
	}

}
