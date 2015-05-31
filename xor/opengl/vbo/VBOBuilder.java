package xor.opengl.vbo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class VBOBuilder {

	private List<VBOAttribute> attributes = new ArrayList<VBOAttribute>();
	private HashSet<String> names = new HashSet<String>();

	public VBOBuilder() {

	}

	public VBOBuilder add(VBOAttribute attribute) {
		if (names.contains(attribute.getName()))
			throw new IllegalArgumentException();
		names.add(attribute.getName());
		attributes.add(attribute);
		return this;
	}

	public VBOAccess build() {
		return new VBO(attributes).access(VBO.WRITE);
	}

	public static VBOAccess build(VBOAttribute... attributes) {
		VBOBuilder builder = new VBOBuilder();
		for (VBOAttribute attribute : attributes)
			builder.add(attribute);
		return builder.build();
	}

}
