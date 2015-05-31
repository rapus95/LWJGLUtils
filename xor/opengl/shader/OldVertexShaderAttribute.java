package xor.opengl.shader;

import org.lwjgl.opengl.GL11;

import xor.opengl.vbo.VBOAttribute;

public class OldVertexShaderAttribute extends ShaderAttribute {

	public OldVertexShaderAttribute(String name) {
		super(name);
	}

	@Override
	public void setup(VBOAttribute attr, int count, int start) {
		GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
		GL11.glVertexPointer(attr.getCount(), attr.getType().asOGL(), count, start);
	}

	@Override
	public void unsetup() {
		GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
	}

}
