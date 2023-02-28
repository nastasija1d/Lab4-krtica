package paket;

import java.awt.Graphics;

public abstract class Zivotinja {
	
	protected Rupa rupa;
	protected int procenat;

	public Zivotinja(Rupa r) {
		rupa = r;
	}
	
	public abstract void udarena(); 
	
	public abstract void pobegla();
	
	public abstract void paint(Graphics g, int t);
}
