package game;

import java.util.Random;

public class Spawner {
	private HUD hud;
	private Handler h;
	private Random r = new Random();
	private Game game;
	private int cnt;
	public Spawner(HUD hud, Handler h,Game game) {
		
		this.hud = hud;
		this.h = h;
		this.game = game;
		cnt = 500;
	}
	
	public void tick(){
		if(cnt%100==0) h.addEnemy(new BasicEnemy(r.nextInt(Game.WIDTH),r.nextInt(Game.HEIGHT), 1, 1, game));
		if(cnt<=0) cnt=500;
		cnt--;
	}
}
