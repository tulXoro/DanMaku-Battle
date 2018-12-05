package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class TankEnemy extends GameObject{

	
	private int damage = 5;
	private EnemyID id = EnemyID.TankEnemy;
	
	public TankEnemy(int x, int y, Game game) {
		super(x, y, game);
		
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		
		sprite = ss.grabImage(4, 1, 32, 32);
	}


	public int getDamage() {
		return damage;
	}

	
	public EnemyID getID() {
		
		return id;
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
		
		g.drawImage(sprite, x, y, 64, 64,null);
		
		g.setColor(Color.green);
		g2d.draw(getHitBox());
	}

	public Rectangle getHitBox() {
		return new Rectangle (x, y, 64, 64);
	}
}