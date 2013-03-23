package peretti.pong;

import java.awt.Color;

public class Ball extends Entity {	
	public static final double vInc = 15;
	
	public static final double l = 15;
	
	public static final byte LEFT = 0;
	public static final byte RIGHT = 1;
	
	private double v; //Pixels per second
	
	double startx;
	double starty;
	
	private double vx; //Used for moving the ball, it cause less computing
	private double vy;
	
	double minAngle = 20;
	double angle = 180 - 2 * minAngle;
	
	public Ball(int x, int y, GameManager gm, Color color){
		super(x, y, l, l, gm, color);
		startx = x;
		starty = y;
		init(LEFT);
	}
	
	public Ball(int x, int y, GameManager gm){
		this(x, y, gm, Color.WHITE);
	}
	
	public void init(int i){
		locx = startx;
		locy = starty;
		setVelocity(200);
		setRotation(170 + 180 * i);
	}
	
	double angle(){
		return Math.atan2(vy, vx);
	}
	
	void setRotation(double a){//In degrees
		a = Math.toRadians(a);
		vx = v * Math.cos(a);
		vy = v * Math.sin(a);
	}
	
	public void setVelocity(double ve){
		v = ve;
		updateVelocity();		
	}
	
	private void updateVelocity(){
		double r = angle();
		vx = v * Math.cos(r);
		vy = v * Math.sin(r);
	}
	
	private void setDirection(double rot, double ve){
		v = ve;
		setRotation(rot);
	}
	
	void update() {
		locx += vx * (1 / (double)GameManager.ticksPerSecond);
		locy += vy * (1 / (double)GameManager.ticksPerSecond);
		double a;
		if(locx < 0)
			gm.goal(0);
		else if(locx > gm.w)
			gm.goal(1);
		//
		else if(locy < 0){
			locy = -locy;
			vy *= -1;
		}
		else if(locy + height > gm.h){
			locy -= (gm.h - locy) / 2;
			vy *= -1;
		}
		//
		else if(vx < 0 && (a = gm.left.getPercCollision(locx, locx + width, locy, locy + height)) != -1){
			//System.out.println("left: a = " + a + " angle: " + ((90.0f - minAngle) - (1 - a) * (180.0f - 2 * minAngle)));
			//setVelocity(v + vInc);
			setDirection((90.0f - minAngle) - (1 - a) * (180.0f - 2 * minAngle), v + vInc);
			//vx *= -1;
			Bar.updateVelocity();
			return;
		}
		else if(vx > 0 && (a = gm.right.getPercCollision(locx, locx + width, locy, locy + height)) != -1){
			//System.out.println("right: a = " + a + " angle: " + ((90.0f + minAngle) + (1 - a) * (180.0f - 2 * minAngle)));
			//setVelocity(v + vInc); 
			setDirection((90.0f + minAngle) + (1 - a) * (180.0f - 2 * minAngle), v + vInc);
			//vx *= -1;
			Bar.updateVelocity();
			return;
		}
	}
}
