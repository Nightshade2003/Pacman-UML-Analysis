package Gui;

import java.awt.Color;
import java.awt.Point;


public class Block {
	protected Point point;
	protected Color color;
	protected int SIZE = 24;
	//protected State state;
	
	//constructors
	public Block() {
		point = new Point();
		color = Color.blue;
	}
	public Block(Point p, Color c) {
		point = new Point(p);
		color = c;
	}
	//getters
	public Point getPoint() {
		return point;
	}
	public Color getColor() {
		return color;
	}
	public int getSize() {
		return SIZE;
	}
	//setters
	public void setColor(Color c) {
		color = c;
	}
}
