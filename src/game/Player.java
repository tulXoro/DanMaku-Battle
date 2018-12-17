package game;

import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player extends GameObject{
	
	private Handler h;
	private boolean isDashing = false;
	private int dashCntDown;
	private int dashBoost = 1;
	private int dashCoolDown = 0;
	private int dImmunity = 0;

	public Player(int x, int y, Game game, Handler h) {
		super(x, y, game);

		this.h = h;//allows use of handler in the player class
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());//allows use of spritesheet (implemented to all enemies)
		
		sprite = ss.grabImage(1, 1, 32, 32); //sets sprite of object (implemented in all enemies)
	}

	public void tick() {
		x += velX*dashBoost; //sets velocity for fluid movement. implemented in all enemies
		y += velY*dashBoost; //excluding dash mechanic (enemies cant dash) and x is only implemented to
							 //fast enemy because they only move left to right
		
		x = Game.clamp(x, -6, Game.WIDTH - 31); //sets boundaries so player can never go offscreen
		y = Game.clamp(y, -5, Game.HEIGHT - 53);
		
		collision(); //calls collision
		
		//constantly subtracts stuff
		if(dashCntDown-- > 0);
		if(dashCoolDown-- > 0);
		if(dImmunity-- > 0); 
	}
	
	//Detects collision
	private void collision() {
			if(dImmunity <= 0) { //while not dashing, take damage and damage enemy if not brittle
				for(int i = 0; i<h.list.size(); i++) {
					EnemyB temp = h.list.get(i);
					if(getHitBox().intersects(temp.getHitBox())) {
						HUD.HP -= temp.getDamage();
						if(temp.isBrittle()) temp.setEneHP(temp.getEneHP()-1);
						if(temp.getEneHP() <= 0) h.list.remove(temp);
					}
				};
			}else if(dImmunity > 0) { //while dashing, damage everything(if not brittle, take less damage)
				for(int i = 0; i<h.list.size(); i++) {
					EnemyB temp = h.list.get(i);
					if(getHitBox().intersects(temp.getHitBox())) {
						if(temp.isBrittle()) temp.setEneHP(temp.getEneHP()-2);
						else temp.setEneHP(temp.getEneHP()-1);
						if(temp.getEneHP() <= 0) h.list.remove(temp);
					}
				};
			if(dashCntDown <= 0) {
				isDashing = false; //sets dash to false when effect
				dashBoost = 1; //dash boost modifier resets
			}
		}
	}
	
	public void render(Graphics g) {
		//Graphics2D g2d = (Graphics2D) g;
		
		g.drawImage(sprite, x, y, null);//renders sprite as player

		//g2d.draw(getHitBox());
	}

	public Rectangle getHitBox() {
		return new Rectangle (x+5, y+5, 20, 20); //rect for hitbox collision. implemented in all enemies
	}
	
	//These should be self explanatory
	public boolean isDashing() {
		return isDashing;
	}
	
	public void setDashingTrue() {
		isDashing = true;
		dImmunity = 25;//sets immunity which allows player to not take damage, even for a short time after dash
		dashCntDown = 10; //sets dashcountdown to activate dashing
		dashCoolDown = 30;//sets cooldown so player cant spam dash
		dashBoost = 3;//multiplier to increase speed 
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
