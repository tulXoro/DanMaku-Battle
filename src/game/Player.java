package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Player extends GameObject{
	
	private HUD hud;
	private boolean isDashing = false;

	public Player(int x, int y, Game game, HUD hud) {
		super(x, y, game);

		
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
		Graphics2D g2d = (Graphics2D) g;
		
		g.drawImage(sprite, x, y, null);
		
		g.setColor(Color.green);
		g2d.draw(getHitBox());
	}

	public Rectangle getHitBox() {
		return new Rectangle (x+5, y+5, 20, 20);
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
