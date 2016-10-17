package com.thechorne.rain.entity;

import java.util.Random;

import com.thechorne.graphics.Screen;
import com.thechorne.rain.level.Level;

public abstract class Entity {
	
	public int x, y;
	private boolean removed;
	protected Level level;
	protected final Random random = new Random();
	
	public void update(){
	}
	
	public void render(Screen screen){
	}
	
	public void removed(){
		// removed from level
		removed = true;
	}
	
	public boolean isRemoved(){
		return removed;
	}

}
