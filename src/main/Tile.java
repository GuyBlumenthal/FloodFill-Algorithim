package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

public class Tile implements MouseListener {

	public static final int SIDE_LENGTH = 60;
	private static final int BORDER_THICKNESS = 2;
	
	protected int x, y;
	TileStyle tileStyle;	
	
	public Tile (int x, int y) {
		this.x = x;
		this.y = y;
		
		this.tileStyle = TileStyle.DefaultTile;
	}
	
	public void display(Graphics2D g2d) {
		tileStyle.display(g2d, this);
	}
	

	private enum TileStyle {
		DefaultTile(Color.WHITE), Wall(Color.BLACK);
		
		private Color c;
		
		private TileStyle(Color c) {
			this.c = c;
		}
		
		protected void display (Graphics2D g2d, Tile t) {
			g2d.setColor(this.c);
			g2d.setStroke(new BasicStroke(BORDER_THICKNESS));
			g2d.fillRect(t.x, t.y, SIDE_LENGTH, SIDE_LENGTH);
		}
				
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e)) {
			this.tileStyle = TileStyle.Wall;
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
