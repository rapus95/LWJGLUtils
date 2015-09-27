package xor.model.ms3d;

import java.io.DataInput;
import java.io.IOException;
import java.util.Arrays;

import xor.utils.DataInputStreamEx;
import xor.vecmat.vec.f.Vec3;

public class Vertex {

	public static final int BONE_COUNT = 4;
	
	private byte flags;
	private Vec3 vertex;
	private byte[] boneIds = {-1, -1, -1, -1};
	private float[] weights = {1.0f, 0.0f, 0.0f, 0.0f};
	private short referenceCount;
	private int extra;
	
	public Vertex(DataInput dataInput) throws IOException {
		flags = dataInput.readByte();
		vertex = DataInputStreamEx.readVec3(dataInput);
		boneIds[0] = dataInput.readByte();
		referenceCount = (short) dataInput.readUnsignedByte();
	}

	public void patch(int vertexSubVersion, DataInput dataInput) throws IOException {
		for(int i=1; i<BONE_COUNT; i++){
			boneIds[i] = dataInput.readByte();
		}
		for(int i=0; i<3; i++){
			weights[i] = dataInput.readUnsignedByte();
			if(vertexSubVersion==1){
				weights[i]/=255;
			}else{
				weights[i]/=100;
			}
		}
		weights[3] = 1.0f-weights[0]-weights[1]-weights[2];
		if(vertexSubVersion>1){
			extra = dataInput.readInt();
		}
	}
	
	public int getFlags() {
		return flags;
	}

	public Vec3 getVertex() {
		return vertex;
	}

	public int getBoneIds(int index) {
		return boneIds[index];
	}

	public float getWeights(int index) {
		return weights[index];
	}

	public int getReferenceCount() {
		return referenceCount;
	}

	public long getExtra() {
		return extra;
	}

	@Override
	public String toString() {
		return "Vertex [flags=" + flags + ", vertex=" + vertex + ", boneIds="
				+ Arrays.toString(boneIds) + ", weights="
				+ Arrays.toString(weights) + ", referenceCount="
				+ referenceCount + ", extra=" + extra + "]";
	}
	
}
