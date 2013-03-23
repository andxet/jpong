package peretti.pong;

import java.awt.Color;
import java.awt.Graphics;

public class Entity {
	protected double locx, locy;
	protected double width, height;
	protected Color c;

	protected GameManager gm;
	
	Entity(double x, double y, double w, double h, GameManager gm, Color c){
		locx = x;
		locy = y;
		width = w;
		height = h;
		this.c = c;
		this.gm = gm;
	}
	
	void paint(Graphics g){
		g.setColor(c);
		g.fillRect((int)locx, (int)locy, (int)width, (int)height);
	}
	
	void update(){}
	
	void setColor(Color color){
		c = color;
	}
}
