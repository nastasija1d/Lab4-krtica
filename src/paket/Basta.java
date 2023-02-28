package paket;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;

public class Basta extends Panel implements Runnable {
	
	
	private Igra igra;
	private int x = 4, y = 4;
	private Rupa[][] mreza;
	private ArrayList<Rupa> slobodna = new ArrayList<>();
	private int povrce = 100;
	private Thread nit;
	private int interval = 1000;

	public Basta(Igra igr,int x, int y) {
		this.igra = igr;
		this.x = x;
		this.y = y;
		setBackground(Color.decode("#00cc00"));
		this.setLayout(new GridLayout(x, y, 15, 15));
		mreza = new Rupa[x][y];
		nit = new Thread(this);
		populate();
	}

	
	private void populate() {
		
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				Rupa r = new Rupa(this);
				mreza[i][j] = r;
				add(r);
				slobodna.add(r);
			}
		}
	}
	
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				Thread.sleep(interval);
				int xb, yb;
				do {
					xb = (int) (Math.random() * x);
					yb = (int) (Math.random() * y);
				}while(mreza[xb][yb].isactive());
				mreza[xb][yb].setZivotinja(new Krtica(mreza[xb][yb]));
				mreza[xb][yb].napravi();
				mreza[xb][yb].pokreni();
				interval = (int) (interval * 0.99);
			}
	} catch (Exception e) {}
		
	}

	
	public void setBrKoraka(int k) {
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				mreza[i][j].setKoraci(k);
			}
		}
	}
	
	public int getBrKoraka() {
		return mreza[0][0].getKoraci();
	}

	public int getPovrce() {
		return povrce;
	}

	public synchronized void setPovrce(int povrce) {
		this.povrce = povrce;
	}

	public void pokreni() {
		nit = new Thread(this);
		nit.start();
	}
	
	public void zaustavi() {
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				mreza[i][j].zaustavi();
			}
		}
		nit.interrupt();
	}
	
	public synchronized void smanji() {
		povrce--;
		igra.azuriraj();
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}
	
	
}
