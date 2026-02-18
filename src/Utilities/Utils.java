package Utilities;

import java.awt.Dimension;
import java.awt.Point;

import Constantes.Constante;

public class Utils {
	public static int[][] clone2DMatrix(int[][] mat){
		int x = mat.length;
		int y = mat[0].length;
		int[][] clone = new int[x][y];
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				clone[i][j] = mat[i][j];
			}
		}
		return clone;
	}
	
	public static Point clonePoint(Point p){
		return new Point(p.x, p.y);
	}
	
	public static Dimension cloneDimension(Dimension d, int w, int h){
		return new Dimension(d.width+w, d.height+h);
	}
	
	public static int howMuchBaseGomes(int[][] mat) {
		int x = mat.length;
		int y = mat[0].length;
		int cnt = 0;
		for(int i = 0; i < x; i++) {
			for(int j = 0; j < y; j++) {
				if(mat[i][j] == 1) cnt++;
			}
		}
		
		return cnt;
	}
	
	public static int[][] makeGomesForNewMap(int[][] mat, int[][] gom, int cnt, int index, int index1){
		int x = mat.length;
		int y = mat[0].length;
		int[][] clone = new int[x][y];
		
		for(int i = 0; i < x & cnt > 0; i++) {
			for(int j = 0; j < y & cnt > 0; j++) {
				
				if(mat[i][j] == 0) {
					clone[i][j] = 1;
					cnt--;
				}
			}
		}
		
		int[][]xs = Constante.xs, ys = Constante.ys, vs = Constante.vs;
		
		//Constante.addSpecialGomes(index, map);
		for(int i = 0; i < vs[0].length; i++) {
			clone[xs[index][i]][ys[index][i]] = gom[xs[index1][i]][ys[index1][i]];
			//Constante.addSpecialGomes(index, clone);
		}
		
		return clone;
	}
	
	
	
}
