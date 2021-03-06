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

	/**
	 * 
	 * @param xp
	 * @param yp
	 * @param sprite
	 * @param flip	1: flip x, 2: flip y, 3: flip both x and y
	 */
	public void renderPlayer(int xp, int yp, Sprite sprite, int flip){
		xp -= xOffset;
		yp -= yOffset;
		for(int y = 0; y < sprite.SIZE; y++){
			int ya = y + yp;
			int yFlip = y;
			if(flip == 2 || flip == 3)
				yFlip = 31 - y;
			for(int x = 0; x < sprite.SIZE; x++){
				int xa = x + xp;
				int xFlip = x;
				if(flip == 1 || flip == 3)
					xFlip = 31 - x;
				// render one more tile in the most left, and the same operation for the most right and bottom, which is operated in the level class 
				if(xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) continue;
				if(xa < 0) xa = 0;
				// get rid of the background color of player
				int col = sprite.pixels[xFlip + yFlip * sprite.SIZE];
				if(col != 0xffff00ff) pixels[xa + ya * width] = col;
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset){
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

}
