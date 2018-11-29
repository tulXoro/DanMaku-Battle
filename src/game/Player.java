package game;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{
	
	HUD hud;
	private boolean isDashing = false;

	public Player(int x, int y, Game game, HUD hud) {
		super(x, y, game);
		this.hud = hud;
		
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		
		sprite = ss.grabImage(1, 1, 32, 32);
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
	
	public void takeDamage(int dmg) {
		hud.setHealth(hud.getHP()-dmg);
	}
	
	public boolean isDashing() {
		return isDashing;
	}
	
	public void setDashing(boolean isDashing) {
		this.isDashing = isDashing;
	}
	
}
