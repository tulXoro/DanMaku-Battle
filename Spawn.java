package gameT;

import java.util.Random;

public class Spawn {
	
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	private int scoreKeep = 0;
	
	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick() {
		scoreKeep++;
		
		if(scoreKeep >= 100) {
			scoreKeep = 0;
			hud.setWave(hud.getWave() + 1);
			
			switch (hud.getWave()) {
			case 2:
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy));
				break;
			case 3:
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy));



			default:
				break;
			}
		}
	}
	
	
}
