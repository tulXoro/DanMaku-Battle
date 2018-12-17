package game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class GameObject {
	protected int x, y;
	protected int velX, velY;
	protected BufferedImage sprite;
	
	public GameObject(int x, int y, Game game){
		this.x = x;
		this.y = y;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getHitBox();
	
	//setters and getters that are inherited so that I dont need to rewrite them
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getVelX() {
		return velX;
	}
	
	public int getVelY() {
		return velY;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setVelX(int velX) {
		this.velX = velX;
	}
	
	public void setVelY(int velY) {
		this.velY = velY;
	}
	
}
