package peretti.pong;

import java.awt.Color;
import java.awt.Graphics;

public class BillboardNumber extends Entity{
	
	public static final int pixDim = 10; //px
	public static final int dist = 1; //px
	
	public static final int width = pixDim * 3 + dist * 2;
	public static final int height = pixDim * 5 + dist * 4;
	
	public static final boolean off = false;
	public static final boolean on = true;
	
	protected Color colorOn;
	protected Color colorOff;
	
	public static final boolean[] one = { 
		off, on, on,
		off, off, on,
		off, off, on,
		off, off, on,
		off, off, on
};

	public static final boolean[] two ={
		on, on, on,
		off, off, on,
		on, on, on,
		on, off, off,
		on, on, on
};

	public static final boolean[] three = {
		on, on, on,
		off, off, on,
		on, on, on,
		off, off, on,
		on, on, on
};
	
	public static final boolean[] four = {
		on, off, on,
		on, off, on,
		on, on, on,
		off, off, on,
		off, off, on
};
	public static final boolean[] five = {
		on, on, on,
		on, off, off,
		on, on, on,
		off, off, on,
		on, on, on
};
	public static final boolean[] six = {
		on, on, on,
		on, off, off,
		on, on, on,
		on, off, on,
		on, on, on
};
	public static final boolean[] seven = { 
		on, on, on,
		off, off, on,
		off, off, on,
		off, off, on,
		off, off, on
};
	public static final boolean[] eigth = {
		on, on, on,
		on, off, on,
		on, on, on,
		on, off, on,
		on, on, on
};
	public static final boolean[] nine = {
		on, on, on,
		on, off, on,
		on, on, on,
		off, off, on,
		on, on, on
};
	public static final boolean[] zero = {
		on, on, on,
		on, off, on,
		on, off, on,
		on, off, on,
		on, on, on
};

	Entity squares[] = new Entity[15];

	BillboardNumber(int x, int y, GameManager gm, Color on, Color off) {
		super(x, y, 3 * pixDim + 2 * dist, 5 * pixDim + 4 * dist, gm, on);
		colorOn = on;
		colorOff = off;
		for(int i = 0; i < 15; i++){
			squares[i] = new Entity((int)locx + (i % 3) * (pixDim + dist), (int)locy + (i / 3) * (pixDim + dist), pixDim, pixDim, gm, off);
		}
		updateValue(0);
	}
	
	public void init(){
	}
	
	public void updateValue(int v){
		v = v % 10;
		boolean number[] = zero;
		switch(v){
		case 1:
			number = one;
			break;
		case 2:
			number = two;
			break;
		case 3:
			number = three;
			break;
		case 4:
			number = four;
			break;
		case 5:
			number = five;
			break;
		case 6:
			number = six;
			break;
		case 7:
			number = seven;
			break;
		case 8:
			number = eigth;
			break;
		case 9:
			number = nine;
			break;		
		}
		for(int i = 0; i < 15; i++){
			if(number[i])
				squares[i].setColor(colorOn);
			else
				squares[i].setColor(colorOff);
		}
	}
	
	public void paint(Graphics g){
		for(int i = 0; i < 15; i++)
			squares[i].paint(g);
	}
	
	public void update(){
	}

}
