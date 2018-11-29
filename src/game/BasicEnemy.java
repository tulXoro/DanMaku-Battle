package game;

import java.awt.Graphics;
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
		g.drawImage(sprite, x, y, null);
	}

	public Rectangle getHitBox() {
		return null;
	}
	
}
