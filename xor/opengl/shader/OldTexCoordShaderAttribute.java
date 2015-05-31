package xor.opengl.shader;

import org.lwjgl.opengl.GL11;

import xor.opengl.vbo.VBOAttribute;

public class OldTexCoordShaderAttribute extends ShaderAttribute {

	public OldTexCoordShaderAttribute(String name) {
		super(name);
	}

	@Override
	public void setup(VBOAttribute attr, int count, int start) {
		GL11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
		GL11.glTexCoordPointer(attr.getCount(), attr.getType().asOGL(), count, start);
	}

	@Override
	public void unsetup() {
		GL11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
	}

}
