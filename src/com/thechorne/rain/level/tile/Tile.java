package com.thechorne.rain.level.tile;

import com.thechorne.graphics.Screen;
import com.thechorne.graphics.Sprite;

public class Tile {
	
	private int x, y;
	public Sprite sprite;
	
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile flower = new FlowerTile(Sprite.flower);
	public static Tile rock = new RockTile(Sprite.rock);
	public static Tile voidTile = new VoidTile(Sprite.voidTile);
	
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
