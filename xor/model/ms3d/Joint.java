package xor.model.ms3d;

import java.io.DataInput;
import java.io.IOException;
import java.util.Arrays;

import xor.model.DataInputStreamEx;
import xor.vecmat.vec.f.Vec3;

public class Joint {

	private byte flags;
	private String name;
	private String parentName;
	private int parentIndex;
	private Vec3 rotation;
	private Vec3 position;
	private KeyFrame[] keyFrameRotations;
	private KeyFrame[] keyFrameTranslations;
	private String comment;
	private Vec3 color;

	public Joint(DataInput dataInput) throws IOException {
		flags = dataInput.readByte();
		name = DataInputStreamEx.readString(dataInput, 32);
		parentName = DataInputStreamEx.readString(dataInput, 32);
		rotation = DataInputStreamEx.readVec3(dataInput);
		position = DataInputStreamEx.readVec3(dataInput);
		int numKeyFramesRotation = dataInput.readUnsignedShort();
		int numKeyFramesTranslation = dataInput.readUnsignedShort();
		keyFrameRotations = new KeyFrame[numKeyFramesRotation];
		keyFrameTranslations = new KeyFrame[numKeyFramesTranslation];
		for (int i = 0; i < numKeyFramesRotation; i++) {
			keyFrameRotations[i] = new KeyFrame(dataInput);
		}
		for (int i = 0; i < numKeyFramesTranslation; i++) {
			keyFrameTranslations[i] = new KeyFrame(dataInput);
		}
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void patch(int jointSubVersion, DataInput dataInput) throws IOException {
		color = DataInputStreamEx.readVec3(dataInput);
	}

	public void resolve(MS3DModel model) {
		parentIndex = model.getJointIndex(parentName);
	}

	public int getFlags() {
		return flags;
	}

	public String getName() {
		return name;
	}

	public int getParentIndex() {
		return parentIndex;
	}

	public Vec3 getRotation() {
		return rotation;
	}

	public Vec3 getPosition() {
		return position;
	}

	public String getComment() {
		return comment;
	}

	public Vec3 getColor() {
		return color;
	}

	public Vec3 getKeyFramesRotation(float time) {
		KeyFrame keyFrameRotation1 = null;
		KeyFrame keyFrameRotation2 = null;
		for (int i = 0; i < keyFrameRotations.length; i++) {
			KeyFrame keyFramesRotation = keyFrameRotations[i];
			if (keyFramesRotation.time < time && (keyFrameRotation1 == null || keyFrameRotation1.time < keyFramesRotation.time)) {
				keyFrameRotation1 = keyFramesRotation;
			}
			if (keyFramesRotation.time >= time && (keyFrameRotation2 == null || keyFrameRotation2.time > keyFramesRotation.time)) {
				keyFrameRotation2 = keyFramesRotation;
			}
		}
		if (keyFrameRotation1 == null)
			return keyFrameRotation2 == null ? null : keyFrameRotation2.vector;
		if (keyFrameRotation2 == null)
			return keyFrameRotation1.vector;
		float p = (time - keyFrameRotation1.time) / (keyFrameRotation2.time - keyFrameRotation1.time);
		return keyFrameRotation1.vector.mix(keyFrameRotation2.vector, p);
	}

	public Vec3 getKeyFrameTranslation(float time) {
		KeyFrame keyFrameTranslation1 = null;
		KeyFrame keyFrameTranslation2 = null;
		for (int i = 0; i < keyFrameTranslations.length; i++) {
			KeyFrame keyFrameTranslation = keyFrameTranslations[i];
			if (keyFrameTranslation.time < time && (keyFrameTranslation1 == null || keyFrameTranslation1.time < keyFrameTranslation.time)) {
				keyFrameTranslation1 = keyFrameTranslation;
			}
			if (keyFrameTranslation.time >= time && (keyFrameTranslation2 == null || keyFrameTranslation2.time > keyFrameTranslation.time)) {
				keyFrameTranslation2 = keyFrameTranslation;
			}
		}
		if (keyFrameTranslation1 == null)
			return keyFrameTranslation2 == null ? null : keyFrameTranslation2.vector;
		if (keyFrameTranslation2 == null)
			return keyFrameTranslation1.vector;
		float p = (time - keyFrameTranslation1.time) / (keyFrameTranslation2.time - keyFrameTranslation1.time);
		return keyFrameTranslation1.vector.mix(keyFrameTranslation2.vector, p);
	}

	@Override
	public String toString() {
		return "Joint [flags=" + flags + ", name=" + name + ", parentName=" + parentName + ", rotation=" + rotation + ", position=" + position
				+ ", keyFramesRotations=" + Arrays.toString(keyFrameRotations) + ", keyFrameTranslations=" + Arrays.toString(keyFrameTranslations)
				+ ", comment=" + comment + ", color=" + color + "]";
	}

}
