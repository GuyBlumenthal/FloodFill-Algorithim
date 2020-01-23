package main;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Main {

	static Window window;
	
	static Timer timer;
	
	static Tile t;
	
	public static void startProgram () {
		window = new Window("Flood Fill Algorithim");
		
		t = new Tile (20, 20);
		
		window.addMouseListener(t);
		
		timer = new Timer(20, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.repaint();
			}
		});
		timer.start();
		
	}
	
	public static void display (Graphics2D g2d) {
		t.display(g2d);
	}
	
	public static void main(String[] args) {
		startProgram();
	}
	
}
