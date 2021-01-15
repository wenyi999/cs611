
public class DigitMoney {

	
	private double money_num; // the number of money
	
	private Currency related_currency; // the current currency of the money
	
	public DigitMoney() {
		this.setCurrency(USDollar.getInstance());
		this.setMoney_Num(10);
	}
	
	public DigitMoney(double num, Currency curr) {
		this.setCurrency(curr);
		this.setMoney_Num(num);
	}

	public double getMoney_Num() {
		return this.money_num;
	}
	
	public void setMoney_Num(double num) {
		this.money_num = num;
	}
	
	public Currency getCurrency() {
		return this.related_currency;
	}
	
	public void setCurrency(Currency c) {
		this.related_currency = c;
	}
	
	// the method to exchange currency
	public void exchangeTo(Currency targetCurrency) {
		double num_before = this.getMoney_Num();
		double num_after;
		// compute the money num after exchange
		num_after = num_before*this.getCurrency().getRate()/targetCurrency.getRate();
		
		this.setMoney_Num(num_after);
		this.setCurrency(targetCurrency);
	}
	
	//return 1 means add success
	public int add(DigitMoney incMoney) {
		incMoney.exchangeTo(this.getCurrency());
		this.setMoney_Num(this.money_num+incMoney.getMoney_Num());
		
		return 1;
	}
	
	// return 1 means decrease success, return 0 means fail
	public int decrease(DigitMoney decMoney) {
		int success = 1;
		decMoney.exchangeTo(this.getCurrency());
		if(decMoney.getMoney_Num()<=this.money_num) {
			this.setMoney_Num(this.money_num-decMoney.getMoney_Num());
		}else {
			success = 0;
		}
		return success;
	}
	
	public String toString() {
		
//		String num = String.valueOf(money_num);
		
		String num = String.format("%.1f", money_num);
		
		if(this.getCurrency() instanceof CHYen) {
			num = "Y"+num; 
		}else if(this.getCurrency() instanceof USDollar) {
			num = "$"+num;
		}else if(this.getCurrency() instanceof EuroDollar) {
			num = "E"+num;
		}
		
		return num;
		
	}
}
