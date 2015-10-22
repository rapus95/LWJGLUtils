package control;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.glfw.GLFW;

public class Gamepad {

	private final static Gamepad[] gamepads = new Gamepad[16];
	private final static Map<String, Gamepad> gamepads2 = new HashMap<String, Gamepad>(16);

	static {
		for (int i = 0; i < 16; i++) {
			gamepads[i] = new Gamepad(i);
		}
	}

	private static GamepadListener gamepadListener;

	public static void setGamepadListener(GamepadListener gamepadListener) {
		Gamepad.gamepadListener = gamepadListener;
	}

	public static Gamepad get(String name) {
		return gamepads2.get(name);
	}

	private final int index;
	private final float[] axis = new float[5];
	private final byte[] buffer = new byte[14];
	private String name;

	public static void update() {
		for (int i = 0; i < 16; i++) {
			gamepads[i].cupdate();
		}
	}

	private Gamepad(int index) {
		this.index = index;
	}

	private void cupdate() {
		FloatBuffer fb = GLFW.glfwGetJoystickAxes(index);
		if (fb == null || fb.capacity()==0) {
			remove();
			return;
		}
		fb.get(axis);
		ByteBuffer bb = GLFW.glfwGetJoystickButtons(index);
		if (bb == null || bb.capacity()==0) {
			remove();
			return;
		}
		bb.get(buffer);
		if (name == null) {
			name = GLFW.glfwGetJoystickName(index);
			if (name != null) {
				String test = name;
				int i = 0;
				while (gamepads2.containsKey(test)) {
					i++;
					test = name + "." + i;
				}
				gamepads2.put(test, this);
				gamepadListener.onControllerAdded(this);
			}
		}
	}

	private void remove() {
		if (name != null) {
			gamepads2.remove(name);
			gamepadListener.onControllerRemoved(this);
		}
		name = null;
	}

	public double getState(int key) {
		if (!isPresent())
			return 0;
		if (key < 10) {
			float x = axis[key / 2];
			if (key % 2 == 1) {
				x = -x;
			}
			return clamp(x, 0, 1);
		}
		return buffer[key - 10];
	}
	
	private double clamp(double current, double min, double max){
		return current < min ? min : current > max ? max : current;
	}

	public boolean isPresent() {
		return name != null;
	}

	public String getName() {
		return name;
	}

}
