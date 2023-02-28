package paket;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Igra extends Frame {

	private static Igra igra = null;
	private Basta basta;
	private Label povrceLB;
	private Button igraj;
	private boolean radi = false;
	
	private Igra() {
		setBounds(700, 200, 450, 400);
		setTitle("Igra");
		
		populate();
		azuriraj();
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				basta.zaustavi();
				dispose();				
			}
		});
	}
	
	public static Igra napraviIgru() {
		if (igra == null) igra = new Igra();
		return igra;
	}

	private void populate() {
		basta = new Basta(this, 4, 4);
		add(basta, BorderLayout.CENTER);
		
//MENI SA STRANE		
		Panel east = new Panel(new BorderLayout());
		Font font = new Font("Arial", Font.BOLD, 12);
		
		Panel tezina = new Panel(new GridLayout(6, 1));
		Label l1 = new Label("     Tezina:     ");
		l1.setFont(font);
		tezina.add(l1);
		
		CheckboxGroup grupa = new CheckboxGroup();

		Checkbox lako = new Checkbox("Lako", true, grupa);
		Checkbox srednje = new Checkbox("Srednje", false, grupa);
		Checkbox tesko = new Checkbox("Tesko", false, grupa);
		tezina.add(lako);
		tezina.add(srednje);
		tezina.add(tesko);
		tezina.add(new Label(""));
		
		igraj = new Button("Igraj");
		tezina.add(igraj);
		
		povrceLB = new Label("Povrce:");
		povrceLB.setFont(font);
		
		east.add(tezina, BorderLayout.NORTH);
		east.add(povrceLB,BorderLayout.CENTER);
		
		add(east, BorderLayout.EAST);
		
		
		
		igraj.addActionListener((e)->{
				if (!radi) {
					igraj.setLabel("Stop");
					int koraci = 0;
					int cekanje = 0;
					if (lako.getState()) {cekanje = 1000; koraci = 10;}
					if (srednje.getState()) {cekanje = 750; koraci = 8;}
					if (tesko.getState()) {cekanje = 500; koraci = 6;}
					basta.setBrKoraka(koraci);
					basta.setInterval(cekanje);
					
					basta.pokreni();
					basta.setPovrce(100);
					azuriraj();
				}else {
					igraj.setLabel("Igraj");
					basta.zaustavi();
				}
				radi = !radi;				
			});
	}
	
	public void azuriraj() {
		povrceLB.setText("Povrce: " + basta.getPovrce());
		povrceLB.revalidate();
		if (basta.getPovrce() <= 1 ) {
			basta.zaustavi();
			radi = false;
			igraj.setLabel("Igraj");
		}
		
	}

//FUNKCIJA MAIN
	public static void main(String[] arg) {
		new Igra();
	}


}
