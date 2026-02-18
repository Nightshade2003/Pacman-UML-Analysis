package Gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import Constantes.Constante;
import Gui.Transformers.Mapper;

public class Structure {
	private Point START = new Point(3,4);
	private Point VSTART = new Point(15,10);
	private List<String> design = new ArrayList<>(List.of("A"));
	private Transformers transformer = new Transformers();
	List<Mapper> customMap = new ArrayList<>();
	
	public Structure() {
		updateDesign(design);
	}
	public Structure(List<String> d) {
		design = d;
		updateDesign(d);
	}
	
	public void updateDesign(List<String> d) {
		for(String l: d) {
			for(Mapper map: transformer.getMapping()) {
				if(l.equals(map.letter)) customMap.add(map);
			}
		}
	}
	
	public void drawDesigns(Graphics g, Point start, List<Mapper> maps, int size) {
		Point first = new Point(start.x, start.y);
		for(Mapper map: maps) {
			Point p = new Point(first.x, first.y);
			System.out.println("Drawing letter : '"+map.letter+"'");
			for(Shapes sh: map.getShapes()) {
				p = new Point(p.x+sh.getXplus(), p.y+sh.getYplus());
				drawLines( g,  sh,  p, size);
			}
			first = new Point(p.x+map.getAdd(), first.y);
		}
	}
	
	public void drawLines(Graphics g, Shapes sh, Point p, int size) {
		if(sh.getLines() == Lines.VERTICAL) {
			drawBlocks(g, p, sh.getNbMin(), sh.getNbMax(), size);
		}else if(sh.getLines() == Lines.HORIZONTAL) {
			drawBlocks(g, p, sh.getNbMax(), sh.getNbMin(), size);
		}else if(sh.getLines() == Lines.TWO_VERTICAL) {
			drawBlocks(g, p, sh.getNbMin(), sh.getNbMax(), size);
			Point x = new Point(p.x+sh.getNbMin()+sh.getInBetween(), p.y);
			drawBlocks(g, x, sh.getNbMin(), sh.getNbMax(), size);
		}else if(sh.getLines() == Lines.TWO_HORIZONTAL) {
			drawBlocks(g, p, sh.getNbMax(), sh.getNbMin(), size);
			Point x  = new Point(p.x, p.y+sh.getNbMin()+sh.getInBetween());
			drawBlocks(g, x, sh.getNbMax(), sh.getNbMin(), size);
		}
	}
	
	public void drawBlocks(Graphics g, Point p, int w, int h, int size) {
		g.fillRect(p.x*size, p.y*size, w*size, h*size);
	}
	
	
	public void drawStrucure(Graphics g, int size) {
		//g.setColor(Color.black);
		//drawDesigns(g, START, customMap, size);
		//drawDesigns(g, VSTART, transformer.getVersion(), size);
		drawMap(g, size);
	}
	
	public void drawMap(Graphics g, int size) {
		double scale = 0.3;
		for(int i = 0; i < Constante.blocksMap.length; i++) {
			for(int j = 0; j < Constante.blocksMap[i].length; j++) {
				//Blocks
				if(Constante.blocksMap[i][j] == 1) {
					g.setColor(Color.black);
					g.fillRect(j*size, i*size, size, size);
				}else if(Constante.blocksMap[i][j] == 3) {
					g.setColor(Color.red);
					g.drawLine(j*size, i*size, (j+1)*size, i*size);
				}
				/*
				//Pacgomes
				if(Constante.gomeMap[i][j] == 1) {
					g.setColor(Color.blue);
					g.fillOval((int) ((j+0.5)*size-scale), (int) ((i+0.5)*size-scale), (int) (size*scale), (int) (size*scale));
				}
				*/
			}
		}
	}
	
}

