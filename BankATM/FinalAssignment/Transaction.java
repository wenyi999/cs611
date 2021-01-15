import java.util.Date;

public class Transaction {
	
	private Account start_Account; // the account that starts this transaction
	private Account receive_Account; // the account that receives this transaction
	private DigitMoney transaction_money; // the amount of money in this transaction
	private Date transaction_time; // the time of the transaction
	
	// no-argument constructor, hardly use this one, only for test 
	public Transaction() {
		this.setStartAccount(new Account());
		this.setReceiveAccount(new Account());
		this.setMoney(new DigitMoney());
		this.setTime(new Date());
	}
	
	public Transaction(Account start, Account receive, DigitMoney money, Date transac_time) {
		this.setStartAccount(start);
		this.setReceiveAccount(receive);
		this.setMoney(money);
		this.setTime(transac_time);
	}
	
	
	public void setStartAccount(Account startAccount) {
		this.start_Account = startAccount;
	}
	
	public Account getStartAccount() {
		return this.start_Account;
	}
	
	public void setReceiveAccount(Account receiveAccount) {
		this.receive_Account = receiveAccount;
	}
	
	public Account getReceiveAccount() {
		return this.receive_Account;
	}
	
	public void setMoney(DigitMoney transacMoney) {
		this.transaction_money = transacMoney;
	}
	
	public DigitMoney getMoney() {
		return this.transaction_money;
	}
	
	public void setTime(Date transacTime) {
		this.transaction_time = transacTime;
	}
	
	public Date getTime() {
		return this.transaction_time;
	}

}
