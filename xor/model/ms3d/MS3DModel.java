package xor.model.ms3d;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;

import xor.utils.DataInputStreamEx;

public class MS3DModel {

	public static final int MAX_VERTICES = 65534;
	public static final int MAX_TRIANGLES = 65534;
	public static final int MAX_GROUPS = 255;
	public static final int MAX_MATERIALS = 128;
	public static final int MAX_JOINTS = 128;

	public static final int SELECTED = 1;
	public static final int HIDDEN = 2;
	public static final int SELECTED2 = 4;
	public static final int DIRTY = 8;

	public static final String MAGIC_NUMBER = "MS3D000000";

	private int version;
	private Vertex[] verteces;
	private Triangle[] triangles;
	private Group[] groups;
	private MS3DMaterial[] materials;
	private float animationFPS;
	private float currentTime;
	private int totalFrames;
	private Joint[] joints;
	private String comment;
	private float jointSize;
	private int transparencyMode;
	private float alphaRef;

	@SuppressWarnings("resource")
	public MS3DModel(InputStream inputStream) throws IOException {
		DataInputStreamEx loaderInputStream = new DataInputStreamEx(inputStream, ByteOrder.LITTLE_ENDIAN);
		String magicNumber = loaderInputStream.readString(10);
		if (!magicNumber.equals(MAGIC_NUMBER))
			throw new IOException("Wrong magic number");
		version = loaderInputStream.readInt();
		int numVertices = loaderInputStream.readUnsignedShort();
		verteces = new Vertex[numVertices];
		for (int i = 0; i < numVertices; i++) {
			verteces[i] = new Vertex(loaderInputStream);
		}
		int numTriangles = loaderInputStream.readUnsignedShort();
		triangles = new Triangle[numTriangles];
		for (int i = 0; i < numTriangles; i++) {
			triangles[i] = new Triangle(loaderInputStream);
		}
		int numGroups = loaderInputStream.readUnsignedShort();
		groups = new Group[numGroups];
		for (int i = 0; i < numGroups; i++) {
			groups[i] = new Group(loaderInputStream);
		}
		int numMaterials = loaderInputStream.readUnsignedShort();
		materials = new MS3DMaterial[numMaterials];
		for (int i = 0; i < numMaterials; i++) {
			materials[i] = new MS3DMaterial(loaderInputStream);
		}
		animationFPS = loaderInputStream.readFloat();
		currentTime = loaderInputStream.readFloat();
		totalFrames = loaderInputStream.readInt();
		int numJoints = loaderInputStream.readUnsignedShort();
		joints = new Joint[numJoints];
		for (int i = 0; i < numJoints; i++) {
			joints[i] = new Joint(loaderInputStream);
		}
		int commentsSubVersion = loaderInputStream.readInt();
		if (commentsSubVersion > 0) {
			int numGroupComments = loaderInputStream.readInt();
			for (int i = 0; i < numGroupComments; i++) {
				int index = loaderInputStream.readInt();
				groups[index].setComment(loaderInputStream.readString(loaderInputStream.readInt()));
			}
			int numMaterialComments = loaderInputStream.readInt();
			for (int i = 0; i < numMaterialComments; i++) {
				int index = loaderInputStream.readInt();
				materials[index].setComment(loaderInputStream.readString(loaderInputStream.readInt()));
			}
			int numJointComments = loaderInputStream.readInt();
			for (int i = 0; i < numJointComments; i++) {
				int index = loaderInputStream.readInt();
				joints[index].setComment(loaderInputStream.readString(loaderInputStream.readInt()));
			}
			int hasModelComment = loaderInputStream.readInt();
			if (hasModelComment == 1) {
				comment = loaderInputStream.readString(loaderInputStream.readInt());
			}
		}
		int vertexSubVersion = loaderInputStream.readInt();
		if (vertexSubVersion > 0) {
			for (int i = 0; i < numVertices; i++) {
				verteces[i].patch(vertexSubVersion, loaderInputStream);
			}
		}
		int jointSubVersion = loaderInputStream.readInt();
		if (jointSubVersion > 0) {
			for (int i = 0; i < numJoints; i++) {
				joints[i].patch(jointSubVersion, loaderInputStream);
			}
		}
		int modelSubVersion = loaderInputStream.readInt();
		if (modelSubVersion > 0) {
			jointSize = loaderInputStream.readFloat();
			transparencyMode = loaderInputStream.readInt();
			alphaRef = loaderInputStream.readFloat();
		}
		for (int i = 0; i < numJoints; i++) {
			joints[i].resolve(this);
		}
	}

	public Group getGroup(int index) {
		return groups[index];
	}

	public Group getGroup(String name) {
		for (int i = 0; i < groups.length; i++) {
			if (name.equals(groups[i].getName())) {
				return groups[i];
			}
		}
		return null;
	}

	public Vertex getVertex(int index) {
		return verteces[index];
	}

	public Triangle getTrianlge(int index) {
		return triangles[index];
	}

	public MS3DMaterial getMaterial(int index) {
		return materials[index];
	}

	public MS3DMaterial getMaterial(String name) {
		for (int i = 0; i < materials.length; i++) {
			if (name.equals(materials[i].getName())) {
				return materials[i];
			}
		}
		return null;
	}

	public Joint getJoint(int index) {
		return joints[index];
	}

	public Joint getJoint(String name) {
		for (int i = 0; i < joints.length; i++) {
			if (name.equals(joints[i].getName())) {
				return joints[i];
			}
		}
		return null;
	}

	public int getJointIndex(String name) {
		for (int i = 0; i < joints.length; i++) {
			if (name.equals(joints[i].getName())) {
				return i;
			}
		}
		return -1;
	}

	public Vertex[] getVerteces() {
		return verteces;
	}

	public Triangle[] getTriangles() {
		return triangles;
	}

	public Group[] getGroups() {
		return groups;
	}

	public MS3DMaterial[] getMaterials() {
		return materials;
	}

	public float getAnimationFPS() {
		return animationFPS;
	}

	public float getCurrentTime() {
		return currentTime;
	}

	public int getTotalFrames() {
		return totalFrames;
	}

	public Joint[] getJoints() {
		return joints;
	}

	public String getComment() {
		return comment;
	}

	public float getJointSize() {
		return jointSize;
	}

	public int getTransparencyMode() {
		return transparencyMode;
	}

	public float getAlphaRef() {
		return alphaRef;
	}

	@Override
	public String toString() {
		String s = "Model\n";
		s += "verteces:" + verteces.length + "\n";
		for (int i = 0; i < verteces.length; i++) {
			s += i + ":" + verteces[i] + "\n";
		}
		s += "triangles:" + triangles.length + "\n";
		for (int i = 0; i < triangles.length; i++) {
			s += i + ":" + triangles[i] + "\n";
		}
		s += "groups:" + groups.length + "\n";
		for (int i = 0; i < groups.length; i++) {
			s += i + ":" + groups[i] + "\n";
		}
		s += "materials:" + materials.length + "\n";
		for (int i = 0; i < materials.length; i++) {
			s += i + ":" + materials[i] + "\n";
		}
		s += "animationFPS:" + animationFPS + "\n";
		s += "currentTime:" + currentTime + "\n";
		s += "totalFrames:" + totalFrames + "\n";
		s += "joints:" + joints.length + "\n";
		for (int i = 0; i < joints.length; i++) {
			s += i + ":" + joints[i] + "\n";
		}
		s += "comment:" + comment + "\n";
		s += "jointSize:" + jointSize + "\n";
		s += "transparencyMode:" + transparencyMode + "\n";
		s += "alphaRef:" + alphaRef;
		return s;
	}

}
