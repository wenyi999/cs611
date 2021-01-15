import java.util.Date;

public class CheckingAccount extends Account{
	
	private double service_fee; // every time user makes transaction we charge them for service fee
	
	public CheckingAccount() {
		super();
		this.setType(2); // checking account's type is 2
		this.setSeriveFee(0.01);
	}
	
	public CheckingAccount(int id, DigitMoney dgm, Date starttime,double servicefee) {
		super(id, dgm, starttime, 2);
		this.setSeriveFee(servicefee);
	}
	
	
	public void setSeriveFee(double sv) {
		this.service_fee = sv;
	}
	
	public double getServiceFee() {
		return this.service_fee;
	}
	
	// override withdraw method to take service fee into account
	public int withdraw(DigitMoney money) {
		// first need to charge for service fee
		double start_money_num = money.getMoney_Num(); 
		double service = money.getMoney_Num()*this.service_fee;
		money.setMoney_Num(start_money_num+service);
		// then withdraw it from balance
		int success = this.getBalance().decrease(money);
		
		return success;
	}

}
