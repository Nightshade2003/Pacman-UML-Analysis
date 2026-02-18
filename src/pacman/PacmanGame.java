package pacman;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import Constantes.Constante;
import Gui.Maze;
import PacObject.PacGhost;
import PacObject.PacMan;
import Utilities.State;
import Utilities.Utils;


public class PacmanGame {
	int score;
	int mapIndex;
	private PacMan pacman = new PacMan();
	private PacGhost[] ghosts = new PacGhost[Constante.NUMBER_OF_GHOST];
	private int[][] blocksMatrix;
	private int[][] gomesMatrix;
	Maze maze;
	public KeyListener listener = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			super.keyPressed(e);
			int key = e.getKeyCode();
			pacman.getKey(key, mapIndex);
		}
	};
	
	public PacmanGame(){
		for(int i = 0; i < Constante.NUMBER_OF_GHOST; i++) {
			ghosts[i] = new PacGhost( Utils.clonePoint(Constante.GHOSTS_START[i]), Constante.GHOSTSCOLORS[i]);
		}
		totalReset();
		maze = new Maze(Utils.clone2DMatrix(blocksMatrix), Utils.clone2DMatrix(gomesMatrix), listener,
				pacman, ghosts);
	}
	
	public void play() {
		while(!noMoreGomes() && !noMoreLife()) {
			maze.setGhostsPoint(ghosts);
			maze.updateMaps(Utils.clone2DMatrix(blocksMatrix), Utils.clone2DMatrix(gomesMatrix));
			maze.show(pacman, score, pacman.getLife());
			pacman.manage();
			manageGhosts();
			pacman.move(mapIndex);
			updateAll();
			moveGhosts();
			updateAll();
		}
		if(noMoreGomes()) congrats("Congrats !!! You've won the game !!!!");
		else congrats("You're a loserrrrr !");
		ask2Play("");
	}
	
	public void ask2Play(String msg) {
		String response = JOptionPane.showInputDialog(null, msg+"Play again ?\n y: yes  n: no");
		System.out.println("Response : " + response);
		if(response.equals("y")) {
			totalReset();
			play();
		}else if(response.equals("n")) {
			quit();
		}else {
			ask2Play("Didn't get ypur response.\n");
		}
	}
	public void congrats(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Infos", JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	public void totalReset() {
		mapIndex = 0;
		score = 0;
		pacman.setLife(Constante.PAC_START_LIFE);
		partialReset();
		resetMaps();
	}
	
	public void partialReset() {
		pacman.back2Start();
		for(int i = 0; i < Constante.NUMBER_OF_GHOST; i++) {
			ghosts[i].back2Start();
		}
	}

	private void resetMaps() {
		blocksMatrix = Utils.clone2DMatrix(Constante.blocksMaps[mapIndex]);
		gomesMatrix = Constante.buildGomeMap(mapIndex);
	}
	
	private void resetMaps(int cnt, int index) {
		blocksMatrix = Utils.clone2DMatrix(Constante.blocksMaps[mapIndex]);
		gomesMatrix = Utils.makeGomesForNewMap(blocksMatrix, gomesMatrix, cnt, mapIndex, index);
	}
	
	public boolean noMoreGomes() {
		for(int i = 0; i < gomesMatrix.length; i++) {
			for(int j = 0; j < gomesMatrix[0].length; j++) {
				if(gomesMatrix[i][j] == 1) return false;
			}
		}
		return true;
	}
	
	public boolean noMoreLife() {
		return pacman.getLife() <= 0;
	}
	
	public void updateGoneMatrix(int i, int j) {
		setGoneMatrixCell(i, j, 0);
	}
	
	public void updateAll() {
		int i = pacman.getMatrixPosition()[0], j = pacman.getMatrixPosition()[1];
		int v = getGoneMatrixCell(i,j);
		if(v != 0) {
			computeScore(v);
			updateGoneMatrix(i, j);
		}
		applyRules(i, j);
		
	}
	
	public void applyRules(int i, int j) {
		if(pacman.getState() == State.INVISIBLE) return;
		
		for(int n = 0; n < Constante.NUMBER_OF_GHOST; n++) {
			int k = ghosts[n].getMatrixPosition()[0], m = ghosts[n].getMatrixPosition()[1];
			if(i==k && j==m) {
				System.out.println("Same pos for Pac and Ghost " + n);
				if(pacman.getState() == State.SUPER) {
					ghosts[n].startJailTimeContDown();
					System.out.println("Ghost " + n + " is in Jail");
				}else if(pacman.getState() == State.NORMAL) {
					System.out.println("Ghost " + n + " caught Pacman");
					pacman.loseLife();
					sleep(300);
					partialReset();
				}
			}
		}
	}
	
	public void computeScore(int v) {
		if(v == 1) {
			score += 100;
		}else if(v == 2) {
			score += 300;
			pacman.specialState(State.INVISIBLE);
			System.out.println("Invisible Pacman");
		}else if(v == 3) {
			score += 500;
			pacman.specialState(State.SUPER);
			slowdownGhosts();
			System.out.println("Super Pacman");
		}else if(v == 4) {
			score += 1000;
			int index = mapIndex;
			mapIndex++;
			mapIndex %= Constante.blocksMaps.length;
			sleep(100);
			partialReset();
			int cnt = Utils.howMuchBaseGomes(gomesMatrix);
			resetMaps(cnt, index);
		}
		
		if(score >= 5000) pacman.addLife();
	}
	
	private int getGoneMatrixCell(int i, int j) {
		return gomesMatrix[j][i];
	}
	private void setGoneMatrixCell(int i, int j, int v) {
		gomesMatrix[j][i] = v;
	}
	
	public void moveGhosts() {
		for(int i = 0; i < Constante.NUMBER_OF_GHOST; i++) {
			ghosts[i].move(mapIndex);
		}
	}
	
	public void slowdownGhosts() {
		for(int i = 0; i < Constante.NUMBER_OF_GHOST; i++) {
			ghosts[i].slowdown();
		}
	}
	
	public void manageGhosts() {
		for(int i = 0; i < Constante.NUMBER_OF_GHOST; i++) {
			ghosts[i].manage();
		}
	}
	
	public void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void printScore() {
		System.out.println("Your score : "+ score);
	}
	
	public void quit() {
		maze.close();
	}
}
