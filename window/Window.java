package window;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

import control.CursorKey;
import control.KeyboardKey;

public class Window {

	private static Window current;

	long window;

	int gridWidth = 1;
	int gridHeight = 1;

	public Window(int width, int height, String title) {
		GLFW.glfwDefaultWindowHints();
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GL11.GL_FALSE);
		window = GLFW.glfwCreateWindow(width, height, title, 0, 0);
		if (window == 0) {
			throw new RuntimeException("Failed to create the GLFW window");
		}
		activate();
		GLContext.createFromCurrent();
		GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);
		GLFW.glfwSetKeyCallback(window, KeyboardKey.KEY_CALLBACK);
		GLFW.glfwSetCursorPosCallback(window, CursorKey.CURSOR_CALLBACK);
		GLFW.glfwShowWindow(window);
		GLFW.glfwSetCursorPos(window, 0, 0);
	}

	public boolean shouldClose() {
		return GLFW.glfwWindowShouldClose(window) != 0;
	}

	private static final IntBuffer xpos = ByteBuffer.allocateDirect(4).order(ByteOrder.nativeOrder()).asIntBuffer();
	private static final IntBuffer ypos = ByteBuffer.allocateDirect(4).order(ByteOrder.nativeOrder()).asIntBuffer();

	public void getSize(int[] buff) {
		if (buff.length != 2)
			throw new IllegalArgumentException();
		xpos.position(0);
		ypos.position(0);
		GLFW.glfwGetWindowSize(window, xpos, ypos);
		buff[0] = xpos.get(0);
		buff[1] = ypos.get(0);
	}

	public void draw() {
		GLFW.glfwSwapBuffers(window);
	}

	public void activate() {
		if (current != this) {
			current = this;
			GLFW.glfwMakeContextCurrent(window);
		}
	}

	public void dispose() {
		if (window != 0) {
			GLFW.glfwDestroyWindow(window);
			window = 0;
		}
	}

	public int getGridHeight() {
		return gridHeight;
	}

	public int getGridWidth() {
		return gridWidth;
	}

	public void setGridWidth(int gridWidth) {
		this.gridWidth = gridWidth;
	}

	public void setGridHeight(int gridHeight) {
		this.gridHeight = gridHeight;
	}

	public long getWindowID() {
		return window;
	}

	public void clear() {
		if (current != this)
			return;
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);		
	}

}