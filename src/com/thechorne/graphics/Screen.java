package com.thechorne.graphics;

import java.util.Random;

public class Screen {
	
	private int width, height;
	private int[] pixels;
	
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

	public void render(int xOffset, int yOffset){
		for(int y = 0; y < height; y++){
			int yp = y + yOffset;
			if( yp < 0 || yp >= height) continue;
			for(int x = 0; x < width; x++){
				int xp = x + xOffset;
				if(xp < 0 || xp >= width) continue;
				pixels[xp + yp * width] = Sprite.grass.pixels[(x & 15) + (y & 15) * Sprite.grass.SIZE];
			}
		}
	}

}
