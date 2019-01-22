package gameEngine;

import java.util.Timer;
import java.util.TimerTask;

import org.lwjgl.input.Keyboard;
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

public class newTestMain {

	public static int gold = 0;
	
	static Entity entity;
	static GoldMine goldMine1;
	static GoldMine goldMine2;
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
		output();
		
		while(!Display.isCloseRequested()) {
			camera.move();
			renderer.prepare();
			shader.start();
			shader.loadViewMatrix(camera);
			renderGraphics(shader, renderer);
			shader.stop();
			picker.update();
			updateGame(picker);
			DisplayManager.updateDisplay();
		}
		shader.cleanUp();
		loader.cleanUp();
		
		DisplayManager.exitDisplay();
		System.exit(0);
	}
	
	public static void output(){
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {	
				System.out.println("Gold: " + gold);
		    }
		}, 1*1000, 1*1000);
	}
	
	public static void setUp(StaticShader shader, Renderer renderer, Image image) {
		entity = image.createImage("Download", new Vector3f(0, 0, -10), 0, 0, 0, 1);
		goldMine1 = new GoldMine(new Vector3f(3, 0, -10), 0, 0, 0, 1, 1);
		//goldMine2 = new GoldMine(new Vector3f(6, 0, -10), 0, 0, 0, 1);
		kaserne = image.createImage("Kaserne", new Vector3f(-3, 0, -10), 0, 0, 0, 1);
	}
	
	public static void renderGraphics(StaticShader shader, Renderer renderer) {
		//renderer.render(entity, shader);
		renderer.render(goldMine1.getImage(), shader);
		//renderer.render(goldMine2.getImage(), shader);
		//renderer.render(kaserne, shader);
	}
	
	public static void updateGame(MousePicker mousePicker) {
		if(mousePicker.isLeftButtonDown() == false) {
			if(goldMine1.isLocationSet() == false)
			goldMine1.getImage().setPosition(new Vector3f(mousePicker.getCurrentRay().x * 11.5f, mousePicker.getCurrentRay().y * 11.5f, -10f));
		}
		if(mousePicker.isLeftButtonDown() == true) {
			System.out.println(mousePicker.getCurrentRay());
			if(goldMine1.isLocationSet() == false)
			goldMine1.getImage().setPosition(new Vector3f(mousePicker.getCurrentRay().x * 11.5f, mousePicker.getCurrentRay().y * 11.5f, -10f));
			goldMine1.setLocationSet(true);
		}
		
		while (Keyboard.next()) {
	        if (Keyboard.getEventKeyState()) {
	            if(Keyboard.getEventKey() == Keyboard.KEY_1) {
	            	goldMine1.takeGold();
	            }
	            if(Keyboard.getEventKey() == Keyboard.KEY_U) {
	    			goldMine1.upgrade();
	            }
	            if(Keyboard.getEventKey() == Keyboard.KEY_G) {
	            	goldMine1.setLocationSet(false);
	            }
	        } else {
	            if(Keyboard.getEventKey() == Keyboard.KEY_1) {
	            	System.out.println("1 was released");
	            }
	        }
		}
	}
	
}
testzeile
testzeile
 2