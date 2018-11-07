package gameT;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	//TODO: Implement better movement
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
				
			if(tempObject.getId() == ID.Player) {
					
				if(key == KeyEvent.VK_W) tempObject.setVelY(-3);
				if(key == KeyEvent.VK_S) tempObject.setVelY(3);
				if(key == KeyEvent.VK_D) tempObject.setVelX(3);
				if(key == KeyEvent.VK_A) tempObject.setVelX(-3);
			}
		}
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
				
			if(tempObject.getId() == ID.Player) {
					
				if(key == KeyEvent.VK_W) tempObject.setVelY(0);
				if(key == KeyEvent.VK_S) tempObject.setVelY(0);
				if(key == KeyEvent.VK_D) tempObject.setVelX(0);
				if(key == KeyEvent.VK_A) tempObject.setVelX(0);
			}
		}
	}
}
	
