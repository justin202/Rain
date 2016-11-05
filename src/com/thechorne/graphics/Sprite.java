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
	public static Sprite voidTile = new Sprite(16, 0x1b87e0);
	
	public static Sprite[] player_forward = {new Sprite(32, 0, 5, SpriteSheet.tiles), 
												new Sprite(32, 0, 6, SpriteSheet.tiles), 
												new Sprite(32, 0, 7, SpriteSheet.tiles)};
	public static Sprite[] player_back = {new Sprite(32, 2, 5, SpriteSheet.tiles),
											new Sprite(32, 2, 6, SpriteSheet.tiles),
											new Sprite(32, 2, 7, SpriteSheet.tiles)};
	public static Sprite[] player_side = {new Sprite(32, 1, 5, SpriteSheet.tiles),
											new Sprite(32, 1, 6, SpriteSheet.tiles),
											new Sprite(32, 1, 7, SpriteSheet.tiles)};
	// rendering the right sprite the reverse way can avoid using the left sprite 
//	public static Sprite player_left = new Sprite(32, 3, 5, SpriteSheet.tiles);
	
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
	
	/**
	 * constructor for the tile with only one color
	 * @param size
	 * @param colour
	 */
	public Sprite(int size, int color){
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}

	private void setColor(int color) {
		for(int i = 0; i < SIZE * SIZE; i++){
			pixels[i] = color;
		}
	}

	private void load() {
		for(int y = 0; y < SIZE; y++){
			for(int x = 0; x < SIZE; x++){
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}

}
