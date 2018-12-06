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
	
	//game background
	private Thread thread;
	private boolean isRunning = false;
	private String title = "DanMakuBlock"; 
	
	//sprites
	private BufferedImage spriteSheet;
	
	//gameobjects
	private Player p;
	private Handler h;
	private HUD hud;
	private Spawner spawner;
	
	//FOR MOVEMENT
	private boolean overWriteX = false, overWriteY = false;
	
	//initializes when game starts
	public void init() throws IOException{
		BufferedImageLoader loader = new BufferedImageLoader();
		
		spriteSheet = loader.loadImage("res/spriteSheet.png");//throws exception if directory not found
		
		//key listener for key input
		addKeyListener(new KeyInput(this));
		
		//inits game objects
		h = new Handler();
		hud = new HUD();
		p = new Player(200, 200, this, h);
		spawner = new Spawner(hud, h, this);
	}
	
	//makes thread when starts
	public synchronized void start(){
		if(isRunning) return;
		thread = new Thread(this);
		thread.start();
		isRunning = true;
	}
	
	//closes thread when game ends
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
		//when directory not found
		try {
			init();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		    }
	    }
		stop();
	}
	
	//ticks all objects
	private void tick(){
		p.tick();
		h.tick();
		hud.tick();
		spawner.tick();
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		//game background
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		p.render(g);
		h.render(g);
		hud.render(g);
		
		g.dispose();
		bs.show();
	}
	//keyboard stuff
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
		
		//DASH
		if(key == KeyEvent.VK_SPACE) {
			if(p.getDashCoolDown() <= 0) {
				p.setDashing(true);
				p.setDashBoost(4);
				p.setDashCoolDown(50);
			}
		}
		
		//CLOSE
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
		
		//DEBUG COMMANDS
		if(key == KeyEvent.VK_H) {
			HUD.HP -=12;
		}
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
	
	//getters
	public String getTitle() {
		return title;
	}

	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

	public static int clamp(int var, int min, int max) {
		if(var >= max) return max;
		else if(var <= min) return min;
		return var;
	}

	public Game(){
		new Window(WIDTH,HEIGHT,title,this);
	}

	public static void main(String[] args) {
		new Game();
	}
}
