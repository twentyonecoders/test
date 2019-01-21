package gameEngine;

import java.util.*;
//import java.util.TimerTask;

import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import entities.Image;

public class GoldMine {
	
	private int gold = 0;
	private int maxGold = 5;
	private int prodRate = 1;
	
	int ID;
	
	public Entity entity;
	boolean locationSet = false;
	
	public GoldMine(Vector3f position, float rotX, float rotY, float rotZ, float scale, int id) {
		generateGold();
		Image image = new Image();
		this.entity = image.createImage("Goldmine", position, rotX, rotY, rotZ, scale);
		this.ID = id;
	}
	
	public Entity getImage() {
		return entity;
	}

	public boolean isLocationSet() {
		return locationSet;
	}

	public void setLocationSet(boolean locationSet) {
		this.locationSet = locationSet;
	}

	public void generateGold(){
		Timer timer = new Timer();
		    
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if (gold <= maxGold - 1) {
					gold += prodRate;
					System.out.println("Goldmine Nr " + ID + " hat : " + gold + " Gold");
				} else {
					System.out.println("Goldmine Nr " + ID + " voll!");
				}
		    }
		}, 1*1000, 1*1000);
	}
	
	public void takeGold() {
		testMain.gold += gold;
		newTestMain.gold += gold;
		gold = 0;
	}
	
	public void upgrade() {
		maxGold += 5;
		prodRate++;
		testMain.gold -= 10;
		newTestMain.gold -= 10;
	}
}
