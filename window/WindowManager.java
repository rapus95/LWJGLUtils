package window;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWvidmode;
import org.lwjgl.opengl.GL11;

import xor.utils.Disposeable;

public class WindowManager extends Disposeable {

	private final List<Window> windows = new ArrayList<>();
	private final int maxWindows;
	private final Map<Window, List<Viewport>> map = new HashMap<>();
	private static ByteBuffer vidmode;

	public WindowManager(int count) {
		maxWindows = count;
	}

	public Viewport createViewport(int index, int x, int y) {
		checkDisposed();
		Window w = getWindow(index);
		if (w == null || x < 0 || y < 0 || x >= w.getGridWidth() || y >= w.getGridHeight()) {
			System.out.println("Error: not a valid Viewport position");
			return null;
		}
		Viewport vp = new Viewport(this, index, x, y);
		map.get(w).add(vp);
		return vp;
	}

	public void setPosition(int index, int x, int y) {
		getWindow(index).setPosition(x, y);
	}
	
	public void setCenter(int index) {
		checkDisposed();
		if(vidmode==null)
			vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
		if(vidmode==null)
			return;
		Window w = getWindow(index);
		int x = (int) ((GLFWvidmode.width(vidmode) - w.unsecure_width) / 2);
		int y = (int) ((GLFWvidmode.height(vidmode) - w.unsecure_height) / 4);
		setPosition(index, x, y);
	}

	public void disposeWindow(int index) {
		checkDisposed();
		if (index < 0) {
			for (int i = 0; i < windows.size(); i++) {
				_disposeWindow(i);
			}
		} else {
			_disposeWindow(index);
		}

	}

	private void _disposeWindow(int index) {
		Window w = getWindow(index);
		if (w != null)
			w.dispose();
	}

	public Window getWindow(int i) {
		checkDisposed();
		return windows.get(i);
	}

	public int getWindowCount() {
		checkDisposed();
		return maxWindows;
	}

	public long getWindowID(int index) {
		return getWindow(index).getWindowID();
	}

	public boolean shallCloseWindow(int index) {
		return getWindow(index).shouldClose();
	}

	public WindowManager activateWindow(int index) {
		getWindow(index).activate();
		return this;
	}

	public WindowManager clearWindow(int index) {
		getWindow(index).clear();
		return this;
	}

	public WindowManager drawWindow(int index) {
		getWindow(index).draw();
		return this;
	}

	public void renderWindow(int index) {
		checkDisposed();
		if (index < 0) {
			for (int i = 0; i < windows.size(); i++) {
				_renderWindow(i);
			}
		} else {
			_renderWindow(index);
		}

	}

	private void _renderWindow(int index) {
		Window w = getWindow(index);
		if (w == null || !w.isValid())
			return;
		for (Viewport vp : map.get(w)) {
			vp.render();
		}
	}

	public void setupWindow(int index, DoubleBuffer projectionMatrix) {
		checkDisposed();
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
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

	public int createWindow(int width, int height, boolean fullscreen, String title) {
		checkDisposed();
		int i=0;
		while(i < windows.size() && getWindow(i) != null) i++;
		createWindow(i, width, height, fullscreen, title);
		return i;
	}

	public void createWindow(int index, int width, int height, boolean fullscreen, String title) {
		checkDisposed();
		if (index < 0 || (maxWindows > 0 && index >= maxWindows)) {
			System.out.println("Error: not a valid window ID");
			return;
		}
		while (index >= windows.size())
			windows.add(null);
		Window w = getWindow(index);
		if (w != null) {
			System.out.println("Warning: reassigning window");
			w.dispose();
		}
		w = new Window(width, height, fullscreen?GLFW.glfwGetPrimaryMonitor():0, title);
		windows.set(index, w);
		map.put(w, new ArrayList<Viewport>());

	}

	@Override
	protected boolean pdispose() {
		for(Window window:windows){
			window.dispose();
		}
		windows.clear();
		map.clear();
		return false;
	}
	
}
