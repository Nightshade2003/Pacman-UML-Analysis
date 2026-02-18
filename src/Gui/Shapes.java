package Gui;

enum Lines{
	HORIZONTAL,
	VERTICAL,
	TWO_HORIZONTAL,
	TWO_VERTICAL;
}

class Shapes{
	private Lines line;
	private int nbMin = 2; //how fat is the line
	private int nbMax = 5; //how long is the lines
	private int xplus = 0;
	private int yplus = 0;
	private int inBetween = 0; //distance between the two lines if two lines
	private int dilation = 1;
	private boolean normalDilation = true;
	
	public Shapes(Lines l, int min, int max, int xp, int yp, int inb, 
			int d, boolean nd) {
		line = l;
		nbMin = min;
		nbMax = max;
		xplus = xp;
		yplus = yp;
		inBetween = inb;
		dilation = d;
		normalDilation = nd;
	}
	public Shapes(Lines l, int min, int max, int xp, int yp, int inb) {
		line = l;
		nbMin = min;
		nbMax = max;
		xplus = xp;
		yplus = yp;
		inBetween = inb;
	}
	public Shapes(Lines l, int min, int max, int xp, int yp) {
		line = l;
		nbMin = min;
		nbMax = max;
		xplus = xp;
		yplus = yp;
	}
	public Shapes(Lines l, int min, int max) {
		line = l;
		nbMin = min;
		nbMax = max;
	}
	public Shapes(Lines l, int xp, int yp, int inb) {
		line = l;
		inBetween = inb;
		xplus = xp;
		yplus = yp;
	}
	
	
	public Lines getLines() {
		return line;
	}
	public int getNbMin() {
		return nbMin;
	}
	public int getNbMax() {
		return nbMax;
	}
	public int getInBetween() {
		return inBetween;
	}
	public boolean isNormalDilation() {
		return normalDilation;
	}
	public int getXplus() {
		return xplus;
	}
	public int getYplus() {
		return yplus;
	}
	public int getDilation() {
		return dilation;
	}
}