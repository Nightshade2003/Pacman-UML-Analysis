package Gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;

import javax.swing.JPanel;

import Constantes.Constante;
import PacObject.PacMan;
import Utilities.Direction;

@SuppressWarnings("serial")
public class Drawer extends JPanel{
	private final int s = Constante.BLOCK_SIZE;
	private Point pacmanPoint = new Point();
	private Point[] ghostPoint = new Point[Constante.NUMBER_OF_GHOST];
	private Color pacColor = Color.yellow;
	private Direction pacmanDirection;
	private int[][] blocks;
	private int[][] gomes;
	
	
	public Drawer(int[][] b, int[][] g) {
		this.setBackground(Color.white);
		updateMaps(b, g);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//Draw the Labyrinthe
		drawMap(g, s);
		//Draw the pacman
		drawPacman(g);
		//draw the pacghosts
		for(int i = 0; i < Constante.NUMBER_OF_GHOST;i++) {
			drawPacghost(g,i);
		}
	}
	
	public void drawPacman(Graphics g) {
		g.setColor(pacColor);
		if(pacmanDirection == Direction.UP) g.fillArc(pacmanPoint.x, pacmanPoint.y, s, s, 125, 290); //Up
		else if(pacmanDirection == Direction.DOWN) g.fillArc(pacmanPoint.x, pacmanPoint.y, s, s, 305, 290); //Down
		else if(pacmanDirection == Direction.LEFT) g.fillArc(pacmanPoint.x, pacmanPoint.y, s, s, 215, 290); //Left
		else g.fillArc(pacmanPoint.x, pacmanPoint.y, s, s, 35, 290); //Right
	}
	
	public void drawPacghost(Graphics g, int i) {
		g.setColor(Constante.GHOSTSCOLORS[i]);
		int x = ghostPoint[i].x, y = ghostPoint[i].y;
		int ex = ( x + 6*s/16 ), ey = (y+s/8);
		int pas = s/4;
		int[] xpoints = new int[]{x+4*(s/8), 
						2*(s/8)+x,
						s/8 + x,
						s/8 + x,
						2*(s/8) + x,
						3*(s/8) + x,
						4*s/8 + x,
						5*s/8 + x,
						6*s/8 + x,
						7*s/8 + x,
						7*s/8 + x,
						6*s/8 + x,
						};
		int[] ypoints = new int[]{y,
						y+s/4,
						y+s/2,
						y+3*s/4,
						y+s,
						y+3*s/4,
						y+s,
						y+3*s/4,
						y+s,
						y+3*s/4,
						y+s/2,
						y+s/4
						};
		Polygon poly = new Polygon(xpoints, ypoints, xpoints.length);
		g.fillPolygon(poly);
		
		g.setColor(Color.black);
		g.fillOval(ex, ey, pas, pas);
	}
	
	public void drawMap(Graphics g, int size) {
		double scale = 0.3;
		for(int i = 0; i < blocks.length; i++) {
			for(int j = 0; j < blocks[i].length; j++) {
				//Blocks
				if(blocks[i][j] == 1) {
					g.setColor(Color.black);
					g.fill3DRect(j*size, i*size, size, size, true);
				}else if(blocks[i][j] == 3) {
					g.setColor(Color.red);
					g.drawLine(j*size, i*size, (j+1)*size, i*size);
				}
				//Pacgomes
				int v = gomes[i][j];
				if(v != 0) {
					scale = Constante.SCALES[v-1];
					g.setColor(Constante.GOMESCOLORS[v-1]);
					g.fillOval((int) ((j+0.25)*size-scale), (int) ((i+0.25)*size-scale), (int) (size*scale), (int) (size*scale));
				}
			}
		}
	}
	
	public void updateMaps(int[][] b, int[][] g) {
		blocks = b;
		gomes = g;
	}
	

	public int getBlockSize() {
		return s;
	}
	
	public void setPacmanFeatures(PacMan pac) {
		pacmanPoint = pac.getPoint();
		pacmanDirection = pac.getMovement().getCurrent();
		pacColor = pac.getColor();
	}
	
	public void setPacghostPoint(int i, Point p, Color c) {
		ghostPoint[i] = p;
		Constante.GHOSTSCOLORS[i] = c;
		
	}
	
}