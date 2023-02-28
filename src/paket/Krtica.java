package paket;

import java.awt.Color;
import java.awt.Graphics;

public class Krtica extends Zivotinja {

	public Krtica(Rupa r) {
		super(r);		
	}

	@Override
	public void udarena() {
		rupa.zaustavi();		
	}

	@Override
	public void pobegla() {
		rupa.getBasta().smanji();
		
	}

	@Override
	public void paint(Graphics g, int trenutni) {
		int x = rupa.getWidth() / (rupa.getKoraci() * 2);
		int y = rupa.getHeight() / (rupa.getKoraci() * 2);
		int x0 = rupa.getWidth() % (rupa.getKoraci() * 2);
		int y0 = rupa.getHeight() % (rupa.getKoraci() * 2);
		g.setColor(Color.GRAY);
		g.fillOval(rupa.getWidth() / 2 - trenutni * x - x0/2,rupa.getHeight() / 2 - trenutni * y - y0/2, x * trenutni * 2  +x0, y * trenutni * 2 +y0);	
		
	}

}
