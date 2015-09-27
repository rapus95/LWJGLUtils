package xor.opengl;

import org.lwjgl.glfw.GLFW;

public final class GL {

	private GL(){}
	
	public static void init(){
		GLFW.glfwInit();
	}
	
	public static void terminate(){
		GLFW.glfwTerminate();
	}
	
	public static void update(){
		GLFW.glfwPollEvents();
	}
	
}
