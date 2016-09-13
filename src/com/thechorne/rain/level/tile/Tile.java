package com.thechorne.rain.level.tile;

import com.thechorne.graphics.Screen;
import com.thechorne.graphics.Sprite;

public class Tile {
	
	private int x, y;
	private Sprite sprite;
	
	public static Tile grass = new GrassTile(Sprite.grass);
	
	public Tile(Sprite sprite){
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen){
	}
	
	/**
	 * 
	 * @return	false the tile can be across
	 * 			true  the tile cannot be across
	 */
	public boolean solid(){
		return false;
	}
}
