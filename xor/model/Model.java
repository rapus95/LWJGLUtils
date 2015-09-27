package xor.model;

import java.util.Collection;

import xor.opengl.shader.ShaderAttribute;
import xor.opengl.texture.Texture;
import xor.opengl.vbo.VBO;

public class Model {

	public Material[] materials;

	public Model(Material[] materials) {
		this.materials = materials;
	}

	public void render(Collection<ShaderAttribute> sAttributes) {
		for (Material material : materials) {
			material.render(sAttributes);
		}
	}

	public ModelTime startAnimation(String name){
		return null;
	}
	
	public static class ModelTime{
		
	}
	
	public static class Material {

		public Texture texture;
		private VBO vbo;

		public Material(Texture texture, VBO vbo) {
			this.texture = texture;
			this.vbo = vbo;
		}

		public void render(Collection<ShaderAttribute> sAttributes) {
			texture.bind(0);
			vbo.draw(sAttributes);
			Texture.unbind(0);
		}

	}

}
