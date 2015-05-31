package xor.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import xor.model.ModelManager.MagicNumber;

public interface IModelLoader {

	MagicNumber getMagicNumber();

	Model load(InputStream nis) throws IOException;

	Collection<String> getExts();

}
