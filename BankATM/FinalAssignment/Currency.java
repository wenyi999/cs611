
public abstract class Currency {
	private String currency_name; // the name of the currency
    private double exchange_rate; // the absolute rate
    
    public Currency() {
    	this.setName("a currency");
    	this.setRate(1);
    }
    
    public Currency(String n, double rate) {
    	this.setName(n);
    	this.setRate(rate);
    }

    public void setName(String n) {
    	this.currency_name = n;
    }
    
    public String getName() {
    	return this.currency_name;
    }
    
    public void setRate(double r) {
    	this.exchange_rate = r;
    }
    
    public double getRate() {
    	return this.exchange_rate;
    }
}
