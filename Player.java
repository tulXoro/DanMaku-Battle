package gameT;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject{
	
	Random r = new Random();
	Handler handler;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
	}
	
	public Rectangle getHitBox() {
		return new Rectangle(x,y,32,32);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp((int)x, 0, Game.WIDTH - 37);
		y = Game.clamp((int)y, 0, Game.HEIGHT - 60);
		
		collision();
	}
	
	private void collision() {
		for(int i =0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.BasicEnemy) {
				if(getHitBox().intersects(tempObject.getHitBox())) {
					HUD.HEALTH--;
				}
			}
		}
	}

	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(Color.black);
		g2d.draw(getHitBox());
		
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, 32, 32);
	}
}
