package com.thechorne.graphics;

/**
 * one cell of sprite sheet
 * @author Hu
 *
 */
public class Sprite {
	
	public final int SIZE;	// size of sprite, not the sprite sheet size
	private int x, y;		// the start pixel coordinates of the sprite
	public int[] pixels;
	private SpriteSheet sheet;
	
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	
	/**
	 * constructor
	 * @param size	
	 * @param x		the X coordinate of the sprite in the sprite sheet
	 * @param y		the Y coordinate of the sprite in the sprite sheet
	 * @param sheet
	 */
	public Sprite(int size, int x, int y, SpriteSheet sheet){
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * SIZE;
		this.y = y * SIZE;
		this.sheet = sheet;
		load();
	}

	private void load() {
		for(int y = 0; y < SIZE; y++){
			for(int x = 0; x < SIZE; x++){
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}

}