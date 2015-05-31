package xor.opengl.shader;

import org.lwjgl.opengl.GL11;

import xor.opengl.vbo.VBOAttribute;

public class OldNormalShaderAttribute extends ShaderAttribute {

	public OldNormalShaderAttribute(String name) {
		super(name);
	}

	@Override
	public void setup(VBOAttribute attr, int count, int start) {
		if (attr.getCount() != 3)
			throw new IllegalArgumentException();
		GL11.glEnableClientState(GL11.GL_NORMAL_ARRAY);
		GL11.glNormalPointer(attr.getType().asOGL(), count, start);
	}

	@Override
	public void unsetup() {
		GL11.glDisableClientState(GL11.GL_NORMAL_ARRAY);
	}

}
