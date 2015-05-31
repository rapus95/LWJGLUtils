package xor.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import xor.model.ms3d.MS3DLoader;

public class ModelManager {

	private List<IModelLoader> loaders = new LinkedList<IModelLoader>();
	private Map<String, IModelLoader> loaderByExt = new HashMap<String, IModelLoader>();
	private int MAX_MAGIC_NUMBER_LENGTH = 0;

	public ModelManager() {
		add(new MS3DLoader());
	}

	public void add(IModelLoader loader) {
		if (loaders.contains(loader))
			return;
		loaders.add(loader);
		for (String ext : loader.getExts()) {
			add(loader, ext);
		}
	}

	public void add(IModelLoader loader, String ext) {
		loaderByExt.put(ext, loader);
	}

	public Model load(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		try {
			return load(fis, getExt(file));
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
			}
		}
	}

	public String getExt(String s) {
		return s.substring(s.lastIndexOf('.') + 1);
	}

	public String getExt(File file) {
		return getExt(file.getName());
	}

	public Model load(InputStream is) throws IOException {
		byte[] b = new byte[MAX_MAGIC_NUMBER_LENGTH];
		int l = is.read(b);
		for (IModelLoader loader : loaders) {
			if (loader.getMagicNumber().is(b, 0, l)) {
				InputStream nis = new SneakPrevInputStream(b, 0, l, is);
				return loader.load(nis);
			}
		}
		return null;
	}

	public Model load(InputStream is, String ext) throws IOException {
		IModelLoader modelLoader = loaderByExt.get(ext.toLowerCase());
		if (modelLoader == null) {
			return load(is);
		} else {
			MagicNumber magicNumber = modelLoader.getMagicNumber();
			byte[] b = new byte[magicNumber.getLength()];
			int l = is.read(b);
			InputStream nis = new SneakPrevInputStream(b, 0, l, is);
			if (magicNumber.is(b, 0, l)) {
				return modelLoader.load(nis);
			} else {
				return load(nis);
			}
		}
	}

	public static class MagicNumber {

		private byte[] number;

		public MagicNumber(byte[] number) {
			this.number = number;
		}

		public MagicNumber(String number) {
			this.number = number.getBytes();
		}

		public boolean is(byte[] b, int off, int len) {
			if (number.length > len) {
				return false;
			}
			for (int i = 0; i < number.length; i++) {
				if (number[i] != b[off + i])
					return false;
			}
			return true;
		}

		public int getLength() {
			return number.length;
		}

	}

	private static class SneakPrevInputStream extends InputStream {

		private byte[] a;
		private int off;
		private int len;
		private int pos;
		private InputStream is;

		public SneakPrevInputStream(byte[] a, int off, int len, InputStream is) {
			this.a = a;
			this.off = off;
			this.len = len;
			this.is = is;
		}

		@Override
		public int read() throws IOException {
			if (a == null) {
				return is.read();
			}
			if (pos < len)
				return a[off + pos++];
			a = null;
			return is.read();
		}

		@Override
		public int read(byte[] b, int off, int len) throws IOException {
			if (a == null) {
				return is.read(b, off, len);
			}
			int l = this.len - pos;
			if (l > len) {
				System.arraycopy(a, pos + off, b, off, len);
				pos += len;
				return len;
			}
			System.arraycopy(a, pos + off, b, off, l);
			a = null;
			return is.read(b, off + l, len - l) + l;
		}

	}

}
