package com.thechorne.rain.level.tile;

import com.thechorne.graphics.Screen;
import com.thechorne.graphics.Sprite;

public class RockTile extends Tile {

	public RockTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen){
		// in the level.render, the x and y have the operation of >> 4
		screen.renderTile(x << 4, y << 4, this);
	}
	
	public boolean solid(){
		return true;
	}

}
