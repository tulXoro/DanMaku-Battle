package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 8242415648071074540L;

	public static final int WIDTH = 640, HEIGHT = WIDTH/12 * 9; //used for pop out window
	
	private Thread thread;
	private boolean isRunning = false;
	private String title = "DanMakuBlock";
	
	private BufferedImage spriteSheet;
	
	private Player p;
	private BasicEnemy bEne;
	private HUD hud;
	
	//FOR MOVEMENT
	private boolean overWriteX = false, overWriteY = false;
	
	public void init() {
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			
			spriteSheet = loader.loadImage("res/spriteSheet.png");
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		addKeyListener(new KeyInput(this));
		
		hud = new HUD();
		p = new Player(200, 200, this, hud);
		bEne = new BasicEnemy(300, 300, this, hud);
	}
	
	
	public Game(){
		
		new Window(WIDTH,HEIGHT,title,this);
		
	}
	
	public synchronized void start(){
		if(isRunning) return;
		thread = new Thread(this);
		thread.start();
		isRunning = true;
	}
	
	public synchronized void stop(){
		if(!isRunning) return;
		try{
			thread.join();
		}catch(InterruptedException e){
			e.printStackTrace();
		}isRunning = false;
	}
	
	//GAMELOOP
	public void run(){
		init();
	    this.requestFocus();
	    long lastTime = System.nanoTime();
	    double amountOfTicks = 60.0;
	    double ns = 1000000000 / amountOfTicks;
	    double delta = 0;
	    long timer = System.currentTimeMillis();
	    int frames = 0;
	    while(isRunning){
	    	long now = System.nanoTime();
	    	delta += (now - lastTime) / ns;
	    	lastTime = now;
	    	while(delta >= 1){
	    		  tick();
	    		  delta--;
	    	}
	    	render();
	    	frames++;
	    	if(System.currentTimeMillis() - timer > 1000){
	    		timer += 1000;
	    		System.out.println("FPS: " + frames);
	    		frames = 0;
	    		System.out.println(p.isDashing());
		    }
	    }
		stop();
	}
	
	private void tick(){
		p.tick();
		bEne.tick();
		hud.tick();
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		//background
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		p.render(g);
		bEne.render(g);
		hud.render(g);
		
		if(p.getHitBox().intersects(bEne.getHitBox())) {
			hud.setHealth(hud.getHP()-1);
		}
		
		g.dispose();
		bs.show();
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		//MOVEMENT
		if(key == KeyEvent.VK_W) {
			if(p.getVelY() == 5) overWriteY = true;
			p.setVelY(-5);
		}
		if(key == KeyEvent.VK_S) {
			if(p.getVelY() == -5) overWriteY = true;
			p.setVelY(5);
		}
		if(key == KeyEvent.VK_D) {
			if(p.getVelX() == -5) overWriteX = true;
			p.setVelX(5);
		}
		if(key == KeyEvent.VK_A) {
			if(p.getVelX() == 5) overWriteX = true;
			p.setVelX(-5);
		}
		
		//DEBUG COMMANDS
		if(key == KeyEvent.VK_H) {
			p.takeDamage(10);
		}
		
		//DASH
		if(key == KeyEvent.VK_SPACE) {
			p.setDashing(true);
			if(p.getVelX() == -5) p.setX(p.getX()-50);
			if(p.getVelX() == 5) p.setX(p.getX()+50);
			if(p.getVelY() == -5) p.setY(p.getY()-50);
			if(p.getVelY() == 5) p.setY(p.getY()+50);
		}
		
		//CLOSE
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		//MOVEMENT
		if(key == KeyEvent.VK_W) {
			if(overWriteY) p.setVelY(5);
			else p.setVelY(0);
			overWriteY = false;
		}
		if(key == KeyEvent.VK_S) {
			if(overWriteY) p.setVelY(-5);
			else p.setVelY(0);
			overWriteY = false;
		}
		if(key == KeyEvent.VK_D) {
			if(overWriteX) p.setVelX(-5);
			else p.setVelX(0);
			overWriteX = false;
		}
		if(key == KeyEvent.VK_A) {
			if(overWriteX) p.setVelX(5);
			else p.setVelX(0);
			overWriteX = false;
		}
	}
	
	public static int clamp(int var, int min, int max) {
		if(var >= max) return max;
		else if(var <= min) return min;
		return var;
	}

	public String getTitle() {
		return title;
	}


	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}


	public static void main(String[] args) {
		new Game();
	}
}
