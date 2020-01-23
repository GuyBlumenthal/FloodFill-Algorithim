package main;

import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Window extends JFrame {
	
	WindowPanel panel;

	public Window(String title) {

		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);

		pack();

		panel = new WindowPanel();

		addKeyListener(panel);

		add(panel);

		setLocationRelativeTo(null);
		
	}
	
	@Override
	public synchronized void addMouseListener(MouseListener l) {
		panel.addMouseListener(l);
	}
	
	@Override
	public synchronized void addMouseMotionListener(MouseMotionListener l) {
		panel.addMouseMotionListener(l);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(600, 400);
	}

}
