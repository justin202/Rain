package com.thechorne.rain.level;

import com.thechorne.graphics.Screen;
import com.thechorne.rain.level.tile.Tile;

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
	
	/**
	 * 
	 * @param xScroll the x location of the player
	 * @param yScroll the y location of the player
	 * @param screen
	 */
	public void render(int xScroll, int yScroll, Screen screen){
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		// render one more tile at the most right
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		// render one more tile at the most bottom
		int y1 = (yScroll + screen.height + 16) >> 4;
		
		for(int y = y0; y < y1; y++){
			for(int x = x0; x < x1; x++){
				getTile(x, y).render(x, y, screen);
			}
		}
	}
	
	public Tile getTile(int x, int y){
		if(x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if(tiles[x + y * width] == 0) return Tile.grass;
		if(tiles[x + y * width] == 1) return Tile.flower;
		if(tiles[x + y * width] == 2) return Tile.rock;
		return Tile.voidTile;
	}

}
