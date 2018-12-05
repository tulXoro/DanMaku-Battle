package game;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	LinkedList<GameObject> ene = new LinkedList<GameObject>();
	
	public void tick() {
		for(GameObject i : ene) {
			i.tick();
		}
	}
	
	public void render(Graphics g) {
		for(GameObject i : ene) {
			i.render(g);
		}
	}
	
	public void addEnemy(GameObject enemy) {
		this.ene.add(enemy);
	}
	
}