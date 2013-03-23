package peretti.pong;

import java.awt.Color;

public class Bar extends Entity{
	protected static final int WIDTH = 20; //px
	protected static final int HEIGHT = 130; //px
	protected static final int startVelocity = 150;
	protected static int velocity = startVelocity; //pixels per seconds
	
	static double dVelocity = startVelocity;
	public static final double vInc = 5;
	
	protected int maxy; //field dimension (heigth)
	
	public Bar(int x, int y, GameManager gm, Color color){
		super(x, y, WIDTH, HEIGHT, gm, color);
		dVelocity = (double) velocity / GameManager.ticksPerSecond;
		maxy = gm.h;
	}
	
	public void init(){
		dVelocity = startVelocity / GameManager.ticksPerSecond;
	}
	
	public void move(double y){
		if(Math.abs(y) > dVelocity)
			if(y > 0)
				y = dVelocity;
			else
				y = -dVelocity;
		locy += y;
		if(locy < 0)
			locy = 0;
		if(locy + height > maxy)
			locy = maxy - height;
	}
	
	public void up(){
		move(-dVelocity);
	}
	
	public void down(){
		move(+dVelocity);
	}
	
	public boolean collision(double x1, double x2, double y1, double y2){
		return (x2 >= locx) && (locx + width >= x1) && (y2 >= locy)	&& (locy + height >= y1);
	}
	/*Get a percentual of the bar up of the point of collision y:
	// 0%						 100%
	// |__________________________|
	// y1   y2
	// |_____|
	*/
	public double getPercCollision(double x1, double x2, double y1, double y2){
		if(!collision(x1, x2, y1, y2))
			return -1;
		double y = (y1 + y2) / 2 - locy; //Get the y in function of the bar, not the screen
		if(y < 0) y = 0;
		if(y > height) y = height;
		System.out.println(1 / height * y);
		return 1 / height * y;		
	}

	@Override
	void update() {
		// TODO Auto-generated method stub
		
	}

	public static void updateVelocity() {
		dVelocity += vInc / GameManager.ticksPerSecond;
	}
	
	public static void goal(){
	}
}
