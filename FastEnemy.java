package gameT;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class FastEnemy extends GameObject{

	public FastEnemy(int x, int y, ID id) {
		super(x, y, id);
		
		velX = 20;
		velY = 20;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(x <= 0 || x >= Game.WIDTH -16) velX *= -1;
		if(y <= 0 || y >= Game.HEIGHT -32) velY *= -1;
	}

	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((int)x, (int)y, 8, 8);
	}


	public Rectangle getHitBox() {
		return new Rectangle(x,y,8,8);
	}

}
