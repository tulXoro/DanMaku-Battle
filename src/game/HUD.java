package game;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	static int HP = 100;
	
	private int greenValue = 255;
	private int timer = 6100;
	
	public void tick() {
		HP = Game.clamp(HP, 0, 100);
		
		greenValue = Game.clamp(greenValue, 0, 255);
		
		greenValue = HP*2;
		
		if(timer > 0) timer-=2;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 16);
		g.setColor(new Color(75, greenValue, 0));
		g.fillRect(15, 15, HP*2, 16);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 16);
		
		//g.setColor(Color.orange);
		g.drawString("Time Left: " + timer/100, 15, 45);
	}
	
	public int getHP() {
		return HP;
	}
	
	public int getTimer() {
		return timer;
	}
}
