package control;

import org.lwjgl.glfw.GLFW;

public class DefaultController implements Controller {

	public final Binding forward = Binding.createAndDefault(KeyboardKey.getKey(GLFW.GLFW_KEY_W));
	public final Binding backward = Binding.createAndDefault(KeyboardKey.getKey(GLFW.GLFW_KEY_S));
	public final Binding left = Binding.createAndDefault(KeyboardKey.getKey(GLFW.GLFW_KEY_A));
	public final Binding right = Binding.createAndDefault(KeyboardKey.getKey(GLFW.GLFW_KEY_D));
	public final Binding jump = Binding.createAndDefault(KeyboardKey.getKey(GLFW.GLFW_KEY_SPACE));

	public final Binding rotateRight = Binding.createAndDefault(new CursorKey(0));
	public final Binding rotateLeft = Binding.createAndDefault(new CursorKey(1));
	public final Binding rotateUp = Binding.createAndDefault(new CursorKey(2));
	public final Binding rotateDown = Binding.createAndDefault(new CursorKey(3));
	
	@Override
	public void update() {
		forward.update();
		backward.update();
		left.update();
		right.update();
		rotateLeft.update();
		rotateRight.update();
		rotateUp.update();
		rotateDown.update();
		jump.update();
	}
	
	//...
	
}
