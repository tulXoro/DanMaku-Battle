package game;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	static int HP = 100;
	
	private int greenValue = 255;//color val of green
	private int timer = 6100;
	private int extra = 0;
	
	public void tick() {
		HP = Game.clamp(HP, 0, 100);
		
		greenValue = Game.clamp(greenValue, 0, 255);//causes HP to gradually turn red w/ less health
		greenValue = HP*2;
		
		if(timer > 0) timer-=2; //subtracts timer and adds extra score when player wins
		else extra++;
	}
	
	public void render(Graphics g) {
		//renders health bar
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 16);
		g.setColor(new Color(75, greenValue, 0));
		g.fillRect(15, 15, HP*2, 16);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 16);
		
		//g.setColor(Color.orange);
		g.drawString("Time Left: " + timer/100, 15, 45);
		if(extra>=1) g.drawString("ExtraScore: " + extra/100, 15, 65);
	}
	//self explanatory to share HP to other classes
	public int getHP() {
		return HP;
	}
	
	public int getTimer() {
		return timer;
	}
}
