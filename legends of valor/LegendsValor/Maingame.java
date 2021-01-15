import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Maingame {

	public static void main(String[] args) {
		
		//List<Hero> herolist = new ArrayList<Hero>();
		//herolist.add(new Hero());
		//herolist.add(new Hero());
		//herolist.add(new Hero());
		//LegendMap testMap = new LegendMap(2,8,herolist);
		//String s = testMap.toString();
	    
		//System.out.println(s);
		

		LegendGame theLegend = new LegendGame(12,12);
		theLegend.startGame();

	}
	
	
}
