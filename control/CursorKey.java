package control;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;

public class CursorKey implements Key {

	public static double sensitivity = 0.25;

	private static double delta_xpos;
	private static double delta_ypos;

	private static double xpos;
	private static double ypos;

	private static double xpos_current;
	private static double ypos_current;

	private static double clamp_posX = -1;
	private static double clamp_negX = -1;
	private static double clamp_posY = -1;
	private static double clamp_negY = -1;

	private static boolean catched;

	public static final GLFWCursorPosCallback CURSOR_CALLBACK = new GLFWCursorPosCallback() {

		@Override
		public void invoke(long window, double xpos, double ypos) {
			if (catched) {
				CursorKey.xpos_current += xpos;
				CursorKey.ypos_current += ypos;
				GLFW.glfwSetCursorPos(window, 0, 0);
			} else {
				CursorKey.xpos_current = xpos;
				CursorKey.ypos_current = ypos;
			}
		}

	};

	public static void setCatched(boolean catched) {
		CursorKey.catched = catched;
		xpos_current = ypos_current = delta_xpos = delta_ypos = xpos = ypos = 0;
	}

	public static void update() {
		if (catched) {
			xpos += delta_xpos = xpos_current;
			xpos_current = 0;
			ypos += delta_ypos = ypos_current;
			ypos_current = 0;
		} else {
			delta_xpos = xpos_current - xpos;
			xpos = xpos_current;
			delta_ypos = ypos_current - ypos;
			ypos = ypos_current;
		}
	}
	public static void setClamp(double posX, double negX, double posY, double negY) {
		clamp_posX = posX;
		clamp_negX = negX;
		clamp_posY = posY;
		clamp_negY = negY;
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
