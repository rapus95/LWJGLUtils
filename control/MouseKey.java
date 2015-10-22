package control;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class MouseKey implements Key {
	
	private static final Map<Integer, WeakReference<MouseKey>> KEYS = new HashMap<Integer, WeakReference<MouseKey>>();
	
	public static final GLFWMouseButtonCallback KEY_CALLBACK = new GLFWMouseButtonCallback(){

		@Override
		public void invoke(long window, int button, int action, int mods) {
			WeakReference<MouseKey> wr = KEYS.get(button);
			if(wr!=null){
				MouseKey kk = wr.get();
				if(kk==null){
					KEYS.remove(button);
				}else{
					kk.state = (action == GLFW.GLFW_PRESS || action == GLFW.GLFW_REPEAT);
				}
			}
		}
		
	};
	
	public static MouseKey getKey(int key){
		WeakReference<MouseKey> wr = KEYS.get(key);
		MouseKey kk;
		if(wr==null || (kk=wr.get())==null){
			kk = new MouseKey(key);
			KEYS.put(key, new WeakReference<MouseKey>(kk));
		}
		return kk;
	}
	
	private final int key;
	private boolean state;
	
	private MouseKey(int key){
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
