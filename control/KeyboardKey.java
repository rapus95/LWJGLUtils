package control;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

public class KeyboardKey implements Key {

	private static final Map<Integer, WeakReference<KeyboardKey>> KEYS = new HashMap<Integer, WeakReference<KeyboardKey>>();
	
	public static final GLFWKeyCallback KEY_CALLBACK = new GLFWKeyCallback(){

		@Override
		public void invoke(long window, int key, int scancode, int action, int mods) {
			WeakReference<KeyboardKey> wr = KEYS.get(key);
			if(wr!=null){
				KeyboardKey kk = wr.get();
				if(kk==null){
					KEYS.remove(key);
				}else{
					kk.state = (action == GLFW.GLFW_PRESS || action == GLFW.GLFW_REPEAT);
				}
			}
		}
		
	};
	
	public static KeyboardKey getKey(int key){
		WeakReference<KeyboardKey> wr = KEYS.get(key);
		KeyboardKey kk;
		if(wr==null || (kk=wr.get())==null){
			kk = new KeyboardKey(key);
			KEYS.put(key, new WeakReference<>(kk));
		}
		return kk;
	}
	
	private final int key;
	private boolean state;
	
	private KeyboardKey(int key){
		this.key = key;
	}
	
	@Override
	public double getState() {
		return state?1:0;
	}

	@Override
	public String getName() {
		return ""+key;
	}

	@Override
	public Type getType() {
		return Type.KEYBOARD;
	}
	
}
