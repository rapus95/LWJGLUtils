package xor.utils;

import java.nio.ByteOrder;

public class MemoryUtils {

	public static long readLong(byte[] buffer, int index, ByteOrder byteOrder) {
		if (byteOrder == ByteOrder.BIG_ENDIAN) {
			return ((long) (buffer[index] & 0xFF) << 56) | ((long) (buffer[index + 1] & 0xFF) << 48) | ((long) (buffer[index + 2] & 0xFF) << 40)
					| ((long) (buffer[index + 3] & 0xFF) << 32) | ((buffer[index + 4] & 0xFF) << 24) | ((buffer[index + 5] & 0xFF) << 16)
					| ((buffer[index + 6] & 0xFF) << 8) | (buffer[index + 7] & 0xFF);
		} else {
			return ((long) (buffer[index + 7] & 0xFF) << 56) | ((long) (buffer[index + 6] & 0xFF) << 48) | ((long) (buffer[index + 5] & 0xFF) << 40)
					| ((long) (buffer[index + 4] & 0xFF) << 32) | ((buffer[index + 3] & 0xFF) << 24) | ((buffer[index + 2] & 0xFF) << 16)
					| ((buffer[index + 1] & 0xFF) << 8) | (buffer[index] & 0xFF);
		}
	}

	public static int readInt(byte[] buffer, int index, ByteOrder byteOrder) {
		if (byteOrder == ByteOrder.BIG_ENDIAN) {
			return ((buffer[index] & 0xFF) << 24) | ((buffer[index + 1] & 0xFF) << 16) | ((buffer[index + 2] & 0xFF) << 8) | (buffer[index + 3] & 0xFF);
		} else {
			return (buffer[index] & 0xFF) | ((buffer[index + 1] & 0xFF) << 8) | ((buffer[index + 2] & 0xFF) << 16) | ((buffer[index + 3] & 0xFF) << 24);
		}
	}

	public static long readUInt(byte[] buffer, int index, ByteOrder byteOrder) {
		return readInt(buffer, index, byteOrder) & 0xFFFFFFFFL;
	}

	public static char readChar(byte[] buffer, int index, ByteOrder byteOrder) {
		return (char) readUShort(buffer, index, byteOrder);
	}

	public static int readUShort(byte[] buffer, int index, ByteOrder byteOrder) {
		if (byteOrder == ByteOrder.BIG_ENDIAN) {
			return ((buffer[index] & 0xFF) << 8) | (buffer[index + 1] & 0xFF);
		} else {
			return (buffer[index] & 0xFF) | ((buffer[index + 1] & 0xFF) << 8);
		}
	}

	public static short readShort(byte[] buffer, int index, ByteOrder byteOrder) {
		return (short) readUShort(buffer, index, byteOrder);
	}

	public static int readUByte(byte[] buffer, int index, ByteOrder byteOrder) {
		return buffer[index] & 0xFF;
	}

	public static byte readByte(byte[] buffer, int index, ByteOrder byteOrder) {
		return buffer[index];
	}

	public static boolean readBoolean(byte[] buffer, int index, ByteOrder byteOrder) {
		return buffer[index] != 0;
	}

}
