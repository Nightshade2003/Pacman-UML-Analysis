package Gui;
import java.util.ArrayList;
import java.util.List;

public class Transformers {
	private List<Mapper> mapping = new ArrayList<>();
	private List<Mapper> v1 = new ArrayList<>();
	private List<Mapper> v2 = new ArrayList<>();
	private int version = 1;
	
	public Transformers(){
		List<Shapes> s;
		//A
		s = new ArrayList<>();
		s.add(new Shapes(Lines.TWO_VERTICAL, 1, 5, 0, 0, 2));
		s.add(new Shapes(Lines.TWO_HORIZONTAL, 1, 2, 1, 0, 1));
		mapping.add(new Mapper("A",s,9));
		//C
		s = new ArrayList<>();
		s.add(new Shapes(Lines.VERTICAL, 2, 10, 0, 0));
		s.add(new Shapes(Lines.TWO_HORIZONTAL, 2, 0, 6));
		mapping.add(new Mapper("C",s,8));
		//M
		s = new ArrayList<>();
		s.add(new Shapes(Lines.VERTICAL, 2, 10, 0, 0));
		s.add(new Shapes(Lines.VERTICAL, 1, 2, 2, 0));
		s.add(new Shapes(Lines.VERTICAL, 1, 2, 1, 2));
		s.add(new Shapes(Lines.VERTICAL, 1, 2, 1, 2));
		s.add(new Shapes(Lines.VERTICAL, 1, 2, 1, -2));
		s.add(new Shapes(Lines.VERTICAL, 1, 2, 1, -2));
		s.add(new Shapes(Lines.VERTICAL, 2, 10, 1, 0));
		mapping.add(new Mapper("M",s,5));
		//N
		s = new ArrayList<>();
		s.add(new Shapes(Lines.VERTICAL, 2, 10, 0, 0));
		s.add(new Shapes(Lines.VERTICAL, 1, 2, 2, 0));
		s.add(new Shapes(Lines.VERTICAL, 1, 2, 1, 2));
		s.add(new Shapes(Lines.VERTICAL, 1, 2, 1, 2));
		s.add(new Shapes(Lines.VERTICAL, 1, 2, 1, 2));
		s.add(new Shapes(Lines.VERTICAL, 1, 2, 1, 2));
		s.add(new Shapes(Lines.VERTICAL, 2, 10, 1, -8));
		mapping.add(new Mapper("N",s,5));
		//P
		s = new ArrayList<>();
		s.add(new Shapes(Lines.VERTICAL, 2, 10));
		s.add(new Shapes(Lines.TWO_HORIZONTAL,2, 4, 2, 0, 2));
		s.add(new Shapes(Lines.VERTICAL, 2, 6, 4, 0));
		mapping.add(new Mapper("P",s,5));
		//V
		s = new ArrayList<>();
		s.add(new Shapes(Lines.VERTICAL, 2, 4, 0, 0));
		s.add(new Shapes(Lines.VERTICAL, 2, 4, 2, 4));
		s.add(new Shapes(Lines.VERTICAL, 2, 2, 2, 4));
		s.add(new Shapes(Lines.VERTICAL, 2, 4, 2, -4));
		s.add(new Shapes(Lines.VERTICAL, 2, 4, 2, -4));
		v1.add(new Mapper("V",s,10));
		v2.add(new Mapper("V",s,10));
		//1
		s = new ArrayList<>();
		s.add(new Shapes(Lines.TWO_HORIZONTAL, 2, 3, 0, 0, 6, 1, true));
		s.add(new Shapes(Lines.VERTICAL, 2, 10, 3, 0));
		s.add(new Shapes(Lines.HORIZONTAL, 2, 3, 2, 8));
		v1.add(new Mapper("1",s,4));
		//2
		s = new ArrayList<>();
		s.add(new Shapes(Lines.TWO_HORIZONTAL, 2, 8, 0, 0, 2, 1, true));
		s.add(new Shapes(Lines.TWO_HORIZONTAL, 2, 8, 0, 6, 2, 1, true));
		s.add(new Shapes(Lines.VERTICAL, 2, 2, 8, -6, 0, 1, false));
		s.add(new Shapes(Lines.VERTICAL, 2, 2, -8, 6, 0, 1, false));
		v2.add(new Mapper("2",s,10));
	}
	
	public void switchVersion() {
		version = (version % 2) + 1;
	}
	
	public List<Mapper> getMapping(){
		return mapping;
	}
	
	public List<Mapper> getVersion(){
		if(version == 2) {
			return v2;
		}
		return v1;
	}
	
	class Mapper{
		public String letter;
		public List<Shapes> shapes = new ArrayList<>();
		int add = 2;
		
		public Mapper(String l, List<Shapes> s, int a) {
			letter = l;
			shapes = s;
			add = a;
		}
		
		public List<Shapes> getShapes(){
			return shapes;
		}
		public String getLetter(){
			return letter;
		}
		public int getAdd(){
			return add;
		}
	}
}
