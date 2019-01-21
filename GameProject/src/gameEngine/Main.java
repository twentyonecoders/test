package gameEngine;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Image;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.Renderer;
import shaders.StaticShader;
import toolBox.MousePicker;

public class Main {

	static Entity entity;
	static Entity goldMine;
	static Entity kaserne;
	
	public static void main(String[] args) {
		DisplayManager.createDisplay();
		
		Image image = new Image();
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
		Camera camera = new Camera();
		MousePicker picker = new MousePicker(renderer.getProjectionMatrix(), camera);
		
		setUp(shader, renderer, image);
		
		while(!Display.isCloseRequested()) {
			camera.move();
			renderer.prepare();
			shader.start();
			shader.loadViewMatrix(camera);
			renderGraphics(shader, renderer);
			shader.stop();
			picker.update();
			kaserne.setPosition(new Vector3f(picker.getCurrentRay().x * 11.5f, picker.getCurrentRay().y * 11.5f, -10f));
			//System.out.println("Mouse Coords: " + picker.getCurrentRay());
			DisplayManager.updateDisplay();
		}
		shader.cleanUp();
		loader.cleanUp();
		
		DisplayManager.exitDisplay();
		System.exit(0);
	}
	
	public static void setUp(StaticShader shader, Renderer renderer, Image image) {
		entity = image.createImage("Download", new Vector3f(0, 0, -10), 0, 0, 0, 1);
		goldMine = image.createImage("Goldmine", new Vector3f(3, 0, -10), 0, 0, 0, 1);
		kaserne = image.createImage("Kaserne", new Vector3f(-3, 0, -10), 0, 0, 0, 1);
	}
	
	public static void renderGraphics(StaticShader shader, Renderer renderer) {
		renderer.render(entity, shader);
		renderer.render(goldMine, shader);
		renderer.render(kaserne, shader);
	}
}
