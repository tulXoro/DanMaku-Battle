package game;

import java.awt.Color;
import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.awt.Rectangle;

public class TankEnemy extends EnemyB{
	
	private Player p;
	public TankEnemy(int x, int y, int damage, int eneHP, Player p, Game game) {
		super(x, y, damage, eneHP, game);
		
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		
		sprite = ss.grabImage(4, 1, 32, 32);
		this.p = p; //adds player for AI tracking
		brittle = false;
	}


	public void tick() {
		x += velX;
		y += velY;
		
		//super complex AI which tracks the player and changes the enemy's velocity to move towards player
		if(p.getX()>x) velX = 1;
		else velX=-1;
		
		if(p.getY()>y) velY = 1;
		else velY=-1;
		
	}


	public void render(Graphics g) {
		//Graphics2D g2d = (Graphics2D) g;
		
		g.drawImage(sprite, x, y, 64, 64,null);
		
		g.setColor(Color.green);
		//g2d.draw(getHitBox());
	}

	public Rectangle getHitBox() {
		return new Rectangle (x, y, 64, 64);
	}
}
