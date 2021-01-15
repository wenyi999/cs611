 import java.util.Date;

public class Account {
	
	private int account_id;
	
	private DigitMoney balance;
	
	private Date start_time; //YYYY-MM-DD, when the account is opened
	
	private int type; // 1 for saving account, 2 for checking account

	private int currency;
	// default constructor
	public Account() {
		this.setAccountId(0);
		this.setBalance(new DigitMoney());
		this.setStartTime(new Date());
		this.setType(1);
	}
	
	public Account(int id, DigitMoney dgm, Date stime, int t) {
		this.setAccountId(id);
		this.setBalance(dgm);
		this.setStartTime(stime);
		this.setType(t);
	}
	
	
	// get and set method
	public void setAccountId(int id) {
		this.account_id = id;
	}
	
	public int getAccountId() {
		return this.account_id;
	}
	
	public void setBalance(DigitMoney bl) {
		this.balance = bl;
	}
	
	public DigitMoney getBalance() {
		return this.balance;
	}
	
	public void setStartTime(Date stime) {
		this.start_time = stime;
	}
	
	public Date getStartTime() {
		return this.start_time;
	}
	
	public void setType(int i) {
		this.type = i;
	}
	
	public int getType() {
		return this.type;
	}

	// money deposit and withdraw and transfer method
	
	public int deposit(DigitMoney money) {

		int success = this.getBalance().add(money);	
		
		return success;

	}
	
	public int withdraw(DigitMoney money) {

        int success = this.getBalance().decrease(money);	
		
		return success;
	}
	
	public void transfer(DigitMoney money, Account targetAccount) {
		this.withdraw(money); // reduce this account
		targetAccount.deposit(money); // increase target account.
	}
	
	public void exchangeCurrency(Currency targetCurrency) {
		// transfer the money to target currency
		this.getBalance().exchangeTo(targetCurrency);
	}

}
