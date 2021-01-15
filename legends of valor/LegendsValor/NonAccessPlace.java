
public class NonAccessPlace extends UnitPlace{
	
	public NonAccessPlace() {
		super("Inaccessible Place","I",false);
		this.setType("NonAccessPlace");
	}
	
	public NonAccessPlace(int x, int y) {
		super("Inaccessible Place", "I", false, x, y);
		this.setType("NonAccessPlace");
	}

}
