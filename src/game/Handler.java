package game;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	LinkedList<EnemyB> list = new LinkedList<EnemyB>();
	
	public void tick() {
		for(EnemyB i : list) {
			i.tick();
		}
	}
	
	public void render(Graphics g) {
		for(EnemyB i : list) {
			i.render(g);
		}
	}
	
	public void addEnemy(EnemyB enemy) {
		this.list.add(enemy);
	}
	
}