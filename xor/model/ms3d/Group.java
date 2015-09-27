package xor.model.ms3d;

import java.io.DataInput;
import java.io.IOException;
import java.util.Arrays;

import xor.utils.DataInputStreamEx;

public class Group {

	private byte flags;
	private String name;
	private int[] triangleIndices;
	private byte materialIndex;
	private String comment;
	
	public Group(DataInput dataInput) throws IOException {
		flags = dataInput.readByte();
		name = DataInputStreamEx.readString(dataInput, 32);
		int numTriangles = dataInput.readUnsignedShort();
		triangleIndices = new int[numTriangles];
		for(int i=0; i<numTriangles; i++){
			triangleIndices[i] = dataInput.readUnsignedShort();
		}
		materialIndex = dataInput.readByte();
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public int getFlags() {
		return flags;
	}
	
	public String getName() {
		return name;
	}
	
	public int[] getTriangleIndices(){
		return triangleIndices;
	}
	
	public int getMaterialIndex(){
		return materialIndex;
	}
	
	public String getComment(){
		return comment;
	}
	
	@Override
	public String toString() {
		return "Group [flags=" + flags + ", name=" + name
				+ ", triangleIndices=" + Arrays.toString(triangleIndices)
				+ ", materialIndex=" + materialIndex + ", comment=" + comment
				+ "]";
	}
	
}
