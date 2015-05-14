package window;

import java.nio.DoubleBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class Viewport {
	private final WindowManager manager;
	private int index;
	private int x;
	private int y;
	private int width;
	private int height;
	private final DoubleBuffer perspective;
	private Renderer renderer;

	Viewport(WindowManager manager, int index, int x, int y) {
		this.manager = manager;
		this.index = index;
		this.x = x;
		this.y = y;
		this.perspective = BufferUtils.createDoubleBuffer(16);
		calculateSize();
	}
	
	public void setRenderer(Renderer renderer) {
		this.renderer = renderer;
	}
	
	void render(){
		if(renderer==null)
			return;
		renderer.render(this);
	}
	
	public boolean isValid() {
		return manager.getWindow(index).isValid();
	}
	
	public void calculateSize() {
		int[] size = new int[2];
		Window w = manager.getWindow(index);
		w.getSize(size);
		int gridWidth = w.getGridWidth();
		int gridHeight = w.getGridHeight();
		this.width = size[0] / gridWidth;
		this.height = size[1] / gridHeight;
	}

	public void activate() {
		manager.activateWindow(index);
		calculateSize();
		int x = width * getX();
		int y = height * getY();
		GL11.glClearColor(1, 1, 1, 1);
		GL11.glViewport(x, y, width, height);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		if (perspective != null) {
			perspective.rewind();
			GL11.glMultMatrixd(perspective);
		}
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
	}

	public WindowManager getManager() {
		return manager;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setPerspective(DoubleBuffer db) {
		this.perspective.clear();
		this.perspective.put(db);
	}

}