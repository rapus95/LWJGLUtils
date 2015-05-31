package xor.opengl;

import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL11;

public enum Type {

	BYTE(1, GL11.GL_BYTE) {
		@Override
		public void storet(ByteBuffer bb, Type t, int v, ICast cast) {
			bb.put((byte) cast.t2t(this, t, v));
		}

		@Override
		public void storef(ByteBuffer bb, float v, ICast cast) {
			bb.put((byte) cast.f2t(this, v));
		}

		@Override
		public void stored(ByteBuffer bb, double v, ICast cast) {
			bb.put((byte) cast.d2t(this, v));
		}
	} ,
	UBYTE(1 | (1 << 31), GL11.GL_UNSIGNED_BYTE) {
		@Override
		public void storet(ByteBuffer bb, Type t, int v, ICast cast) {
			bb.put((byte) cast.t2t(this, t, v));
		}

		@Override
		public void storef(ByteBuffer bb, float v, ICast cast) {
			bb.put((byte) cast.f2t(this, v));
		}

		@Override
		public void stored(ByteBuffer bb, double v, ICast cast) {
			bb.put((byte) cast.d2t(this, v));
		}
	} ,
	SHORT(2, GL11.GL_SHORT) {
		@Override
		public void storet(ByteBuffer bb, Type t, int v, ICast cast) {
			bb.putShort((short) cast.t2t(this, t, v));
		}

		@Override
		public void storef(ByteBuffer bb, float v, ICast cast) {
			bb.putShort((short) cast.f2t(this, v));
		}

		@Override
		public void stored(ByteBuffer bb, double v, ICast cast) {
			bb.putShort((short) cast.d2t(this, v));
		}
	} ,
	USHORT(2 | (1 << 31), GL11.GL_UNSIGNED_SHORT) {
		@Override
		public void storet(ByteBuffer bb, Type t, int v, ICast cast) {
			bb.putShort((short) cast.t2t(this, t, v));
		}

		@Override
		public void storef(ByteBuffer bb, float v, ICast cast) {
			bb.putShort((short) cast.f2t(this, v));
		}

		@Override
		public void stored(ByteBuffer bb, double v, ICast cast) {
			bb.putShort((short) cast.d2t(this, v));
		}
	} ,
	INT(4, GL11.GL_INT) {
		@Override
		public void storet(ByteBuffer bb, Type t, int v, ICast cast) {
			bb.putInt(cast.t2t(this, t, v));
		}

		@Override
		public void storef(ByteBuffer bb, float v, ICast cast) {
			bb.putInt(cast.f2t(this, v));
		}

		@Override
		public void stored(ByteBuffer bb, double v, ICast cast) {
			bb.putInt(cast.d2t(this, v));
		}
	},
	UINT(4 | (1 << 31), GL11.GL_UNSIGNED_INT) {
		@Override
		public void storet(ByteBuffer bb, Type t, int v, ICast cast) {
			bb.putInt(cast.t2t(this, t, v));
		}

		@Override
		public void storef(ByteBuffer bb, float v, ICast cast) {
			bb.putInt(cast.f2t(this, v));
		}

		@Override
		public void stored(ByteBuffer bb, double v, ICast cast) {
			bb.putInt(cast.d2t(this, v));
		}
	},
	FLOAT(4 | (1 << 30), GL11.GL_FLOAT) {
		@Override
		public void storet(ByteBuffer bb, Type t, int v, ICast cast) {
			bb.putFloat(cast.t2f(t, v));
		}

		@Override
		public void storef(ByteBuffer bb, float v, ICast cast) {
			bb.putFloat(v);
		}

		@Override
		public void stored(ByteBuffer bb, double v, ICast cast) {
			bb.putFloat((float) v);
		}
	} ,
	DOUBLE(8 | (1 << 30), GL11.GL_DOUBLE) {
		@Override
		public void storet(ByteBuffer bb, Type t, int v, ICast cast) {
			bb.putDouble(cast.t2d(t, v));
		}

		@Override
		public void storef(ByteBuffer bb, float v, ICast cast) {
			bb.putDouble(v);
		}

		@Override
		public void stored(ByteBuffer bb, double v, ICast cast) {
			bb.putDouble(v);
		}
	};

	private static final int BYTE_MASK = 0xFF;
	private static final int UNSIGNED = 1 << 31;
	private static final int FLOAT_POINT = 1 << 30;

	private final int flags;
	private final int oglType;

	Type(int flags, int oglType) {
		this.flags = flags;
		this.oglType = oglType;
	}

	public int getBytes() {
		return flags & BYTE_MASK;
	}

	public boolean isUnsigned() {
		return (flags & UNSIGNED) != 0;
	}

	public boolean isFloat() {
		return (flags & FLOAT_POINT) != 0;
	}

	public int getBits() {
		return getBytes() * 8;
	}

	public int getMax() {
		return (1 << (getBits() - (isUnsigned() ? 0 : 1))) - 1;
	}

	public int getMin() {
		return isUnsigned() ? 0 : -(1 << (getBits() - 1));
	}

	public abstract void storet(ByteBuffer bb, Type t, int v, ICast cast);

	public abstract void storef(ByteBuffer bb, float v, ICast cast);

	public abstract void stored(ByteBuffer bb, double v, ICast cast);

	public int asOGL() {
		return oglType;
	}

}
