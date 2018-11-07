package gameT;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	public static int HEALTH = 100;
	
	private int greenValue = 255;
	private int score = 0;
	private int wave = 1;
	private int timer = 60;
	
	public void tick() {
		HEALTH = Game.clamp(HEALTH, 0, 100);
		
		greenValue = Game.clamp(greenValue, 0, 255);
		
		greenValue = HEALTH*2;
		
		score++;
		timer--;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 16);
		g.setColor(new Color(75, greenValue, 0));
		g.fillRect(15, 15, HEALTH*2, 16);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 16);
		
		g.setColor(Color.black);
		g.drawString("Wave: " + wave, 15, 45);
		g.drawString("Score: " + score, 15, 60);
		g.drawString("Time Left: " + timer, 15, 75);

	}
	
	public void score(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getWave() {
		return wave;
	}
	
	public void setWave(int wave) {
		this.wave = wave;
	}
}
