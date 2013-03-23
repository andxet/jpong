package peretti.pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.File;


public class MessageBox extends Entity {

	protected static String fontName = new String("../res/Arcade.ttf");
	protected static float fontDimension = 100;
	protected static int bounds = 7;
	protected static int thick = 4;
	protected static int stxy = bounds + thick;

	protected String s;
	//protected int top = 30;
	protected Color fcolor;
	static Font messageFont;
	
	boolean blink;
	boolean blinkOn = false;
	public static final double blinkRate = 0.4f; //time to switch on/off when blinking
	double timeBlink = - 15 / GameManager.ticksPerSecond;
	

	MessageBox(String toShow, int ypos, boolean blink, GameManager gm, Color rectColor, Color fontColor) {
		super(gm.w / 2, ypos, 0, 0, gm, rectColor);
		//top = ypos;
		this.blink = blink;
		s = toShow;
		fcolor = fontColor;
		loadFont();
		setDim();
	}

	private void setDim() {
		FontMetrics fm = gm.getGraphics().getFontMetrics(messageFont);
		locx = gm.w / 2 - fm.stringWidth(s) / 2 - stxy;
		width = fm.stringWidth(s) + stxy * 2;
		height = fm.getHeight() + stxy * 2 - 17;
	}

	void loadFont(){
		try {
			File f = new File (fontName); 
			messageFont = Font.createFont(Font.TRUETYPE_FONT, f);
			messageFont = messageFont.deriveFont(fontDimension);
		} catch (Exception ex) {
			System.err.println(fontName + " not loaded.  Using serif font.\n" + new File(".").getAbsolutePath());
			messageFont = new Font("helvetica", Font.PLAIN, (int)fontDimension);
		}
	}

	public void paint(Graphics g){
		if(!blink){
			g.setColor(c);
			g.fillRect((int)locx, (int)locy, (int)width, (int)height - 14);
			g.setColor(Color.black);
			g.fillRect((int)locx + thick, (int)locy + thick, (int)width - 2 * thick, (int)height - 2 * thick - 14);
		}
		if(!blink || (blink && blinkOn)){
		g.setColor(fcolor);
		g.setFont(messageFont);
		g.drawString(s, (int)(locx + stxy), (int)(locy + (stxy + height) - 17));
		}
	}
	
	public void update(){
		if(blink){
			timeBlink += blinkRate / GameManager.ticksPerSecond;
			if(timeBlink > blinkRate){
				timeBlink -= blinkRate;
				blinkOn = !blinkOn;
			}
		}
	}
}
