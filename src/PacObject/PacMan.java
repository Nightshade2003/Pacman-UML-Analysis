package PacObject;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;

import Constantes.Constante;
import Utilities.Direction;
import Utilities.State;
import Utilities.Utils;

public class PacMan extends AbstractCharacter{
	private int life;
	boolean bonus;

	public PacMan() {
		START = new Point(Constante.PAC_START.x,Constante.PAC_START.y);
		point = Utils.clonePoint(START);
		movement = new Movement();
		state = State.NORMAL;
		life = Constante.PAC_START_LIFE;
		baseColor = Color.yellow.darker();
		color = baseColor;
		bonus = false;
	}
	
	@Override
	public void move(int index) {
		this.checkToChangeDirection(index);
		
		int x = point.x;
		int y = point.y;
		
		if(movement.getCurrent() == Direction.UP) y-=velocity;
		else if(movement.getCurrent() == Direction.DOWN) y+=velocity;
		else if(movement.getCurrent() == Direction.LEFT) x-=velocity;
		else if(movement.getCurrent() == Direction.RIGHT) x+=velocity;
		
		if(checkBounds(x, y)) {
			if(collision(index, x, y)) return;
			point.x = x;
			point.y = y;
		}
		
		if(wraparound(x,y)) return;
	}
	
	public void manage() {
		if(unit > 0) {
			unit--;
			setColor(baseColor.brighter());
		}
		else {
			this.changeState(State.NORMAL);
			setColor(baseColor);
		}
	}
	
	public void specialState(State s) {
		this.changeState(s);
		this.unit = Constante.UNIT;
	}
	
	@Override
	public void back2Start() {
		super.back2Start();
		movement.setCurrent(Direction.NONE);
	}
	
	public void addLife() {
		if(bonus) return;
		life++;
		bonus = true;
	}
	public void loseLife() {
		life--;
	}
	private void changeState(State s) {
		this.state = s;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
	
	public void getKey(int key, int index) {
		if(key == KeyEvent.VK_UP) {this.movement.setNext(Direction.UP);}
		else if(key == KeyEvent.VK_DOWN) {this.movement.setNext(Direction.DOWN);}
		else if(key == KeyEvent.VK_LEFT) {this.movement.setNext(Direction.LEFT);}
		else if(key == KeyEvent.VK_RIGHT) {this.movement.setNext(Direction.RIGHT);}
		
		this.checkToChangeDirection(index);
	}
	
	public void resetBonus() {
		bonus = false;
	}

}
