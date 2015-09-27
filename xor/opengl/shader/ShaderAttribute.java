package xor.opengl.shader;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import xor.opengl.vbo.VBOAttribute;

public abstract class ShaderAttribute {

	public static final String VERTEX_NAME = "position";
	public static final String COLOR_NAME = "color";
	public static final String NORMAL_NAME = "normal";
	public static final String TEXCOORD_NAME = "texcoord";
	
	public static final ShaderAttribute OLD_POSITION = new OldVertexShaderAttribute(VERTEX_NAME);
	public static final ShaderAttribute OLD_COLOR = new OldColorShaderAttribute(COLOR_NAME);
	public static final ShaderAttribute OLD_NORMAL = new OldNormalShaderAttribute(NORMAL_NAME);
	public static final ShaderAttribute OLD_TEXCOORD = new OldTexCoordShaderAttribute(TEXCOORD_NAME);
	
	public static final List<ShaderAttribute> OLD_ATTRS = Collections.unmodifiableList(Arrays.asList(OLD_POSITION, OLD_COLOR, OLD_NORMAL, OLD_TEXCOORD));
	
	private String name;

	public ShaderAttribute(String name) {
		this.name = name;
	}

	public abstract void setup(VBOAttribute attr, int count, int start);

	public abstract void unsetup();

	public String getName() {
		return name;
	}

}
