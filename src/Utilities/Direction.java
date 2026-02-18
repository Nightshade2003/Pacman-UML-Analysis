package Utilities;

public enum Direction {
	
	NONE(0,0),
	UP(0, -Constantes.Constante.BLOCK_SIZE),
	DOWN(0, Constantes.Constante.BLOCK_SIZE),
	LEFT(-Constantes.Constante.BLOCK_SIZE, 0),
	RIGHT(Constantes.Constante.BLOCK_SIZE, 0);

	
	private int x;
	private int y;

	private Direction(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
	    return x;
	}

	public int getY() {
	    return y;
	}	

	@Override
	public String toString() {
		return "(" + x + ", " + y +")";
	}
}
		
