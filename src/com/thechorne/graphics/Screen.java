package com.thechorne.graphics;

import java.util.Random;

import com.thechorne.rain.level.tile.Tile;

public class Screen {
	
	public int width, height;
	private int[] pixels;
	public int xOffset, yOffset;
	private final int TILE_SIZE = 64;
	private final int TILE_SIZE_MASK = TILE_SIZE - 1;
	
	private int[] tiles = new int[TILE_SIZE * TILE_SIZE];
	private Random random = new Random();
	
	public Screen(int width, int height){
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		
		for(int i = 0; i < tiles.length; i++)
			tiles[i] = random.nextInt(0xffffff);
	}
	
	public int[] getPixels() {
		return pixels;
	}
	
	public void clear(){
		for(int i = 0; i < pixels.length; pixels[i++] = 0);
	}

	/**
	 * 
	 * @param xp x position
	 * @param yp y position
	 * @param tile
	 * 
	 * xa: the absolute position of x on the map
	 * ya: the absolute position of y on the map
	 */
	public void renderTile(int xp, int yp, Tile tile){
		xp -= xOffset;
		yp -= yOffset;
		for(int y = 0; y < tile.sprite.SIZE; y++){
			int ya = y + yp;
			for(int x = 0; x < tile.sprite.SIZE; x++){
				int xa = x + xp;
				// render one more tile in the most left, and the same operation for the most right and bottom, which is operated in the level class 
				if(xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) continue;
				if(xa < 0) xa = 0;
				pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
			}
		}
	}

	public void renderPlayer(int xp, int yp, Sprite sprite){
		xp -= xOffset;
		yp -= yOffset;
		for(int y = 0; y < 16; y++){
			int ya = y + yp;
			for(int x = 0; x < 16; x++){
				int xa = x + xp;
				// render one more tile in the most left, and the same operation for the most right and bottom, which is operated in the level class 
				if(xa < -16 || xa >= width || ya < 0 || ya >= height) continue;
				if(xa < 0) xa = 0;
				int col = sprite.pixels[x + y * 16];
				// get rid of the background color of player
				if(col != 0xffFF00ff) pixels[xa + ya * width] = col;
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset){
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

}
