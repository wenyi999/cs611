
public class USDollar extends Currency{
	
	private static final USDollar USDollarInstance = new USDollar();

	private USDollar() {
		super("USDollar",7);
	}
	
	public static USDollar getInstance() {
		return USDollarInstance;
	}
}
