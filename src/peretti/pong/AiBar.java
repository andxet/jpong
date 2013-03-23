package peretti.pong;

import java.awt.Color;

public class AiBar extends Bar{
	
	Ball ball;
	double bally;
	double ballx;
	boolean ballGoingUp = true;
	boolean ballGoingToMe = true;
	
	int rangePercent = 60; //Range in which the bar will not move if the ball is in
	double ry0, ry1; //values of the range in the bar
	
	public AiBar(int x, int y, GameManager gm, Color color){
		super(x, y, gm, color);
		ry0 = (double) height / 100 * ((100 - rangePercent)/2);
		ry1 = (double) height / 100 * rangePercent + ry0;
		ballx = 0;
		bally = gm.h;
	}
	
	public AiBar(int x, int y, GameManager gm){
		this(x, y, gm, Color.WHITE);
	}
	
	public void update(){
		ball = gm.getBall();
		updateBallStatus();
		if(ballGoingToMe)
			updateToBall();
		else
			updateRelax();
	}
	
	private void updateBallStatus(){
		if((ballx > ball.locx && ballx > locx) || (ballx < ball.locx && ballx < locx))
			ballGoingToMe = true;
		else
			ballGoingToMe = false;
		
		if(ball.locy > bally)
			ballGoingUp = false;
		else
			ballGoingUp = true;
		ballx = ball.locx;
		bally = ball.locy;
	}
	
	private void updateRelax(){
		if(locy + height / 2 > gm.h / 2 + 5)
			up();
		else if (locy + height / 2 < gm.h / 2 - 5)
			down();
	}
	
	private void updateToBall(){
		double by = (ball.height / 2 + ball.locy);
		if(ballGoingUp){
			if(by < locy + ry1){
				up();
				if(locy + ry1 < by)
					locy = by - ry1;
			}
			else{
				down();
				if(locy + ry1 > by)
					locy = by - ry1;
			}
		}
		else{
			if(by > locy + ry0){
				down();
				if(locy + ry0 > by)
					locy = by - ry0;
			}
			else{
				up();
				if(locy + ry0 < by)
					locy = by - ry0;
			}
		}
	}
}