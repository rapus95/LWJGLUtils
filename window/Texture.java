package window;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map.Entry;

import org.lwjgl.opengl.GL11;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;

public class Texture {
	
	private static HashMap<Path, Texture> map = new HashMap<Path, Texture>();
	
	public final int textureHandle;
	
	public static Texture create(Path path){
		System.out.println("NOW");
		Format f = PNGDecoder.Format.RGBA;
		try(InputStream s = Files.newInputStream(path)){
			for(Entry<Path, Texture> e:map.entrySet()){
				if(Files.isSameFile(path, e.getKey())){
					return e.getValue();
				}
			}
			PNGDecoder decoder = new PNGDecoder(s);
			// assuming RGB here but should allow for RGB and RGBA (changing
			// wall.png to RGBA will crash this!)
			ByteBuffer buf = ByteBuffer.allocateDirect(f.getNumComponents() * decoder.getWidth() * decoder.getHeight());
			decoder.decode(buf, decoder.getWidth() * f.getNumComponents(), f);
			buf.flip();
			int texture;
			texture = GL11.glGenTextures();
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB, decoder.getWidth(), decoder.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buf);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
			Texture t = new Texture(texture);
			map.put(path, t);
			return t;
		}catch(IOException e){
		}
		return null;
	}

	public Texture(int handle) {
		this.textureHandle = handle;
	}
	
	public void bind(){
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureHandle);
	}
}
