package xor.opengl.vbo;

import java.nio.ByteBuffer;

import xor.opengl.Cast;
import xor.opengl.ICast;
import xor.opengl.Type;
import xor.opengl.shader.ShaderAttribute;

public final class VBOAttribute {

	public static final VBOAttribute POS3F = new VBOAttribute(ShaderAttribute.VERTEX_NAME, Cast.INSTANCE, 0, 0, 0);
	public static final VBOAttribute POS4F = new VBOAttribute(ShaderAttribute.VERTEX_NAME, Cast.INSTANCE, 0, 0, 0, 1);
	public static final VBOAttribute TEXCOORD2F = new VBOAttribute(ShaderAttribute.TEXCOORD_NAME, Cast.INSTANCE, 0, 0);
	public static final VBOAttribute NORMAL3F = new VBOAttribute(ShaderAttribute.NORMAL_NAME, Cast.INSTANCE, 0, 0, 0);
	public static final VBOAttribute COLOR3F = new VBOAttribute(ShaderAttribute.COLOR_NAME, Cast.INSTANCE, 0, 0, 0);
	public static final VBOAttribute COLOR4F = new VBOAttribute(ShaderAttribute.COLOR_NAME, Cast.INSTANCE, 0, 0, 0, 1);

	private String name;
	private Type type;
	private int count;
	private int hash;
	private ICast cast;
	private Object def;

	public VBOAttribute(String name, Type type, ICast cast, int... def) {
		if (type == null)
			throw new NullPointerException();
		if (name == null)
			throw new NullPointerException();
		if (def == null)
			throw new NullPointerException();
		if (cast == null)
			throw new NullPointerException();
		if (type == Type.FLOAT || type == Type.DOUBLE)
			throw new IllegalArgumentException();
		this.name = name;
		this.type = type;
		this.count = def.length;
		this.def = def;
		this.cast = cast;
		calcHash();
	}

	public VBOAttribute(String name, ICast cast, float... def) {
		if (name == null)
			throw new NullPointerException();
		if (def == null)
			throw new NullPointerException();
		if (cast == null)
			throw new NullPointerException();
		if (type == Type.FLOAT || type == Type.DOUBLE)
			throw new IllegalArgumentException();
		this.name = name;
		this.type = Type.FLOAT;
		this.count = def.length;
		this.def = def;
		this.cast = cast;
		calcHash();
	}

	public VBOAttribute(String name, ICast cast, double... def) {
		if (name == null)
			throw new NullPointerException();
		if (def == null)
			throw new NullPointerException();
		if (cast == null)
			throw new NullPointerException();
		if (type == Type.FLOAT || type == Type.DOUBLE)
			throw new IllegalArgumentException();
		this.name = name;
		this.type = Type.DOUBLE;
		this.count = def.length;
		this.def = def;
		this.cast = cast;
		calcHash();
	}

	private void calcHash() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cast.hashCode();
		result = prime * result + count;
		result = prime * result + def.hashCode();
		result = prime * result + name.hashCode();
		result = prime * result + type.hashCode();
		hash = result;
	}

	@Override
	public int hashCode() {
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VBOAttribute other = (VBOAttribute) obj;
		if (cast == null) {
			if (other.cast != null)
				return false;
		} else if (!cast.equals(other.cast))
			return false;
		if (count != other.count)
			return false;
		if (def == null) {
			if (other.def != null)
				return false;
		} else if (!def.equals(other.def))
			return false;
		if (hash != other.hash)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	public int getStorageSize() {
		return type.getBytes() * count;
	}

	public void storebv(ByteBuffer byteBuffer, int[] ptr, int offset, int length) {
		storetv(byteBuffer, Type.BYTE, ptr, offset, length);
	}

	public void storebv(ByteBuffer byteBuffer, byte[] ptr, int offset, int length) {
		storetv(byteBuffer, Type.BYTE, ptr, offset, length);
	}

	public void storesv(ByteBuffer byteBuffer, int[] ptr, int offset, int length) {
		storetv(byteBuffer, Type.SHORT, ptr, offset, length);
	}

	public void storesv(ByteBuffer byteBuffer, short[] ptr, int offset, int length) {
		storetv(byteBuffer, Type.SHORT, ptr, offset, length);
	}

	public void storeiv(ByteBuffer byteBuffer, int[] ptr, int offset, int length) {
		storetv(byteBuffer, Type.INT, ptr, offset, length);
	}

	public void storeubv(ByteBuffer byteBuffer, int[] ptr, int offset, int length) {
		storetv(byteBuffer, Type.UBYTE, ptr, offset, length);
	}

	public void storeubv(ByteBuffer byteBuffer, byte[] ptr, int offset, int length) {
		storetv(byteBuffer, Type.UBYTE, ptr, offset, length);
	}

	public void storeusv(ByteBuffer byteBuffer, int[] ptr, int offset, int length) {
		storetv(byteBuffer, Type.USHORT, ptr, offset, length);
	}

	public void storeusv(ByteBuffer byteBuffer, short[] ptr, int offset, int length) {
		storetv(byteBuffer, Type.USHORT, ptr, offset, length);
	}

	public void storeuiv(ByteBuffer byteBuffer, int[] ptr, int offset, int length) {
		storetv(byteBuffer, Type.UINT, ptr, offset, length);
	}

	public void storefv(ByteBuffer byteBuffer, float[] ptr, int offset, int length) {
		for (int i = 0; i < count; i++) {
			if (i >= length) {
				storeDefault(byteBuffer, i);
				return;
			}
			type.storef(byteBuffer, ptr[i + offset], cast);
		}
	}

	public void storedv(ByteBuffer byteBuffer, double[] ptr, int offset, int length) {
		for (int i = 0; i < count; i++) {
			if (i >= length) {
				storeDefault(byteBuffer, i);
				return;
			}
			type.stored(byteBuffer, ptr[i + offset], cast);
		}
	}

	public void storetv(ByteBuffer byteBuffer, Type type, byte[] ptr, int offset, int length) {
		for (int i = 0; i < count; i++) {
			if (i >= length) {
				storeDefault(byteBuffer, i);
				return;
			}
			type.storet(byteBuffer, type, ptr[i + offset], cast);
		}
	}

	public void storetv(ByteBuffer byteBuffer, Type type, short[] ptr, int offset, int length) {
		for (int i = 0; i < count; i++) {
			if (i >= length) {
				storeDefault(byteBuffer, i);
				return;
			}
			type.storet(byteBuffer, type, ptr[i + offset], cast);
		}
	}

	public void storetv(ByteBuffer byteBuffer, Type type, int[] ptr, int offset, int length) {
		for (int i = 0; i < count; i++) {
			if (i >= length) {
				storeDefault(byteBuffer, i);
				return;
			}
			type.storet(byteBuffer, type, ptr[i + offset], cast);
		}
	}

	private void storeDefault(ByteBuffer byteBuffer, int i) {
		if (type == Type.FLOAT) {
			float[] fa = (float[]) def;
			for (; i < count; i++) {
				byteBuffer.putFloat(fa[i]);
			}
		} else if (type == Type.DOUBLE) {
			double[] da = (double[]) def;
			for (; i < count; i++) {
				byteBuffer.putDouble(da[i]);
			}
		}
		int[] ia = (int[]) def;
		for (; i < count; i++) {
			type.storet(byteBuffer, type, ia[i], cast);
		}
	}

	public String getName() {
		return name;
	}

	public int getCount() {
		return count;
	}

	public Type getType() {
		return type;
	}

}
