package xor.opengl;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL11;

import xor.opengl.texture.Texture;

public class Bitmap extends Texture {

	private int[][] charDim;

	private Map<Character, int[]> special;

	private Bitmap(int type, int width, int height) {
		super(type, width, height);
	}

	public static Bitmap from(Font font) {
		return from(font, null);
	}

	public static Bitmap from(Font font, String specials) {
		int textureSize = 256;
		int lastTextureSize = 0;
		BufferedImage imgTemp = new BufferedImage(textureSize, textureSize, BufferedImage.TYPE_INT_ARGB);
		Graphics g = imgTemp.getGraphics();
		g.setColor(new Color(0, 0, 0, 0));
		g.fillRect(0, 0, textureSize, textureSize);
		int rowHeight = 0;
		int positionX = 0;
		int positionY = 0;
		int fontHeight = 0;

		int[][] ca = new int[128 - 32][];

		int sp = specials == null ? 0 : specials.length();

		Map<Character, int[]> special = sp == 0 ? null : new HashMap<Character, int[]>();

		for (int i = 32; i < 128 + sp; i++) {

			char ch = i < 128 ? (char) i : specials.charAt(i - 128);
			if (i>=128 && ch >= 32 && ch < 128)
				continue;
			BufferedImage fontImage = getFontImage(ch, font);

			int[] cd = new int[4];

			cd[2] = fontImage.getWidth();
			cd[3] = fontImage.getHeight();

			if (positionX + cd[2] >= textureSize) {
				positionY += rowHeight;
				rowHeight = 0;
				if (positionY < lastTextureSize)
					positionX = lastTextureSize;
				else
					positionX = 0;
			}

			if (cd[3] > fontHeight)
				fontHeight = cd[3];

			if (cd[3] > rowHeight)
				rowHeight = cd[3];

			if (positionY + rowHeight >= textureSize) {
				lastTextureSize = textureSize;
				textureSize *= 2;
				positionY = 0;
				positionX = lastTextureSize;
				BufferedImage newImg = new BufferedImage(textureSize, textureSize, BufferedImage.TYPE_INT_ARGB);
				Graphics gn = newImg.getGraphics();
				gn.setColor(new Color(0, 0, 0, 0));
				gn.fillRect(0, 0, textureSize, textureSize);
				gn.drawImage(imgTemp, 0, 0, null);
				g = gn;
				imgTemp = newImg;
			}

			cd[0] = positionX;
			cd[1] = positionY;

			// Draw it here
			g.drawImage(fontImage, positionX, positionY, null);

			positionX += cd[2];

			fontImage = null;
			if (i < 128) {
				ca[i - 32] = cd;
			} else {
				special.put(ch, cd);
			}
		}

		Bitmap bm = new Bitmap(GL11.GL_RGBA, textureSize, textureSize);
		bm.charDim = ca;
		bm.special = special == null ? null : special.isEmpty() ? null : special;
		bm.upload(imgTemp);
		return bm;
	}

	private static BufferedImage getFontImage(char ch, Font font) {
		BufferedImage tempfontImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		Graphics g = tempfontImage.getGraphics();
		if (g instanceof Graphics2D)
			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setFont(font);
		FontMetrics fontMetrics = g.getFontMetrics();
		Rectangle2D r = fontMetrics.getStringBounds(ch == '\t' ? "    " : "" + ch, g);

		int charwidth = (int) r.getWidth();
		int charheight = (int) r.getHeight();
		if (charwidth <= 0)
			charwidth = 1;
		// Create another image holding the character we are creating
		BufferedImage fontImage;
		fontImage = new BufferedImage(charwidth, charheight, BufferedImage.TYPE_INT_ARGB);
		Graphics gt = fontImage.getGraphics();
		if (gt instanceof Graphics2D)
			((Graphics2D) gt).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		gt.setColor(new Color(0, 0, 0, 1));
		gt.fillRect(0, 0, charwidth, charheight);
		gt.setFont(font);
		gt.setColor(Color.WHITE);
		int charx = 0;
		int chary = 0;
		gt.drawString(String.valueOf(ch), (charx), (chary) + fontMetrics.getAscent());

		return fontImage;

	}

	public void render(float x, float y, float z, String text) {
		bind(0);
		int h = 0;
		GL11.glBegin(GL11.GL_QUADS);
		float xx = x;
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);

			int[] dim = null;
			if (c >= 32 && charDim.length > c - 32) {
				dim = charDim[c - 32];
			} else if (special != null) {
				dim = special.get(c);
			}
			if (dim != null) {
				if (dim[3] > h) {
					h = dim[3];
				}
				GL11.glTexCoord2f(((float) dim[0]) / width, ((float) dim[1]) / height);
				GL11.glVertex3f(xx, y, z);
				GL11.glTexCoord2f(((float) dim[0]) / width, ((float) (dim[1] + dim[3])) / height);
				GL11.glVertex3f(xx, y - dim[3], z);
				GL11.glTexCoord2f(((float) (dim[0] + dim[2])) / width, ((float) (dim[1] + dim[3])) / height);
				GL11.glVertex3f(xx + dim[2], y - dim[3], z);
				GL11.glTexCoord2f(((float) (dim[0] + dim[2])) / width, ((float) dim[1]) / height);
				GL11.glVertex3f(xx + dim[2], y, z);
				xx += dim[2];
			}
			if (c == '\n') {
				y -= h;
				xx = x;
				h = 0;
			}
		}
		GL11.glEnd();
	}

}
