package com.thechorne.rain.level;

import com.thechorne.graphics.Screen;

/**
 * this is general level class, the actual level will extends this level class</br>
 * there are two kinds of level:</br>
 * 1. random level</br>
 * 2. load level from file
 * 
 * @author Hu
 *
 */
public class Level {
	
	protected int width, height;
	protected int[] tiles;
	
	public Level(int width, int height){
		this.width = width;
		this.height = height;
		this.tiles = new int[width * height];
		generateLevel();
	}
	
	public Level(String path){
		loadLevel(path);
	}

	private void loadLevel(String path) {
	}

	protected void generateLevel() {
	}
	
	public void update(){
	}
	
	public void time(){
	}
	
	public void render(int xScroll, int yScroll, Screen screen){
	}

}
