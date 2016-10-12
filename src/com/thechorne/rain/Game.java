package com.thechorne.rain;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.thechorne.graphics.Screen;
import com.thechorne.rain.input.Keyboard;
import com.thechorne.rain.level.Level;
import com.thechorne.rain.level.RandomLevel;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	private int width = 300;
	private int height = 300 / 16 * 9;
	public static final String TITLE = "Rain";
	
	private int scale = 3;
	
	private Thread thread;
	private Level level;
	private JFrame frame;
	
	private boolean running = false;
	
	private Screen screen;
	private Keyboard key;	// add keys control
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	public Game(){
		thread = new Thread(this, "Display");
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		frame = new JFrame();
		level = new RandomLevel(64, 64);
		key = new Keyboard();
		addKeyListener(key);
		
		screen = new Screen(width, height);
	}
	
	private synchronized void start(){
		running = true;
		thread.start();
	}
	
	private synchronized void stop(){
		try {
			running = false;
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	int x = 0, y = 0;
	private void update(){
		key.update();
		if(key.up) y--;
		if(key.down) y++;
		if(key.left) x--;
		if(key.right) x++;
	}
	
	private void render(){
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(1);
			return;
		}
		
		screen.clear();
		level.render(x, y, screen);
		
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = screen.getPixels()[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		final double ns = 1000000000.0 / 60.0;	// this is 1 / 60 s. every "ns" update the game. 1s update 60 times.
		double delta = 0.0;
		int fps = 0, ups = 0;
		requestFocus();							// request focus on the canvas
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;		// every loop contains how many ns. 累加至 1/60s，更新 
			lastTime = now;
			while(delta >= 1){
				update();
				ups++;
				delta--;
			}
			render();
			fps++;
			
			if(ups >= 60){
				frame.setTitle(TITLE + " | " + ups + " ups, " + fps + " fps");
				ups = 0;
				fps = 0;
			}
		}
		stop();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setTitle(TITLE);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setResizable(false);
		game.frame.setLocationRelativeTo(null);
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setVisible(true);
		game.start();
	}

}













