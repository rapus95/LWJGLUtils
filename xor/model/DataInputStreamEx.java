package xor.model;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.nio.ByteOrder;

import xor.vecmat.vec.f.Vec2;
import xor.vecmat.vec.f.Vec3;
import xor.vecmat.vec.f.Vec4;

public class DataInputStreamEx extends FilterInputStream implements DataInput {

	private final ByteOrder byteOrder;

	private final byte readBuffer[] = new byte[8];

	private char lineBuffer[];

	public DataInputStreamEx(InputStream in) {
		this(in, ByteOrder.nativeOrder());
	}

	public DataInputStreamEx(InputStream in, ByteOrder byteOrder) {
		super(in);
		this.byteOrder = byteOrder;
	}

	public void readFully(byte b[]) throws IOException {
		readFully(b, 0, b.length);
	}

	public void readFully(byte b[], int off, int len) throws IOException {
		if (len < 0)
			throw new IndexOutOfBoundsException();
		int n = 0;
		while (n < len) {
			int count = in.read(b, off + n, len - n);
			if (count < 0)
				throw new EOFException();
			n += count;
		}
	}

	public int skipBytes(int n) throws IOException {
		int total = 0;
		int cur = 0;

		while ((total < n) && ((cur = (int) in.skip(n - total)) > 0)) {
			total += cur;
		}

		return total;
	}

	public boolean readBoolean() throws IOException {
		int ch = in.read();
		if (ch < 0)
			throw new EOFException();
		return ch != 0;
	}

	public byte readByte() throws IOException {
		int ch = in.read();
		if (ch < 0)
			throw new EOFException();
		return (byte) (ch);
	}

	public int readUnsignedByte() throws IOException {
		int ch = in.read();
		if (ch < 0)
			throw new EOFException();
		return ch;
	}

	public short readShort() throws IOException {
		readFully(readBuffer, 0, 2);
		return MemoryUtils.readShort(readBuffer, 0, byteOrder);
	}

	public int readUnsignedShort() throws IOException {
		readFully(readBuffer, 0, 2);
		return MemoryUtils.readUShort(readBuffer, 0, byteOrder);
	}

	public char readChar() throws IOException {
		readFully(readBuffer, 0, 2);
		return MemoryUtils.readChar(readBuffer, 0, byteOrder);
	}

	public int readInt() throws IOException {
		readFully(readBuffer, 0, 4);
		return MemoryUtils.readInt(readBuffer, 0, byteOrder);
	}

	public long readLong() throws IOException {
		readFully(readBuffer, 0, 8);
		return MemoryUtils.readLong(readBuffer, 0, byteOrder);
	}

	public float readFloat() throws IOException {
		return Float.intBitsToFloat(readInt());
	}

	public double readDouble() throws IOException {
		return Double.longBitsToDouble(readLong());
	}

	public String readLine() throws IOException {
		char buf[] = lineBuffer;

		if (buf == null) {
			buf = lineBuffer = new char[128];
		}

		int room = buf.length;
		int offset = 0;
		int c;

		loop: while (true) {
			switch (c = in.read()) {
			case -1:
			case '\n':
				break loop;

			case '\r':
				int c2 = in.read();
				if ((c2 != '\n') && (c2 != -1)) {
					if (!(in instanceof PushbackInputStream)) {
						this.in = new PushbackInputStream(in);
					}
					((PushbackInputStream) in).unread(c2);
				}
				break loop;

			default:
				if (--room < 0) {
					buf = new char[offset + 128];
					room = buf.length - offset - 1;
					System.arraycopy(lineBuffer, 0, buf, 0, offset);
					lineBuffer = buf;
				}
				buf[offset++] = (char) c;
				break;
			}
		}
		if ((c == -1) && (offset == 0)) {
			return null;
		}
		return String.copyValueOf(buf, 0, offset);
	}

	/**
	 * @see java.io.DataInputStream#readUTF(java.io.DataInput)
	 */
	public String readUTF() throws IOException {
		return DataInputStream.readUTF(this);
	}

	public String readString(int size) throws IOException {
		return readString(this, size);
	}
	
	public static String readString(DataInput dataInput, int size) throws IOException {
		byte[] buffer = new byte[size];
		dataInput.readFully(buffer);
		int i = buffer.length - 1;
		for (; i >= 0 && buffer[i] == 0; i--)
			;
		return new String(buffer, 0, i + 1).trim();
	}
	
	public static Vec2 readVec2(DataInput dataInput) throws IOException {
		return Vec2.Vec2(dataInput.readFloat(), dataInput.readFloat());
	}
	
	public static Vec3 readVec3(DataInput dataInput) throws IOException {
		return Vec3.Vec3(dataInput.readFloat(), dataInput.readFloat(), dataInput.readFloat());
	}
	
	public static Vec4 readVec4(DataInput dataInput) throws IOException {
		return Vec4.Vec4(dataInput.readFloat(), dataInput.readFloat(), dataInput.readFloat(), dataInput.readFloat());
	}

}
