package xor.opengl.texture;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import xor.opengl.OpenGLObject;

public class Texture extends OpenGLObject {

	protected int type;

	protected int width;
	
	protected int height;
	
	public Texture(int type, int width, int height) {
		this.type = type;
		this.width = width;
		this.height = height;
		id = GL11.glGenTextures();
		bind(0);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, type, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (ByteBuffer) null);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		unbind(0);
	}

	public void upload(BufferedImage image) {
		int w = image.getWidth(null);
		int h = image.getHeight(null);
		ByteBuffer bb = ByteBuffer.allocateDirect(w * h * 4).order(ByteOrder.nativeOrder());
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				int c = image.getRGB(x, y);
				bb.put((byte) ((c>>16)&0xFF));
				bb.put((byte) ((c>>8)&0xFF));
				bb.put((byte) ((c)&0xFF));
				bb.put((byte) ((c>>24)&0xFF));
			}
		}
		upload(w, h, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, bb);
	}

	public void upload(int width, int height, int ctype, int btype, ByteBuffer bb) {
		bind(0);
		bb.position(0);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, type, width, height, 0, ctype, btype, bb);
		unbind(0);
	}

	@Override
	protected boolean pdispose() {
		GL11.glDeleteTextures(id);
		return true;
	}

	public void bind(int unit) {
		GL13.glActiveTexture(GL13.GL_TEXTURE0 + unit);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
	}

	public static void unbind(int unit) {
		GL13.glActiveTexture(GL13.GL_TEXTURE0 + unit);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
	}

	public static Texture create(File file) throws IOException {
		return create(ImageIO.read(file));
	}

	public static Texture create(BufferedImage image) {
		Texture t = new Texture(GL11.GL_RGBA, image.getWidth(null), image.getHeight(null));
		t.upload(image);
		return t;
	}

}
