package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player extends GameObject{
	
	private Handler h;
	private boolean isDashing = false;
	private int dashCntDown;
	private int dashBoost = 1;
	private int dashCoolDown = 0;

	public Player(int x, int y, Game game, Handler h) {
		super(x, y, game);

		this.h = h;
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		
		sprite = ss.grabImage(1, 1, 32, 32);
	}

	public void tick() {
		x += velX*dashBoost;
		y += velY*dashBoost;
		
		x = Game.clamp(x, -6, Game.WIDTH - 31);
		y = Game.clamp(y, -5, Game.HEIGHT - 53);
		
		collision();
		
		if(dashCoolDown-- > 0);
	}
	
	//TODO set so when collide, enemy loose HP
	private void collision() {
			if(!isDashing) {
				for(int i = 0; i<h.list.size(); i++) {
					EnemyB temp = h.list.get(i);
					if(getHitBox().intersects(temp.getHitBox())) {
						HUD.HP -= temp.getDamage();
						if(temp.isBrittle()) temp.setEneHP(temp.getEneHP()-1);
						if(temp.getEneHP() <= 0) h.list.remove(i);
					}
				};
			}else if(isDashing) {
				for(int i = 0; i<h.list.size(); i++) {
					EnemyB temp = h.list.get(i);
					if(getHitBox().intersects(temp.getHitBox())) {
						if(temp.isBrittle()) temp.setEneHP(temp.getEneHP()-2);
						else temp.setEneHP(temp.getEneHP()-1);
						if(temp.getEneHP() <= 0) h.list.remove(i);
					}
				};
			dashCntDown-=1;
			if(dashCntDown <= 0) {
				isDashing = false;
				dashBoost = 1;
			}
		}
		
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(Color.green);
		g.drawImage(sprite, x, y, null);

		g2d.draw(getHitBox());
	}

	public Rectangle getHitBox() {
		return new Rectangle (x+5, y+5, 20, 20);
	}
	
	public void takeDamage(int dmg) {
		HUD.HP -=2;
	}
	
	public boolean isDashing() {
		return isDashing;
	}
	
	public void setDashing(boolean isDashing) {
		this.isDashing = isDashing;
		dashCntDown = 10;
	}
	
	public int getDashBoost() {
		return dashBoost;
	}
	
	public void setDashBoost(int dashBoost) {
		this.dashBoost = dashBoost;
	}
	
	public int getDashCoolDown() {
		return dashCoolDown;
	}
	
	public void setDashCoolDown(int dashCoolDown) {
		this.dashCoolDown = dashCoolDown;
	}
}
