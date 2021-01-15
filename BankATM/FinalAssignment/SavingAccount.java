import java.util.Date;

public class SavingAccount extends Account{
	
	private double interest; // the interest of day
	
	
	public SavingAccount() {
		super();
		this.setType(1);
		this.setInterest(0.001);
	}
	
	public SavingAccount(int id, DigitMoney dgm, Date stime, double intere) {
		super(id,dgm,stime,1); // 1 means saving account
		this.setInterest(intere);
	}
	
	public void setInterest(double itst) {
		this.interest = itst;
	}
	
	public double getInterest() {
		return this.interest;
	}
	
	public DigitMoney calculateCurrentBalance() {
		Date currentDate = new Date(); // get current date
		
		// calculate the days after open this account
		int days = (int) ((currentDate.getTime() - this.getStartTime().getTime()) / (24 * 60 * 60 * 1000));
		
		for(int i = 0;i<days;i++) {
			DigitMoney currentBalance = this.getBalance();
			double currentNum = currentBalance.getMoney_Num();
			currentBalance.setMoney_Num(currentNum*(1+this.interest)); // compute the balance after interested
			this.setBalance(currentBalance);
		}
		
		
		return this.getBalance(); // return the digitMoney
	}
	
	

}
