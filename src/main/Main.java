package main;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class Main {

	static Window window;
	
	static Timer timer;
	
	static ArrayList<ArrayList<Tile>> grid;
	
	public static void startProgram () {
		window = new Window("Flood Fill Algorithim");
		
		grid = new ArrayList<ArrayList<Tile>>();
		
		resizeGrid();
		
		timer = new Timer(20, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.repaint();
				resizeGrid();
			}
		});
		timer.start();
		
	}
	
	public static void resizeGrid () {
		
		int width = window.getWidth();
		int height = window.getHeight();
		
		int reqCols = width / Tile.SIDE_LENGTH + 1;
		int reqRows = height / Tile.SIDE_LENGTH + 1;
		
		while (grid.size() != reqCols) {
			if (grid.size() < reqCols) {
			grid.add(new ArrayList<Tile>());
			} else {
				grid.remove(grid.size() - 1);
			}
		}
		
		for (int colIndex = 0; colIndex < reqCols; colIndex++) {
			if (grid.get(colIndex) == null) {
				grid.set(colIndex, new ArrayList<Tile>());
			}
			ArrayList<Tile> col = grid.get(colIndex);
			while (col.size() != reqCols) {
				if (col.size() < reqCols) {
					Tile newTile = new Tile(colIndex, col.size());
					window.addMouseListener(newTile);
					window.addMouseMotionListener(newTile);
					col.add(newTile);
				} else {
					col.remove(col.size() - 1);
				}
			}
		}
		
	}
	
	public static void commenceFloodFill (Tile startTile) {
		
		grid.get(startTile.x).get(startTile.y).fill();
		
		for (Tile t : getNeighbours(startTile)) {
			perpetuateFill(t);
		}
	}
	
	public static void perpetuateFill (Tile t) {
		ArrayList<Tile> neighbours = getNeighbours(t);
		if (neighbours.size() != 0) {
			for (Tile newT : neighbours) {
				newT.fill();
				perpetuateFill(newT);
			}
		}
	}
	
	public static ArrayList<Tile> getNeighbours (Tile t) {
		
		ArrayList<Tile> neighbours = new ArrayList<Tile>();
		
		if (t.x > 0) {
			addTileToFill(grid.get(t.x - 1).get(t.y), neighbours);
		}
		
		if (t.y > 0) {
			addTileToFill(grid.get(t.x).get(t.y-1), neighbours);
		}
		
		if (t.x < grid.size() - 2) {
			addTileToFill(grid.get(t.x + 1).get(t.y), neighbours); 
		}
		
		if (t.y < grid.get(t.x).size() - 2) {
			addTileToFill(grid.get(t.x).get(t.y + 1), neighbours);
		}
		
		return neighbours;
		
	}
	
	public static void addTileToFill (Tile t, ArrayList<Tile> n) {
		if (!t.isFilled()) {
			n.add(t);
		}
	}
	
	public static void display (Graphics2D g2d) {
		for (ArrayList<Tile> list : grid) {
			for (Tile t : list) {
				t.display(g2d);
			}
		}
	}
	
	public static void main(String[] args) {
		startProgram();
	}
	
}
