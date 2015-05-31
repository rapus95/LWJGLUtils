package xor.opengl.vbo;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

import xor.opengl.OpenGLObject;
import xor.opengl.shader.ShaderAttribute;

public class VBO extends OpenGLObject {

	public static final int READ = 1;
	public static final int WRITE = 2;
	public static final int MODIFY = 4;

	private int bucketSize;
	private VBOAccess access;
	private VBOAttribute[] attributes;
	private Map<String, int[]> attributeInfos = new HashMap<String, int[]>();
	private int count;

	protected VBO(List<VBOAttribute> attributes) {
		int offset = 0;
		int i = 0;
		this.attributes = new VBOAttribute[attributes.size()];
		for (VBOAttribute attribute : attributes) {
			attributeInfos.put(attribute.getName(), new int[] { i, offset });
			this.attributes[i] = attribute;
			i++;
			offset += attribute.getStorageSize();
		}
		bucketSize = offset;
	}

	public VBOAccess access(int mode) {
		if (access != null)
			throw new IllegalStateException();
		if (mode != WRITE)
			throw new UnsupportedOperationException();
		return access = new VBOAccess(this);
	}

	@Override
	protected boolean pdispose() {
		if (id != 0) {
			GL15.glDeleteBuffers(id);
			id = 0;
		}
		return true;
	}

	protected void upload(ByteBuffer bb, int count) {
		if (id == 0) {
			id = GL15.glGenBuffers();
		}
		bind();
		this.count = count;
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, bb.limit(), bb, GL15.GL_STATIC_DRAW);
		access = null;
		unbind();
	}

	int[] getAttributeInfo(VBOAttribute attr) {
		int[] info = attributeInfos.get(attr.getName());
		if (info == null)
			return null;
		return attributes[info[0]].equals(attr) ? info : null;
	}

	public int getBucketSize() {
		return bucketSize;
	}

	VBOAttribute[] getAttributes() {
		return attributes;
	}

	private void bind() {
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, id);
	}

	private static void unbind() {
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}

	public void draw(Collection<ShaderAttribute> sAttributes) {
		bind();
		for (ShaderAttribute sAttribute : sAttributes) {
			int[] info = attributeInfos.get(sAttribute.getName());
			if (info != null) {
				sAttribute.setup(attributes[info[0]], bucketSize, info[1]);
			}
		}
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, count);
		for (ShaderAttribute sAttribute : sAttributes) {
			sAttribute.unsetup();
		}
		unbind();
	}

	public void draw(ShaderAttribute... sAttributes) {
		draw(Arrays.asList(sAttributes));
	}

}
