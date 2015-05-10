package window;

import java.nio.DoubleBuffer;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

public class WindowManager {

	Window[] windows;

	public WindowManager(int count) {
		this.windows = new Window[count];
	}

	public void setPosition(int index, int x, int y) {
		GLFW.glfwSetWindowPos(getWindowID(index), x, y);
	}

	public void dispose() {
		for (Window window : windows) {
			if (window == null)
				continue;
			window.dispose();
		}
	}

	public Window getWindow(int i) {
		return windows[i];
	}

	public int getWindowCount() {
		return windows.length;
	}

	public long getWindowID(int index) {
		return windows[index].getWindowID();
	}

	public boolean shallCloseWindow(int index){
		return windows[index].shouldClose();
	}

	public WindowManager activateWindow(int index) {
		windows[index].activate();
		return this;
	}

	public WindowManager clearWindow(int index) {
		windows[index].clear();
		return this;
	}

	public WindowManager drawWindow(int index) {
		windows[index].draw();
		return this;
	}
	
	public void setupWindow(int index, DoubleBuffer projectionMatrix) {
		if (index < 0)
			for (int i = 0; i < getWindowCount(); i++) {
				_setupWindow(i, projectionMatrix);
			}
		else {
			_setupWindow(index, projectionMatrix);
		}
	}

	private void _setupWindow(int index, DoubleBuffer projectionMatrix) {
		activateWindow(index);
		GL11.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glMultMatrixd(projectionMatrix);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		GL11.glEnable(GL11.GL_DEPTH_TEST);
	}

	public void createWindow(int i, int width, int height, String title) {
		if (windows[i] != null)
			System.out.println("Warning: reassigning window");
		windows[i] = new Window(width, height, title);

	}
}
