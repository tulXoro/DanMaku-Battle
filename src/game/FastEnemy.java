package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class FastEnemy extends EnemyB{
	
	public FastEnemy(int x, int y, int damage, int eneHP, Game game) {
		super(x, y, damage, eneHP, game);
		
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		
		velX = 15;
		sprite = ss.grabImage(3, 1, 32, 32);
		id = EnemyID.FastEnemy;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		//x = Game.clamp(x, 0, Game.WIDTH);
		//y = Game.clamp(y, 0, Game.HEIGHT);

	}


	public void render(Graphics g) {
		
		g.drawImage(sprite, x, y, 32, 32,null);
		
		g.setColor(Color.green);
		//g2d.draw(getHitBox());
	}

	public Rectangle getHitBox() {
		return new Rectangle (x+5, y+5, 20, 20);
	}

}
