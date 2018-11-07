package gameT;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject{

	public BasicEnemy(int x, int y, ID id) {
		super(x, y, id);
		
		velX = 5;
		velY = 5;
		
	}
	
	public Rectangle getHitBox() {
		return new Rectangle(x,y,16,16);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(x <= 0 || x >= Game.WIDTH -16) velX *= -1;
		if(y <= 0 || y >= Game.HEIGHT -32) velY *= -1;

	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 16, 16);
	}
}
