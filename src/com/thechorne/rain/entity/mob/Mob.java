package com.thechorne.rain.entity.mob;

import com.thechorne.graphics.Sprite;
import com.thechorne.rain.entity.Entity;

public abstract class Mob extends Entity {
	
	protected Sprite sprite;
	protected int direction = -1; // 0: up, 1: right, 2: down, 3: left, -1: default
	protected boolean moving = false;
	
	public void update(){
		
	}
	
	public void render(){
		
	}
	
	/**
	 * 
	 * @param xa the change of x coordinate, xa = -1, 0, 1 which means
	 * @param ya the change of x coordinate
	 */
	public void move(int xa, int ya){
		
		if(xa > 0) direction = 1;
		if(xa < 0) direction = 3;
		if(ya > 0) direction = 2;
		if(ya < 0) direction = 0;
		
		if(!collision()){
			x += xa;
			y += ya;
		}
		
	}
	
	private boolean collision(){
		return false;
	}
	
}
