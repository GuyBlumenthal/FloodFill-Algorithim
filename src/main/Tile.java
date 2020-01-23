package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingUtilities;

public class Tile implements MouseListener, MouseMotionListener {

	public static final int SIDE_LENGTH = 60;
	private static final int BORDER_THICKNESS = 5;
	
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
	
	public Rectangle getHitbox () {
		return new Rectangle(this.x * SIDE_LENGTH, this.y * SIDE_LENGTH, SIDE_LENGTH, SIDE_LENGTH);
	}

	public void fill () {
		this.tileStyle = TileStyle.Filled;
	}
	
	public boolean isFilled () {
		return !(this.tileStyle == TileStyle.DefaultTile);
	}
	
	private enum TileStyle {
		DefaultTile(Color.WHITE), Wall(Color.BLACK), Filled (Color.GREEN);
		
		private Color c;
		
		private TileStyle(Color c) {
			this.c = c;
		}
		
		protected void display (Graphics2D g2d, Tile t) {
			g2d.setColor(this.c);
			g2d.setStroke(new BasicStroke(BORDER_THICKNESS));
			g2d.fillRect(t.x * SIDE_LENGTH, t.y * SIDE_LENGTH, SIDE_LENGTH, SIDE_LENGTH);
		}
				
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e)) {
			if (getHitbox().contains(e.getPoint())) {
				this.tileStyle = TileStyle.Wall;
			}
		} else if(SwingUtilities.isRightMouseButton((e))) {
			if (getHitbox().contains(e.getPoint())) {
				this.tileStyle = TileStyle.DefaultTile;
			}
		} else if (SwingUtilities.isMiddleMouseButton(e)) {
			if (getHitbox().contains(e.getPoint())) {
				Main.commenceFloodFill(this);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e)) {
			if (getHitbox().contains(e.getPoint())) {
				this.tileStyle = TileStyle.Wall;
			}
		} else if(SwingUtilities.isRightMouseButton((e))) {
			if (getHitbox().contains(e.getPoint())) {
				this.tileStyle = TileStyle.DefaultTile;
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}
	
}
