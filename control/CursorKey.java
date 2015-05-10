package control;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;

public class CursorKey implements Key {

	public static double sensitivity = 0.25;

	private static double delta_xpos;
	private static double delta_ypos;

	private static double xpos;
	private static double ypos;

	private static boolean ccatch;
	
	public static final GLFWCursorPosCallback CURSOR_CALLBACK = new GLFWCursorPosCallback() {

		@Override
		public void invoke(long window, double xpos, double ypos) {
			if(!ccatch)
				return;
			CursorKey.xpos += xpos;
			CursorKey.ypos += ypos;
			GLFW.glfwSetCursorPos(window, 0, 0);
		}

	};

	public static void update() {
		delta_xpos = xpos;
		xpos = 0;
		delta_ypos = ypos;
		ypos = 0;
	}

	public static void setCatch(boolean state){
		ccatch = state;
	}
	
	private final int key;

	public CursorKey(int key) {
		this.key = key;
	}

	@Override
	public double getState() {
		switch (key) {
			case 0 :
				return delta_xpos > 0 ? delta_xpos * sensitivity : 0;
			case 1 :
				return delta_xpos < 0 ? -delta_xpos * sensitivity : 0;
			case 2 :
				return delta_ypos > 0 ? delta_ypos * sensitivity : 0;
			case 3 :
				return delta_ypos < 0 ? -delta_ypos * sensitivity : 0;
		}
		return 0;
	}

	@Override
	public String getName() {
		return "" + key;
	}

	@Override
	public Type getType() {
		return Type.CURSOR;
	}

}
