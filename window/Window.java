package window;

import java.nio.IntBuffer;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

import xor.buffer.BufferUtils;
import xor.buffer.RecycleBuffer;
import xor.utils.Disposeable;
import control.CursorKey;
import control.KeyboardKey;
import control.MouseKey;

public class Window extends Disposeable {

	private static Window current;

	long window;
	
	int unsecure_width;
	int unsecure_height;
	
	int posX;
	int posY;
	
	boolean fullscreen;

	int gridWidth = 1;
	int gridHeight = 1;

	public Window(int width, int height, long fullscreen, String title) {
		this(width, height, fullscreen, title, true);
	}
	
	public Window(int width, int height, long fullscreen, String title, boolean grabCursor) {
		this.unsecure_width = width;
		this.unsecure_height = height;
		GLFW.glfwDefaultWindowHints();
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GL11.GL_FALSE);
		window = GLFW.glfwCreateWindow(width, height, title, fullscreen, 0);
		this.fullscreen = fullscreen!=0;
		if (window == 0) {
			throw new RuntimeException("Failed to create the GLFW window");
		}
		activate();
		GLContext.createFromCurrent();
		if(grabCursor)
			GLFW.glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);
		GLFW.glfwSetKeyCallback(window, KeyboardKey.KEY_CALLBACK);
		GLFW.glfwSetCursorPosCallback(window, CursorKey.CURSOR_CALLBACK);
		GLFW.glfwSetMouseButtonCallback(window, MouseKey.KEY_CALLBACK);
		GLFW.glfwShowWindow(window);
		if(grabCursor)
			GLFW.glfwSetCursorPos(window, 0, 0);
	}

	public boolean shouldClose() {
		return GLFW.glfwWindowShouldClose(window) != 0;
	}

	//private static final IntBuffer xpos = ByteBuffer.allocateDirect(4).order(ByteOrder.nativeOrder()).asIntBuffer();
	//private static final IntBuffer ypos = ByteBuffer.allocateDirect(4).order(ByteOrder.nativeOrder()).asIntBuffer();

	public void getSize(int[] buff) {
		if (buff.length != 2)
			throw new IllegalArgumentException();
		RecycleBuffer rb = RecycleBuffer.allocateDirect(8);
		IntBuffer pos = rb.asByteBuffer().asIntBuffer();
		GLFW.glfwGetWindowSize(window, pos, BufferUtils.atPosition(pos, 1));
		unsecure_width = buff[0] = pos.get(0);
		unsecure_height = buff[1] = pos.get(1);
		rb.recycle();
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
	
	public void setPosition(int x, int y){
		if(fullscreen)
			return;
		posX = x;
		posY = y;
		GLFW.glfwSetWindowPos(window, x, y);
	}
	
	public void setSize(int width, int height){
		unsecure_width = width;
		unsecure_height = height;
		GLFW.glfwSetWindowSize(window, width, height);
	}
	
	public void setFullscreen(boolean enable){
		if(fullscreen == enable)
			return;
		if(enable){
//			setSize(GLFWvidmode.width(vidmode), GLFWvidmode.height(vidmode));
		}
		fullscreen = enable;
	}

	public boolean isValid() {
		return window != 0;
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

	@Override
	protected boolean pdispose() {
		if (window != 0) {
			GLFW.glfwDestroyWindow(window);
			window = 0;
		}
		return false;
	}

}