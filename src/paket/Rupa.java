package paket;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Rupa extends Canvas implements Runnable {
	
	private Basta basta;
	private Zivotinja zivotinja;
	private Thread nit = new Thread(this);
	private int koraci = 10;
	private int trenutni = 0;
	private boolean radi = false;
	
	public Rupa(Basta b) {
		basta = b;
		setBackground(Color.decode("#654321"));
		nit = new Thread(this);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(zivotinja != null) {
					zivotinja.udarena();
				}
			}
		});
	}
	
	@Override
	public void paint(Graphics g) {	
		if (zivotinja != null) zivotinja.paint(g, trenutni);
	}
	
	@Override
	public void run() {
		try {	
			while (!Thread.interrupted()){
				synchronized (this) {
					while (radi == false) wait();
				}
				try {
					for (trenutni = 1; trenutni < koraci; trenutni++) {
						Thread.sleep(100);
						repaint();
					}
					Thread.sleep(2000);
				} catch (Exception e) {
					repaint();
				}
				if(zivotinja != null) zivotinja.pobegla();
				zaustavi();
				repaint();
			}
		}catch (InterruptedException e) {return;}		

	}
	
	
	public Zivotinja getZivotinja() {
		return zivotinja;
	}

	public void setZivotinja(Zivotinja zivotinja) {
		this.zivotinja = zivotinja;
	}

	public synchronized void napravi() {
		nit = new Thread(this);
	}
	
	public synchronized void pokreni() {
		radi = true;
		nit.start();
	}
	
	public synchronized boolean isactive() {
		return radi;
	}
	
	public synchronized void zaustavi() {
		nit.interrupt();
		radi = false;
		zivotinja = null;
	}

	public void setKoraci(int koraci) {
		this.koraci = koraci;
	}
	
	public int getKoraci() {
		return koraci;
	}
	
	public Basta getBasta() {
		return basta;
	}
	
	
	
	

}
