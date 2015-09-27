package xor.buffer;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.IntBuffer;

public class BufferUtils {

	public static RecycleBuffer allocate(int size, boolean direct){
		return RecycleBuffer.allocate(size, direct);
	}
	
	public static RecycleBuffer allocateDirect(int size){
		return RecycleBuffer.allocateDirect(size);
	}
	
	public static ByteBuffer atPosition(ByteBuffer buffer, int position){
		buffer = buffer.duplicate();
		buffer.position(position);
		return buffer;
	}
	
	public static IntBuffer atPosition(IntBuffer buffer, int position){
		buffer = buffer.duplicate();
		buffer.position(position);
		return buffer;
	}
	
	public static DoubleBuffer atPosition(DoubleBuffer buffer, int position){
		buffer = buffer.duplicate();
		buffer.position(position);
		return buffer;
	}
	
}
