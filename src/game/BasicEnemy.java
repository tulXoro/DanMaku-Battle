package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject implements EnemyS{
	
	private int damage = 5;
	private HUD hud;
	
	public BasicEnemy(int x, int y, Game game, HUD hud) {
		super(x, y, game);
		this.hud = hud;
		
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		
		sprite = ss.grabImage(2, 1, 32, 32);
		
	}
	
	public int getDamage(){
		return damage;
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, -6, Game.WIDTH - 31);
		y = Game.clamp(y, -5, Game.HEIGHT - 53);
	}

	public void render(Graphics g) {		
		Graphics2D g2d = (Graphics2D) g;
		
		g.drawImage(sprite, x, y, 45, 45,null);
		
		g.setColor(Color.green);
		g2d.draw(getHitBox());
	}

	public Rectangle getHitBox() {
		return new Rectangle (x+4, y+2, 34, 37);
	}
	
}
