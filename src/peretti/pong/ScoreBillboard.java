package peretti.pong;

import java.awt.Color;
import java.awt.Graphics;

public class ScoreBillboard extends Entity{
	public static final int distance = 3;
	
	int score = 0;
	
	public static final int width = BillboardNumber.width * 2 + distance;
	public static final int height = BillboardNumber.height;
	
	BillboardNumber dec, unit;
	
	ScoreBillboard(int x, int y, GameManager gm, Color c, Color d) {
		super(x, y, width, height, gm, c);
		dec = new BillboardNumber(x, y, gm, c, d);
		unit = new BillboardNumber(x + BillboardNumber.width + distance, y, gm, c, d);
		init();
	}
	
	void init(){
		dec.init();
		unit.init();
	}
	
	void updateScore(int s){
		dec.updateValue(s / 10);
		unit.updateValue(s % 10);
	}
	
	void update(){
		dec.update();
		unit.update();
	}
	
	void paint(Graphics g) {
		dec.paint(g);
		unit.paint(g);
	}
	
}
