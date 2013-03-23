package peretti.pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class HalfField extends Entity{
	
	public static final int tickness = 4;//px
	
	public HalfField(GameManager gm, Color color){
		super(gm.w / 2 - tickness / 2, 0, tickness, gm.h, gm, color);
	}

	void update() {
		// TODO Auto-generated method stub
		
	}
}
