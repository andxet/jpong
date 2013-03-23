package peretti.pong;

import java.awt.Color;

public class PlayerBar extends Bar{
	
	boolean up = false;
	boolean down = false;
	
	public PlayerBar(int x, int y, GameManager gm, Color color){
		super(x, y, gm, color);
	}
	
	public PlayerBar(int x, int y, GameManager gm){
		this(x, y, gm, Color.WHITE);
	}	
	
	public void update(){
		if(up)
			up();
		else if(down)
			down();
	}
}
