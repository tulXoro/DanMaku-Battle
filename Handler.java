package game;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	LinkedList<EnemyB> list = new LinkedList<EnemyB>();
	
	public void tick() {
		for(GameObject i : list) {
			i.tick();
		}
	}
	
	public void render(Graphics g) {
		for(GameObject i : list) {
			i.render(g);
		}
	}
	
	public void addEnemy(EnemyB enemy) {
		this.list.add(enemy);
	}
	
}