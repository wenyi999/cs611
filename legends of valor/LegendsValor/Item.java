
public class Item implements Cloneable{
	
	private String name;
	private int id;
	private int price;
	private int levelLimit;
	private String type;
	
	public Item() {
		this.setName("default item");
		this.setId(0);
		this.setPrice(0);
		this.setLevelLimit(1);
		this.setType("regualar");
	}
	public Item(String n, int i, int p, int l) {
		this.setName(n);
		this.setId(i);
		this.setPrice(p);
		this.setLevelLimit(l);
		this.setType("regualar");
	}
	
	// override clone method
	@Override
	public Item clone() {
		Item it = null;
		try {
			it = (Item)super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		return it;
	}
	
	
	// get and set methods
	public void setName(String n) {
		this.name = n;
	}
	
	public String getName() {
		return this.name;
	}

	public void setId(int i) {
		if(i>=0) {
			this.id = i;
		}else {
			this.id = 0;
			System.out.println("the id of item must be over 0, now apply 0 to id");
		}
		
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setPrice(int p) {
		if(p>=0) {
			this.price = p;
		}else {
			this.price = 0;
			System.out.println("the price of item must be over 0, now apply 0 to price");
		}
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public void setLevelLimit(int l) {
		if(l>=1) {
			this.levelLimit = l;
		}else {
			this.levelLimit = 1;
			System.out.println("the level limit of item must be over 1, now apply 1 to level limit");
		}
	}
	
	public int getLevelLimit() {
		return this.levelLimit;
	}
	
	public void setType(String t) {
		this.type = t;
	}
	
	public String getType() {
		return this.type;
	}

}
