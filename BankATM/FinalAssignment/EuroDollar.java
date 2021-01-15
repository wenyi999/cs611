
public class EuroDollar extends Currency{
	
	private static final EuroDollar EuroDollarInstance = new EuroDollar();

	private EuroDollar() {
		super("EuroDollar",10);
	}
	
	public static EuroDollar getInstance() {
		return EuroDollarInstance;
	}

}
