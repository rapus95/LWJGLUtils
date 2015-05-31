package xor.model.ms3d;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;

import xor.model.IModelLoader;
import xor.model.Model;
import xor.model.Model.Material;
import xor.model.ModelManager.MagicNumber;
import xor.opengl.texture.Texture;
import xor.opengl.vbo.VBOAccess;
import xor.opengl.vbo.VBOAttribute;
import xor.opengl.vbo.VBOBuilder;
import xor.vecmat.vec.f.Vec2;
import xor.vecmat.vec.f.Vec3;

public class MS3DLoader implements IModelLoader {

	private static final MagicNumber MAGIC_NUMBER = new MagicNumber("MS3D000000");

	private static final List<String> EXTENSIONS = Collections.unmodifiableList(Arrays.asList("ms3d"));

	@Override
	public MagicNumber getMagicNumber() {
		return MAGIC_NUMBER;
	}

	@Override
	public Model load(InputStream nis) throws IOException {
		MS3DModel m = new MS3DModel(nis);
		MS3DMaterial[] ms = m.getMaterials();
		boolean without = false;
		for (Group g : m.getGroups()) {
			if (g.getMaterialIndex() == -1) {
				without = true;
				break;
			}
		}
		Material[] materials = new Material[ms.length + (without ? 1 : 0)];
		for (int i = 0; i < ms.length; i++) {
			MS3DMaterial ma = ms[i];
			BufferedImage bi = ImageIO.read(new File(ma.getTexture()));
			BufferedImage mask = ImageIO.read(new File(ma.getAlphamap()));
			Texture t = Texture.create(mergeAlphaMask(bi, mask));
			VBOAccess vboAccess = new VBOBuilder().add(VBOAttribute.POS3F).add(VBOAttribute.NORMAL3F).add(VBOAttribute.TEXCOORD2F).build();
			for (Group g : m.getGroups()) {
				if (g.getMaterialIndex() == i) {
					build(m, g.getTriangleIndices(), vboAccess);
				}
			}
			materials[i] = new Material(t, vboAccess.upload());
		}
		if (without) {
			BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
			image.setRGB(0, 0, 0xffffff);
			Texture t = Texture.create(image);
			VBOAccess vboAccess = new VBOBuilder().add(VBOAttribute.POS3F).add(VBOAttribute.NORMAL3F).add(VBOAttribute.TEXCOORD2F).build();
			for (Group g : m.getGroups()) {
				if (g.getMaterialIndex() == -1) {
					build(m, g.getTriangleIndices(), vboAccess);
				}
			}
			materials[ms.length] = new Material(t, vboAccess.upload());
		}
		Model model = new Model(materials);
		return model;
	}

	private void build(MS3DModel m, int[] triangles, VBOAccess vboAccess) {
		for (int tri : triangles) {
			Triangle t = m.getTrianlge(tri);
			for (int i = 0; i < 3; i++) {
				Vec3 pos = m.getVertex(t.getVertexIndex(i)).getVertex();
				vboAccess.set3f(VBOAttribute.POS3F, pos.x(), pos.y(), pos.z());
				Vec3 normal = t.getVertexNormal(i);
				vboAccess.set3f(VBOAttribute.NORMAL3F, normal.x(), normal.y(), normal.z());
				Vec2 texcoord = t.getTextureCoord(i);
				vboAccess.set2f(VBOAttribute.TEXCOORD2F, texcoord.x(), texcoord.y());
				vboAccess.emit();
			}
		}
	}

	public BufferedImage mergeAlphaMask(BufferedImage image, BufferedImage mask) {
		if (mask == null) {
			if (image == null) {
				image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
				image.setRGB(0, 0, 0xffffff);
			}
			return image;
		} else if (image == null) {
			int width = mask.getWidth();
			int height = mask.getHeight();
			int[] imagePixels = mask.getRGB(0, 0, width, height, null, 0, width);
			for (int i = 0; i < imagePixels.length; i++) {
				int alpha = imagePixels[i] << 24;
				imagePixels[i] = alpha | 0xffffff;
			}
			mask.setRGB(0, 0, width, height, imagePixels, 0, width);
			return mask;
		}
		int width = image.getWidth();
		int height = image.getHeight();

		int[] imagePixels = image.getRGB(0, 0, width, height, null, 0, width);
		int[] maskPixels = mask.getRGB(0, 0, width, height, null, 0, width);

		for (int i = 0; i < imagePixels.length; i++) {
			int color = imagePixels[i] & 0x00ffffff;
			int alpha = maskPixels[i] << 24;
			imagePixels[i] = color | alpha;
		}

		image.setRGB(0, 0, width, height, imagePixels, 0, width);
		return image;
	}

	@Override
	public Collection<String> getExts() {
		return EXTENSIONS;
	}

}
