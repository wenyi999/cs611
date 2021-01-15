
public class GeneralMap {
	
	private String name;

	private int mapLength;
	
	private int mapWidth;
	
	public GeneralMap() {
		this.setName("a general map");
		this.setMapWidth(8);
		this.setMapLength(8);
	}
	
	public GeneralMap(String n,int w, int l) {
		this.setName(n);
		this.setMapWidth(w);
		this.setMapLength(l);
	}
	
	
	public void setName(String n) {
		this.name = n;
	}
	public String getName() {
		return this.name;
	}
	
	public void setMapLength(int len) {
		if(len>=8) {
			this.mapLength = len;
		}else {
			this.mapLength = 8;
		}// the length of a map can't be negative
	}
	
	public int getMapLength() {
		return this.mapLength;
	}
	
	public void setMapWidth(int wid) {
		if(wid>=8) {
			this.mapWidth = wid;
		}else {
			this.mapWidth = 8;
		}// the width of a map can't be negative
	}
	
	public int getMapWidth() {
		return this.mapWidth;
	}
	
	

}
