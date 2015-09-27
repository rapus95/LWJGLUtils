package xor.opengl;

import xor.utils.Disposeable;

public abstract class OpenGLObject extends Disposeable {

	protected int id;
	
	public int getID(){
		return id;
	}
	
}
