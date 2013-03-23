package peretti.pong;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameManager extends Applet implements Runnable, KeyListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3338701004403679115L;
	protected static byte LEFT = 0;
	protected static byte RIGHT = 1;
	protected static final int scoreToWin = 10;
	
	boolean playing = true;
	boolean demo = true;
	boolean pause = false;
	
	Bar left, right;
	Ball ball;
	int w = 700, h = 600;
	
	Graphics offscreen;
	Image image;
	Thread animation;

	double before, after;
	
	int lscore, rscore;
	static final int ticksPerSecond = 30;
	Field field;
	
	public void init() {
		field = new Field(this, w, h);
		resize(w, h);
		showStatus("Loading -- WAIT!");
		setBackground(Color.black);
		image = createImage(w, h);
		offscreen = image.getGraphics();
		setFocusable(true);
		addKeyListener(this);
	}
	
	public void start() {
		animation = new Thread(this);
		if(animation != null)
			animation.start();
	}

	public void stop() {
		if(animation != null){
			animation.interrupt();
			animation = null;
		}
	}
	
	public void newGame(){
		left = new PlayerBar(3, h/2, this);
		right = new AiBar(w - 3 - AiBar.WIDTH, h/2, this);
		ball = new Ball(w / 2, h / 2, this);
		lscore = 0;
		rscore = 0;
		field.NewGame();
		playing = true;
		demo = false;
	}
	
	public void demo(){
		left = new AiBar(3, h/2, this);
		right = new AiBar(w - 3 - AiBar.WIDTH, h/2, this);
		ball = new Ball(w / 2, h / 2, this);
		lscore = 0;
		rscore = 0;
		field.demo();
		playing = true;
		demo = true;
	}
	
	public void gameOver(){
		playing = false;
		field.GameOver();
	}
	
	public void paint(Graphics g){
		field.paint(g);
		left.paint(g);
		right.paint(g);
		ball.paint(g);
	}
	
	public void run(){
		demo();
		while (true) {
			before = System.nanoTime();
			repaint();
			updateManagers();
			//Thread.currentThread().yield();
			after = System.nanoTime();
			try {
				Thread.sleep (1000 / ticksPerSecond - (int)((after - before) / 1000000));
			} catch (Exception exc) { };
		} 
	}
	
	private void updateManagers(){
		field.update();
		if(!playing || pause)
			return;
		ball.update();
		left.update();
		right.update();
	}
	
	private void pause(){
		if(playing && !demo)
			pause = !pause;
		field.pause();
	}
	
	protected Ball getBall(){
		return ball;
	}
	
	protected Bar leftBar(){
		return left;
	}
	
	protected Bar rightBar(){
		return right;
	}
	
	@Override
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_P)
			pause();
		if(left instanceof PlayerBar){
			PlayerBar player = (PlayerBar)left;
			if(key == KeyEvent.VK_UP)
				player.up = true;
			if(key == KeyEvent.VK_DOWN)
				player.down = true;
		}
		if((demo || !playing) && key == KeyEvent.VK_ENTER)
			newGame();
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(left instanceof PlayerBar){
			PlayerBar player = (PlayerBar)left;
			if(key == KeyEvent.VK_UP)
				player.up = false;
			if(key == KeyEvent.VK_DOWN)
				player.down = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void goal(int i) {
		if(i == LEFT)
			rscore++;
		else
			lscore++;
		field.updateScore();
		if(lscore >= scoreToWin || rscore >= scoreToWin){
			if(demo)
				demo();
			else
				gameOver();
			return;
		}
		ball.init(i);
		left.init();
		right.init();
	}
}
