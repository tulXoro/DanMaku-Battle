package game;

import java.util.Random;

public class Spawner {
	private Handler h;
	private Player p;
	private Random r = new Random();
	private Game game;
	private int cnt;
	
	public Spawner(Handler h, Player p, Game game) {
		//variable setting
		this.h = h;
		this.p = p;
		this.game = game;
		cnt = 1100;
	}
	
	public void tick(){
		//Spawns new enemies at certain intervals
		if(cnt%500==0) h.addEnemy(new TankEnemy(r.nextInt(3),r.nextInt(Game.HEIGHT-64), 15, 9, p, game));
		if(cnt%100==0) h.addEnemy(new BasicEnemy(Game.WIDTH-26,r.nextInt(Game.HEIGHT), 10, 1, p, game));
		if(cnt%5 == 0) h.addEnemy(new FastEnemy(r.nextInt(3),r.nextInt(Game.HEIGHT-32), 1, 1, game));
		
		//when counter reaches 0, counter resets
		if(cnt<=0) cnt=1100;
		cnt--; //constantly remove counter
		
		//removes enemy when outside boundaries
		for(int i = 0; i<h.list.size(); i++) {
			EnemyB temp = h.list.get(i);
			if(temp.getX() <= 0 || temp.getX() >= Game.WIDTH) h.list.remove(temp);
			if(temp.getY() <= 0 || temp.getY() >= Game.HEIGHT) h.list.remove(temp);
		};
	}
}
