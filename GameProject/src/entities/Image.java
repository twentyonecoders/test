package entities;

import org.lwjgl.util.vector.Vector3f;

import models.RawModel;
import models.TexturedModel;
import renderEngine.Loader;
import textures.ModelTexture;

public class Image {

	static Loader loader = new Loader();
	
	private float[] vertices = {
		    -1f, 1f, 0f,   //v0
		    -1f, -1f, 0f,  //v1
		    1f, -1f, 0f,   //v2
		    1f, 1f, 0f     //v3
		  };
	
	private int[] indices = {
			0, 1, 3,
			3, 1, 2
	};
	
	private float[] textureCoords = {
			0, 0,
			0, 1,
			1, 1,
			1, 0
	};
	
	public Entity createImage(String fileName, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
		ModelTexture texture = new ModelTexture(loader.loadTexture(fileName));
		TexturedModel texturedModel = new TexturedModel(model, texture);
		return new Entity(texturedModel,position, rotX, rotY, rotZ, scale);
	}
}
