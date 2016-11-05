package com.thechorne.rain.entity.mob;

import com.thechorne.graphics.Screen;
import com.thechorne.graphics.Sprite;
import com.thechorne.rain.input.Keyboard;

public class Player extends Mob {
	
	private Keyboard input;
	private Sprite sprite;
	private boolean walking = false;
	private int anim = 0;
	
	public Player(Keyboard input){
		this.input = input;
		sprite = Sprite.player_back[0];
	}
	
	public Player(int x, int y, Keyboard input){
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.player_back[0];
	}
	
	public void update(){
		int xa = 0, ya = 0;
		if(anim <= 4000)
			anim++;
		else
			anim = 0;
		if(input.up) ya--;
		if(input.down) ya++;
		if(input.left) xa--;
		if(input.right) xa++;
		
		if(xa != 0 || ya != 0){
			move(xa, ya);
			walking = true;
		}else
			walking = false;
	}
	
	public void render(Screen screen){
		int flip = 0;
		switch(direction){
		case 0: 			//up
			sprite = Sprite.player_forward[0];
			if(walking){
				if(anim % 40 > 30){
					sprite = Sprite.player_forward[1];
				}
				else{
					if(anim % 40 > 20){
						sprite = Sprite.player_forward[0];
					}else{
						if(anim % 40 > 10){
							sprite = Sprite.player_forward[2];
						}else{
							sprite = Sprite.player_forward[0];
						}
					}
				}
			}
			break;
		case 1: 			//right
			sprite = Sprite.player_side[0];
			if(walking){
				if(anim % 40 > 30){
					sprite = Sprite.player_side[1];
				}
				else{
					if(anim % 40 > 20){
						sprite = Sprite.player_side[0];
					}else{
						if(anim % 40 > 10){
							sprite = Sprite.player_side[2];
						}else{
							sprite = Sprite.player_side[0];
						}
					}
				}
			}
			break;
		case 2: 			//down
			sprite = Sprite.player_back[0];
			if(walking){
				if(anim % 40 > 30){
					sprite = Sprite.player_back[1];
				}
				else{
					if(anim % 40 > 20){
						sprite = Sprite.player_back[0];
					}else{
						if(anim % 40 > 10){
							sprite = Sprite.player_back[2];
						}else{
							sprite = Sprite.player_back[0];
						}
					}
				}
			}
			break;
		case 3: 			//left
			sprite = Sprite.player_side[0];
			if(walking){
				if(anim % 40 > 30){
					sprite = Sprite.player_side[1];
				}
				else{
					if(anim % 40 > 20){
						sprite = Sprite.player_side[0];
					}else{
						if(anim % 40 > 10){
							sprite = Sprite.player_side[2];
						}else{
							sprite = Sprite.player_side[0];
						}
					}
				}
			}
			flip = 1;
			break;
		default:
			sprite = Sprite.player_back[0];
			break;
		}
		screen.renderPlayer(x - 16, y - sprite.SIZE / 2, sprite, flip);
	}

}
