package PacObject;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import Constantes.Constante;
import Utilities.Direction;
import Utilities.State;
import Utilities.Utils;


public abstract class AbstractCharacter {
	protected Point point; //position on the map
	protected Movement movement  = new Movement(); //contains current and next position
	protected State state; //state of the object (normal, super, weak)
	protected int velocity = Constante.STD_VELOCITY; //speed of the character
	protected Point START; //starting point on the map
	protected Color color; //current color of the character
	protected Color baseColor; //base color
	int unit = 0; //it defines the duration of an effect 
	
	
	public abstract void move(int index);
	public abstract void manage();
	
	public void setColor(Color c) {
		color = c;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void checkToChangeDirection(int index) {
		if(movement.getNext() != Direction.NONE) {
			int x = point.x;
			int y = point.y;
			Direction d = movement.getNext();
			
			if(!collision(index, x+d.getX(), y+d.getY())) {
				movement.setCurrent(d);
				movement.setNext(Direction.NONE);
			}
			
		}
	}
	
	public void askToChangeDirection(Direction d) {
		movement.setNext(d);
	}
	
	public boolean checkBounds(int x, int y) {
		return (x >= 0) && (y >= 0) && (x < Constante.DIMENSION.width) && (y < Constante.DIMENSION.height);
	}
	
	public boolean collision(int index, int x, int y) {		
		if(!checkBounds(x,y)) return true;
		
		int i = x/Constante.BLOCK_SIZE;
		int j = y/Constante.BLOCK_SIZE;
		
		if(Constante.blocksMaps[index][j][i] == 0 || (Constante.blocksMaps[index][j][i] != 1 && movement.getCurrent() == Direction.UP)) {
			return false;
		}
		
		return true;
	}
	
	public boolean wraparound(int x, int y) {
		for(int i = 0; i < 2; i++) {
			if(x == Constante.STATIC_WRAPAROUND[i].x && y == Constante.STATIC_WRAPAROUND[i].y) {
				point = Utils.clonePoint(Constante.DYNAMIC_WRAPAROUND[(i+1) % 2]);
				return true;
			}
		}
		return false;
	}
	
	public int[] getMatrixPosition() {
		return new int[]{point.x/Constante.BLOCK_SIZE, point.y/Constante.BLOCK_SIZE};
	}
	
	public Point getPoint() {
		return point;
	}
	
	public void back2Start() {
		point = Utils.clonePoint(START);
	}
	public Movement getMovement() {
		return movement;
	}
	public State getState() {
		return state;
	}
	
	
	public void setPoint(Point p) {
		this.point = p;
	}
	public void setMovement(Movement m) {
		this.movement = m;
	}
	public void setState(State s) {
		this.state = s;
	}
	
	
	
	public class Movement{
		private Direction current;
		private Direction next;
		
		public Movement() {
			current = Direction.NONE;
			next = Direction.NONE;
		}
		
		public Direction getCurrent() {
			return current;
		}
		public Direction getNext() {
			return next;
		}
		
		public void setCurrent(Direction c) {
			this.current = c;
		}
		public void setNext(Direction n) {
			this.next = n;
		}
	}

}
