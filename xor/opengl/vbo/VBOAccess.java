package xor.opengl.vbo;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class VBOAccess {

	private final int[] IBUFFER = new int[4];
	private final float[] FBUFFER = new float[4];
	private final double[] DBUFFER = new double[4];
	private final VBO vbo;
	private ByteBuffer bb;
	private ByteBuffer[] buffers;
	private int count;

	protected VBOAccess(VBO vbo) {
		this.vbo = vbo;
		VBOAttribute[] attributes = vbo.getAttributes();
		buffers = new ByteBuffer[attributes.length];
		int i = 0;
		for (VBOAttribute attribute : attributes) {
			buffers[i++] = ByteBuffer.allocate(attribute.getStorageSize()).order(ByteOrder.nativeOrder());
		}
	}

	public void set1b(VBOAttribute attr, int v) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			IBUFFER[0] = v;
			attr.storebv((ByteBuffer) buffers[info[0]].position(0), IBUFFER, 0, 1);
		}
	}

	public void set1s(VBOAttribute attr, int v) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			IBUFFER[0] = v;
			attr.storesv((ByteBuffer) buffers[info[0]].position(0), IBUFFER, 0, 1);
		}
	}

	public void set1i(VBOAttribute attr, int v) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			IBUFFER[0] = v;
			attr.storeiv((ByteBuffer) buffers[info[0]].position(0), IBUFFER, 0, 1);
		}
	}

	public void set1ub(VBOAttribute attr, int v) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			IBUFFER[0] = v;
			attr.storeubv((ByteBuffer) buffers[info[0]].position(0), IBUFFER, 0, 1);
		}
	}

	public void set1us(VBOAttribute attr, int v) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			IBUFFER[0] = v;
			attr.storeusv((ByteBuffer) buffers[info[0]].position(0), IBUFFER, 0, 1);
		}
	}

	public void set1ui(VBOAttribute attr, int v) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			IBUFFER[0] = v;
			attr.storeuiv((ByteBuffer) buffers[info[0]].position(0), IBUFFER, 0, 1);
		}
	}

	public void set1f(VBOAttribute attr, float v) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			FBUFFER[0] = v;
			attr.storefv((ByteBuffer) buffers[info[0]].position(0), FBUFFER, 0, 1);
		}
	}

	public void set1d(VBOAttribute attr, double v) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			DBUFFER[0] = v;
			attr.storedv((ByteBuffer) buffers[info[0]].position(0), DBUFFER, 0, 1);
		}
	}

	public void set2b(VBOAttribute attr, int v1, int v2) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			IBUFFER[0] = v1;
			IBUFFER[1] = v2;
			attr.storebv((ByteBuffer) buffers[info[0]].position(0), IBUFFER, 0, 2);
		}
	}

	public void set2s(VBOAttribute attr, int v1, int v2) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			IBUFFER[0] = v1;
			IBUFFER[1] = v2;
			attr.storesv((ByteBuffer) buffers[info[0]].position(0), IBUFFER, 0, 2);
		}
	}

	public void set2i(VBOAttribute attr, int v1, int v2) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			IBUFFER[0] = v1;
			IBUFFER[1] = v2;
			attr.storeiv((ByteBuffer) buffers[info[0]].position(0), IBUFFER, 0, 2);
		}
	}

	public void set2ub(VBOAttribute attr, int v1, int v2) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			IBUFFER[0] = v1;
			IBUFFER[1] = v2;
			attr.storeubv((ByteBuffer) buffers[info[0]].position(0), IBUFFER, 0, 2);
		}
	}

	public void set2us(VBOAttribute attr, int v1, int v2) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			IBUFFER[0] = v1;
			IBUFFER[1] = v2;
			attr.storeusv((ByteBuffer) buffers[info[0]].position(0), IBUFFER, 0, 2);
		}
	}

	public void set2ui(VBOAttribute attr, int v1, int v2) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			IBUFFER[0] = v1;
			IBUFFER[1] = v2;
			attr.storeuiv((ByteBuffer) buffers[info[0]].position(0), IBUFFER, 0, 2);
		}
	}

	public void set2f(VBOAttribute attr, float v1, float v2) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			FBUFFER[0] = v1;
			FBUFFER[1] = v2;
			attr.storefv((ByteBuffer) buffers[info[0]].position(0), FBUFFER, 0, 2);
		}
	}

	public void set2d(VBOAttribute attr, double v1, double v2) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			DBUFFER[0] = v1;
			DBUFFER[1] = v2;
			attr.storedv((ByteBuffer) buffers[info[0]].position(0), DBUFFER, 0, 2);
		}
	}

	public void set3b(VBOAttribute attr, int v1, int v2, int v3) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			IBUFFER[0] = v1;
			IBUFFER[1] = v2;
			IBUFFER[2] = v3;
			attr.storebv((ByteBuffer) buffers[info[0]].position(0), IBUFFER, 0, 3);
		}
	}

	public void set3s(VBOAttribute attr, int v1, int v2, int v3) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			IBUFFER[0] = v1;
			IBUFFER[1] = v2;
			IBUFFER[2] = v3;
			attr.storesv((ByteBuffer) buffers[info[0]].position(0), IBUFFER, 0, 3);
		}
	}

	public void set3i(VBOAttribute attr, int v1, int v2, int v3) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			IBUFFER[0] = v1;
			IBUFFER[1] = v2;
			IBUFFER[2] = v3;
			attr.storeiv((ByteBuffer) buffers[info[0]].position(0), IBUFFER, 0, 3);
		}
	}

	public void set3ub(VBOAttribute attr, int v1, int v2, int v3) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			IBUFFER[0] = v1;
			IBUFFER[1] = v2;
			IBUFFER[2] = v3;
			attr.storeubv((ByteBuffer) buffers[info[0]].position(0), IBUFFER, 0, 3);
		}
	}

	public void set3us(VBOAttribute attr, int v1, int v2, int v3) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			IBUFFER[0] = v1;
			IBUFFER[1] = v2;
			IBUFFER[2] = v3;
			attr.storeusv((ByteBuffer) buffers[info[0]].position(0), IBUFFER, 0, 3);
		}
	}

	public void set3ui(VBOAttribute attr, int v1, int v2, int v3) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			IBUFFER[0] = v1;
			IBUFFER[1] = v2;
			IBUFFER[2] = v3;
			attr.storeuiv((ByteBuffer) buffers[info[0]].position(0), IBUFFER, 0, 3);
		}
	}

	public void set3f(VBOAttribute attr, float v1, float v2, float v3) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			FBUFFER[0] = v1;
			FBUFFER[1] = v2;
			FBUFFER[2] = v3;
			attr.storefv((ByteBuffer) buffers[info[0]].position(0), FBUFFER, 0, 3);
		}
	}

	public void set3d(VBOAttribute attr, double v1, double v2, double v3) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			DBUFFER[0] = v1;
			DBUFFER[1] = v2;
			DBUFFER[2] = v3;
			attr.storedv((ByteBuffer) buffers[info[0]].position(0), DBUFFER, 0, 3);
		}
	}

	public void set4b(VBOAttribute attr, int v1, int v2, int v3, int v4) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			IBUFFER[0] = v1;
			IBUFFER[1] = v2;
			IBUFFER[2] = v3;
			IBUFFER[3] = v4;
			attr.storebv((ByteBuffer) buffers[info[0]].position(0), IBUFFER, 0, 4);
		}
	}

	public void set4s(VBOAttribute attr, int v1, int v2, int v3, int v4) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			IBUFFER[0] = v1;
			IBUFFER[1] = v2;
			IBUFFER[2] = v3;
			IBUFFER[3] = v4;
			attr.storesv((ByteBuffer) buffers[info[0]].position(0), IBUFFER, 0, 4);
		}
	}

	public void set4i(VBOAttribute attr, int v1, int v2, int v3, int v4) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			IBUFFER[0] = v1;
			IBUFFER[1] = v2;
			IBUFFER[2] = v3;
			IBUFFER[3] = v4;
			attr.storeiv((ByteBuffer) buffers[info[0]].position(0), IBUFFER, 0, 4);
		}
	}

	public void set4ub(VBOAttribute attr, int v1, int v2, int v3, int v4) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			IBUFFER[0] = v1;
			IBUFFER[1] = v2;
			IBUFFER[2] = v3;
			IBUFFER[3] = v4;
			attr.storeubv((ByteBuffer) buffers[info[0]].position(0), IBUFFER, 0, 4);
		}
	}

	public void set4us(VBOAttribute attr, int v1, int v2, int v3, int v4) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			IBUFFER[0] = v1;
			IBUFFER[1] = v2;
			IBUFFER[2] = v3;
			IBUFFER[3] = v4;
			attr.storeusv((ByteBuffer) buffers[info[0]].position(0), IBUFFER, 0, 4);
		}
	}

	public void set4ui(VBOAttribute attr, int v1, int v2, int v3, int v4) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			IBUFFER[0] = v1;
			IBUFFER[1] = v2;
			IBUFFER[2] = v3;
			IBUFFER[3] = v4;
			attr.storeuiv((ByteBuffer) buffers[info[0]].position(0), IBUFFER, 0, 4);
		}
	}

	public void set4f(VBOAttribute attr, float v1, float v2, float v3, float v4) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			FBUFFER[0] = v1;
			FBUFFER[1] = v2;
			FBUFFER[2] = v3;
			FBUFFER[3] = v4;
			attr.storefv((ByteBuffer) buffers[info[0]].position(0), FBUFFER, 0, 4);
		}
	}

	public void set4d(VBOAttribute attr, double v1, double v2, double v3, double v4) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			DBUFFER[0] = v1;
			DBUFFER[1] = v2;
			DBUFFER[2] = v3;
			DBUFFER[3] = v4;
			attr.storedv((ByteBuffer) buffers[info[0]].position(0), DBUFFER, 0, 4);
		}
	}

	public void setbv(VBOAttribute attr, int[] ptr, int offset, int length) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			attr.storebv((ByteBuffer) buffers[info[0]].position(0), ptr, offset, length);
		}
	}

	public void setbv(VBOAttribute attr, byte[] ptr, int offset, int length) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			attr.storebv((ByteBuffer) buffers[info[0]].position(0), ptr, offset, length);
		}
	}

	public void setsv(VBOAttribute attr, int[] ptr, int offset, int length) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			attr.storesv((ByteBuffer) buffers[info[0]].position(0), ptr, offset, length);
		}
	}

	public void setsv(VBOAttribute attr, short[] ptr, int offset, int length) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			attr.storesv((ByteBuffer) buffers[info[0]].position(0), ptr, offset, length);
		}
	}

	public void setiv(VBOAttribute attr, int[] ptr, int offset, int length) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			attr.storeiv((ByteBuffer) buffers[info[0]].position(0), ptr, offset, length);
		}
	}

	public void setubv(VBOAttribute attr, int[] ptr, int offset, int length) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			attr.storeubv((ByteBuffer) buffers[info[0]].position(0), ptr, offset, length);
		}
	}

	public void setubv(VBOAttribute attr, byte[] ptr, int offset, int length) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			attr.storeubv((ByteBuffer) buffers[info[0]].position(0), ptr, offset, length);
		}
	}

	public void setusv(VBOAttribute attr, int[] ptr, int offset, int length) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			attr.storeusv((ByteBuffer) buffers[info[0]].position(0), ptr, offset, length);
		}
	}

	public void setusv(VBOAttribute attr, short[] ptr, int offset, int length) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			attr.storeusv((ByteBuffer) buffers[info[0]].position(0), ptr, offset, length);
		}
	}

	public void setuiv(VBOAttribute attr, int[] ptr, int offset, int length) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			attr.storeuiv((ByteBuffer) buffers[info[0]].position(0), ptr, offset, length);
		}
	}

	public void setfv(VBOAttribute attr, float[] ptr, int offset, int length) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			attr.storefv((ByteBuffer) buffers[info[0]].position(0), ptr, offset, length);
		}
	}

	public void setdv(VBOAttribute attr, double[] ptr, int offset, int length) {
		int[] info = vbo.getAttributeInfo(attr);
		if (info != null) {
			attr.storedv((ByteBuffer) buffers[info[0]].position(0), ptr, offset, length);
		}
	}

	public void emit() {
		int cap;
		if (bb == null) {
			cap = vbo.getBucketSize() * 1000;
			bb = ByteBuffer.allocateDirect(cap).order(ByteOrder.nativeOrder());
		} else if (bb.capacity() <= (cap = bb.position() + vbo.getBucketSize())) {
			cap += vbo.getBucketSize() * 1000;
			ByteBuffer nbb = ByteBuffer.allocateDirect(cap).order(ByteOrder.nativeOrder());
			bb.limit(bb.position());
			bb.position(0);
			nbb.put(bb);
			bb = nbb;
		}
		for (ByteBuffer buffer : buffers) {
			buffer.position(0);
			bb.put(buffer);
		}
		count++;
	}

	public VBO upload() {
		bb.limit(bb.position());
		bb.position(0);
		vbo.upload(bb, count);
		return vbo;
	}

}
