package game;

import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.awt.Rectangle;

public class BasicEnemy extends EnemyB{

	private Player p;
	public BasicEnemy(int x, int y, int damage, int eneHP, Player p, Game game) {
		super(x, y, damage, eneHP, game);
		
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		
		sprite = ss.grabImage(2, 1, 32, 32);
		this.p = p;//adds player to set tracking
		//setsID
		id = EnemyID.BasicEnemy;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		//super complex AI which tracks the player and changes the enemy's velocity to move towards player
		if(p.getX()>x) velX = 5;
		else velX=-5;
		
		if(p.getY()>y) velY = 5;
		else velY=-5;
		
		//x = Game.clamp(x, -6, Game.WIDTH - 31);
		//y = Game.clamp(y, -5, Game.HEIGHT - 53);
		

	}

	public void render(Graphics g) {		
		//Graphics2D g2d = (Graphics2D) g;
		
		g.drawImage(sprite, x, y, 45, 45,null);
		
		//g2d.draw(getHitBox());
	}

	public Rectangle getHitBox() {
		return new Rectangle (x+4, y+2, 34, 37);
	}
	
}
