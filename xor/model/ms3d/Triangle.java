package xor.model.ms3d;

import java.io.DataInput;
import java.io.IOException;
import java.util.Arrays;

import xor.model.DataInputStreamEx;
import xor.vecmat.vec.f.Vec2;
import xor.vecmat.vec.f.Vec3;

public class Triangle {

	public static final int VERTEX_COUNT = 3;

	private byte flags;
	private TriangleVertex[] verteces = new TriangleVertex[VERTEX_COUNT];
	private byte smoothingGroup;
	private short groupIndex;

	public Triangle(DataInput dataInput) throws IOException {
		flags = (byte) dataInput.readUnsignedShort();
		for (int i = 0; i < VERTEX_COUNT; i++) {
			verteces[i] = new TriangleVertex();
			verteces[i].textureCoord = Vec2.Vec2();
		}
		for (int i = 0; i < VERTEX_COUNT; i++)
			verteces[i].vertexIndex = dataInput.readUnsignedShort();
		for (int i = 0; i < VERTEX_COUNT; i++)
			verteces[i].vertexNormal = DataInputStreamEx.readVec3(dataInput);
		for (int i = 0; i < VERTEX_COUNT; i++)
			verteces[i].textureCoord.x(dataInput.readFloat());
		for (int i = 0; i < VERTEX_COUNT; i++)
			verteces[i].textureCoord.y(dataInput.readFloat());
		smoothingGroup = dataInput.readByte();
		groupIndex = (short) dataInput.readUnsignedByte();
	}

	public int getFlags() {
		return flags;
	}

	public int getSmoothingGroup() {
		return smoothingGroup;
	}

	public int getGroupIndex() {
		return groupIndex;
	}

	public int getVertexIndex(int index) {
		return verteces[index].vertexIndex;
	}

	public Vec3 getVertexNormal(int index) {
		return verteces[index].vertexNormal;
	}

	public Vec2 getTextureCoord(int index) {
		return verteces[index].textureCoord;
	}

	@Override
	public String toString() {
		return "Triangle [flags=" + flags + ", verteces=" + Arrays.toString(verteces) + ", smoothingGroup=" + smoothingGroup + ", groupIndex=" + groupIndex
				+ "]";
	}

	private static class TriangleVertex {

		public int vertexIndex;
		public Vec3 vertexNormal;
		public Vec2 textureCoord;

		@Override
		public String toString() {
			return "TriangleVertex [vertexIndex=" + vertexIndex + ", vertexNormal=" + vertexNormal + ", textureCoord=" + textureCoord + "]";
		}

	}

}
