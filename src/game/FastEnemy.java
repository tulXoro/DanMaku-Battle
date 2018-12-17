package game;

import java.awt.Color;
import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.awt.Rectangle;

public class FastEnemy extends EnemyB{
	
	public FastEnemy(int x, int y, int damage, int eneHP, Game game) {
		super(x, y, damage, eneHP, game);
		
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		
		velX = 15;
		
		sprite = ss.grabImage(3, 1, 32, 32);
	}

	public void tick() {
		x += velX;
	}


	public void render(Graphics g) {
		//Graphics2D g2d = (Graphics2D) g;
		
		g.drawImage(sprite, x, y, 32, 32,null);
		
		g.setColor(Color.green);
		//g2d.draw(getHitBox());
	}

	public Rectangle getHitBox() {
		return new Rectangle (x+5, y+5, 20, 20);
	}

}
