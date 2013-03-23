package peretti.pong;

import java.awt.Color;
import java.awt.Graphics;

public class Field {
	
	Color back = Color.BLACK;
	Color field = new Color(70, 70, 70);
	Color offLeds = new Color(20, 20, 20);
	
	HalfField half;
	ScoreBillboard left, rigth;
	MessageBox message;
	MessageBox info;
	
	public static final int distFromCenter = 20;
	
	GameManager gm;
	int w, h;
	
	public Field(GameManager gm, int w, int h){
		this.gm = gm;
		this.w = w;
		this.h = h;
		half = new HalfField(gm, field);
		left = new ScoreBillboard(gm.w / 2 - ScoreBillboard.width - distFromCenter, (gm.h - ScoreBillboard.height) / 2, gm, field, offLeds);
		rigth = new ScoreBillboard(gm.w / 2 + distFromCenter, (gm.h - ScoreBillboard.height) / 2, gm, field, offLeds);
	}

	public void update(){
		left.update();
		rigth.update();
		if(message != null)
			message.update();
		if(info != null)
			info.update();
	}
	
	public void paint(Graphics g){
		g.setColor(back);
		g.drawRect(0, 0, w, h);
		half.paint(g);
		left.paint(g);
		rigth.paint(g);
		if(message != null)
			message.paint(g);
		if(info != null)
			info.paint(g);
	}

	public void updateScore() {
		left.updateScore(gm.lscore);
		rigth.updateScore(gm.rscore);
	}

	public void GameOver() {
		String s = null;
		if(gm.lscore > gm.rscore)
			s = "LEFT WINS!";
		else
			s = "RIGHT WINS!";
		message = new MessageBox(s, 30, false, gm, field, field);
		info = new MessageBox("Press Enter", gm.h - 120, true, gm, field, field);
	}
	
	public void NewGame(){
		updateScore();
		message = null;
		info = null;
	}
	
	public void demo(){
		updateScore();
		if(message == null) message = new MessageBox("PONG", 30, false, gm, field, field);;
		if(info == null)info = new MessageBox("Press Enter", gm.h - 120, true, gm, field, field);
	}

	public void pause() {
		if(gm.pause)
			info = new MessageBox("PAUSE", gm.h - 120, true, gm, field, field);
		else
			info = null;
	}
}
