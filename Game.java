package gameT;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = -7299324271264248131L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH/12 * 9;
	
	private Thread thread;
	private boolean isRunning = false;
	private static String title = "Test";
	
	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	
	public Game(){
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler)); //allows game to "listen" to keys
		
		new Window(WIDTH,HEIGHT,title,this);
		
		hud = new HUD();
		spawner = new Spawn(handler, hud);
		
		
		r = new Random();
		handler.addObject(new Player(WIDTH/2 -32, HEIGHT/2 -32, ID.Player, handler));
		handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy));

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
	
	public void run(){
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
	
	private void tick(){
		handler.tick();
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
		
		g.setColor(Color.orange);
		g.fillRect(0, 0, WIDTH, HEIGHT);
			
		handler.render(g);
			
		hud.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public static String getTitle() {
		return title;
	}
	
	public static int clamp(int var, int min, int max) {
		if(var >= max) return max;
		else if(var <= min) return min;
		return var;
	}
	
	public static void main(String[] args) {
		new Game();
		
		
	}
}
