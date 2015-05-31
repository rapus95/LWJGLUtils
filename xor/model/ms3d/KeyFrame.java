package xor.model.ms3d;

import java.io.DataInput;
import java.io.IOException;

import xor.model.DataInputStreamEx;
import xor.vecmat.vec.f.Vec3;

public class KeyFrame {

	public float time;
	public Vec3 vector;
	
	public KeyFrame(DataInput dataInput) throws IOException {
		time = dataInput.readFloat();
		vector = DataInputStreamEx.readVec3(dataInput);
	}

	@Override
	public String toString() {
		return "KeyFrame [time=" + time + ", vector=" + vector
				+ "]";
	}
	
}
