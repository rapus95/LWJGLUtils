package xor.model.ms3d;

import java.io.DataInput;
import java.io.IOException;

import xor.model.DataInputStreamEx;
import xor.vecmat.vec.f.Vec4;

public class MS3DMaterial {

	private String name;
	public Vec4 ambient;
	public Vec4 diffuse;
	public Vec4 specular;
	public Vec4 emissive;
	public float shininess;
	public float transparency;
	public byte mode;
	public String texture;
	public String alphamap;
	public String comment;
	
	public MS3DMaterial(DataInput dataInput) throws IOException {
		name = DataInputStreamEx.readString(dataInput, 32);
		ambient = DataInputStreamEx.readVec4(dataInput);
		diffuse = DataInputStreamEx.readVec4(dataInput);
		specular = DataInputStreamEx.readVec4(dataInput);
		emissive = DataInputStreamEx.readVec4(dataInput);
		shininess = dataInput.readFloat();
		transparency = dataInput.readFloat();
		mode = dataInput.readByte();
		texture = DataInputStreamEx.readString(dataInput, 128);
		alphamap = DataInputStreamEx.readString(dataInput, 128);
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getName() {
		return name;
	}
	
	public Vec4 getAmbient() {
		return ambient;
	}

	public Vec4 getDiffuse() {
		return diffuse;
	}

	public Vec4 getSpecular() {
		return specular;
	}

	public Vec4 getEmissive() {
		return emissive;
	}

	public float getShininess() {
		return shininess;
	}

	public float getTransparency() {
		return transparency;
	}

	public byte getMode() {
		return mode;
	}

	public String getTexture() {
		return texture;
	}

	public String getAlphamap() {
		return alphamap;
	}

	public String getComment() {
		return comment;
	}
	
	@Override
	public String toString() {
		return "Material [name=" + name + ", ambient=" + ambient + ", diffuse="
				+ diffuse + ", specular=" + specular + ", emissive=" + emissive
				+ ", shininess=" + shininess + ", transparency=" + transparency
				+ ", mode=" + mode + ", texture=" + texture + ", alphamap="
				+ alphamap + ", comment=" + comment + "]";
	}
	
}
